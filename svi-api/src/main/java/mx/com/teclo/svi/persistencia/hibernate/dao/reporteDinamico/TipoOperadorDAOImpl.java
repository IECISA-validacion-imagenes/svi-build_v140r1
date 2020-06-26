package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;

@Repository
public class TipoOperadorDAOImpl extends BaseDaoHibernate<TipoOperadorDTO> implements TipoOperadorDAO {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TipoOperadorDTO> getCatTipoOperador() {
		Criteria c = getCurrentSession().createCriteria(TipoOperadorDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<TipoOperadorDTO>) c.list();
	}

}
