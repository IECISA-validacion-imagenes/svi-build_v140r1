package mx.com.teclo.svi.persistencia.hibernate.dao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqReporteRV;
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.InfoBasicaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteRVConsultaVO;
import mx.com.teclo.svi.negocio.vo.supervision.ClasificaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaIncidenciaCSVVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.IncidenciaVO;
import mx.com.teclo.svi.negocio.vo.supervision.InfoBasicaExpValidadoVO;
import mx.com.teclo.svi.negocio.vo.supervision.ItemAsignacionVO;
import mx.com.teclo.svi.negocio.vo.validacion.ValidacionDatosVO;
import mx.com.teclo.svi.negocio.vo.validacion.registrosInconsistenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.UltimaValidacionDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivoCsvDetallesVO;


@SuppressWarnings({"unchecked"})
@Repository("archivoDetalleEvaDAO")
public class ArchivoDetalleEvaDAOImpl extends BaseDaoHibernate<ArchivoDetalleEvaDTO>implements ArchivoDetalleEvaDAO {

	private static short ACTIVO = 1;
	private static short REVALIDACION = 1;
	
	private static final String QUERY_MARCAR_MASIVAMENTE = "UPDATE ArchivoDetalleEvaDTO SET st2daValidacion = 1, stInconsistencia = 0, idUsrModifica = :idUsr, fhModificacion = SYSDATE WHERE exists (select deteva.idRegistroCsv from ArchivoDetalleEvaDTO deteva inner join deteva.idArchivoCsv csv inner join csv.idPtLote lote where lote.idPtLote = :idPtLote)";
	private static final String QUERY_REINICIAR_BANDERAS_REVALIDACION = "UPDATE ArchivoDetalleEvaDTO tb1 SET st2daValidacion = 1, stInconsistencia = 0, idUsrModifica = :idUsr, fhModificacion = SYSDATE WHERE exists (SELECT detalleEva.idRegistroCsv FROM ArchivoDetalleEvaDTO detalleEva inner join detalleEva.idArchivoCsv csv inner join csv.idPtLote lote inner join lote.idEntrega entrega left outer join detalleEva.idMarca marca left outer join detalleEva.idSubMarca submarca left outer join detalleEva.idPerfil perfil left outer join detalleEva.idUltimaValidacion ultimaValidacion 			 left outer join ultimaValidacion.incidenciasCollection incidencias left outer join incidencias.idIncidencia incidencia where ";


	@Autowired
	EntregaDAO entregaDAO;
	@Autowired
	private ParametrosComponente parametrosComponente;
	
	@Autowired
	ArchivoCsvDAO archivoCsvDAO;

	@Autowired
	DetalleIncidenciaDAO detalleIncidenciaDAO;
	
	@Override
	public List<ArchivoDetalleEvaDTO> getRegistrosInconsistentes(Long idArchivoSCV) {//1
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv", "archivoCsvDTO");
		c.add(Restrictions.eq("archivoCsvDTO.idArchivoCsv",idArchivoSCV));
		c.add(Restrictions.eq("stValidacion", ACTIVO));
		c.add(Restrictions.eq("st2daValidacion", REVALIDACION));
		return (List<ArchivoDetalleEvaDTO>) c.list();
	}
	
	@Override
	@Transactional
	public List<ArchivoDetalleEvaDTO> getRegistrosValidados(long idArchivoSCV) {	
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv", "archivoCsvDTO");
		c.add(Restrictions.eq("archivoCsvDTO.idArchivoCsv",idArchivoSCV));
		c.add(Restrictions.eq("stValidacion", ACTIVO));
		return (List<ArchivoDetalleEvaDTO>) c.list();
	}
	
	@Override
	public Long getRegistrosInconsistentesAsignacion(Long idArchivoSCV) {//1
		String sql = "SELECT COUNT(*) AS TOTAL FROM TCI005D_PT_ARCHIVO_DETALLE_EVA " + 
				"WHERE ST_VALIDACION = 1 AND ST_REVALIDACION >=1 " + 
				"AND ID_ARCHIVO_CSV = "+idArchivoSCV;
		Query query2 = getCurrentSession().createSQLQuery(sql.toString()).addScalar("TOTAL",LongType.INSTANCE);
		Long idLote = (Long) query2.uniqueResult();
		return idLote;
	}
	
	@Override
	public Long getRegistrosInconsistentesValidadosAsignacion(Long idArchivoSCV) {//1
		String sql = "SELECT COUNT(*) AS TOTAL FROM TCI005D_PT_ARCHIVO_DETALLE_EVA " + 
				"WHERE ST_VALIDACION = 1 AND ST_REVALIDACION = 1 " + 
				"AND ID_ARCHIVO_CSV = "+idArchivoSCV;
		Query query2 = getCurrentSession().createSQLQuery(sql.toString()).addScalar("TOTAL",LongType.INSTANCE);
		Long idLote = (Long) query2.uniqueResult();
		return idLote;
	}


	@Override
	public Boolean validarSiPermitirDescargar(Long idPt){
		String sql = "SELECT CASE WHEN (SELECT COUNT(*) FROM TCI003D_PT_ARCHIVO_CSV csv2 WHERE csv2.ID_PT_LOTE = lote.ID_PT_LOTE AND ST_VALIDACION = 1) = COUNT(*) THEN 1 ELSE 0 END AS REQUIERE FROM TCI002D_PT_LOTE lote " 
				   + "INNER JOIN TCI003D_PT_ARCHIVO_CSV csv ON (lote.ID_PT_LOTE = csv.ID_PT_LOTE) "
				   + "WHERE lote.ID_PT_LOTE = :lote "
				   + "GROUP BY lote.ID_PT_LOTE";
		
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		q.setParameter("lote", idPt);
		
		BigDecimal val = (BigDecimal) q.uniqueResult();
		
		return val.intValueExact() == 1 ? Boolean.TRUE:Boolean.FALSE;
		
	}

	@Override
	public List<ReporteRVConsultaVO> consultarReporteResultadoValidacion(String fhInicio, String fhFin, Long idEntrega){
		
		String sql = "SELECT\r\n" + 
				"	IDENTREGA,\r\n" + 
				"	ENTREGAS AS \"NBENTREGA\",\r\n" + 
				"	LOTES AS \"NBLOTE\",\r\n" + 
				"	IDLOTE,\r\n" + 
				"	ARCH_CSV AS \"NU_ARCHIVOS\",\r\n" + 
				"	REGISTROS AS \"NU_REGISTROS\",\r\n" + 
				"	VALIDACIONES,\r\n" + 
				"	PENDIENTES\r\n" + 
				"\r\n" + 
				"FROM\r\n" + 
				"	(WITH RESUMEN AS (\r\n" + 
				"	SELECT\r\n" + 
				"		ENTREGA.ID_ENTREGA,\r\n" + 
				"		ENTREGA.NB_ENTREGA,\r\n" + 
				"		LOTE.NB_PT_LOTE,\r\n" + 
				"		LOTE.ID_PT_LOTE,\r\n" + 
				"		CSV.ID_ARCHIVO_CSV,\r\n" + 
				"		CSV.NB_ARCHIVO_CSV,\r\n" + 
				"		COUNT(EVA.ID_REGISTRO_CSV) TOT_REGS,\r\n" + 
				"		COUNT(CASE WHEN EVA.ST_ACTIVO = 1 AND EVA.ST_VALIDACION = 1 THEN 1 ELSE NULL END) TOT_VALIDS,\r\n" + 
				"		COUNT(EVA.ID_REGISTRO_CSV) - COUNT(CASE WHEN EVA.ST_ACTIVO = 1 AND EVA.ST_VALIDACION = 1 THEN 1 ELSE NULL END) AS TOT_PENDS\r\n" + 
				"	FROM\r\n" + 
				"		TCI001D_PT_ENTREGA ENTREGA\r\n" + 
				"	INNER JOIN TCI002D_PT_LOTE LOTE ON\r\n" + 
				"		ENTREGA.ID_ENTREGA = LOTE.ID_ENTREGA\r\n" + 
				"	INNER JOIN TCI003D_PT_ARCHIVO_CSV CSV ON\r\n" + 
				"		LOTE.ID_PT_LOTE = CSV.ID_PT_LOTE\r\n" + 
				"	LEFT OUTER JOIN TCI005D_PT_ARCHIVO_DETALLE_EVA EVA ON\r\n" + 
				"		EVA.ID_ARCHIVO_CSV = CSV.ID_ARCHIVO_CSV\r\n" + 
				"	GROUP BY\r\n" + 
				"		ENTREGA.ID_ENTREGA,\r\n" + 
				"		ENTREGA.NB_ENTREGA,\r\n" + 
				"		LOTE.ID_PT_LOTE,\r\n" + 
				"		LOTE.NB_PT_LOTE,\r\n" + 
				"		CSV.ID_ARCHIVO_CSV,\r\n" + 
				"		CSV.NB_ARCHIVO_CSV)\r\n" + 
				"	SELECT\r\n" + 
				"		RESUMEN.ID_ENTREGA AS IDENTREGA,\r\n" + 
				"		RESUMEN.NB_ENTREGA AS ENTREGAS,\r\n" + 
				"		RESUMEN.NB_PT_LOTE AS LOTES,\r\n" + 
				"		RESUMEN.ID_PT_LOTE AS IDLOTE,	\r\n" + 
				"		COUNT(DISTINCT RESUMEN.ID_ARCHIVO_CSV) ARCH_CSV,\r\n" + 
				"		SUM(TOT_REGS) REGISTROS,\r\n" + 
				"		SUM(TOT_VALIDS) VALIDACIONES,\r\n" + 
				"		SUM(TOT_PENDS) PENDIENTES\r\n" + 
				"	FROM\r\n" + 
				"		RESUMEN\r\n" + 
				"	GROUP BY\r\n" + 
				"		RESUMEN.ID_ENTREGA,\r\n" + 
				"		RESUMEN.NB_ENTREGA,\r\n" + 
				"		RESUMEN.NB_PT_LOTE,\r\n" + 
				"		RESUMEN.ID_PT_LOTE\r\n" + 
				")\r\n" + 
				"WHERE\r\n" + 
				"	1 = 1\r\n" + 
				"	AND\r\n" + 
				"	 PENDIENTES=0\r\n";
				
		
		if(idEntrega!=null){
			if(idEntrega!=-10) {
				sql = sql + " AND IDENTREGA = "+idEntrega;
			}
		}
		
		sql =sql + " ORDER BY\r\n" + 
				"	IDENTREGA,\r\n" + 
				"	ENTREGAS,\r\n" + 
				"	LOTES";

		SQLQuery q = getCurrentSession().createSQLQuery(sql).
				addScalar("IDENTREGA", LongType.INSTANCE)
				.addScalar("NBENTREGA", StringType.INSTANCE)
				.addScalar("NBLOTE", StringType.INSTANCE)
				.addScalar("IDLOTE", LongType.INSTANCE)
				.addScalar("NU_ARCHIVOS", IntegerType.INSTANCE)
				.addScalar("NU_REGISTROS", IntegerType.INSTANCE)
				.addScalar("VALIDACIONES", IntegerType.INSTANCE)
				.addScalar("PENDIENTES", IntegerType.INSTANCE);
		List<Object[]> lista = q.list();
		
		List<ReporteRVConsultaVO> res = new ArrayList<ReporteRVConsultaVO>();
		
		for(Object[] obj:lista){
			ReporteRVConsultaVO rrvcVO = new ReporteRVConsultaVO();
			rrvcVO.setIdEntrega(obj[0] != null ? Long.parseLong(obj[0].toString(), 10) : null);
			rrvcVO.setNbEntrega(obj[1] != null ? obj[1].toString() : null);
			rrvcVO.setNbLote(obj[2] != null ? obj[2].toString() : null);
			rrvcVO.setIdLote(obj[3] != null ? Long.parseLong(obj[3].toString(), 10) : null);
			rrvcVO.setNuArchivos(obj[4] != null ? Integer.parseInt(obj[4].toString(), 10) : null);
			rrvcVO.setNuRegistros(obj[5] != null ? Integer.parseInt(obj[5].toString(), 10) : null);
			rrvcVO.setNuValidaciones(obj[6] != null ? Integer.parseInt(obj[6].toString(), 10) : null);
			rrvcVO.setPendientes(obj[7] != null ? Integer.parseInt(obj[7].toString(), 10) : null);
			
			res.add(rrvcVO);
		}
		
		return res;
	}
	
	
	@Override
	@Transactional
	public List<ArchivoDetalleEvaDTO> generarListArchivosParaExcel(Long idArhivoCSV){	
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv","archivoCsvDTO");
		c.add(Restrictions.eq("archivoCsvDTO.idArchivoCsv", idArhivoCSV));
		c.add(Restrictions.eq("stValidacion", ACTIVO));
		return (List<ArchivoDetalleEvaDTO>) c.list();
	}
	
	@Override
	@Transactional
	public Long getNuRegistros(Long idPeriodo, String nbLote){
		String sql2  = "";
		sql2 = sql2 + "	SELECT count(deteva.ID_REGISTRO_CSV) as totalregistros \r\n" + 
				"	FROM TCI002D_PT_LOTE lote  \r\n" + 
				"	INNER JOIN TCI003D_PT_ARCHIVO_CSV csv ON (lote.ID_PT_LOTE = csv.ID_PT_LOTE)  \r\n" + 
				"	INNER JOIN TCI001D_PT_ENTREGA periodo ON (lote.ID_ENTREGA = periodo.ID_ENTREGA)\r\n" + 
				"	INNER JOIN TCI005D_PT_ARCHIVO_DETALLE_EVA deteva ON (deteva.ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) "
				+ " WHERE lote.ST_ACTIVO = 1";
		if(idPeriodo != null){
			sql2 = sql2 + "AND periodo.ID_ENTREGA = "+idPeriodo+" ";
		}
		sql2 = sql2 +"AND lote.NB_PT_LOTE ='"+nbLote+"' ";
		
		SQLQuery q2 = getCurrentSession().createSQLQuery(sql2.toString()).addScalar("totalregistros", LongType.INSTANCE);
		
		
//		TotalesExpedientesVO totales = new TotalesExpedientesVO();
//		List<TotalesExpedientesVO> listTotales = new ArrayList<TotalesExpedientesVO>();
		Long totalRegistros = (Long) q2.uniqueResult();
		return totalRegistros;
	}
	
	@Override
	public List<Object> consultarIncidencias(FiltroExpedienteVO filtroExpedienteVO){
		String sql  = "";
				   
				   
		if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.PT.getId()){
			sql = sql + "SELECT ID_PT_LOTE, NB_ENTREGA, NB_PT_LOTE, ST_VALIDO, ST_REVALIDACION, COUNT(*) AS CANTIDAD_CSV, SUM(TOTAL_VALIDADO) AS TOTAL_CSV_VALIDADOS, SUM(TOTAL_FALTANTE) AS TOTAL_CSV_RESTANTES FROM ( " 
					  + "SELECT lote.ID_PT_LOTE, periodo.NB_ENTREGA, lote.NB_PT_LOTE, lote.ST_VALIDACION AS ST_VALIDO, lote.ST_REVALIDACION, "
					  + "CASE WHEN lote.ST_REVALIDACION = 0 OR lote.ST_REVALIDACION != 0 AND csv.ST_VALIDACION = 0 THEN " 
					  + "(SELECT COUNT(*) FROM TCI003D_PT_ARCHIVO_CSV WHERE ST_VALIDACION = 1 AND ST_REVALIDACION = 0 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) "
					  + "WHEN lote.ST_REVALIDACION != 0 THEN " 
					  + "(SELECT COUNT(*) FROM TCI003D_PT_ARCHIVO_CSV WHERE ST_VALIDACION = 1 AND ST_REVALIDACION != 1 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) END AS TOTAL_VALIDADO, "
					  + "CASE WHEN lote.ST_REVALIDACION = 0 THEN "
					  + "(SELECT COUNT(*) FROM TCI003D_PT_ARCHIVO_CSV WHERE ST_VALIDACION = 0 AND ST_REVALIDACION = 0 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) "
					  + "WHEN lote.ST_REVALIDACION != 0 THEN "
					  + "(SELECT COUNT(*) FROM TCI003D_PT_ARCHIVO_CSV WHERE ST_VALIDACION = 0 AND ST_REVALIDACION != 2 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) END AS TOTAL_FALTANTE " 
					  + "FROM TCI002D_PT_LOTE lote " 
					  + "INNER JOIN TCI003D_PT_ARCHIVO_CSV csv ON (lote.ID_PT_LOTE = csv.ID_PT_LOTE) " 
					  + "INNER JOIN TCI001D_PT_ENTREGA periodo ON (lote.ID_ENTREGA = periodo.ID_ENTREGA) WHERE lote.ST_ACTIVO = 1 ";
			
			if(filtroExpedienteVO.getListaEntregas().get(0) != null){
				sql = sql + "AND periodo.ID_ENTREGA = "+filtroExpedienteVO.getListaEntregas().get(0)+" ";
			}
			sql = sql + ") ";
			
//			sql = sql +"WHERE NB_PT_LOTE LIKE '%"+valor+"%' ";
			
			if(filtroExpedienteVO.getListaLotes().size() == 1){
				sql = sql +"WHERE ID_PT_LOTE = "+filtroExpedienteVO.getListaLotes().get(0)+" ";
			}else if(filtroExpedienteVO.getListaLotes().size() > 1){
				sql = sql +"WHERE ID_PT_LOTE IN ("+filtroExpedienteVO.getListaLotes().get(0);
				for(int i=1;i<filtroExpedienteVO.getListaLotes().size();i++){
					if(i+1 == filtroExpedienteVO.getListaLotes().size()){
						sql = sql +","+filtroExpedienteVO.getListaLotes().get(i)+") ";
					}else{
						sql = sql +","+filtroExpedienteVO.getListaLotes().get(i);
					}
				}
			}
					  
			sql = sql + "GROUP BY ID_PT_LOTE, NB_ENTREGA, NB_PT_LOTE, ST_VALIDO, ST_REVALIDACION " 
					  + "ORDER BY ID_PT_LOTE, NB_ENTREGA, NB_PT_LOTE, ST_VALIDO, ST_REVALIDACION ASC ";
			
		}else if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.CSV.getId()){
			sql = sql + "SELECT csv.ID_ARCHIVO_CSV, periodo.NB_ENTREGA, lote.NB_PT_LOTE, csv.NB_ARCHIVO_CSV, csv.ST_VALIDACION, csv.ST_REVALIDACION, lote.ST_VALIDACION as ST_PT_VALIDACION, " 
					  + "(SELECT COUNT(*) FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) AS NU_REGISTROS_CSV, "
					  + "CASE WHEN csv.ST_REVALIDACION = 0 THEN "
					  + "(SELECT COUNT(*) FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ST_VALIDACION = 1 AND ST_INCONSISTENCIA = 0 AND ST_REVALIDACION = 0 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) " 
					  + "WHEN csv.ST_REVALIDACION != 0 THEN " 
					  + "(SELECT COUNT(*) FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ST_VALIDACION = 1 AND ST_INCONSISTENCIA = 0 AND ST_REVALIDACION != 1 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) END AS TOTAL_VALIDADO, "
					  + "CASE WHEN csv.ST_REVALIDACION = 0 THEN "
					  + "(SELECT COUNT(*) FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ST_VALIDACION = 1 AND ST_INCONSISTENCIA != 0 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) " 
					  + "WHEN csv.ST_REVALIDACION != 0 THEN "
					  + "(SELECT COUNT(*) FROM TCI005D_PT_ARCHIVO_DETALLE_EVA WHERE ST_VALIDACION = 1 AND ST_INCONSISTENCIA != 0 AND ST_REVALIDACION != 1 AND ID_ARCHIVO_CSV = csv.ID_ARCHIVO_CSV) END AS TOTAL_INCIDENCIA "
					  + "FROM TCI002D_PT_LOTE lote "
					  + "INNER JOIN TCI003D_PT_ARCHIVO_CSV csv ON (lote.ID_PT_LOTE = csv.ID_PT_LOTE) " 
					  + "INNER JOIN TCI001D_PT_ENTREGA periodo ON (lote.ID_ENTREGA = periodo.ID_ENTREGA) " 
			 		  + "WHERE csv.ST_ACTIVO = 1 AND ";
			
			if(filtroExpedienteVO.getListaCsvs().size() == 1){
				sql = sql +"csv.ID_ARCHIVO_CSV = "+filtroExpedienteVO.getListaCsvs().get(0)+" ";
			}else if(filtroExpedienteVO.getListaCsvs().size() > 1){
				sql = sql +"csv.ID_ARCHIVO_CSV IN ("+filtroExpedienteVO.getListaCsvs().get(0);
				for(int i=1;i<filtroExpedienteVO.getListaCsvs().size();i++){
					if(i+1 == filtroExpedienteVO.getListaCsvs().size()){
						sql = sql +","+filtroExpedienteVO.getListaCsvs().get(i)+") ";
					}else{
						sql = sql +","+filtroExpedienteVO.getListaCsvs().get(i);
					}
				}
			}
			
			
			if(filtroExpedienteVO.getListaEntregas().get(0) != null){
				sql = sql + "AND periodo.ID_ENTREGA = "+filtroExpedienteVO.getListaEntregas().get(0)+" ";
			}
		}
	
		
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		
		List<Object[]> lista = q.list();
		
		List<Object> res = new ArrayList<Object>();
		if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.PT.getId()){
			for(Object[] obj:lista){
				ConsultaIncidenciaPTVO ciVO = new ConsultaIncidenciaPTVO();
				ciVO.setIdPtLote(((BigDecimal)obj[0]).longValueExact());
				ciVO.setNbEntrega((String)obj[1]);
				ciVO.setNbPtLote((String)obj[2]);
				ciVO.setStValidacion(((BigDecimal)obj[3]).intValueExact() == 1 ? Boolean.TRUE : Boolean.FALSE);
				ciVO.setStRevalidacion(((BigDecimal)obj[4]).intValueExact() != 1 ? Boolean.TRUE : Boolean.FALSE);
				ciVO.setNuCsv(((BigDecimal)obj[5]).longValueExact());
				ciVO.setNuValidados(((BigDecimal)obj[6]).longValueExact());
				ciVO.setNuFaltantes(((BigDecimal)obj[7]).longValueExact());
				
				res.add(ciVO);
			}
		}else if(filtroExpedienteVO.getTipoBusqueda() == EnumTipoBusqReporteRV.CSV.getId()){
			for(Object[] obj:lista){
				ConsultaIncidenciaCSVVO ciVO = new ConsultaIncidenciaCSVVO();
				ciVO.setIdArchivoCsv(((BigDecimal)obj[0]).longValueExact());
				ciVO.setPeriodo((String)obj[1]);
				ciVO.setLote((String)obj[2]);
				ciVO.setArchivoCsv((String)obj[3]);
				ciVO.setStValidacion(((BigDecimal)obj[4]).intValueExact() == 1 ? Boolean.TRUE : Boolean.FALSE);
				ciVO.setStRevalidacion(((BigDecimal)obj[5]).intValueExact() != 1 ? Boolean.TRUE : Boolean.FALSE);
				ciVO.setStPtValidacion(((BigDecimal)obj[6]).intValueExact() == 1 ? Boolean.TRUE : Boolean.FALSE);
				ciVO.setTotalRegistro(((BigDecimal)obj[7]).longValueExact());
				ciVO.setTotalValidado(((BigDecimal)obj[8]).longValueExact());
				ciVO.setTotalIncidencia(((BigDecimal)obj[9]).longValueExact());
				res.add(ciVO);
			}
		}
		
		return res;
	}

	@Override
	public List<ConsultaDetalleIncidenciaCSVVO> consultarDetalleIncidenciasCSV(Long idArchivo){
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv", "csv");
		c.add(Restrictions.eq("csv.idArchivoCsv", idArchivo));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
		List<ArchivoDetalleEvaDTO> list = (List<ArchivoDetalleEvaDTO>)c.list();
		List<ConsultaDetalleIncidenciaCSVVO> res = new ArrayList<ConsultaDetalleIncidenciaCSVVO>();
		for(ArchivoDetalleEvaDTO adeDTO:list){
			ConsultaDetalleIncidenciaCSVVO cdiVO = new ConsultaDetalleIncidenciaCSVVO();
			cdiVO.setIdRegistroCsv(adeDTO.getIdRegistroCsv());
			cdiVO.setNuExpediente(adeDTO.getNuExpediente());
			cdiVO.setNuCarril(adeDTO.getNuCarril().toString());
			cdiVO.setPlacaDelantera(adeDTO.getCdPlacaDelantera());
			cdiVO.setEntidadDelantera(adeDTO.getCdEntidadDelantera());
			cdiVO.setPlacaTrasera(adeDTO.getCdPlacaTrasera());
			cdiVO.setEntidadTrasera(adeDTO.getCdEntidadTrasera());
			cdiVO.setPerfil(adeDTO.getCdPerfil());
			cdiVO.setValidacion(adeDTO.getStValidacion() == (short)1 ? Boolean.TRUE:Boolean.FALSE);
			cdiVO.setStPreSeleccion(adeDTO.getStPreSeleccion());
			cdiVO.setInconsistencia(adeDTO.getStInconsistencia() == (short) 1 ? detalleIncidenciaDAO.buscarIncidenciasPorRegistro(adeDTO.getIdRegistroCsv()): new ArrayList<IncidenciaVO>());
			cdiVO.setClasificacion(adeDTO.getIdPtClasifExpe() != null ? new ClasificaExpedienteVO(adeDTO.getIdPtClasifExpe().getCdClasifExpe(), adeDTO.getIdPtClasifExpe().getTxClasifExpe()):null);
			cdiVO.setIdMarca(adeDTO.getIdMarca().getIdPtMarca());
			cdiVO.setIdSubmarca(adeDTO.getIdSubMarca().getIdPtSubmarca());
			cdiVO.setIdPerfil(adeDTO.getIdPerfil().getIdPtPerfiles());
			
			res.add(cdiVO);
		}
		
		return res;
	}
	
	@Override
	public List<ConsultaDetalleIncidenciaCSVVO> consultarDetalleIncidenciasCSV(List<Long> listIdRegistro){
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv", "csv");
		c.add(Restrictions.in("idRegistroCsv", listIdRegistro));
		c.add(Restrictions.eq("stActivo", (short) 1));
		
		List<ArchivoDetalleEvaDTO> list = (List<ArchivoDetalleEvaDTO>)c.list();
		List<ConsultaDetalleIncidenciaCSVVO> res = new ArrayList<ConsultaDetalleIncidenciaCSVVO>();
		for(ArchivoDetalleEvaDTO adeDTO:list){
			ConsultaDetalleIncidenciaCSVVO cdiVO = new ConsultaDetalleIncidenciaCSVVO();
			cdiVO.setIdRegistroCsv(adeDTO.getIdRegistroCsv());
			cdiVO.setNuExpediente(adeDTO.getNuExpediente());
			cdiVO.setNuCarril(adeDTO.getNuCarril().toString());
			cdiVO.setPlacaDelantera(adeDTO.getCdPlacaDelantera());
			cdiVO.setEntidadDelantera(adeDTO.getCdEntidadDelantera());
			cdiVO.setPlacaTrasera(adeDTO.getCdPlacaTrasera());
			cdiVO.setEntidadTrasera(adeDTO.getCdEntidadTrasera());
			cdiVO.setPerfil(adeDTO.getCdPerfil());
			cdiVO.setValidacion(adeDTO.getStValidacion() == (short)1 ? Boolean.TRUE:Boolean.FALSE);
			cdiVO.setStPreSeleccion(adeDTO.getStPreSeleccion());
			cdiVO.setInconsistencia(adeDTO.getStInconsistencia() == (short) 1 ? detalleIncidenciaDAO.buscarIncidenciasPorRegistro(adeDTO.getIdRegistroCsv()): new ArrayList<IncidenciaVO>());
			cdiVO.setClasificacion(adeDTO.getIdPtClasifExpe() != null ? new ClasificaExpedienteVO(adeDTO.getIdPtClasifExpe().getCdClasifExpe(), adeDTO.getIdPtClasifExpe().getTxClasifExpe()):null);
			cdiVO.setIdMarca(adeDTO.getIdMarca().getIdPtMarca());
			cdiVO.setIdSubmarca(adeDTO.getIdSubMarca().getIdPtSubmarca());
			cdiVO.setIdPerfil(adeDTO.getIdPerfil().getIdPtPerfiles());
			
			res.add(cdiVO);
		}
		
		return res;
	}
	
	@Override
	public String test(){
		Criteria c = getCurrentSession().createCriteria(EntregaDTO.class);
		EntregaDTO eDTO = (EntregaDTO)c.uniqueResult();
		eDTO.setNuTotalRegSil(eDTO.getNuTotalRegSil() + 1L);
		entregaDAO.save(eDTO);
		return eDTO.getNuTotalRegSil().toString();
	}
	
	
	@Override
	public ValidacionDatosVO archivoEstaValidado(Long idArchivo, ValidadoresConfigDTO validador) {
		
		ValidacionDatosVO datosv = new ValidacionDatosVO();
		
		StringBuilder strQuery = new StringBuilder(" "
				+ "SELECT COUNT(*) TOTAL "
				+ "FROM TCI005D_PT_ARCHIVO_DETALLE_EVA ARCHDETALLE "
				+ "WHERE ARCHDETALLE.ID_ARCHIVO_CSV = "+idArchivo+" AND ARCHDETALLE.ST_VALIDACION=1 AND ARCHDETALLE.ST_ACTIVO=1");
		Query query = getCurrentSession().createSQLQuery(strQuery.toString()).addScalar("TOTAL", LongType.INSTANCE);
		Long totalValidados = (Long) query.uniqueResult();
		
		StringBuilder strQuery2 = new StringBuilder(" "
				+ " SELECT count(*) AS NU_REGISTROS_CSV  FROM  TCI005D_PT_ARCHIVO_DETALLE_EVA DETEVA WHERE DETEVA.ID_ARCHIVO_CSV = "+idArchivo);
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("NU_REGISTROS_CSV", LongType.INSTANCE);
		Long totalRegistros = (Long) query2.uniqueResult();
		datosv.setTotalRegistros(totalRegistros);
		datosv.setTotalValidadas(totalValidados);
		datosv.setTotalRestantes(totalRegistros-totalValidados);
		
		Long cantidadTotalMaximaAsignar=0L;
		if(validador.getIdConfiguracion().getIdConfiguracion().equals( 
				EnumTipoBusqReporteRV.REGISTRO.getId())) {
			cantidadTotalMaximaAsignar = validador.getIdConfiguracion().getNuMax();
				
		}else if(validador.getIdConfiguracion().getIdConfiguracion().equals(
				EnumTipoBusqReporteRV.CSV.getId())) {
			cantidadTotalMaximaAsignar = totalRegistros;
		}
		
		
		
		datosv.setTotalRegistrosAsignados(cantidadTotalMaximaAsignar);
		
		if(totalRegistros>0L&&totalValidados>0L&&totalRegistros.equals(totalValidados)) {
			datosv.setArchivoValidado(true);
		}else {
			datosv.setArchivoValidado(false);
		}
		return datosv;
	}

	@Override
	public String obtenerHost() {
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();
		
		return rutaHost;
	}
	
	@Override
	public List<InfoBasicaExpedienteVO> getExpedientes(FiltroExpedienteVO filtro) {
		Criteria criteria = obtenerCriteriaBusqueda(filtro);
		criteria.createAlias("ultimaValidacion.incidenciasCollection", "ultimasIncidencias", JoinType.LEFT_OUTER_JOIN);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("detalleEva.idRegistroCsv").as("idRegistroCsv"))
				.add(Projections.groupProperty("entrega.nbEntrega").as("nbEntrega"))
				.add(Projections.groupProperty("lote.nbPtLote").as("nbPtLote"))
				.add(Projections.groupProperty("csv.nbArchivoCsv").as("nbArchivoCsv"))
				.add(Projections.groupProperty("detalleEva.cdPlacaDelantera").as("cdPlacaDelantera"))
				.add(Projections.groupProperty("detalleEva.cdEntidadDelantera").as("cdEntidadDelantera"))
				.add(Projections.groupProperty("detalleEva.cdPlacaTrasera").as("cdPlacaTrasera"))
				.add(Projections.groupProperty("detalleEva.cdEntidadTrasera").as("cdEntidadTrasera"))
				.add(Projections.groupProperty("detalleEva.nbEstado").as("nbEstado"))
				.add(Projections.groupProperty("detalleEva.nuExpediente").as("nuExpediente"))
				.add(Projections.groupProperty("ultimaValidacion.nuOrden").as("validaciones"))
				.add(Projections.count("ultimasIncidencias.idRegistroCsv").as("incidencias")));	
		
		criteria.addOrder(Order.asc("entrega.nbEntrega"));
		criteria.addOrder(Order.asc("lote.nbPtLote"));
		criteria.addOrder(Order.asc("csv.nbArchivoCsv"));
		criteria.addOrder(Order.asc("detalleEva.nuExpediente"));
		
		criteria.setResultTransformer(Transformers.aliasToBean(InfoBasicaExpedienteVO.class));

		List<InfoBasicaExpedienteVO> listaArchivosCsv = criteria.list();
		return listaArchivosCsv;
	}

	@Override
	public DetalleExpedienteVO getExpediente(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class, "detalleEva");
		criteria.createAlias("detalleEva.idArchivoCsv", "csv");
		criteria.createAlias("csv.idPtLote", "lote");
		criteria.createAlias("lote.idEntrega", "entrega");
		criteria.createAlias("detalleEva.idPTClasifExpe", "clasif", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idPtSilueta", "silueta", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idPerfil", "perfil", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idMarca", "marca", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idSubMarca", "submarca", JoinType.LEFT_OUTER_JOIN);

		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("detalleEva.idRegistroCsv").as("idRegistroCsv"))
				.add(Projections.property("entrega.idEntrega").as("idEntrega"))
				.add(Projections.property("entrega.nbEntrega").as("nbEntrega"))
				.add(Projections.property("lote.idPtLote").as("idPtLote"))
				.add(Projections.property("lote.nbPtLote").as("nbPtLote"))
				.add(Projections.property("csv.idArchivoCsv").as("idArchivoCsv"))
				.add(Projections.property("csv.nbArchivoCsv").as("nbArchivoCsv"))
				.add(Projections.property("csv.txCarpetaImg").as("txCarpetaImg"))
				.add(Projections.property("csv.txCarpetaSil").as("txCarpetaSil"))
				.add(Projections.property("detalleEva.nodoVpn").as("nodoVpn"))
				.add(Projections.property("detalleEva.nuCarril").as("nuCarril"))				
				.add(Projections.property("detalleEva.nuExpediente").as("nuExpediente"))
				.add(Projections.property("detalleEva.cdPlacaDelantera").as("cdPlacaDelantera"))
				.add(Projections.property("detalleEva.cdEntidadDelantera").as("cdEntidadDelantera"))
				.add(Projections.property("detalleEva.cdPlacaTrasera").as("cdPlacaTrasera"))
				.add(Projections.property("detalleEva.cdEntidadTrasera").as("cdEntidadTrasera"))
				.add(Projections.property("detalleEva.nbImagenPlacaDelantera").as("nbImagenPlacaDelantera"))
				.add(Projections.property("detalleEva.nbImagenPlacaTrasera").as("nbImagenPlacaTrasera"))
				.add(Projections.property("detalleEva.nbImagenConductor").as("nbImagenConductor"))
				.add(Projections.property("detalleEva.nbImagenAmbiental").as("nbImagenAmbiental"))
				.add(Projections.property("detalleEva.nbImagenPerfil").as("nbImagenPerfil"))
				.add(Projections.property("detalleEva.cdPerfil").as("cdPerfil"))
				.add(Projections.property("silueta.txSilueta").as("txSilueta"))
				.add(Projections.property("perfil.idPtPerfiles").as("idPerfil"))
				.add(Projections.property("perfil.txDescripcion").as("txPerfil"))
				.add(Projections.property("marca.txtMarca").as("txMarca"))
				.add(Projections.property("submarca.txtMarca").as("txSubMarca"))
				.add(Projections.property("clasif.txClasifExpe").as("txClasifExpe"))
				.add(Projections.sqlProjection(
						"(SELECT TX_DESC_ENTIDAD FROM  TCI011C_PT_ENTIDAD WHERE CD_ENTIDAD = cd_entidad_delantera) AS txDescEntidadDelantera",
						new String[] { "txDescEntidadDelantera" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }),
						"txDescEntidadDelantera")
				.add(Projections.sqlProjection(
						"(SELECT TX_DESC_ENTIDAD FROM  TCI011C_PT_ENTIDAD WHERE CD_ENTIDAD = cd_entidad_trasera) AS txDescEntidadTrasera",
						new String[] { "txDescEntidadTrasera" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }),
						"txDescEntidadTrasera")
				.add(Projections.sqlProjection("case when this_.ST_VALIDACION =1 then 't' else 'f' end  AS stValidacion",
						new String[] { "stValidacion" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValidacion")
				.add(Projections.sqlProjection(
						"case when ST_VAL_ENTIDAD_DELANTERA = 1 then 't' else 'f' end  AS stValEntidadDelantera",
						new String[] { "stValEntidadDelantera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValEntidadDelantera")
				.add(Projections.sqlProjection(
						"case when ST_VAL_ENTIDAD_TRASERA = 1 then 't' else 'f' end  AS stValEntidadTrasera",
						new String[] { "stValEntidadTrasera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValEntidadTrasera")
				.add(Projections.sqlProjection(
						"case when ST_VAL_IMAGEN_MAL = 1 then 't' else 'f' end  AS stValImagenMal",
						new String[] { "stValImagenMal" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValImagenMal")
				.add(Projections.sqlProjection("case when ST_VAL_PERFIL = 1 then 't' else 'f' end  AS stValPerfil",
						new String[] { "stValPerfil" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValPerfil")
				.add(Projections.sqlProjection(
						"case when ST_VAL_PLACA_DELANTERA = 1 then 't' else 'f' end  AS stValPlacaDelantera",
						new String[] { "stValPlacaDelantera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValPlacaDelantera")
				.add(Projections.sqlProjection(
						"case when ST_VAL_PLACA_TRASERA = 1 then 't' else 'f' end  AS stValPlacaTrasera",
						new String[] { "stValPlacaTrasera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stValPlacaTrasera")
				.add(Projections.sqlProjection(
						"case when ST_PLACA_OFI_DELANTERA = 1 then 't' else 'f' end  AS stPlacaOfiDelantera",
						new String[] { "stPlacaOfiDelantera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stPlacaOfiDelantera")
				.add(Projections.sqlProjection(
						"case when ST_PLACA_OFI_TRASERA = 1 then 't' else 'f' end  AS stPlacaOfiTrasera",
						new String[] { "stPlacaOfiTrasera" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stPlacaOfiTrasera")
				.add(Projections.sqlProjection("case when ST_POCHOMOVIL = 1 then 't' else 'f' end  AS stPochomovil",
						new String[] { "stPochomovil" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stPochomovil")
				.add(Projections.sqlProjection("case when ST_DOBLE_PLACA = 1 then 't' else 'f' end  AS stDoblePlaca",
						new String[] { "stDoblePlaca" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stDoblePlaca")
				.add(Projections.sqlProjection("case when ST_PRE_SELECCION = 1 then 't' else 'f' end  AS stPreSeleccion",
						new String[] { "stPreSeleccion" },
						new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
						"stPreSeleccion")
				.add(Projections.sqlProjection(
						"(SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam()+") AS txRutaStorage",
						new String[] { "txRutaStorage" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "txRutaStorage"))
				.add(Restrictions.eq("detalleEva.idRegistroCsv", id))
				.setResultTransformer(Transformers.aliasToBean(DetalleExpedienteVO.class));
		return (DetalleExpedienteVO) criteria.uniqueResult();
	}

	
	@Override
	public Boolean habilitarRevalidacion(List<Long> idLista, Long idUsuario){
		Boolean res = false;
		int contador =0;
		List<String> listIds = new ArrayList<String>();
		String ids="";
		
		for(Long id: idLista){
			contador++;		
			if (contador % 400 == 0||contador==idLista.size()) {
				ids=ids+id+"";
				listIds.add(ids);
				ids="";
			}else {
				ids=ids+id+",";
			}
		}
		
		for(int i=0;i<listIds.size();i++) {
			Query q = getCurrentSession().createQuery("UPDATE ArchivoDetalleEvaDTO SET st2daValidacion = 1, stInconsistencia = 0, idUsrModifica = :idUsr, fhModificacion = SYSDATE WHERE idRegistroCsv in ("+listIds.get(i)+")");
			q.setLong("idUsr", idUsuario);
			
			q.executeUpdate();
			res = true;
			if(!res){break;}
		}
		return res;
	}
	
	@Override
	public void deshabilitarAntiguasIncidencias(Long idArchivoCsv, Long idUsuario){
		String sql = "SELECT idRegistroCsv FROM ArchivoDetalleEvaDTO WHERE stInconsistencia = 1 AND idArchivoCsv = :idCsv";
		Query q = getCurrentSession().createQuery(sql);
		q.setLong("idCsv", idArchivoCsv);
		
		List<? extends Object> res = (List<BigDecimal>)q.list();
		
		List<Long> lista = (List<Long>)res;
		
		detalleIncidenciaDAO.deshabilitarIncidenciasPorRegistros(lista, idUsuario);
	}

	@Override
	public Boolean habilitarRevalidacion(Long idArchivoCsv, Long idUsuario) {
		Boolean res = false;
		
		Query q = getCurrentSession().createQuery("UPDATE ArchivoDetalleEvaDTO SET st2daValidacion = 1, stInconsistencia = 0, idUsrModifica = :idUsr, fhModificacion = SYSDATE WHERE idArchivoCsv = :idReg");
		q.setLong("idUsr", idUsuario);
		q.setLong("idReg", idArchivoCsv);
			
		q.executeUpdate();
		res = true;
			
		return res;
	}
	
	@Override
	@Transactional
	public List<ArchivoCsvDetallesVO> getTodosDetallesArchivoPorRegistro(List<Long> idRegistros) { 
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.add(Restrictions.in("idRegistroCsv", idRegistros));
		c.add(Restrictions.eq("stInconsistencia", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.addOrder(Order.asc("idRegistroCsv"));
		
		List<ArchivoDetalleEvaDTO> list = c.list();
		int totreg=0;
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
		
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();
		
		List<ArchivoCsvDetallesVO> listaArchivosDetalleRespuesta = new ArrayList<ArchivoCsvDetallesVO>();
		ArchivoCsvDetallesVO datos = null;
		totreg = list.size();
		for(ArchivoDetalleEvaDTO adeDTO:list){//Solo debería haber un solo archivo
			datos = new ArchivoCsvDetallesVO();
			
			datos.setIdArchivoCsv(adeDTO.getIdArchivoCsv().getIdArchivoCsv());
			datos.setIdPtLote(adeDTO.getIdArchivoCsv().getIdPtLote().getIdPtLote());//revisame here
			datos.setNbArchivoCsv(adeDTO.getIdArchivoCsv().getNbArchivoCsv());
			datos.setNuRegistrosCsv(adeDTO.getIdArchivoCsv().getNuRegistrosCsv());
			datos.setNbCarpetaImg(adeDTO.getIdArchivoCsv().getNbCarpetaImg());
			datos.setTxCarpetaImg(adeDTO.getIdArchivoCsv().getTxCarpetaImg());
			datos.setNuImagenes(adeDTO.getIdArchivoCsv().getNuImagenes());
			datos.setNbCarpetaSil(adeDTO.getIdArchivoCsv().getNbCarpetaSil());
			datos.setTxCarpetaSil(adeDTO.getIdArchivoCsv().getTxCarpetaSil());
			datos.setNuSiluetas(adeDTO.getIdArchivoCsv().getNuSiluetas());
			datos.setStActivo(adeDTO.getIdArchivoCsv().getStActivo());
			datos.setTxRutaStorage(rutaHost);
			
			datos.setIdRegistroCsv(adeDTO.getIdRegistroCsv());
			datos.setNodoVpn(adeDTO.getNodoVpn());
			datos.setNuCarril(adeDTO.getNuCarril());
			datos.setCdPlacaDelantera(adeDTO.getCdPlacaDelantera());
			datos.setTxtPlacaDelantera(adeDTO.getCdPlacaDelantera());
			datos.setCdEntidadDelantera(adeDTO.getCdEntidadTrasera());
			datos.setCdPlacaTrasera(adeDTO.getCdPlacaTrasera());
			datos.setTxtPlacaTrasera(adeDTO.getCdPlacaTrasera());
			datos.setCdEntidadTrasera(adeDTO.getCdEntidadTrasera());
			datos.setNbImagenPlacaDelantera(adeDTO.getNbImagenPlacaDelantera());
			datos.setNbImagenPlacaTrasera(adeDTO.getNbImagenPlacaTrasera());
			datos.setNbImagenConductor(adeDTO.getNbImagenConductor());
			datos.setNbImagenAmbiental(adeDTO.getNbImagenAmbiental());
			datos.setCdPerfil(adeDTO.getCdPerfil());
			datos.setNbImagenPerfil(adeDTO.getNbImagenPerfil());
			datos.setStValidacion(adeDTO.getStValidacion());
			datos.setIdPtClasifExpe(adeDTO.getIdPtClasifExpe()!=null?adeDTO.getIdPtClasifExpe().getIdPtClasifExpe():0L);
			datos.setTotAsignacionInicial(totreg);
//			datos.setIdCatValidacion(adeDTO.getidc); No se de donde proviene?
			
			listaArchivosDetalleRespuesta.add(datos);	
		}
		
		return listaArchivosDetalleRespuesta;
	}
	
	@Override
	@Transactional
	public Long getNuRegistrosByIdCSV(Long idCSV) {
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.createAlias("idArchivoCsv", "archivoCsvDTO");
		c.add(Restrictions.eq("archivoCsvDTO.idArchivoCsv",idCSV));
		List<Long> list = c.list();
		return new Long(list.size());
	}
	
	@Override
	@Transactional
	public List<Long> getTodosIdArchivoQueEstenAsignadosSusRegistros(List<Long> idRegistros) {
		List<Long> listaRegistrosLong = new ArrayList<Long>();
		if(!idRegistros.isEmpty()) {
			Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
			c.add(Restrictions.in("idRegistroCsv", idRegistros));
			c.addOrder(Order.desc("fhCreacion"));
			List<ArchivoDetalleEvaDTO> listaRegistros = (List<ArchivoDetalleEvaDTO>) c.list();

			for(int i=0;i<listaRegistros.size();i++) {
				listaRegistrosLong.add(listaRegistros.get(i).getIdArchivoCsv().getIdArchivoCsv());
			}
		}
		
		return listaRegistrosLong;
	}
	
	@Override
	@Transactional
	public List<Long> getTodosIdRegistrosPorIdArchivo(Long idArchivo, List<Long> listIdRegistrosActivos) { 
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class);
		c.add(Restrictions.eq("stActivo", (short) 1));
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.createAlias("idArchivoCsv", "archivoCsvDTO");
		c.add(Restrictions.eq("archivoCsvDTO.idArchivoCsv",idArchivo));
		if(listIdRegistrosActivos!=null) {
			c.add(Restrictions.not(
				    Restrictions.in("idRegistroCsv", listIdRegistrosActivos)
				  ));
			c.addOrder(Order.asc("idRegistroCsv"));
		}
		
		
		List<ArchivoDetalleEvaDTO> listaRegistros = (List<ArchivoDetalleEvaDTO>) c.list();
		List<Long> listaRegistrosLong = new ArrayList<Long>();
		for(int i=0;i<listaRegistros.size();i++) {
			listaRegistrosLong.add(listaRegistros.get(i).getIdRegistroCsv());
		}
		return listaRegistrosLong;
	}
	
	@Override
	public List<Long> obtenerListaDeNavegacion(FiltroExpedienteVO filtro) {
		Criteria criteria = obtenerCriteriaBusqueda(filtro);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("detalleEva.idRegistroCsv")));
		criteria.addOrder(Order.asc("entrega.nbEntrega"));
		criteria.addOrder(Order.asc("lote.nbPtLote"));
		criteria.addOrder(Order.asc("csv.nbArchivoCsv"));
		criteria.addOrder(Order.asc("detalleEva.nuExpediente"));
		return criteria.list();
	}
	
	private Criteria obtenerCriteriaBusqueda(FiltroExpedienteVO filtro) {		
		
		
		
		Criteria criteria = getCurrentSession().createCriteria(ArchivoDetalleEvaDTO.class, "detalleEva");
		criteria.createAlias("detalleEva.idArchivoCsv", "csv");
		criteria.createAlias("csv.idPtLote", "lote");
		criteria.createAlias("lote.idEntrega", "entrega");				
		criteria.createAlias("detalleEva.idMarca", "marca", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idSubMarca", "submarca", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idPerfil", "perfil", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("detalleEva.idUltimaValidacion", "ultimaValidacion", JoinType.LEFT_OUTER_JOIN);		
			
		
		if (filtro != null && filtro.getNuExpediente() != null && StringUtils.isNotBlank(filtro.getNuExpediente())) {
			criteria.add(Restrictions.eq("detalleEva.nuExpediente", filtro.getNuExpediente()));
		}
		if (filtro != null && filtro.getCdPlaca() != null && StringUtils.isNotBlank(filtro.getCdPlaca())) {
			Criterion rest1 = Restrictions.ilike("detalleEva.cdPlacaDelantera", filtro.getCdPlaca());
			Criterion rest2 = Restrictions.ilike("detalleEva.cdPlacaTrasera", filtro.getCdPlaca());
			criteria.add(Restrictions.or(rest1, rest2));
		}

		if (filtro != null && filtro.getListaEntregas() != null && !filtro.getListaEntregas().isEmpty()) {
			criteria.add(Restrictions.in("entrega.idEntrega", filtro.getListaEntregas()));
		}
		if (filtro != null && filtro.getListaLotes() != null && !filtro.getListaLotes().isEmpty()) {
			criteria.add(Restrictions.in("lote.idPtLote", filtro.getListaLotes()));
		}
		if (filtro != null && filtro.getListaCsvs() != null && !filtro.getListaCsvs().isEmpty()) {
			criteria.add(Restrictions.in("csv.idArchivoCsv", filtro.getListaCsvs()));
		}
		if (filtro != null && filtro.getListaMarcas() != null && !filtro.getListaMarcas().isEmpty()) {
			criteria.add(Restrictions.in("marca.idPtMarca", filtro.getListaMarcas()));
		}
		if (filtro != null && filtro.getListaSubMarcas() != null && !filtro.getListaSubMarcas().isEmpty()) {
			criteria.add(Restrictions.in("submarca.idPtSubmarca", filtro.getListaSubMarcas()));
		}
		if (filtro != null && filtro.getListaPerfiles() != null && !filtro.getListaPerfiles().isEmpty()) {
			criteria.add(Restrictions.in("perfil.idPtPerfiles", filtro.getListaPerfiles()));
		}		
		
		if (filtro != null && filtro.getListaIncidencias() != null && !filtro.getListaIncidencias().isEmpty()) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UltimaValidacionDTO.class, "ultima");	
			detachedCriteria.createAlias("ultima.incidenciasCollection", "incidencias");
			detachedCriteria.createAlias("incidencias.idIncidencia", "incidencia");			
			detachedCriteria.add(Restrictions.in("incidencia.idIncidencia", filtro.getListaIncidencias()));
			detachedCriteria.add(Property.forName("ultima.idRegistroCsv.idRegistroCsv").eqProperty("detalleEva.idRegistroCsv"));
			criteria.add(Subqueries.exists(detachedCriteria.setProjection(Projections.property("ultima.idRegistroCsv.idRegistroCsv"))));
		}
		
		
		criteria.setResultTransformer(Transformers.aliasToBean(InfoBasicaExpedienteVO.class));

		return criteria;
	}
	
	@Override
	public Long contarResultadosDeLaBusqueda(FiltroExpedienteVO filtro) {
		Criteria criteria = obtenerCriteriaBusqueda(filtro);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public void marcarExpediente(Long idRegistroCsv, Long idUsuarioModifica) {	
		Query q = getCurrentSession().createQuery("update ArchivoDetalleEvaDTO set stPreSeleccion = true, fhModificacion = sysdate, idUsrModifica = :idUsuarioModifica where idRegistroCsv = :idRegistroCsv");
		q.setLong("idUsuarioModifica", idUsuarioModifica);
		q.setLong("idRegistroCsv", idRegistroCsv);		
		q.executeUpdate();		
	}

	@Override
	public List<InfoBasicaExpValidadoVO> getExpedientesValidados(FiltroExpedienteVO filtro, Short nivel) {
		Criteria criteria = obtenerCriteriaBusqueda(filtro);	
		// Nivel 1 = agrupar resultados por PT
		if (nivel.shortValue() == BigDecimal.ONE.shortValue()) {
			criteria.setProjection(
					Projections.projectionList().add(Projections.groupProperty("entrega.idEntrega").as("idEntrega"))
							.add(Projections.groupProperty("entrega.nbEntrega").as("nbEntrega"))
							.add(Projections.groupProperty("lote.idPtLote").as("idPtLote"))
							.add(Projections.groupProperty("lote.nbPtLote").as("nbPtLote"))
							.add(Projections.groupProperty("lote.nbEstado").as("nbEstado"))
							.add(Projections.countDistinct("csv.idArchivoCsv").as("totalCsvs"))
							.add(Projections.count("detalleEva.idRegistroCsv").as("totalRegistros"))
							.add(Projections.sqlProjection(
									"count(distinct case when this_.ST_PRE_SELECCION =1 then 1 else NULL end) AS totalMarcados",
									new String[] { "totalMarcados" },
									new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }),"totalMarcados")
							.add(Projections.sqlProjection(
									"count(distinct case when csv1_.ST_VALIDACION =1 then csv1_.ID_ARCHIVO_CSV else NULL end) AS totalCsvsValidados",
									new String[] { "totalCsvsValidados" },
									new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }),"totalCsvsValidados")
							.add(Projections.sqlProjection(
									"count(distinct case when csv1_.ST_VALIDACION <> 1 then csv1_.ID_ARCHIVO_CSV else NULL end) AS totalCsvsFaltantes",
									new String[] { "totalCsvsFaltantes" },
									new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }),"totalCsvsFaltantes"));
			criteria.setResultTransformer(Transformers.aliasToBean(InfoBasicaExpValidadoVO.class));

			List<InfoBasicaExpValidadoVO> listaArchivosCsv = criteria.list();
			return listaArchivosCsv;
		}
		// Nivel 2 = agrupar resultados por CSV
		criteria.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("entrega.idEntrega").as("idEntrega"))
				.add(Projections.groupProperty("entrega.nbEntrega").as("nbEntrega"))
				.add(Projections.groupProperty("lote.idPtLote").as("idPtLote"))
				.add(Projections.groupProperty("lote.nbPtLote").as("nbPtLote"))
				.add(Projections.groupProperty("csv.idArchivoCsv").as("idArchivoCsv"))
				.add(Projections.groupProperty("csv.nbArchivoCsv").as("nbArchivoCsv"))
				.add(Projections.groupProperty("csv.nbEstado").as("nbEstado"))
				.add(Projections.groupProperty("csv.nuRegistrosCsv").as("totalRegistros"))
				.add(Projections.countDistinct("detalleEva.idRegistroCsv").as("totalCoincidencias"))
				.add(Projections.sqlProjection(
						"count(case when this_.ST_PRE_SELECCION =1 then 1 else NULL end) AS totalMarcados",
						new String[] { "totalMarcados" },
						new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }),"totalMarcados")
				.add(Projections.sqlProjection(
						"count(case when this_.ST_INCONSISTENCIA =1 then 1 else NULL end) AS totalInconsistentes",
						new String[] { "totalInconsistentes" },
						new org.hibernate.type.LongType[] { new org.hibernate.type.LongType() }),"totalInconsistentes"));
		criteria.setResultTransformer(Transformers.aliasToBean(InfoBasicaExpValidadoVO.class));

		List<InfoBasicaExpValidadoVO> listaArchivosCsv = criteria.list();
		return listaArchivosCsv;
	}
	
	@Override
	public List<ItemAsignacionVO> getDetalleNoAsignado(Long idPtLote, Long idPtCsv, FiltroExpedienteVO filtro) {
		Criteria criteria = obtenerCriteriaBusqueda(filtro);	
		
		
		// solo contemplamos el detalle del pt o csv seleccionado
		if (idPtLote != null) {	
			criteria.add(Restrictions.eq("lote.idPtLote", idPtLote));				
		} 
		if(idPtCsv != null) {			
			criteria.add(Restrictions.eq("csv.idArchivoCsv", idPtCsv));
		}
	
		
		// agregamos restricción de que el csv no esté reasignado en el ciclo vigente
		criteria.createAlias("csv.reasignsCollection", "reasignsCvs", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("reasignsCvs.idCicloValidacion", "cicloCsv", JoinType.LEFT_OUTER_JOIN,
				Restrictions.eq("cicloCsv.stVigente", BigDecimal.ONE.longValue()));
		criteria.add(Restrictions.isNull("reasignsCvs.idMotivoCsv"));
		
		// agregamos restricción de que el expediente no esté reasignado en el ciclo vigente		
		criteria.createAlias("detalleEva.reasignsCollection", "reasignsExpediente", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("reasignsExpediente.idCicloValidacion", "ciclo", JoinType.LEFT_OUTER_JOIN,
				Restrictions.eq("ciclo.stVigente", BigDecimal.ONE.longValue()));
		criteria.add(Restrictions.isNull("reasignsExpediente.idDetalleReval"));
		

		// regresar detalle a agrupado por csv
		if (idPtLote != null) {
			criteria.setProjection(
					Projections.projectionList().add(
							Projections.groupProperty("csv.idArchivoCsv").as("idArchivoCsv"))
							.add(Projections.groupProperty("csv.nbArchivoCsv").as("nbArchivoCsv"))							
							.add(Projections.countDistinct("detalleEva.idRegistroCsv").as("totalRegistros")));			
		}
		else if(idPtCsv != null) {
			//agregamos la clasifcacion
			criteria.createAlias("detalleEva.idPTClasifExpe", "clasif");
			
			// regresar detalle por expediente
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("detalleEva.idRegistroCsv").as("idRegistroCsv"))                    
                    .add(Projections.property("csv.nbArchivoCsv").as("nbArchivoCsv"))
                    .add(Projections.property("detalleEva.nuCarril").as("nuCarril"))                
                    .add(Projections.property("detalleEva.nuExpediente").as("nuExpediente"))
                    .add(Projections.property("detalleEva.cdPlacaDelantera").as("cdPlacaDelantera"))
                    .add(Projections.property("detalleEva.cdEntidadDelantera").as("cdEntidadDelantera"))
                    .add(Projections.property("detalleEva.cdPlacaTrasera").as("cdPlacaTrasera"))
                    .add(Projections.property("detalleEva.cdEntidadTrasera").as("cdEntidadTrasera"))
                    .add(Projections.property("perfil.txDescripcion").as("cdPerfil"))
                    .add(Projections.property("detalleEva.stInconsistencia").as("stInconsistencia"))
                    .add(Projections.property("clasif.cdClasifExpe").as("cdClasificacion"))
                    .add(Projections.sqlProjection("case when this_.ST_PRE_SELECCION = 1 then 't' else 'f' end  AS stPreseleccion",
                            new String[] { "stPreseleccion" },
                            new org.hibernate.type.TrueFalseType[] { new org.hibernate.type.TrueFalseType() }),
                            "stPreseleccion")
                    .add(Projections.property("ultimaValidacion.nuOrden").as("validaciones")));
		}else {
			// default regresar agrupado por PT
			criteria.setProjection(
					Projections.projectionList().add(
							Projections.groupProperty("csv.idArchivoCsv").as("idArchivoCsv"))
							.add(Projections.groupProperty("csv.nbArchivoCsv").as("nbArchivoCsv"))							
							.add(Projections.countDistinct("detalleEva.idRegistroCsv").as("totalRegistros")));	
		}
		criteria.setResultTransformer(Transformers.aliasToBean(ItemAsignacionVO.class));
		List<ItemAsignacionVO> listaArchivosCsv = criteria.list();
		return listaArchivosCsv;
		
	}
	
	@Override
	public void updateMarcadoMasivo(Long idPtLote, Long idUsuario) {
			
		Query q = getCurrentSession().createQuery(QUERY_MARCAR_MASIVAMENTE);
		q.setLong("idUsr", idUsuario);
		q.setLong("idPtLote", idPtLote);		
		q.executeUpdate();
				
	}
	
	@Override
	public void actualizarBanderasRevalidacion(FiltroExpedienteVO filtro, Long idUsuario) {
		Query query = getCurrentSession().createQuery(getHqlString(filtro));		
		Map<String, Object> parametros = obtenerParametrosBusqHql(filtro, idUsuario);		
		for (String parametro : query.getNamedParameters()) {
			  query.setParameter(parametro, parametros.get(parametro));				
		}		
		query.executeUpdate();
		
	}
	
	/**
	 * Se crea el query con las restricciones agregadas de acuerdo a 
	 * los valores encontrados en el filtro
	 * @param filtro
	 * @return
	 */
	private String getHqlString(FiltroExpedienteVO filtro) {
		StringBuilder hql =  new StringBuilder("");
				
			if (filtro != null && filtro.getNuExpediente() != null && StringUtils.isNotBlank(filtro.getNuExpediente())) {				
				hql.append("detalleEva.nuExpediente = :nuExpediente ");
			}
			if (filtro != null && filtro.getCdPlaca() != null && StringUtils.isNotBlank(filtro.getCdPlaca())) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append(" ( upper(detalleEva.cdPlacaDelantera) = :cdPlacaDelantera OR upper(detalleEva.cdPlacaTrasera) = :cdPlacaTrasera )");			
			}

			if (filtro != null && filtro.getListaEntregas() != null && !filtro.getListaEntregas().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("entrega.idEntrega in (:listaEntregas) ");
			}
			if (filtro != null && filtro.getListaLotes() != null && !filtro.getListaLotes().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("lote.idPtLote in (:listaLotes) ");
			}
			if (filtro != null && filtro.getListaCsvs() != null && !filtro.getListaCsvs().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("csv.idArchivoCsv in (:listaCsvs) ");
			}
			if (filtro != null && filtro.getListaMarcas() != null && !filtro.getListaMarcas().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("marca.idPtMarca in (:listaMarcas) ");
			}
			if (filtro != null && filtro.getListaSubMarcas() != null && !filtro.getListaSubMarcas().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("submarca.idPtSubmarca in (:listaSubmarca) ");
			}
			if (filtro != null && filtro.getListaPerfiles() != null && !filtro.getListaPerfiles().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("perfil.idPtPerfiles in (:listaPerfiles) ");
			}		
			
			if (filtro != null && filtro.getListaIncidencias() != null && !filtro.getListaIncidencias().isEmpty()) {
				if(hql.length() > BigDecimal.ZERO.intValue()) {
					hql.append(" AND ");
				}
				hql.append("incidencia.idIncidencia in (:listaIncidencias) ");				
			}	
			hql.append(" AND tb1.idRegistroCsv = detalleEva.idRegistroCsv ");
			hql.append(")");
			return new StringBuilder(QUERY_REINICIAR_BANDERAS_REVALIDACION).append(hql.toString()).toString();
		
	}
	
	
	/**
	 * Crea el mapeo de valores encontrados en el filtro y que serán asignados en la busqueda
	 * @param filtro
	 * @return
	 */
	private Map<String, Object> obtenerParametrosBusqHql(FiltroExpedienteVO filtro, Long idUsuario){
		Map<String, Object> parametros = new HashMap();
		parametros.put("idUsr", idUsuario);
		
		if (filtro != null && filtro.getNuExpediente() != null && StringUtils.isNotBlank(filtro.getNuExpediente())) {
			parametros.put("nuExpediente", filtro.getNuExpediente());
		}
		if (filtro != null && filtro.getCdPlaca() != null && StringUtils.isNotBlank(filtro.getCdPlaca())) {
			parametros.put("cdPlacaDelantera", filtro.getCdPlaca().toUpperCase());
			parametros.put("cdPlacaTrasera", filtro.getCdPlaca().toUpperCase());						
		}

		if (filtro != null && filtro.getListaEntregas() != null && !filtro.getListaEntregas().isEmpty()) {
			parametros.put("listaEntregas", filtro.getListaEntregas());
			
		}
		if (filtro != null && filtro.getListaLotes() != null && !filtro.getListaLotes().isEmpty()) {			
			parametros.put("listaLotes", filtro.getListaLotes());
		}
		if (filtro != null && filtro.getListaCsvs() != null && !filtro.getListaCsvs().isEmpty()) {
			parametros.put("listaCsvs", filtro.getListaCsvs());			
		}
		if (filtro != null && filtro.getListaMarcas() != null && !filtro.getListaMarcas().isEmpty()) {			
			parametros.put("listaMarcas", filtro.getListaMarcas());
		}
		if (filtro != null && filtro.getListaSubMarcas() != null && !filtro.getListaSubMarcas().isEmpty()) {			
			parametros.put("listaSubmarca", filtro.getListaSubMarcas());
		}
		if (filtro != null && filtro.getListaPerfiles() != null && !filtro.getListaPerfiles().isEmpty()) {			
			parametros.put("listaPerfiles", filtro.getListaPerfiles());
		}		
		
		if (filtro != null && filtro.getListaIncidencias() != null && !filtro.getListaIncidencias().isEmpty()) {			
			parametros.put("listaIncidencias", filtro.getListaIncidencias());		
		}	
		return parametros;
	}
	
	public List<registrosInconsistenciaVO> getExpedientesInconsistentes(Long idArchivoSCV) {

        Long total = getRegistrosInconsistentesAsignacion(idArchivoSCV);
        Long totalValidados = getRegistrosInconsistentesValidadosAsignacion(idArchivoSCV);
        Long porValidar = total - totalValidados;
        String host = obtenerHost();   
                      
        StringBuilder sbQuery = new StringBuilder("");                         
        sbQuery.append("select ")
        .append("    archivocsv1_.id_pt_lote as IdPtLote, ")
        .append("    archivocsv1_.id_archivo_csv as idArchivoCsv, ")          
        .append("    this_.id_registro_csv as idRegistroCsv, ")
        .append("    this_.nodo_vpn as nodoVpn, ")
        .append("    this_.nu_carril as nuCarril, ")
        .append("    this_.cd_placa_delantera as cdPlacaDelantera, ")
        .append("    this_.cd_entidad_delantera as cdEntidadDelantera, ")
        .append("    this_.nb_imagen_placa_delantera as nbImagenPlacaDelantera, ")
        .append("    this_.cd_placa_trasera as cdPlacaTrasera, ")
        .append("    this_.cd_entidad_trasera as cdEntidadTrasera, ")
        .append("    this_.nb_imagen_placa_trasera as nbImagenPlacaTrasera, ")
        .append("    this_.nb_imagen_conductor as nbImagenConductor, ")
        .append("    this_.nb_imagen_ambiental as nbImagenAmbiental, ")       
        .append("    this_.cd_perfil as cdPerfil, ")
        .append("    this_.nb_imagen_perfil as nbImagenPerfil, ")
        .append("    0 as Revalidacion, ")
        .append("    archivocsv1_.nb_archivo_csv as nbArchivoCsv, ")
        .append("    archivocsv1_.tx_carpeta_sil as txCarpetaSil, ")
        .append("    archivocsv1_.tx_carpeta_img as txCarpetaImg, ")
        .append("    '"+host+"' as txRutaStorage, ")
        .append("    "+total+" as totalRegistros, ")       
        .append("    "+porValidar+" as regValidados, ")
        .append("    "+porValidar+" as regValidados, ")
        .append("    marcas.id_pt_marca as idMarca, ")
        .append("    perfiles.id_pt_perfiles as idPerfil, ")
        .append("    submarcas.id_pt_submarca as idSubMarca, ")
        .append("    clasif.id_pt_clasif_expe as idClasificExpediente")
        .append(" from ")
        .append("    tci005d_pt_archivo_detalle_eva this_  ")
        .append("inner join ")
        .append("    tci003d_pt_archivo_csv archivocsv1_  ")
        .append("        on this_.id_archivo_csv=archivocsv1_.id_archivo_csv  ")
        .append("left outer join ")
        .append("    tci002d_pt_lote lotedto4_  ")
        .append("        on archivocsv1_.id_pt_lote=lotedto4_.id_pt_lote  ")
        .append("left outer join ")
        .append("    tci001d_pt_entrega entregadto5_  ")
        .append("        on lotedto4_.id_entrega=entregadto5_.id_entrega  ")
        .append("left outer join ")
        .append("    TCI017C_PT_MARCAS marcas ")
        .append("        on this_.id_marca = marcas.id_pt_marca ")
        .append("left outer join ")
        .append("    TCI018C_PT_SUBMARCA submarcas ")
        .append("        on this_.ID_SUBMARCA = submarcas.id_pt_submarca ")
        .append("left outer join ")
        .append("    TCI013C_PT_PERFILES perfiles ")
        .append("        on this_.ID_PERFIL = perfiles.id_pt_perfiles ")
        .append("left outer join ")
        .append("    TCI012C_PT_CLASIF_EXPEDIENTE clasif ")
        .append("        on this_.ID_PT_CLASIF_EXPE = clasif.ID_PT_CLASIF_EXPE ")          
        .append(" where ")
        .append("    archivocsv1_.id_archivo_csv=")
        .append(idArchivoSCV)
        .append("    and this_.st_validacion=1 ")
        .append("    and this_.st_revalidacion=1 ");

        String queryString = sbQuery.toString();           

        List<registrosInconsistenciaVO> lista = getCurrentSession().createSQLQuery(queryString)
       .addScalar("IdPtLote",LongType.INSTANCE)
        .addScalar("idArchivoCsv",LongType.INSTANCE)
        .addScalar("idRegistroCsv",LongType.INSTANCE)
        .addScalar("nodoVpn",LongType.INSTANCE)
        .addScalar("nuCarril",LongType.INSTANCE)
        .addScalar("cdPlacaDelantera",StringType.INSTANCE)
        .addScalar("cdEntidadDelantera",StringType.INSTANCE)
        .addScalar("nbImagenPlacaDelantera",StringType.INSTANCE)
        .addScalar("cdPlacaTrasera",StringType.INSTANCE)
        .addScalar("cdEntidadTrasera",StringType.INSTANCE)
        .addScalar("nbImagenPlacaTrasera",StringType.INSTANCE)
        .addScalar("nbImagenConductor",StringType.INSTANCE)
        .addScalar("nbImagenAmbiental",StringType.INSTANCE)
        .addScalar("cdPerfil",StringType.INSTANCE)
        .addScalar("nbImagenPerfil",StringType.INSTANCE)
        .addScalar("Revalidacion",LongType.INSTANCE)
        .addScalar("nbArchivoCsv",StringType.INSTANCE)
        .addScalar("txCarpetaSil",StringType.INSTANCE)
        .addScalar("txCarpetaImg",StringType.INSTANCE)
        .addScalar("txRutaStorage",StringType.INSTANCE)
        .addScalar("totalRegistros",LongType.INSTANCE)
        .addScalar("regValidados",LongType.INSTANCE)
        .addScalar("idMarca",LongType.INSTANCE)
        .addScalar("idPerfil",LongType.INSTANCE)
        .addScalar("idSubMarca",LongType.INSTANCE)
        .addScalar("idClasificExpediente",LongType.INSTANCE)
        .setResultTransformer(Transformers.aliasToBean(registrosInconsistenciaVO.class)).list();

        return lista;
  }
}

