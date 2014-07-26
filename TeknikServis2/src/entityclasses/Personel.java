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
@Table(name = "Personel")
@XmlRootElement
@NamedQueries({ 
    @NamedQuery(name = "Personel.findAll", query = "SELECT p FROM Personel p"),
    @NamedQuery(name = "Personel.findByID", query = "SELECT p FROM Personel p WHERE p.pid = :ID"),
    @NamedQuery(name = "Personel.findByAd", query = "SELECT p FROM Personel p WHERE p.pFName = :Ad"),
    @NamedQuery(name = "Personel.findBySoyad", query = "SELECT p FROM Personel p WHERE p.pLName = :Soyad"),
    @NamedQuery(name = "Personel.findByDtarihi", query = "SELECT p FROM Personel p WHERE p.pBDate = :Dtarihi"),
    @NamedQuery(name = "Personel.findByCinsiyet", query = "SELECT p FROM Personel p WHERE p.pGender = :Cinsiyet"),
    @NamedQuery(name = "Personel.findByMaas", query = "SELECT p FROM Personel p WHERE p.pSalary = :Maas"),
    @NamedQuery(name = "Personel.findByNufus", query = "SELECT p FROM Personel p WHERE p.pNufus = :Nufus")})
public class Personel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @Column(name = "PID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer pid;
    @Column(name = "PFName")
    private String pFName;
    @Column(name = "PLName")
    private String pLName;
    @Column(name = "PBDate")
    @Temporal(TemporalType.DATE)
    private Date pBDate;
    @Column(name = "PGender")
    private String pGender;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PSalary")
    private Double pSalary;
    @Column(name = "PNufus")
    private Integer pNufus;

    public Personel() {
    }

    public Personel(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPFName() {
        return pFName;
    }

    public void setPFName(String pFName) {
        this.pFName = pFName;
    }

    public String getPLName() {
        return pLName;
    }

    public void setPLName(String pLName) {
        this.pLName = pLName;
    }

    public Date getPBDate() {
        return pBDate;
    }

    public void setPBDate(Date pBDate) {
        this.pBDate = pBDate;
    }

    public String getPGender() {
        return pGender;
    }

    public void setPGender(String pGender) {
        this.pGender = pGender;
    }

    public Double getPSalary() {
        return pSalary;
    }

    public void setPSalary(Double pSalary) {
        this.pSalary = pSalary;
    }

    public Integer getPNufus() {
        return pNufus;
    }

    public void setPNufus(Integer pNufus) {
        this.pNufus = pNufus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personel)) {
            return false;
        }
        Personel other = (Personel) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Personel[ pid=" + pid + " ]";
    }
    
}
