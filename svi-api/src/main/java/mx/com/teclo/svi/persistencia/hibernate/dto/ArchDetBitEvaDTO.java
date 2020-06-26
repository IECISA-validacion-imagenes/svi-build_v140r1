package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI006D_PT_ARCHIVO_DET_BIT_EVA")
public class ArchDetBitEvaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "SEQPTDETBITEVASEQ", sequenceName="PT_ARCHIVO_DET_BIT_EVA_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPTDETBITEVASEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REG_BIT_EVA")
    private Long idRegBitEva;
    
    @JoinColumn(name = "ID_REGISTRO_CSV", referencedColumnName = "ID_REGISTRO_CSV")
    @ManyToOne(optional = false)
    private ArchivoDetalleEvaDTO idRegistroCsv;
    
    @Column(name = "NODO_VPN")
    private Long nodoVpn;
    @Column(name = "NU_EXPEDIENTE")
    private String nuExpediente;
    @Column(name = "FH_GENERACION")
    private String fhGeneracion;
    @Column(name = "NU_CARRIL")
    private Long nuCarril;
    
    @Column(name = "CD_PLACA_DELANTERA")
    private String cdPlacaDelantera;
    @Column(name = "ST_DIF_PLACA_DELANTERA")
    private Short stDifPlacaDelantera;
    @Column(name = "CD_ENTIDAD_DELANTERA")
    private String cdEntidadDelantera;
    @Column(name = "ST_DIF_ENTIDAD_DELANTERA")
    private Short stDifEntidadDelantera;
    @Column(name = "NB_IMAGEN_PLACA_DELANTERA")
    private String nbImagenPlacaDelantera;
    @Column(name = "CD_PLACA_TRASERA")
    private String cdPlacaTrasera;
    @Column(name = "ST_DIF_PLACA_TRASERA")
    private Short stDifPlacaTrasera;
    @Column(name = "CD_ENTIDAD_TRASERA")
    private String cdEntidadTrasera;
    @Column(name = "ST_DIF_ENTIDAD_TRASERA")
    private Short stDifEntidadTrasera;
    @Column(name = "NB_IMAGEN_PLACA_TRASERA")
    private String nbImagenPlacaTrasera;
    @Column(name = "NB_IMAGEN_CONDUCTOR")
    private String nbImagenConductor;
    @Column(name = "NB_IMAGEN_AMBIENTAL")
    private String nbImagenAmbiental;
    @Column(name = "CD_PERFIL")
    private String cdPerfil;
    @Column(name = "ST_DIF_PERFIL")
    private Short stDifPerfil;
    @Column(name = "NB_IMAGEN_PERFIL")
    private String nbImagenPerfil;
    @Basic(optional = false)
    @Column(name = "ST_VALIDACION")
    private Short stValidacion;
    @Basic(optional = false)
    @Column(name = "ST_REVALIDACION")
    private Short st2daValidacion;
    @Basic(optional = false)
    @Column(name = "ST_INCONSISTENCIA")
    private Short stInconsistencia;
    @Basic(optional = false)
    @Column(name = "ST_ACTIVO")
    private short stActivo;
    
    @Basic(optional = false)
    @Column(name = "FH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhCreacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_CREACION")
    private long idUsrCreacion;
    
    @Basic(optional = false)
    @Column(name = "FH_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhModificacion;
    
    @Basic(optional = false)
    @Column(name = "ID_USR_MODIFICA")
    private long idUsrModifica;
    @Column(name = "ST_VAL_PLACA_DELANTERA")
    private Boolean stValPlacaDelantera;
    @Column(name = "ST_VAL_ENTIDAD_DELANTERA")
    private Boolean stValEntidadDelantera;
    @Column(name = "ST_VAL_PLACA_TRASERA")
    private Boolean stValPlacaTrasera;
    @Column(name = "ST_VAL_ENTIDAD_TRASERA")
    private Boolean stValEntidadTrasera;

    @Column(name = "ST_POCHOMOVIL")
    private Boolean stPochomovil;
    
    @Column(name = "ST_PLACA_OFI_DELANTERA")
    private Boolean stPlacaOfiDelantera;
    
    @Column(name = "ST_PLACA_OFI_TRASERA")
    private Boolean stPlacaOfiTrasera;
    
    @Column(name = "ST_VAL_IMAGEN_MAL")
    private Boolean stValImagenMal;
    
    @Column(name = "ST_VAL_PERFIL")
    private Boolean stValPerfil;
    
    @Column(name = "ST_DOBLE_PLACA")
    private Boolean stDoblePlaca;
    
    @Column(name = "ST_CON_CAMBIOS")
    private Boolean stConCambios;
    
    @Column(name = "ST_PRE_SELECCION")
    private Boolean stPreSeleccion;
    
    @JoinColumn(name = "ID_SUBMARCA", referencedColumnName = "ID_PT_SUBMARCA")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PtSubmarcasDTO idSubMarca;
    
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID_PT_MARCA")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PtMarcasDTO idMarca;
    
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PT_PERFILES")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private  PtPerfilesDTO idPerfil;
    
    @JoinColumn(name = "ID_PT_CLASIF_EXPE", referencedColumnName = "ID_PT_CLASIF_EXPE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PtClasifExpedientesDTO idPTClasifExpe;
    
    @JoinColumn(name = "ID_PT_SILUETA", referencedColumnName = "ID_PT_SILUETA")
    @ManyToOne(fetch = FetchType.LAZY)
    private PtSiluetasDTO idPtSilueta;
	
    @JoinColumn(name = "ID_ARCHIVO_CSV", referencedColumnName = "ID_ARCHIVO_CSV")
    @ManyToOne(optional = false)
    private ArchivoCsvDTO idArchivoCsv;
    
    @JoinColumn(name = "ID_REASIGNA_VAL", referencedColumnName = "ID_REASIGNA_VAL")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private DetalleValidadorDTO idReasignaVal;
    
    

    public ArchDetBitEvaDTO() {
    }

    public ArchDetBitEvaDTO(Long idRegBitEva) {
        this.idRegBitEva = idRegBitEva;
    }

    public ArchDetBitEvaDTO(Long idRegBitEva, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idRegBitEva = idRegBitEva;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }
    
    public ArchDetBitEvaDTO(ArchivoDetalleEvaDTO archivoDetalleEvaDTO, DetalleValidadorDTO detalleValidador) {
    	this.setCdEntidadDelantera(archivoDetalleEvaDTO.getCdEntidadDelantera());
    	this.setCdEntidadTrasera(archivoDetalleEvaDTO.getCdEntidadTrasera());
    	this.setCdPerfil(archivoDetalleEvaDTO.getCdPerfil());
    	this.setCdPlacaDelantera(archivoDetalleEvaDTO.getCdPlacaDelantera());
    	this.setCdPlacaTrasera(archivoDetalleEvaDTO.getCdPlacaTrasera());
    	this.setFhCreacion(archivoDetalleEvaDTO.getFhModificacion());
    	this.setFhGeneracion(archivoDetalleEvaDTO.getFhGeneracion());
    	this.setFhModificacion(archivoDetalleEvaDTO.getFhModificacion());
    	this.setIdArchivoCsv(archivoDetalleEvaDTO.getIdArchivoCsv());
    	this.setIdMarca(archivoDetalleEvaDTO.getIdMarca());
    	this.setIdPerfil(archivoDetalleEvaDTO.getIdPerfil());
    	this.setIdPTClasifExpe(archivoDetalleEvaDTO.getIdPtClasifExpe());
    	this.setIdPtSilueta(archivoDetalleEvaDTO.getIdPtSilueta());
    	this.setIdReasignaVal(detalleValidador);
    	this.setIdRegistroCsv(archivoDetalleEvaDTO);
    	this.setIdSubMarca(archivoDetalleEvaDTO.getIdSubMarca());
    	this.setIdUsrCreacion(archivoDetalleEvaDTO.getIdUsrModifica());
    	this.setIdUsrModifica(archivoDetalleEvaDTO.getIdUsrModifica());
    	this.setNbImagenAmbiental(archivoDetalleEvaDTO.getNbImagenAmbiental());
    	this.setNbImagenConductor(archivoDetalleEvaDTO.getNbImagenConductor());
    	this.setNbImagenPerfil(archivoDetalleEvaDTO.getNbImagenPerfil());
    	this.setNbImagenPlacaDelantera(archivoDetalleEvaDTO.getNbImagenPlacaDelantera());
    	this.setNbImagenPlacaTrasera(archivoDetalleEvaDTO.getNbImagenPlacaTrasera());
    	this.setNodoVpn(archivoDetalleEvaDTO.getNodoVpn());
    	this.setNuCarril(archivoDetalleEvaDTO.getNuCarril());
    	this.setNuExpediente(archivoDetalleEvaDTO.getNuExpediente());
    	this.setSt2daValidacion(archivoDetalleEvaDTO.getSt2daValidacion());
    	this.setStActivo(archivoDetalleEvaDTO.getStActivo());
    	this.setStConCambios(archivoDetalleEvaDTO.getStConCambios());
    	this.setStDifEntidadDelantera(archivoDetalleEvaDTO.getStDifEntidadDelantera());
    	this.setStDifEntidadTrasera(archivoDetalleEvaDTO.getStDifEntidadTrasera());
    	this.setStDifPerfil(archivoDetalleEvaDTO.getStDifPerfil());
    	this.setStDifPlacaDelantera(archivoDetalleEvaDTO.getStDifPlacaDelantera());
    	this.setStDifPlacaTrasera(archivoDetalleEvaDTO.getStDifPlacaTrasera());
    	this.setStDoblePlaca(archivoDetalleEvaDTO.getStDoblePlaca());
    	this.setStInconsistencia(archivoDetalleEvaDTO.getStInconsistencia());
    	this.setStPlacaOfiDelantera(archivoDetalleEvaDTO.getStPlacaOfiDelantera());
    	this.setStPlacaOfiTrasera(archivoDetalleEvaDTO.getStPlacaOfiTrasera());
    	this.setStPochomovil(archivoDetalleEvaDTO.getStPochomovil());
    	this.setStValEntidadDelantera(archivoDetalleEvaDTO.getStValEntidadDelantera());
    	this.setStValEntidadTrasera(archivoDetalleEvaDTO.getStValEntidadTrasera());
    	this.setStValidacion(archivoDetalleEvaDTO.getStValidacion());
    	this.setStValImagenMal(archivoDetalleEvaDTO.getStValImagenMal());
    	this.setStValPerfil(archivoDetalleEvaDTO.getStValPerfil());
    	this.setStValPlacaDelantera(archivoDetalleEvaDTO.getStValPlacaDelantera());
    	this.setStValPlacaTrasera(archivoDetalleEvaDTO.getStValPlacaTrasera());
    	this.setStPreSeleccion(archivoDetalleEvaDTO.getStPreSeleccion());
    }

	public Long getIdRegBitEva() {
		return idRegBitEva;
	}

	public void setIdRegBitEva(Long idRegBitEva) {
		this.idRegBitEva = idRegBitEva;
	}

	public ArchivoDetalleEvaDTO getIdRegistroCsv() {
		return idRegistroCsv;
	}

	public void setIdRegistroCsv(ArchivoDetalleEvaDTO idRegistroCsv) {
		this.idRegistroCsv = idRegistroCsv;
	}

	public Long getNodoVpn() {
		return nodoVpn;
	}

	public void setNodoVpn(Long nodoVpn) {
		this.nodoVpn = nodoVpn;
	}

	public String getNuExpediente() {
		return nuExpediente;
	}

	public void setNuExpediente(String nuExpediente) {
		this.nuExpediente = nuExpediente;
	}

	public String getFhGeneracion() {
		return fhGeneracion;
	}

	public void setFhGeneracion(String fhGeneracion) {
		this.fhGeneracion = fhGeneracion;
	}

	public Long getNuCarril() {
		return nuCarril;
	}

	public void setNuCarril(Long nuCarril) {
		this.nuCarril = nuCarril;
	}

	public String getCdPlacaDelantera() {
		return cdPlacaDelantera;
	}

	public void setCdPlacaDelantera(String cdPlacaDelantera) {
		this.cdPlacaDelantera = cdPlacaDelantera;
	}

	public Short getStDifPlacaDelantera() {
		return stDifPlacaDelantera;
	}

	public void setStDifPlacaDelantera(Short stDifPlacaDelantera) {
		this.stDifPlacaDelantera = stDifPlacaDelantera;
	}

	public String getCdEntidadDelantera() {
		return cdEntidadDelantera;
	}

	public void setCdEntidadDelantera(String cdEntidadDelantera) {
		this.cdEntidadDelantera = cdEntidadDelantera;
	}

	public Short getStDifEntidadDelantera() {
		return stDifEntidadDelantera;
	}

	public void setStDifEntidadDelantera(Short stDifEntidadDelantera) {
		this.stDifEntidadDelantera = stDifEntidadDelantera;
	}

	public String getNbImagenPlacaDelantera() {
		return nbImagenPlacaDelantera;
	}

	public void setNbImagenPlacaDelantera(String nbImagenPlacaDelantera) {
		this.nbImagenPlacaDelantera = nbImagenPlacaDelantera;
	}

	public String getCdPlacaTrasera() {
		return cdPlacaTrasera;
	}

	public void setCdPlacaTrasera(String cdPlacaTrasera) {
		this.cdPlacaTrasera = cdPlacaTrasera;
	}

	public Short getStDifPlacaTrasera() {
		return stDifPlacaTrasera;
	}

	public void setStDifPlacaTrasera(Short stDifPlacaTrasera) {
		this.stDifPlacaTrasera = stDifPlacaTrasera;
	}

	public String getCdEntidadTrasera() {
		return cdEntidadTrasera;
	}

	public void setCdEntidadTrasera(String cdEntidadTrasera) {
		this.cdEntidadTrasera = cdEntidadTrasera;
	}

	public Short getStDifEntidadTrasera() {
		return stDifEntidadTrasera;
	}

	public void setStDifEntidadTrasera(Short stDifEntidadTrasera) {
		this.stDifEntidadTrasera = stDifEntidadTrasera;
	}

	public String getNbImagenPlacaTrasera() {
		return nbImagenPlacaTrasera;
	}

	public void setNbImagenPlacaTrasera(String nbImagenPlacaTrasera) {
		this.nbImagenPlacaTrasera = nbImagenPlacaTrasera;
	}

	public String getNbImagenConductor() {
		return nbImagenConductor;
	}

	public void setNbImagenConductor(String nbImagenConductor) {
		this.nbImagenConductor = nbImagenConductor;
	}

	public String getNbImagenAmbiental() {
		return nbImagenAmbiental;
	}

	public void setNbImagenAmbiental(String nbImagenAmbiental) {
		this.nbImagenAmbiental = nbImagenAmbiental;
	}

	public String getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(String cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public Short getStDifPerfil() {
		return stDifPerfil;
	}

	public void setStDifPerfil(Short stDifPerfil) {
		this.stDifPerfil = stDifPerfil;
	}

	public String getNbImagenPerfil() {
		return nbImagenPerfil;
	}

	public void setNbImagenPerfil(String nbImagenPerfil) {
		this.nbImagenPerfil = nbImagenPerfil;
	}

	public Short getStValidacion() {
		return stValidacion;
	}

	public void setStValidacion(Short stValidacion) {
		this.stValidacion = stValidacion;
	}

	public Short getSt2daValidacion() {
		return st2daValidacion;
	}

	public void setSt2daValidacion(Short st2daValidacion) {
		this.st2daValidacion = st2daValidacion;
	}

	public Short getStInconsistencia() {
		return stInconsistencia;
	}

	public void setStInconsistencia(Short stInconsistencia) {
		this.stInconsistencia = stInconsistencia;
	}

	public short getStActivo() {
		return stActivo;
	}

	public void setStActivo(short stActivo) {
		this.stActivo = stActivo;
	}

	public Date getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public long getIdUsrCreacion() {
		return idUsrCreacion;
	}

	public void setIdUsrCreacion(long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}

	public Date getFhModificacion() {
		return fhModificacion;
	}

	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}

	public long getIdUsrModifica() {
		return idUsrModifica;
	}

	public void setIdUsrModifica(long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}

	public Boolean getStValPlacaDelantera() {
		return stValPlacaDelantera;
	}

	public void setStValPlacaDelantera(Boolean stValPlacaDelantera) {
		this.stValPlacaDelantera = stValPlacaDelantera;
	}

	public Boolean getStValEntidadDelantera() {
		return stValEntidadDelantera;
	}

	public void setStValEntidadDelantera(Boolean stValEntidadDelantera) {
		this.stValEntidadDelantera = stValEntidadDelantera;
	}

	public Boolean getStValPlacaTrasera() {
		return stValPlacaTrasera;
	}

	public void setStValPlacaTrasera(Boolean stValPlacaTrasera) {
		this.stValPlacaTrasera = stValPlacaTrasera;
	}

	public Boolean getStValEntidadTrasera() {
		return stValEntidadTrasera;
	}

	public void setStValEntidadTrasera(Boolean stValEntidadTrasera) {
		this.stValEntidadTrasera = stValEntidadTrasera;
	}

	public Boolean getStPochomovil() {
		return stPochomovil;
	}

	public void setStPochomovil(Boolean stPochomovil) {
		this.stPochomovil = stPochomovil;
	}

	public Boolean getStPlacaOfiDelantera() {
		return stPlacaOfiDelantera;
	}

	public void setStPlacaOfiDelantera(Boolean stPlacaOfiDelantera) {
		this.stPlacaOfiDelantera = stPlacaOfiDelantera;
	}

	public Boolean getStPlacaOfiTrasera() {
		return stPlacaOfiTrasera;
	}

	public void setStPlacaOfiTrasera(Boolean stPlacaOfiTrasera) {
		this.stPlacaOfiTrasera = stPlacaOfiTrasera;
	}

	public Boolean getStValImagenMal() {
		return stValImagenMal;
	}

	public void setStValImagenMal(Boolean stValImagenMal) {
		this.stValImagenMal = stValImagenMal;
	}

	public Boolean getStValPerfil() {
		return stValPerfil;
	}

	public void setStValPerfil(Boolean stValPerfil) {
		this.stValPerfil = stValPerfil;
	}

	public Boolean getStDoblePlaca() {
		return stDoblePlaca;
	}

	public void setStDoblePlaca(Boolean stDoblePlaca) {
		this.stDoblePlaca = stDoblePlaca;
	}

	public Boolean getStConCambios() {
		return stConCambios;
	}

	public void setStConCambios(Boolean stConCambios) {
		this.stConCambios = stConCambios;
	}

	public PtSubmarcasDTO getIdSubMarca() {
		return idSubMarca;
	}

	public void setIdSubMarca(PtSubmarcasDTO idSubMarca) {
		this.idSubMarca = idSubMarca;
	}

	public PtMarcasDTO getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(PtMarcasDTO idMarca) {
		this.idMarca = idMarca;
	}

	public PtPerfilesDTO getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(PtPerfilesDTO idPerfil) {
		this.idPerfil = idPerfil;
	}

	public PtClasifExpedientesDTO getIdPTClasifExpe() {
		return idPTClasifExpe;
	}

	public void setIdPTClasifExpe(PtClasifExpedientesDTO idPTClasifExpe) {
		this.idPTClasifExpe = idPTClasifExpe;
	}

	public PtSiluetasDTO getIdPtSilueta() {
		return idPtSilueta;
	}

	public void setIdPtSilueta(PtSiluetasDTO idPtSilueta) {
		this.idPtSilueta = idPtSilueta;
	}

	public ArchivoCsvDTO getIdArchivoCsv() {
		return idArchivoCsv;
	}

	public void setIdArchivoCsv(ArchivoCsvDTO idArchivoCsv) {
		this.idArchivoCsv = idArchivoCsv;
	}

	public DetalleValidadorDTO getIdReasignaVal() {
		return idReasignaVal;
	}

	public void setIdReasignaVal(DetalleValidadorDTO idReasignaVal) {
		this.idReasignaVal = idReasignaVal;
	}

	public Boolean getStPreSeleccion() {
		return stPreSeleccion;
	}

	public void setStPreSeleccion(Boolean stPreSeleccion) {
		this.stPreSeleccion = stPreSeleccion;
	}	
	   
}
