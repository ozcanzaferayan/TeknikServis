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
@Table(name = "TeknikServis")
@XmlRootElement
@NamedQueries({ 
//    ID, Ad, Address, Telefon
    @NamedQuery(name = "TeknikServis.findAll", query = "SELECT t FROM TeknikServis t"),
    @NamedQuery(name = "TeknikServis.findByID", query = "SELECT t FROM TeknikServis t WHERE t.sid = :ID"),
    @NamedQuery(name = "TeknikServis.findByAd", query = "SELECT t FROM TeknikServis t WHERE t.serviceName = :Ad"),
    @NamedQuery(name = "TeknikServis.findByAddress", query = "SELECT t FROM TeknikServis t WHERE t.serviceAddress = :Address"),
    @NamedQuery(name = "TeknikServis.findByTelefon", query = "SELECT t FROM TeknikServis t WHERE t.serviceTel = :Telefon")})
public class TeknikServis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @Column(name = "SID", nullable = false)
    @GeneratedValue(generator="MySeq")
    @SequenceGenerator(name="MySeq",sequenceName="seq_Name", allocationSize=1)
    private Integer sid;
    @Column(name = "ServiceName")
    private String serviceName;
    @Column(name = "ServiceAddress")
    private String serviceAddress;
    @Column(name = "ServiceTel")
    private String serviceTel;

    public TeknikServis() {
    }

    public TeknikServis(Integer sid) {
        this.sid = sid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeknikServis)) {
            return false;
        }
        TeknikServis other = (TeknikServis) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.TeknikServis[ sid=" + sid + " ]";
    }
    
}
