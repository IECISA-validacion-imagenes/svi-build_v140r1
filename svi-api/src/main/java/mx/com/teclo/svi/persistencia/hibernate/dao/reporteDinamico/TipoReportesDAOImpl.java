package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoReportesDTO;

@Repository("TipoReporteDAO")
public class TipoReportesDAOImpl extends BaseDaoHibernate<TipoReportesDTO> implements TipoReportesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoReportesDTO> listaTipoReporte() {
		Criteria c = getCurrentSession().createCriteria(TipoReportesDTO.class);
		List<TipoReportesDTO> tipoReporte = c.list();
		return tipoReporte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoReportesDTO> getAllReports() {
		Criteria c = getCurrentSession().createCriteria(TipoReportesDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<TipoReportesDTO>) c.list();
	}

	@Override
	@Transactional
	public TipoReportesDTO getTipoReporteById(Long idTipoReporte) {
		Criteria c = getCurrentSession().createCriteria(TipoReportesDTO.class);
		c.add(Restrictions.eq("idTipoReporte", idTipoReporte));
		c.add(Restrictions.eq("stActivo", 1));
		return (TipoReportesDTO) c.uniqueResult();
	}

}
