package com.arnhomtestproj.Network.MessageHandler;

import com.arnhomtestproj.Network.ConnectionIO;
import com.arnhomtestproj.Network.ConnectionMessageHandler;
import com.arnhomtestproj.Network.TransferPOJO.Envelope;
import com.arnhomtestproj.Network.TransferPOJO.OpenEnvelope;
import com.arnhomtestproj.Network.TransferPOJO.TennisBall;
import com.google.gson.Gson;
import com.sun.tools.doclint.Env;

public class TennisHandler implements ConnectionMessageHandler {

    private ConnectionIO io;
    final String tennisBall = "tennisBall";

    public TennisHandler(ConnectionIO io){
        this.io = io;
    }

    @Override
    public void handleMessage(String message) {
        Envelope env = Envelope.parseMessage(message);
        if(env.is(tennisBall)){
            TennisBall ball = env.open(TennisBall.class);
            handleTennisBall(env,ball);
        }
    }


    public void handleTennisBall(Envelope env, TennisBall ball){
        ball.bounce();
        String response = env.fill(ball);
        io.println(response);
    }
}
