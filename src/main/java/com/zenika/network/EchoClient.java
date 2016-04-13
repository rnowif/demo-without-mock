package com.zenika.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient {
    private final String hostname;
    private final int port;

    public EchoClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String sendAndReceive(String message) throws IOException {
        try (
                Socket socket = new Socket(hostname, port);
                DataInputStream reader = getReader(socket);
                DataOutputStream writer = getWriter(socket)
        ) {
            writer.writeUTF(message);
            writer.flush();
            return reader.readUTF();
        }
    }

    private DataOutputStream getWriter(Socket socket) throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }

    private DataInputStream getReader(Socket socket) throws IOException {
        return new DataInputStream(socket.getInputStream());
    }
}
