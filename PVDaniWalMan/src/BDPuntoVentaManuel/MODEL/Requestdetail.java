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
@Table(name = "requestdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requestdetail.findAll", query = "SELECT r FROM Requestdetail r"),
    @NamedQuery(name = "Requestdetail.findById", query = "SELECT r FROM Requestdetail r WHERE r.id = :id"),
    @NamedQuery(name = "Requestdetail.findByDobPrice", query = "SELECT r FROM Requestdetail r WHERE r.dobPrice = :dobPrice"),
    @NamedQuery(name = "Requestdetail.findByDobQuantity", query = "SELECT r FROM Requestdetail r WHERE r.dobQuantity = :dobQuantity"),
    @NamedQuery(name = "Requestdetail.findByDobTotal", query = "SELECT r FROM Requestdetail r WHERE r.dobTotal = :dobTotal")})
public class Requestdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dobPrice", nullable = false)
    private double dobPrice;
    @Basic(optional = false)
    @Column(name = "dobQuantity", nullable = false)
    private double dobQuantity;
    @Basic(optional = false)
    @Column(name = "dobTotal", nullable = false)
    private double dobTotal;
    @JoinColumn(name = "idRequest", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Request idRequest;
    @JoinColumn(name = "idProduct", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product idProduct;

    public Requestdetail() {
    }

    public Requestdetail(Integer id) {
        this.id = id;
    }

    public Requestdetail(Integer id, double dobPrice, double dobQuantity, double dobTotal) {
        this.id = id;
        this.dobPrice = dobPrice;
        this.dobQuantity = dobQuantity;
        this.dobTotal = dobTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDobPrice() {
        return dobPrice;
    }

    public void setDobPrice(double dobPrice) {
        this.dobPrice = dobPrice;
    }

    public double getDobQuantity() {
        return dobQuantity;
    }

    public void setDobQuantity(double dobQuantity) {
        this.dobQuantity = dobQuantity;
    }

    public double getDobTotal() {
        return dobTotal;
    }

    public void setDobTotal(double dobTotal) {
        this.dobTotal = dobTotal;
    }

    public Request getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Request idRequest) {
        this.idRequest = idRequest;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
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
        if (!(object instanceof Requestdetail)) {
            return false;
        }
        Requestdetail other = (Requestdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Requestdetail[ id=" + id + " ]";
    }
    
}
