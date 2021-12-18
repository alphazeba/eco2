package com.arnhomtestproj.Network.GameMaker;

import com.arnhomtestproj.Network.Connection;
import com.arnhomtestproj.Network.ConnectionBuilder;
import com.arnhomtestproj.Network.ConnectionIO;
import com.arnhomtestproj.Network.ConnectionMessageHandler;
import com.arnhomtestproj.Network.MessageHandler.EchoHandler;

import java.io.IOException;
import java.net.Socket;

public class GameMakerConnectionBuilder implements ConnectionBuilder {
    @Override
    public Connection build(Socket socket) throws IOException {
        ConnectionIO io = new GameMakerConnectionIO(socket);
        ConnectionMessageHandler handler = new EchoHandler(io);
        return new Connection(socket, io, handler);
    }
}
