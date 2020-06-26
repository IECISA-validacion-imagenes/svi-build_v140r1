package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignRegValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

@SuppressWarnings("unchecked")
@Repository("AsignRegValidadorDAO")
public class AsignRegValidadorDAOImpl extends BaseDaoHibernate<AsignRegValidadorDTO> implements AsignRegValidadorDAO{

	@Override
	@Transactional
	public List<Long> getTodosRegistrosAsignadosActivos(Long idValidador) {//aquuui
		Criteria c = getCurrentSession().createCriteria(AsignRegValidadorDTO.class);
		c.createAlias("idValidadorConfig", "ValidadoresConfigDTO");
		c.createAlias("ValidadoresConfigDTO.idValidador", "ValidadoresDTO");
		c.add(Restrictions.eq("ValidadoresDTO.idValidador", idValidador));	
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
//		c.setProjection(Projections.projectionList().add(Projections.property("idRegistroCsv"), "idRegistroCsv"))
//		.setResultTransformer(Transformers.aliasToBean(AsignValidadorDTO.class));
		List<AsignRegValidadorDTO> listaRegistros = (List<AsignRegValidadorDTO>) c.list();
		List<Long> listaRegistrosLong = new ArrayList<Long>();
		for(int i=0;i<listaRegistros.size();i++) {
			listaRegistrosLong.add(listaRegistros.get(i).getIdRegistroCsv());
		}
		
		
		return listaRegistrosLong;
	}
	
	@Override
	@Transactional
	public List<Long> MC_getTodosRegistrosAsignadosActivos(Long idValidador) {//aquuui
		Criteria c = getCurrentSession().createCriteria(AsignRegValidadorDTO.class);
		c.createAlias("idValidadorConfig", "ValidadoresConfigDTO");
		c.createAlias("ValidadoresConfigDTO.idValidador", "ValidadoresDTO");
		c.add(Restrictions.eq("ValidadoresDTO.idValidador", idValidador));	
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
//		c.setProjection(Projections.projectionList().add(Projections.property("idRegistroCsv"), "idRegistroCsv"))
//		.setResultTransformer(Transformers.aliasToBean(AsignValidadorDTO.class));
		List<AsignRegValidadorDTO> listaRegistros = (List<AsignRegValidadorDTO>) c.list();
		List<Long> listaRegistrosLong = new ArrayList<Long>();
		for(int i=0;i<listaRegistros.size();i++) {
			listaRegistrosLong.add(listaRegistros.get(i).getIdRegistroCsv());
		}
		
		
		return listaRegistrosLong;
	}
	
	@Override
	public AsignRegValidadorDTO findByIdRegistro(Long idRegistroCsv) {
		Criteria c = getCurrentSession().createCriteria(AsignRegValidadorDTO.class);
		c.add(Restrictions.eq("idRegistroCsv", idRegistroCsv));
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		return (AsignRegValidadorDTO) c.uniqueResult();
	}
	
	@Override
	@Transactional
	public List<Long> getTodosIdsRegistrosAsignadosActivos() {
		Criteria c = getCurrentSession().createCriteria(AsignRegValidadorDTO.class);
//		c.setProjection(Projections.projectionList().add(Projections.property("idRegistroCsv"), "idRegistroCsv"))
//		.setResultTransformer(Transformers.aliasToBean(AsignValidadorDTO.class));
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		List<AsignRegValidadorDTO> listaRegistros = (List<AsignRegValidadorDTO>) c.list();
		List<Long> listaRegistrosLong = new ArrayList<Long>();
		for(int i=0;i<listaRegistros.size();i++) {
			listaRegistrosLong.add(listaRegistros.get(i).getIdRegistroCsv());
		}
		return listaRegistrosLong;
	}
	
	@Override
	@Transactional
	public Long asignarRegistroValidador(Long idRegistro, ValidadoresConfigDTO validadoresConfig, Long idUsuario) {
		
		AsignRegValidadorDTO asignacion = new AsignRegValidadorDTO();
		Long idRetornado=0L;
		
		
			asignacion.setIdValidadorConfig(validadoresConfig);
			asignacion.setIdRegistroCsv(idRegistro);
			asignacion.setStValidacion((short) 0);
			asignacion.setStActivo((short) 1);	
			asignacion.setFhCreacion(new Date(System.currentTimeMillis()));		
			asignacion.setIdUsrCreacion(idUsuario);
			asignacion.setFhModificacion(new Date(System.currentTimeMillis()));
			asignacion.setIdUsrModifica(idUsuario);
			idRetornado = (Long) getCurrentSession().save(asignacion);
		
		
		return idRetornado;//OBSERVAR AQUI TAMBIEN
	}

	@Override
	public List<AsignRegValidadorDTO> getTodosArchivosDeRegistrosAsignadosActivos2() {
		Criteria c = getCurrentSession().createCriteria(AsignRegValidadorDTO.class);
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
		return (List<AsignRegValidadorDTO>) c.list();
	}
	
	
	

}
