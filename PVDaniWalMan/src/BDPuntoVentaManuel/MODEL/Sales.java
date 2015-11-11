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
@Table(name = "sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findById", query = "SELECT s FROM Sales s WHERE s.id = :id"),
    @NamedQuery(name = "Sales.findByDobTotal", query = "SELECT s FROM Sales s WHERE s.dobTotal = :dobTotal"),
    @NamedQuery(name = "Sales.findByDatFecha", query = "SELECT s FROM Sales s WHERE s.datFecha = :datFecha")})
public class Sales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dobTotal")
    private double dobTotal;
    @Column(name = "datFecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSales")
    private Collection<Salesdetail> salesdetailCollection;

    public Sales() {
    }

    public Sales(Integer id) {
        this.id = id;
    }

    public Sales(Integer id, double dobTotal) {
        this.id = id;
        this.dobTotal = dobTotal;
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

    public Date getDatFecha() {
        return datFecha;
    }

    public void setDatFecha(Date datFecha) {
        this.datFecha = datFecha;
    }

    @XmlTransient
    public Collection<Salesdetail> getSalesdetailCollection() {
        return salesdetailCollection;
    }

    public void setSalesdetailCollection(Collection<Salesdetail> salesdetailCollection) {
        this.salesdetailCollection = salesdetailCollection;
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
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Sales[ id=" + id + " ]";
    }
    
}
