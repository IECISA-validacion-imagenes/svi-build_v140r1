package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ComponentesDTO;


@Repository("ComponenteDAO")
public class ComponenteDAOImpl extends BaseDaoHibernate<ComponentesDTO> implements ComponenteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentesDTO> listaComponentes() {
		Criteria c =getCurrentSession().createCriteria(ComponentesDTO.class);
		List<ComponentesDTO> listComponentes = c.list();
		
		return listComponentes;
	}

	@Override
	public ComponentesDTO getComponentesById(Long idComponente) {
		Criteria c =getCurrentSession().createCriteria(ComponentesDTO.class);
		c.add(Restrictions.eq("idComponente", idComponente));
		c.add(Restrictions.eq("stActivo", 1));
		return (ComponentesDTO)c.uniqueResult();
	}

}
