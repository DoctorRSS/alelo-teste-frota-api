package com.frota.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frota.entity.Vehicle;
import com.frota.event.RecursoCriadoEvent;
import com.frota.repository.filter.VehicleFilter;
import com.frota.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleResource {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Vehicle> pesquisar(VehicleFilter vehicleFilter, Pageable pageable) {
		return vehicleService.filtrarComPaginacao(vehicleFilter, pageable);
	}
	
	@GetMapping("/filter")
	public List<Vehicle> buscarPorFiltro(VehicleFilter vehicleFilter) {
		return vehicleService.buscarVeiculoComFiltro(vehicleFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> buscarPorId(@PathVariable Long id) {
		Vehicle vehicle = vehicleService.buscarPorId(id);
		return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Vehicle> criar( @RequestBody Vehicle vehicle, HttpServletResponse response){
		Vehicle veiculoSalvo = vehicleService.salvar(vehicle);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, veiculoSalvo.getId()));;		
		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> atualizar(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		Vehicle veiculoSalvo = vehicleService.atualizar(id, vehicle);
		return ResponseEntity.ok(veiculoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		vehicleService.remover(id);
	}
	
	
}
