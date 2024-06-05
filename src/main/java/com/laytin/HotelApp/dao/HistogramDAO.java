package com.laytin.HotelApp.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Profile("sql")
@Component
public class HistogramDAO {
    private final EntityManager entityManager;
    @Autowired
    public HistogramDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public Map<String, Long> counterBy(String type) {
        Session s = entityManager.unwrap(Session.class);
        Query q = null;
        switch (type.toLowerCase()){
            case "brand":
                q = s.createQuery("select h.brand, count(*) from Hotel h group by h.brand");
                break;
            case "city":
                q = s.createQuery("select a.city, count(*) from Address a group by a.city");
                break;
            case "amenities":
                // amenities != Entity, therefore we should do NativeQuery instead of HQL "EntityQuery"
                q = s.createNativeQuery("SELECT AMENITY, COUNT(*) From AMENITIES GROUP BY AMENITY");
                break;
            case "country":
                q = s.createQuery("select a.country, count(*) from Address a group by a.country");
                break;
        }
        List<Object[]> jb =  q.getResultList();
        Map<String, Long> result = new HashMap<>();
        jb.stream().forEach(f-> result.put((String) f[0], (Long) f[1]));
        return result;
    }
}
