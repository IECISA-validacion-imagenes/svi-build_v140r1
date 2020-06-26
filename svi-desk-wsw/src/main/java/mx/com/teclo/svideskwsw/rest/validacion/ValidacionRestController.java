package mx.com.teclo.svideskwsw.rest.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.svideskwsw.persistencia.vo.validacion.LotesVO;
import mx.com.teclo.svideskwsw.service.validacion.ValidacionService;

@RestController
public class ValidacionRestController {

	@Autowired
	ValidacionService validacionService;
	
	@RequestMapping(value = "/validacion/informacionImagen", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> guardarInformacionImagen(@RequestBody LotesVO listaPTS) {
		Boolean res = validacionService.guardarLote(listaPTS);
		return new ResponseEntity<Boolean>(res ,HttpStatus.OK);
	}
}
