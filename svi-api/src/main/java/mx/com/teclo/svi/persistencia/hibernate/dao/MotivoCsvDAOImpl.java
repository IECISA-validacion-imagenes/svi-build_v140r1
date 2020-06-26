package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoCsvDTO;

@SuppressWarnings("unchecked")
@Repository("motivoCsvDAO")
public class MotivoCsvDAOImpl extends BaseDaoHibernate<MotivoCsvDTO> implements MotivoCsvDAO {
	@Override
	public Long getMaxCicloByIdArchivoCsv(Long idArchivoCsv) {

		Criteria criteria = getCurrentSession().createCriteria(MotivoCsvDTO.class, "motivo");
		criteria.createAlias("motivo.idArchivoCsv", "csv");
		criteria.add(Restrictions.eq("csv.idArchivoCsv", idArchivoCsv));
		criteria.add(Restrictions.eq("motivo.stActivo", Boolean.TRUE.booleanValue()));

		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlProjection("coalesce(max(ID_CICLO_VALIDACION), 1) as maximoCiclo",
						new String[] { "maximoCiclo" },
						new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }), "maximoCiclo"));
		return (Long) criteria.uniqueResult();
	}
}
