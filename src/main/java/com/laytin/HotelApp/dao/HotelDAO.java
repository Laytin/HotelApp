package com.laytin.HotelApp.dao;

import com.laytin.HotelApp.models.Address;
import com.laytin.HotelApp.models.AmenityEnum;
import com.laytin.HotelApp.models.Contacts;
import com.laytin.HotelApp.models.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Profile("sql")
@Component
public class HotelDAO {
    private final EntityManager entityManager;

    @Autowired
    public HotelDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional(readOnly = true)
    public List<Hotel> search(Optional<String> name, Optional<String> brand, Optional<String> city, Optional<String> country, Optional<String[]> amenities) {
        Session s = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Hotel> query = builder.createQuery(Hotel.class);
        Root<Hotel> root = query.from(Hotel.class);
        Fetch<Hotel, Address> addressFetch = root.fetch("address",JoinType.LEFT);
        root.fetch("contacts",JoinType.LEFT);
        Fetch<Hotel, AmenityEnum> amenityFetch = root.fetch("amenities",JoinType.LEFT);
        Join<Hotel, Address> addressJoin = (Join<Hotel, Address>) addressFetch;

        List<Predicate> predicates = new ArrayList<>();
        name.ifPresent(string -> predicates.add(builder.equal(root.get("name"), string)));
        brand.ifPresent(string -> predicates.add(builder.equal(root.get("brand"), string)));
        city.ifPresent(string -> predicates.add(builder.equal(addressJoin.get("city"), string)));
        country.ifPresent(string -> predicates.add(builder.equal(addressJoin.get("country"), string)));
        amenities.ifPresent(string -> {
            for (String g: string) {
                // 'FREE_WIFI' and 'Free WIFI'
                AmenityEnum a2= AmenityEnum.getEnumByString(g);
                if(a2!=null)
                    predicates.add(builder.equal(root.joinList("amenities"), a2.toString()));
                else try {
                    predicates.add(builder.equal(root.joinList("amenities"),AmenityEnum.valueOf(g).toString()));
                }catch (IllegalArgumentException e){
                }
            }
        });
        Predicate finalQuery = builder.and(predicates.toArray(new Predicate[0]));
        query.where(finalQuery);

        return s.createQuery(query).getResultList();
    }
}
