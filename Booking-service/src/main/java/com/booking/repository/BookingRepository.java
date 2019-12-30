package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
