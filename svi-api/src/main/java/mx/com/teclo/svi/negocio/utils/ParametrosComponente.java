package mx.com.teclo.svi.negocio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParametrosComponente {
	@Value("${app.config.parametros}")
	private final Long idPtParam;
	
	public Long getIdPtParam() {
		return idPtParam;
	}
	
	@Autowired
    public ParametrosComponente(@Value("${app.config.parametros}") Long idPtParam) {
        this.idPtParam = idPtParam;
    }
	
	
}
