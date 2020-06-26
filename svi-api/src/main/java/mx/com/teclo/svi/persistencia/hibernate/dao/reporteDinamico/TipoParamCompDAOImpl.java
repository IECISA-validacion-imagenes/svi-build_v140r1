package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParamCompDTO;

@Repository
public class TipoParamCompDAOImpl extends BaseDaoHibernate<TipoParamCompDTO> implements TipoParamCompDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParamCompDTO> getRelacionParamComponen() {
		Criteria c = getCurrentSession().createCriteria(TipoParamCompDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<TipoParamCompDTO>) c.list();
	}

}
