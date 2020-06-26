package mx.com.teclo.svi.negocio.utils.comun;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RutinasTiempoImpl {
	
	/**
	 * @author genunt
	 * @param format String
	 * @param fecha Date
	 * @return String
	 */
	public String getStringDateFromFormta(String format,Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}

	/**
	 * @author genunt
	 * @param stringDate String 
	 * @return Date dd/MM/yyyy
	 */
	public Date convertirStringDate(String stringDate, String formato){
		SimpleDateFormat format = new SimpleDateFormat(formato);
		Date fecha = new Date();
		
		try {
			fecha = format.parse(stringDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fecha;
	}
		
//	/**
//	 * @author genunt
//	 * @return List<Integer>
//	 */
//	public List<ComboValuesVO> obtenerAnioactualAniosAnterires( Date fecha, Integer numeroAnos){
////		recibee Date fecha, Integer numeroAnos
//		List<ComboValuesVO> listaComboValuesVO = new ArrayList<ComboValuesVO>();
//		ComboValuesVO comboValuesVO = new ComboValuesVO();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(fecha);
//		
//		comboValuesVO.setDescripcion(""+calendar.get(Calendar.YEAR));
//		comboValuesVO.setValor(""+calendar.get(Calendar.YEAR));
//		listaComboValuesVO.add(comboValuesVO);
//		
//		for (int i = 1; i <= numeroAnos; i++) {
//			calendar.add(Calendar.YEAR, -1);
//			comboValuesVO = new ComboValuesVO();
//			comboValuesVO.setDescripcion(""+calendar.get(Calendar.YEAR));
//			comboValuesVO.setValor(""+calendar.get(Calendar.YEAR));
//			listaComboValuesVO.add(comboValuesVO);
//		}
//		
//		return listaComboValuesVO;
////		List<ComboValuesVO> listaComboValuesVO = new ArrayList<ComboValuesVO>();
////		Date date = new Date();
////		SimpleDateFormat custom = new SimpleDateFormat("yyyy");
////		String fecha = custom.format(date);
////		List<Integer> anios = new ArrayList<Integer>();
////		anios.add(Integer.parseInt(fecha));
////		anios.add(Integer.parseInt(fecha) - 1);
////		anios.add(Integer.parseInt(fecha) - 2);
////		return listaComboValuesVO;
//	}
//	
////	public List<ComboValuesVO> obtenerFechasSalarios() {
////		List<ComboValuesVO> listaComboValuesVO = new ArrayList<ComboValuesVO>();
////		Date date = new Date();
////		SimpleDateFormat custom = new SimpleDateFormat("yyyy");
////		String fecha = custom.format(date);
////		List<Integer> anios = new ArrayList<Integer>();
////		anios.add(Integer.parseInt(fecha));
////		anios.add(Integer.parseInt(fecha) - 1);
////		anios.add(Integer.parseInt(fecha) - 2);
////		return listaComboValuesVO;
////	}
	
	/**
	 * @author genunt
	 * @param fecha
	 * @param dias
	 * @return Date
	 */
	public Date incrementarNumeroDias(Date fecha, Integer dias){
		List<Integer> listaAno = new ArrayList<Integer>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		listaAno.add(calendar.get(Calendar.YEAR));
		
		for (int i = 1; i <= dias; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, i);
		}	
		
		return calendar.getTime();
	}
		
	/**
	 * @author genunt
	 * @param fecha Date
	 * @return Map<String, String>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> obtenerFechaDesglosada(Date fecha){
		Map listaFecha = new HashMap<String, String>();
		String result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		switch(month){
		  case 0:
		    {
		      result="Enero";
		      break;
		    }
		  case 1:
		    {
		      result="Febrero";
		      break;
		    }
		  case 2:
		    {
		      result="Marzo";
		      break;
		    }
		  case 3:
		    {
		      result="Abril";
		      break;
		    }
		  case 4:
		    {
		      result="Mayo";
		      break;
		    }
		  case 5:
		    {
		      result="Junio";
		      break;
		    }
		  case 6:
		    {
		      result="Julio";
		      break;
		    }
		  case 7:
		    {
		      result="Agosto";
		      break;
		    }
		  case 8:
		    {
		      result="Septiembre";
		      break;
		    }
		  case 9:
		    {
		      result="Octubre";
		      break;
		    }
		  case 10:
		    {
		      result="Noviembre";
		      break;
		    }
		  case 11:
		    {
		      result="Diciembre";
		      break;
		    }
		  default:
		    {
		      result="Error";
		      break;
		    }
		}
		
		listaFecha.put("day", day);
		listaFecha.put("month", result);
		listaFecha.put("year", year);
		
		return listaFecha;
	}
	
	/**
	 * @author genunt
	 * @param fecha String yyyyMMdd
	 * @return Date dd/MM/yyyy
	 */
	public Date getFechaStringDate(String fecha){
		String ano = fecha.substring(0, 4);
		String mes = fecha.substring(4, 6);
		String dia = fecha.substring(6, 8);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		try {
			date = format.parse(dia + "/" + mes + "/" + ano);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return date;
	}
	
	/**
	 * @author genunt
	 * @return String 
	 */
	public String getStringFechaDDMMYYYHHMMSS (){
        Calendar fecha = Calendar.getInstance();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
        return dia + "-" + mes + "-" + ano + "-" + hora + "-" + minuto + "-" + segundo;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param fecha Date
	 * @return String anio
	 */
	public String obtenerAnioFecha(Date fecha){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		Integer year = calendar.get(Calendar.YEAR);
		
		return year.toString();
	}
	
	/**
	 * Regresa la fecha actual en formato dd/MM/yyyy
	 * @author Fjmb
	 * @param fecha Date
	 * @return String anio
	 */
	public Date getFechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formateador.format(fechaActual);
        try {
        	fechaActual =  formateador.parse(fecha);
		} catch (ParseException e) {}
        return fechaActual;
    }
	
	
	/**
	 * @author javier07
	 * @param file File
	 * @return Date
	 */
	public Date obtenrFechaModificacionArchivo(File file){
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("America/Mexico_City"));
        calendar.setTimeInMillis(file.lastModified());
        
        Date fecha = calendar.getTime();
        String fechaArchivo = formateador.format(fecha);
        try {
        	fecha =  formateador.parse(fechaArchivo);
		} catch (ParseException e) {}
        return fecha;
	}
	
	/**
	 * @author genunt
	 * @param stringDate String 
	 * @return Date dd/MM/yyyy
	 */
	public Date convertirStringDate(String stringDate){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		
		try {
			fecha = format.parse(stringDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fecha;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public String getFecha_ddMMYYYY_hhmmss(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateString=sdf.format(date);
		return dateString;
	}
	
	/**
	 * @author Kevin Ojeda
	 * @return Integer
	 */
	public Integer diferenciaDeDiasEntreFechas(Date Finicial, Date Ffinal){
		long today = Finicial.getTime();
		long lastDate = Ffinal.getTime() ;
		if(lastDate<today)
			return -1;
		long diffTime = lastDate - today;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return ((int) diffDays);
	}
	
	/**
	 * Obtiene el año en dos digitos 2017 -> 17
	 * @author Jorge Luis
	 * @return Stirng
	 * */
	public String getAnioEnDosDigistos(Date f){
		DateFormat df = new SimpleDateFormat("YY");
		String formattedDate = df.format(f.getTime());
		return formattedDate;
	}

	
	/**
	 * Metodo que recibe el numero del mes y retorna el nombre 
	 * @author Javier Flores
	 * @param mes
	 * @return String
	 */
	public String getNombreMes(int mes){
		
		String nombreMes;	
		
		switch(mes-1){
		  case 0:
		    {
		      nombreMes="Enero";
		      break;
		    }
		  case 1:
		    {
		      nombreMes="Febrero";
		      break;
		    }
		  case 2:
		    {
		      nombreMes="Marzo";
		      break;
		    }
		  case 3:
		    {
		      nombreMes="Abril";
		      break;
		    }
		  case 4:
		    {
		      nombreMes="Mayo";
		      break;
		    }
		  case 5:
		    {
		      nombreMes="Junio";
		      break;
		    }
		  case 6:
		    {
		      nombreMes="Julio";
		      break;
		    }
		  case 7:
		    {
		      nombreMes="Agosto";
		      break;
		    }
		  case 8:
		    {
		      nombreMes="Septiembre";
		      break;
		    }
		  case 9:
		    {
		      nombreMes="Octubre";
		      break;
		    }
		  case 10:
		    {
		      nombreMes="Noviembre";
		      break;
		    }
		  case 11:
		    {
		      nombreMes="Diciembre";
		      break;
		    }
		  default:
		    {
		      nombreMes="Error";
		      break;
		    }
		}//fin case
		
		return nombreMes;
	}
	
	/**
	 * Metodo que recibe el numero y año y devuelbe el total de dias de ese mes 
	 * @author Fernando Octavio
	 * @param mes 
	 * @param anio
	 * @return String
	 */
	public int numeroDiasXMesyAnio(int anio,int mes){
		Calendar fecha = Calendar.getInstance();
		fecha.set(anio, mes, 0);
		int totalDias = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
		return totalDias;
	}
	
	/**
	 * Metodo que devuelve la fecha actual eb formato dd/MM/YYYY hh:mm:ss
	 * @author Fernando Octavio
	 * @return String
	 */
	
	public String getFechaActualFO(){
		String fechaActual="";
		Date myDate = new Date();
		fechaActual = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(myDate);
		return fechaActual;
	}
	
	/**
	 * Metodo que devuelve la fecha actual eb formato dd/MM/YYYY hh:mm:ss
	 * @author Fernando Octavio
	 * @return Date
	 */
	public Date getFechaActualFormatDate(){
		Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha = formateador.format(fechaActual);
        try {
        	fechaActual =  formateador.parse(fecha);
		} catch (ParseException e) {}
        return fechaActual;
	}
	
}
