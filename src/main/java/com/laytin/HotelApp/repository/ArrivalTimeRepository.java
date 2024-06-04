package com.laytin.HotelApp.repository;

import com.laytin.HotelApp.models.ArrivalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime,Integer> {
}
