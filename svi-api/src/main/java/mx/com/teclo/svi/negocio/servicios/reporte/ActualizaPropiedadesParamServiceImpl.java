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
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.ParamPropScriptVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParamPropScriptDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosPropDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PropiedadesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParamPropScriptDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosPropDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesDTO;

@Service
public class ActualizaPropiedadesParamServiceImpl implements ActualizaPropiedadesParamService{

	@Autowired
	private ParametrosPropDAO parametrosPropDAO;
	
	@Autowired
	private PropiedadesDAO propiedadesDAO;
	
	@Autowired
	private UsuarioFirmadoService usrSession;
	
	@Autowired
	private ParamPropScriptDAO paramPropScriptDAO;
	
	@Transactional
	@Override
	public Boolean comprobarPropiedadesParam(List<PropiedadesVO> propiedades, ParametrosDTO parametrosDTO) {
		List<ParametrosPropDTO> propiedadesDTO = parametrosPropDAO.getParametrosPropiedad(parametrosDTO.getIdParamtro());
		
		if(propiedadesDTO.isEmpty() && !propiedades.isEmpty()){//guardar todas las nuevas propiedades
			guardarNuevaPropiedad(propiedades, parametrosDTO);
		}else if(!propiedadesDTO.isEmpty() && !propiedades.isEmpty()){
			Set<Long> listaDesBD = extraerColumn(propiedadesDTO);
			Set<Long> listaDesPeticion = extraerColumnIds(propiedades);
			
			Set<Long> elementosABorrar = Sets.difference(listaDesBD, listaDesPeticion);
			Set<Long> elementosNuevos = Sets.difference(listaDesPeticion, listaDesBD);
			Set<Long> interseccion = Sets.intersection(listaDesPeticion, listaDesBD);

			if(!elementosABorrar.isEmpty()){
				List<ParametrosPropDTO> toDelete = extraerColumnasBD(elementosABorrar, propiedadesDTO);
				if(!toDelete.isEmpty()){
					for(ParametrosPropDTO deleteEl: toDelete){
						/*Obtener las propiedades dell script en caso de que lo tenga*/
						ParamPropScriptDTO paramPropScriptDTO = paramPropScriptDAO.getParamPropById(deleteEl.getIdParamtrosProp());
						/*eliminar el registro su existe*/
						if(paramPropScriptDTO != null)
							paramPropScriptDAO.delete(paramPropScriptDTO);
						/*Eliminar la propiedad*/
						parametrosPropDAO.delete(deleteEl);
					}
				}
			}
			if(!elementosNuevos.isEmpty()){
				List<PropiedadesVO> toSave = extraerColumnasPeticion(elementosNuevos,propiedades);
				if(!toSave.isEmpty()){
					guardarNuevaPropiedad(toSave, parametrosDTO);
				}
			}
			
			if(!interseccion.isEmpty()){
				List<ParametrosPropDTO> toUpdate = extraerColumnasBD(interseccion, propiedadesDTO);
				List<PropiedadesVO> voInterseccion = extraerColumnasPeticion(interseccion,propiedades);
				ParamPropScriptDTO dtoParamPropScript = null;
				if(!toUpdate.isEmpty() && !voInterseccion.isEmpty()){
					for(PropiedadesVO vo: voInterseccion){
						for(ParametrosPropDTO update: toUpdate){
							if(vo.getIdPropiedad().equals(update.getPropiedad().getIdPropiedad())){
								update.setFhModificacion(new Date());
								update.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
								if(vo.getValue() == null && update.getPropiedad().getStValorRequerido().equals(0L))
									update.setTxValor("DIRECTIVESINVAL");
								else
									update.setTxValor(vo.getValue());
								parametrosPropDAO.update(update);
								/*Validar aquí si la propiedad nueva es de tipo query lo cual significa 
								 * que es un componente de tipo doble lista*/
								if(vo.getParamPropScriptVO() != null){
									dtoParamPropScript = paramPropScriptDAO.getParamPropById(update.getIdParamtrosProp());
									ParamPropScriptVO paramPropScriptVO = vo.getParamPropScriptVO();
									if(dtoParamPropScript!= null){
										dtoParamPropScript.setFhModificacion(new Date());
										dtoParamPropScript.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
										String cdParamProvisional = paramPropScriptVO.getCdParamProvisional();
										String queryFull = paramPropScriptVO.getQueryFull().replace(cdParamProvisional, "busquePrevia"+parametrosDTO.getIdParamtro());
										dtoParamPropScript.setScript(queryFull);
										paramPropScriptDAO.update(dtoParamPropScript);
									}
								}
							}
						}
					}	
				}
			}
		}else if(!propiedadesDTO.isEmpty() && propiedades.isEmpty()){
			for(ParametrosPropDTO deleteEl: propiedadesDTO){
				/*Obtener las propiedades dell script en caso de que lo tenga*/
				ParamPropScriptDTO paramPropScriptDTO = paramPropScriptDAO.getParamPropById(deleteEl.getIdParamtrosProp());
				/*eliminar el registro su existe*/
				if(paramPropScriptDTO != null)
					paramPropScriptDAO.delete(paramPropScriptDTO);
				/*Eliminar la propiedad*/
				parametrosPropDAO.delete(deleteEl);
			}
		}
		
		return true;
	}
	
	

	@Transactional
	@Override
	public Boolean guardarNuevaPropiedad(List<PropiedadesVO> propiedades, ParametrosDTO parametrosDTO) {
		if ((propiedades != null && !propiedades.isEmpty()) && parametrosDTO != null) {
			ParametrosPropDTO parametrosPropDTO = null;
			PropiedadesDTO propiedadDTO = null;
			ParamPropScriptDTO dtoParamPropScript = null;
			for (PropiedadesVO propiedadVO : propiedades) {
				parametrosPropDTO = new ParametrosPropDTO();// objecto a persistir
				propiedadDTO = new PropiedadesDTO();
				propiedadDTO = propiedadesDAO.getPropiedadById(propiedadVO.getIdPropiedad());
				parametrosPropDTO.setParametro(parametrosDTO);
				parametrosPropDTO.setPropiedad(propiedadDTO);
				parametrosPropDTO.setStActivo(1);
				parametrosPropDTO.setIdUsrCreacion(usrSession.getUsuarioFirmadoVO().getId());
				parametrosPropDTO.setFhCreacion(new Date());
				parametrosPropDTO.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
				parametrosPropDTO.setFhModificacion(new Date());

				if (propiedadVO.getValue() == null && propiedadDTO.getStValorRequerido().equals(0L))
					parametrosPropDTO.setTxValor("DIRECTIVESINVAL");
				else
					parametrosPropDTO.setTxValor(propiedadVO.getValue());
				parametrosPropDAO.save(parametrosPropDTO);

				// Validar aquí si la propiedad nueva es de tipo query lo cual
				// significa que es un componente de tipo doble lista
				if(propiedadVO.getParamPropScriptVO() != null){
					ParamPropScriptVO paramPropScriptVO = propiedadVO.getParamPropScriptVO();
					dtoParamPropScript = new ParamPropScriptDTO();
					dtoParamPropScript.setParamtrosPropDTO(parametrosPropDTO);
					String cdParamProvisional = paramPropScriptVO.getCdParamProvisional();
					String queryFull = paramPropScriptVO.getQueryFull().replace(cdParamProvisional, "busquePrevia"+parametrosDTO.getIdParamtro());
					dtoParamPropScript.setScript(queryFull);
					dtoParamPropScript.setStActivo(1);
					dtoParamPropScript.setIdUsrCreacion(usrSession.getUsuarioFirmadoVO().getId());
					dtoParamPropScript.setFhCreacion(new Date());
					dtoParamPropScript.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
					dtoParamPropScript.setFhModificacion(new Date());
					paramPropScriptDAO.save(dtoParamPropScript);
				}
			}
		}
		return true;
	}
	
	@Transactional
	public Set<Long> extraerColumn(List<ParametrosPropDTO> paramProp) {
		Set<Long> viejosElementos = new HashSet<Long>();
		for(ParametrosPropDTO pc: paramProp){
			viejosElementos.add(pc.getPropiedad().getIdPropiedad());
		}
		return viejosElementos;
	}
	
	public Set<Long> extraerColumnIds(List<PropiedadesVO> propiedadVO) {
		Set<Long> elementosNuevos = new HashSet<Long>();
		for(PropiedadesVO pc: propiedadVO){
			elementosNuevos.add(pc.getIdPropiedad());
		}
		return elementosNuevos;
	}
	public List<ParametrosPropDTO> extraerColumnasBD (Set<Long> elementosABorrar, List<ParametrosPropDTO> propiedades){
		List<ParametrosPropDTO> columnasReturn = new ArrayList<>();
		for(Long element: elementosABorrar){
			for(ParametrosPropDTO pc: propiedades){
				if(element.equals(pc.getPropiedad().getIdPropiedad()))
					columnasReturn.add(pc);
			}
		}
		return columnasReturn;
	}
	public List<PropiedadesVO> extraerColumnasPeticion (Set<Long> elementosNuevos, List<PropiedadesVO> vos){
		List<PropiedadesVO> columnasReturn = new ArrayList<>();
		for(Long element: elementosNuevos){
			for(PropiedadesVO cols: vos){
				if(element.equals(cols.getIdPropiedad()))
					columnasReturn.add(cols);
			}
		}
		return columnasReturn;
	}
}
