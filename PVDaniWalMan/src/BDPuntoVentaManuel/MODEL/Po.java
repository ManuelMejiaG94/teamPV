/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "po")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Po.findAll", query = "SELECT p FROM Po p"),
    @NamedQuery(name = "Po.findById", query = "SELECT p FROM Po p WHERE p.id = :id"),
    @NamedQuery(name = "Po.findByDobTotal", query = "SELECT p FROM Po p WHERE p.dobTotal = :dobTotal"),
    @NamedQuery(name = "Po.findByBitEstatus", query = "SELECT p FROM Po p WHERE p.bitEstatus = :bitEstatus")})
public class Po implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dobTotal")
    private double dobTotal;
    @Basic(optional = false)
    @Column(name = "bitEstatus")
    private int bitEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPo", fetch = FetchType.LAZY)
    private Collection<Podetail> podetailCollection;
    @JoinColumn(name = "idCurrency", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Currency idCurrency;
    @JoinColumn(name = "idSupplier", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier idSupplier;

    public Po() {
    }

    public Po(Integer id) {
        this.id = id;
    }

    public Po(Integer id, double dobTotal, int bitEstatus) {
        this.id = id;
        this.dobTotal = dobTotal;
        this.bitEstatus = bitEstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDobTotal() {
        return dobTotal;
    }

    public void setDobTotal(double dobTotal) {
        this.dobTotal = dobTotal;
    }

    public int getBitEstatus() {
        return bitEstatus;
    }

    public void setBitEstatus(int bitEstatus) {
        this.bitEstatus = bitEstatus;
    }

    @XmlTransient
    public Collection<Podetail> getPodetailCollection() {
        return podetailCollection;
    }

    public void setPodetailCollection(Collection<Podetail> podetailCollection) {
        this.podetailCollection = podetailCollection;
    }

    public Currency getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(Currency idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Supplier getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Supplier idSupplier) {
        this.idSupplier = idSupplier;
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
        if (!(object instanceof Po)) {
            return false;
        }
        Po other = (Po) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Po[ id=" + id + " ]";
    }
    
}
