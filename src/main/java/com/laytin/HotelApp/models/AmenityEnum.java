package com.laytin.HotelApp.models;

public enum AmenityEnum {
    FREE_PARKING ("Free parking"),
    FREE_WIFI ("Free WiFi"),
    NON_SMOKING ("Non-smoking rooms"),
    CONCIERGE ("Concierge"),
    RESTAURANT("On-site restaurant"),
    FITNESS_CENTER ("Fitness center"),
    PET_FRIENDLY ("Pet-friendly rooms"),
    ROOM_SERVICE ("Room service"),
    BUSINESS("Business center"),
    MEETING ("Meeting rooms");

    private String title;

    AmenityEnum(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public static AmenityEnum getEnumByString(String code){
        for(AmenityEnum e : AmenityEnum.values())
            if(e.title.equals(code)) return e;
        return null;
    }
}
