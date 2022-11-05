package com.crescent.drone.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.crescent.drone.exceptions.DroneBatteryException;
import com.crescent.drone.exceptions.DroneExistsException;
import com.crescent.drone.exceptions.DroneNotFoundException;
import com.crescent.drone.exceptions.DroneWeightException;
import com.crescent.drone.model.Drone;
import com.crescent.drone.model.Medicine;
import com.crescent.drone.service.DroneService;

@RestController
public class DroneController {
	
	@Autowired
	DroneService droneService;
	
	@GetMapping(path = "/getDroneMeds/{serialNumber}")
	public List<Medicine> getDroneMeds(@PathVariable String serialNumber) throws DroneNotFoundException{
		return droneService.getDroneMeds(serialNumber);
	}
	
	@PostMapping(path = "/registerDrone")
	public Drone registerDrone(@RequestBody Drone drone) throws DroneExistsException {
		return droneService.registerDrone(drone);
	}
	
	@PostMapping(path="/addMedicine/{serialNumber}")
	public Medicine addMedicine(@RequestBody Medicine medicine,@PathVariable String serialNumber) throws DroneNotFoundException, DroneBatteryException, DroneWeightException {
		return droneService.addMedicine(medicine.getName(), medicine.getWeight(), medicine.getCode(), 
				medicine.getImageUrl(), serialNumber);
	}
	
	@GetMapping(path = "/getAvailableDrones")
	public List<Drone> getAvailableDrones(){
		return droneService.findAvailableDrones();
	}
	
	@GetMapping(path = "/getDroneBattery/{serialNumber}")
	public ResponseEntity<Object> getDroneBattery(@PathVariable String serialNumber) throws DroneNotFoundException{
		Map<String,Object > battery=new HashMap<>();
		battery.put("battery", droneService.getBattery(serialNumber));
		return new ResponseEntity<Object>(battery,HttpStatus.OK);
	}
	
}
