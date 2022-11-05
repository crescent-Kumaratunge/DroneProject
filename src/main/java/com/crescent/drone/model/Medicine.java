package com.crescent.drone.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicine")
public class Medicine implements Serializable{
	@Id
	@Column(name = "medicine_id")
	private int medicineId;
	@Column(name = "name")
	private String name;
	@Column(name = "weight")
	private long weight;
	@Column(name = "code")
	private String code;
	@Column(name = "image_url")
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "drone_id")
	private Drone drone;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public Medicine(String name, long weight, String code, String imageUrl, Drone drone) {
		super();
		this.name = name;
		this.weight = weight;
		this.code = code;
		this.imageUrl = imageUrl;
		this.drone = drone;
	}

	public Medicine() {}
	
	
}
