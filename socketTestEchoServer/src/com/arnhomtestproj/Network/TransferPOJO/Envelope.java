package com.arnhomtestproj.Network.TransferPOJO;

import com.google.gson.Gson;
import sun.tools.java.ClassType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;

import static javax.swing.UIManager.put;

public class Envelope {
    static Gson gson = new Gson();
    static public Envelope parseMessage(String message){
        return gson.fromJson(message,Envelope.class);
    }

    String type;
    String json;
    String cid; // connection id

    public <T> T open(Class<T> classType){
        return gson.fromJson(json,classType);
    }

    public String fill(Object obj){
        json = gson.toJson(obj);
        return gson.toJson(this);
    }

    public boolean is(String messageType){
        return Objects.equals(type, messageType);
    }
}
