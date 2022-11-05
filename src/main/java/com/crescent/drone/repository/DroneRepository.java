package com.crescent.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crescent.drone.model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Integer>{
	Drone findBySerialNumber(String serialNumber);
}
