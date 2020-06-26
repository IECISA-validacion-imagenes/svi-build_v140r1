package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.UsuarioDTO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresDTO;

@Repository("validadoresDAO")
public class ValidadoresDAOImpl extends BaseDaoHibernate<ValidadoresDTO> implements ValidadoresDAO {

	

	@Override
	public Long getIdUsuario(Long idValidador) {
		ValidadoresDTO validador = getCurrentSession().get(ValidadoresDTO.class, idValidador);
		UsuarioDTO idusu = validador.getIdUsuario();
		return idusu.getIdUsuario();
	}
	

	@Override
	@Transactional
	public ValidadoresDTO getValidadorByIdUsuario(Long idUsuario) {
		ValidadoresDTO validador;
		Criteria criteria = getCurrentSession().createCriteria(ValidadoresDTO.class);	
		criteria.createAlias("idUsuario","usuarios");
		criteria.add(Restrictions.eq("usuarios.idUsuario",idUsuario ));
		
		return (ValidadoresDTO) criteria.uniqueResult();
	}

	@Override
	public ValidadoresDTO getValidadorByIdValidador(Long idValidador) {
		ValidadoresDTO validador = getCurrentSession().get(ValidadoresDTO.class, idValidador);
		
		return validador;
	}
	
	@Override
	public Long getTotalMaxAsignar(Long idValidador) {
		StringBuilder strQuery = new StringBuilder(" "
				+ "SELECT ARCHCONF.NU_MAX FROM TCI009D_PT_VALIDADORES_CONFIG VALCONF " + 
				"JOIN  TCI008C_PT_ARCHIVOS_CONFIG ARCHCONF ON VALCONF.ID_CONFIGURACION = ARCHCONF.ID_CONFIGURACION " + 
				"WHERE VALCONF.ST_ACTIVO=1 AND ARCHCONF.ST_ACTIVO=1 AND VALCONF.ID_VALIDADOR="+idValidador);
		Query query = getCurrentSession().createSQLQuery(strQuery.toString()).addScalar("NU_MAX", LongType.INSTANCE);
		Long nuMax = (Long) query.uniqueResult();
//		Iterator rowsIt = rows.iterator();
//		Long nuMax=0L;
//		if(!rows.isEmpty()){
//			while (rowsIt.hasNext()) {
//			Object[] lista = (Object[]) rowsIt.next();	
//				nuMax= lista[0] != null ? Long.parseLong(lista[0].toString(), 10) : 0;
//			}
//		}
		
		
		return nuMax;
	}
}
