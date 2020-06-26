package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqAsignacion;
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.AsignValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

@SuppressWarnings("unchecked")
@Repository("asignValidadorDAO")
public class AsignValidadorDAOImpl extends BaseDaoHibernate<AsignValidadorDTO> implements AsignValidadorDAO{
	

	@Autowired
	private ValidadoresConfigDAO validadoresConfigHDAO;
	
	@Autowired
	private ParametrosComponente parametrosComponente;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ArchivosCsvRespuestaVO> getTodosArchivosAsignadosActivos(Long idUsuario) {
		
		StringBuilder strQuery = new StringBuilder(" "
				+ "SELECT CSV.ID_ARCHIVO_CSV, CSV.ID_PT_LOTE, CSV.NB_ARCHIVO_CSV, CSV.NU_REGISTROS_CSV,    \r\n" + 
				"					CSV.NB_CARPETA_IMG, CSV.TX_CARPETA_IMG, CSV.NU_IMAGENES, CSV.NB_CARPETA_SIL,    \r\n" + 
				"					CSV.TX_CARPETA_SIL, CSV.NU_SILUETAS, CSV.ST_VALIDACION, CSV.ST_ACTIVO   \r\n" + 
				"				 FROM TCI003D_PT_ARCHIVO_CSV CSV   \r\n" + 
				"				 JOIN TCI010D_PT_ASIGN_VALIDADOR ASIGN ON ASIGN.ID_ARCHIVO_CSV = CSV.ID_ARCHIVO_CSV   \r\n" + 
				"				 JOIN TCI009D_PT_VALIDADORES_CONFIG CONFIG ON CONFIG.ID_VALIDADOR_CONFIG = ASIGN.ID_VALIDADOR_CONFIG     \r\n" + 
				"				JOIN TCI007C_PT_VALIDADORES val ON CONFIG.ID_VALIDADOR = val.ID_VALIDADOR\r\n" + 
				"					WHERE val.ID_USUARIO ="+idUsuario+" and ASIGN.ST_VALIDACION=0 AND ASIGN.ST_ACTIVO=1 ORDER BY ASIGN.ID_ARCHIVO_CSV ASC");
				
		
		Query query = getCurrentSession().createSQLQuery(strQuery.toString());
		List rows = query.list();
		Iterator rowsIt = rows.iterator();
		
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());

		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();				
	
		
		List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = new ArrayList<ArchivosCsvRespuestaVO>();
		ArchivosCsvRespuestaVO datos = null;
		
		if(!rows.isEmpty()){
			while (rowsIt.hasNext()) {
			Object[] lista = (Object[]) rowsIt.next();	
			datos = new ArchivosCsvRespuestaVO();
			
			datos.setIdArchivoCsv(lista[0] != null ? Long.parseLong(lista[0].toString(), 10) : null);
			datos.setIdPtLote(lista[1] != null ? Long.parseLong(lista[1].toString(), 10) : null);
			datos.setNbArchivoCsv(lista[2] != null ? lista[2].toString() : null);
			datos.setNuRegistrosCsv(lista[3] != null ? Long.parseLong(lista[3].toString(), 10) : null);
			datos.setNbCarpetaImg(lista[4] != null ? lista[4].toString() : null);
			datos.setTxCarpetaImg(lista[5] != null ? lista[5].toString() : null);
			datos.setNuImagenes(lista[6] != null ? Long.parseLong(lista[6].toString(), 10) : null);
			datos.setNbCarpetaSil(lista[7] != null ? lista[7].toString() : null);
			datos.setTxCarpetaSil(lista[8] != null ? lista[8].toString() : null);
			datos.setNuSiluetas(lista[9] != null ? Long.parseLong(lista[9].toString(), 10) : null);
			datos.setStValidacion(lista[10] != null ? Short.parseShort(lista[10].toString()) : null);
			datos.setStActivo(lista[11] != null ? Short.parseShort(lista[11].toString()) : null);
			datos.setTxRutaStorage(rutaHost);
			listaArchivosRespuesta.add(datos);	
			}

		}
		return listaArchivosRespuesta;
	}
	
	public String validaDato(Object value) {
		if(value!=null||!value.equals(null)) {
			return value.toString();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ArchivoCsvDetallesVO> getDetallesArchivo(List<ArchivosCsvRespuestaVO> archivosCsvRespuestaVO) {
		
		List<ArchivoCsvDetallesVO> listaArchivosDetalleRespuesta = new ArrayList<ArchivoCsvDetallesVO>();
		
		for(int i=0; i<archivosCsvRespuestaVO.size();i++) {
			
			StringBuilder strQuery = new StringBuilder(" "
					+ " SELECT ARCHDETALLE.ID_REGISTRO_CSV, ARCHDETALLE.NODO_VPN, " + 
					" ARCHDETALLE.NU_CARRIL, ARCHDETALLE.CD_PLACA_DELANTERA, ARCHDETALLE.CD_ENTIDAD_DELANTERA, " + 
					" ARCHDETALLE.CD_PLACA_TRASERA, ARCHDETALLE.CD_ENTIDAD_TRASERA, ARCHDETALLE.NB_IMAGEN_PLACA_DELANTERA, " + 
					" ARCHDETALLE.NB_IMAGEN_PLACA_TRASERA, ARCHDETALLE.NB_IMAGEN_CONDUCTOR, ARCHDETALLE.NB_IMAGEN_AMBIENTAL, " + 
					" ARCHDETALLE.CD_PERFIL, ARCHDETALLE.NB_IMAGEN_PERFIL, ARCHDETALLE.ST_VALIDACION, ARCHDETALLE.ID_PT_CLASIF_EXPE, ARCHDETALLE.ID_PT_SILUETA  FROM TCI005D_PT_ARCHIVO_DETALLE_EVA ARCHDETALLE "
					+ " WHERE ARCHDETALLE.ID_ARCHIVO_CSV= "+archivosCsvRespuestaVO.get(i).getIdArchivoCsv()+
					" AND  ARCHDETALLE.ST_VALIDACION=0 AND ARCHDETALLE.ST_REVALIDACION=0 ORDER BY ARCHDETALLE.ID_REGISTRO_CSV ASC");
			Query query = getCurrentSession().createSQLQuery(strQuery.toString());
			List rows = query.list();
			Iterator rowsIt = rows.iterator();
			int totreg=0;
//			List<ArchivoCsvDetallesVO> listaArchivosDetalleRespuesta = new ArrayList<ArchivoCsvDetallesVO>();
			ArchivoCsvDetallesVO datos = null;
			totreg = rows.size();
			if(!rows.isEmpty()){
				while (rowsIt.hasNext()) {
				Object[] lista = (Object[]) rowsIt.next();	
				datos = new ArchivoCsvDetallesVO();
				 
				datos.setIdArchivoCsv(archivosCsvRespuestaVO.get(i).getIdArchivoCsv());
				datos.setIdPtLote(archivosCsvRespuestaVO.get(i).getIdPtLote());
				datos.setNbArchivoCsv(archivosCsvRespuestaVO.get(i).getNbArchivoCsv());
				datos.setNuRegistrosCsv(archivosCsvRespuestaVO.get(i).getNuRegistrosCsv());
				datos.setNbCarpetaImg(archivosCsvRespuestaVO.get(i).getNbCarpetaImg());
				datos.setTxCarpetaImg(archivosCsvRespuestaVO.get(i).getTxCarpetaImg());
				datos.setNuImagenes(archivosCsvRespuestaVO.get(i).getNuImagenes());
				datos.setNbCarpetaSil(archivosCsvRespuestaVO.get(i).getNbCarpetaSil());
				datos.setTxCarpetaSil(archivosCsvRespuestaVO.get(i).getTxCarpetaSil());
				datos.setNuSiluetas(archivosCsvRespuestaVO.get(i).getNuSiluetas());
				datos.setStActivo(archivosCsvRespuestaVO.get(i).getStActivo());
				datos.setTxRutaStorage(archivosCsvRespuestaVO.get(i).getTxRutaStorage());
				
				datos.setIdRegistroCsv(lista[0] != null ? Long.parseLong(lista[0].toString(), 10) : null);
				datos.setNodoVpn(lista[1] != null ? Long.parseLong(lista[1].toString(), 10) : null);
				datos.setNuCarril(lista[2] != null ? Long.parseLong(lista[2].toString(), 10) : null);
				datos.setCdPlacaDelantera(lista[3] != null ? lista[3].toString() : null);
				datos.setTxtPlacaDelantera(lista[3] != null ? lista[3].toString() : null);
				datos.setCdEntidadDelantera(lista[4] != null ? lista[4].toString() : null);
				datos.setCdPlacaTrasera(lista[5] != null ? lista[5].toString() : null);
				datos.setTxtPlacaTrasera(lista[5] != null ? lista[5].toString() : null);
				datos.setCdEntidadTrasera(lista[6] != null ? lista[6].toString() : null);
				datos.setNbImagenPlacaDelantera(lista[7] != null ? lista[7].toString() : null);
				datos.setNbImagenPlacaTrasera(lista[8] != null ? lista[8].toString() : null);
				datos.setNbImagenConductor(lista[9] != null ? lista[9].toString() : null);
				datos.setNbImagenAmbiental(lista[10] != null ? lista[10].toString() : null);
				datos.setCdPerfil(lista[11] != null ? lista[11].toString() : null);
				datos.setNbImagenPerfil(lista[12] != null ? lista[12].toString() : null);
				datos.setStValidacion(lista[13] != null ? Short.parseShort(lista[13].toString()) : null);
				datos.setIdPtClasifExpe(lista[14] != null ? Long.parseLong(lista[14].toString(), 10) : null);
				datos.setIdPtClasifExpe(lista[15] != null ? Long.parseLong(lista[15].toString(), 10) : null);
				datos.setTotAsignacionInicial(totreg);
				listaArchivosDetalleRespuesta.add(datos);	
				
				}		
			}
//			archivosCsvRespuestaVO.get(i).setArchivodetalle(listaArchivosDetalleRespuesta);

		}
		
		return listaArchivosDetalleRespuesta;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ArchivoCsvDetallesVO> getDetalleArchivo(ArchivosCsvRespuestaVO archivoCsvRespuestaVO) {
		List<ArchivoCsvDetallesVO> listaArchivosDetalleRespuesta = new ArrayList<ArchivoCsvDetallesVO>();
			StringBuilder strQuery = new StringBuilder(" "
					+ " SELECT ARCHDETALLE.ID_REGISTRO_CSV, ARCHDETALLE.NODO_VPN, " + 
					" ARCHDETALLE.NU_CARRIL, ARCHDETALLE.CD_PLACA_DELANTERA, ARCHDETALLE.CD_ENTIDAD_DELANTERA, " + 
					" ARCHDETALLE.CD_PLACA_TRASERA, ARCHDETALLE.CD_ENTIDAD_TRASERA, ARCHDETALLE.NB_IMAGEN_PLACA_DELANTERA, " + 
					" ARCHDETALLE.NB_IMAGEN_PLACA_TRASERA, ARCHDETALLE.NB_IMAGEN_CONDUCTOR, ARCHDETALLE.NB_IMAGEN_AMBIENTAL, " + 
					" ARCHDETALLE.CD_PERFIL, ARCHDETALLE.NB_IMAGEN_PERFIL, ARCHDETALLE.ST_VALIDACION, ARCHDETALLE.ID_PT_CLASIF_EXPE, ARCHDETALLE.ID_PT_SILUETA  FROM TCI005D_PT_ARCHIVO_DETALLE_EVA ARCHDETALLE "
					+ " WHERE ARCHDETALLE.ID_ARCHIVO_CSV= "+archivoCsvRespuestaVO.getIdArchivoCsv()+
					" AND  ARCHDETALLE.ST_VALIDACION=0 AND ARCHDETALLE.ST_REVALIDACION=0 ORDER BY ARCHDETALLE.ID_REGISTRO_CSV ASC");
			
			Query query = getCurrentSession().createSQLQuery(strQuery.toString());
			List rows = query.list();
			int totreg=0;
			Iterator rowsIt = rows.iterator();
			
//			List<ArchivoCsvDetallesVO> listaArchivosDetalleRespuesta = new ArrayList<ArchivoCsvDetallesVO>();
			ArchivoCsvDetallesVO datos = null;
			totreg = rows.size();
			if(!rows.isEmpty()){
				while (rowsIt.hasNext()) {
				Object[] lista = (Object[]) rowsIt.next();	
				datos = new ArchivoCsvDetallesVO();
				
				datos.setIdArchivoCsv(archivoCsvRespuestaVO.getIdArchivoCsv());
				datos.setIdPtLote(archivoCsvRespuestaVO.getIdPtLote());
				datos.setNbArchivoCsv(archivoCsvRespuestaVO.getNbArchivoCsv());
				datos.setNuRegistrosCsv(archivoCsvRespuestaVO.getNuRegistrosCsv());
				datos.setNbCarpetaImg(archivoCsvRespuestaVO.getNbCarpetaImg());
				datos.setTxCarpetaImg(archivoCsvRespuestaVO.getTxCarpetaImg());
				datos.setNuImagenes(archivoCsvRespuestaVO.getNuImagenes());
				datos.setNbCarpetaSil(archivoCsvRespuestaVO.getNbCarpetaSil());
				datos.setTxCarpetaSil(archivoCsvRespuestaVO.getTxCarpetaSil());
				datos.setNuSiluetas(archivoCsvRespuestaVO.getNuSiluetas());
				datos.setStActivo(archivoCsvRespuestaVO.getStActivo());
				datos.setTxRutaStorage(archivoCsvRespuestaVO.getTxRutaStorage());
				
				datos.setIdRegistroCsv(lista[0] != null ? Long.parseLong(lista[0].toString(), 10) : null);
				datos.setNodoVpn(lista[1] != null ? Long.parseLong(lista[1].toString(), 10) : null);
				datos.setNuCarril(lista[2] != null ? Long.parseLong(lista[2].toString(), 10) : null);
				datos.setCdPlacaDelantera(lista[3] != null ? lista[3].toString() : null);
				datos.setTxtPlacaDelantera(lista[3] != null ? lista[3].toString() : null);
				datos.setCdEntidadDelantera(lista[4] != null ? lista[4].toString() : null);
				datos.setCdPlacaTrasera(lista[5] != null ? lista[5].toString() : null);
				datos.setTxtPlacaTrasera(lista[5] != null ? lista[5].toString() : null);
				datos.setCdEntidadTrasera(lista[6] != null ? lista[6].toString() : null);
				datos.setNbImagenPlacaDelantera(lista[7] != null ? lista[7].toString() : null);
				datos.setNbImagenPlacaTrasera(lista[8] != null ? lista[8].toString() : null);
				datos.setNbImagenConductor(lista[9] != null ? lista[9].toString() : null);
				datos.setNbImagenAmbiental(lista[10] != null ? lista[10].toString() : null);
				datos.setCdPerfil(lista[11] != null ? lista[11].toString() : null);
				datos.setNbImagenPerfil(lista[12] != null ? lista[12].toString() : null);
				datos.setStValidacion(lista[13] != null ? Short.parseShort(lista[13].toString()) : null);
				datos.setIdPtClasifExpe(lista[14] != null ? Long.parseLong(lista[14].toString(), 10) : null);
				datos.setIdPtClasifExpe(lista[15] != null ? Long.parseLong(lista[15].toString(), 10) : null);
				datos.setTotAsignacionInicial(totreg);
				listaArchivosDetalleRespuesta.add(datos);	
				
				}		
			}
//			archivosCsvRespuestaVO.get(i).setArchivodetalle(listaArchivosDetalleRespuesta);		
		
		return listaArchivosDetalleRespuesta;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivosCsvRespuestaVO> getTodosArchivosNoAsignados() {
		
		List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = new ArrayList<ArchivosCsvRespuestaVO>();
		ArchivosCsvRespuestaVO datos = null;
		
		StringBuilder strQuery = new StringBuilder(" "
				+ "SELECT 	\r\n" + 
				"	CSV.ID_ARCHIVO_CSV, CSV.ID_PT_LOTE, CSV.NB_ARCHIVO_CSV, CSV.NU_REGISTROS_CSV,\r\n" + 
				"	CSV.NB_CARPETA_IMG, CSV.TX_CARPETA_IMG, CSV.NU_IMAGENES, CSV.NB_CARPETA_SIL,\r\n" + 
				"	CSV.TX_CARPETA_SIL, CSV.NU_SILUETAS, CSV.ST_VALIDACION, CSV.ST_ACTIVO\r\n" + 
				"FROM TCI003D_PT_ARCHIVO_CSV CSV \r\n" + 
				"WHERE CSV.ID_ARCHIVO_CSV NOT IN (\r\n" + 
				"	SELECT ASIGN.ID_ARCHIVO_CSV FROM TCI010D_PT_ASIGN_VALIDADOR ASIGN \r\n" + 
				"	WHERE  ASIGN.ST_ACTIVO=1 AND ASIGN.ST_VALIDACION=0\r\n" + 
				") AND CSV.ST_ACTIVO=1 AND CSV.ST_VALIDACION=0 ORDER BY ID_ARCHIVO_CSV ASC ");
		
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
		
		Query query = getCurrentSession().createSQLQuery(strQuery.toString());
		List rows = query.list();
		Iterator rowsIt = rows.iterator();
		
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();
		
		
		if(!rows.isEmpty()){
			while (rowsIt.hasNext()) {
			Object[] lista = (Object[]) rowsIt.next();	
			datos = new ArchivosCsvRespuestaVO();
			
			datos.setIdArchivoCsv(lista[0] != null ? Long.parseLong(lista[0].toString(), 10) : null);
			datos.setIdPtLote(lista[1] != null ? Long.parseLong(lista[1].toString(), 10) : null);
			datos.setNbArchivoCsv(lista[2] != null ? lista[2].toString() : null);
			datos.setNuRegistrosCsv(lista[3] != null ? Long.parseLong(lista[3].toString(), 10) : null);
			datos.setNbCarpetaImg(lista[4] != null ? lista[4].toString() : null);
			datos.setTxCarpetaImg(lista[5] != null ? lista[5].toString() : null);
			datos.setNuImagenes(lista[6] != null ? Long.parseLong(lista[6].toString(), 10) : null);
			datos.setNbCarpetaSil(lista[7] != null ? lista[7].toString() : null);
			datos.setTxCarpetaSil(lista[8] != null ? lista[8].toString() : null);
			datos.setNuSiluetas(lista[9] != null ? Long.parseLong(lista[9].toString(), 10) : null);
			datos.setStValidacion(lista[10] != null ? Short.parseShort(lista[10].toString()) : null);
			datos.setStActivo(lista[11] != null ? Short.parseShort(lista[11].toString()) : null);
			datos.setTxRutaStorage(rutaHost);
			
			listaArchivosRespuesta.add(datos);	
			}

		}
		
		return listaArchivosRespuesta;
	}
	
	
	@Override
	public void asignarArchivosValidador(List<ArchivosCsvRespuestaVO> archivosParaAsignar, Long idValidador, Long idUsuario) {
		
		for(ArchivosCsvRespuestaVO archivo: archivosParaAsignar) {
			asignarArchivoValidador(archivo,idValidador,idUsuario);
		}
	}
	
	@Override
	public Long asignarArchivoValidador(ArchivosCsvRespuestaVO archivo, Long idValidador, Long idUsuario) {
		AsignValidadorDTO asignacion = new AsignValidadorDTO();
		Long idRetornado;
		
		ValidadoresConfigDTO validadoresConfig = validadoresConfigHDAO.getValidadorConfigByIdValidador(idValidador);
		
		asignacion.setIdValidadorConfig(validadoresConfig);
		asignacion.setIdPtLote(archivo.getIdPtLote());
		asignacion.setIdArchivoCsv(archivo.getIdArchivoCsv());
		asignacion.setStValidacion((short) 0);
		asignacion.setStActivo((short) 1);	
		asignacion.setFhCreacion(new Date(System.currentTimeMillis()));		
		asignacion.setIdUsrCreacion(idUsuario);
		asignacion.setFhModificacion(new Date(System.currentTimeMillis()));
		asignacion.setIdUsrModifica(idUsuario);
		

		idRetornado = (Long) getCurrentSession().save(asignacion);
	
		return idRetornado;
	}
	
	@Override
	public void actualizarArchivo(AsignValidadorDTO asignacion) {
		// TODO Auto-generated method stub
		getCurrentSession().update(asignacion);
		getCurrentSession().flush();
		
	}
	
	@Override
	public List<ConsultaAsignacionVO> buscarAsignacionesPorCriterio(Long idTipo, String valor){
		String sql = "SELECT av.ID_ASIGN_VALIDADOR, u.CD_USUARIO, u.NB_USUARIO || ' ' || u.NB_APATERNO || ' ' || u.NB_AMATERNO AS NB_COMPLETO, pt.NB_PT_LOTE, csv.NB_ARCHIVO_CSV, csv.ST_VALIDACION, av.ST_ACTIVO, TO_CHAR(av.FH_CREACION, 'DD/MM/YYYY HH24:MI:SS'), csv.ID_ARCHIVO_CSV FROM TCI010D_PT_ASIGN_VALIDADOR av "
				   + "INNER JOIN TCI009D_PT_VALIDADORES_CONFIG vc ON (vc.ID_VALIDADOR_CONFIG = av.ID_VALIDADOR_CONFIG) "
				   + "INNER JOIN TCI007C_PT_VALIDADORES v ON (vc.ID_VALIDADOR = v.ID_VALIDADOR) "
				   + "INNER JOIN TCI003D_PT_ARCHIVO_CSV csv ON (av.ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) "
				   + "INNER JOIN TCI002D_PT_LOTE pt ON (av.ID_PT_LOTE = pt.ID_PT_LOTE) "
				   + "INNER JOIN TAQ025C_SE_USUARIOS u ON (v.ID_USUARIO = u.ID_USUARIO) "
				   + "WHERE av.ST_ACTIVO = 1 AND av.ST_VALIDACION = 0 ";
		
		String sql2 = "SELECT TO_CHAR(FH_MODIFICACION, 'DD/MM/YYYY HH24:MI:SS') FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ID_ARCHIVO_CSV = :csv ORDER BY FH_CREACION DESC";
		
		if(EnumTipoBusqAsignacion.USUARIO.getId() == idTipo){
			sql = sql + "AND LOWER(u.CD_USUARIO) LIKE '%"+valor.toLowerCase()+"%'";
		}else if(EnumTipoBusqAsignacion.NOMBRE.getId() == idTipo){
			sql = sql + "AND LOWER(u.NB_USUARIO) LIKE '%"+valor.toLowerCase()+"%'";
		}else if(EnumTipoBusqAsignacion.APELLIDOPAT.getId() == idTipo){
			sql = sql + "AND LOWER(u.NB_APATERNO) LIKE '%"+valor.toLowerCase()+"%'";
		}
		
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		List<Object[]> c = q.list();
		
		List<ConsultaAsignacionVO> res = new ArrayList<ConsultaAsignacionVO>();
		
		for(Object[] obj: c){
			ConsultaAsignacionVO caVO = new ConsultaAsignacionVO();
			caVO.setIdAsignaValidacion(((BigDecimal)obj[0]).longValueExact());
			caVO.setNbUsuario((String)obj[1]);
			caVO.setNbNombre((String)obj[2]);
			caVO.setNbLote((String)obj[3]);
			caVO.setNbArchivo((String)obj[4]);
			caVO.setStValidacion(((BigDecimal)obj[5]).intValueExact() == 1 ? Boolean.TRUE : Boolean.FALSE);
			caVO.setStActivo(((BigDecimal)obj[6]).intValueExact() == 1 ? Boolean.TRUE : Boolean.FALSE);
			caVO.setFhAsignacion((String)obj[7]);
			
			//Ubicamos el ultimo registro validado
			SQLQuery q2 = getCurrentSession().createSQLQuery(sql2);
			q2.setParameter("csv", ((BigDecimal)obj[8]).longValueExact());
			List<Object> d = q2.list();
			caVO.setFhUltimaValidacion((String)d.get(0));
			
			res.add(caVO);
		}
		
		return res;
	}
	
	@Override
	public Boolean cancelaAsignacion(Long idAsigna){
		Criteria c = getCurrentSession().createCriteria(AsignValidadorDTO.class);
		c.add(Restrictions.eq("idAsignValidador", idAsigna));
		
		AsignValidadorDTO res = (AsignValidadorDTO)c.uniqueResult();
		res.setStActivo((short)0);
		
		if(this.update(res)!=null) {
			return true;
		}	
		return false;
	}
	
	
	
	@Override
	public void actualizaEstatusValidacion(Long idArchivo, Long idValidador, Long usuario) {
		
		StringBuilder strQuery = new StringBuilder(" "
				+ " UPDATE TCI010D_PT_ASIGN_VALIDADOR SET ST_VALIDACION = 1, FH_MODIFICACION=SYSDATE, ID_USR_MODIFICA="+usuario+" WHERE ID_ARCHIVO_CSV = "+idArchivo);
		Query query = getCurrentSession().createSQLQuery(strQuery.toString());
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> MC_getTodosIdsDeArchivosAsignadosActivos() {
		Criteria c = getCurrentSession().createCriteria(AsignValidadorDTO.class);
//		c.setProjection(Projections.projectionList().add(Projections.property("idArchivoCsv"), "idArchivoCsv"))
//		.setResultTransformer(Transformers.aliasToBean(AsignValidadorDTO.class));
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
		List<AsignValidadorDTO> listAsignValidadorDTO = (List<AsignValidadorDTO>) c.list();
		
		List<Long> listaidArchivoCsv = new ArrayList<Long>();
		for(int i=0;i<listAsignValidadorDTO.size();i++) {
			listaidArchivoCsv.add(listAsignValidadorDTO.get(i).getIdArchivoCsv());
		}
		
		return listaidArchivoCsv;//resivar
	}
	
	@Override
	@Transactional
	public Long asignarArchivoValidador(ArchivosCsvRespuestaVO archivo, ValidadoresConfigDTO validadoresConfig, Long idUsuario) {
		AsignValidadorDTO asignacion = new AsignValidadorDTO();
		Long idRetornado;
		
		asignacion.setIdValidadorConfig(validadoresConfig);
		asignacion.setIdPtLote(archivo.getIdPtLote());
		asignacion.setIdArchivoCsv(archivo.getIdArchivoCsv());
		asignacion.setStValidacion((short) 0);
		asignacion.setStActivo((short) 1);	
		asignacion.setFhCreacion(new Date(System.currentTimeMillis()));		
		asignacion.setIdUsrCreacion(idUsuario);
		asignacion.setFhModificacion(new Date(System.currentTimeMillis()));
		asignacion.setIdUsrModifica(idUsuario);
		

		idRetornado = (Long) getCurrentSession().save(asignacion);
	
		return idRetornado;//OBSERVAR AQUI TAMBIEN
	}
	
	
}
