package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;

@Repository
public class ParametrosDAOImpl extends BaseDaoHibernate<ParametrosDTO> implements ParametrosDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosDTO> getParametrosByReporte(Long idReporte) {
		Criteria c = getCurrentSession().createCriteria(ParametrosDTO.class);
		c.createAlias("reporteDTO", "rep");
		c.add(Restrictions.eq("rep.idReporte", idReporte));
		c.add(Restrictions.eq("stActivo", 1));
		c.addOrder(Order.asc("nuOrden"));
		return (List<ParametrosDTO>) c.list();
	}

	@Override
	public ParametrosDTO getParametrosById(Long idParametro) {
		Criteria c = getCurrentSession().createCriteria(ParametrosDTO.class);
		c.add(Restrictions.eq("idParamtro", idParametro));
		return (ParametrosDTO)c.uniqueResult();
	}

}
