package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.CatPerfilVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;


@Repository("PtSubmarcasDAO")
public class PtSubmarcasDAOImpl extends BaseDaoHibernate<PtSubmarcasDTO> implements PtSubmarcasDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatPerfilVO> getCatSubMarcas() {
		Criteria criteria = getCurrentSession().createCriteria(PtSubmarcasDTO.class, "submarca");
		criteria.createAlias("submarca.idPtMarca", "marca");
		criteria.setProjection(
				Projections.projectionList()
				.add(Projections.property("marca.idPtMarca").as("idPtMarca"))
				.add(Projections.property("marca.txtMarca").as("txMarca"))
				.add(Projections.property("submarca.idPtSubmarca").as("idPtSubMarca"))
				.add(Projections.property("submarca.txtMarca").as("txSubMarca")));
		criteria.add(Restrictions.eq("submarca.stActivo", BigDecimal.ONE.shortValue()));
		criteria.addOrder(Order.asc("marca.txtMarca"));
		criteria.addOrder(Order.asc("submarca.txtMarca"));
		criteria.setResultTransformer(Transformers.aliasToBean(CatPerfilVO.class));

		return criteria.list();
	}
	
	@Override
	public String getResumenFiltro(List<Long> idsSubmarcas) {
		if(idsSubmarcas.isEmpty()) { return null; }
		Criteria criteria = getCurrentSession().createCriteria(PtSubmarcasDTO.class);
		criteria.add(Restrictions.in("idPtMarca", idsSubmarcas));
		criteria.setProjection(Projections.projectionList()				
				.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(TXT_MARCA)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();
	}

}
