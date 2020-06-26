package mx.com.teclo.svi.negocio.servicios.evaresportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqReporteRV;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.negocio.vo.evareportes.EvaReportesVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteRVConsultaVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svi.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;

@Service
public class EvaReportesServiceImpl implements EvaReportesService{
	
	@Autowired
	ArchivoDetalleEvaDAO archivoDetalleEvaDAO;
	
	@Autowired
	EntregaDAO entregaDAO;
	
	@Autowired
	ArchivoCsvDAO archivoCsvDAO;
	
	@Autowired
	LoteDAO loteDAO;

//	@Override
//	@Transactional
//	public List<ReporteRVConsultaVO> consultaResultadoValidacion(String valor, String periodo, Long idTipoBusq){
//		return archivoDetalleEvaDAO.consultarReporteResultadoValidacion(valor, periodo, idTipoBusq);
//	}
	@Override
	@Transactional
	public List<ReporteRVConsultaVO> consultaReportesPts(String fhInicial, String fhFinal, Long idEntregable){
		return archivoDetalleEvaDAO.consultarReporteResultadoValidacion(fhInicial, fhFinal, idEntregable);
	}
	
//	@Override
//	@Transactional
//	public Map<String, Object>catalogoReporteValidacion(){
//		Map<String, Object> res = new HashMap<String, Object>();
//		List<CatalogoVO> listaCat = EnumTipoBusqReporteRV.getCatalog();
//		List<CatalogoVO> listaPeriodo = entregaDAO.catalogoPeriodos();
//		
//		res.put("catTipoBusq", listaCat);
//		res.put("catPeriodo", listaPeriodo);
//		return res;
//	}
	
	@Override
	@Transactional
	public List<CatalogoVO> catalogoReporteValidacion(){
		List<CatalogoVO> listaEntregas = entregaDAO.catalogoPeriodos();
		
		CatalogoVO catEntregaAll = new CatalogoVO();
		catEntregaAll.setIdCat(-10L);
		catEntregaAll.setNameCat("Todos");
		
		List<CatalogoVO> listaEntregasFinal = new ArrayList<CatalogoVO>();
		listaEntregasFinal.add(catEntregaAll);
		for(int i=0;i<listaEntregas.size();i++) {
			listaEntregasFinal.add(listaEntregas.get(i));
		}
		
		return listaEntregasFinal;
	}
	
	@Override
	@Transactional
	public Map generarResultadoValidacion(Long idPt) throws BusinessException{
		
		List<ArchivoCsvDTO>lista = new ArrayList<ArchivoCsvDTO>();
		lista = archivoCsvDAO.generarArchivosParaExcel(idPt);
		Map<String, Object> listaRes = new HashMap<String, Object>();
		Map<String, byte[]> listaArchivosCSV = new HashMap<String, byte[]>();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		
		if(archivoDetalleEvaDAO.validarSiPermitirDescargar(idPt)){
			String nombreZIP = lista.get(0).getIdPtLote().getIdEntrega().getNbEntrega()+"_"+lista.get(0).getIdPtLote().getNbPtLote()+".zip";
			ZipOutputStream zos = new ZipOutputStream(baos);
			
			List<ArchivoDetalleEvaDTO> listaRegistros = new ArrayList<ArchivoDetalleEvaDTO>();
			
			for(int i=0;i<lista.size();i++) {
				listaRegistros.addAll(archivoDetalleEvaDAO.getRegistrosValidados(lista.get(i).getIdArchivoCsv()));
				
			}
			String[] nombre = lista.get(0).getNbArchivoCsv().split("\\.");
			 
			try {
				listaArchivosCSV = new HashMap<String, byte[]>();
//				
				listaArchivosCSV.put("CSV_FINAL_"+lista.get(0).getIdPtLote().getNbPtLote()+"_EVA.csv", generarEVA(listaRegistros));
				listaArchivosCSV.put("CSV_FINAL_"+lista.get(0).getIdPtLote().getNbPtLote()+"_FINAL.csv", generarFINAL(listaRegistros));
				listaArchivosCSV.put("CSV_FINAL_"+lista.get(0).getIdPtLote().getNbPtLote()+"_RES.csv", generarRES(listaRegistros));
				
				for(Map.Entry<String, byte[]> file: listaArchivosCSV.entrySet()){
			    	ZipEntry entry = new ZipEntry(file.getKey());
				   	entry.setSize(file.getValue().length);
				   	zos.putNextEntry(entry);
					zos.write(file.getValue());
			   	}
				zos.closeEntry();
			    zos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			listaRes.put("nombre", nombreZIP);
			listaRes.put("archivo", baos);
		}else{
			throw new BusinessException("No se puede descargar el archivo, tiene archivos csv pendientes");
		}
	    
	    return listaRes;
	}
	
	private byte[] generarEVA(List<ArchivoDetalleEvaDTO>lista){
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		
		//Titulos de cada columna
		String[] titulos = new String[]{"numeroExpediente","carril","placaDelantera","entidadDelantera","placaTrasera","entidadTrasera","marca","modelo","perfil","validaPlacaDelantera","validaEntidadDelantera","validaPlacaTrasera","validaEntidadTrasera","validaPerfil","validaDoblePlaca","validaPersonalizada","validaPochimovil","validaImagenMal","nodo_VPN","fechaGeneracion","urlImagenPlacaDelantera","urlImagenPlacaTrasera","urlImagenConductor","urlImagenAmbiental","urlPerfil"};
		
		try {
			for(int i = 0;i<titulos.length;i++){
				
				reporte.write(titulos[i].getBytes());
				if(i+1 != titulos.length){
					reporte.write(",".getBytes());
				}
			}
			reporte.write("\n".getBytes());
			for(int i = 0; i <lista.size();i++){
				
				//numeroExpediente
				reporte.write(lista.get(i).getNuExpediente() != null ? lista.get(i).getNuExpediente().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//carril
				reporte.write(lista.get(i).getNuCarril() != null ? lista.get(i).getNuCarril().toString().getBytes() : "".getBytes());
				reporte.write(",".getBytes());

				
				//placaDelantera
				reporte.write(lista.get(i).getCdPlacaDelantera() != null ? lista.get(i).getCdPlacaDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//entidadDelantera
				reporte.write(lista.get(i).getCdEntidadDelantera() != null ? lista.get(i).getCdEntidadDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//placaTrasera
				reporte.write(lista.get(i).getCdPlacaTrasera() != null ? lista.get(i).getCdPlacaTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//entidadTrasera
				reporte.write(lista.get(i).getCdEntidadTrasera() != null ? lista.get(i).getCdEntidadTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				
				
				//marca
				reporte.write(lista.get(i).getIdMarca() !=null? lista.get(i).getIdMarca().getTxtMarca() != null ? lista.get(i).getIdMarca().getTxtMarca().getBytes() : "".getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//modelo
				reporte.write(lista.get(i).getIdSubMarca()!=null ? lista.get(i).getIdSubMarca().getTxtMarca() != null ? lista.get(i).getIdSubMarca().getTxtMarca().getBytes() : "".getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//perfil
				reporte.write(lista.get(i).getIdPerfil() !=null ? lista.get(i).getIdPerfil().getTxDescripcion() != null ? lista.get(i).getIdPerfil().getTxDescripcion().getBytes() : "".getBytes() : "".getBytes() );
				reporte.write(",".getBytes());
				
				//validaPlacaDelantera
				reporte.write(lista.get(i).getStValPlacaDelantera() ==true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());
				//validaEntidadDelantera
				reporte.write(lista.get(i).getStValEntidadDelantera() == true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());
				//validaPlacaTrasera
				reporte.write(lista.get(i).getStValPlacaTrasera() == true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());
				//validaEntidadTrasera
				reporte.write(lista.get(i).getStValEntidadTrasera()== true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());
				
				//validaPerfil
				reporte.write(lista.get(i).getStValPerfil() == true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());

				//validaDoblePlaca
				reporte.write(lista.get(i).getStDoblePlaca()!=null ? lista.get(i).getStDoblePlaca()?"True".getBytes():"False".getBytes() : "False".getBytes());
				reporte.write(",".getBytes());
				
				//validaPersonalizada	
				Boolean isPochimovil=lista.get(i).getStPochomovil();
				Boolean EsPersonalizada = esPlacaPersonalizada(lista.get(i).getCdPerfil(),lista.get(i).getStPlacaOfiDelantera(), lista.get(i).getStPlacaOfiTrasera(), isPochimovil);
				reporte.write(EsPersonalizada==true? "True".getBytes() : "False".getBytes());
				reporte.write(",".getBytes());
				//validaPochimovil
				reporte.write(lista.get(i).getStPochomovil()!=null? lista.get(i).getStPochomovil()?"True".getBytes():"False".getBytes() : "False".getBytes());
				reporte.write(",".getBytes());
				
				//ValidaImagenMal
				reporte.write(lista.get(i).getStValImagenMal()==true?"True".getBytes():"False".getBytes());
				reporte.write(",".getBytes());
				
				//nodo_VPN
				reporte.write(lista.get(i).getNodoVpn() != null ? lista.get(i).getNodoVpn().toString().getBytes() : "".getBytes());
				reporte.write(",".getBytes());	
				
//				//fechaGeneracion
				reporte.write(lista.get(i).getFhGeneracion() != null ? lista.get(i).getFhGeneracion().getBytes() : "".getBytes());
				reporte.write(",".getBytes());	
				
				
				//urlImagenPlacaDelantera
				reporte.write(lista.get(i).getNbImagenPlacaDelantera() != null ? lista.get(i).getNbImagenPlacaDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenPlacaTrasera
				reporte.write(lista.get(i).getNbImagenPlacaTrasera() != null ? lista.get(i).getNbImagenPlacaTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenConductor
				reporte.write(lista.get(i).getNbImagenConductor() != null ? lista.get(i).getNbImagenConductor().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenAmbiental
				reporte.write(lista.get(i).getNbImagenAmbiental() != null ? lista.get(i).getNbImagenAmbiental().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlPerfil
				reporte.write(lista.get(i).getNbImagenPerfil() != null ? lista.get(i).getNbImagenPerfil().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				
				
				
				
				
				if(i+1 != lista.size()){
					reporte.write("\n".getBytes());
				}
			}
			reporte.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return reporte.toByteArray();
	}
	
	public Boolean isValImagenMal(Long idClasifExped) {
		Boolean isMal=false;
		
		
		return isMal;
	}
	
	
//	public Boolean esPerfilValido(String valor) {
//		List<String> perfilesInvalidos = new ArrayList<String>();
//		perfilesInvalidos.add("BICICLETA");
//		perfilesInvalidos.add("PERSONAS");
//		perfilesInvalidos.add("OTROS");
//		perfilesInvalidos.add("OTROSOBJ");
//		
//		boolean esValido=true;
//		if(valor!=null) {
//			if(perfilesInvalidos.contains(valor)) {
//				esValido=false;
//			}
//		}else {
//			esValido=false;
//		}
//		return esValido;
//	}
	
	public Boolean esPlacaPersonalizada(String cdPerfil, Boolean getStPlacaOfiDelantera, 
			Boolean getStPlacaOfiTrasera, Boolean isPochimovil) {
		Boolean EsPersonalizada=false;
		if(cdPerfil!= null) {
			if(cdPerfil.equals("MOTOCICLETA")||isPochimovil) {
				if(getStPlacaOfiTrasera!=null) {
					if(getStPlacaOfiTrasera==false){
						EsPersonalizada=true;
					}
				}
			}else {
				if(getStPlacaOfiTrasera!=null&&getStPlacaOfiDelantera!=null) {
					if(!(getStPlacaOfiTrasera&&getStPlacaOfiDelantera)){
						EsPersonalizada=true;
					}
				}					
			}
		}else {EsPersonalizada=false;}
		return EsPersonalizada;
	}
	
	
	public EvaReportesVO asignaDatosPorcarril(ArchivoDetalleEvaDTO registro, EvaReportesVO report) {
		int totPlacCorr=report.getTotalPlacaCorrecta();
		int totPerf=report.getTotalPerfil();
		int totExcepcion=report.getTotalExcepcion();
		
		if(registro.getStValPlacaDelantera()!=null&&registro.getStValEntidadDelantera()!=null) {
			if(registro.getStValPlacaDelantera()&&registro.getStValEntidadDelantera()) {
				totPlacCorr=totPlacCorr+1;
			}
		}
		if(registro.getStValPerfil()) {
			totPerf=totPerf+1;
		}
		
		Boolean isPochimovil=registro.getStPochomovil();
		
		if(esPlacaPersonalizada(registro.getCdPerfil(), registro.getStPlacaOfiDelantera(), registro.getStPlacaOfiTrasera(), isPochimovil)) {
			totExcepcion=totExcepcion+1;
		}
		
		int totreg=report.getTotalRegistros()+1;
		
		double totEfecti=(totPlacCorr/totreg);
		
		report.setCarril(registro.getNuCarril());
		report.setTotalRegistros(totreg);	
		report.setTotalPlacaCorrecta(totPlacCorr);
		
		report.setTotalExcepcion(totExcepcion);
		report.setTotalPerfil(totPerf);
		report.setTotalEfectividad(String.valueOf(totEfecti));
		return report;
	}
	
	private byte[] generarRES(List<ArchivoDetalleEvaDTO>lista){
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		
		//Titulos de cada columna
		String[] titulos = new String[]{"carril", "totalRegistros", "totalPlacaCorrecta", "totalExcepcion", "totalPerfil", "totalEfectividad"};
		Long contTotalExep = 0L;
		Long contTotalPerf = 0L;
		
		
		try {
			
			for(int i = 0;i<titulos.length;i++){
				reporte.write(titulos[i].getBytes());
				if(i+1 != titulos.length){
					reporte.write(",".getBytes());
				}
			}
			
			List<EvaReportesVO> listReport = new ArrayList<EvaReportesVO>();

			//Generamosla lista de carriles 
			for(int i = 0; i <lista.size();i++){
				EvaReportesVO report = new EvaReportesVO();	
				Boolean updated=false;
				
				for(int j = 0; j <listReport.size();j++){
					if(listReport.get(j).getCarril().equals(lista.get(i).getNuCarril())) {				
						report=asignaDatosPorcarril(lista.get(i), listReport.get(j));
						listReport.set(j, report);
						updated=true;
					}				
				}
				
				if(!updated) {
					report=asignaDatosPorcarril(lista.get(i), report);
					listReport.add(report);			
				}
			}
			
			
			reporte.write("\n".getBytes());
			if (listReport != null) {
//				for(int i = 0; i <lista.size();i++){
//					contTotalExep = lista.get(i).getIdPtSilueta().getIdPtSilueta() == 1L ? contTotalExep + 1L : contTotalExep;
//					contTotalPerf = lista.get(i).getIdPtSilueta().getIdPtSilueta() == 3L ? contTotalPerf + 1L : contTotalPerf; 
//				}
				
				for(int i = 0; i <listReport.size();i++){
					reporte.write(Long.toString(listReport.get(i).getCarril()).getBytes());
					reporte.write(",".getBytes());
					reporte.write(String.valueOf(Integer.toString(listReport.get(i).getTotalRegistros())).getBytes());
					reporte.write(",".getBytes());
					reporte.write(String.valueOf(Integer.toString(listReport.get(i).getTotalPlacaCorrecta())).getBytes());
					reporte.write(",".getBytes());
					reporte.write(String.valueOf(Integer.toString(listReport.get(i).getTotalExcepcion())).getBytes());
					reporte.write(",".getBytes());
					reporte.write(String.valueOf(Integer.toString(listReport.get(i).getTotalPerfil())).getBytes());
					reporte.write(",".getBytes());
					reporte.write(String.valueOf(listReport.get(i).getTotalEfectividad()).getBytes());
					reporte.write("\n".getBytes());
				}
			}
			reporte.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return reporte.toByteArray();
	}
	
	private byte[] generarFINAL(List<ArchivoDetalleEvaDTO>lista){
ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		
		//Titulos de cada columna
		String[] titulos = new String[]{"nodo_VPN","numeroExpediente","fechaGeneracion","carril","placaDelantera","entidadDelantera","placaTrasera","entidadTrasera","urlImagenPlacaDelantera","urlImagenPlacaTrasera","urlImagenConductor","urlImagenAmbiental","urlPerfil"};
										
		try {
			for(int i = 0;i<titulos.length;i++){
				
				reporte.write(titulos[i].getBytes());
				if(i+1 != titulos.length){
					reporte.write(",".getBytes());
				}
			}
			reporte.write("\n".getBytes());
			for(int i = 0; i <lista.size();i++){
				//nodo_VPN
				reporte.write(lista.get(i).getNodoVpn() != null ? lista.get(i).getNodoVpn().toString().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//numeroExpediente
				reporte.write(lista.get(i).getNuExpediente() != null ? lista.get(i).getNuExpediente().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//fechaGeneracion
				reporte.write(lista.get(i).getFhGeneracion() != null ? lista.get(i).getFhGeneracion().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//carril
				reporte.write(lista.get(i).getNuCarril() != null ? lista.get(i).getNuCarril().toString().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//placaDelantera
				reporte.write(lista.get(i).getCdPlacaDelantera() != null ? lista.get(i).getCdPlacaDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//entidadDelantera
				reporte.write(lista.get(i).getCdEntidadDelantera() != null ? lista.get(i).getCdEntidadDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//placaTrasera
				reporte.write(lista.get(i).getCdPlacaTrasera() != null ? lista.get(i).getCdPlacaTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//entidadTrasera
				reporte.write(lista.get(i).getCdEntidadTrasera() != null ? lista.get(i).getCdEntidadTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				
				//urlImagenPlacaDelantera
				reporte.write(lista.get(i).getNbImagenPlacaDelantera() != null ? lista.get(i).getNbImagenPlacaDelantera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenPlacaTrasera
				reporte.write(lista.get(i).getNbImagenPlacaTrasera() != null ? lista.get(i).getNbImagenPlacaTrasera().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenConductor
				reporte.write(lista.get(i).getNbImagenConductor() != null ? lista.get(i).getNbImagenConductor().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//urlImagenAmbiental
				reporte.write(lista.get(i).getNbImagenAmbiental() != null ? lista.get(i).getNbImagenAmbiental().getBytes() : "".getBytes());
				reporte.write(",".getBytes());
				//
//				reporte.write(lista.get(i).getCdPerfil() != null ? lista.get(i).getCdPerfil().getBytes() : "".getBytes());
//				reporte.write(",".getBytes());
				//urlPerfil
				reporte.write(lista.get(i).getNbImagenPerfil() != null ? lista.get(i).getNbImagenPerfil().getBytes() : "".getBytes());
				
				if(i+1 != lista.size()){
					reporte.write("\n".getBytes());
				}
			}
			reporte.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return reporte.toByteArray();
	}
}
