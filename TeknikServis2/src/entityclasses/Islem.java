/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityclasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author -ZaferAYAN-
 */
@Entity
@Table(name = "Islem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Islem.findAll", query = "SELECT i FROM Islem i"),
    @NamedQuery(name = "Islem.findById", query = "SELECT i FROM Islem i WHERE i.id = :id"),
    @NamedQuery(name = "Islem.findByTarih", query = "SELECT i FROM Islem i WHERE i.tarih = :tarih"),
    @NamedQuery(name = "Islem.findByTarihler", query = "SELECT i FROM Islem i WHERE i.tarih BETWEEN :tarih1 AND :tarih2"),
    @NamedQuery(name = "Islem.findByOdenen", query = "SELECT i FROM Islem i WHERE i.odenen = :odenen")})
public class Islem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer id;
    @Column(name = "Tarih")
    @Temporal(TemporalType.DATE)
    private Date tarih;
    @Lob
    @Column(name = "YapilanIslem")
    private String yapilanIslem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Odenen")
    private Double odenen;
    @JoinColumn(name = "MusteriID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Musteri musteriID;
    @JoinColumn(name = "DovizTuru", referencedColumnName = "DID")
    @ManyToOne(optional = false)
    private Doviz dovizTuru;

    public Islem() {
    }

    public Islem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getYapilanIslem() {
        return yapilanIslem;
    }

    public void setYapilanIslem(String yapilanIslem) {
        this.yapilanIslem = yapilanIslem;
    }

    public Double getOdenen() {
        return odenen;
    }

    public void setOdenen(Double odenen) {
        this.odenen = odenen;
    }

    public Musteri getMusteriID() {
        return musteriID;
    }

    public void setMusteriID(Musteri musteriID) {
        this.musteriID = musteriID;
    }

    public Doviz getDovizTuru() {
        return dovizTuru;
    }

    public void setDovizTuru(Doviz dovizTuru) {
        this.dovizTuru = dovizTuru;
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
        if (!(object instanceof Islem)) {
            return false;
        }
        Islem other = (Islem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Islem[ id=" + id + " ]";
    }
    
}
