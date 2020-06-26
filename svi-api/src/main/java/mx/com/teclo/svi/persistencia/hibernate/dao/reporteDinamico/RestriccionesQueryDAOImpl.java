package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.RestriccionesQueryDTO;

@Repository
public class RestriccionesQueryDAOImpl extends BaseDaoHibernate<RestriccionesQueryDTO> implements RestriccionesQueryDAO{

	@SuppressWarnings("unchecked")
	@Override
	public RestriccionesQueryDTO getRestriccion() {
		RestriccionesQueryDTO objectReturn= null;
		Criteria c = getCurrentSession().createCriteria(RestriccionesQueryDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		List<RestriccionesQueryDTO> listaRestriccions = c.list();
		if(!listaRestriccions.isEmpty()){
			objectReturn = listaRestriccions.get(0);
			return objectReturn;
		}
		return objectReturn;
	}

}
