package com.arnhomtestproj.Core.Entities.Data.Address;

import com.arnhomtestproj.Core.Entities.Layer;

public class FullAddress {
    LayerAddress layerAddress;
    EntityAddress entityAddress;

    public FullAddress(LayerAddress layerAddress, EntityAddress entityAddress){
        this.layerAddress = layerAddress;
        this.entityAddress = entityAddress;
    }

    public boolean matches(LayerAddress layerAddress){
        return this.layerAddress.matches(layerAddress);
    }

    public boolean matches(EntityAddress entityAddress){
        return this.entityAddress.matches(entityAddress);
    }

    public EntityAddress getEntityAddress(){
        return entityAddress;
    }
}
