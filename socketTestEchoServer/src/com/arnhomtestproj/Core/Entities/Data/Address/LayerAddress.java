package com.arnhomtestproj.Core.Entities.Data.Address;

public class LayerAddress {
    private String id;
    public LayerAddress(){
        this.id = "dummy test, need to make this random";
    }


    public boolean matches(FullAddress address){
        return matches(address.layerAddress);
    }

    public boolean matches(LayerAddress other){
        return id.equals(other.id);
    }
}
