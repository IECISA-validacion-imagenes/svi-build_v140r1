package mx.com.teclo.svideskwsw.rest.carga;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.DetalleLoteVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.InformacionEnviarVO;
import mx.com.teclo.svideskwsw.persistencia.vo.carga.LotesyDetalleVO;
import mx.com.teclo.svideskwsw.service.carga.cargaInformacionService;


@RestController
@RequestMapping("/cargaInformacioncsvPT")
public class CargarInformacionRestController {
	
	@Autowired
	private cargaInformacionService cargaDetalleCSVService;
	
  
  /* Metodo para insertar informacion de los PT csv a base de datos */
  @RequestMapping(value = "/guardarInformacionPT", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<Boolean> guardarInformacionCSV(@RequestBody LotesyDetalleVO detalleCSV )throws NotFoundException{		
             //cargaDetalleCSVService.guardarDetalles(detalleCSV);  
             cargaDetalleCSVService.guardarDetallesPreviendoErroresConexion(detalleCSV);
         return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
  }
  
  
  /* Metodo para consultar detalle de lotes para regresar a java y hacer rutas de carga */
  @RequestMapping(value = "/obtenerInformacion", method = RequestMethod.GET, consumes = "application/json")
  public ResponseEntity<List<DetalleLoteVO>> obtenerDetalleArchivoCSV(@RequestParam(value="tipo")String tipo)throws NotFoundException{
	  List<DetalleLoteVO> detalleCSV = cargaDetalleCSVService.getDetalleLote(tipo);
  return new ResponseEntity<List<DetalleLoteVO>>(detalleCSV, HttpStatus.OK);
  }

}