package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleValidadorDTO;

@Repository("DetalleValidadorDAO")
public class DetalleValidadorDAOImpl extends BaseDaoHibernate<DetalleValidadorDTO> implements DetalleValidadorDAO {

	@Override
	public Long countByIdRegistroCSV(Long idRegistroCsv) {
		Criteria criteria = getCurrentSession().createCriteria(DetalleValidadorDTO.class, "validacion");
		criteria.createAlias("validacion.idRegistroCSV", "detEva");		
		criteria.add(Restrictions.eq("detEva.idRegistroCsv", idRegistroCsv));
		criteria.add(Restrictions.eq("validacion.stActivo", BigDecimal.ONE.longValue()));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();		
	}

}
