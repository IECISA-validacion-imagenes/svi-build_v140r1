package mx.com.teclo.svi.negocio.utils;

import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.svi.negocio.vo.catalogo.PtClasifExpedientesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtEntidadVO;
import mx.com.teclo.svi.negocio.vo.catalogo.PtPerfilesVO;
import mx.com.teclo.svi.negocio.vo.catalogo.catValidacionSiluetasVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtClasifExpedientesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEntidadDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtPerfilesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;

public class Utils {

	
	public static void llenadoDatosPtEntidadDTOtoPtEntidadVO(PtEntidadDTO origen,PtEntidadVO destino) {
		if(destino == null)
			destino = new PtEntidadVO();	
		destino.setIdPtCatalogoEntidad(origen.getIdPtCatalogoEntidad());
		destino.setCdEntidad(origen.getCdEntidad());
		destino.setTxDescEntidad(origen.getTxDescEntidad());
	}
	
	public static void llenadoDatosListaPtEntidadDTOtoListaPtEntidadVO(List<PtEntidadDTO> listaOrigen,
			List<PtEntidadVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(PtEntidadDTO origen : listaOrigen) {
			PtEntidadVO destino = new PtEntidadVO();
			llenadoDatosPtEntidadDTOtoPtEntidadVO(origen, destino);
			listaDestino.add(destino);
		}
			
	}
	

	public static void llenadoDatosPtClasifExpedientesDTOtoPtClasifExpedientesVO(PtClasifExpedientesDTO origen,PtClasifExpedientesVO destino) {
		if(destino == null)
			destino = new PtClasifExpedientesVO();
		
		destino.setIdPtClasifExpe(origen.getIdPtClasifExpe());
		destino.setCdClasifExpe(origen.getCdClasifExpe());
		destino.setTxClasifExpe(origen.getTxClasifExpe());
	}	

	public static void llenadoDatosListaPtClasifExpedientesDTOtoListaPtClasifExpedientesVO(List<PtClasifExpedientesDTO> listaOrigen,
			List<PtClasifExpedientesVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(PtClasifExpedientesDTO origen : listaOrigen) {
			PtClasifExpedientesVO destino = new PtClasifExpedientesVO();
			llenadoDatosPtClasifExpedientesDTOtoPtClasifExpedientesVO(origen, destino);
			listaDestino.add(destino);
		}
			
	}
	
	/*Catalogo de perfiles */
	public static void llenadoDatosListaDTOListaVO(PtPerfilesDTO origen,PtPerfilesVO destino) {
		if(destino == null)
			destino = new PtPerfilesVO();	
		destino.setIdPtPerfiles(origen.getIdPtPerfiles());
		destino.setCdIdentificacion(origen.getCdIdentificacion());		
		destino.setTxDescripcion(origen.getTxDescripcion());
	}
	
	public static void llenadoDatosListasPtPerfilesDTO(List<PtPerfilesDTO> listaOrigen,List<PtPerfilesVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(PtPerfilesDTO origen : listaOrigen) {
			PtPerfilesVO destino = new PtPerfilesVO();
			llenadoDatosListaDTOListaVO(origen, destino);
			listaDestino.add(destino);
		}
			
	}
 /* Catalogo valores de siluetas */
	
	public static void catValidacionSiluetasDTO(List<PtSiluetasDTO> listaOrigen,List<catValidacionSiluetasVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();	
		for(PtSiluetasDTO origen : listaOrigen) {
			catValidacionSiluetasVO destino = new catValidacionSiluetasVO();
			LlenarListaVO(origen, destino);
			listaDestino.add(destino);
		}
			
	}
	
	public static void LlenarListaVO(PtSiluetasDTO origen,catValidacionSiluetasVO destino) {
		if(destino == null)
			destino = new catValidacionSiluetasVO();
		destino.setIdCatValidacion(origen.getIdPtSilueta());
		destino.setCdValidacionSilueta(origen.getCdPtSilueta());
		destino.setTxtDescripcion(origen.getTxSilueta());
	}

}
