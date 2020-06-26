package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.catalogo.PtParametrosVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtParametrosDTO;

@Repository("PtParametrosDAO")
public class PtParametrosDAOImpl extends BaseDaoHibernate<PtParametrosDTO> implements PtParametrosDAO {

	@Override
	public PtParametrosVO obtenerParametros(Long idPtParam) {
		Criteria criteria = getCurrentSession().createCriteria(PtParametrosDTO.class, "parametros");

		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("parametros.idPtParam").as("idPtParam"))
				.add(Projections.property("parametros.txStorageWeb").as("txStorageWeb"))
				.add(Projections.property("parametros.maxResultados").as("maxResultados")));
		criteria.add(Restrictions.eq("parametros.idPtParam", idPtParam))
				.setResultTransformer(Transformers.aliasToBean(PtParametrosVO.class));
		return (PtParametrosVO) criteria.uniqueResult();
	}

}