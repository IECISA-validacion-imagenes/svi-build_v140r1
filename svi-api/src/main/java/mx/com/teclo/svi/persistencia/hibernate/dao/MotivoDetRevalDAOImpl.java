package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.MotivoDetRevalDTO;

@Repository("motivoDetRevalDAO")
public class MotivoDetRevalDAOImpl extends BaseDaoHibernate<MotivoDetRevalDTO> implements MotivoDetRevalDAO{

}
