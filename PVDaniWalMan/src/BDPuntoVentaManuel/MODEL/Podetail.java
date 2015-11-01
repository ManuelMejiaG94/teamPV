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
@Table(name = "podetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Podetail.findAll", query = "SELECT p FROM Podetail p"),
    @NamedQuery(name = "Podetail.findById", query = "SELECT p FROM Podetail p WHERE p.id = :id"),
    @NamedQuery(name = "Podetail.findByDobQuantity", query = "SELECT p FROM Podetail p WHERE p.dobQuantity = :dobQuantity"),
    @NamedQuery(name = "Podetail.findByDobTotal", query = "SELECT p FROM Podetail p WHERE p.dobTotal = :dobTotal"),
    @NamedQuery(name = "Podetail.findByDobPc", query = "SELECT p FROM Podetail p WHERE p.dobPc = :dobPc")})
public class Podetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dobQuantity", nullable = false)
    private double dobQuantity;
    @Basic(optional = false)
    @Column(name = "dobTotal", nullable = false)
    private double dobTotal;
    @Basic(optional = false)
    @Column(name = "dobPc", nullable = false)
    private double dobPc;
    @JoinColumn(name = "idPo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Po idPo;
    @JoinColumn(name = "idProducto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product idProducto;

    public Podetail() {
    }

    public Podetail(Integer id) {
        this.id = id;
    }

    public Podetail(Integer id, double dobQuantity, double dobTotal, double dobPc) {
        this.id = id;
        this.dobQuantity = dobQuantity;
        this.dobTotal = dobTotal;
        this.dobPc = dobPc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getDobPc() {
        return dobPc;
    }

    public void setDobPc(double dobPc) {
        this.dobPc = dobPc;
    }

    public Po getIdPo() {
        return idPo;
    }

    public void setIdPo(Po idPo) {
        this.idPo = idPo;
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
        if (!(object instanceof Podetail)) {
            return false;
        }
        Podetail other = (Podetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Podetail[ id=" + id + " ]";
    }
    
}
