package mx.com.teclo.svi.negocio.servicios.validacion;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svi.persistencia.hibernate.dao.ArchDetBitEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CicloValidacionDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.DetalleValidadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.MotivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ValidadoresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchDetBitEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleValidadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;

@Service
public class DetalleValidadorServiceImpl implements DetalleValidadorService {

	@Autowired
	private DetalleValidadorDAO detalleValidadorDAO;
	@Autowired
	private ArchivoDetalleEvaDAO archivoDetalleEvaDAO;
	@Autowired
	private ValidadoresDAO validadoresDAO;
	@Autowired
	private ArchDetBitEvaDAO archDetBitEvaDAO;
	@Autowired
	private MotivoCsvDAO motivoCsvDAO;
	@Autowired
	private CicloValidacionDAO cicloValidacionDAO;

	@Override
	@Transactional
	public Long saveDetalleValidador(Long idRegistroCsv) {
		ArchivoDetalleEvaDTO archivoDetalleEvaDTO = archivoDetalleEvaDAO.findOne(idRegistroCsv);
		ValidadoresDTO validadorDTO = validadoresDAO.getValidadorByIdUsuario(archivoDetalleEvaDTO.getIdUsrModifica());
		Long nuOrden = detalleValidadorDAO.countByIdRegistroCSV(idRegistroCsv);
		Long ciclo = motivoCsvDAO.getMaxCicloByIdArchivoCsv(archivoDetalleEvaDTO.getIdArchivoCsv().getIdArchivoCsv());

		DetalleValidadorDTO detalleValidadorDTO = new DetalleValidadorDTO();
		detalleValidadorDTO.setIdRegistroCSV(archivoDetalleEvaDTO);
		detalleValidadorDTO.setIdValidador(validadorDTO);
		detalleValidadorDTO.setStActivo(BigDecimal.ONE.longValue());
		detalleValidadorDTO.setFechaCreacion(archivoDetalleEvaDTO.getFhModificacion());
		detalleValidadorDTO.setIdUserCreacion(archivoDetalleEvaDTO.getIdUsrModifica());
		detalleValidadorDTO.setFechaModificacion(new Date());
		detalleValidadorDTO.setIdUserModifica(archivoDetalleEvaDTO.getIdUsrModifica());
		detalleValidadorDTO.setNuOrden(nuOrden + BigDecimal.ONE.intValue());
		detalleValidadorDTO.setIdCicloValidacion(cicloValidacionDAO.findOne(ciclo));
		detalleValidadorDAO.save(detalleValidadorDTO);
		replicarValidacionEnBitacora(archivoDetalleEvaDTO, detalleValidadorDTO);
		return detalleValidadorDTO.getIdReasignaVal();
	}
	
	private void replicarValidacionEnBitacora(final ArchivoDetalleEvaDTO archivoDetalleEvaDTO, DetalleValidadorDTO detalleValidadorDTO) {
		ArchDetBitEvaDTO archDetBitEvaDTO = new ArchDetBitEvaDTO(archivoDetalleEvaDTO,detalleValidadorDTO);
		archDetBitEvaDAO.save(archDetBitEvaDTO);		
	}

}
