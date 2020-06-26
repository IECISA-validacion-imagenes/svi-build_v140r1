package mx.com.teclo.svi.negocio.servicios.expediente;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import mx.com.teclo.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.svi.negocio.enumerable.EnumObjectMapper;
import mx.com.teclo.svi.negocio.utils.comun.Messages;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleFiltroVO;
import mx.com.teclo.svi.negocio.vo.expediente.EtiquetaExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.FiltroExpedienteVO;
import mx.com.teclo.svi.negocio.vo.expediente.ItemFiltroExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CatalogoIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtEtiquetasRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtFiltroDetalleEvaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtPerfilesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSubmarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtEtiquetasRevalDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtFiltroDetalleEvaDTO;

@Service
public class FiltroExpedienteServiceImpl<ItemExpedienteVO> implements FiltroExpedienteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroExpedienteService.class);
	
	@Autowired	    
	MessageSource messageSource;

	@Autowired
	private PtEtiquetasRevalDAO ptEtiquetasRevalDAO;

	@Autowired
	private PtFiltroDetalleEvaDAO ptFiltroDetalleEvaDAO;

	@Autowired
	private UsuarioFirmadoService contexto;
	
	@Autowired
	private Messages messages;
		
	@Autowired
	private EntregaDAO entregaDAO;
	
	@Autowired
	private LoteDAO loteDAO;
	
	@Autowired
	private ArchivoCsvDAO csvDAO;
	
	@Autowired
	private PtMarcasDAO marcaDAO;
	
	@Autowired
	private PtSubmarcasDAO subMarcaDAO;
	
	@Autowired
	private PtPerfilesDAO perfilDAO;
	
	@Autowired
	private CatalogoIncidenciasDAO incidenciaDAO;

	@Override
	@Transactional
	public EtiquetaExpedienteVO registrarFiltro(EtiquetaExpedienteVO etiquetaExpedienteVO)
			throws JsonProcessingException, BusinessException {
		if (etiquetaExpedienteVO == null || etiquetaExpedienteVO.getFiltro() == null || StringUtils.isBlank(etiquetaExpedienteVO.getNbEtiqueta())) {
			throw new BusinessException("Etiqueta inválida");
		}		

		if (!isUnicoFiltroActivo(etiquetaExpedienteVO.getFiltro())) {
			EtiquetaExpedienteVO etiquetaDuplicada = ptEtiquetasRevalDAO.findByCdUnicidad(calcularCdUnicidad(etiquetaExpedienteVO.getFiltro()));
			etiquetaDuplicada.setIsDuplicada(Boolean.TRUE.booleanValue());
			return etiquetaDuplicada;
		}
		
		if (!isUnicaEtiqueta(etiquetaExpedienteVO.getNbEtiqueta())) {
			throw new BusinessException("Ya existe una etiqueta con ese mismo nombre.");
		}
		return crearFiltro(crearEtiqueta(etiquetaExpedienteVO), etiquetaExpedienteVO.getFiltro());

	}

	@Transactional
	@Override
	public Long crearEtiqueta(EtiquetaExpedienteVO etiquetaExpedienteVO) throws JsonProcessingException {
		PtEtiquetasRevalDTO ptEtiquetasRevalDTO = new PtEtiquetasRevalDTO();
		Long idUsuario = contexto.getUsuarioFirmadoVO().getId();
		ptEtiquetasRevalDTO.setNbEtiqueta(etiquetaExpedienteVO.getNbEtiqueta());
		ptEtiquetasRevalDTO.setFhCreacion(new Date());
		ptEtiquetasRevalDTO.setIdUsrCreacion(idUsuario);
		ptEtiquetasRevalDTO.setFhModificacion(new Date());
		ptEtiquetasRevalDTO.setIdUsrModifica(idUsuario);
		ptEtiquetasRevalDTO.setStActivo(Boolean.TRUE.booleanValue());

		Long cdUnicidad = (long) etiquetaExpedienteVO.getFiltro().toString().hashCode();
		ptEtiquetasRevalDTO.setCdUnicidad(cdUnicidad);
		ptEtiquetasRevalDAO.save(ptEtiquetasRevalDTO);
		return ptEtiquetasRevalDTO.getIdEtiqReval();
	}

	@Override
	@Transactional
	public EtiquetaExpedienteVO crearFiltro(Long idEtiqReval, FiltroExpedienteVO filtroExpedienteVO)
			throws JsonProcessingException, BusinessException {

		PtEtiquetasRevalDTO ptEtiquetasRevalDTO = ptEtiquetasRevalDAO.findOne(idEtiqReval);
		filtroExpedienteVO.setIdFiltro(null);

		PtFiltroDetalleEvaDTO ptFiltroDetalleEvaDTO = new PtFiltroDetalleEvaDTO();
		ptFiltroDetalleEvaDTO.setIdEtiqReval(ptEtiquetasRevalDTO);
		ptFiltroDetalleEvaDTO
				.setNbFiltro(EnumObjectMapper.INSTANCE.getObjectMapper().writeValueAsString(filtroExpedienteVO));
		ptFiltroDetalleEvaDAO.save(ptFiltroDetalleEvaDTO);
		EtiquetaExpedienteVO etiquetaExpedienteVO = new EtiquetaExpedienteVO();
		etiquetaExpedienteVO.setFiltro(filtroExpedienteVO);
		etiquetaExpedienteVO.setIdEtiqueta(idEtiqReval);
		etiquetaExpedienteVO.setNbEtiqueta(ptEtiquetasRevalDTO.getNbEtiqueta().toUpperCase());
		return etiquetaExpedienteVO;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public DetalleFiltroVO getFiltro(Long idFiltro) {
		DetalleFiltroVO detalleFiltroVO = ptEtiquetasRevalDAO.getDetalleFiltro(idFiltro);
		try {
			detalleFiltroVO.setDetalle((java.util.List<ItemFiltroExpedienteVO>) obtenerDetalleFiltro(detalleFiltroVO.getTxFiltro()));
		}catch (Exception e) {
			detalleFiltroVO.setDetalle(Collections.EMPTY_LIST);
		}
		
		return detalleFiltroVO;
	}

	/**
	 * Toma el hashcode del toString del filtro para buscarlo en la BD y as&iacute;
	 * saber si es &uacute;nico
	 * 
	 * @param filtroExpedienteVO
	 * @return
	 * @throws JsonProcessingException
	 */
	
	private boolean isUnicoFiltroActivo(FiltroExpedienteVO filtroExpedienteVO) throws JsonProcessingException {
		if (filtroExpedienteVO == null) {
			return Boolean.FALSE.booleanValue();
		}		
		return ptEtiquetasRevalDAO.countByCdUnicidad(calcularCdUnicidad(filtroExpedienteVO)) == BigDecimal.ZERO.longValue();

	}
	
	/**
	 * Ordena los valores de cada lista del filtro y obtiene el hash
	 * @param filtroExpedienteVO
	 * @return
	 */
	private Long calcularCdUnicidad(FiltroExpedienteVO filtroExpedienteVO) {
		filtroExpedienteVO.setIdFiltro(null);
		filtroExpedienteVO.setNbEtiqueta(null);
		ordenarItems(filtroExpedienteVO);
		return (long) filtroExpedienteVO.toString().hashCode();
	}
	
	private boolean isUnicaEtiqueta(String etiqueta){
		if (StringUtils.isBlank(etiqueta)) {
			return Boolean.FALSE.booleanValue();
		}		
		Long conteo = ptEtiquetasRevalDAO.countByNbEtiqueta(etiqueta);
		return conteo.longValue() == BigDecimal.ZERO.longValue();
	}

	/**
	 * Para que el orden en que fueron seleccionados los elementos en el filtro no sea
	 * factor de diferencia
	 * 
	 * @param filtroExpedienteVO
	 */
	private void ordenarItems(FiltroExpedienteVO filtroExpedienteVO) {
		if (!filtroExpedienteVO.getListaEntregas().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaEntregas(), Collections.reverseOrder());
		}
		if (!filtroExpedienteVO.getListaLotes().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaLotes(), Collections.reverseOrder());
		}

		if (!filtroExpedienteVO.getListaCsvs().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaCsvs(), Collections.reverseOrder());
		}

		if (!filtroExpedienteVO.getListaMarcas().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaMarcas(), Collections.reverseOrder());
		}

		if (!filtroExpedienteVO.getListaSubMarcas().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaSubMarcas(), Collections.reverseOrder());
		}

		if (!filtroExpedienteVO.getListaPerfiles().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaPerfiles(), Collections.reverseOrder());
		}
		
		if (!filtroExpedienteVO.getListaIncidencias().isEmpty()) {
			Collections.sort(filtroExpedienteVO.getListaIncidencias(), Collections.reverseOrder());
		}
	}
	
	
	private List<ItemFiltroExpedienteVO> obtenerDetalleFiltro(String filtro) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		FiltroExpedienteVO filtroExpedienteVO = EnumObjectMapper.INSTANCE.fromJson(filtro,	new TypeReference<FiltroExpedienteVO>() {});
		List<ItemFiltroExpedienteVO> detalle = new ArrayList<>();
	    
		BeanInfo beanInfo = Introspector.getBeanInfo(FiltroExpedienteVO.class);
		for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
			String propiedad = propertyDesc.getName();
			Object valor = propertyDesc.getReadMethod().invoke(filtroExpedienteVO);
			if (valor != null ) {
				ItemFiltroExpedienteVO resumen = getResumenSeleccion(propiedad, valor);
				if (resumen != null) {
					detalle.add(resumen);
				}
			}
		}
		return detalle;
	}
	
	@SuppressWarnings("unchecked")
	private ItemFiltroExpedienteVO getResumenSeleccion(String propiedad, Object params) {
		if (propiedad.equals("class") || propiedad.equals("tipoBusqueda")) {
			return null;
		}
		String nbAtributo = messages.get(String.format("filtro.expediente.etiqueta.%1$s", propiedad));
		 
		switch (propiedad) {
		case "idFiltro":
		case "nbEtiqueta":
			return null;		
		case "nuExpediente":
		case "cdPlaca":
			return new ItemFiltroExpedienteVO(nbAtributo, (String) params);
		case "listaEntregas":	
			return new ItemFiltroExpedienteVO(nbAtributo, entregaDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaLotes":
			return new ItemFiltroExpedienteVO(nbAtributo, loteDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaCsvs":
			return new ItemFiltroExpedienteVO(nbAtributo, csvDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaMarcas":
			return new ItemFiltroExpedienteVO(nbAtributo, marcaDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaSubMarcas":
			return new ItemFiltroExpedienteVO(nbAtributo, subMarcaDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaPerfiles":
			return new ItemFiltroExpedienteVO(nbAtributo, perfilDAO.getResumenFiltro((java.util.List<Long>) params));
		case "listaIncidencias":
			return new ItemFiltroExpedienteVO(nbAtributo, incidenciaDAO.getResumenFiltro((java.util.List<Long>) params));
		default:
			return null;
		}
	}
}
