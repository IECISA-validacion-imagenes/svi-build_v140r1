package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEntidadDTO;


@SuppressWarnings("unchecked")
@Repository("ptEntidadDAO")
public class PtEntidadDAOImpl extends BaseDaoHibernate<PtEntidadDTO> implements PtEntidadDAO {
	
	@Override
	public PtEntidadDTO getPtEntidadById(Long idPtCatalogoEntidad) {
		
		return null;
		
		/*
		 	ImagenesDTO img = null;//getCurrentSession().get(ImagenesDTO.class, idImagen);
		String queryJPA =
				"	SELECT img 						"
				+ "	FROM ImagenesDTO img 			"
				+ "	WHERE img.idImagen = :idImagen	";
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idImagen", idImagen);
		img = (ImagenesDTO) query.list().get(0);
		return img;
		 */
	}


	@Override
	@Transactional
	public List<PtEntidadDTO> getCatalogoEntidad() {
		Criteria c= getCurrentSession().createCriteria(PtEntidadDTO.class);
		c.addOrder(Order.asc("txDescEntidad"));
		return (List<PtEntidadDTO>) c.list();
	}

}
