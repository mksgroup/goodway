package mksgroup.goodway.model;

/**
 * Model for OrderDetailProduct
 * @author NamTang
 *
 */
public class OrderDetailProductModel {
    private Integer id;
    private String name;
    private String description;
    private Double height;
    private Double width;
    private Double length;
    private Double weight;
    private Integer quantity;
    /**
    * Get value of id.
    * @return the id
    */
    public Integer getId() {
        return id;
    }
    /**
    * Get value of name.
    * @return the name
    */
    public String getName() {
        return name;
    }
    /**
    * Get value of description.
    * @return the description
    */
    public String getDescription() {
        return description;
    }
    /**
    * Get value of height.
    * @return the height
    */
    public Double getHeight() {
        return height;
    }
    /**
    * Get value of width.
    * @return the width
    */
    public Double getWidth() {
        return width;
    }
    /**
    * Get value of length.
    * @return the length
    */
    public Double getLength() {
        return length;
    }
    /**
    * Get value of weight.
    * @return the weight
    */
    public Double getWeight() {
        return weight;
    }
    /**
    * Get value of quantity.
    * @return the quantity
    */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     * Set the value for id.
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Set the value for description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Set the value for height.
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }
    /**
     * Set the value for width.
     * @param width the width to set
     */
    public void setWidth(Double width) {
        this.width = width;
    }
    /**
     * Set the value for length.
     * @param length the length to set
     */
    public void setLength(Double length) {
        this.length = length;
    }
    /**
     * Set the value for weight.
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    /**
     * Set the value for quantity.
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
