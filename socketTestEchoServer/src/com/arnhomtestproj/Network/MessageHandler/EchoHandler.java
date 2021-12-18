package com.arnhomtestproj.Network.MessageHandler;

import com.arnhomtestproj.Network.ConnectionIO;
import com.arnhomtestproj.Network.ConnectionMessageHandler;

public class EchoHandler implements ConnectionMessageHandler {

    ConnectionIO io;

    public EchoHandler(ConnectionIO io){
        this.io = io;
    }

    @Override
    public void handleMessage(String message) {
        // echoes it back;
        System.out.println("recieved from gamemaker: " + message);
        io.println(message);
    }
}
