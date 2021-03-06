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
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strClave")
    private String strClave;
    @Basic(optional = false)
    @Column(name = "strName")
    private String strName;
    @Column(name = "strDescription")
    private String strDescription;
    @Basic(optional = false)
    @Column(name = "dobPV")
    private double dobPV;
    @Basic(optional = false)
    @Column(name = "donPC")
    private double donPC;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "intStock")
    private Double intStock;
    @Basic(optional = false)
    @Column(name = "strPresentation")
    private String strPresentation;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
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

    public Product(Integer id, String strClave, String strName, double dobPV, double donPC, String strPresentation) {
        this.id = id;
        this.strClave = strClave;
        this.strName = strName;
        this.dobPV = dobPV;
        this.donPC = donPC;
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

    public Double getIntStock() {
        return intStock;
    }

    public void setIntStock(Double intStock) {
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
        return strName;
    }
    
}
