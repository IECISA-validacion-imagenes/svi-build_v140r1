package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;

@SuppressWarnings("unchecked")
@Repository
public class PropiedadesCompDAOImpl extends BaseDaoHibernate<PropiedadesCompDTO> implements PropiedadesCompDAO{

	
	@Override
	@Transactional
	public List<PropiedadesCompDTO> getComponentesPropiedad(Long idComponente) {
		Criteria c = getCurrentSession().createCriteria(PropiedadesCompDTO.class);
		c.createAlias("componente", "c");
		c.add(Restrictions.eq("c.idComponente", idComponente));
		c.add(Restrictions.eq("c.stActivo", 1));
		c.add(Restrictions.eq("stActivo",1));
		return (List<PropiedadesCompDTO>)c.list();
	}

	@Override
	@Transactional
	public List<PropiedadesCompDTO> getComponentesPropiedad() {
		Criteria c = getCurrentSession().createCriteria(PropiedadesCompDTO.class);
		c.add(Restrictions.eq("stActivo",1));
		return (List<PropiedadesCompDTO>)c.list();
	}
	
	
}
