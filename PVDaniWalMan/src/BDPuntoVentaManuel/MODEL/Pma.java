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
@Table(name = "pma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pma.findAll", query = "SELECT p FROM Pma p"),
    @NamedQuery(name = "Pma.findById", query = "SELECT p FROM Pma p WHERE p.id = :id"),
    @NamedQuery(name = "Pma.findByProInsert", query = "SELECT p FROM Pma p WHERE p.proInsert = :proInsert"),
    @NamedQuery(name = "Pma.findByProUpdate", query = "SELECT p FROM Pma p WHERE p.proUpdate = :proUpdate"),
    @NamedQuery(name = "Pma.findByProSelect", query = "SELECT p FROM Pma p WHERE p.proSelect = :proSelect"),
    @NamedQuery(name = "Pma.findByProInavilited", query = "SELECT p FROM Pma p WHERE p.proInavilited = :proInavilited")})
public class Pma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "proInsert")
    private boolean proInsert;
    @Basic(optional = false)
    @Column(name = "proUpdate")
    private boolean proUpdate;
    @Basic(optional = false)
    @Column(name = "proSelect")
    private boolean proSelect;
    @Basic(optional = false)
    @Column(name = "proInavilited")
    private boolean proInavilited;
    @JoinColumn(name = "idperfil", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Perfil idperfil;
    @JoinColumn(name = "idmodulo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modulo idmodulo;

    public Pma() {
    }

    public Pma(Integer id) {
        this.id = id;
    }

    public Pma(Integer id, boolean proInsert, boolean proUpdate, boolean proSelect, boolean proInavilited) {
        this.id = id;
        this.proInsert = proInsert;
        this.proUpdate = proUpdate;
        this.proSelect = proSelect;
        this.proInavilited = proInavilited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getProInsert() {
        return proInsert;
    }

    public void setProInsert(boolean proInsert) {
        this.proInsert = proInsert;
    }

    public boolean getProUpdate() {
        return proUpdate;
    }

    public void setProUpdate(boolean proUpdate) {
        this.proUpdate = proUpdate;
    }

    public boolean getProSelect() {
        return proSelect;
    }

    public void setProSelect(boolean proSelect) {
        this.proSelect = proSelect;
    }

    public boolean getProInavilited() {
        return proInavilited;
    }

    public void setProInavilited(boolean proInavilited) {
        this.proInavilited = proInavilited;
    }

    public Perfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Perfil idperfil) {
        this.idperfil = idperfil;
    }

    public Modulo getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Modulo idmodulo) {
        this.idmodulo = idmodulo;
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
        if (!(object instanceof Pma)) {
            return false;
        }
        Pma other = (Pma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Pma[ id=" + id + " ]";
    }
    
}
