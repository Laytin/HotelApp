package com.laytin.HotelApp.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("nosql")
@Component
public class HistogramDAOMongo {
    private final MongoOperations mongoOperations;
    @Autowired
    public HistogramDAOMongo(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    @Transactional(readOnly = true)
    public Map<String, Long> counterBy(String type) {
        Map<String, Long> mapp = new HashMap<>();
        String myType = "";
        switch (type.toLowerCase()){
            case "brand":
                myType=type;
                break;
            case "city":
                myType = "address.city";
                break;
            case "amenities":
                myType = "amenities";
                break;
            case "country":
                myType = "address.country";
                break;
        }
        GroupOperation sumType = Aggregation.group(myType).count().as("cnt");
        Aggregation ag = myType.equals("amenities") ? Aggregation.newAggregation(Aggregation.unwind("amenities"),sumType) :Aggregation.newAggregation(sumType);
        AggregationResults<Document> result = mongoOperations.aggregate(ag, "hotel", Document.class);
        List<Document> l = result.getMappedResults();
        l.forEach(f-> mapp.put((String) f.get("_id"), Long.valueOf((Integer) f.get("cnt"))));
        return mapp;
    }
}
