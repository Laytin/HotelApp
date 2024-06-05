package com.laytin.HotelApp.dao;

import com.laytin.HotelApp.models.AmenityEnum;
import com.laytin.HotelApp.models.Hotel;
import com.laytin.HotelApp.models.mongo.HotelMongo;
import com.laytin.HotelApp.models.mongo.MongoCounter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Profile("nosql")
@Component
public class HotelDAOMongo {
    private final MongoOperations mongoOperations;
    @Autowired
    public HotelDAOMongo(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    @Transactional()
    public int getNextSequence() {
        Query query = new Query(Criteria.where("_id").is("hotel"));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true).upsert(true);
        MongoCounter counter = mongoOperations.findAndModify(query, update, options, MongoCounter.class);
        return counter.getSeq();
    }
    @Transactional(readOnly = true)
    public List<HotelMongo> search(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities) {
        Query query = new Query();
        name.ifPresent(string -> query.addCriteria(Criteria.where("name").is(string)));
        brand.ifPresent(string -> query.addCriteria(Criteria.where("brand").is(string)));
        city.ifPresent(string -> query.addCriteria(Criteria.where("address.city").is(string)));
        country.ifPresent(string -> query.addCriteria(Criteria.where("address.country").is(string)));
        amenities.ifPresent(string -> {
            String g = string[0];
            AmenityEnum a2= AmenityEnum.getEnumByString(g);
            if(a2!=null)
                query.addCriteria(Criteria.where("amenities").is(a2.toString()));
            else try {
                query.addCriteria(Criteria.where("amenities").is(g));
            }catch (IllegalArgumentException e){
            }
        });
        return mongoOperations.find(query, HotelMongo.class);
    }
}
