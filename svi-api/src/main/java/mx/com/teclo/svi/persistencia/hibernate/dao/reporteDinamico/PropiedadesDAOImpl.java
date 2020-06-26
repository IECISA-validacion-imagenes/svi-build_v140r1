package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesDTO;

@Repository("PropiedadesDAO")
public class PropiedadesDAOImpl extends BaseDaoHibernate<PropiedadesDTO> implements PropiedadesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PropiedadesDTO> listaPropiedades() {
		Criteria c = getCurrentSession().createCriteria(PropiedadesDTO.class);
		List<PropiedadesDTO> listPropiedadesDTO = c.list();
		return listPropiedadesDTO;
	}

	@Override
	public PropiedadesDTO getPropiedadById(Long idPropiedad) {
		Criteria c = getCurrentSession().createCriteria(PropiedadesDTO.class);
		c.add(Restrictions.eq("idPropiedad", idPropiedad));
		c.add(Restrictions.eq("stActivo", 1));
		return (PropiedadesDTO) c.uniqueResult();
	}

}
