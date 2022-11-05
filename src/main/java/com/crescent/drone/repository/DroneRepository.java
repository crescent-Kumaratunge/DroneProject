package com.crescent.drone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crescent.drone.model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Integer>{
	Drone findBySerialNumber(String serialNumber);
	
	@Query(value = "SELECT * FROM drone.drone where battery>=?1 and weight<?2 and state=?3",nativeQuery = true)
	List<Drone> findAvailableDrones(long battery, long weight,String state);
}
