package mx.com.teclo.svi.negocio.servicios.supervision;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.svi.negocio.utils.comun.Messages;
import mx.com.teclo.svi.persistencia.hibernate.dao.CicloValidacionDAO;

@Service
public class CicloValidacionServiceImpl implements CicloValidacionService {

	@Autowired
	private Messages messages;

	@Autowired
	private CicloValidacionDAO cicloDAO;

	@Override
	@Transactional(readOnly = true)
	public Boolean isValidaReasignacion(List<Long> arregloIdsCsv, Long idArchivoCsv) throws BusinessException {
		if (arregloIdsCsv == null && idArchivoCsv == null) {
			throw new BusinessException(messages.get("supervision.reasignacion.nula"));
		}
		if (!isCicloAnteriorCerrado()) {
			throw new BusinessException(messages.get("supervision.reasignacion.cicloAnteriorNoCerrado"));
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean isCicloAnteriorCerrado() {
		return cicloDAO.isCicloAnteriorCerrado();
	}

}
