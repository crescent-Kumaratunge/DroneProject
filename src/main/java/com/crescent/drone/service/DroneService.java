package com.crescent.drone.service;

import java.util.List;

import com.crescent.drone.model.Medicine;

public interface DroneService {
	public List<Medicine> getDroneMeds(String serialNumber);
}
