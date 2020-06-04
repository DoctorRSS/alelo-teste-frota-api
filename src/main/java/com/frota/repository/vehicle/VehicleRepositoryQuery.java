package com.frota.repository.vehicle;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frota.entity.Vehicle;
import com.frota.repository.filter.VehicleFilter;

public interface VehicleRepositoryQuery {
	
	public Page<Vehicle> filtrar(VehicleFilter vehicleFilter, Pageable pageable);
	
	public List<Vehicle> buscarPorFiltro(VehicleFilter vehicleFilter);

}
