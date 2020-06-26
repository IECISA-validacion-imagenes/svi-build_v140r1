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
import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.DependenciasSelectVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ParametrosColumnDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosColumnDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ParametrosTablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;

@Service
public class ActualizaColumnasParamServiceImpl implements ActualizaColumnasParamService{

	@Autowired
	private ParametrosColumnDAO parametrosColumnDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmado;
	
	@Autowired
	private GuadaColumnasParamService guadaColumnasParamService;
	
	@Override
	public Boolean actualizaIdentificador(List<ColumnasVO> identificador, ParametrosTablasDTO parametrosTablasDTO) {
		ColumnasDTO columnasDTO = null;
		ParametrosColumnDTO dtoSave = null;
		if (!identificador.isEmpty()) {
			for (ColumnasVO columnasVO : identificador) {
				dtoSave = parametrosColumnDAO.getIdentificador(parametrosTablasDTO.getIdParametroTabla());
				columnasDTO = new ColumnasDTO();
				ResponseConverter.copiarPropriedades(columnasDTO, columnasVO);
				dtoSave.setColumna(columnasDTO);
				dtoSave.setParametroTabla(parametrosTablasDTO);
				dtoSave.setStIsKey(1);
				dtoSave.setStIsDesc(0);
				dtoSave.setStIsWhere(0);
				dtoSave.setTxValorWhere(null);
				dtoSave.setTipoOperador(null);
				dtoSave.setStIsOrder(0);
				dtoSave.setTpOrder(null);
				dtoSave.setIdParamTabDependency(null);
				dtoSave.setNuOrden(0L);//order en caso de identificador no aplica
				parametrosColumnDAO.update(dtoSave);
			}
		}
		return true;
	}	
	@Override
	public Boolean actualizaDescripcion(List<ColumnasVO> descripcion, ParametrosTablasDTO parametrosTablasDTO) {
		List<ParametrosColumnDTO> parametrosColumnDesc = parametrosColumnDAO.getDescripcionList(parametrosTablasDTO.getIdParametroTabla());//obtener todas las descripciones actuales
		if(parametrosColumnDesc.isEmpty() && !descripcion.isEmpty()){//si no hay descripciones y si hay en los VOs, guardar todos
			guadaColumnasParamService.guardarDescripcion(descripcion,parametrosTablasDTO);
		}else if(!parametrosColumnDesc.isEmpty() && !descripcion.isEmpty()){
			Set<Long> listaDesBD = extraerColumn(parametrosColumnDesc);
			Set<Long> listaDesPeticion = extraerColumnIds(descripcion);
			
			Set<Long> elementosABorrar = Sets.difference(listaDesBD, listaDesPeticion);
			Set<Long> elementosNuevos = Sets.difference(listaDesPeticion, listaDesBD);
			Set<Long> interseccion = Sets.intersection(listaDesPeticion, listaDesBD);
			
			if(!elementosABorrar.isEmpty()){
				List<ParametrosColumnDTO> delete = extraerColumnasBD(elementosABorrar, parametrosColumnDesc);
					if(!delete.isEmpty())
						for(ParametrosColumnDTO pc : delete)
							parametrosColumnDAO.delete(pc);
			}
			if(!elementosNuevos.isEmpty()){
				List<ColumnasVO> columnasGuadar = extraerColumnasPeticion(elementosNuevos,descripcion);
				if(!columnasGuadar.isEmpty())
					guadaColumnasParamService.guardarDescripcion(columnasGuadar,parametrosTablasDTO);
			}
			if(!interseccion.isEmpty()){
				List<ParametrosColumnDTO> columnasUpdateDB = extraerColumnasBD(interseccion, parametrosColumnDesc);
				List<ColumnasVO> columnasPeticion = extraerColumnasPeticion(interseccion,descripcion);
				if(!columnasUpdateDB.isEmpty() && !columnasPeticion.isEmpty()){
					for(ColumnasVO vo: columnasPeticion){
						for(ParametrosColumnDTO dto: columnasUpdateDB){
							if(vo.getIdColumna().equals(dto.getColumna().getIdColumna())){
								dto.setFhModificacion(new Date());
								dto.setIdUsrModifica(usuarioFirmado.getUsuarioFirmadoVO().getId());
								dto.setNuOrden(vo.getNuOrden());
								parametrosColumnDAO.update(dto);
							}
						}
					}
				}		
			}
		}	
		return true;
	}
	@Override
	public Boolean actualizaRestriccion(List<ColumnasVO> restriccion, ParametrosTablasDTO parametrosTablasDTO) {
		List<ParametrosColumnDTO> parametrosColumnRest = parametrosColumnDAO.getRestriccionList(parametrosTablasDTO.getIdParametroTabla());//obtener todas las descripciones actuales
		if(parametrosColumnRest.isEmpty() && !restriccion.isEmpty()){//si no hay descripciones y si hay en los VOs, guardar todos
			guadaColumnasParamService.guardarRestriccion(restriccion,parametrosTablasDTO);
		}else if(!parametrosColumnRest.isEmpty() && !restriccion.isEmpty()){
			Set<Long> listaDesBD = extraerColumn(parametrosColumnRest);
			Set<Long> listaDesPeticion = extraerColumnIds(restriccion);
			
			Set<Long> elementosABorrar = Sets.difference(listaDesBD, listaDesPeticion);
			Set<Long> elementosNuevos = Sets.difference(listaDesPeticion, listaDesBD);
			Set<Long> interseccion = Sets.intersection(listaDesPeticion, listaDesBD);
			
			if(!elementosABorrar.isEmpty()){
				List<ParametrosColumnDTO> delete = extraerColumnasBD(elementosABorrar, parametrosColumnRest);
					if(!delete.isEmpty())
						for(ParametrosColumnDTO pc : delete)
							parametrosColumnDAO.delete(pc);
			}
			if(!elementosNuevos.isEmpty()){
				List<ColumnasVO> columnasGuadar = extraerColumnasPeticion(elementosNuevos,restriccion);
				if(!columnasGuadar.isEmpty())
					guadaColumnasParamService.guardarRestriccion(columnasGuadar,parametrosTablasDTO);
			}
			
			if(!interseccion.isEmpty()){
				TipoOperadorDTO tipoOperadorDTO = null;
				List<ParametrosColumnDTO> columnasUpdateDB = extraerColumnasBD(interseccion, parametrosColumnRest);
				List<ColumnasVO> columnasPeticion = extraerColumnasPeticion(interseccion,restriccion);
				if(!columnasUpdateDB.isEmpty() && !columnasPeticion.isEmpty()){
					for(ColumnasVO vo: columnasPeticion){
						for(ParametrosColumnDTO dto: columnasUpdateDB){
							if(vo.getIdColumna().equals(dto.getColumna().getIdColumna())){
								dto.setFhModificacion(new Date());
								dto.setIdUsrModifica(usuarioFirmado.getUsuarioFirmadoVO().getId());
								dto.setTxValorWhere(vo.getValor());
								tipoOperadorDTO = new TipoOperadorDTO();
								if(vo.getTipoOperador() != null){
									ResponseConverter.copiarPropriedades(tipoOperadorDTO, vo.getTipoOperador());
									dto.setTipoOperador(tipoOperadorDTO);
								}else{
									dto.setTipoOperador(null);
								}
								parametrosColumnDAO.update(dto);
							}
						}
					}
				}		
			}
		}
		return true;
	}
	@Override
	public Boolean actualizaOrden(List<ColumnasVO> orden, ParametrosTablasDTO parametrosTablasDTO) {
		List<ParametrosColumnDTO> parametrosColumnOrder = parametrosColumnDAO.getOrdenList(parametrosTablasDTO.getIdParametroTabla());//obtener todas las descripciones actuales
		if(parametrosColumnOrder.isEmpty() && !orden.isEmpty()){//si no hay descripciones y si hay en los VOs, guardar todos
			guadaColumnasParamService.guardarOrden(orden,parametrosTablasDTO);
		}else if(!parametrosColumnOrder.isEmpty() && !orden.isEmpty()){
			Set<Long> listaDesBD = extraerColumn(parametrosColumnOrder);
			Set<Long> listaDesPeticion = extraerColumnIds(orden);
			
			Set<Long> elementosABorrar = Sets.difference(listaDesBD, listaDesPeticion);
			Set<Long> elementosNuevos = Sets.difference(listaDesPeticion, listaDesBD);
			Set<Long> interseccion = Sets.intersection(listaDesPeticion, listaDesBD);

			if(!elementosABorrar.isEmpty()){
				List<ParametrosColumnDTO> delete = extraerColumnasBD(elementosABorrar, parametrosColumnOrder);
					if(!delete.isEmpty())
						for(ParametrosColumnDTO pc : delete)
							parametrosColumnDAO.delete(pc);
			}
			if(!elementosNuevos.isEmpty()){
				List<ColumnasVO> columnasGuadar = extraerColumnasPeticion(elementosNuevos,orden);
				if(!columnasGuadar.isEmpty())
					guadaColumnasParamService.guardarOrden(columnasGuadar,parametrosTablasDTO);
			}
			if(!interseccion.isEmpty()){
				List<ParametrosColumnDTO> columnasUpdateDB = extraerColumnasBD(interseccion, parametrosColumnOrder);
				List<ColumnasVO> columnasPeticion = extraerColumnasPeticion(interseccion,orden);
				if(!columnasUpdateDB.isEmpty() && !columnasPeticion.isEmpty()){
					for(ColumnasVO vo: columnasPeticion){
						for(ParametrosColumnDTO dto: columnasUpdateDB){
							if(vo.getIdColumna().equals(dto.getColumna().getIdColumna())){
								dto.setFhModificacion(new Date());
								dto.setIdUsrModifica(usuarioFirmado.getUsuarioFirmadoVO().getId());
								dto.setTpOrder(vo.getTipoOrdenamiento());
								dto.setNuOrden(vo.getNuOrden());
								parametrosColumnDAO.update(dto);
							}
						}
					}
				}		
			}
		}
		return true;
	}
	@Transactional
	@Override
	public Boolean actualizaRestriccionDependiente(List<ParametrosTablasDTO> paramsTabs,List<DependenciasSelectVO> dependencias) {
		for(ParametrosTablasDTO ptab : paramsTabs){
			List<ParametrosColumnDTO> paramsColumn =  parametrosColumnDAO.getColumnasDependientes(ptab.getIdParametroTabla());
			if(!paramsColumn.isEmpty()){
				for(ParametrosColumnDTO ocolumns : paramsColumn){
					parametrosColumnDAO.delete(ocolumns);
				}
			}
		}
		if(!paramsTabs.isEmpty() && (dependencias != null && !dependencias.isEmpty())){
			guadaColumnasParamService.guardaRestriccionDependiente(paramsTabs, dependencias);
		}
		return true;
	}
	@Override
	public ParametrosColumnDTO setDatosBitacora() {
		Long idUserInSession = usuarioFirmado.getUsuarioFirmadoVO().getId();
		ParametrosColumnDTO dtoReturn = new ParametrosColumnDTO();
		
		dtoReturn.setStActivo(1);
		dtoReturn.setIdUsrCreacion(idUserInSession);
		dtoReturn.setFhCreacion(new Date());
		dtoReturn.setIdUsrModifica(idUserInSession);
		dtoReturn.setFhModificacion(new Date());
		return dtoReturn;
	}
	@Override
	public ParametrosTablasDTO getParamPadreDependencia(List<ParametrosTablasDTO> dtos, String cdParametro) {
		ParametrosTablasDTO objectReturn = null;
		if(!dtos.isEmpty()){
			for(ParametrosTablasDTO dto : dtos){
				if(dto.getParametro().getCdParametro().equals(cdParametro)){
					objectReturn = new ParametrosTablasDTO();
					ResponseConverter.copiarPropriedades(objectReturn, dto);
					break;
				}
			}
		}
		return objectReturn;
	}
	public Set<Long> extraerColumn(List<ParametrosColumnDTO> parametrosColumnDesc) {
		Set<Long> viejosElementos = new HashSet<Long>();
		for(ParametrosColumnDTO pc: parametrosColumnDesc){
			viejosElementos.add(pc.getColumna().getIdColumna());
		}
		return viejosElementos;
	}
	public Set<Long> extraerColumnIds(List<ColumnasVO> columnas) {
		Set<Long> elementosNuevos = new HashSet<Long>();
		for(ColumnasVO pc: columnas){
			elementosNuevos.add(pc.getIdColumna());
		}
		return elementosNuevos;
	}
	public List<ColumnasVO> extraerColumnasPeticion (Set<Long> elementosNuevos, List<ColumnasVO> vos){
		List<ColumnasVO> columnasReturn = new ArrayList<>();
		for(Long element: elementosNuevos){
			for(ColumnasVO cols: vos){
				if(element.equals(cols.getIdColumna()))
					columnasReturn.add(cols);
			}
		}
		return columnasReturn;
	}
	public List<ParametrosColumnDTO> extraerColumnasBD (Set<Long> elementosABorrar, List<ParametrosColumnDTO> columnaDTO){
		List<ParametrosColumnDTO> columnasReturn = new ArrayList<>();
		for(Long element: elementosABorrar){
			for(ParametrosColumnDTO pc: columnaDTO){
				if(element.equals(pc.getColumna().getIdColumna()))
					columnasReturn.add(pc);
			}
		}
		return columnasReturn;
	}
	
	
}
