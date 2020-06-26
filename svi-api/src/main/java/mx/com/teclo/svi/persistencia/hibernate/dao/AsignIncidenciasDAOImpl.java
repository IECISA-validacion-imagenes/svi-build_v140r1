package mx.com.teclo.svi.persistencia.hibernate.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignIncidenciasDTO;


@Repository("asignIncidenciasDAO")
public class AsignIncidenciasDAOImpl  extends BaseDaoHibernate<AsignIncidenciasDTO> implements AsignIncidenciasDAO {

	@SuppressWarnings("unchecked")
	@Override
	public AsignIncidenciasDTO getInconsistenciaAsignada(Long IdUsuario) {	
		List<AsignIncidenciasDTO> listaIncidencias = new ArrayList<AsignIncidenciasDTO>();
		
		Criteria criteria  = getCurrentSession().createCriteria(AsignIncidenciasDTO.class);
		criteria.createAlias("idValidadorConfig","ValConfig");
		criteria.createAlias("ValConfig.idValidador","Val");
		criteria.createAlias("Val.idUsuario", "usrVal");
		criteria.add(Restrictions.eq("stActivo", 1L)); //debe estar activo 
		criteria.add(Restrictions.eq("stValidacion",0L)); //no debe estar validado 
		criteria.add(Restrictions.eq("usrVal.idUsuario", IdUsuario));
		criteria.addOrder(Order.asc("idArchivoCsv"));
		listaIncidencias = criteria.list();
		if(listaIncidencias.size()>0) {
			return listaIncidencias.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public int getAllInconsistenciasAsignadas(Long IdUsuario) {	
		List<AsignIncidenciasDTO> listaIncidencias = new ArrayList<AsignIncidenciasDTO>();
		
		Criteria criteria  = getCurrentSession().createCriteria(AsignIncidenciasDTO.class);
		criteria.createAlias("idValidadorConfig","ValConfig");
		criteria.createAlias("ValConfig.idValidador","Val");
		criteria.createAlias("Val.idUsuario", "usrVal");
		criteria.add(Restrictions.eq("stActivo", 1L)); //debe estar activo 
		criteria.add(Restrictions.eq("stValidacion",0L)); //no debe estar validado 
		criteria.add(Restrictions.eq("usrVal.idUsuario", IdUsuario));
		criteria.addOrder(Order.asc("idArchivoCsv"));
		listaIncidencias = criteria.list();
		return listaIncidencias.size();
	}

}
