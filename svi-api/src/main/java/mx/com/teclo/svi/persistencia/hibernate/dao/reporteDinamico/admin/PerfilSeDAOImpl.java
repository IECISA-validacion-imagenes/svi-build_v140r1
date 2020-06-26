package mx.com.teclo.svi.persistencia.hibernate.dao.reporteDinamico.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.negocio.vo.reporte.PerfilesAdminVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.reportes.PerfilSeDTO;

@SuppressWarnings("unchecked")
@Repository("Perfil_SE_DAO")
public class PerfilSeDAOImpl extends BaseDaoHibernate<PerfilSeDTO> implements Perfil_SE_DAO {

	private static Long ACTIVO = (long) 1;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<PerfilesAdminVO> getPerfiles() {
		List<PerfilesAdminVO> lisPerfilesVO = new ArrayList<PerfilesAdminVO>();
		Criteria c = getCurrentSession().createCriteria(PerfilSeDTO.class);
		c.add(Restrictions.eq("idAplicacion",14L));
		c.add(Restrictions.eq("stActivo",1L));
		List<PerfilSeDTO> perfilesDTO = c.list();
		if(perfilesDTO.isEmpty())
			return null;
		for(PerfilSeDTO elementoDTO: perfilesDTO){
			PerfilesAdminVO objeto = new PerfilesAdminVO();
			ResponseConverter.copiarPropriedades(objeto, elementoDTO);
			lisPerfilesVO.add(objeto);
		}
		return lisPerfilesVO;
	}

	@Override
	public PerfilSeDTO getPerfilbyIdperfil(Long idPerfil) {
		Criteria c =  getCurrentSession().createCriteria(PerfilSeDTO.class);
		c.add(Restrictions.eq("perfilId",idPerfil));
		c.add(Restrictions.eq("stActivo", ACTIVO));
		return (PerfilSeDTO) c.uniqueResult();
	}

}
