package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAOImpl;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoDetalleDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository("archivoDetalleDAO")
public class ArchivoDetalleDAOImpl extends BaseDAOImpl<ArchivoDetalleDTO> implements ArchivoDetalleDAO {


	@Override
	@Transactional
	public void cargarDetalleCSVDTO(ArchivoDetalleDTO detalleCSV) {
		getCurrentSession().save(detalleCSV);
		getCurrentSession().flush();
	}



	@Override
	@Transactional
	public int getUltimoRegistro(Long idArchivoCSV) {
		StringBuilder consulta = new StringBuilder(" SELECT COUNT(*) AS ultimo FROM TCI004D_PT_ARCHIVO_DETALLE " 
				+ "  WHERE ID_ARCHIVO_CSV ="+ idArchivoCSV);
		Query execucion = getCurrentSession().createSQLQuery(consulta.toString()).addScalar("ultimo",IntegerType.INSTANCE);
		int ultimoRegistro = (int) execucion.uniqueResult();
		return ultimoRegistro;
	}

	@Override
	@Transactional
	public void updateOrGuardar(ArchivoDetalleDTO detalleCSV) {
		getCurrentSession().saveOrUpdate(detalleCSV);
		getCurrentSession().flush();
	}



	@Override
	@Transactional
	public ArchivoDetalleDTO getDTObyNumExpediente(String expediente,Long ArchivoCSV) {
		Criteria c = getCurrentSession().createCriteria(ArchivoDetalleDTO.class);
		c.add(Restrictions.eq("nuExpediente", expediente));
		c.createAlias("idArchivoCsv","CSV");
		c.add(Restrictions.eq("CSV.idArchivoCsv", ArchivoCSV));
		return (ArchivoDetalleDTO)c.uniqueResult();
	}
	
	

}
