/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entityclasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author -ZaferAYAN-
 */
@Entity
@Table(name = "Malzeme")
@XmlRootElement
@NamedQueries({ //ID, Ad, Fiyat, Uretici, Iskonto, Doviz
    @NamedQuery(name = "Malzeme.findAll", query = "SELECT m FROM Malzeme m"),
    @NamedQuery(name = "Malzeme.findByID", query = "SELECT m FROM Malzeme m WHERE m.mid = :ID"),
    @NamedQuery(name = "Malzeme.findByAd", query = "SELECT m FROM Malzeme m WHERE m.malzemeName = :Ad"),
    @NamedQuery(name = "Malzeme.findByFiyat", query = "SELECT m FROM Malzeme m WHERE m.malzemeFiyat = :Fiyat"),
    @NamedQuery(name = "Malzeme.findByUretici", query = "SELECT m FROM Malzeme m WHERE m.uretici = :Uretici"),
    @NamedQuery(name = "Malzeme.findByIskonto", query = "SELECT m FROM Malzeme m WHERE m.iskonto = :Iskonto"),
    @NamedQuery(name = "Malzeme.findByDoviz", query = "SELECT m FROM Malzeme m WHERE m.fiyatTuru = :Doviz")})
public class Malzeme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @Column(name = "MID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer mid;
    @Column(name = "MalzemeName")
    private String malzemeName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MalzemeFiyat")
    private Double malzemeFiyat;
    @Column(name = "Uretici")
    private String uretici;
    @Column(name = "Iskonto")
    private Double iskonto;
    @Column(name = "FiyatTuru")
    private String fiyatTuru;

    public Malzeme() {
    }

    public Malzeme(Integer mid) {
        this.mid = mid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMalzemeName() {
        return malzemeName;
    }

    public void setMalzemeName(String malzemeName) {
        this.malzemeName = malzemeName;
    }

    public Double getMalzemeFiyat() {
        return malzemeFiyat;
    }

    public void setMalzemeFiyat(Double malzemeFiyat) {
        this.malzemeFiyat = malzemeFiyat;
    }

    public String getUretici() {
        return uretici;
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    public Double getIskonto() {
        return iskonto;
    }

    public void setIskonto(Double iskonto) {
        this.iskonto = iskonto;
    }

    public String getFiyatTuru() {
        return fiyatTuru;
    }

    public void setFiyatTuru(String fiyatTuru) {
        this.fiyatTuru = fiyatTuru;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Malzeme)) {
            return false;
        }
        Malzeme other = (Malzeme) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Malzeme[ mid=" + mid + " ]";
    }
    
}
