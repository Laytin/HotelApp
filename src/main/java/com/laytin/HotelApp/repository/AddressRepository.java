package com.laytin.HotelApp.repository;

import com.laytin.HotelApp.models.Address;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Profile("sql")
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
