package com.frota.repository.vehicle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.frota.entity.Vehicle;
import com.frota.entity.Vehicle_;
import com.frota.repository.filter.VehicleFilter;

public class VehicleRepositoryImpl implements VehicleRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Vehicle> buscarPorFiltro(VehicleFilter vehicleFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteria = builder.createQuery(Vehicle.class);
		Root<Vehicle> root = criteria.from(Vehicle.class);	
		Predicate[] predicates = criarRestricoes(vehicleFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Vehicle> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public Page<Vehicle> filtrar(VehicleFilter vehicleFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteria = builder.createQuery(Vehicle.class);
		Root<Vehicle> root = criteria.from(Vehicle.class);	
		Predicate[] predicates = criarRestricoes(vehicleFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Vehicle> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query,pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(vehicleFilter));
	}

	private Predicate[] criarRestricoes(VehicleFilter vehicleFilter, CriteriaBuilder builder,
			Root<Vehicle> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(vehicleFilter.getPlate())) {
			predicates.add(builder.like(
					builder.upper(root.get(Vehicle_.plate)), "%" + vehicleFilter.getPlate().toUpperCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(vehicleFilter.getStatus())) {
			predicates.add(builder.equal(root.get(Vehicle_.status), vehicleFilter.getStatus()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(VehicleFilter vehicleFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Vehicle> root = criteria.from(Vehicle.class);
		
		Predicate[] predicates = criarRestricoes(vehicleFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
