package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;

@Repository("FormatoDescargaDAO")
public class FormatoDescargaDAOImpl extends BaseDaoHibernate<FormatoDescargaDTO> implements FormatoDescargaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<FormatoDescargaDTO> listaFormatoDescarga() {
		Criteria c = getCurrentSession().createCriteria(FormatoDescargaDTO.class);
		List<FormatoDescargaDTO> listaFormatoDesc = c.list();
		return listaFormatoDesc;
	}

	@Override
	public FormatoDescargaDTO getFormatoDescargaById(Long idFormatoDescarga) {
		Criteria c = getCurrentSession().createCriteria(FormatoDescargaDTO.class);
		c.add(Restrictions.eq("idFormatoDescarga", idFormatoDescarga));
		c.add(Restrictions.eq("stActivo", 1));
		return (FormatoDescargaDTO) c.uniqueResult();
	}

}
