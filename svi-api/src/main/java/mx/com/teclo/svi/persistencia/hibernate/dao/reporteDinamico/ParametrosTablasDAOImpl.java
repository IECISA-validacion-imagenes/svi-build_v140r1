package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;

@Repository
public class ParametrosTablasDAOImpl extends BaseDaoHibernate<ParametrosTablasDTO> implements ParametrosTablasDAO{

	@Override
	@Transactional
	public ParametrosTablasDTO getParametrosTablas(Long idParametro) {
		ParametrosTablasDTO dto = null;
		Criteria c = getCurrentSession().createCriteria(ParametrosTablasDTO.class);
		c.createAlias("parametro", "p");
		c.add(Restrictions.eq("p.idParamtro", idParametro));
		c.add(Restrictions.eq("p.stActivo", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosTablasDTO)c.uniqueResult();
	}

	@Override
	@Transactional
	public ParametrosTablasDTO getParametrosTablaById(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosTablasDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("idParametroTabla", idParametroTabla));
		return (ParametrosTablasDTO)c.uniqueResult();
	}
}
