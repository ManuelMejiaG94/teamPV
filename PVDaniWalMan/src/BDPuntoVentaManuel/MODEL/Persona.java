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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id = :id"),
    @NamedQuery(name = "Persona.findByStrNombre", query = "SELECT p FROM Persona p WHERE p.strNombre = :strNombre"),
    @NamedQuery(name = "Persona.findByStrAPaterno", query = "SELECT p FROM Persona p WHERE p.strAPaterno = :strAPaterno"),
    @NamedQuery(name = "Persona.findByStrAMaterno", query = "SELECT p FROM Persona p WHERE p.strAMaterno = :strAMaterno")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strNombre", nullable = false, length = 50)
    private String strNombre;
    @Basic(optional = false)
    @Column(name = "strAPaterno", nullable = false, length = 50)
    private String strAPaterno;
    @Basic(optional = false)
    @Column(name = "strAMaterno", nullable = false, length = 50)
    private String strAMaterno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona", fetch = FetchType.LAZY)
    private Collection<Contacto> contactoCollection;

    public Persona() {
    }

    public Persona(Integer id) {
        this.id = id;
    }

    public Persona(Integer id, String strNombre, String strAPaterno, String strAMaterno) {
        this.id = id;
        this.strNombre = strNombre;
        this.strAPaterno = strAPaterno;
        this.strAMaterno = strAMaterno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrAPaterno() {
        return strAPaterno;
    }

    public void setStrAPaterno(String strAPaterno) {
        this.strAPaterno = strAPaterno;
    }

    public String getStrAMaterno() {
        return strAMaterno;
    }

    public void setStrAMaterno(String strAMaterno) {
        this.strAMaterno = strAMaterno;
    }

    @XmlTransient
    public Collection<Contacto> getContactoCollection() {
        return contactoCollection;
    }

    public void setContactoCollection(Collection<Contacto> contactoCollection) {
        this.contactoCollection = contactoCollection;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Persona[ id=" + id + " ]";
    }
    
}
