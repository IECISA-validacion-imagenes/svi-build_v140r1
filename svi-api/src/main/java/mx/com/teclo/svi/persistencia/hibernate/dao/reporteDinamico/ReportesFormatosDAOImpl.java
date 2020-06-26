package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesFormatosDTO;

@Repository
public class ReportesFormatosDAOImpl extends BaseDaoHibernate<ReportesFormatosDTO> implements ReportesFormatosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportesFormatosDTO> getReportesFormatosById(Long idReporte) {
		Criteria c = getCurrentSession().createCriteria(ReportesFormatosDTO.class);
		c.createAlias("reporte", "r");
		c.add(Restrictions.eq("r.idReporte", idReporte));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ReportesFormatosDTO>) c.list();
	}

	@Override
	public ReportesFormatosDTO getReportesFormatosByIdReporteFormato(Long idReporteFormato) {
		Criteria c = getCurrentSession().createCriteria(ReportesFormatosDTO.class);
		c.add(Restrictions.eq("idReporteFormato", idReporteFormato));
		return (ReportesFormatosDTO)c.uniqueResult();
	}

}
