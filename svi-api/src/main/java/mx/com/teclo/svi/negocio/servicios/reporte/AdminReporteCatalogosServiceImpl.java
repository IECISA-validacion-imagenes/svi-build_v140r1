package mx.com.teclo.svi.negocio.servicios.reporte;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionAllCatalogosVO;
import mx.com.teclo.svi.negocio.vo.reporte.AgrupacionHojasVO;
import mx.com.teclo.svi.negocio.vo.reporte.ColumnasVO;
import mx.com.teclo.svi.negocio.vo.reporte.ComponentesVO;
import mx.com.teclo.svi.negocio.vo.reporte.FormatoDescargaVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.PropiedadesVO;
import mx.com.teclo.svi.negocio.vo.reporte.TablasVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoOperadorVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParamCompVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoParametroVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoReporteVO;
import mx.com.teclo.svi.negocio.vo.reporte.TipoTituloVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.CaracteresVO;
import mx.com.teclo.svi.negocio.vo.reporte.reportesalta.TipoOrdenamientoVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.AgrupacionHojasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.CaracteresDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ColumnasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.ComponenteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.FormatoDescargaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PropiedadesCompDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.PropiedadesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TablasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoOperadorDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoParamCompDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoParametroDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoReportesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.TipoTitulosDAO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.AgrupacionHojasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.CaracteresDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesCompDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PropiedadesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TablasDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoOperadorDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParamCompDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoParametroDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.TipoTitulosDTO;

@Service
public class AdminReporteCatalogosServiceImpl implements AdminReporteCatalogosService{

	@Autowired
	private TablasDAO tablasDAO;
	
	@Autowired
	private TipoReportesDAO tipoReporteDAO;

	@Autowired
	private FormatoDescargaDAO formatoDescargaDAO;

	@Autowired
	private ComponenteDAO componenteDAO;

	@Autowired
	private TipoParametroDAO tipoParametroDAO;

	@Autowired
	private PropiedadesDAO propiedadesDAO;

	@Autowired
	private TipoTitulosDAO tipoTituloDAO;

	@Autowired
	private ColumnasDAO columnasDAO;
	
	@Autowired
	private TipoOperadorDAO tipoOperadorDAO;
	
	@Autowired
	private CaracteresDAO caracteresDAO;
	
	@Autowired
	private AgrupacionHojasDAO agrupacionHojasDAO;
	
	@Autowired
	private TipoParamCompDAO tipoParamDAO;
	
	@Autowired
	private PropiedadesCompDAO propiedadComponenteDAO;

	@Value("${app.config.codigo}")
	private String cdApp;

	@Transactional
	@Override
	public AgrupacionAllCatalogosVO getAllCatalogosVO() {
		AgrupacionAllCatalogosVO voReturn = new AgrupacionAllCatalogosVO();
		
		List<AgrupacionHojasDTO> agrupacionHojasDTO = agrupacionHojasDAO.getReportesFormato();
		List<AgrupacionHojasVO> listVOReturn = new ArrayList<>();
		if (!agrupacionHojasDTO.isEmpty())
			listVOReturn = ResponseConverter.converterLista(new ArrayList<>(), agrupacionHojasDTO,AgrupacionHojasVO.class);
		voReturn.setAgrupacionHojasVO(listVOReturn);
		
		List<TipoTitulosDTO> listTipoTituloDTO = tipoTituloDAO.listaTipoTitulo();
		List<TipoTituloVO> listTipoTituloVO = new ArrayList<>();
		if (!listTipoTituloDTO.isEmpty())
			listTipoTituloVO = ResponseConverter.converterLista(new ArrayList<>(), listTipoTituloDTO,TipoTituloVO.class);
		voReturn.setTipoTituloVO(listTipoTituloVO);
		
		voReturn.setTipoReporteVO(obtenerListaTipoReporte());
		
		List<FormatoDescargaDTO> formatoDescargaDTO = formatoDescargaDAO.listaFormatoDescarga();
		List<FormatoDescargaVO> listaFormatoVO = new ArrayList<>();
		if (!formatoDescargaDTO.isEmpty())
			listaFormatoVO = ResponseConverter.converterLista(new ArrayList<>(), formatoDescargaDTO, FormatoDescargaVO.class);
		voReturn.setFormatoDescargaVO(listaFormatoVO);
		
		List<TipoParametroDTO> tipoParametroDTO = tipoParametroDAO.listaTiposParametro();
		List<TipoParametroVO> listParametroVO = new ArrayList<>();
		if (!tipoParametroDTO.isEmpty())
			listParametroVO = ResponseConverter.converterLista(new ArrayList<>(), tipoParametroDTO,TipoParametroVO.class);
		voReturn.setTipoParametroVO(listParametroVO);
		
		List<TipoParamCompDTO> tipoParamCompDTO = tipoParamDAO.getRelacionParamComponen();
		List<TipoParamCompVO> listaReturn = new ArrayList<>();
		if (!tipoParamCompDTO.isEmpty()) {
			TipoParamCompVO vo = null;
			for (TipoParamCompDTO dto : tipoParamCompDTO) {
				vo = new TipoParamCompVO(dto.getIdTipoParamComp(), dto.getTipoParametro().getIdTipoParametro(),dto.getComponente().getIdComponente());
				listaReturn.add(vo);
			}
		}
		voReturn.setTipoParamCompVO(listaReturn);
		
		List<ComponentesDTO> componentesDTO = componenteDAO.listaComponentes();
		List<ComponentesVO> componentesVO = new ArrayList<>();
		if (!componentesDTO.isEmpty())
			componentesVO = ResponseConverter.converterLista(new ArrayList<>(), componentesDTO,ComponentesVO.class);
		voReturn.setComponentesVO(componentesVO);
		
		List<PropiedadesDTO> propiedadesDTO = propiedadesDAO.listaPropiedades();
		List<PropiedadesVO> propiedadesVO = new ArrayList<>();
		if (!propiedadesDTO.isEmpty())
			propiedadesVO = ResponseConverter.converterLista(new ArrayList<>(), propiedadesDTO,PropiedadesVO.class);
		voReturn.setPropiedadesVO(propiedadesVO);
		
		
		List<PropiedadesCompDTO> propiedadesCompDTO = propiedadComponenteDAO.getComponentesPropiedad();
		List<PropiedadesCompVO> propiedadesCompVO = new ArrayList<>();
		if (!propiedadesCompDTO.isEmpty()) {
			PropiedadesCompVO vo = null;
			for (PropiedadesCompDTO dto : propiedadesCompDTO) {
				vo = new PropiedadesCompVO(dto.getIdPropiedadComp(), dto.getPropiedad().getIdPropiedad(),dto.getComponente().getIdComponente());
				propiedadesCompVO.add(vo);
			}
		}
		voReturn.setPropiedadesCompVO(propiedadesCompVO);
		
		List<TablasDTO> tablasDTO = tablasDAO.getCatalogoTablas();
		List<TablasVO> tablasVO = new ArrayList<>();
		if(!tablasDTO.isEmpty())
			tablasVO = ResponseConverter.converterLista(new ArrayList<>(), tablasDTO,TablasVO.class);
		voReturn.setTablasVO(tablasVO);
		
		List<ColumnasDTO> columnasDTO = columnasDAO.getCatalogoColumnas();
		List<ColumnasVO> columnasVO = new ArrayList<>();
		ColumnasVO vo = null;
		if(!columnasDTO.isEmpty())
			for(ColumnasDTO dto : columnasDTO){
				vo = new ColumnasVO(dto.getIdColumna(),dto.getNbAlias(),dto.getNbReal(),dto.getTabla().getIdTabla());
				columnasVO.add(vo);
			}
		voReturn.setColumnasVO(columnasVO);
		
		List<TipoOperadorDTO> tipoOperadorDTO = tipoOperadorDAO.getCatTipoOperador();
		List<TipoOperadorVO> tipoOperadorVO = new ArrayList<>();
		if (!tipoOperadorDTO.isEmpty())
			tipoOperadorVO = ResponseConverter.converterLista(new ArrayList<>(), tipoOperadorDTO,TipoOperadorVO.class);
		voReturn.setTipoOperadorVO(tipoOperadorVO);
		
		List<TipoOrdenamientoVO> tipoOrdenamientoVO = new ArrayList<>();
		tipoOrdenamientoVO.add(new TipoOrdenamientoVO(1,"Ascendente - ASC","ASC"));
		tipoOrdenamientoVO.add(new TipoOrdenamientoVO(2,"Descendente - DESC","DESC"));
		
		voReturn.setTipoOrdenamientoVO(tipoOrdenamientoVO);
		
		List<CaracteresDTO> caracteresDTO = caracteresDAO.getCaracteresSeperacion();
		List<CaracteresVO> caracteresVO = new ArrayList<>();
		if(!caracteresDTO.isEmpty())
			caracteresVO = ResponseConverter.converterLista(new ArrayList<>(), caracteresDTO,CaracteresVO.class);
		voReturn.setCaracteresVO(caracteresVO);
		
		return voReturn;
	}
	
	@Override
	@Transactional
	public List<TipoReporteVO> obtenerListaTipoReporte() {
		List<TipoReportesDTO> listaDTO = tipoReporteDAO.listaTipoReporte();
		List<TipoReporteVO> listaTipoReportes = new ArrayList<>();
		if (!listaDTO.isEmpty())
			listaTipoReportes = ResponseConverter.converterLista(new ArrayList<>(), listaDTO, TipoReporteVO.class);
		return listaTipoReportes;
	}

	
}
