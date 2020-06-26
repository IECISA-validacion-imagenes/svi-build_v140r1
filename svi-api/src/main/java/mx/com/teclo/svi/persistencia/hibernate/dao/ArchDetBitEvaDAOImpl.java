package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteHistoricoVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchDetBitEvaDTO;

@Repository("archDetBitEvaDAO")
public class ArchDetBitEvaDAOImpl extends BaseDaoHibernate<ArchDetBitEvaDTO> implements ArchDetBitEvaDAO {
	
	@Autowired	
	private CatalogoSubMarcasDAOImp catalogoSubmarca;

	@Override
	@Transactional
	@SuppressWarnings("rawtypes")
	public List<DetalleExpedienteHistoricoVO> getDetalleHistorico(Long registroCSV) {
		List<DetalleExpedienteHistoricoVO> listDetalleHistorico = new ArrayList<DetalleExpedienteHistoricoVO>();
		int cont = 1;
		
		StringBuilder original = new StringBuilder("SELECT CD_PLACA_DELANTERA, CD_ENTIDAD_DELANTERA,CD_PLACA_TRASERA, CD_ENTIDAD_TRASERA, " +
				"CD_PERFIL,FH_CREACION FROM  TCI004D_PT_ARCHIVO_DETALLE " +
				" WHERE ID_REGISTRO_CSV ="+ registroCSV +" ORDER BY FH_CREACION ASC");
		
		Query exeConsulta = getCurrentSession().createSQLQuery(original.toString());
		List renglones = exeConsulta.list();
		Iterator row = renglones.iterator();
		if(!renglones.isEmpty()){
			while (row.hasNext()) {
				Object[] lista = (Object[]) row.next();
				DetalleExpedienteHistoricoVO detalle = new DetalleExpedienteHistoricoVO();
				detalle.setCdPlacaD(lista[0] == null ? "" : lista[0].toString());
				detalle.setCdEntidadD(lista[1] == null ? "" :lista[1].toString());
				detalle.setCdPlacaT(lista[2] == null ? "" :lista[2].toString());
				detalle.setCdEntidadT(lista[3] == null ? "" :lista[3].toString());
				detalle.setCdPerfil(lista[4]  == null ? "" :lista[4].toString());
				detalle.setFhCreacion(lista[5]  == null ? "" :lista[5].toString());
				detalle.setTipoValidacion("Original");
				listDetalleHistorico.add(detalle);	
			}
		}
		
		StringBuilder strQuery = new StringBuilder("SELECT CD_PLACA_DELANTERA, CD_ENTIDAD_DELANTERA,CD_PLACA_TRASERA, CD_ENTIDAD_TRASERA, " +
				"CD_PERFIL,ID_MARCA,ID_SUBMARCA,FH_CREACION FROM TCI006D_PT_ARCHIVO_DET_BIT_EVA " +
				" WHERE ID_REGISTRO_CSV ="+ registroCSV +" ORDER BY FH_CREACION ASC");

		Query query = getCurrentSession().createSQLQuery(strQuery.toString());
		
		List rows = query.list();
		Iterator rowsIt = rows.iterator();
		if(!rows.isEmpty()){
			while (rowsIt.hasNext()) {
				Object[] lista = (Object[]) rowsIt.next();
				DetalleExpedienteHistoricoVO detalle = new DetalleExpedienteHistoricoVO();
				detalle.setCdPlacaD(lista[0].toString()); //placa delantera 
				detalle.setCdEntidadD(lista[1].toString());//entidad D
				detalle.setCdPlacaT(lista[2].toString()); //placa Trasera
				detalle.setCdEntidadT(lista[3].toString()); //cd entidadT
				detalle.setCdPerfil(lista[4].toString()); // perfil
				detalle.setMarca(catalogoSubmarca.getMarca(Long.parseLong(lista[5].toString()))); //marca
				detalle.setSubMarca(catalogoSubmarca.getSubMarca(Long.parseLong(lista[6].toString()))); //submarca modelo 
				detalle.setFhCreacion(lista[7].toString());//fecha
				detalle.setTipoValidacion("Validación "+ cont);
				cont++;
				listDetalleHistorico.add(detalle);		
			}
		}
		return listDetalleHistorico;
	}
	
}
