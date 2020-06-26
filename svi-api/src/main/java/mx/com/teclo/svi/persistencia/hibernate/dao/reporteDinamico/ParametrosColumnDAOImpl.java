package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;

@Repository
public class ParametrosColumnDAOImpl extends BaseDaoHibernate<ParametrosColumnDTO> implements ParametrosColumnDAO {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosColumnDTO> getAllColumns(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("pt.stActivo", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ParametrosColumnDTO>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosColumnDTO> getHijos(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("idParamTabDependency", idParametroTabla));
		return (List<ParametrosColumnDTO>) c.list();
	}

	@Override
	public ParametrosColumnDTO getIdentificador(Long idColumna, Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.createAlias("columna", "col");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("col.idColumna", idColumna));
		c.add(Restrictions.eq("stIsKey", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosColumnDTO)c.uniqueResult();
	}

	@Override
	public ParametrosColumnDTO getDescripcion(Long idColumna, Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.createAlias("columna", "col");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("col.idColumna", idColumna));
		c.add(Restrictions.eq("stIsDesc", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosColumnDTO)c.uniqueResult();
	}

	@Override
	public ParametrosColumnDTO getRestriccion(Long idColumna, Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.createAlias("columna", "col");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("col.idColumna", idColumna));
		c.add(Restrictions.eq("stIsWhere", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosColumnDTO)c.uniqueResult();
	}

	@Override
	public ParametrosColumnDTO getOrden(Long idColumna, Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.createAlias("columna", "col");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("col.idColumna", idColumna));
		c.add(Restrictions.eq("stIsOrder", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosColumnDTO)c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosColumnDTO> getDescripcionList(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("stIsDesc", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ParametrosColumnDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosColumnDTO> getRestriccionList(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("stIsWhere", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ParametrosColumnDTO>)c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametrosColumnDTO> getOrdenList(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("stIsOrder", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ParametrosColumnDTO>)c.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ParametrosColumnDTO> getColumnasDependientes(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.isNotNull("idParamTabDependency"));//eq("idParamTabDependency", idParametroTabla));
		return (List<ParametrosColumnDTO>) c.list();
	}

	@Override
	public ParametrosColumnDTO getIdentificador(Long idParametroTabla) {
		Criteria c = getCurrentSession().createCriteria(ParametrosColumnDTO.class);
		c.createAlias("parametroTabla", "pt");
		c.add(Restrictions.eq("pt.idParametroTabla", idParametroTabla));
		c.add(Restrictions.eq("stIsKey", 1));
		c.add(Restrictions.eq("stActivo", 1));
		return (ParametrosColumnDTO)c.uniqueResult();
	}
}
