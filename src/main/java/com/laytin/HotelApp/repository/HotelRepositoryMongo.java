package com.laytin.HotelApp.repository;

import com.laytin.HotelApp.models.mongo.HotelMongo;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Profile("nosql")
@Repository
public interface HotelRepositoryMongo extends MongoRepository<HotelMongo,Integer> {
    Optional<HotelMongo> findById(int id);
}
