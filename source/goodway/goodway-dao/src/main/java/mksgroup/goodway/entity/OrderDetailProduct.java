/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThachLN
 */
@Entity
@Table(name = "goodway_order_detail_product", catalog = "goodway", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetailProduct.findAll", query = "SELECT o FROM OrderDetailProduct o")
    , @NamedQuery(name = "OrderDetailProduct.findById", query = "SELECT o FROM OrderDetailProduct o WHERE o.id = :id")
    , @NamedQuery(name = "OrderDetailProduct.findByProductName", query = "SELECT o FROM OrderDetailProduct o WHERE o.productName = :productName")
    , @NamedQuery(name = "OrderDetailProduct.findByPrice", query = "SELECT o FROM OrderDetailProduct o WHERE o.price = :price")
    , @NamedQuery(name = "OrderDetailProduct.findByQuantity", query = "SELECT o FROM OrderDetailProduct o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "OrderDetailProduct.findByCreated", query = "SELECT o FROM OrderDetailProduct o WHERE o.created = :created")
    , @NamedQuery(name = "OrderDetailProduct.findByCreatedbyUsername", query = "SELECT o FROM OrderDetailProduct o WHERE o.createdbyUsername = :createdbyUsername")
    , @NamedQuery(name = "OrderDetailProduct.findByLastmodified", query = "SELECT o FROM OrderDetailProduct o WHERE o.lastmodified = :lastmodified")
    , @NamedQuery(name = "OrderDetailProduct.findByLastmodifiedbyUsername", query = "SELECT o FROM OrderDetailProduct o WHERE o.lastmodifiedbyUsername = :lastmodifiedbyUsername")})
public class OrderDetailProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_name", nullable = false, length = 128)
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 22)
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
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
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "order_detail_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private OrderDetail orderDetailId;

    public OrderDetailProduct() {
    }

    public OrderDetailProduct(Integer id) {
        this.id = id;
    }

    public OrderDetailProduct(Integer id, String productName, Date created, String createdbyUsername) {
        this.id = id;
        this.productName = productName;
        this.created = created;
        this.createdbyUsername = createdbyUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public OrderDetail getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(OrderDetail orderDetailId) {
        this.orderDetailId = orderDetailId;
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
        if (!(object instanceof OrderDetailProduct)) {
            return false;
        }
        OrderDetailProduct other = (OrderDetailProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mksgroup.goodway.entity.OrderDetailProduct[ id=" + id + " ]";
    }
    
}
