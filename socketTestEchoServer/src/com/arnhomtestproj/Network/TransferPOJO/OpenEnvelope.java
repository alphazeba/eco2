package com.arnhomtestproj.Network.TransferPOJO;

import com.google.gson.Gson;

public class OpenEnvelope<T> {
    static Gson gson = new Gson();
    String type;
    T data;
    String cid; // connection id

    public  OpenEnvelope(Envelope envelope, Class<T> classType){
        type = envelope.type;
        cid = envelope.cid;
        data = gson.fromJson(envelope.json, classType);
    }
}
