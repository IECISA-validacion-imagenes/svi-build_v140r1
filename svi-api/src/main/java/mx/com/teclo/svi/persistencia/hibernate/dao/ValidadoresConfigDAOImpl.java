package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.supervision.ValidadorVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ValidadoresConfigDTO;

@SuppressWarnings("unchecked")
@Repository("ValidadoresConfigDAO")
public class ValidadoresConfigDAOImpl extends BaseDaoHibernate<ValidadoresConfigDTO> implements ValidadoresConfigDAO {

	@Override
	@Transactional
	public ValidadoresConfigDTO getValidadorConfigByIdValidador(Long idValidador) {
//		ValidadoresConfigDTO validadorConfig = getCurrentSession().get(ValidadoresConfigDTO.class, idValidador);
		
		Criteria c = getCurrentSession().createCriteria(ValidadoresConfigDTO.class);
		c.createAlias("idValidador","ValidadoresDTO");
		c.add(Restrictions.eq("ValidadoresDTO.idValidador", idValidador));
		ValidadoresConfigDTO validadorConfig = (ValidadoresConfigDTO) c.uniqueResult();
		
		return validadorConfig;//TOMAR PRECAUCIÓN AQUI
	}
	
	@Override
	@Transactional
	public Long getValidadorConfigByIdValidador2(Long idValidador) {
	StringBuilder strQuery = new StringBuilder("SELECT ID_VALIDADOR_CONFIG FROM TCI009D_PT_VALIDADORES_CONFIG WHERE ID_VALIDADOR = "+idValidador);
	Query query = getCurrentSession().createSQLQuery(strQuery.toString()).addScalar("ID_VALIDADOR_CONFIG", LongType.INSTANCE);
	return (Long) query.uniqueResult();
	}
	
	@Override
	public List<ValidadorVO> obtenerValidadoresVO(){
		String sql = "SELECT u.NB_USUARIO ||' '|| u.NB_APATERNO ||' '|| u.NB_AMATERNO AS NOMBRE, val.ID_VALIDADOR FROM TCI009D_PT_VALIDADORES_CONFIG vcfg\r\n" + 
				"INNER JOIN TCI007C_PT_VALIDADORES val ON (val.ID_VALIDADOR = vcfg.ID_VALIDADOR)\r\n" + 
				"INNER JOIN TAQ025C_SE_USUARIOS u ON (u.ID_USUARIO = val.ID_USUARIO) ";
		
		SQLQuery q = getCurrentSession().createSQLQuery(sql);
		List<Object[]> list = (List<Object[]>) q.list();
		
		List<ValidadorVO> res = new ArrayList<ValidadorVO>();
		
		for(Object[] obj: list){
			ValidadorVO vVO = new ValidadorVO();
			vVO.setNbValidador(((String)obj[0]));
			vVO.setIdValidador(((BigDecimal)obj[1]).longValueExact());
			res.add(vVO);
		}
		
		return res;
	}

}
