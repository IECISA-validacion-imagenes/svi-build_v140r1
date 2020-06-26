package mx.com.teclo.svi.persistencia.hibernate.dao;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.svi.negocio.vo.expediente.DetalleExpedienteHistoricoVO;
import mx.com.teclo.svi.persistencia.hibernate.dto.ArchDetBitEvaDTO;

public interface ArchDetBitEvaDAO extends BaseDao<ArchDetBitEvaDTO> {
	
	public List<DetalleExpedienteHistoricoVO> getDetalleHistorico(Long registroCSV);
		
}