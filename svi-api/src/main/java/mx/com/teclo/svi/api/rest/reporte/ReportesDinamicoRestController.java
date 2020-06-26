package mx.com.teclo.svi.api.rest.reporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.servicios.reporte.ReporteDinamicoIService;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteDinamicoVO;


@RestController	
public class ReportesDinamicoRestController {
	
		@Autowired
		private UsuarioFirmadoService usuarioFirmadoService;
		
		@Autowired
		private ReporteDinamicoIService rptDinamicoService;
		
		@RequestMapping(value = "/listaReportes", method = RequestMethod.GET)
		//@PreAuthorize("hasAnyAuthority('SERVICIO_GENERICO')")
		public ResponseEntity<ReporteDinamicoVO> GetListaReportes()throws NotFoundException, Throwable{
			Long idPerfil = usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilId();
			
			ReporteDinamicoVO reporte = rptDinamicoService.obtenerListaReportes(idPerfil); 		
			if (reporte == null) {
				throw new NotFoundException("No se encontraron registros");
			}
			return new  ResponseEntity<ReporteDinamicoVO>(reporte, HttpStatus.OK);
		}	
		
	 


}
