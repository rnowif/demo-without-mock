package com.zenika.network;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class EchoClientTest {

    private static final int PORT = 9999;

    @Test
    public void should_send_message_and_get_response() throws IOException {
        FakeEchoServer server = new FakeEchoServer(PORT);
        server.run();

        String echoResult = new EchoClient("localhost", PORT).sendAndReceive("Hello");
        assertThat(echoResult).isEqualTo("Hello");

        server.stop();
    }
}
