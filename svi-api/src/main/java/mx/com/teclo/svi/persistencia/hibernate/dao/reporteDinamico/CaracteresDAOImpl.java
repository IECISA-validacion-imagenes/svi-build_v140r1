package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.CaracteresDTO;

@Repository
public class CaracteresDAOImpl extends BaseDaoHibernate<CaracteresDTO> implements CaracteresDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CaracteresDTO> getCaracteresSeperacion() {
		Criteria c = getCurrentSession().createCriteria(CaracteresDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<CaracteresDTO>)c.list();
	}

	
	
}
