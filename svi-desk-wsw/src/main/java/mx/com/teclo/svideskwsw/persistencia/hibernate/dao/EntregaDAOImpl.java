package mx.com.teclo.svideskwsw.persistencia.hibernate.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.svideskwsw.persistencia.hibernate.comun.BaseDAOImpl;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;

@SuppressWarnings("unchecked")
@Repository("entregaDAO")
public class EntregaDAOImpl extends BaseDAOImpl<EntregaDTO>implements EntregaDAO {

	@Override
	public Long getSequenceNextVal(){
		SQLQuery q = getCurrentSession().createSQLQuery("SELECT PT_ENTREGA_SEQ.nextval FROM DUAL");
		
		BigDecimal r = (BigDecimal) q.uniqueResult();
		
		return r.longValueExact();
	}
	
	@Override
	public EntregaDTO buscarPosibleEntregable(String nbEntregable, Long idUsuario){
		Criteria c = getCurrentSession().createCriteria(EntregaDTO.class);
		c.add(Restrictions.eq("nbEntrega", nbEntregable).ignoreCase());
		
		EntregaDTO res = (EntregaDTO) c.uniqueResult(); 
		
		if(res == null){
			res = new EntregaDTO();
			res.setIdEntrega(getSequenceNextVal());
			res.setNbEntrega(nbEntregable);
			
			/*Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR,Integer.parseInt(nbEntregable.substring(0,4)));
			cal.set(Calendar.MONTH,Integer.parseInt(nbEntregable.substring(4,6))-1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);*/
			
			//res.setFhRecepcion(cal.getTime());
			res.setFhRecepcion(new Date());
			res.setNuCantidadPts(0L);
			res.setNuCantidadCsv(0L);
			res.setNuTotalRegCsv(0L);
			res.setNuTotalRegImg(0L);
			res.setNuTotalRegSil(0L);
			res.setTxEntrega(new SimpleDateFormat("MMMMM").format(new Date()));
			res.setStActivo(Boolean.TRUE);
			res.setFhCreacion(new Date());
			res.setIdUsrCreacion(idUsuario);
			res.setFhModificacion(new Date());
			res.setIdUsrModifica(idUsuario);
			
			this.save(res);
		}
		return res;
	}
	
	@Override
	@Transactional
	public void actualizar(EntregaDTO elemento) {
		getCurrentSession().update(elemento);
		getCurrentSession().flush();	
	}


	@Override
	@Transactional
	public EntregaDTO consultarEntregaDTO(long idEntrega) {
		Criteria criteria = getCurrentSession().createCriteria(EntregaDTO.class);
		criteria.add(Restrictions.eq("idEntrega", idEntrega));
		return(EntregaDTO)criteria.uniqueResult();
	}

	@Override
	@Transactional
	public List<EntregaDTO> getValoresTxtRuta() {
		Criteria criteria = getCurrentSession().createCriteria(EntregaDTO.class);
		criteria.add(Restrictions.eq("nuTotalRegCsv", 0l));
		return(List<EntregaDTO>)criteria.list();
	}
	
}
