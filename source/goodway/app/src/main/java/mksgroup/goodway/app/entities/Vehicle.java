package mksgroup.goodway.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "vehicle")
public class Vehicle {
	@Id
	private String id;
    private double volume;
    private String name;
    private double capacity;
    
    public Vehicle() {
    	
    }
    
	public Vehicle(double volume, String name, double capacity) {
		super();
		this.volume = volume;
		this.name = name;
		this.capacity = capacity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
    
    
}
