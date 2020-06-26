package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPerfilVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;

@SuppressWarnings("unchecked")
@Repository("catPerfiles")
public class PtPerfilesDAOImpl  extends BaseDaoHibernate<PtPerfilesDTO> implements PtPerfilesDAO {


	@Override
	@Transactional
	public List <PtPerfilesDTO> catalogoPerfiles() {
		Criteria c = getCurrentSession().createCriteria(PtPerfilesDTO.class);
		c.add(Restrictions.eq("stActivo",1L));
		c.addOrder(Order.asc("txDescripcion"));
		return (List<PtPerfilesDTO>)c.list();
	}
	
	
	@Override
	public List<CatPerfilVO> getCatPerfiles() {
		Criteria criteria = getCurrentSession().createCriteria(PtSubmarcasDTO.class, "submarca");
		criteria.createAlias("submarca.idPtPerfil", "perfil");
		criteria.createAlias("submarca.idPtMarca", "marca");
		criteria.setProjection(
				Projections.projectionList()
				.add(Projections.property("marca.idPtMarca").as("idPtMarca"))
				.add(Projections.property("marca.txtMarca").as("txMarca"))
				.add(Projections.property("submarca.idPtSubmarca").as("idPtSubMarca"))
				.add(Projections.property("submarca.txtMarca").as("txSubMarca"))
				.add(Projections.property("perfil.idPtPerfiles").as("idPtPerfil"))
				.add(Projections.property("perfil.txDescripcion").as("txPerfil")));
		criteria.add(Restrictions.eq("perfil.stActivo", BigDecimal.ONE.longValue()));
		criteria.addOrder(Order.asc("marca.txtMarca"));
		criteria.addOrder(Order.asc("submarca.txtMarca"));
		criteria.addOrder(Order.asc("perfil.txDescripcion"));
		criteria.setResultTransformer(Transformers.aliasToBean(CatPerfilVO.class));

		return criteria.list();
	}


	@Override
	public List<CatPerfilVO> getCatPerfilesDistinct() {
		Criteria criteria = getCurrentSession().createCriteria(PtPerfilesDTO.class, "perfil");
		criteria.setProjection(
				Projections.projectionList()
				.add(Projections.property("perfil.idPtPerfiles").as("idPtPerfil"))
				.add(Projections.property("perfil.txDescripcion").as("txPerfil")));
		criteria.add(Restrictions.eq("perfil.stActivo", BigDecimal.ONE.longValue()));
		criteria.addOrder(Order.asc("perfil.txDescripcion"));
		criteria.setResultTransformer(Transformers.aliasToBean(CatPerfilVO.class));

		return criteria.list();

	}
	
	@Override
	public String getResumenFiltro(List<Long> idsPerfiles) {
		if(idsPerfiles.isEmpty()){ return null; }
		Criteria criteria = getCurrentSession().createCriteria(PtPerfilesDTO.class);
		criteria.add(Restrictions.in("idPtPerfiles", idsPerfiles));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(TX_DESCRIPCION)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },				
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();
	}
}
