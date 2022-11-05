package com.crescent.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crescent.drone.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
	
}
