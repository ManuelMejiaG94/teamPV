/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "salesdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salesdetail.findAll", query = "SELECT s FROM Salesdetail s"),
    @NamedQuery(name = "Salesdetail.findById", query = "SELECT s FROM Salesdetail s WHERE s.id = :id"),
    @NamedQuery(name = "Salesdetail.findByIntquantity", query = "SELECT s FROM Salesdetail s WHERE s.intquantity = :intquantity"),
    @NamedQuery(name = "Salesdetail.findByDobPrice", query = "SELECT s FROM Salesdetail s WHERE s.dobPrice = :dobPrice"),
    @NamedQuery(name = "Salesdetail.findByDobTotal", query = "SELECT s FROM Salesdetail s WHERE s.dobTotal = :dobTotal")})
public class Salesdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "intquantity", nullable = false)
    private int intquantity;
    @Basic(optional = false)
    @Column(name = "dobPrice", nullable = false)
    private double dobPrice;
    @Basic(optional = false)
    @Column(name = "dobTotal", nullable = false)
    private double dobTotal;
    @JoinColumn(name = "idSales", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sales idSales;
    @JoinColumn(name = "idProducto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product idProducto;

    public Salesdetail() {
    }

    public Salesdetail(Integer id) {
        this.id = id;
    }

    public Salesdetail(Integer id, int intquantity, double dobPrice, double dobTotal) {
        this.id = id;
        this.intquantity = intquantity;
        this.dobPrice = dobPrice;
        this.dobTotal = dobTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIntquantity() {
        return intquantity;
    }

    public void setIntquantity(int intquantity) {
        this.intquantity = intquantity;
    }

    public double getDobPrice() {
        return dobPrice;
    }

    public void setDobPrice(double dobPrice) {
        this.dobPrice = dobPrice;
    }

    public double getDobTotal() {
        return dobTotal;
    }

    public void setDobTotal(double dobTotal) {
        this.dobTotal = dobTotal;
    }

    public Sales getIdSales() {
        return idSales;
    }

    public void setIdSales(Sales idSales) {
        this.idSales = idSales;
    }

    public Product getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Product idProducto) {
        this.idProducto = idProducto;
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
        if (!(object instanceof Salesdetail)) {
            return false;
        }
        Salesdetail other = (Salesdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Salesdetail[ id=" + id + " ]";
    }
    
}
