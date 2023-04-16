package edu.duke.ece651.teamX.shared;

import static org.junit.jupiter.api.Assertions.*;;


import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void testConstructor() throws Exception {
        ServerSocket ss = new ServerSocket(1237);

        Server s = new Server(ss);
        Thread serverThread = new Thread(() -> {
            s.run();
        });
        serverThread.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Client clis = new Client(1237, "localhost", in);
        s.stop();
        serverThread.join();
        ss.close();
    }

    @Test
    public void testjoinNewGame() throws Exception {
        ServerSocket ss = new ServerSocket(1237);

        Server s = new Server(ss);
        Thread serverThread = new Thread(() -> {
            s.run();
        });
        serverThread.start();

        BufferedReader clientReader = mock(BufferedReader.class);
        Client cli = new Client(1237, "localhost", clientReader);

        when(clientReader.readLine()).thenReturn("S").thenReturn("qwe").thenReturn("123").thenReturn("N").
                thenReturn("C");

        BufferedReader clientReader1 = mock(BufferedReader.class);
        Client cli1 = new Client(1237, "localhost", clientReader1);

        when(clientReader1.readLine()).thenReturn("L").thenReturn("qwe").thenReturn("123").thenReturn("N").
                thenReturn("H");


        cli.doLoginOrSignup();
        cli.joinNewGame();
        cli1.doLoginOrSignup();
        cli1.joinNewGame();
        s.stop();
        serverThread.interrupt();
        serverThread.join();
    }

    @Test
    public void testJoinNewGameAndRun() throws Exception {

        ServerSocket ss = new ServerSocket(1249);

        Server s = new Server(ss);
        Thread serverThread = new Thread(() -> {
            s.run();
        });
        serverThread.start();

        BufferedReader clientReader = mock(BufferedReader.class);
        Client cli = new Client(1249, "localhost", clientReader);

        when(clientReader.readLine()).thenReturn("S").thenReturn("qwe").thenReturn("123").thenReturn("N").
                thenReturn("C").thenReturn("a0v").thenReturn("a1v").thenReturn("a2v").thenReturn("a3v").
                thenReturn("a4v").thenReturn("a5u").thenReturn("d0u").thenReturn("d3u").
                thenReturn("d6u").thenReturn("d8u").thenReturn("F").thenReturn("a0").thenReturn("S").thenReturn("b4");
        cli.stop();
        cli.run();

        Client cli1 = new Client(1249, "localhost", clientReader);
        when(clientReader.readLine()).thenReturn("L").thenReturn("qwe").thenReturn("123").thenReturn("N").
                thenReturn("C").thenReturn("v").thenReturn("a0v").thenReturn("a0H").thenReturn("a1V").thenReturn("a2v").thenReturn("a3v").
                thenReturn("a4v").thenReturn("a5u").thenReturn("d0u").thenReturn("d3u").
                thenReturn("d6u").thenReturn("d8u").thenReturn("S").thenReturn("b4");
        cli1.stop();
        cli1.run();

        Client cli2 = new Client(1249, "localhost", clientReader);
        when(clientReader.readLine()).thenReturn("S").thenReturn("qw").thenReturn("123").thenReturn("N").
                thenReturn("C").thenReturn("v").thenReturn("a0v").thenReturn("a0H").thenReturn("a1V").thenReturn("a2v").thenReturn("a3v").
                thenReturn("a4v").thenReturn("a5u").thenReturn("d0u").thenReturn("d3u").
                thenReturn("d6u").thenReturn("d8u").thenReturn("M").thenReturn("a1").thenReturn("a0H").thenReturn("k0H");
        cli2.stop();
        cli2.run();
        sleep(200);
        s.stop();
        serverThread.interrupt();
        serverThread.join();
    }



}
