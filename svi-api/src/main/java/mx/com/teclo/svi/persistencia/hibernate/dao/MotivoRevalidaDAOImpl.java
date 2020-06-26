package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.enumerable.EnumTipoBusqAsignacion;
import mx.com.teclo.svi.negocio.vo.catalogo.CatalogoVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoRevalidaDTO;

@SuppressWarnings("unchecked")
@Repository("motivoRevalidaDAO")
public class MotivoRevalidaDAOImpl extends BaseDaoHibernate<MotivoRevalidaDTO> implements MotivoRevalidaDAO{

	@Override
	public List<CatalogoVO> buscarCatMotivo(){
		Criteria c = getCurrentSession().createCriteria(MotivoRevalidaDTO.class);
		c.add(Restrictions.eq("stActivo", Boolean.TRUE));
		
		List<MotivoRevalidaDTO> lst = (List<MotivoRevalidaDTO>)c.list(); 
		
		List<CatalogoVO> res = new ArrayList<CatalogoVO>();
		
		for(MotivoRevalidaDTO mrDTO: lst){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(mrDTO.getIdMotivoReva());
			cVO.setNameCat(mrDTO.getTxMotivo());
			res.add(cVO);
		}
		
		return res;
	}
	
	@Override
	public List<CatalogoVO> buscarCatMotivoFiltrado(Long tipoExclusionInclusion){
		
		Criteria c = getCurrentSession().createCriteria(MotivoRevalidaDTO.class);
		c.add(Restrictions.eq("stActivo", Boolean.TRUE));
		if(EnumTipoBusqAsignacion.MOTIVO_CSV.getId() == tipoExclusionInclusion) {
			c.add(Restrictions.eq("stMotivoCsv", Boolean.TRUE));
		}
		if(EnumTipoBusqAsignacion.MOTIVO_EXPEDIENTES.getId() == tipoExclusionInclusion) {
			c.add(Restrictions.eq("stMotivoDetalle", Boolean.TRUE));
		}
		List<MotivoRevalidaDTO> lst = (List<MotivoRevalidaDTO>)c.list(); 
		
		List<CatalogoVO> res = new ArrayList<CatalogoVO>();
		
		for(MotivoRevalidaDTO mrDTO: lst){
			CatalogoVO cVO = new CatalogoVO();
			cVO.setIdCat(mrDTO.getIdMotivoReva());
			cVO.setNameCat(mrDTO.getTxMotivo());
			res.add(cVO);
		}
		
		return res;
	}
}
