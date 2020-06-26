package mx.com.teclo.svi.persistencia.hibernate.mybatisDAO;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.svi.negocio.vo.reporte.ReporteVO;

@Mapper
public interface ReporteMyBatisDAO {

	String V_SSP_CONS_REP = "select ip, url_tipo_rpte, url, id_usr_repnet, psw_repnet, reporte "
			+ "from V_SSP_CONS_REP " + " where usu_id = #{id} " + "order by id_reporte ";

	@Select(V_SSP_CONS_REP)
	List<ReporteVO> getListaLinks(@Param("id") Long idEmpleado);

	/**
	 * Descripcin: Mtodo para consultar informacin que estar en el reporte,
	 * recibiendo este como parametro el query que ejecutar
	 * 
	 * @author Jorge Luis
	 * @param String
	 * @return List<HashMap<String, String>>
	 */

	@Select({ "${sql}" })
	public List<LinkedHashMap<Object, Object>> executeQuery(@Param("sql") String sql);

}
