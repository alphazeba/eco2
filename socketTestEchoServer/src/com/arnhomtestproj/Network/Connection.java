package com.arnhomtestproj.Network;

import java.io.*;
import java.net.*;

public class Connection {

    Socket socket;
    ConnectionIO io;
    ConnectionMessageHandler handler;
    Thread readThread;

    public Connection(Socket socket, ConnectionIO io, ConnectionMessageHandler handler) throws IOException {
        this.socket = socket;
        this.io = io;
        this.handler = handler;
        this.readThread = new Thread(() -> {
            String input;
            while((input = io.readLine()) != null){
                handler.handleMessage(input);
            }
        });
        readThread.start();
    }

    public void sendMessage(String message){
        io.println(message);
    }

}
