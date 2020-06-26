package mx.com.teclo.svi.negocio.servicios.configuraciones;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.vo.configuraciones.FormularioRevalidacionVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.configuraciones.ConfigCapturaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ConfigCapturaDTO;

@Service
public class ConfigCapturaServiceImpl implements ConfigCapturaService {

	@Autowired
	private ConfigCapturaDAO configCapturaDAO;

	@Autowired
	private UsuarioFirmadoService usuarioService;

	@Override
	@Transactional
	public void guardarConfigCaptura(FormularioRevalidacionVO configuracionVO) throws BusinessException {
		try {
			ConfigCapturaDTO configCapturaDTO = configCapturaDAO.findOne(BigDecimal.ONE.longValue());
			configCapturaDTO.setTxConfiguracion(
					EnumObjectMapper.INSTANCE.getObjectMapper().writeValueAsString(configuracionVO));
			configCapturaDTO.setIdUsrModifica(usuarioService.getUsuarioFirmadoVO().getId());
			configCapturaDTO.setFhModificacion(new Date());
			configCapturaDAO.saveOrUpdate(configCapturaDTO);
		} catch (JsonProcessingException e) {
			throw new BusinessException("Error al guardar la configuraci\u00F3n");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public FormularioRevalidacionVO obtenerConfigCaptura(Long id) {
		ConfigCapturaDTO configCapturaDTO = configCapturaDAO.findOne(id);
		FormularioRevalidacionVO formularioRevalidacionVO = EnumObjectMapper.INSTANCE
				.fromJson(configCapturaDTO.getTxConfiguracion(), new TypeReference<FormularioRevalidacionVO>() {
				});
		return formularioRevalidacionVO;

	}

}
