package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtFiltroDetalleEvaDTO;

@Repository("ptFiltroDetalleEvaDAO")
public class PtFiltroDetalleEvaDAOImpl extends BaseDaoHibernate<PtFiltroDetalleEvaDTO>
		implements PtFiltroDetalleEvaDAO {

	@Override
	public DetalleAsignacionVO getDetalleAsignacion(Long idFiltro) {
		Criteria criteria = getCurrentSession().createCriteria(PtFiltroDetalleEvaDTO.class, "filtro");
		criteria.createAlias("filtro.idEtiqReval", "etiqueta");
		criteria.add(Restrictions.eq("etiqueta.idEtiqReval", idFiltro));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("etiqueta.idEtiqReval").as("idEtiqueta"))
						.add(Projections.property("etiqueta.nbArchivoCsv").as("nbEtiqueta")));
		criteria.setResultTransformer(Transformers.aliasToBean(DetalleAsignacionVO.class));
		return (DetalleAsignacionVO) criteria.uniqueResult();
	}
}
