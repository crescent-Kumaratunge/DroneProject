package com.crescent.drone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crescent.drone.constants.DroneConstants;
import com.crescent.drone.exceptions.DroneExistsException;
import com.crescent.drone.exceptions.DroneNotFoundException;
import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;
import com.crescent.drone.repository.DroneRepository;
import com.crescent.drone.repository.MedicineRepository;

@Service
public class DroneServiceImpl implements DroneService {
	
	@Autowired
	DroneRepository droneRepository;
	
	@Autowired
	MedicineRepository medicineRepository;

	@Override
	public List<Medicine> getDroneMeds(String serialNumber) throws DroneNotFoundException {
		Drone drone=droneRepository.findBySerialNumber(serialNumber);
		if(drone==null) {
			throw new DroneNotFoundException(DroneConstants.DRONE_NOT_FOUND+serialNumber);
		}
		return drone.getMedicines();
	}
	
	@Override
	public Drone registerDrone(Drone drone) throws DroneExistsException {
		if(droneRepository.findBySerialNumber(drone.getSerialNumber())!=null) {
			throw new DroneExistsException(DroneConstants.DRONE_EXISTS+drone.getSerialNumber());
		}
		return droneRepository.save(drone);
	}
	@Override
	public Medicine addMedicine(String name, long weight, String code, String imageUrl, String serialNumber) {
		Drone drone=droneRepository.findBySerialNumber(serialNumber);
		Medicine medicine=new Medicine(name, 200, code, imageUrl, drone);
		medicineRepository.save(medicine);
		return medicine;		
	}
	
	@Override
	public List<Drone> findAvailableDrones(){
		return droneRepository.findAvailableDrones(25, 500, "IDLE");
	}
	@Override
	public long getBattery(String serialNumber) {
		return droneRepository.findBySerialNumber(serialNumber).getBattery();
	}

}
