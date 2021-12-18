package com.arnhomtestproj.Network.GameMaker;

import com.arnhomtestproj.Network.ConnectionIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameMakerConnectionIO implements ConnectionIO {

    private PrintWriter out;
    private BufferedReader in;
    public GameMakerConnectionIO(Socket socket) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
    }

    @Override
    public void println(String message) {
        out.println(message);
    }

    @Override
    public String readLine() {
        String result = null;
        try{
            result = in.readLine();
            if(result != null){ // if we got something, then we also need to skip past the 0 byte that gamemaker adds.
                in.skip(1);
            }
        }
        catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }
        return result;
    }
}
