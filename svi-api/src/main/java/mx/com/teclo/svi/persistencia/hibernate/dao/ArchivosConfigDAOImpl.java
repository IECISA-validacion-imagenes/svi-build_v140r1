package mx.com.teclo.svi.persistencia.hibernate.dao;

import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchivosConfigDTO;

@Repository("archivosConfigDAO")
public class ArchivosConfigDAOImpl extends BaseDaoHibernate<ArchivosConfigDTO>implements ArchivosConfigDAO {

}
