/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityclasses;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author -ZaferAYAN-
 */
@Entity
@Table(name = "Musteri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musteri.findAll", query = "SELECT m FROM Musteri m"),
    @NamedQuery(name = "Musteri.findByID", query = "SELECT m FROM Musteri m WHERE m.id = :ID"),
    @NamedQuery(name = "Musteri.findByAd", query = "SELECT m FROM Musteri m WHERE m.fName = :Ad"),
    @NamedQuery(name = "Musteri.findBySoyad", query = "SELECT m FROM Musteri m WHERE m.lName = :Soyad"),
    @NamedQuery(name = "Musteri.findByAdSoyad", query = "SELECT m FROM Musteri m WHERE m.fName = :Ad AND m.lName = :Soyad" ),
    @NamedQuery(name = "Musteri.findByDtarihi", query = "SELECT m FROM Musteri m WHERE m.bDate = :Dtarihi"),
    @NamedQuery(name = "Musteri.findByCinsiyet", query = "SELECT m FROM Musteri m WHERE m.gender = :Cinsiyet"),
    @NamedQuery(name = "Musteri.findByMeslek", query = "SELECT m FROM Musteri m WHERE m.job = :Meslek")})
public class Musteri implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musteriID")
    private Collection<Islem> islemCollection;
    @Id   
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer id;
    @Column(name = "FName")
    private String fName;
    @Column(name = "LName")
    private String lName;
    @Column(name = "BDate")
    @Temporal(TemporalType.DATE)
    private Date bDate;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Job")
    private String job;

    public Musteri() {
    }

    public Musteri(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        String oldFName = this.fName;
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        String oldLName = this.lName;
        this.lName = lName;
    }

    public Date getBDate() {
        return bDate;
    }

    public void setBDate(Date bDate) {
        Date oldBDate = this.bDate;
        this.bDate = bDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        String oldGender = this.gender;
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        String oldJob = this.job;
        this.job = job;
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
        if (!(object instanceof Musteri)) {
            return false;
        }
        Musteri other = (Musteri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Musteri[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    @XmlTransient
    public Collection<Islem> getIslemCollection() {
        return islemCollection;
    }

    public void setIslemCollection(Collection<Islem> islemCollection) {
        this.islemCollection = islemCollection;
    }

    
}
