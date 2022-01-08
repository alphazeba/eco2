package com.arnhomtestproj.Core.Entities.Data.Address;

public class LayerAddress {
    private String id;
    public LayerAddress(){
        this.id = "L_"+AddressHelper.generateAddress();
    }

    public boolean matches(LayerAddress other){
        return id.equals(other.id);
    }
}
