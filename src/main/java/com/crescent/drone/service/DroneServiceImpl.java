package com.crescent.drone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crescent.drone.constants.DroneConstants;
import com.crescent.drone.exceptions.DroneBatteryException;
import com.crescent.drone.exceptions.DroneExistsException;
import com.crescent.drone.exceptions.DroneNotFoundException;
import com.crescent.drone.exceptions.DroneWeightException;
import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;
import com.crescent.drone.repository.DroneRepository;
import com.crescent.drone.repository.MedicineRepository;
import static com.crescent.drone.constants.DroneConstants.*;

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
			throw new DroneNotFoundException(DRONE_NOT_FOUND+serialNumber);
		}
		return drone.getMedicines();
	}
	
	@Override
	public Drone registerDrone(Drone drone) throws DroneExistsException {
		if(droneRepository.findBySerialNumber(drone.getSerialNumber())!=null) {
			throw new DroneExistsException(DRONE_EXISTS+drone.getSerialNumber());
		}
		return droneRepository.save(drone);
	}
	@Override
	public Medicine addMedicine(String name, long weight, String code, String imageUrl, String serialNumber) 
			throws DroneNotFoundException, DroneBatteryException, DroneWeightException {
		Drone drone=droneRepository.findBySerialNumber(serialNumber);
		if(drone==null) {
			throw new DroneNotFoundException(DRONE_NOT_FOUND+serialNumber);
		}
		if(drone.getBattery()<MIN_BATTERY) {
			throw new DroneBatteryException(DRONE_BATTERY_LOW);
		}
		if(drone.getWeight()+weight>MAX_WEIGHT) {
			throw new DroneWeightException(DRONE_WEIGT_LOW);
		}
		Medicine medicine=new Medicine(name, weight, code, imageUrl, drone);
		drone.setWeight(drone.getWeight()-weight);
		medicineRepository.save(medicine);
		droneRepository.save(drone);
		return medicine;		
	}
	
	@Override
	public List<Drone> findAvailableDrones(){
		return droneRepository.findAvailableDrones(DroneConstants.MIN_BATTERY, DroneConstants.MAX_WEIGHT, "IDLE");
	}
	@Override
	public long getBattery(String serialNumber) throws DroneNotFoundException {
		Drone drone= droneRepository.findBySerialNumber(serialNumber);
		if(drone==null) {
			throw new DroneNotFoundException(DRONE_NOT_FOUND+serialNumber);
		}
		return drone.getBattery();
	}

}
