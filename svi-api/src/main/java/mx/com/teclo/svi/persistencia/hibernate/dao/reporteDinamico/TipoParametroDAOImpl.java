package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParametroDTO;

@Repository("TipoParametroDAO")
public class TipoParametroDAOImpl extends BaseDaoHibernate<TipoParametroDTO> implements TipoParametroDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParametroDTO> listaTiposParametro() {
		Criteria c = getCurrentSession().createCriteria(TipoParametroDTO.class);
		List<TipoParametroDTO> listaTipoParam = c.list();
		return listaTipoParam;
	}

	@Override
	public TipoParametroDTO gettipoParametroById(Long idTipoParametro) {
		Criteria c = getCurrentSession().createCriteria(TipoParametroDTO.class);
		c.add(Restrictions.eq("idTipoParametro", idTipoParametro));
		c.add(Restrictions.eq("stActivo", 1));
		return (TipoParametroDTO)c.uniqueResult();
	}

}
