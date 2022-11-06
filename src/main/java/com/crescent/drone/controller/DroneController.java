package com.crescent.drone.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crescent.drone.exceptions.DroneBatteryException;
import com.crescent.drone.exceptions.DroneExistsException;
import com.crescent.drone.exceptions.DroneNotFoundException;
import com.crescent.drone.exceptions.DroneWeightException;
import com.crescent.drone.exceptions.InvalidDroneModelException;
import com.crescent.drone.exceptions.InvalidMedicineCodeException;
import com.crescent.drone.exceptions.InvalidMedicineNameException;
import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;
import com.crescent.drone.service.DroneService;

import static com.crescent.drone.constants.MedicineConstants.*;
import static com.crescent.drone.constants.DroneConstants.*;

@RestController
@RequestMapping(path = "/droneApi")
public class DroneController {
	
	@Autowired
	DroneService droneService;
	
	@GetMapping(path = "/getDroneMeds/{serialNumber}")
	public ResponseEntity<List<Medicine>>  getDroneMeds(@PathVariable String serialNumber) throws DroneNotFoundException{
		return new ResponseEntity<>(droneService.getDroneMeds(serialNumber),HttpStatus.OK);
	
	}
	
	@PostMapping(path = "/registerDrone")
	public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) throws DroneExistsException, InvalidDroneModelException {
		if(!Arrays.asList(DRONE_MODELS).contains(drone.getModel())) {
			throw new InvalidDroneModelException(INVALID_DRONE_MODEL);
		}
		return new ResponseEntity<>(droneService.registerDrone(drone),HttpStatus.OK);
	}
	
	@PostMapping(path="/addMedicine/{serialNumber}")
	public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine,@PathVariable String serialNumber) throws DroneNotFoundException, 
	DroneBatteryException, DroneWeightException, InvalidMedicineNameException, InvalidMedicineCodeException {
		
		if(!medicine.getName().matches("^[a-zA-Z0-9_-]*$")) {
			throw new InvalidMedicineNameException(INVALID_NAME);
		}
		else if(!medicine.getCode().matches("^[A-Z0-9_]*$")) {
			throw new InvalidMedicineCodeException(INVALID_CODE);
		}
		return new ResponseEntity<>(droneService.addMedicine(medicine.getName(), medicine.getWeight(), medicine.getCode(), 
				medicine.getImageUrl(), serialNumber),HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAvailableDrones")
	public ResponseEntity<List<Drone>> getAvailableDrones(){
		return new ResponseEntity<>(droneService.findAvailableDrones(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/getDroneBattery/{serialNumber}")
	public ResponseEntity<Object> getDroneBattery(@PathVariable String serialNumber) throws DroneNotFoundException{
		Map<String,Object > battery=new HashMap<>();
		battery.put("battery", droneService.getBattery(serialNumber));
		return new ResponseEntity<Object>(battery,HttpStatus.OK);
	}
	
}
