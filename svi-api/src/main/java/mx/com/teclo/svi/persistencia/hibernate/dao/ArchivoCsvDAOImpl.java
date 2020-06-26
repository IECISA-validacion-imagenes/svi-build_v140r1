package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.vo.catalogo.CatCsvVO;
import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;
import mx.com.teclo.svi.negocio.vo.supervision.ConsultaDetalleIncidenciaPTVO;
import mx.com.teclo.svi.negocio.vo.supervision.DetalleAsignacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svi.persistencia.vo.asignacionarchivos.ArchivosCsvRespuestaVO;

@SuppressWarnings("unchecked")
@Repository("archivoCsvDAO")
public class ArchivoCsvDAOImpl extends BaseDaoHibernate<ArchivoCsvDTO>implements ArchivoCsvDAO {
	@Autowired
	private ParametrosComponente parametrosComponente;

	@Override
	public List<ArchivoCsvDTO> generarArchivosParaExcel(Long idPt){
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.createAlias("idPtLote","lote");
		c.add(Restrictions.eq("lote.idPtLote", idPt));
		c.add(Restrictions.eq("stValidacion", (short) 1));
		
		return (List<ArchivoCsvDTO>)c.list();
	}
	
	@Override
	public List<CatCsvVO> buscarCatTodosArchivos(){
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.add(Restrictions.eq("stActivo", (short) 1));
		List<ArchivoCsvDTO> listaCsv = c.list();
		List<CatCsvVO> catListaCsv = new ArrayList<CatCsvVO>();
		
		for(ArchivoCsvDTO aCsvDTO: listaCsv){
			CatCsvVO catCsvVO = new CatCsvVO();
			catCsvVO.setIdArchivoCsv(aCsvDTO.getIdArchivoCsv());
			catCsvVO.setNbPt(aCsvDTO.getIdPtLote().getNbPtLote());
			catCsvVO.setIdPt(aCsvDTO.getIdPtLote().getIdPtLote());
			catCsvVO.setNbNombre(aCsvDTO.getNbArchivoCsv());
			catCsvVO.setNuRegistrosCsv(aCsvDTO.getNuRegistrosCsv());
			catListaCsv.add(catCsvVO);
		}
		return catListaCsv;
	}
	
	@Override
	public List<CatCsvVO> buscarCatTodosArchivos(List<Long> listaCsvs){
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.add(Restrictions.eq("stActivo", (short) 1));
		c.add(Restrictions.eq("idArchivoCsv", listaCsvs));
		List<ArchivoCsvDTO> listaCsv = c.list();
		List<CatCsvVO> catListaCsv = new ArrayList<CatCsvVO>();
		
		for(ArchivoCsvDTO aCsvDTO: listaCsv){
			CatCsvVO catCsvVO = new CatCsvVO();
			catCsvVO.setIdArchivoCsv(aCsvDTO.getIdArchivoCsv());
			catCsvVO.setNbPt(aCsvDTO.getIdPtLote().getNbPtLote());
			catCsvVO.setIdPt(aCsvDTO.getIdPtLote().getIdPtLote());
			catCsvVO.setNbNombre(aCsvDTO.getNbArchivoCsv());
			catCsvVO.setNuRegistrosCsv(aCsvDTO.getNuRegistrosCsv());
			catListaCsv.add(catCsvVO);
		}
		return catListaCsv;
	}
	
	@Override
	public void actualizarArchivo(ArchivoCsvDTO archivo) {
		// TODO Auto-generated method stub
		getCurrentSession().update(archivo);
		getCurrentSession().flush();
		
	}
	
	@Override
	public ArchivoCsvDTO getArchivoCsvById(Long idArchivo) {
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.add(Restrictions.eq("idArchivoCsv", idArchivo));
		return (ArchivoCsvDTO) c.uniqueResult();
	}
	
	@Override
	@Transactional
	public Boolean updateArchivoCsvDTOByID(Long idArchivo, Long usuario) {
		StringBuilder qwery = new StringBuilder("UPDATE TCI003D_PT_ARCHIVO_CSV\r\n" + 
				"SET ST_REVALIDACION=2, FH_MODIFICACION=SYSDATE, ID_USR_MODIFICA= " +usuario+ 
				"WHERE ID_ARCHIVO_CSV="+ idArchivo);
		Query query2 = getCurrentSession().createSQLQuery(qwery.toString());
		query2.executeUpdate();
		return true;
	}	
	
	@Override
	@Transactional
	public Long getIDLoteByIdArchivo(Long idArchivo) {
		StringBuilder qwery = new StringBuilder("SELECT LOTE.ID_PT_LOTE AS ID_LOTE FROM TCI002D_PT_LOTE LOTE\r\n" + 
				"JOIN TCI003D_PT_ARCHIVO_CSV CSV ON CSV.ID_PT_LOTE = LOTE.ID_PT_LOTE\r\n" + 
				"WHERE CSV.ID_ARCHIVO_CSV= "+ idArchivo);
		Query query2 = getCurrentSession().createSQLQuery(qwery.toString()).addScalar("ID_LOTE",LongType.INSTANCE);
		Long idLote = (Long) query2.uniqueResult();
		return idLote;
	}

	@Override
	@Transactional
	public Long getLotesinRevalidacion(Long idPtLote) {
		StringBuilder qwery = new StringBuilder("SELECT COUNT(*) as TOTAL FROM TCI003D_PT_ARCHIVO_CSV " 
											  + " WHERE ST_ACTIVO = 1 AND ST_REVALIDACION >= 1 AND ID_PT_LOTE="+ idPtLote);
		Query query2 = getCurrentSession().createSQLQuery(qwery.toString()).addScalar("TOTAL",LongType.INSTANCE);
		Long totCSVxPT = (Long) query2.uniqueResult();
		return totCSVxPT;
	}


	@Override
	public Long getLotesconRevalidacion(Long idPtLote) {
		StringBuilder qwery = new StringBuilder("SELECT COUNT(*) as TOTAL FROM TCI003D_PT_ARCHIVO_CSV " 
				  + " WHERE ST_ACTIVO = 1 AND ST_REVALIDACION = 2 AND ID_PT_LOTE="+idPtLote);
		Query query2 = getCurrentSession().createSQLQuery(qwery.toString()).addScalar("TOTAL",LongType.INSTANCE);
		Long totCSVxPT = (Long) query2.uniqueResult();
		return totCSVxPT;
	}
	
	@Override
	public List<CsvExpedienteVO> getAllArchivosCsv() {
		Criteria criteria = getCurrentSession().createCriteria(ArchivoCsvDTO.class, "csv");
		criteria.createAlias("csv.idPtLote", "lote");
		criteria.createAlias("lote.idEntrega", "entrega");
		criteria.setProjection(
				Projections.projectionList().add(Projections.property("entrega.idEntrega").as("idEntrega"))
						.add(Projections.property("entrega.nbEntrega").as("nbEntrega"))
						.add(Projections.property("lote.idPtLote").as("idPtLote"))
						.add(Projections.property("lote.nbPtLote").as("nbPtLote"))
						.add(Projections.property("csv.idArchivoCsv").as("idArchivoCsv"))
						.add(Projections.property("csv.nbArchivoCsv").as("nbArchivoCsv")))	
				.setResultTransformer(Transformers.aliasToBean(CsvExpedienteVO.class));

		List<CsvExpedienteVO> listaArchivosCsv = criteria.list();
		return listaArchivosCsv;

	}
	
	@Override
	public List<ConsultaDetalleIncidenciaPTVO> consultarDetalleIncidenciasPT(Long idPt){
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.createAlias("idPtLote", "pt");
		c.add(Restrictions.eq("pt.idPtLote", idPt));
		c.add(Restrictions.eq("pt.stActivo", (short)1));
		
		List<ArchivoCsvDTO> list = (List<ArchivoCsvDTO>)c.list();
		List<ConsultaDetalleIncidenciaPTVO> res = new ArrayList<ConsultaDetalleIncidenciaPTVO>();
		
		for(ArchivoCsvDTO acsvDTO: list){
			ConsultaDetalleIncidenciaPTVO cdiPTVO  = ResponseConverter.copiarPropiedadesFull(acsvDTO, ConsultaDetalleIncidenciaPTVO.class);
			res.add(cdiPTVO);
		}
		return res;
	}
	
	@Override
	@Transactional
	public List<ArchivosCsvRespuestaVO> getTodosArchivosNoAsignados(List<Long> listaIdArchivosAsignados) {
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		if(listaIdArchivosAsignados.size()>0) {
			c.add(Restrictions.not(
					Restrictions.in("idArchivoCsv", listaIdArchivosAsignados)
				)
			);
		}
		
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		c.addOrder(Order.asc("idArchivoCsv"));

		List<ArchivoCsvDTO> listArchivosNoAsignados = (List<ArchivoCsvDTO>) c.list();
		
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
		
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();
		
		List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = new ArrayList<ArchivosCsvRespuestaVO>();
		ArchivosCsvRespuestaVO datos = null;
		
		for(ArchivoCsvDTO archivoCsv:listArchivosNoAsignados){
			datos = new ArchivosCsvRespuestaVO();
			
			datos.setIdArchivoCsv(archivoCsv.getIdArchivoCsv());
			datos.setIdPtLote(archivoCsv.getIdPtLote().getIdPtLote());
			datos.setNbArchivoCsv(archivoCsv.getNbArchivoCsv());
			datos.setNuRegistrosCsv(archivoCsv.getNuRegistrosCsv());
			datos.setNbCarpetaImg(archivoCsv.getNbCarpetaImg());
			datos.setTxCarpetaImg(archivoCsv.getTxCarpetaImg());
			datos.setNuImagenes(archivoCsv.getNuImagenes());
			datos.setNbCarpetaSil(archivoCsv.getNbCarpetaSil());
			datos.setTxCarpetaSil(archivoCsv.getTxCarpetaSil());
			datos.setNuSiluetas(archivoCsv.getNuSiluetas());
			datos.setStValidacion(archivoCsv.getStValidacion());
			datos.setStActivo(archivoCsv.getStActivo());
			datos.setTxRutaStorage(rutaHost);
			
			listaArchivosRespuesta.add(datos);	
		}
		
		return listaArchivosRespuesta;
	}
	
	
	@Override
	public List<ArchivosCsvRespuestaVO> getTodosArchivosSiAsignados(List<Long> listaIdArchivosAsignados) { 
		Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		c.add(Restrictions.in("idArchivoCsv", listaIdArchivosAsignados)
		);
		c.add(Restrictions.eq("stValidacion", (short) 0));
		c.add(Restrictions.eq("stActivo", (short) 1));
		c.addOrder(Order.asc("idArchivoCsv"));
		
		List<ArchivoCsvDTO> listArchivosNoAsignados = (List<ArchivoCsvDTO>) c.list();
		
		StringBuilder strQuery2 = new StringBuilder(" "
				+ "SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
		
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String rutaHost = (String) query2.uniqueResult();
		
		List<ArchivosCsvRespuestaVO> listaArchivosRespuesta = new ArrayList<ArchivosCsvRespuestaVO>();
		ArchivosCsvRespuestaVO datos = null;
		
		for(ArchivoCsvDTO archivoCsv:listArchivosNoAsignados){
			datos = new ArchivosCsvRespuestaVO();
			
			datos.setIdArchivoCsv(archivoCsv.getIdArchivoCsv());
			datos.setIdPtLote(archivoCsv.getIdPtLote().getIdPtLote());
			datos.setNbArchivoCsv(archivoCsv.getNbArchivoCsv());
			datos.setNuRegistrosCsv(archivoCsv.getNuRegistrosCsv());
			datos.setNbCarpetaImg(archivoCsv.getNbCarpetaImg());
			datos.setTxCarpetaImg(archivoCsv.getTxCarpetaImg());
			datos.setNuImagenes(archivoCsv.getNuImagenes());
			datos.setNbCarpetaSil(archivoCsv.getNbCarpetaSil());
			datos.setTxCarpetaSil(archivoCsv.getTxCarpetaSil());
			datos.setNuSiluetas(archivoCsv.getNuSiluetas());
			datos.setStValidacion(archivoCsv.getStValidacion());
			datos.setStActivo(archivoCsv.getStActivo());
			datos.setTxRutaStorage(rutaHost);
			
			listaArchivosRespuesta.add(datos);	
		}
		
		return listaArchivosRespuesta;
		
		
	}

	@Override
	public String getResumenFiltro(List<Long> idsCsvs) {
		if (idsCsvs.isEmpty()) {
			return null;
		}
		Criteria criteria = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		criteria.add(Restrictions.in("idArchivoCsv", idsCsvs));
		criteria.setProjection(Projections.projectionList()				
				.add(Projections.sqlProjection("Rtrim(Xmlagg (Xmlelement (e, upper(nbArchivoCsv)|| ', ')).extract  ( '//text()' ), ', ') as valor", new String[] { "valor" },
						new org.hibernate.type.StringType[] { new org.hibernate.type.StringType() }), "valor"));
		return (String) criteria.uniqueResult();
	}
	
	@Override
	@Transactional
	public DetalleAsignacionVO getDetalleAsignacion(Long idPtCsv) {
		Criteria criteria = getCurrentSession().createCriteria(ArchivoCsvDTO.class, "csv");
		criteria.add(Restrictions.eq("csv.idArchivoCsv", idPtCsv));
		criteria.setProjection(Projections.projectionList().add(Projections.property("csv.idArchivoCsv").as("idPtCsv"))
				.add(Projections.property("csv.nbArchivoCsv").as("nbCsv")));
		criteria.setResultTransformer(Transformers.aliasToBean(DetalleAsignacionVO.class));
		return (DetalleAsignacionVO) criteria.uniqueResult();
	}
}
