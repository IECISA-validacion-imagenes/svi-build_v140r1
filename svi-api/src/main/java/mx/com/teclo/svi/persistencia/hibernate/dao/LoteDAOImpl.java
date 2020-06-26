package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPtVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;

@SuppressWarnings("unchecked")
@Repository("loteDAO")
public class LoteDAOImpl extends BaseDaoHibernate<LoteDTO>implements LoteDAO {

	@Override
	public List<CatPtVO> buscarCatTodosPuntosTacticos(){
		Criteria c = getCurrentSession().createCriteria(LoteDTO.class);
		c.add(Restrictions.eq("stActivo", (short)1));
		List<LoteDTO> listaCsv = c.list();
		List<CatPtVO> catListaPt = new ArrayList<CatPtVO>();
		
		for(LoteDTO lDTP: listaCsv){
			CatPtVO catPtVO = new CatPtVO();
			catPtVO.setIdPt(lDTP.getIdPtLote());
			catPtVO.setNbEntrega(lDTP.getIdEntrega().getNbEntrega());
			catPtVO.setNbNombre(lDTP.getNbPtLote());
			catPtVO.setNuTotalRegCsv(lDTP.getNuTotalRegCsv());
			catListaPt.add(catPtVO);
		}
		return catListaPt;
	}
	
	@Override
	public List<CatPtVO> buscarCatTodosPuntosTacticos(List<Long> listaLotes){
		Criteria c = getCurrentSession().createCriteria(LoteDTO.class);
		c.add(Restrictions.eq("stActivo", (short)1));
		c.add(Restrictions.eq("idPtLote", listaLotes));
		List<LoteDTO> listaCsv = c.list();
		List<CatPtVO> catListaPt = new ArrayList<CatPtVO>();
		
		for(LoteDTO lDTP: listaCsv){
			CatPtVO catPtVO = new CatPtVO();
			catPtVO.setIdPt(lDTP.getIdPtLote());
			catPtVO.setNbEntrega(lDTP.getIdEntrega().getNbEntrega());
			catPtVO.setNbNombre(lDTP.getNbPtLote());
			catPtVO.setNuTotalRegCsv(lDTP.getNuTotalRegCsv());
			catListaPt.add(catPtVO);
		}
		return catListaPt;
	}

	@Override
	public String getResumenFiltro(List<Long> idLotes) {
		if (idLotes.isEmpty()) {
			return null;
		}
		Criteria criteria = getCurrentSession().createCriteria(LoteDTO.class);
		criteria.add(Restrictions.in("idPtLote", idLotes));
		criteria.setProjection(Projections.projectionList()				
						.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(nbPtLote)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();

	}
	@Override
	public DetalleAsignacionVO getDetalleAsignacion(Long idPtLote) {
				Criteria criteria = getCurrentSession().createCriteria(LoteDTO.class, "lote");
		criteria.add(Restrictions.eq("lote.idPtLote", idPtLote));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("lote.idPtLote").as("idPtLote"))
				.add(Projections.property("lote.nbPtLote").as("nbLote")));
		criteria.setResultTransformer(Transformers.aliasToBean(DetalleAsignacionVO.class));
		return (DetalleAsignacionVO) criteria.uniqueResult();
	}
}
