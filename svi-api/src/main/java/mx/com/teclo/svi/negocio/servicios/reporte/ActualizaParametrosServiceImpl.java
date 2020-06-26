package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParametrosNewReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ReportesTaqNewReporteVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ComponenteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParamPropScriptDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosColumnDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosPropDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosTablasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoParametroDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParametroDTO;

@Service
public class ActualizaParametrosServiceImpl implements ActualizaParametrosService{

	@Autowired
	private TipoParametroDAO tipoParametroDAO;
	
	@Autowired
	private ComponenteDAO componenteDAO;
	
	@Autowired
	private UsuarioFirmadoService usrSession;
	
	@Autowired
	private ParametrosDAO parametrosDAO;
	
	@Autowired
	private ActualizaPropiedadesParamService actualizaPropiedadesParamService;
	
	@Autowired
	private ParametrosTablasDAO parametrosTablasDAO;
	
	@Autowired
	private GuadaColumnasParamService guadaColumnasParamService;
	
	@Autowired
	private ParametrosPropDAO parametrosPropDAO;
	
	@Autowired
	private ParamPropScriptDAO paramPropScriptDAO;
	
	@Autowired
	private ParametrosColumnDAO parametrosColumnDAO;
	
	@Autowired
	private ActualizaColumnasParamService actualizaColumnasParamService;
	
	
	@Transactional
	@Override
	public Boolean comprobarCambiosParametros(ReportesTaqNewReporteVO reporteVO,List<ParametrosNewReporteVO> parametrosVO,ReportesDTO reporteDTO) {
		
		List<ParametrosDTO> parametroBD = reporteDTO.getParametros();
		TipoParametroDTO tipoParametroDTO = null;
		ComponentesDTO componentesDTO = null;
		/*Lista temporal para identificar los parametros dependientes*/
		List<ParametrosTablasDTO> listaTMP = new ArrayList<>();
		ParametrosTablasDTO parametrosTablasDTO = null;
		TablasDTO tablasDTO = null;
		
		if(parametroBD.isEmpty() && (parametrosVO != null && !parametrosVO.isEmpty())){//guardar todos los parámetros
			guardarNuevoParametro(reporteVO, parametrosVO,reporteDTO);
		}else if(!parametroBD.isEmpty() && (parametrosVO != null && !parametrosVO.isEmpty())){//comprobar los cambios en los parametros
			
			Set<Long> listaDesBD = extraerColumn(parametroBD);
			Set<Long> listaDesPeticion = extraerColumnIds(parametrosVO);
			
			Set<Long> elementosABorrar = Sets.difference(listaDesBD, listaDesPeticion);
			//Set<Long> elementosNuevos = Sets.difference(listaDesPeticion, listaDesBD);
			Set<Long> interseccion = Sets.intersection(listaDesPeticion, listaDesBD);
			
			if(!elementosABorrar.isEmpty()){
				List<ParametrosDTO> toDelete = extraerColumnasBD(elementosABorrar, parametroBD);
				if(!toDelete.isEmpty()){
					//borrar todos los parámetros con sus dependientes
					for(ParametrosDTO parametroDTO : toDelete){
						eliminarParametro(parametroDTO);
					}
				}
			}
			//if(!elementosNuevos.isEmpty()){
			List<ParametrosNewReporteVO> toSave = nuevosParametros(parametrosVO);
			if (!toSave.isEmpty()) {
				guardarNuevoParametro(reporteVO, toSave, reporteDTO);
			}
			//}
			if (!interseccion.isEmpty()) {
				List<ParametrosDTO> toUpdate = extraerColumnasBD(interseccion, parametroBD);
				List<ParametrosNewReporteVO> voInterseccion = extraerColumnasPeticion(interseccion, parametrosVO);
				if (!toUpdate.isEmpty() && !voInterseccion.isEmpty()) {
					for (ParametrosNewReporteVO vo : voInterseccion) {
						for (ParametrosDTO update : toUpdate) {
							if(vo.getIdParamtro().equals(update.getIdParamtro())){
								tipoParametroDTO = new TipoParametroDTO();
								componentesDTO = new ComponentesDTO();
								update.setFhModificacion(new Date());
								update.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
							
								tipoParametroDTO = tipoParametroDAO.gettipoParametroById(vo.getTipoParametro().getIdTipoParametro());
								componentesDTO = componenteDAO.getComponentesById(vo.getComponente().getIdComponente());
								
								update.setTipoParametro(tipoParametroDTO);
								update.setComponente(componentesDTO);
								update.setCdParametro(vo.getCdParametro());
								update.setNbParametro(vo.getNbParametro());
								update.setTxParametro(vo.getTxParametro());
								update.setStIsCatalogo(vo.getStIsCatalogo());
								update.setTxValor(vo.getTxValor());
								update.setStMultipleValores(vo.getStMultipleValores());
								update.setNuOrden(vo.getNuOrden());
								update.setTxAyuda(vo.getTxAyuda());
								parametrosDAO.update(update);
								
								List<PropiedadesVO> listProVo = vo.getPropieades();
								/*Guardar las posibles propiedades aplicables al componente*/
								if(listProVo != null)
									actualizaPropiedadesParamService.comprobarPropiedadesParam(listProVo, update);
								
								/**En caso de que el parámetro es de tipo catálogo*/
								if(vo.getStIsCatalogo().intValue() == 1){
									parametrosTablasDTO = parametrosTablasDAO.getParametrosTablas(vo.getIdParamtro());
									tablasDTO = new TablasDTO();
									ResponseConverter.copiarPropriedades(tablasDTO, vo.getConfigParamTipoCatVO().getTablaActual());
									parametrosTablasDTO.setTabla(tablasDTO);
									parametrosTablasDTO.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
									parametrosTablasDTO.setFhModificacion(new Date());
									parametrosTablasDAO.update(parametrosTablasDTO);//persisir la relación entre el parametro y la tabla catalogo
									listaTMP.add(parametrosTablasDTO);
									
									/**Guardar las columnas que servirán como los filtros de búsqueda dentro del catálogo*/
									actualizaColumnasParamService.actualizaIdentificador(vo.getConfigParamTipoCatVO().getIdentificador(),parametrosTablasDTO);
									actualizaColumnasParamService.actualizaDescripcion(vo.getConfigParamTipoCatVO().getDescripciones(),parametrosTablasDTO);
									actualizaColumnasParamService.actualizaRestriccion(vo.getConfigParamTipoCatVO().getRestricciones(),parametrosTablasDTO);
									actualizaColumnasParamService.actualizaOrden(vo.getConfigParamTipoCatVO().getOrdenes(),parametrosTablasDTO);
								}
								
							}
						}
					}
				}
			}
		}else if(!parametroBD.isEmpty() && (parametrosVO == null || parametrosVO.isEmpty())){
			//borrar todos los parámetros con sus dependientes
			for(ParametrosDTO parametroDTO : parametroBD){
				eliminarParametro(parametroDTO);
			}
		}
		if(!listaTMP.isEmpty()){
			actualizaColumnasParamService.actualizaRestriccionDependiente(listaTMP, reporteVO.getDependenciasSelect());
		}
		
		
		return true;
	}
	
	public List<ParametrosNewReporteVO> nuevosParametros(List<ParametrosNewReporteVO> vos) {
		List<ParametrosNewReporteVO> listReturn = new ArrayList<>();
		for (ParametrosNewReporteVO vo : vos) {
			if (vo.getIdParamtro() == null)
				listReturn.add(vo);
		}
		return listReturn;
	}
	
	
	public List<ParametrosNewReporteVO> extraerColumnasPeticion (Set<Long> elementosNuevos, List<ParametrosNewReporteVO> vos){
		List<ParametrosNewReporteVO> columnasReturn = new ArrayList<>();
		for(Long element: elementosNuevos){
			for(ParametrosNewReporteVO cols: vos){
				if(element.equals(cols.getIdParamtro()))
					columnasReturn.add(cols);
			}
		}
		return columnasReturn;
	}
	
	public List<ParametrosDTO> extraerColumnasBD (Set<Long> elementosABorrar, List<ParametrosDTO> parametros){
		List<ParametrosDTO> columnasReturn = new ArrayList<>();
		for(Long element: elementosABorrar){
			for(ParametrosDTO p: parametros){
				if(element.equals(p.getIdParamtro()))
					columnasReturn.add(p);
			}
		}
		return columnasReturn;
	}
	
	public Set<Long> extraerColumn(List<ParametrosDTO> parametros) {
		Set<Long> viejosElementos = new HashSet<Long>();
		for(ParametrosDTO p: parametros){
			viejosElementos.add(p.getIdParamtro());
		}
		return viejosElementos;
	}
	
	public Set<Long> extraerColumnIds(List<ParametrosNewReporteVO> parametrosVO) {
		Set<Long> elementosNuevos = new HashSet<Long>();
		for(ParametrosNewReporteVO p: parametrosVO){
			elementosNuevos.add(p.getIdParamtro());
		}
		return elementosNuevos;
	}
	

	@Transactional
	@Override
	public Boolean guardarNuevoParametro(ReportesTaqNewReporteVO reporteVO, List<ParametrosNewReporteVO> parametrosVO, ReportesDTO reporteDTO) {

		/* Crear objeto de parametros que persitirán */
		ParametrosDTO parametrosDTO = null;
		TipoParametroDTO tipoParametroDTO = null;
		ComponentesDTO componentesDTO = null;
		TablasDTO tablasDTO = null;
		ParametrosTablasDTO parametrosTablasDTO = null;
		/* Lista temporal para identificar los parametros dependientes */
		List<ParametrosTablasDTO> listaTMP = new ArrayList<>();

		if (!parametrosVO.isEmpty()) {
			for (ParametrosNewReporteVO parametroVO : parametrosVO) {
				parametrosDTO = new ParametrosDTO();
				tipoParametroDTO = new TipoParametroDTO();
				componentesDTO = new ComponentesDTO();
				
				tipoParametroDTO = tipoParametroDAO.gettipoParametroById(parametroVO.getTipoParametro().getIdTipoParametro());
				componentesDTO = componenteDAO.getComponentesById(parametroVO.getComponente().getIdComponente());
				
				parametrosDTO.setReporte(reporteDTO);
				parametrosDTO.setTipoParametro(tipoParametroDTO);
				parametrosDTO.setComponente(componentesDTO);
				parametrosDTO.setCdParametro(parametroVO.getCdParametro());
				parametrosDTO.setNbParametro(parametroVO.getNbParametro());
				parametrosDTO.setTxParametro(parametroVO.getTxParametro());
				parametrosDTO.setTxValor(parametroVO.getTxValor());
				parametrosDTO.setStIsCatalogo(parametroVO.getStIsCatalogo());
				parametrosDTO.setStActivo(1);
				parametrosDTO.setIdUsrCreacion(usrSession.getUsuarioFirmadoVO().getId());
				parametrosDTO.setFhCreacion(new Date());
				parametrosDTO.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
				parametrosDTO.setFhModificacion(new Date());
				parametrosDTO.setStMultipleValores(parametroVO.getStMultipleValores());
				parametrosDTO.setNuOrden(parametroVO.getNuOrden());
				parametrosDTO.setTxAyuda(parametroVO.getTxAyuda());
				parametrosDAO.save(parametrosDTO);
				
				List<PropiedadesVO> listProVo = parametroVO.getPropieades();
				//Guardar las posibles propiedades aplicables al componente
				if(listProVo != null)
					actualizaPropiedadesParamService.guardarNuevaPropiedad(listProVo, parametrosDTO);
				
				//En caso de que el parámetro es de tipo catálogo
				if(parametroVO.getStIsCatalogo().intValue() == 1){
					parametrosTablasDTO = new ParametrosTablasDTO();
					parametrosTablasDTO.setParametro(parametrosDTO);
					tablasDTO = new TablasDTO();
					ResponseConverter.copiarPropriedades(tablasDTO, parametroVO.getConfigParamTipoCatVO().getTablaActual());
					parametrosTablasDTO.setTabla(tablasDTO);
					parametrosTablasDTO.setStActivo(1);
					parametrosTablasDTO.setIdUsrCreacion(usrSession.getUsuarioFirmadoVO().getId());
					parametrosTablasDTO.setFhCreacion(new Date());
					parametrosTablasDTO.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
					parametrosTablasDTO.setFhModificacion(new Date());
					parametrosTablasDAO.save(parametrosTablasDTO);//persisir la relación entre el parametro y la tabla catalogo
					listaTMP.add(parametrosTablasDTO);
					
					//Guardar las columnas que servirán como los filtros de búsqueda dentro del catálogo
					guadaColumnasParamService.guardarIdentificador(parametroVO.getConfigParamTipoCatVO().getIdentificador(),parametrosTablasDTO);
					guadaColumnasParamService.guardarDescripcion(parametroVO.getConfigParamTipoCatVO().getDescripciones(),parametrosTablasDTO);
					guadaColumnasParamService.guardarRestriccion(parametroVO.getConfigParamTipoCatVO().getRestricciones(),parametrosTablasDTO);
					guadaColumnasParamService.guardarOrden(parametroVO.getConfigParamTipoCatVO().getOrdenes(),parametrosTablasDTO);
				}
				
			}
		}
		/*Validación para guardar los parámetros dependientes*/
		if(!listaTMP.isEmpty() && (reporteVO.getDependenciasSelect() != null && !reporteVO.getDependenciasSelect().isEmpty())){
			guadaColumnasParamService.guardaRestriccionDependiente(listaTMP, reporteVO.getDependenciasSelect());
		}
		return true;
	}


	@Override
	public Boolean eliminarParametro(ParametrosDTO parametroDTO) {
		/*Obtener las propiedades de los parámetros*/
		List<ParametrosPropDTO> propiedadesParam = parametrosPropDAO.getParametrosPropiedad(parametroDTO.getIdParamtro());
		if(!propiedadesParam.isEmpty()){
			for(ParametrosPropDTO paramPropDTO: propiedadesParam){
				/*Obtener las propiedades dell script en caso de que lo tenga*/
				ParamPropScriptDTO paramPropScriptDTO = paramPropScriptDAO.getParamPropById(paramPropDTO.getIdParamtrosProp());
				/*eliminar el registro su existe*/
				if(paramPropScriptDTO != null)
					paramPropScriptDAO.delete(paramPropScriptDTO);
				/*Eliminar la propiedad*/
				parametrosPropDAO.delete(paramPropDTO);
			}
		}
		/*Obener las asociaciones con los catálogos, en caso de 
		 * que el parámetro es de tipo catalogo*/
		ParametrosTablasDTO propiedadesTabDTO = parametrosTablasDAO.getParametrosTablas(parametroDTO.getIdParamtro());
		if(propiedadesTabDTO != null){
			/*Obtener los parametros columnas*/
			List<ParametrosColumnDTO> paramsColumn =  parametrosColumnDAO.getAllColumns(propiedadesTabDTO.getIdParametroTabla());
			if(!paramsColumn.isEmpty()){
				for(ParametrosColumnDTO paramColumnDTO: paramsColumn){
					parametrosColumnDAO.delete(paramColumnDTO);
				}
			}
			parametrosTablasDAO.delete(propiedadesTabDTO);
		}
		/*Depues de haber eliminado todas las dependencias
		 * de los parametros borramos el parametros*/
		parametrosDAO.delete(parametroDTO);
		return true;
	}
	
}
