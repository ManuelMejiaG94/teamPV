/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id"),
    @NamedQuery(name = "Request.findByDoubTotal", query = "SELECT r FROM Request r WHERE r.doubTotal = :doubTotal"),
    @NamedQuery(name = "Request.findByDatFecha", query = "SELECT r FROM Request r WHERE r.datFecha = :datFecha"),
    @NamedQuery(name = "Request.findByBitEstatus", query = "SELECT r FROM Request r WHERE r.bitEstatus = :bitEstatus")})
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "doubTotal", nullable = false)
    private double doubTotal;
    @Basic(optional = false)
    @Column(name = "datFecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datFecha;
    @Basic(optional = false)
    @Column(name = "bitEstatus", nullable = false)
    private int bitEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRequest")
    private Collection<Requestdetail> requestdetailCollection;
    @JoinColumn(name = "idCurrency", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Currency idCurrency;
    @JoinColumn(name = "idSuplier", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Supplier idSuplier;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, double doubTotal, Date datFecha, int bitEstatus) {
        this.id = id;
        this.doubTotal = doubTotal;
        this.datFecha = datFecha;
        this.bitEstatus = bitEstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDoubTotal() {
        return doubTotal;
    }

    public void setDoubTotal(double doubTotal) {
        this.doubTotal = doubTotal;
    }

    public Date getDatFecha() {
        return datFecha;
    }

    public void setDatFecha(Date datFecha) {
        this.datFecha = datFecha;
    }

    public int getBitEstatus() {
        return bitEstatus;
    }

    public void setBitEstatus(int bitEstatus) {
        this.bitEstatus = bitEstatus;
    }

    @XmlTransient
    public Collection<Requestdetail> getRequestdetailCollection() {
        return requestdetailCollection;
    }

    public void setRequestdetailCollection(Collection<Requestdetail> requestdetailCollection) {
        this.requestdetailCollection = requestdetailCollection;
    }

    public Currency getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Currency idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Supplier getIdSuplier() {
        return idSuplier;
    }

    public void setIdSuplier(Supplier idSuplier) {
        this.idSuplier = idSuplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Request[ id=" + id + " ]";
    }
    
}
