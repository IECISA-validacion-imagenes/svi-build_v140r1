package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.AgrupacionHojasDTO;


@SuppressWarnings("unchecked")
@Repository
public class AgrupacionHojasDAOImpl extends BaseDaoHibernate<AgrupacionHojasDTO> implements AgrupacionHojasDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<AgrupacionHojasDTO> getReportesFormato() {
		Criteria c = getCurrentSession().createCriteria(AgrupacionHojasDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<AgrupacionHojasDTO>) c.list();
	}

	@Override
	public AgrupacionHojasDTO getTipoAgrupacionById(Long idTipoAgrupacion) {
		Criteria c = getCurrentSession().createCriteria(AgrupacionHojasDTO.class);
		c.add(Restrictions.eq("idTipoAgrupacion", idTipoAgrupacion));
		c.add(Restrictions.eq("stActivo", 1));
		return (AgrupacionHojasDTO)c.uniqueResult();
	}

}
