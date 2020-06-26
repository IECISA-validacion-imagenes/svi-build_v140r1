package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import org.hibernate.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAOImpl;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleEvaDTO;


@SuppressWarnings("unchecked")
@Repository("archivoDetalleEvaDAO")
public class ArchivoDetalleEvaDAOImpl extends BaseDAOImpl<ArchivoDetalleEvaDTO>implements ArchivoDetalleEvaDAO {

	@Override
	@Transactional
	public void guardarDetalleEva(ArchivoDetalleEvaDTO elementoEVA) {
		getCurrentSession().save(elementoEVA);
		getCurrentSession().flush();	
	}

	@Override
	@Transactional
	public int getUltimoRegistro(Long idArchivoCSV) {
		StringBuilder consulta = new StringBuilder(" SELECT COUNT(*) AS ultimo FROM TCI005D_PT_ARCHIVO_DETALLE_EVA " 
				+ "  WHERE ID_ARCHIVO_CSV ="+ idArchivoCSV);
		Query execucion = getCurrentSession().createSQLQuery(consulta.toString()).addScalar("ultimo",IntegerType.INSTANCE);
		int ultimoRegistro = (int) execucion.uniqueResult();
		return ultimoRegistro;
	}


}
