package com.laytin.HotelApp.repository;

import com.laytin.HotelApp.models.Hotel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Optional<Hotel> findById(int id);
    //1+n solving
    @Query(value = "SELECT h FROM Hotel h LEFT JOIN FETCH h.address left join fetch h.contacts left join fetch h.amenities")
    List<Hotel> findAllHotels();
}
