package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.AgrupacionHojasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.FormatoDescargaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ReportesFormatosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.AgrupacionHojasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesFormatosDTO;

@Service
public class AdminReporteFormatosServiceImpl implements AdminReporteFormatosService{

	@Autowired
	private ReportesFormatosDAO reportesFormatosDAO;
	
	@Autowired
	private AgrupacionHojasDAO agrupacionHojasDAO;
	
	@Autowired
	private FormatoDescargaDAO formatoDescargaDAO;
	
	@Autowired
	private UsuarioFirmadoService usrSession;
	
	@Autowired
	private ReportesDAO reporteDAO;
	
	@Value("${app.config.codigo}")
	private String codigo;
	
	@Transactional
	@Override
	public Boolean comprobarFormatoDescarga(List<FormatoDescargaVO> formatosPeticion, Long idReporte, AgrupacionHojasVO agrupacion,String colPagina){
		List<ReportesFormatosDTO> reporteActualesBD = reportesFormatosDAO.getReportesFormatosById(idReporte);
		ReportesDTO reporteDTO = reporteDAO.getReporteById(idReporte, codigo);
		if(!reporteActualesBD.isEmpty() && !formatosPeticion.isEmpty()){
			Set<Long> listaPeticion = extrerIdVO(formatosPeticion);
			Set<Long> listaBD = extrerIdDTO(reporteActualesBD);
			
			Set<Long> elementosABorrar = Sets.difference(listaBD, listaPeticion);
			Set<Long> elementosNuevos = Sets.difference(listaPeticion, listaBD);
			Set<Long> interseccion = Sets.intersection(listaPeticion, listaBD);
			
			if(!elementosABorrar.isEmpty()){
				List<ReportesFormatosDTO> delete = getFormatosDTO(elementosABorrar, reporteActualesBD);
				if (!delete.isEmpty())
					for (ReportesFormatosDTO pc : delete)
						reportesFormatosDAO.delete(pc);
			}
			if(!elementosNuevos.isEmpty()){
				List<FormatoDescargaVO> formatoToSave= getFormatosToSave(elementosNuevos, formatosPeticion);
				if(!formatosPeticion.isEmpty()){
					for(FormatoDescargaVO voToSave: formatoToSave){
						guadarNuevoFormatoDescarga(reporteDTO,agrupacion.getIdTipoAgrupacion(),voToSave,colPagina);
					}
				}
			}
			if(!interseccion.isEmpty()){
				AgrupacionHojasDTO agrupacionHojasDTO = null;
				List<ReportesFormatosDTO> formatsToUpdate = getFormatosDTO(interseccion, reporteActualesBD);
				if(!formatsToUpdate.isEmpty()){
					for(ReportesFormatosDTO dto: formatsToUpdate){
						agrupacionHojasDTO = new AgrupacionHojasDTO();
						ResponseConverter.copiarPropriedades(agrupacionHojasDTO, agrupacion);
						dto.setAgrupacionHojas(agrupacionHojasDTO);
						dto.setNbColumnaAgrupacion(colPagina);
						dto.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
						dto.setFhModificacion(new Date());
						reportesFormatosDAO.update(dto);
					}
				}
			}
		}
		return true;
	}

	public Set<Long> extrerIdVO (List<FormatoDescargaVO> formatoVO){
		Set<Long> elementoVO = new HashSet<Long>();
		for(FormatoDescargaVO vo: formatoVO){
			elementoVO.add(vo.getIdFormatoDescarga());
		}
		return elementoVO;
	}
	
	public Set<Long> extrerIdDTO (List<ReportesFormatosDTO> repFormatoDTO){
		Set<Long> elementoBD = new HashSet<Long>();
		for(ReportesFormatosDTO dto: repFormatoDTO){
			elementoBD.add(dto.getFormatoDescarga().getIdFormatoDescarga());
		}
		return elementoBD;
	}
	
	@Transactional
	public List<ReportesFormatosDTO> getFormatosDTO (Set<Long> elementosABorrar, List<ReportesFormatosDTO> elementoBD){
		List<ReportesFormatosDTO> columnasReturn = new ArrayList<>();
		for(Long element: elementosABorrar){
			for(ReportesFormatosDTO pc: elementoBD){
				if(element.equals(pc.getFormatoDescarga().getIdFormatoDescarga()))
					columnasReturn.add(pc);
			}
		}
		return columnasReturn;
	}

	public List<FormatoDescargaVO> getFormatosToSave (Set<Long> elementosNuevos, List<FormatoDescargaVO> vos){
		List<FormatoDescargaVO> formatosReturn = new ArrayList<>();
		for(Long elemento: elementosNuevos){
			for(FormatoDescargaVO vo : vos){
				if(elemento.equals(vo.getIdFormatoDescarga()))
					formatosReturn.add(vo);
			}
		}
		return formatosReturn;
	}
	
	@Transactional
	@Override
	public void guadarNuevoFormatoDescarga(ReportesDTO reporteDTO,
										   Long idTipoAgrupacion,
										   FormatoDescargaVO formatToSave, 
										   String colPaginacion){
		/*Nuevo objeto de reportes formatos que va persistir*/
		ReportesFormatosDTO reportesFormatosDTO = null;
		FormatoDescargaDTO formatoDescargaDTO = null;
		AgrupacionHojasDTO agrupacionHojasDTO = agrupacionHojasDAO.getTipoAgrupacionById(idTipoAgrupacion);
		
		reportesFormatosDTO = new ReportesFormatosDTO();
		formatoDescargaDTO = formatoDescargaDAO.getFormatoDescargaById(formatToSave.getIdFormatoDescarga());
		reportesFormatosDTO.setFormatoDescarga(formatoDescargaDTO);
		reportesFormatosDTO.setReporte(reporteDTO);
		reportesFormatosDTO.setAgrupacionHojas(agrupacionHojasDTO);
		reportesFormatosDTO.setStActivo(1);
		reportesFormatosDTO.setIdUsrCreacion(usrSession.getUsuarioFirmadoVO().getId());
		reportesFormatosDTO.setFhCreacion(new Date());
		reportesFormatosDTO.setIdUsrModifica(usrSession.getUsuarioFirmadoVO().getId());
		reportesFormatosDTO.setFhModificacion(new Date());
		if(colPaginacion != null){
			reportesFormatosDTO.setNbColumnaAgrupacion(colPaginacion);
		}
		reportesFormatosDAO.saveOrUpdate(reportesFormatosDTO);
	}
	
}
