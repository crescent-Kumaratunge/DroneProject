package com.crescent.drone.service;

import java.util.List;

import com.crescent.drone.exceptions.DroneExistsException;
import com.crescent.drone.exceptions.DroneNotFoundException;
import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;

public interface DroneService {
	public List<Medicine> getDroneMeds(String serialNumber) throws DroneNotFoundException;

	Drone registerDrone(Drone drone) throws DroneExistsException;

	Medicine addMedicine(String name, long weight, String code, String imageUrl, String serialNumber);

	List<Drone> findAvailableDrones();

	long getBattery(String serialNumber);
}
