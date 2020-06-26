package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.CatIncidenciasVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.CatIncidenciaDTO;

@Repository("catIncidenciaDAO")
public class CatalogoIncidenciasDAOImpl extends BaseDaoHibernate<CatIncidenciaDTO> implements CatalogoIncidenciasDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<CatIncidenciasVO> getCatIncidencias() {
		Criteria criteria = getCurrentSession().createCriteria(CatIncidenciaDTO.class, "incidencia");
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("incidencia.idIncidencia").as("idIncidencia"))
						.add(Projections.property("incidencia.txIncidencia").as("txIncidencia")));
		criteria.add(Restrictions.eq("incidencia.stActivo", Boolean.TRUE.booleanValue()));
		criteria.addOrder(Order.asc("incidencia.txIncidencia"));
		criteria.setResultTransformer(Transformers.aliasToBean(CatIncidenciasVO.class));
		return criteria.list();

	}
	
	@Override
	public String getResumenFiltro(List<Long> idsIncidencias) {
		if(idsIncidencias.isEmpty()) { return null; }
		Criteria criteria = getCurrentSession().createCriteria(CatIncidenciaDTO.class);
		criteria.add(Restrictions.in("idIncidencia", idsIncidencias));
		criteria.setProjection(Projections.projectionList()				
				.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(TX_INCIDENCIA)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();
	}

}
