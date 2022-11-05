package com.crescent.drone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;
import com.crescent.drone.repository.DroneRepository;

@Service
public class DroneServiceImpl implements DroneService {
	
	@Autowired
	DroneRepository droneRepository;

	@Override
	public List<Medicine> getDroneMeds(String serialNumber) {
		Drone drone=droneRepository.findBySerialNumber(serialNumber);
		return drone.getMedicines();
	}

}
