/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityclasses;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author -ZaferAYAN-
 */
@Entity
@Table(name = "Doviz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doviz.findAll", query = "SELECT d FROM Doviz d"),
    @NamedQuery(name = "Doviz.findByID", query = "SELECT d FROM Doviz d WHERE d.did = :ID"),
    @NamedQuery(name = "Doviz.findByAd", query = "SELECT d FROM Doviz d WHERE d.dovizName = :Ad"),
    @NamedQuery(name = "Doviz.findByKur", query = "SELECT d FROM Doviz d WHERE d.dovizKur = :Kur")})
public class Doviz implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dovizTuru")
    private Collection<Islem> islemCollection;
    @Id   
    @Column(name = "DID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer did;
    @Column(name = "DovizName")
    private String dovizName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DovizKur")
    private Double dovizKur;

    public Doviz() {
    }

    public Doviz(Integer did) {
        this.did = did;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        Integer oldDid = this.did;
        this.did = did;
        changeSupport.firePropertyChange("did", oldDid, did);
    }

    public String getDovizName() {
        return dovizName;
    }

    public void setDovizName(String dovizName) {
        String oldDovizName = this.dovizName;
        this.dovizName = dovizName;
        changeSupport.firePropertyChange("dovizName", oldDovizName, dovizName);
    }

    public Double getDovizKur() {
        return dovizKur;
    }

    public void setDovizKur(Double dovizKur) {
        Double oldDovizKur = this.dovizKur;
        this.dovizKur = dovizKur;
        changeSupport.firePropertyChange("dovizKur", oldDovizKur, dovizKur);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (did != null ? did.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doviz)) {
            return false;
        }
        Doviz other = (Doviz) object;
        if ((this.did == null && other.did != null) || (this.did != null && !this.did.equals(other.did))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Doviz[ did=" + did + " ]";
    }

    @XmlTransient
    public Collection<Islem> getIslemCollection() {
        return islemCollection;
    }

    public void setIslemCollection(Collection<Islem> islemCollection) {
        this.islemCollection = islemCollection;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    
}
