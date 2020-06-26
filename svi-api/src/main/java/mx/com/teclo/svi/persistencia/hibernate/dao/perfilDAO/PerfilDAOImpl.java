package mx.com.teclo.svi.persistencia.hibernate.dao.perfilDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilDTO;

@Repository
public class PerfilDAOImpl extends BaseDaoHibernate<PerfilDTO> implements PerfilDAO{

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilDTO> obtieneListaPerfiles(String codeApplication) {
		Criteria criteria =  getCurrentSession().createCriteria(PerfilDTO.class);
		criteria.createAlias("aplicacionDTO", "aplicacionDTO");
		criteria.add(Restrictions.eq("aplicacionDTO.codigo", codeApplication));
		criteria.add(Restrictions.eq("stVisible", "1"));
		criteria.addOrder(Order.asc("perfilNombre"));
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilDTO> obtieneListaPerfilesTCLAdministrador(String codeApplication) {
		Criteria criteria =  getCurrentSession().createCriteria(PerfilDTO.class);
		criteria.createAlias("aplicacionDTO", "aplicacionDTO");
		criteria.add(Restrictions.eq("aplicacionDTO.codigo", codeApplication));
		criteria.addOrder(Order.asc("perfilNombre"));
		return criteria.list();
	}


	@Override
	@Transactional
	public PerfilDTO getPerfilById(Long idPerfil, String cdApp) {
		Criteria c = getCurrentSession().createCriteria(PerfilDTO.class);
		c.createAlias("aplicacionDTO", "app");
		c.add(Restrictions.eq("perfilId", idPerfil));
		c.add(Restrictions.eq("app.codigo", cdApp));
		return (PerfilDTO) c.uniqueResult();
	}
	
	@Override
	public PerfilDTO buscarPerfilPorNombre(String nombrePerfil,String codigoAplicacion) {
		Criteria criteria =  getCurrentSession().createCriteria(PerfilDTO.class);
		criteria.createAlias("aplicacionDTO", "aplicacionDTO");
		criteria.add(Restrictions.eq("aplicacionDTO.codigo", codigoAplicacion));
		criteria.add(Restrictions.eq("perfilNombre", nombrePerfil));
		return (PerfilDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilDTO> getPerfilActiveBycdApp(String cdApp) {
		Criteria c =  getCurrentSession().createCriteria(PerfilDTO.class);
		c.createAlias("aplicacionDTO", "aplicacionDTO");
		c.add(Restrictions.eq("aplicacionDTO.codigo", cdApp));
		c.add(Restrictions.eq("stActivo", true));
		c.addOrder(Order.asc("perfilNombre"));
		return c.list();
	}

	@Override
	@Transactional
	public PerfilDTO getPerfilbyIdperfil(Long idPerfil) {
		Criteria c =  getCurrentSession().createCriteria(PerfilDTO.class);
		c.add(Restrictions.eq("perfilId",idPerfil));
		c.add(Restrictions.eq("stActivo", true));
		return (PerfilDTO) c.uniqueResult();
	}


}
