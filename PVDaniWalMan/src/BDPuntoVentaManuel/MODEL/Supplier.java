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
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findById", query = "SELECT s FROM Supplier s WHERE s.id = :id"),
    @NamedQuery(name = "Supplier.findByStrClave", query = "SELECT s FROM Supplier s WHERE s.strClave = :strClave"),
    @NamedQuery(name = "Supplier.findByStrBussinessName", query = "SELECT s FROM Supplier s WHERE s.strBussinessName = :strBussinessName"),
    @NamedQuery(name = "Supplier.findByStrNumber", query = "SELECT s FROM Supplier s WHERE s.strNumber = :strNumber"),
    @NamedQuery(name = "Supplier.findByStrDescripcion", query = "SELECT s FROM Supplier s WHERE s.strDescripcion = :strDescripcion"),
    @NamedQuery(name = "Supplier.findByStrAddress", query = "SELECT s FROM Supplier s WHERE s.strAddress = :strAddress"),
    @NamedQuery(name = "Supplier.findByBoolEstatus", query = "SELECT s FROM Supplier s WHERE s.boolEstatus = :boolEstatus")})
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strClave", nullable = false, length = 50)
    private String strClave;
    @Basic(optional = false)
    @Column(name = "strBussinessName", nullable = false, length = 50)
    private String strBussinessName;
    @Basic(optional = false)
    @Column(name = "strNumber", nullable = false, length = 50)
    private String strNumber;
    @Basic(optional = false)
    @Column(name = "strDescripcion", nullable = false, length = 50)
    private String strDescripcion;
    @Basic(optional = false)
    @Column(name = "strAddress", nullable = false, length = 25)
    private String strAddress;
    @Basic(optional = false)
    @Column(name = "boolEstatus", nullable = false)
    private boolean boolEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuplier")
    private Collection<Request> requestCollection;
    @JoinColumn(name = "idContacto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Contacto idContacto;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Catcategoria idCategoria;

    public Supplier() {
    }

    public Supplier(Integer id) {
        this.id = id;
    }

    public Supplier(Integer id, String strClave, String strBussinessName, String strNumber, String strDescripcion, String strAddress, boolean boolEstatus) {
        this.id = id;
        this.strClave = strClave;
        this.strBussinessName = strBussinessName;
        this.strNumber = strNumber;
        this.strDescripcion = strDescripcion;
        this.strAddress = strAddress;
        this.boolEstatus = boolEstatus;
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

    public String getStrBussinessName() {
        return strBussinessName;
    }

    public void setStrBussinessName(String strBussinessName) {
        this.strBussinessName = strBussinessName;
    }

    public String getStrNumber() {
        return strNumber;
    }

    public void setStrNumber(String strNumber) {
        this.strNumber = strNumber;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public boolean getBoolEstatus() {
        return boolEstatus;
    }

    public void setBoolEstatus(boolean boolEstatus) {
        this.boolEstatus = boolEstatus;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    public Contacto getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }

    public Catcategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Catcategoria idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Supplier[ id=" + id + " ]";
    }
    
}
