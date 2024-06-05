package com.laytin.HotelApp.repository;

import com.laytin.HotelApp.models.Contacts;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Profile("sql")
@Repository
public interface ContactsRepository extends JpaRepository<Contacts,Integer> {
}
