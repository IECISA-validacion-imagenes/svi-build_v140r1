package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO;
import mx.com.teclo.svi.negocio.vo.expediente.EtiquetaExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEtiquetasRevalDTO;

@Repository("ptEtiquetasRevalDAO")
public class PtEtiquetasRevalDAOImpl extends BaseDaoHibernate<PtEtiquetasRevalDTO> implements PtEtiquetasRevalDAO {
	 private static final String SQL_DETALLE_FILTRO = "SELECT new mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO(e.idEtiqReval as idFiltro, e.nbEtiqueta as nbEtiqueta,  e.fhCreacion, (SELECT u.nbUsuario||' '||u.nbApaterno||' '||u.nbAmaterno from UsuarioDTO u WHERE u.idUsuario = e.idUsrCreacion) as autor , f.nbFiltro as txFiltro) FROM PtFiltroDetalleEvaDTO as f inner join f.idEtiqReval as e WHERE f.idEtiqReval = :idEtiqReval";
	 
	@Override
	public Long countByCdUnicidad(Long cdUnicidad) {
		Criteria criteria = getCurrentSession().createCriteria(PtEtiquetasRevalDTO.class, "etiqueta");
		criteria.add(Restrictions.eq("etiqueta.cdUnicidad", cdUnicidad));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public EtiquetaExpedienteVO findByCdUnicidad(Long cdUnicidad) {
		Criteria criteria = getCurrentSession().createCriteria(PtEtiquetasRevalDTO.class, "etiqueta");
		criteria.add(Restrictions.eq("etiqueta.cdUnicidad", cdUnicidad));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("etiqueta.idEtiqReval").as("idEtiqueta"))
						.add(Projections.property("etiqueta.nbEtiqueta").as("nbEtiqueta")))
				.setResultTransformer(Transformers.aliasToBean(EtiquetaExpedienteVO.class));
		return (EtiquetaExpedienteVO) criteria.uniqueResult();
	}

	@Override
	public Long countByNbEtiqueta(String etiqueta) {
		Criteria criteria = getCurrentSession().createCriteria(PtEtiquetasRevalDTO.class, "etiqueta");
		criteria.add(Restrictions.ilike("etiqueta.nbEtiqueta", etiqueta));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public EtiquetaExpedienteVO findByNbEtiqueta(String etiqueta) {
		Criteria criteria = getCurrentSession().createCriteria(PtEtiquetasRevalDTO.class, "etiqueta");
		criteria.add(Restrictions.ilike("etiqueta.nbEtiqueta", etiqueta));
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("etiqueta.idEtiqReval").as("idEtiqReval"))
						.add(Projections.property("etiqueta.nbEtiqueta").as("nbEtiqueta")))
				.setResultTransformer(Transformers.aliasToBean(EtiquetaExpedienteVO.class));
		return (EtiquetaExpedienteVO) criteria.uniqueResult();
	}

	@Override
	public List<PtEtiquetasRevalDTO> getAllEtiquetas() {
		Criteria criteria = getCurrentSession().createCriteria(PtEtiquetasRevalDTO.class, "etiqueta");
		criteria.add(Restrictions.eq("stActivo", true));
		criteria.addOrder(Order.asc("nbEtiqueta"));
		return (List<PtEtiquetasRevalDTO>) criteria.list();
	}

	@Override
	public DetalleFiltroVO getDetalleFiltro(Long idFiltro) {
		Query query = getCurrentSession().createQuery(SQL_DETALLE_FILTRO);
		query.setLong("idEtiqReval", idFiltro);
		return (DetalleFiltroVO) query.uniqueResult();

	}

}
