package com.crescent.drone.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "drone")
public class Drone implements Serializable{
	@Id
	@Column(name = "drone_id")
	private int droneId;
	@Column(name = "serial_number")
	private String serialNumber;
	@Column(name = "model")
	private String model;
	@Column(name = "weight")
	private long weight;
	@Column(name = "battery")
	private long battery;
	@Column(name = "state")
	private String state;
	
	@OneToMany(mappedBy = "drone")
	@JsonIgnore
	private List<Medicine> medicines;

	public int getDroneId() {
		return droneId;
	}

	public void setDroneId(int droneId) {
		this.droneId = droneId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getBattery() {
		return battery;
	}

	public void setBattery(long battery) {
		this.battery = battery;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Drone(int droneId, String serialNumber, String model, long weight, long battery, String state,
			List<Medicine> medicines) {
		super();
		this.droneId = droneId;
		this.serialNumber = serialNumber;
		this.model = model;
		this.weight = weight;
		this.battery = battery;
		this.state = state;
		this.medicines = medicines;
	}

	public Drone() {		
	}
	
	
	
	
}
