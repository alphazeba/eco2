package com.arnhomtestproj.Network;

import java.io.IOException;
import java.net.Socket;

public interface ConnectionBuilder {
    public Connection build(Socket socket) throws IOException;
}
