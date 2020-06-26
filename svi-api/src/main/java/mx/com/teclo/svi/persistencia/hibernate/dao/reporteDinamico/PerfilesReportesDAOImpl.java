package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;

@Repository
public class PerfilesReportesDAOImpl extends BaseDaoHibernate<PerfilesReportesDTO> implements PerfilesReportesDAO {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesReportesDTO> getReportePerfilAtivos() {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<PerfilesReportesDTO>) c.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public PerfilesReportesDTO byReporteAndPerfil(Long perfilId, Long reporteId) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("reporte", "rep");
		c.add(Restrictions.eq("perfil", perfilId));
		c.add(Restrictions.eq("rep.idReporte", reporteId));
		List<PerfilesReportesDTO> dtoList = c.list();
		if (CollectionUtils.isEmpty(dtoList))
			return null;
		return dtoList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesReportesDTO> ontenerReportesPorPerfil(Long idPerfil) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
//		c.createAlias("perfil", "p");
		c.createAlias("reporte", "r");
		c.add(Restrictions.eq("perfil", idPerfil));
		c.add(Restrictions.eq("stActivo", 1));
    	c.add(Restrictions.eq("r.stActivo", 1));
//		c.add(Restrictions.eq("p.stActivo", true));

		return (List<PerfilesReportesDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilesReportesDTO> getRegistrosByIdReporte(Long idReporte) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("reporte", "rep");
		c.add(Restrictions.eq("rep.idReporte", idReporte));
		return (List<PerfilesReportesDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilesReportesDTO> getPReporteByTipoYNb(Long perfilId, String cdApp, Long tipoReporte,String nbReporte) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("perfil", "p");
		c.createAlias("p.aplicacionDTO", "app");
		c.createAlias("reporte", "rep");
		c.createAlias("rep.tipoReporte", "tipoRep");
		
		c.add(Restrictions.eq("perfil", perfilId)); 
		c.add(Restrictions.eq("app.codigo", cdApp));
		c.add(Restrictions.eq("tipoRep.idTipoReporte", tipoReporte));
		c.add(Restrictions.like("rep.nbReporte", nbReporte , MatchMode.ANYWHERE));
		return (List<PerfilesReportesDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesReportesDTO> getTodosReportes() {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		return (List<PerfilesReportesDTO>) c.list();
	}

}
