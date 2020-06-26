package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;

@SuppressWarnings("unchecked")
@Repository("ptClasifExpedientesDAO")
public class PtClasifExpedientesDAOImpl extends BaseDaoHibernate<PtClasifExpedientesDTO> implements PtClasifExpedientesDAO {

	@Override
	@Transactional
	public List<PtClasifExpedientesDTO> getClasificacionExpediente() {
		Criteria c = getCurrentSession().createCriteria(PtClasifExpedientesDTO.class);
		c.add(Restrictions.eq("stActivo",1L));
		c.addOrder(Order.asc("cdClasifExpe"));
		return (List<PtClasifExpedientesDTO>)c.list();
	} 

}
