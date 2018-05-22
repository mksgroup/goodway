/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mksgroup.goodway.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "goodyway_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "quantity", nullable = true)
    private int quantity;
    @Column(name = "logo", nullable = false)
    private String logo;
    
    /** Thể tích đã đóng gói. length × width × height. Đơn vị mỗi cạnh là m.*/
    @Column(name = "height", nullable = true)
    private double height;
    @Column(name = "width", nullable = true)
    private double width;
    @Column(name = "length", nullable = true)
    private double length;
    
    /** Trọng lượng đã đóng gói. Đơn vị tính là kg. */
    
    @Column(name = "weight", nullable = true)
    private double weight;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<OrderDetail> listOrderDetail;

    
    
    public Product() {
    	
    	
    	
    }
    
    
    

    /**
     * @param name
     * @param quantity
     * @param height
     * @param width
     * @param length
     * @param weight
     */
    public Product(String name, double length, double width, double height, double weight) {
        super();
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }




    public double getHeight() {
		return height;
	}




	public void setHeight(double height) {
		this.height = height;
	}




	public double getWidth() {
		return width;
	}




	public void setWidth(double width) {
		this.width = width;
	}




	public double getLength() {
		return length;
	}




	public void setLength(double length) {
		this.length = length;
	}




	public double getWeight() {
		return weight;
	}




	public void setWeight(double weight) {
		this.weight = weight;
	}




	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
