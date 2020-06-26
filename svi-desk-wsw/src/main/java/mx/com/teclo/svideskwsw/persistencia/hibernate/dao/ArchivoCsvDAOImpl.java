package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAOImpl;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoCsvDTO;


@SuppressWarnings("unchecked")
@Repository("archivoCsvDAO")
public class ArchivoCsvDAOImpl extends BaseDAOImpl<ArchivoCsvDTO>implements ArchivoCsvDAO {
	
	@Override
	public List<ArchivoCsvDTO> obtenerArchivoPTSCV() {
	 Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
	 c.add(Restrictions.eq("nuRegistrosCsv",0L));		
		//List<ArchivoCsvDTO> detCargoslist = (List<ArchivoCsvDTO>) c.list();
		//List<DetalleCargosDTO> detCargoslist = (List<DetalleCargosDTO>) detPago.list();
		return c.list();
	}


	@Override
	@Transactional
	public void editarDetalleTCI003D(ArchivoCsvDTO elemento) {
		getCurrentSession().update(elemento);
		getCurrentSession().flush();
	}

	@Override
	@Transactional
	public ArchivoCsvDTO consultarArchivoPT(Long IdArchivoSCV, Long IdLotePT) {
		Criteria criteria = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		criteria.add(Restrictions.eq("idArchivoCsv",IdArchivoSCV));
		criteria.createAlias("idPtLote","lotePT");
		criteria.add(Restrictions.eq("lotePT.idPtLote",IdLotePT ));
		// TODO Auto-generated method stub
		return (ArchivoCsvDTO) criteria.uniqueResult();
	}


	@Override
	@Transactional
	public void guardarDTO(ArchivoCsvDTO DTO) {
		getCurrentSession().save(DTO);
		getCurrentSession().flush();
	}


	@Override
	@Transactional
	public Long getIdCSVxIdPTyNomCSV(Long IdPtLote, String nbArchivoCSV) {
		StringBuilder consulta = new StringBuilder("SELECT ID_ARCHIVO_CSV AS id FROM TCI003D_PT_ARCHIVO_CSV "
				+"  WHERE ID_PT_LOTE="+ IdPtLote +" AND NB_ARCHIVO_CSV='"+nbArchivoCSV+"'");
		Query execucion = getCurrentSession().createSQLQuery(consulta.toString()).addScalar("id",LongType.INSTANCE);
		Long idArchivoCSV = (Long) execucion.uniqueResult();
		return idArchivoCSV;
	}

	@Override
	@Transactional
	public ArchivoCsvDTO BuscarDTOxId(Long IdPtLote) {
		 Criteria c = getCurrentSession().createCriteria(ArchivoCsvDTO.class);
		 c.add(Restrictions.eq("idArchivoCsv",IdPtLote));
		 return (ArchivoCsvDTO) c.uniqueResult();
	}

	
}
