package com.flight.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
	@Query("SELECT f FROM Flight f WHERE f.airport_from = :airport_from")
	public List<Flight> findBySourceCity(@Param("airport_from") String airport_from);
}
