package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoTitulosDTO;

@Repository("TipoTituloDAO")
public class TipoTitulosDAOImpl extends BaseDaoHibernate<TipoTitulosDTO> implements TipoTitulosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTitulosDTO> listaTipoTitulo() {
		Criteria c = getCurrentSession().createCriteria(TipoTitulosDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		List<TipoTitulosDTO> listTipoTituloDTO = c.list();
		return listTipoTituloDTO;
	}

	@Override
	public TipoTitulosDTO getTipoTituloById(Long idTipoTitulo) {
		Criteria c = getCurrentSession().createCriteria(TipoTitulosDTO.class);
		c.add(Restrictions.eq("idTipoTitulo", idTipoTitulo));
		c.add(Restrictions.eq("stActivo", 1));
		return (TipoTitulosDTO) c.uniqueResult();
	}

}
