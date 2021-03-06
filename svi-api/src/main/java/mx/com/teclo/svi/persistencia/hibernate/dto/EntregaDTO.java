package mx.com.teclo.svi.persistencia.hibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author unitis02
 */
@Entity
@Table(name = "TCI001D_PT_ENTREGA")

public class EntregaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ENTREGA")
    private Long idEntrega;
    
    @Column(name = "NB_ENTREGA")
    private String nbEntrega;
    
    @Column(name = "FH_RECEPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhRecepcion;
    
    @Column(name = "FH_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhEntrega;
    
    @Column(name = "NU_CANTIDAD_PTS")
    private Long nuCantidadPts;
    
    @Column(name = "NU_CANTIDAD_CSV")
    private Long nuCantidadCsv;
    
    @Column(name = "NU_TOTAL_REG_CSV")
    private Long nuTotalRegCsv;
    
    @Column(name = "NU_TOTAL_REG_IMG")
    private Long nuTotalRegImg;
    
    @Column(name = "NU_TOTAL_REG_SIL")
    private Long nuTotalRegSil;
    
    @Column(name = "TX_ENTREGA")
    private String txEntrega;
    
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
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntrega")
//    private Collection<LoteDTO> loteCollection;

    public EntregaDTO() {
    }

    public EntregaDTO(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public EntregaDTO(Long idEntrega, short stActivo, Date fhCreacion, long idUsrCreacion, Date fhModificacion, long idUsrModifica) {
        this.idEntrega = idEntrega;
        this.stActivo = stActivo;
        this.fhCreacion = fhCreacion;
        this.idUsrCreacion = idUsrCreacion;
        this.fhModificacion = fhModificacion;
        this.idUsrModifica = idUsrModifica;
    }

    public Long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNbEntrega() {
        return nbEntrega;
    }

    public void setNbEntrega(String nbEntrega) {
        this.nbEntrega = nbEntrega;
    }

    public Date getFhRecepcion() {
        return fhRecepcion;
    }

    public void setFhRecepcion(Date fhRecepcion) {
        this.fhRecepcion = fhRecepcion;
    }

    public Date getFhEntrega() {
        return fhEntrega;
    }

    public void setFhEntrega(Date fhEntrega) {
        this.fhEntrega = fhEntrega;
    }

    public Long getNuCantidadPts() {
        return nuCantidadPts;
    }

    public void setNuCantidadPts(Long nuCantidadPts) {
        this.nuCantidadPts = nuCantidadPts;
    }

    public Long getNuCantidadCsv() {
        return nuCantidadCsv;
    }

    public void setNuCantidadCsv(Long nuCantidadCsv) {
        this.nuCantidadCsv = nuCantidadCsv;
    }

    public Long getNuTotalRegCsv() {
        return nuTotalRegCsv;
    }

    public void setNuTotalRegCsv(Long nuTotalRegCsv) {
        this.nuTotalRegCsv = nuTotalRegCsv;
    }

    public Long getNuTotalRegImg() {
        return nuTotalRegImg;
    }

    public void setNuTotalRegImg(Long nuTotalRegImg) {
        this.nuTotalRegImg = nuTotalRegImg;
    }

    public Long getNuTotalRegSil() {
        return nuTotalRegSil;
    }

    public void setNuTotalRegSil(Long nuTotalRegSil) {
        this.nuTotalRegSil = nuTotalRegSil;
    }

    public String getTxEntrega() {
        return txEntrega;
    }

    public void setTxEntrega(String txEntrega) {
        this.txEntrega = txEntrega;
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

//    @XmlTransient
//    public Collection<LoteDTO> getLoteCollection() {
//        return loteCollection;
//    }
//
//    public void setLoteCollection(Collection<LoteDTO> loteCollection) {
//        this.loteCollection = loteCollection;
//    }
}

