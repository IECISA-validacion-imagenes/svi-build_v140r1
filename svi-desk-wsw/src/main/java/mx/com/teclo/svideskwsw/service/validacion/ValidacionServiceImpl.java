package mx.com.teclo.svideskwsw.service.validacion;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.ArchivoCsvDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.EntregaDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dao.LoteDAO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.ArchivoCsvDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.EntregaDTO;
import mx.com.teclo.svideskwsw.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.svideskwsw.persistencia.vo.validacion.ArchivoCSVVO;
import mx.com.teclo.svideskwsw.persistencia.vo.validacion.ImagenesVO;
import mx.com.teclo.svideskwsw.persistencia.vo.validacion.LotesVO;
import mx.com.teclo.svideskwsw.persistencia.vo.validacion.PuntoTacticoVO;
import mx.com.teclo.svideskwsw.persistencia.vo.validacion.SiluetasVO;

@Service
public class ValidacionServiceImpl implements ValidacionService {

	@Autowired
	LoteDAO loteDAO;
	
	@Autowired
	EntregaDAO entregaDAO;
	
	@Autowired
	ArchivoCsvDAO archivoCsvDAO;
	
	@Override
	@Transactional
	public boolean guardarLote(LotesVO listaPTS){
		for(PuntoTacticoVO ptVO: listaPTS.getPuntosTacticos()){
			
			EntregaDTO eDTO = entregaDAO.buscarPosibleEntregable(listaPTS.getNbEntregable(), listaPTS.getIdUsuario());
			if(!loteDAO.validaSiExisteLote(ptVO.getName(), eDTO)){
				LoteDTO lDTO = new LoteDTO();
				lDTO.setIdPtLote(loteDAO.getSequenceNextVal());
				lDTO.setIdEntrega(eDTO);
				lDTO.setNbPtLote(ptVO.getName());
				lDTO.setStEntrega(Boolean.FALSE);
				lDTO.setStValidacion(Boolean.FALSE);
				lDTO.setStActivo(Boolean.FALSE);
				lDTO.setNuCarpetasImg(Long.parseLong(String.valueOf(ptVO.getImagenes().size())));
				lDTO.setNuTotalRegImg(0L);
				lDTO.setNuArchivosCsv(Long.parseLong(String.valueOf(ptVO.getArchivos().size())));
				lDTO.setNuTotalRegCsv(0L);
				lDTO.setNuCarpetasSil(Long.parseLong(String.valueOf(ptVO.getSiluetas().size())));
				lDTO.setNuTotalRegSil(0L);
				lDTO.setFhCreacion(new Date());
				lDTO.setIdUsrCreacion(listaPTS.getIdUsuario());
				lDTO.setFhModificacion(new Date());
				lDTO.setIdUsrModifica(listaPTS.getIdUsuario());
				lDTO.setStatusCarga((long)0);
				lDTO.setNuTotalRegistrosxLote((long)0);
				
				//Actualiza en N° de registros
				if(loteDAO.save(lDTO)){
					//if(asignarArchivosCSV(ptVO, listaPTS.getNbEntregable(), listaPTS.getIdUsuario(), lDTO)){
						eDTO.setNuCantidadPts(eDTO.getNuCantidadPts() +1);
						eDTO.setNuCantidadCsv(eDTO.getNuCantidadCsv() +ptVO.getArchivos().size());
						entregaDAO.save(eDTO);
					//}
				}
			}else{
				continue;
			}
		}
		return true;
	}
	
	private boolean asignarArchivosCSV(PuntoTacticoVO ptVO, String ruta, Long idUsuario, LoteDTO lDTO){
		boolean allCorrect = false;
		for(int i=0;i<ptVO.getArchivos().size();i++){
			ArchivoCsvDTO aCSVDTO = new ArchivoCsvDTO(); 
			//aCSVDTO.setIdArchivoCsv(archivoCsvDAO.getSequenceNextVal());
			aCSVDTO.setIdPtLote(lDTO);
			aCSVDTO.setNbArchivoCsv(ptVO.getArchivos().get(i).getName());
			aCSVDTO.setNuRegistrosCsv(0L);
			aCSVDTO.setNbCarpetaImg(ptVO.getImagenes().get(i).getName());
			aCSVDTO.setTxCarpetaImg(ruta+"/"+ptVO.getName()+"/"+ptVO.getImagenes().get(i).getName());
			aCSVDTO.setNuImagenes(0L);
			if(ptVO.getSiluetas().size()>0){
				aCSVDTO.setNbCarpetaSil(ptVO.getSiluetas().get(i).getName());
				aCSVDTO.setTxCarpetaSil(ruta+"/"+ptVO.getName()+"/"+ptVO.getSiluetas().get(i).getName());
				aCSVDTO.setNuSiluetas(0L);
			}else{
				aCSVDTO.setNbCarpetaSil(null);
				aCSVDTO.setTxCarpetaSil(null);
				aCSVDTO.setNuSiluetas(0L);
			}
			aCSVDTO.setStValidacion(Boolean.FALSE);
			aCSVDTO.setStActivo(Boolean.TRUE);
			aCSVDTO.setFhCreacion(new Date());
			aCSVDTO.setIdUsrCreacion(idUsuario);
			aCSVDTO.setFhModificacion(new Date());
			aCSVDTO.setIdUsrModifica(idUsuario);
			
			allCorrect = archivoCsvDAO.save(aCSVDTO);
		}
		
		return allCorrect;
	}
}
