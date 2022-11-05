package com.crescent.drone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.crescent.drone.model.Medicine;
import com.crescent.drone.service.DroneService;

@RestController
public class DroneController {
	
	@Autowired
	DroneService droneService;
	
	@GetMapping(path = "/getDroneMeds/{serialNumber}")
	public List<Medicine> getDroneMeds(@PathVariable String serialNumber){
		return droneService.getDroneMeds(serialNumber);
	}
	
}
