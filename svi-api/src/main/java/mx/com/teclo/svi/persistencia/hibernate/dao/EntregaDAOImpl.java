package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.EntregaDTO;

@SuppressWarnings("unchecked")
@Repository("entregaDAO")
public class EntregaDAOImpl extends BaseDaoHibernate<EntregaDTO> implements EntregaDAO {

	@Override
	public List<CatalogoVO>catalogoPeriodos(){
		String sql = "SELECT ID_ENTREGA, NB_ENTREGA, NU_TOTAL_REG_CSV FROM TCI001D_PT_ENTREGA ORDER BY NB_ENTREGA";
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
				
		List<Object[]> listaObj = q.list();
		List<CatalogoVO> lista = new ArrayList<CatalogoVO>();
		
		for(Object[] obj: listaObj){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(((BigDecimal)obj[0]).longValueExact());
			cVO.setNameCat((String)obj[1]);
			cVO.setNuCantidad((BigDecimal)obj[2]);
			lista.add(cVO);
		}
		return lista;
	}
	
	@Override
	public List<CatalogoVO>catalogoPeriodos(List<Long> listaEntregas){
		String listaent = "";
		for(int i=0;i<listaEntregas.size();i++) {
			if(listaEntregas.size()+1==listaEntregas.size()) {
				listaent = listaent+listaEntregas.get(i);
			}else {
				listaent = listaent+listaEntregas.get(i)+",";
			}
		}
		String sql = "SELECT ID_ENTREGA, NB_ENTREGA, NU_TOTAL_REG_CSV FROM TCI001D_PT_ENTREGA "
				+ "WHERE ID_ENTREGA IN ("+listaent+") ORDER BY NB_ENTREGA";
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
				
		List<Object[]> listaObj = q.list();
		List<CatalogoVO> lista = new ArrayList<CatalogoVO>();
		
		for(Object[] obj: listaObj){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(((BigDecimal)obj[0]).longValueExact());
			cVO.setNameCat((String)obj[1]);
			cVO.setNuCantidad((BigDecimal)obj[2]);
			lista.add(cVO);
		}
		return lista;
	}

	@Override
	public List<CsvExpedienteVO> getAllEntregas() {
		Criteria criteria = getCurrentSession().createCriteria(EntregaDTO.class, "entrega");
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("entrega.idEntrega").as("idEntrega"))
						.add(Projections.property("entrega.nbEntrega").as("nbEntrega")))
				.setResultTransformer(Transformers.aliasToBean(CsvExpedienteVO.class));
		List<CsvExpedienteVO> listaArchivosCsv = criteria.list();
		return listaArchivosCsv;
	}
	
	@Override
	public String getResumenFiltro(List<Long> idsEntregas) {
		if (idsEntregas.isEmpty()) {
			return null;
		}
		Criteria criteria = getCurrentSession().createCriteria(EntregaDTO.class);
		criteria.add(Restrictions.in("idPtMarca", idsEntregas));
		criteria.setProjection(Projections.projectionList()				
				.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(nbEntrega)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },
				new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();
		
		
	}
}
