package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TablasDTO;

@Repository
public class TablasDAOImpl extends BaseDaoHibernate<TablasDTO> implements TablasDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TablasDTO> getCatalogoTablas() {
		Criteria c = getCurrentSession().createCriteria(TablasDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<TablasDTO>) c.list();
	}

}
