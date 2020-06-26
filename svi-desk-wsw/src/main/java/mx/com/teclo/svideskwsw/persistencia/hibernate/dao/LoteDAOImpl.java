package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAOImpl;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;


@SuppressWarnings("unchecked")
@Repository("loteDAO")
public class LoteDAOImpl extends BaseDAOImpl<LoteDTO> implements LoteDAO {
	private static String INCOMPLETO="ErrorCarga";
	private static Long SINCARGA = 0L;
	private static Long CEROS= 0L; 
	

	@Override
	public Long getSequenceNextVal(){
		SQLQuery q = getCurrentSession().createSQLQuery("SELECT PT_LOTE_SEQ.nextval FROM DUAL");
		
		BigDecimal r = (BigDecimal) q.uniqueResult();
		
		return r.longValueExact();
	}
	
	@Override
	public Boolean validaSiExisteLote(String nbLote, EntregaDTO eDTO){
		Criteria c = getCurrentSession().createCriteria(LoteDTO.class);
		c.add(Restrictions.like("nbPtLote", nbLote, MatchMode.ANYWHERE));
		c.add(Restrictions.eq("idEntrega", eDTO));
		
		List<LoteDTO> r = (List<LoteDTO>) c.list();
		
		return r.isEmpty() ? Boolean.FALSE:Boolean.TRUE;
	}

	@Override
	@Transactional
	public LoteDTO actualizarLote(long idPtLote) {
		Criteria c = getCurrentSession().createCriteria(LoteDTO.class);
		c.add(Restrictions.eq("idPtLote", idPtLote));
		return (LoteDTO) c.uniqueResult();
	}

	@Override
	@Transactional
	public void modificarEntraga(LoteDTO lote) {
		getCurrentSession().update(lote);
		getCurrentSession().flush();
	}

	
	@Override
	@Transactional
	public List<LoteDTO> getLoteCargar(String tipo) {
		List<LoteDTO> respuesta = new ArrayList<LoteDTO>();
		Criteria c = getCurrentSession().createCriteria(LoteDTO.class);		
		if(tipo.equals(INCOMPLETO)){
			c.add(Restrictions.eq("statusCarga", SINCARGA));
			c.add(Restrictions.eq("stValidacion", false));
			c.add(Restrictions.gtProperty("nuTotalRegistrosxLote" , "nuTotalRegCsv"));
			respuesta = c.list();
		}else{	
			c.add(Restrictions.eq("statusCarga", SINCARGA));
			c.add(Restrictions.eq("stValidacion", false));
			c.add(Restrictions.eq("nuTotalRegCsv",CEROS));
			respuesta = c.list();
		}
		return respuesta;
	}

	@Override
	@Transactional
	public Boolean existePT(Long idLote) {
		StringBuilder consulta = new StringBuilder("SELECT ID_PT_LOTE AS existe  FROM TCI002D_PT_LOTE WHERE ID_PT_LOTE ="+ idLote);
		Query execucion = getCurrentSession().createSQLQuery(consulta.toString()).addScalar("existe",LongType.INSTANCE);
		Long lote = (Long) execucion.uniqueResult();
		if(lote>0){
			return true;
		}else{
			return false;
		}
	}


	
}
