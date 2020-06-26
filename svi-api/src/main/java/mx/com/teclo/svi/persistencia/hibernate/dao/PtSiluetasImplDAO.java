package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.PtSiluetasDTO;


@SuppressWarnings("unchecked")
@Repository("ptSiluetasDAO")
public class PtSiluetasImplDAO extends BaseDaoHibernate<PtSiluetasDTO> implements PtSiluetasDAO {

	/*Criteria c = getCurrentSession().createCriteria(LoteDTO.class);
		c.add(Restrictions.eq("idPtLote", idPtLote));
		return (LoteDTO) c.uniqueResult();*/
	
	@Override
	@Transactional
	public List<PtSiluetasDTO> getCatValidacionSiluetas() {
		Criteria c = getCurrentSession().createCriteria(PtSiluetasDTO.class);
		c.add(Restrictions.eq("stActivo", 1L));
		return (List<PtSiluetasDTO>)c.list();
	}
	


}
