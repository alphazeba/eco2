package com.arnhomtestproj;

import com.arnhomtestproj.Core.Simulation;
import com.arnhomtestproj.Network.GameMaker.GameMakerConnectionBuilder;
import com.arnhomtestproj.Network.Server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
	    // write your code here
        // Server.main(new String[]{ "8080" });
        Simulation sim = new Simulation();
        Scanner io = new Scanner(System.in);

        while(true){
            sim.stepForward();
            System.out.println("press enter to continue. enter x to exit");
            String input = io.nextLine();
            if(input.length() > 0 && input.charAt(0) == 'x') break;
        }

        /*
        Server server = new Server(8080, new GameMakerConnectionBuilder());
        server.startListening();
        *
         */
    }
}




