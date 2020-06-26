package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.DetalleRevalidaDTO;

@Repository("detalleRevalidaDAO")
public class DetalleRevalidaDAOImpl extends BaseDaoHibernate<DetalleRevalidaDTO> implements DetalleRevalidaDAO{

}
