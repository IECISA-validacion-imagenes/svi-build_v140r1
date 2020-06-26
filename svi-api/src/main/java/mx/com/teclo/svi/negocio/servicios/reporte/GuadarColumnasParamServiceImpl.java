package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GuadarColumnasParamServiceImpl implements GuadaColumnasParamService{

	@Autowired
	private ParametrosColumnDAO parametrosColumnDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmado;
	
	
	@Override
	@Transactional
	public Boolean guardarIdentificador(List<ColumnasVO> identificador, ParametrosTablasDTO parametrosTablasDTO) {
		ColumnasDTO columnasDTO = null;
		ParametrosColumnDTO dtoSave = null;
		if (!identificador.isEmpty()) {
			for (ColumnasVO columnasVO : identificador) {
				dtoSave = setDatosBitacora();
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
				parametrosColumnDAO.save(dtoSave);
			}
		}
		return true;
	}
	@Override
	@Transactional
	public Boolean  guardarDescripcion(List<ColumnasVO> descripcion, ParametrosTablasDTO parametrosTablasDTO){
		ColumnasDTO columnasDTO = null;
		ParametrosColumnDTO dtoSave = null;
		if (!descripcion.isEmpty()) {
			for (ColumnasVO columnasVO : descripcion) {
				dtoSave = setDatosBitacora();
				columnasDTO = new ColumnasDTO();
				ResponseConverter.copiarPropriedades(columnasDTO, columnasVO);
				dtoSave.setColumna(columnasDTO);
				dtoSave.setParametroTabla(parametrosTablasDTO);
				dtoSave.setStIsKey(0);
				dtoSave.setStIsDesc(1);
				dtoSave.setStIsWhere(0);
				dtoSave.setTxValorWhere(null);
				dtoSave.setTipoOperador(null);
				dtoSave.setStIsOrder(0);
				dtoSave.setTpOrder(null);
				dtoSave.setIdParamTabDependency(null);
				dtoSave.setNuOrden(columnasVO.getNuOrden());//order que se aplciarÃ¡n las descripciones en el select
				parametrosColumnDAO.save(dtoSave);
			}
		}
		return true;
	}
	@Override
	@Transactional
	public Boolean guardarRestriccion(List<ColumnasVO> restriccion, ParametrosTablasDTO parametrosTablasDTO) {
		ColumnasDTO columnasDTO = null;
		ParametrosColumnDTO dtoSave = null;
		TipoOperadorDTO tipoOperadorDTO = null;
		if (!restriccion.isEmpty()) {
			for (ColumnasVO columnasVO : restriccion) {
				dtoSave = setDatosBitacora();
				columnasDTO = new ColumnasDTO();
				ResponseConverter.copiarPropriedades(columnasDTO, columnasVO);
				dtoSave.setColumna(columnasDTO);
				dtoSave.setParametroTabla(parametrosTablasDTO);
				dtoSave.setStIsKey(0);
				dtoSave.setStIsDesc(0);
				dtoSave.setStIsWhere(1);
				dtoSave.setTxValorWhere(columnasVO.getValor());
				tipoOperadorDTO = new TipoOperadorDTO();
				ResponseConverter.copiarPropriedades(tipoOperadorDTO, columnasVO.getTipoOperador());
				if(columnasVO.getTipoOperador() != null){
					ResponseConverter.copiarPropriedades(tipoOperadorDTO, columnasVO.getTipoOperador());
					dtoSave.setTipoOperador(tipoOperadorDTO);
				}else{
					dtoSave.setTipoOperador(null);
				}
				dtoSave.setStIsOrder(0);
				dtoSave.setTpOrder(null);
				dtoSave.setIdParamTabDependency(null);
				dtoSave.setNuOrden(0L);// order que se aplciarÃ¡n las descripciones en el select
				parametrosColumnDAO.save(dtoSave);
			}
		}
		return true;
	}
	@Override
	@Transactional
	public Boolean guardarOrden(List<ColumnasVO> orden, ParametrosTablasDTO parametrosTablasDTO){
		ColumnasDTO columnasDTO = null;
		ParametrosColumnDTO dtoSave = null;
		if (!orden.isEmpty()) {
			for (ColumnasVO columnasVO : orden) {
				dtoSave = setDatosBitacora();
				columnasDTO = new ColumnasDTO();
				ResponseConverter.copiarPropriedades(columnasDTO, columnasVO);
				dtoSave.setColumna(columnasDTO);
				dtoSave.setParametroTabla(parametrosTablasDTO);
				dtoSave.setStIsKey(0);
				dtoSave.setStIsDesc(0);
				dtoSave.setStIsWhere(0);
				dtoSave.setTxValorWhere(null);
				dtoSave.setTipoOperador(null);
				dtoSave.setStIsOrder(1);
				dtoSave.setTpOrder(columnasVO.getTipoOrdenamiento());
				dtoSave.setIdParamTabDependency(null);
				dtoSave.setNuOrden(columnasVO.getNuOrden());// order que se aplciarÃ¡n las descripciones en el select
				parametrosColumnDAO.save(dtoSave);
			}
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
	@Override
	@Transactional
	public Boolean guardaRestriccionDependiente(List<ParametrosTablasDTO> paramsTabs,List<DependenciasSelectVO> dependencias) {
		ParametrosColumnDTO dtoSave = null;
		ColumnasDTO columnasDTO = null;
		for(ParametrosTablasDTO parametroTablaDTO: paramsTabs){
			for(DependenciasSelectVO dependenciaVO : dependencias){
				ParametrosTablasDTO padre = getParamPadreDependencia(paramsTabs, dependenciaVO.getCdParametroPadre());//padre dependiente
				if(padre != null && dependenciaVO.getCdParametroHijo().equals(parametroTablaDTO.getParametro().getCdParametro())){
					dtoSave = setDatosBitacora();
					columnasDTO = new ColumnasDTO();
					ResponseConverter.copiarPropriedades(columnasDTO, dependenciaVO.getColumnaHijo());
					dtoSave.setColumna(columnasDTO);
					dtoSave.setParametroTabla(parametroTablaDTO);
					dtoSave.setStIsKey(0);
					dtoSave.setStIsDesc(0);
					dtoSave.setStIsWhere(1);
					dtoSave.setTxValorWhere(null);
					dtoSave.setTipoOperador(null);
					dtoSave.setStIsOrder(0);
					dtoSave.setTpOrder(null);
					dtoSave.setIdParamTabDependency(padre.getIdParametroTabla());
					dtoSave.setNuOrden(0L);
					parametrosColumnDAO.save(dtoSave);
				}
			}
		}
		return true;
	}
	
}
