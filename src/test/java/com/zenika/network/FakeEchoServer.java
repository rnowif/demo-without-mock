package com.zenika.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeEchoServer {
    private final int port;
    private volatile boolean running;

    public FakeEchoServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        running = true;

        new Thread(() -> {
            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    try (DataInputStream reader = getReader(socket); DataOutputStream writer = getWriter(socket)) {
                        writer.writeUTF(reader.readUTF());
                        writer.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private DataOutputStream getWriter(Socket socket) throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }

    private DataInputStream getReader(Socket socket) throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    public void stop() {
        running = false;
    }
}
