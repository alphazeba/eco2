package com.arnhomtestproj;

import com.arnhomtestproj.Core.Simulation;
import com.arnhomtestproj.Network.GameMaker.GameMakerConnectionBuilder;
import com.arnhomtestproj.Network.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
	    // write your code here
        // Server.main(new String[]{ "8080" });
        Simulation sim = new Simulation();
        /*
        Server server = new Server(8080, new GameMakerConnectionBuilder());
        server.startListening();
        *
         */
    }
}




