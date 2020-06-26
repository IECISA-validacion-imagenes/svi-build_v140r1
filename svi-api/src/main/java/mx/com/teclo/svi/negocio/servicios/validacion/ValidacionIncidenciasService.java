package mx.com.teclo.svi.negocio.servicios.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svi.persistencia.hibernate.dao.CatalogoIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleIncidenciaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.CatIncidenciaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleIncidenciaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleValidadorDTO;

@Service
public class ValidacionIncidenciasService {
	@Autowired 
	private CatalogoIncidenciasDAO catIncidenciaDAO;
	
	@Autowired 
	private DetalleIncidenciaDAO detalleIncidenciaDAO;
	
	@Autowired 
	private DetalleValidadorDAO detalleValidadorDAO;
	
	/* Expedientes tipos de clasificacion*/
	private static final int GRUPO1 = 1;
	private static final int GRUPO2 = 2;
	private static final int GRUPO3 = 3;
	private static final int GRUPO4 = 4;
	private static final int GRUPO5 = 5;
	
	
	/* Metodo para Crear tipo de incidencias */
	@Transactional
	public void createIncidenciaDTO (ArchivoDetalleEvaDTO idRegistroCsv, Long idReasignaVal, Long idCatIncidencias){
		DetalleIncidenciaDTO detalleDTO = new DetalleIncidenciaDTO();
		CatIncidenciaDTO TipoIncidencia = catIncidenciaDAO.findOne(idCatIncidencias);	
		DetalleValidadorDTO detalleValidadorDTO = detalleValidadorDAO.findOne(idReasignaVal);
		detalleDTO.setIdIncidencia(TipoIncidencia);
		detalleDTO.setIdRegistroCsv(idRegistroCsv);
		detalleDTO.setFhCreacion(idRegistroCsv.getFhModificacion());
		detalleDTO.setFhModificacion(idRegistroCsv.getFhModificacion());
		detalleDTO.setIdUsrCreacion(idRegistroCsv.getIdUsrModifica());
		detalleDTO.setIdUsrModifica(idRegistroCsv.getIdUsrModifica());
		detalleDTO.setStActivo(Boolean.TRUE.booleanValue());
		detalleDTO.setIdReasignaVal(detalleValidadorDTO);
		detalleIncidenciaDAO.save(detalleDTO);
	}
	
	public Boolean validaClasificacionExpedientes(ArchivoDetalleEvaDTO elementoDTO) {
		Boolean placaDelantera = elementoDTO.getStValPlacaDelantera();
		Boolean placaTrasera = elementoDTO.getStValPlacaTrasera();
		Boolean entidadDelantera = elementoDTO.getStValEntidadDelantera();
		Boolean entidadTrasera = elementoDTO.getStValEntidadTrasera();
		Boolean perfil = elementoDTO.getStValPerfil();

		Boolean pochimovil = elementoDTO.getStPochomovil();
		Boolean imagenMal = elementoDTO.getStValImagenMal();
		Boolean clasifCorrecta = Boolean.FALSE.booleanValue();
		
		Integer regla =elementoDTO.getIdPtClasifExpe().getIdPtClasifExpe().intValue();
		Long idMarca = elementoDTO.getIdMarca().getIdPtMarca();
		Long idSubMarca = elementoDTO.getIdSubMarca().getIdPtSubmarca();
		String cdPerfil = elementoDTO.getCdPerfil().toUpperCase();
		
		switch(regla) {
		// 1.- Expediente completo placas entidades perfil marca y modelo
		case GRUPO1:
			if((placaDelantera == true && placaTrasera == true && entidadDelantera == true && entidadTrasera == true 	&& perfil == true 
			&& (pochimovil == false && !cdPerfil.equals("MOTOCICLETA"))) || (placaDelantera == false && placaTrasera == true && entidadDelantera == false
			&& entidadTrasera == true && perfil == true && (pochimovil == true || cdPerfil.equals("MOTOCICLETA")))){
				clasifCorrecta = true;
				}
			break;
			
		case GRUPO2:
			// 2 - Expediente con entidad y placa pero sin identificación de perfil del vehículo
			if ((placaDelantera == true && placaTrasera == true && entidadDelantera == true && entidadTrasera == true
					&& perfil == false && pochimovil == false && !cdPerfil.equals("MOTOCICLETA"))
					|| (placaDelantera == false && placaTrasera == true && entidadDelantera == false
							&& entidadTrasera == true && perfil == false
							&& (pochimovil == true || cdPerfil.equals("MOTOCICLETA")))) {
				clasifCorrecta = true;
			} 
			break;
			
		case GRUPO3:
			// 3 - Expediente con perfil, pero sin identificación de placa y entidad
			if ((placaDelantera == false && placaTrasera == false && entidadDelantera == false && entidadTrasera == false
					&& perfil == true && pochimovil == false && !cdPerfil.equals("MOTOCICLETA"))
					|| (placaDelantera == false && placaTrasera == false && entidadDelantera == false
							&& entidadTrasera == false && perfil == true
							&& (pochimovil == true || cdPerfil.equals("MOTOCICLETA")))) {
				clasifCorrecta = true;
			} 
			break;
			
		case GRUPO4:
			// 4 - Identificación de placa, entidad y perfil pero sin identificación de marca y modelo
			if (((placaDelantera == true && placaTrasera == true && entidadDelantera == true && entidadTrasera == true
					&& perfil == true && pochimovil == false && !cdPerfil.equals("MOTOCICLETA"))
					|| (placaDelantera == false && placaTrasera == true && entidadDelantera == false
							&& entidadTrasera == true && perfil == true
							&& (pochimovil == true || cdPerfil.equals("MOTOCICLETA"))))
					&& ((idMarca == 52 && (idSubMarca == 435 || idSubMarca == 436))
							|| (idMarca == 53 && (idSubMarca >= 438 && idSubMarca <= 450)))) {
				clasifCorrecta =  true;
			} 
			break;
			
		case GRUPO5:
			// 5 - No se puede clasificar el expediente (Placa, entidad y perfil de vehículo)
			if (placaDelantera == false && placaTrasera == false && entidadDelantera == false && entidadTrasera == false
					&& perfil == false && imagenMal == true) {
				clasifCorrecta = true;
			}
			break;
		}
		return clasifCorrecta;
	}
}
