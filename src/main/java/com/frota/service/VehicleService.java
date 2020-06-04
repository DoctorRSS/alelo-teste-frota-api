package com.frota.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frota.entity.Vehicle;
import com.frota.repository.VehicleRepository;
import com.frota.repository.filter.VehicleFilter;
import com.frota.service.exception.PlacaJaExistenteException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Vehicle> buscarVeiculoComFiltro(VehicleFilter vehicleFilter) {
		return vehicleRepository.buscarPorFiltro(vehicleFilter);
	}

	public Page<Vehicle> filtrarComPaginacao(VehicleFilter vehicleFilter, Pageable pageable) {
		return vehicleRepository.filtrar(vehicleFilter, pageable);
	}

	public Vehicle buscarPorId(Long id) {
		Optional<Vehicle> optVehicle = vehicleRepository.findById(id);
		if(optVehicle.isPresent()) {
			return optVehicle.get();
		} else {
			throw new IllegalArgumentException(); 
		}
	}

	public Vehicle salvar(Vehicle vehicle) {
		VehicleFilter vehicleFilter = new VehicleFilter();
		vehicleFilter.setPlate(vehicle.getPlate());
		List<Vehicle> lista = buscarVeiculoComFiltro(vehicleFilter);
		if(lista.isEmpty()) {
			return vehicleRepository.save(vehicle);
		} else {
			throw new PlacaJaExistenteException();
		}
	}

	public void remover(Long id) {
		vehicleRepository.deleteById(id);
	}

	public Vehicle atualizar(Long id, Vehicle vehicle) {
		Vehicle veiculoSalvo = buscarPorId(id);
		BeanUtils.copyProperties(vehicle, veiculoSalvo, "id");
		return vehicleRepository.save(veiculoSalvo);
	}
}
