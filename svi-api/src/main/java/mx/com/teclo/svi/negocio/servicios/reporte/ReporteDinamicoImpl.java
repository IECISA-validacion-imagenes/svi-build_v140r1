package mx.com.teclo.svi.negocio.servicios.reporte;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.vo.reporte.ReporteDinamicoVO;
import mx.com.teclo.svi.negocio.vo.reporte.ReportesTaqLiteVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PerfilesReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoReportesDTO;

@Service
public class ReporteDinamicoImpl implements ReporteDinamicoIService {
	
	
//	@Autowired
//	private ReporteDinamicoMyBatisDAO reporteDinamicoMyBatis;
//	
//	@Autowired
//    private GeneraExcelService GeneraRptExcel;
//	
//	@Autowired
//	private GeneraExcelInfraccionGral GeneraRptExcelGral;
//	
//	@Autowired
//	private GenerarExcelInfraccArticulos RptExcelArticulos;
//	
//	@Autowired
//	private GeneraExcelInfraccionesDelegaciones RptExcelDeglaciones;
//	
//	@Autowired
//	private GeneraExcelTotalInfraccionesporArticulo generaExcelTotalArticulo;
//    
//	@Autowired
//    private GeneraExcelInfraccionEmpleado infraccionEmpleadoExcel;
	
	private ByteArrayOutputStream  reporte;
	
	@Autowired
	PerfilesReportesDAO perfilesReporteDAO;
	
	@Autowired
	private TipoReportesDAO tipoReporte;
	
	

	@Override
	@Transactional
	public ReporteDinamicoVO obtenerListaReportes(Long idPerfil) throws NotFoundException{
		List<PerfilesReportesDTO> listaReportePerfilDTO = perfilesReporteDAO.ontenerReportesPorPerfil(idPerfil);
		if(listaReportePerfilDTO.isEmpty())
			throw new NotFoundException("No se encontraron registros");
		
		List<TipoReportesDTO> listaTipos = tipoReporte.getAllReports();
		if(listaTipos.isEmpty())
			throw new NotFoundException("No se encontraron tipos de reportes");
		
		ReporteDinamicoVO objectReturn = new ReporteDinamicoVO();
		List<TipoReporteVO> listaTiposVO = new ArrayList<>();
		TipoReporteVO tipoVO = null;
		for(TipoReportesDTO tipoRep : listaTipos){
			tipoVO = new TipoReporteVO();
			ResponseConverter.copiarPropriedades(tipoVO, tipoRep);
			listaTiposVO.add(tipoVO);
		}
		objectReturn.setTipoReporte(listaTiposVO);
		
		List<ReportesTaqLiteVO> listReporteVO = new ArrayList<>();
		ReportesTaqLiteVO vo = null;
		for(PerfilesReportesDTO perfilRep : listaReportePerfilDTO){
			ReportesDTO dto = perfilRep.getReporte();
			vo = new ReportesTaqLiteVO(dto.getIdReporte(),dto.getNbReporte(),dto.getUrl(),dto.getTipoReporte().getIdTipoReporte(),dto.getTxUrlDinamic());
			listReporteVO.add(vo);
		}
		objectReturn.setReportes(listReporteVO);
		return objectReturn;
	}



}
