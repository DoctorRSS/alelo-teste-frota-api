package com.frota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frota.entity.Vehicle;
import com.frota.repository.vehicle.VehicleRepositoryQuery;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long>, VehicleRepositoryQuery {
	
}
