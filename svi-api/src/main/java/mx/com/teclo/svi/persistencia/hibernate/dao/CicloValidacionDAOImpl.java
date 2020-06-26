package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.supervision.CicloAnteriorVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.CicloValidacionDTO;

@Repository("cicloValidacionDAO")
public class CicloValidacionDAOImpl extends BaseDaoHibernate<CicloValidacionDTO> implements CicloValidacionDAO	{

	@Transactional(readOnly = true)
	@Override
	public CicloValidacionDTO getCicloVigente() {	
		Criteria criteria  = getCurrentSession().createCriteria(CicloValidacionDTO.class);
		criteria.add(Restrictions.eq("stActivo", 1L)); //debe estar activo 
		criteria.add(Restrictions.eq("stVigente",1L)); //
		return (CicloValidacionDTO) criteria.uniqueResult();
	}
	
	@Override
	public Boolean isCicloAnteriorCerrado() {
		CicloAnteriorVO cicloAnteriorVO = contarRevaldsPeriodoAnterior();		
		if (cicloAnteriorVO == null || cicloAnteriorVO.getIdCicloValidacion() == null) {
			return Boolean.FALSE.booleanValue();
		}
		// si el periodo anterior no es el primer ciclo
		if(cicloAnteriorVO.getIdCicloValidacion().longValue() > BigDecimal.ONE.longValue()) {
			return cicloAnteriorVO.getTotalReasignaciones().longValue() == cicloAnteriorVO.getTotalValidaciones();			
		} 
		CicloAnteriorVO primerCicloVO = contarValdsByPrimerCiclo();
		if(primerCicloVO ==  null || primerCicloVO.getTotalAsignaciones() == null) {
			return Boolean.FALSE.booleanValue();
		}
		return primerCicloVO.getTotalAsignaciones().longValue() == cicloAnteriorVO.getTotalValidaciones().longValue();
		
	}


	private CicloAnteriorVO contarRevaldsPeriodoAnterior() {
		DetachedCriteria idCicloVigente = DetachedCriteria.forClass(CicloValidacionDTO.class, "vigente");	
		idCicloVigente.add(Restrictions.eq("vigente.stActivo", 1L)); 
		idCicloVigente.add(Restrictions.eq("vigente.stVigente",1L)); 
		idCicloVigente.setProjection(Projections.property("vigente.idCicloValidacion"));
		
		Criteria qCicloAnterior = getCurrentSession().createCriteria(CicloValidacionDTO.class, "ciclo");
		qCicloAnterior.createAlias("ciclo.detReasignCollection", "reasignacion",JoinType.LEFT_OUTER_JOIN);
		qCicloAnterior.createAlias("reasignacion.idRegistroCSV", "registro", JoinType.LEFT_OUTER_JOIN);
		qCicloAnterior.createAlias("ciclo.detValidacionesCollection", "validacion", JoinType.LEFT_OUTER_JOIN);
	
		
		qCicloAnterior.add(Property.forName("idCicloValidacion").lt(idCicloVigente));
		qCicloAnterior.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("ciclo.idCicloValidacion").as("idCicloValidacion"))				
				.add(Projections.countDistinct("registro.idRegistroCsv").as("totalReasignaciones"))				
				.add(Projections.count("validacion.idReasignaVal").as("totalValidaciones")));
		qCicloAnterior.addOrder(Order.desc("ciclo.idCicloValidacion"));
		qCicloAnterior.setFirstResult(BigDecimal.ZERO.intValue());
		qCicloAnterior.setMaxResults(BigDecimal.ONE.intValue());
		qCicloAnterior.setResultTransformer(Transformers.aliasToBean(CicloAnteriorVO.class));
		return (CicloAnteriorVO) qCicloAnterior.uniqueResult();

	}
	
	private CicloAnteriorVO contarValdsByPrimerCiclo() {
		Criteria criteria = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class, "asignacion");
		criteria.add(Restrictions.eq("asignacion.stActivo", BigDecimal.ONE.shortValue())); 
		criteria.setProjection(Projections.projectionList()
				.add(Projections.count("asignacion.idRegistroCsv").as("totalAsignaciones")));
		criteria.setResultTransformer(Transformers.aliasToBean(CicloAnteriorVO.class));
		return (CicloAnteriorVO) criteria.uniqueResult();
	}
}
