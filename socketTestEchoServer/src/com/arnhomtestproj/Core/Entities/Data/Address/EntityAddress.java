package com.arnhomtestproj.Core.Entities.Data.Address;

import java.util.Map;

public class EntityAddress {
    String id;

    public EntityAddress(){
        id = "TEST DUMMY ENTITY ADDRESS, please implement something real lel";
    }

    public EntityAddress(String manualId){
        id = manualId;
    }

    public boolean matches(EntityAddress other) {
        return this.id.equals(other.id);
    }

    public String getId(){
        return id;
    }
}
