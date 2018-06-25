/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_customer", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cd", "version"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id")
    , @NamedQuery(name = "Customer.findByCd", query = "SELECT c FROM Customer c WHERE c.cd = :cd")
    , @NamedQuery(name = "Customer.findBySeqNo", query = "SELECT c FROM Customer c WHERE c.seqNo = :seqNo")
    , @NamedQuery(name = "Customer.findByVersion", query = "SELECT c FROM Customer c WHERE c.version = :version")
    , @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
    , @NamedQuery(name = "Customer.findByShortName", query = "SELECT c FROM Customer c WHERE c.shortName = :shortName")
    , @NamedQuery(name = "Customer.findByAddr", query = "SELECT c FROM Customer c WHERE c.addr = :addr")
    , @NamedQuery(name = "Customer.findByAddrId", query = "SELECT c FROM Customer c WHERE c.addrId = :addrId")
    , @NamedQuery(name = "Customer.findByRepresentative", query = "SELECT c FROM Customer c WHERE c.representative = :representative")
    , @NamedQuery(name = "Customer.findByRole", query = "SELECT c FROM Customer c WHERE c.role = :role")
    , @NamedQuery(name = "Customer.findByTaxId", query = "SELECT c FROM Customer c WHERE c.taxId = :taxId")
    , @NamedQuery(name = "Customer.findByNote", query = "SELECT c FROM Customer c WHERE c.note = :note")
    , @NamedQuery(name = "Customer.findByCellPhone", query = "SELECT c FROM Customer c WHERE c.cellPhone = :cellPhone")
    , @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone")
    , @NamedQuery(name = "Customer.findByFax", query = "SELECT c FROM Customer c WHERE c.fax = :fax")
    , @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")
    , @NamedQuery(name = "Customer.findByWeb", query = "SELECT c FROM Customer c WHERE c.web = :web")
    , @NamedQuery(name = "Customer.findByMandatoryChars", query = "SELECT c FROM Customer c WHERE c.mandatoryChars = :mandatoryChars")
    , @NamedQuery(name = "Customer.findByBusinessType", query = "SELECT c FROM Customer c WHERE c.businessType = :businessType")
    , @NamedQuery(name = "Customer.findByBusinessLine", query = "SELECT c FROM Customer c WHERE c.businessLine = :businessLine")
    , @NamedQuery(name = "Customer.findByFilename1", query = "SELECT c FROM Customer c WHERE c.filename1 = :filename1")
    , @NamedQuery(name = "Customer.findByFilename2", query = "SELECT c FROM Customer c WHERE c.filename2 = :filename2")
    , @NamedQuery(name = "Customer.findByFilename3", query = "SELECT c FROM Customer c WHERE c.filename3 = :filename3")
    , @NamedQuery(name = "Customer.findByCreated", query = "SELECT c FROM Customer c WHERE c.created = :created")
    , @NamedQuery(name = "Customer.findByCreatedbyUsername", query = "SELECT c FROM Customer c WHERE c.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "Customer.findByLastmodified", query = "SELECT c FROM Customer c WHERE c.lastmodified = :lastmodified")
    , @NamedQuery(name = "Customer.findByLastmodifiedbyUsername", query = "SELECT c FROM Customer c WHERE c.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cd", length = 64)
    private String cd;
    @Basic(optional = false)
    @Column(name = "seq_no", nullable = false)
    private int seqNo;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private int version = 1;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 128)
    private String name;
    @Basic(optional = false)
    @Column(name = "short_name", length = 30)
    private String shortName;
    @Column(name = "addr", length = 256)
    private String addr;
    
    @Column(name = "addr_id")
    private Integer addrId;
    
    @Column(name = "representative", length = 100)
    private String representative;
    @Column(name = "role", length = 100)
    private String role;
    @Column(name = "tax_id", length = 20)
    private String taxId;
    @Column(name = "note", length = 256)
    private String note;
    @Column(name = "cell_phone", length = 20)
    private String cellPhone;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "fax", length = 20)
    private String fax;
    @Column(name = "email", length = 64)
    private String email;
    @Column(name = "web", length = 128)
    private String web;
    @Column(name = "mandatory_chars", length = 30)
    private String mandatoryChars;
    @Column(name = "business_type", length = 30)
    private String businessType;
    @Column(name = "business_line", length = 30)
    private String businessLine;
    @Lob
    @Column(name = "attachment1")
    private byte[] attachment1;
    @Column(name = "filename1", length = 255)
    private String filename1;
    @Lob
    @Column(name = "attachment2")
    private byte[] attachment2;
    @Column(name = "filename2", length = 255)
    private String filename2;
    @Lob
    @Column(name = "attachment3")
    private byte[] attachment3;
    @Column(name = "filename3", length = 255)
    private String filename3;
    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "createdby_username", nullable = false, length = 50)
    private String createdbyUsername;
    @Column(name = "lastmodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;
    @Column(name = "lastmodifiedby_username", length = 50)
    private String lastmodifiedbyUsername;
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Address addressId;
    @JoinColumn(name = "address_id1", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Address addressId1;
    @JoinColumn(name = "address_id2", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Address addressId2;
    @JoinColumn(name = "address_id3", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Address addressId3;
    @JoinColumn(name = "address_id4", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Address addressId4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OrderMaster> orderMasterList;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String cd, int seqNo, int version, String name, String shortName, Date created, String createdbyUsername) {
        this.id = id;
        this.cd = cd;
        this.seqNo = seqNo;
        this.version = version;
        this.name = name;
        this.shortName = shortName;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
    * Get value of addr_id.
    * @return the addr_id
    */
    public Integer getAddrId() {
        return addrId;
    }

    /**
     * Set the value for addr_id.
     * @param addr_id the addr_id to set
     */
    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getMandatoryChars() {
        return mandatoryChars;
    }

    public void setMandatoryChars(String mandatoryChars) {
        this.mandatoryChars = mandatoryChars;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public byte[] getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(byte[] attachment1) {
        this.attachment1 = attachment1;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public byte[] getAttachment2() {
        return attachment2;
    }

    public void setAttachment2(byte[] attachment2) {
        this.attachment2 = attachment2;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public byte[] getAttachment3() {
        return attachment3;
    }

    public void setAttachment3(byte[] attachment3) {
        this.attachment3 = attachment3;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedbyUsername() {
        return createdbyUsername;
    }

    public void setCreatedbyUsername(String createdbyUsername) {
        this.createdbyUsername = createdbyUsername;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedbyUsername() {
        return lastmodifiedbyUsername;
    }

    public void setLastmodifiedbyUsername(String lastmodifiedbyUsername) {
        this.lastmodifiedbyUsername = lastmodifiedbyUsername;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public Address getAddressId1() {
        return addressId1;
    }

    public void setAddressId1(Address addressId1) {
        this.addressId1 = addressId1;
    }

    public Address getAddressId2() {
        return addressId2;
    }

    public void setAddressId2(Address addressId2) {
        this.addressId2 = addressId2;
    }

    public Address getAddressId3() {
        return addressId3;
    }

    public void setAddressId3(Address addressId3) {
        this.addressId3 = addressId3;
    }

    public Address getAddressId4() {
        return addressId4;
    }

    public void setAddressId4(Address addressId4) {
        this.addressId4 = addressId4;
    }

    @XmlTransient
    public List<OrderMaster> getOrderMasterList() {
        return orderMasterList;
    }

    public void setOrderMasterList(List<OrderMaster> orderMasterList) {
        this.orderMasterList = orderMasterList;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.Customer[ id=" + id + " ]";
    }
    
}
