package com.zenika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class EchoClientTest {

    private static final int PORT = 9999;

    private FakeEchoServer server;

    @Before
    public void setUp() throws IOException {
        server = new FakeEchoServer(PORT);
        server.run();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void should_send_message_and_get_response() throws IOException {
        assertThat(new EchoClient("localhost", PORT).sendAndReceive("Hello")).isEqualTo("Hello");
    }
}
