package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;



@Repository
public class ParametrosPropDAOImpl extends BaseDaoHibernate<ParametrosPropDTO> implements ParametrosPropDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosPropDTO> getParametrosPropiedad(Long idParametro) {
		Criteria c = getCurrentSession().createCriteria(ParametrosPropDTO.class);
		c.createAlias("parametro", "p");
		c.add(Restrictions.eq("p.idParamtro", idParametro));
		c.add(Restrictions.eq("p.stActivo", 1));
		c.add(Restrictions.eq("stActivo",1));
		return (List<ParametrosPropDTO>)c.list();
	}

	@Override
	public ParametrosPropDTO getParametroPropiedad(Long idParametro, Long idPropiedad) {
		Criteria c = getCurrentSession().createCriteria(ParametrosPropDTO.class);
		c.createAlias("parametro", "p");
		c.createAlias("propiedad", "prop");
		c.add(Restrictions.eq("p.idParamtro", idParametro));
		c.add(Restrictions.eq("prop.idPropiedad", idPropiedad));
		return (ParametrosPropDTO)c.uniqueResult();
	}

}
