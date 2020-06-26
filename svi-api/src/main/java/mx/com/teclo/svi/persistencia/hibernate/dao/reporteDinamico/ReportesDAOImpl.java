package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;

@SuppressWarnings("unchecked")
@Repository
public class ReportesDAOImpl extends BaseDaoHibernate<ReportesDTO> implements ReportesDAO{
	
	
	@Transactional
	@Override
	public Long selectMaximo() {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.setProjection(Projections.max("idReporte"));
		return (Long)c.uniqueResult();
	}

	
	@Value("${app.config.codigo}")
	private String codigo;
	
	@Override
	@Transactional
	public ReportesDTO getReporteById(Long idReporte, String cdApp) {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.add(Restrictions.eq("idReporte", idReporte));
		c.add(Restrictions.eq("stActivo", 1));
		return (ReportesDTO)c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ReportesDTO> obtrenerReportesActivos() {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.add(Restrictions.eq("aplicacion",14L));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ReportesDTO>)c.list();
	}

	@Override
	@Transactional
	public ReportesDTO obtenerReporteBy(Long idReporte) {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.add(Restrictions.eq("idReporte", idReporte));
		c.add(Restrictions.eq("stActivo",1));
		return (ReportesDTO)c.uniqueResult();
	}

}
