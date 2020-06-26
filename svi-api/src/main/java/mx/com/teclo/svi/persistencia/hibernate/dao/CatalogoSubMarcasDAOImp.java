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
import mx.com.teclo.svi.negocio.utils.ParametrosComponente;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoMarcaSubVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSubmarcasDTO;


@SuppressWarnings("unchecked")
@Repository("catalogoSubmarca")
public class CatalogoSubMarcasDAOImp extends BaseDaoHibernate<PtSubmarcasDTO> implements CatalogoSubMarcasDAO {

	private static String CARPETA = "E01/PT047/imagenes/";
	@Autowired
	private ParametrosComponente parametrosComponente;
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	@Transactional
	public List<CatalogoMarcaSubVO> getCatalogoSubmarcas() {
		List<CatalogoMarcaSubVO> respuesta = new ArrayList<CatalogoMarcaSubVO>();
		CatalogoMarcaSubVO datos = null; 
		
		StringBuilder strQuery2 = new StringBuilder("SELECT TX_STORAGE_WEB FROM TCI014C_PT_PARAMETROS WHERE ID_PT_PARAM="+parametrosComponente.getIdPtParam());
				
		StringBuilder strQuery = new StringBuilder("SELECT  "
				+"sub.ID_PT_PERFIL AS idPerfil, "
				+"sub.ID_PT_MARCA AS idMarca, "
				+"sub.ID_PT_SUBMARCA AS idSubmarca, "
				+"UPPER(MARCAS.TXT_MARCA) ||'- '|| UPPER(SUB.TXT_MARCA)||' -'||UPPER(PERFIL.TX_DESCRIPCION) AS marcaSubPerfil, "
				+"SUB.TXT_IMAGEN AS urlImagen, "
				+"PERFIL.TX_DESCRIPCION AS txtPerfil"
				+" FROM TCI018C_PT_SUBMARCA sub"
				+"  JOIN TCI017C_PT_MARCAS marcas ON(marcas.ID_PT_MARCA = sub.ID_PT_MARCA) "
				+"  JOIN TCI013C_PT_PERFILES perfil ON(perfil.ID_PT_PERFILES = sub.ID_PT_PERFIL)"
				+"  WHERE sub.ST_ACTIVO = 1 ORDER BY marcas.TXT_MARCA ");
		
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TX_STORAGE_WEB", StringType.INSTANCE);
		String urlHost = (String) query2.uniqueResult();
		
			
		Query query = getCurrentSession().createSQLQuery(strQuery.toString());
		List rows = query.list();
		Iterator rowsIt = rows.iterator();
		if(!rows.isEmpty()){
			while (rowsIt.hasNext()) {
				Object[] lista = (Object[]) rowsIt.next();	
				
				String nombreImagen = lista[4] != null ? lista[4].toString() :"NOIMAGEN.jpg" ;
				datos = new CatalogoMarcaSubVO();			
				datos.setIdPerfil(lista[0].toString());
				datos.setIdMarca(lista[1].toString());
				datos.setIdSubmarca(lista[2].toString());
				
				datos.setMarcaSubPerfil(lista[3].toString());
				datos.setUrlImagen(urlHost + CARPETA + nombreImagen);
				datos.setTxtPerfil(lista[5].toString());
				respuesta.add(datos);
			}
		 }
		return respuesta;
	}

	@Override
	public String getMarca(Long idSubMarca) {
		StringBuilder strQuery2 = new StringBuilder("SELECT TXT_MARCA FROM TCI017C_PT_MARCAS WHERE ID_PT_MARCA ="+idSubMarca);
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TXT_MARCA", StringType.INSTANCE);
		String subMarca = (String) query2.uniqueResult();
		return subMarca;
	}

	@Override
	public String getSubMarca(Long idModelo) {
		StringBuilder strQuery2 = new StringBuilder("SELECT TXT_MARCA FROM TCI018C_PT_SUBMARCA WHERE ID_PT_SUBMARCA = "+ idModelo);
		Query query2 = getCurrentSession().createSQLQuery(strQuery2.toString()).addScalar("TXT_MARCA", StringType.INSTANCE);
		String modelo = (String) query2.uniqueResult();
		return modelo;
	}

}
