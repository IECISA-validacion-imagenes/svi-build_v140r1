/**
 * 
 */
package mx.com.teclo.svideskwsw.service.carga;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.ArchivoDetalleDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.DetalleLoteVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.LoteCargaVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.LoteVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.LotesyDetalleVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.infoConteosDatosCSVO;

/**
 * @author FernandoSanchez
 *
 */
@Service
public class cargaInformacionServiceImpl implements cargaInformacionService {
	
	@Autowired 
	private ArchivoDetalleDAO archivoDetalleDAO;
	
	@Autowired
	private ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired
	private LoteDAO loteDAO;
	
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;	
	
	@Autowired
	private EntregaDAO entregaDAO;
	

	@Override
	@Transactional
	public List<infoConteosDatosCSVO> obtenerDetallePT() {
		infoConteosDatosCSVO listaInformacion = new infoConteosDatosCSVO();
		List<infoConteosDatosCSVO> respuesta = new ArrayList<infoConteosDatosCSVO>(); 
		
		List<ArchivoCsvDTO> detalleLotes = archivoCsvDAO.obtenerArchivoPTSCV();	
		if(detalleLotes.size()>0){
			for(ArchivoCsvDTO elemento: detalleLotes){
				listaInformacion = ResponseConverter.copiarPropiedadesFull(elemento, infoConteosDatosCSVO.class);
				respuesta.add(listaInformacion);
			}
		}
		
		return respuesta;
	}

	@SuppressWarnings("unused")
	@Override
	public List<DetalleLoteVO> getDetalleLote(String tipo) {
		int con = 0;
		List<DetalleLoteVO> response  = new ArrayList<DetalleLoteVO>();
		DetalleLoteVO valorLote = new DetalleLoteVO();
		List<LoteDTO> lotesDTO = loteDAO.getLoteCargar(tipo);
		for(LoteDTO lote : lotesDTO){
			valorLote = ResponseConverter.copiarPropiedadesFull(lote, DetalleLoteVO.class);
			valorLote.setNbEntrega(lote.getIdEntrega().getNbEntrega());
			response.add(valorLote);
		}
		return response;
	}

	@SuppressWarnings("unused")
	@Override
	public void guardarDetalles(List<LotesyDetalleVO> informacion) {
		List<ArchivoDetalleDTO> listaCargaDTO = new ArrayList<ArchivoDetalleDTO>();
		int tamano = 0;
		int pox=0;

 		for(LotesyDetalleVO obj : informacion){
			ArchivoCsvDTO listaTCI003D = new ArchivoCsvDTO(); 
			tamano =  obj.getDetalleArchivosCSV().size();
			LoteDTO lote = loteDAO.actualizarLote(obj.getListaDetalleElemento().getIdPtLote());
			LoteVO loteCompleto = ResponseConverter.copiarPropiedadesFull(lote, LoteVO.class);
			listaTCI003D = devolverLoteDTO(loteCompleto ,obj.getListaDetalleElemento());
			archivoCsvDAO.guardarDTO(listaTCI003D);
			
			for(pox = 0; pox< tamano; pox++){
				ArchivoDetalleDTO cargaDTO = new ArchivoDetalleDTO();
		    	ArchivoDetalleEvaDTO cargaDetalleEVA = new ArchivoDetalleEvaDTO();
		    		cargaDTO.setIdArchivoCsv(listaTCI003D);
		    		cargaDTO.setNodoVpn(obj.getDetalleArchivosCSV().get(pox).getNodoVpn());
		    		cargaDTO.setNuExpediente(obj.getDetalleArchivosCSV().get(pox).getNuExpediente());
		    		cargaDTO.setFhGeneracion(obj.getDetalleArchivosCSV().get(pox).getFhGeneracion());
		    		cargaDTO.setNuCarril(obj.getDetalleArchivosCSV().get(pox).getNuCarril());
		    		cargaDTO.setCdPlacaDelantera(obj.getDetalleArchivosCSV().get(pox).getCdPlacaDelantera());
		    		cargaDTO.setCdEntidadDelantera(obj.getDetalleArchivosCSV().get(pox).getCdEntidadDelantera());
		    		cargaDTO.setCdPlacaTrasera(obj.getDetalleArchivosCSV().get(pox).getCdPlacaTrasera());
		    		cargaDTO.setCdEntidadTrasera(obj.getDetalleArchivosCSV().get(pox).getCdEntidadTrasera());
		    		cargaDTO.setNbImagenPlacaDelantera(obj.getDetalleArchivosCSV().get(pox).getNbImagenPlacaDelantera());
		    		cargaDTO.setNbImagenPlacaTrasera(obj.getDetalleArchivosCSV().get(pox).getNbImagenPlacaTrasera());
		    		cargaDTO.setNbImagenConductor(obj.getDetalleArchivosCSV().get(pox).getNbImagenConductor());
		    		cargaDTO.setNbImagenAmbiental(obj.getDetalleArchivosCSV().get(pox).getNbImagenAmbiental());
		    		cargaDTO.setCdPerfil(obj.getDetalleArchivosCSV().get(pox).getCdPerfil());
		    		cargaDTO.setNbImagenPerfil(obj.getDetalleArchivosCSV().get(pox).getNbImagenPerfil());
		    		cargaDTO.setStActivo(obj.getDetalleArchivosCSV().get(pox).getStActivo());
		    		cargaDTO.setFhCreacion(obj.getDetalleArchivosCSV().get(pox).getFhCreacion());
		    		cargaDTO.setIdUsrCreacion(obj.getDetalleArchivosCSV().get(pox).getIdUsrCreacion());
		    		archivoDetalleDAO.cargarDetalleCSVDTO(cargaDTO); 
		    		
		    		/* gUARDAR dETALLE eva */	
		    		cargaDetalleEVA = ResponseConverter.copiarPropiedadesFull(cargaDTO, ArchivoDetalleEvaDTO.class);
		    		cargaDetalleEVA.setFhModificacion(cargaDTO.getFhCreacion());
		    		cargaDetalleEVA.setSt2daValidacion((short) 0);
		    		cargaDetalleEVA.setStInconsistencia((short)0);
		    		archivoDetalleEvaDAO.guardarDetalleEva(cargaDetalleEVA);
		    		}
            }
		
		
		/* Metodo para actulizar LOTE */
		long lote = informacion.get(0).getListaDetalleElemento().getIdPtLote();
		long filasCSV = 0,totalFilasEntrega = 0;
		int lotes = informacion.size();
		int x= 0;
		long idEntrega = informacion.get(0).getListaDetalleElemento().getIdEntrega().getIdEntrega();
		
		for(x=0; x< lotes; x++){
			if(lote == informacion.get(x).getListaDetalleElemento().getIdPtLote() && 
					idEntrega == informacion.get(x).getListaDetalleElemento().getIdEntrega().getIdEntrega()){
				filasCSV += informacion.get(x).getDetalleArchivosCSV().size();	
				totalFilasEntrega += filasCSV;
			}else{				
				LoteDTO modificarLote = loteDAO.actualizarLote(lote);
				modificarLote.setNuTotalRegCsv(filasCSV);
				modificarLote.setNuTotalRegImg(informacion.get(x).getListaDetalleElemento().getNuImagenes());
				modificarLote.setFhModificacion(new Date());
				loteDAO.modificarEntraga(modificarLote);
				
				/*Editar entrega */
				EntregaDTO entregaDTO = new EntregaDTO();
				entregaDTO = entregaDAO.consultarEntregaDTO(informacion.get(x -1).getListaDetalleElemento().getIdEntrega().getIdEntrega()); 
				entregaDTO.setNuTotalRegCsv(totalFilasEntrega + entregaDTO.getNuTotalRegCsv());
				entregaDTO.setFhModificacion(new Date());
				entregaDAO.actualizar(entregaDTO);
				
				lote = informacion.get(x).getListaDetalleElemento().getIdPtLote();
				idEntrega = informacion.get(x).getListaDetalleElemento().getIdEntrega().getIdEntrega();
				x=x-1;			
				filasCSV = 0 ;
			}
		}
		
		if(x == lotes){
			LoteDTO modificarLote = loteDAO.actualizarLote(lote);
			modificarLote.setNuTotalRegCsv(filasCSV);
			modificarLote.setNuTotalRegImg(informacion.get(x-1).getListaDetalleElemento().getNuImagenes());
			modificarLote.setFhModificacion(new Date());
			loteDAO.modificarEntraga(modificarLote);
			
			/*Editar entrega */
			EntregaDTO entregaDTO = new EntregaDTO();
			entregaDTO = entregaDAO.consultarEntregaDTO(informacion.get(x -1).getListaDetalleElemento().getIdEntrega().getIdEntrega()); 
			entregaDTO.setNuTotalRegCsv(totalFilasEntrega + entregaDTO.getNuTotalRegCsv());
			entregaDTO.setNuTotalRegImg(entregaDTO.getNuTotalRegImg() + informacion.get(x -1).getListaDetalleElemento().getNuImagenes());
			entregaDTO.setFhModificacion(new Date());
			entregaDAO.actualizar(entregaDTO);
			
			filasCSV = 0 ;
		}
	  }
	
	public ArchivoCsvDTO devolverLoteDTO (LoteVO loteCompleto, infoConteosDatosCSVO cuerpo){
		LoteCargaVO loteDTO = new LoteCargaVO();

		loteDTO.setIdPtLote(loteCompleto);
		//loteDTO.setIdEntrega(cuerpo.getIdEntrega());
		loteDTO.setNbCarpetaImg(cuerpo.getNbCarpetaImg());
		loteDTO.setNbArchivoCsv(cuerpo.getNbArchivoCsv());
		loteDTO.setNuRegistrosCsv(cuerpo.getNuRegistrosCsv());
		loteDTO.setTxCarpetaImg(cuerpo.getTxCarpetaImg());
		loteDTO.setNuImagenes(cuerpo.getNuImagenes());
		loteDTO.setNbCarpetaSil(cuerpo.getNbCarpetaSil());
		loteDTO.setTxCarpetaSil(cuerpo.getTxCarpetaSil());
		loteDTO.setNuSiluetas(cuerpo.getNuSiluetas());
		loteDTO.setStValidacion(cuerpo.getStValidacion());
		loteDTO.setStActivo(cuerpo.getStActivo());
		loteDTO.setFhCreacion(cuerpo.getFhCreacion());
		loteDTO.setIdUsrCreacion(cuerpo.getIdUsrCreacion());
		loteDTO.setFhModificacion(cuerpo.getFhModificacion());
		loteDTO.setIdUsrModifica(cuerpo.getIdUsrModifica());
		
		ArchivoCsvDTO DTO = ResponseConverter.copiarPropiedadesFull(loteDTO, ArchivoCsvDTO.class);
		
		return DTO;
	}

	@Override
	public void guardarDetallesPreviendoErroresConexion(LotesyDetalleVO informacion) {
		//Variables a Usar 
		int ultimoRegistro=0, totalRegistrosDetalle = 0 ,totalRegistrosDetalleEva = 0;
		ArchivoCsvDTO listaTCI003D = new ArchivoCsvDTO(); 
		int tamano = 0, pox=0;
		Long temCSV=0L;
		Boolean insertRegistros= false ;
		
		//Comienzo del codigo de guarda
		Long IdPtLote = informacion.getListaDetalleElemento().getIdPtLote();
		//Long totalRegistrosxLote = informacion.getListaDetalleElemento().getTotalRegistroxLote();
		
		//Traer Datos del Lote 
		LoteDTO loteDTO = loteDAO.actualizarLote(IdPtLote);
		if(loteDTO.getNuTotalRegistrosxLote() == 0){
			loteDTO.setNuTotalRegistrosxLote(informacion.getListaDetalleElemento().getTotalRegistroxLote());
			loteDAO.modificarEntraga(loteDTO);
			
			EntregaDTO entregaDTO = new EntregaDTO();
			entregaDTO = entregaDAO.consultarEntregaDTO(informacion.getListaDetalleElemento().getIdEntrega().getIdEntrega()); 
			entregaDTO.setNuTotalRegCsv(informacion.getListaDetalleElemento().getNuRegistrosCsv());
			entregaDTO.setFhModificacion(new Date());
			entregaDAO.actualizar(entregaDTO);	
		}
		
		
		//Consultar total de registros guarde en Detalle 
		Long archivo = archivoCsvDAO.getIdCSVxIdPTyNomCSV(informacion.getListaDetalleElemento().getIdPtLote() , 
														  informacion.getListaDetalleElemento().getNbArchivoCsv());
		
		/* Actualizar proceso de cargar de CSV en LOTE */	
		if(archivo != null){
			    totalRegistrosDetalleEva = archivoDetalleEvaDAO.getUltimoRegistro(archivo);
			    totalRegistrosDetalle    = archivoDetalleDAO.getUltimoRegistro(archivo);
			    if(totalRegistrosDetalle > totalRegistrosDetalleEva){
			    	ultimoRegistro = totalRegistrosDetalleEva;
			    }else {
			    	ultimoRegistro = totalRegistrosDetalle;
			    }
			    listaTCI003D = archivoCsvDAO.BuscarDTOxId(archivo);			
			}else{ // no Existe el csv 
				LoteVO loteCompleto = ResponseConverter.copiarPropiedadesFull(loteDTO, LoteVO.class);
				listaTCI003D = devolverLoteDTO(loteCompleto ,informacion.getListaDetalleElemento());
				archivoCsvDAO.guardarDTO(listaTCI003D);		
			}
		
		
		/* Comprobar que TABLA tiene mayor cantidad de registros EVA o DETALLE */
		if(totalRegistrosDetalle > totalRegistrosDetalleEva){
			//el detalle es mas grande que el eva 
			for(int ajuste = totalRegistrosDetalleEva; ajuste < totalRegistrosDetalle; ajuste++){
				ArchivoDetalleDTO cargaDTO = new ArchivoDetalleDTO();
				ArchivoDetalleEvaDTO cargaDetalleEVA = new ArchivoDetalleEvaDTO();
				cargaDTO = archivoDetalleDAO.getDTObyNumExpediente(informacion.getDetalleArchivosCSV().get(ajuste).getNuExpediente(),
						listaTCI003D.getIdArchivoCsv());
//				cargaDTO.setIdArchivoCsv(listaTCI003D);
//				cargaDTO.setNodoVpn(informacion.getDetalleArchivosCSV().get(ajuste).getNodoVpn());
//				cargaDTO.setNuExpediente(informacion.getDetalleArchivosCSV().get(ajuste).getNuExpediente());
//				cargaDTO.setFhGeneracion(informacion.getDetalleArchivosCSV().get(ajuste).getFhGeneracion());
//				cargaDTO.setNuCarril(informacion.getDetalleArchivosCSV().get(ajuste).getNuCarril());
//				cargaDTO.setCdPlacaDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getCdPlacaDelantera());
//				cargaDTO.setCdEntidadDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getCdEntidadDelantera());
//				cargaDTO.setCdPlacaTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getCdPlacaTrasera());
//				cargaDTO.setCdEntidadTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getCdEntidadTrasera());
//				cargaDTO.setNbImagenPlacaDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPlacaDelantera());
//				cargaDTO.setNbImagenPlacaTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPlacaTrasera());
//				cargaDTO.setNbImagenConductor(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenConductor());
//				cargaDTO.setNbImagenAmbiental(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenAmbiental());
//				cargaDTO.setCdPerfil(informacion.getDetalleArchivosCSV().get(ajuste).getCdPerfil());
//				cargaDTO.setNbImagenPerfil(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPerfil());
//				cargaDTO.setStActivo(informacion.getDetalleArchivosCSV().get(ajuste).getStActivo());
//				cargaDTO.setFhCreacion(informacion.getDetalleArchivosCSV().get(ajuste).getFhCreacion());
//				cargaDTO.setIdUsrCreacion(informacion.getDetalleArchivosCSV().get(ajuste).getIdUsrCreacion());
//				archivoDetalleDAO.getDTObyNumExpediente(cargaDTO);
				
				/*  dETALLE eva */	
	    		cargaDetalleEVA = ResponseConverter.copiarPropiedadesFull(cargaDTO, ArchivoDetalleEvaDTO.class);
	    		cargaDetalleEVA.setFhModificacion(cargaDTO.getFhCreacion());
	    		cargaDetalleEVA.setIdUsrModifica(cargaDTO.getIdUsrCreacion());
	    		cargaDetalleEVA.setSt2daValidacion((short) 0);
	    		cargaDetalleEVA.setStInconsistencia((short)0);
	    		cargaDetalleEVA.setStValidacion((short)0);
	    		archivoDetalleEvaDAO.guardarDetalleEva(cargaDetalleEVA);
			}
			ultimoRegistro = totalRegistrosDetalle;
			
		}else if(totalRegistrosDetalle < totalRegistrosDetalleEva){
			// Eva tiene mas ergistros que detalle 
			for(int ajuste = totalRegistrosDetalle; ajuste < totalRegistrosDetalleEva; ajuste++){
				ArchivoDetalleDTO cargaDTO = new ArchivoDetalleDTO();
				//ArchivoDetalleEvaDTO cargaDetalleEVA = new ArchivoDetalleEvaDTO();
				cargaDTO.setIdArchivoCsv(listaTCI003D);
				cargaDTO.setNodoVpn(informacion.getDetalleArchivosCSV().get(ajuste).getNodoVpn());
				cargaDTO.setNuExpediente(informacion.getDetalleArchivosCSV().get(ajuste).getNuExpediente());
				cargaDTO.setFhGeneracion(informacion.getDetalleArchivosCSV().get(ajuste).getFhGeneracion());
				cargaDTO.setNuCarril(informacion.getDetalleArchivosCSV().get(ajuste).getNuCarril());
				cargaDTO.setCdPlacaDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getCdPlacaDelantera());
				cargaDTO.setCdEntidadDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getCdEntidadDelantera());
				cargaDTO.setCdPlacaTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getCdPlacaTrasera());
				cargaDTO.setCdEntidadTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getCdEntidadTrasera());
				cargaDTO.setNbImagenPlacaDelantera(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPlacaDelantera());
				cargaDTO.setNbImagenPlacaTrasera(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPlacaTrasera());
				cargaDTO.setNbImagenConductor(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenConductor());
				cargaDTO.setNbImagenAmbiental(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenAmbiental());
				cargaDTO.setCdPerfil(informacion.getDetalleArchivosCSV().get(ajuste).getCdPerfil());
				cargaDTO.setNbImagenPerfil(informacion.getDetalleArchivosCSV().get(ajuste).getNbImagenPerfil());
				cargaDTO.setStActivo(informacion.getDetalleArchivosCSV().get(ajuste).getStActivo());
				cargaDTO.setFhCreacion(informacion.getDetalleArchivosCSV().get(ajuste).getFhCreacion());
				cargaDTO.setIdUsrCreacion(informacion.getDetalleArchivosCSV().get(ajuste).getIdUsrCreacion());
				archivoDetalleDAO.cargarDetalleCSVDTO(cargaDTO);
			}
			ultimoRegistro = totalRegistrosDetalleEva;
		}// fin del if 
		
		
		//Cargar Datos desde donde me quede 
		 int largoArchivo = informacion.getDetalleArchivosCSV().size();			
			for(pox = ultimoRegistro; pox < largoArchivo; pox++){
				ArchivoDetalleDTO cargaDTO = new ArchivoDetalleDTO();
		    	ArchivoDetalleEvaDTO cargaDetalleEVA = new ArchivoDetalleEvaDTO();
		    		cargaDTO.setIdArchivoCsv(listaTCI003D);
		    		cargaDTO.setNodoVpn(informacion.getDetalleArchivosCSV().get(pox).getNodoVpn());
		    		cargaDTO.setNuExpediente(informacion.getDetalleArchivosCSV().get(pox).getNuExpediente());
		    		cargaDTO.setFhGeneracion(informacion.getDetalleArchivosCSV().get(pox).getFhGeneracion());
		    		cargaDTO.setNuCarril(informacion.getDetalleArchivosCSV().get(pox).getNuCarril());
		    		cargaDTO.setCdPlacaDelantera(informacion.getDetalleArchivosCSV().get(pox).getCdPlacaDelantera());
		    		cargaDTO.setCdEntidadDelantera(informacion.getDetalleArchivosCSV().get(pox).getCdEntidadDelantera());
		    		cargaDTO.setCdPlacaTrasera(informacion.getDetalleArchivosCSV().get(pox).getCdPlacaTrasera());
		    		cargaDTO.setCdEntidadTrasera(informacion.getDetalleArchivosCSV().get(pox).getCdEntidadTrasera());
		    		cargaDTO.setNbImagenPlacaDelantera(informacion.getDetalleArchivosCSV().get(pox).getNbImagenPlacaDelantera());
		    		cargaDTO.setNbImagenPlacaTrasera(informacion.getDetalleArchivosCSV().get(pox).getNbImagenPlacaTrasera());
		    		cargaDTO.setNbImagenConductor(informacion.getDetalleArchivosCSV().get(pox).getNbImagenConductor());
		    		cargaDTO.setNbImagenAmbiental(informacion.getDetalleArchivosCSV().get(pox).getNbImagenAmbiental());
		    		cargaDTO.setCdPerfil(informacion.getDetalleArchivosCSV().get(pox).getCdPerfil());
		    		cargaDTO.setNbImagenPerfil(informacion.getDetalleArchivosCSV().get(pox).getNbImagenPerfil());
		    		cargaDTO.setStActivo(informacion.getDetalleArchivosCSV().get(pox).getStActivo());
		    		cargaDTO.setFhCreacion(informacion.getDetalleArchivosCSV().get(pox).getFhCreacion());
		    		cargaDTO.setIdUsrCreacion(informacion.getDetalleArchivosCSV().get(pox).getIdUsrCreacion());
		    		
		    		archivoDetalleDAO.cargarDetalleCSVDTO(cargaDTO); 
		    		
		    		/*  dETALLE eva */	
		    		cargaDetalleEVA = ResponseConverter.copiarPropiedadesFull(cargaDTO, ArchivoDetalleEvaDTO.class);
		    		cargaDetalleEVA.setFhModificacion(cargaDTO.getFhCreacion());
		    		cargaDetalleEVA.setIdUsrModifica(cargaDTO.getIdUsrCreacion());
		    		cargaDetalleEVA.setSt2daValidacion((short) 0);
		    		cargaDetalleEVA.setStInconsistencia((short)0);
		    		cargaDetalleEVA.setStValidacion((short)0);
		    		archivoDetalleEvaDAO.guardarDetalleEva(cargaDetalleEVA);
		    		
		    		insertRegistros= true;
		    		
		    	}
								
			/* Metodo para actulizar LOTE */
			if(insertRegistros == true){
				loteDTO.setNuTotalRegCsv(loteDTO.getNuTotalRegCsv() + (long)pox);
				if(loteDTO.getNuTotalRegCsv().equals(informacion.getListaDetalleElemento().getTotalRegistroxLote())){
					loteDTO.setStatusCarga(1L);
				}
				loteDTO.setNuTotalRegImg(informacion.getListaDetalleElemento().getNuImagenes());
				loteDTO.setFhModificacion(new Date());
				loteDAO.modificarEntraga(loteDTO);
			}
			
			if(insertRegistros==false){//no carge nada en ese CSV S
				if(loteDTO.getNuTotalRegCsv().equals(informacion.getListaDetalleElemento().getTotalRegistroxLote())){
					loteDTO.setStatusCarga(1L);	
					loteDTO.setNuTotalRegImg(informacion.getListaDetalleElemento().getNuImagenes());
					loteDTO.setFhModificacion(new Date());
					loteDAO.modificarEntraga(loteDTO);
				}
			}				
	}//fin metdoo 


}
