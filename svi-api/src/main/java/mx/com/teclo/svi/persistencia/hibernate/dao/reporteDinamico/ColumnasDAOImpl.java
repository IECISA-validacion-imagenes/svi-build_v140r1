package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.ColumnasDTO;

@Repository
public class ColumnasDAOImpl extends BaseDaoHibernate<ColumnasDTO> implements ColumnasDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ColumnasDTO> getCatalogoColumnas() {
		Criteria c = getCurrentSession().createCriteria(ColumnasDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ColumnasDTO>) c.list();
	}

	@Override
	@Transactional
	public ColumnasDTO getColumnaDeFiltro(Long idTabla, String strColumnaReal) {
		Criteria c = getCurrentSession().createCriteria(ColumnasDTO.class);
		c.createAlias("tabla", "t");
		c.add(Restrictions.eq("stActivo", 1));
		c.add(Restrictions.eq("t.idTabla", idTabla));
		c.add(Restrictions.eq("nbReal", strColumnaReal));
		return (ColumnasDTO)c.uniqueResult();
	}
	
	
	
	

}
