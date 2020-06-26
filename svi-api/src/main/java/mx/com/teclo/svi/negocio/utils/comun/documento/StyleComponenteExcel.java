package mx.com.teclo.svi.negocio.utils.comun.documento;


import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Component;


@Component
public class StyleComponenteExcel {

	public static final String INCORREC_TYPE_FIELD = "Error: incorrect_type";
	public static final short DATA_COLOR = new XSSFColor(Color.BLACK).getIndexed();
	public static final short HEADER_FILL_FOREGROUND_COLOR = new XSSFColor(Color.BLACK).getIndexed();
	public static final short HEADER_COLOR = new XSSFColor(Color.BLACK).getIndexed();
	public static final short BORDER = 1;
	public static final short DATA_FONT_HEIGHT_IN_POINTS = 10;
	public static final short DATA_ALIGNMENT = 1;
	public static final short HEADER_BOLD_WEIGHT = 700;
	public static final short HEADER_FILL_PATTERN = 1;
	public static final short HEADER_FONT_HEIGHT_IN_POINTS = 12;
	public static final short HEADER_ALIGNMENT = 1;

	public static final String COMPANY = "Teclo Mexicana";
	public static final String REPORTNAME = "Reporte Sin Nombre";
	public static final String SHEETNAME = "Hoja ";
	public static final String FILENAME = "Example.xlsx";

	private Cell cell;
	private CellStyle formatDataHeader;
	private Font fontData;
	private CellStyle formatData;
	private Font fontDataHeader;
	private Font fontMainHeader;
	private CellStyle formatMainHeader;
	private Font fontMainHeaderBA;
	private CellStyle formatMainHeaderBA;
	private Font fontHeader;
	private CellStyle formatHeader;

	public StyleComponenteExcel() {
		this.formatDataHeader = null;
		this.fontData = null;
		this.formatData = null;
		this.fontDataHeader = null;
		this.fontMainHeader = null;
		this.formatMainHeader = null;
		this.fontMainHeaderBA = null;
		this.formatMainHeaderBA = null;
		this.fontHeader = null;
		this.formatHeader = null;
	}

	public void formato(Workbook libroExcel) {
		this.fontData = libroExcel.createFont();
		this.fontData.setFontHeightInPoints((short) 10);
		this.fontData.setColor(DATA_COLOR);

		this.formatData = libroExcel.createCellStyle();
		this.formatData.setFont(this.fontData);
		this.formatData.setBorderBottom((short) 1);

		this.fontDataHeader = libroExcel.createFont();
		this.fontDataHeader.setFontHeightInPoints((short) 12);
		this.fontDataHeader.setBoldweight((short) 700);
		this.fontDataHeader.setColor((short) 9);

		this.formatDataHeader = libroExcel.createCellStyle();
		this.formatDataHeader.setFont(this.fontDataHeader);
		this.formatDataHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		this.formatDataHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		this.fontMainHeader = libroExcel.createFont();
		this.fontMainHeader.setFontHeightInPoints((short) 12);

		this.formatMainHeader = libroExcel.createCellStyle();
		this.formatMainHeader.setAlignment((short) 1);
		this.formatMainHeader.setFont(this.fontMainHeader);

		this.fontMainHeaderBA = libroExcel.createFont();
		this.fontMainHeaderBA.setFontHeightInPoints((short) 16);
		this.fontMainHeaderBA.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		this.formatMainHeaderBA = libroExcel.createCellStyle();
		this.formatMainHeaderBA.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		this.formatMainHeaderBA.setFont(this.fontMainHeaderBA);

		this.fontHeader = libroExcel.createFont();
		this.fontHeader.setFontHeightInPoints((short) 10);
		this.fontHeader.setBoldweight((short) 700);
		this.fontHeader.setColor(HEADER_COLOR);

		this.formatHeader = libroExcel.createCellStyle();
		this.formatHeader.setAlignment((short) 1);
		this.formatHeader.setFont(this.fontHeader);
	}

	private Cell formatoEncabezadoPrincipalBA(Workbook libroExcel, Row registro, int posicion, String valor) {
		this.cell = registro.createCell((short) posicion);
		this.cell.setCellValue(valor);
		this.cell.setCellStyle(this.formatMainHeaderBA);
		return this.cell;
	}

	private Cell formatoEncabezado(Workbook libroExcel, Row registro, int posicion, String valor) {
		this.cell = registro.createCell((short) posicion);
		this.cell.setCellValue(valor);
		this.fontHeader.setItalic(true);
		this.formatHeader.setFont(this.fontHeader);
		this.cell.setCellStyle(this.formatHeader);
		this.fontHeader.setItalic(false);
		this.formatHeader.setFont(this.fontHeader);
		return this.cell;
	}

	private Cell formatoEncabezadoDatos(Workbook libroExcel, Row registro, int posicion, String valor) {
		this.cell = registro.createCell((short) posicion);
		this.cell.setCellValue(valor);
		this.cell.setCellStyle(this.formatDataHeader);
		return this.cell;
	}

	private Cell formatoDatos(Workbook libroExcel, Row registro, int posicion, String valor) {
		this.cell = registro.createCell((short) posicion);
		this.cell.setCellValue(valor);
		this.cell.setCellStyle(this.formatData);
		return this.cell;
	}

	public void generarHojas(Workbook libroExcel, PeticioReporteVO voPeticion) {
		// numero de hojas en las que se dividira el resultado
		int noHojas = (voPeticion.getPropiedadesReporte().getNuHojas() != 0)
				? voPeticion.getPropiedadesReporte().getNuHojas() : 1;

		int sizeDataReport = voPeticion.getContenido().size();
		int inicioIndice = 0;
		int finalIndice = sizeDataReport;// por default se toma todo el tamaño
											// del datareport
		int filasXhoja = sizeDataReport / noHojas;
		// int indiceHoja=1;//por default inicia con 1
		boolean multipleHoja = (noHojas > 1) ? true : false;
		for (int indiceHoja = 1; indiceHoja <= noHojas; indiceHoja++) {
			/*
			 * Semaphore sem=new Semaphore(1); sem.acquire();
			 */
			// verificar si es la ultima hoja es le asignan las filas restantes
			// para el caso de que se encuentren impares
			// -1 para evitar nullpointerexception para la ultima hoja
			finalIndice = indiceHoja == noHojas ? voPeticion.getContenido().size() - 1 : (filasXhoja * indiceHoja) - 1;// filasXhoja-1
																														// porque
																														// inicia
																														// el
																														// indice
																														// desde
																														// 0
			crearHoja(libroExcel, "" + indiceHoja, inicioIndice, finalIndice, voPeticion, multipleHoja);
			// ASIGNAR NUEVO INDICE DE INICIO
			inicioIndice = finalIndice + 1;
			// sem.release();
		}
	}

	/**
	 * CREAR UNA NUEVA HOJA AL LIBRO EXCEL, EL FORMATO DE LA HOJA ES EL MISMO
	 * PARA TODAS NOTA: cambiar response.setContentType =
	 * application/vnd.ms-excel, para que se genere en formato .xls
	 * 
	 */
	@SuppressWarnings("unchecked")
	public synchronized void crearHoja(Workbook libroExcel, String indexHoja, int initFila, int finalFila,
			PeticioReporteVO voPeticion, boolean multipleHoja) {
		// INICIO DE ELABORACION DE LA HOJA EXCEL
		int numRenglon = 1;
		long nuRegistros = (long) voPeticion.getContenido().size();
		String multiple = null;

		String titHoja = (voPeticion.getPropiedadesReporte().getNbHoja()) != null
				? voPeticion.getPropiedadesReporte().getNbHoja() : SHEETNAME;
		Sheet hoja = libroExcel.createSheet(titHoja + " " + indexHoja);

		// INSERCCION DE REGISTROS
		Row registro = hoja.createRow(numRenglon);
		registro = hoja.createRow(numRenglon);
		++numRenglon;

		// COLOCA TITULOS
		CellRangeAddress merge = new CellRangeAddress(1, 1, 1, voPeticion.getEncabezado().size());
		hoja.addMergedRegion(merge);
		String tituloComp = voPeticion.getPropiedadesReporte().getNbReporte() != null
				? voPeticion.getPropiedadesReporte().getNbReporte() : REPORTNAME;
				this.formatoEncabezadoPrincipalBA(libroExcel, registro, 1, tituloComp);
		registro = hoja.createRow(numRenglon);
		numRenglon++;
		merge = new CellRangeAddress(2, 2, 1, voPeticion.getEncabezado().size());
		hoja.addMergedRegion(merge);

		if (voPeticion.getPropiedadesReporte().getSubtitulos() != null
				&& voPeticion.getPropiedadesReporte().getSubtitulos() instanceof List<?>) {
			List<String> fields = voPeticion.getPropiedadesReporte().getSubtitulos();
			for (int i = 0; i < fields.size(); i++) {
				registro = hoja.createRow(numRenglon);
				numRenglon++;
				merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 10);
				hoja.addMergedRegion(merge);
				this.formatoEncabezado(libroExcel, registro, 1,
						(fields.get(i) instanceof String ? (String) fields.get(i) : INCORREC_TYPE_FIELD));
			}
		}

		registro = hoja.createRow(numRenglon);
		numRenglon++;
		merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 2);
		hoja.addMergedRegion(merge);
		this.formatoEncabezado(libroExcel, registro, 1, "Total de Registros: " + nuRegistros);
		registro = hoja.createRow(numRenglon);
		numRenglon++;

		if (multipleHoja) {
			int inicio = initFila + 1;
			int fin = finalFila + 1;
			multiple = "Registro de " + inicio + " a " + fin;
			registro = hoja.createRow(numRenglon);
			numRenglon++;
			merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 2);
			hoja.addMergedRegion(merge);
			this.formatoEncabezado(libroExcel, registro, 1, multiple);
			registro = hoja.createRow(numRenglon);
			numRenglon++;
		}
		registro = hoja.createRow(numRenglon);
		//
		numRenglon++;
		// COLOCAR NOMBRE DE LAS COLUMNAS
		for (int i = 0; i < voPeticion.getEncabezado().size(); ++i) {
			String atributosColumna = (String) voPeticion.getEncabezado().get(i);
			this.formatoEncabezadoDatos(libroExcel, registro, i + 1, atributosColumna);
			hoja.setColumnWidth((short) (i + 1), 7000);
		}
		// COLOCAR DATOS
		// VALIDAR LOS INDICES PARA EVITAR UN ERROR DE NULLPOINTER EXCEPTION
		// cambiar LOS INDICES PARA LAS HOJAS
		List<Object> datosColumn = null;
		for (int i = initFila; i <= finalFila; ++i) {
			registro = hoja.createRow(numRenglon);
			++numRenglon;
			datosColumn = (List<Object>) voPeticion.getContenido().get(i);
			for (int j = 0; j < datosColumn.size(); ++j) {
				String valor = datosColumn.get(j) != null ? datosColumn.get(j).toString() : "";
				this.formatoDatos(libroExcel, registro, j + 1, valor);
			}
		}
	}

	public void generarHojasPaginable(Workbook libroExcel, PeticioReporteVO voPeticion) {
		// numero de hojas en las que se dividira el resultado
		int noHojas = voPeticion.getContenido().size();
		int numContenido = 0;
		for (int indiceHoja = 1; indiceHoja <= noHojas; indiceHoja++) {
			crearHojaPaginable(libroExcel, "" + indiceHoja, voPeticion, numContenido);
			numContenido++;
		}
	}

	/**
	 * CREAR UNA NUEVA HOJA AL LIBRO EXCEL, EL FORMATO DE LA HOJA ES EL MISMO
	 * PARA TODAS NOTA: cambiar response.setContentType =
	 * application/vnd.ms-excel, para que se genere en formato .xls
	 * 
	 */
	@SuppressWarnings("unchecked")
	public synchronized void crearHojaPaginable(Workbook libroExcel, String indexHoja, PeticioReporteVO voPeticion,
			int numContenido) {
		// INICIO DE ELABORACION DE LA HOJA EXCEL
		// key = columna por la que se va paginas
		// value = registros asociados a la columna por la que se va paginar
		String filtroPor = voPeticion.getPropiedadesReporte().getTxColumnaPaginacion();
		Map.Entry<Object, List<Object>> mapElement = (Map.Entry<Object, List<Object>>) voPeticion.getContenido()
				.get(numContenido);
		int numRenglon = 1;
		Object key = null;
		List<?> listValues = null;
		long nuRegistros = 0L;

		key = mapElement.getKey();
		listValues = mapElement.getValue();
		nuRegistros = listValues.size();

		String titHoja = WorkbookUtil.createSafeSheetName((String) (key != null ? key : "Valores Vacíos"));
		Sheet hoja = libroExcel.createSheet(titHoja + " " + indexHoja);

		// INSERCCION DE REGISTROS
		Row registro = hoja.createRow(numRenglon);
		registro = hoja.createRow(numRenglon);
		++numRenglon;

		CellRangeAddress merge = new CellRangeAddress(1, 1, 1, voPeticion.getEncabezado().size());
		hoja.addMergedRegion(merge);
		String tituloComp = voPeticion.getPropiedadesReporte().getNbReporte() != null
				? voPeticion.getPropiedadesReporte().getNbReporte() : REPORTNAME;
		this.formatoEncabezadoPrincipalBA(libroExcel, registro, 1, tituloComp);
		registro = hoja.createRow(numRenglon);
		numRenglon++;
		// merge = new CellRangeAddress(2, 2, 1,
		// voPeticion.getEncabezado().size());
		// hoja.addMergedRegion(merge);

		if (voPeticion.getPropiedadesReporte().getSubtitulos() != null
				&& voPeticion.getPropiedadesReporte().getSubtitulos() instanceof List<?>) {
			List<String> fields = voPeticion.getPropiedadesReporte().getSubtitulos();
			for (int i = 0; i < fields.size(); i++) {
				registro = hoja.createRow(numRenglon);
				numRenglon++;
				merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 2);
				hoja.addMergedRegion(merge);
				this.formatoEncabezado(libroExcel, registro, 1,
						(fields.get(i) instanceof String ? (String) fields.get(i) : INCORREC_TYPE_FIELD));
			}
		}

		registro = hoja.createRow(numRenglon);
		numRenglon++;
		merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 2);
		hoja.addMergedRegion(merge);
		this.formatoEncabezado(libroExcel, registro, 1, "Hoja por " + filtroPor + ": " + key);
		registro = hoja.createRow(numRenglon);
		numRenglon++;

		registro = hoja.createRow(numRenglon);
		numRenglon++;
		merge = new CellRangeAddress(numRenglon - 1, numRenglon - 1, 1, 2);
		hoja.addMergedRegion(merge);
		this.formatoEncabezado(libroExcel, registro, 1, "Total de Registros: " + nuRegistros);
		registro = hoja.createRow(numRenglon);
		numRenglon++;

		registro = hoja.createRow(numRenglon);
		numRenglon++;
		// COLOCAR NOMBRE DE LAS COLUMNAS
		for (int i = 0; i < voPeticion.getEncabezado().size(); ++i) {
			String atributosColumna = (String) voPeticion.getEncabezado().get(i);
			this.formatoEncabezadoDatos(libroExcel, registro, i + 1, atributosColumna);
			hoja.setColumnWidth((short) (i + 1), 7000);
		}
		// COLOCAR DATOS
		// VALIDAR LOS INDICES PARA EVITAR UN ERROR DE NULLPOINTER EXCEPTION
		// cambiar LOS INDICES PARA LAS HOJAS
		List<Object> datosColumn = null;
		for (int i = 0; i < listValues.size(); ++i) {
			registro = hoja.createRow(numRenglon);
			++numRenglon;
			datosColumn = (List<Object>) listValues.get(i);
			for (int j = 0; j < datosColumn.size(); ++j) {
				String valor = datosColumn.get(j) != null ? datosColumn.get(j).toString() : "";
				this.formatoDatos(libroExcel, registro, j + 1, valor);
			}
		}
	}

}
