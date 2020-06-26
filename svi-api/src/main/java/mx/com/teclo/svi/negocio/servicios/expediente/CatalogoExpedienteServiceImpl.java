package mx.com.teclo.svi.negocio.servicios.expediente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svi.negocio.vo.catalogo.CsvExpedienteVO;
import mx.com.teclo.svi.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.CatalogoIncidenciasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtEtiquetasRevalDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtMarcasDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtPerfilesDAO;
import mx.com.teclo.svi.persistencia.hibernate.dao.PtSubmarcasDAO;

@Service
public class CatalogoExpedienteServiceImpl implements CatalogoExpedienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoExpedienteService.class);

	@Autowired
	private ArchivoCsvDAO archivoCsvDAO;
	@Autowired
	private EntregaDAO entregaDAO;
	@Autowired
	private LoteDAO loteDAO;
	@Autowired
	private PtMarcasDAO ptMarcasDAO;
	@Autowired
	private PtSubmarcasDAO ptSubmarcasDAO;
	@Autowired
	private PtPerfilesDAO ptPerfilesDAO;
	@Autowired
	private CatalogoIncidenciasDAO catIncidenciasDAO;
	
	@Autowired
	private PtEtiquetasRevalDAO etiquetasDAO;

	@Override
	@Transactional(readOnly = true)
	public List<CsvExpedienteVO> getCatCsv() {
		return archivoCsvDAO.getAllArchivosCsv();
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getCatalogos() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("catPT", loteDAO.buscarCatTodosPuntosTacticos());
		res.put("catCSV", archivoCsvDAO.buscarCatTodosArchivos());
		res.put("catPeriodo", entregaDAO.catalogoPeriodos());
		res.put("catMarca", ptMarcasDAO.getCatMarcas());
		res.put("catSubMarca", ptSubmarcasDAO.getCatSubMarcas());
		res.put("catPerfil", ptPerfilesDAO.getCatPerfiles());
		res.put("catPerfilDistinct", ptPerfilesDAO.getCatPerfilesDistinct());
		res.put("catIncidencias", catIncidenciasDAO.getCatIncidencias());
		res.put("catEtiquetas", etiquetasDAO.getAllEtiquetas());
		return res;
	}
}
