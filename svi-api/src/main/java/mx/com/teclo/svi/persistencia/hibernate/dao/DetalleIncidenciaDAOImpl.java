package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.supervision.IncidenciaVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleIncidenciaDTO;

@SuppressWarnings("unchecked")
@Repository("detalleIncidenciaDAO")
public class DetalleIncidenciaDAOImpl extends BaseDaoHibernate<DetalleIncidenciaDTO> implements DetalleIncidenciaDAO{

	@Override
	public List<IncidenciaVO> buscarIncidenciasPorRegistro(Long idRegistro){
		Criteria c = getCurrentSession().createCriteria(DetalleIncidenciaDTO.class);
		c.createAlias("idRegistroCsv", "reg");
		c.add(Restrictions.eq("stActivo", Boolean.TRUE));
		c.add(Restrictions.eq("reg.idRegistroCsv", idRegistro));
		
		List<DetalleIncidenciaDTO> q = (List<DetalleIncidenciaDTO>)c.list();
		List<IncidenciaVO> res = new ArrayList<IncidenciaVO>();
		
		for(DetalleIncidenciaDTO diDTO: q){
			IncidenciaVO iVO = new IncidenciaVO(diDTO.getIdIncidencia().getCdIncidencia(), diDTO.getIdIncidencia().getTxIncidencia());
			res.add(iVO);
		}
		
		return res;
	}
	
	@Override
	public Boolean deshabilitarIncidenciasPorRegistros(List<Long> idLista, Long idUsuario){
		Boolean res = false;
		
		
		int contador=0;
		List<String> listIds = new ArrayList<String>();
		String ids="";
		
		for(Long id: idLista){
			contador++;		
			if (contador % 400 == 0||contador==idLista.size()) {
				ids=ids+id+"";
				listIds.add(ids);
				ids="";
			}else {
				ids=ids+id+",";
			}
		}
		
		for(int i=0;i<listIds.size();i++) {
			Query q = getCurrentSession().createQuery("UPDATE DetalleIncidenciaDTO SET stActivo = 0, idUsrModifica = :idUsr, fhModificacion = SYSDATE WHERE idRegistroCsv in ("+listIds.get(i)+")");
			q.setLong("idUsr", idUsuario);
			
			q.executeUpdate();
			res = true;
			if(!res){break;}
		}

		return res;
	}
}
