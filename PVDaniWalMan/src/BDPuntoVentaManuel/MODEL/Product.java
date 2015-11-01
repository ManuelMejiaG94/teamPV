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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByStrClave", query = "SELECT p FROM Product p WHERE p.strClave = :strClave"),
    @NamedQuery(name = "Product.findByStrName", query = "SELECT p FROM Product p WHERE p.strName = :strName"),
    @NamedQuery(name = "Product.findByStrDescription", query = "SELECT p FROM Product p WHERE p.strDescription = :strDescription"),
    @NamedQuery(name = "Product.findByDobPV", query = "SELECT p FROM Product p WHERE p.dobPV = :dobPV"),
    @NamedQuery(name = "Product.findByDonPC", query = "SELECT p FROM Product p WHERE p.donPC = :donPC"),
    @NamedQuery(name = "Product.findByIntStock", query = "SELECT p FROM Product p WHERE p.intStock = :intStock"),
    @NamedQuery(name = "Product.findByStrPresentation", query = "SELECT p FROM Product p WHERE p.strPresentation = :strPresentation")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strClave", nullable = false, length = 30)
    private String strClave;
    @Basic(optional = false)
    @Column(name = "strName", nullable = false, length = 50)
    private String strName;
    @Basic(optional = false)
    @Column(name = "strDescription", nullable = false, length = 50)
    private String strDescription;
    @Basic(optional = false)
    @Column(name = "dobPV", nullable = false)
    private double dobPV;
    @Basic(optional = false)
    @Column(name = "donPC", nullable = false)
    private double donPC;
    @Basic(optional = false)
    @Column(name = "intStock", nullable = false)
    private int intStock;
    @Basic(optional = false)
    @Column(name = "strPresentation", nullable = false, length = 50)
    private String strPresentation;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Catcategoria idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<Salesdetail> salesdetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<Podetail> podetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduct")
    private Collection<Requestdetail> requestdetailCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String strClave, String strName, String strDescription, double dobPV, double donPC, int intStock, String strPresentation) {
        this.id = id;
        this.strClave = strClave;
        this.strName = strName;
        this.strDescription = strDescription;
        this.dobPV = dobPV;
        this.donPC = donPC;
        this.intStock = intStock;
        this.strPresentation = strPresentation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrClave() {
        return strClave;
    }

    public void setStrClave(String strClave) {
        this.strClave = strClave;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public double getDobPV() {
        return dobPV;
    }

    public void setDobPV(double dobPV) {
        this.dobPV = dobPV;
    }

    public double getDonPC() {
        return donPC;
    }

    public void setDonPC(double donPC) {
        this.donPC = donPC;
    }

    public int getIntStock() {
        return intStock;
    }

    public void setIntStock(int intStock) {
        this.intStock = intStock;
    }

    public String getStrPresentation() {
        return strPresentation;
    }

    public void setStrPresentation(String strPresentation) {
        this.strPresentation = strPresentation;
    }

    public Catcategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Catcategoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public Collection<Salesdetail> getSalesdetailCollection() {
        return salesdetailCollection;
    }

    public void setSalesdetailCollection(Collection<Salesdetail> salesdetailCollection) {
        this.salesdetailCollection = salesdetailCollection;
    }

    @XmlTransient
    public Collection<Podetail> getPodetailCollection() {
        return podetailCollection;
    }

    public void setPodetailCollection(Collection<Podetail> podetailCollection) {
        this.podetailCollection = podetailCollection;
    }

    @XmlTransient
    public Collection<Requestdetail> getRequestdetailCollection() {
        return requestdetailCollection;
    }

    public void setRequestdetailCollection(Collection<Requestdetail> requestdetailCollection) {
        this.requestdetailCollection = requestdetailCollection;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Product[ id=" + id + " ]";
    }
    
}
