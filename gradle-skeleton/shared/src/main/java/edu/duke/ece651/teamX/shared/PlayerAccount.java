package edu.duke.ece651.teamX.shared;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PlayerAccount {
    /** streams pass to client*/
    private PrintWriter output;
    /** Reader for clients message*/
    private BufferedReader reader;
    private final String username;
    private String password;
    private List<GameThread> joinedThread;
    private List<Integer> numList;
    public PlayerAccount(PrintWriter output, BufferedReader reader, String username, String password) {
        this.username = username;
        this.password = password;
        this.output = output;
        this.reader = reader;
        this.joinedThread = new ArrayList<>();
        this.numList = new ArrayList<>();
    }
    public boolean match(String otherUsername, String otherPassword){
        return username.equals(otherUsername) && password.equals(otherPassword);
    }

    public String getUsername() {
        return username;
    }

    public void updateIO(PrintWriter out, BufferedReader r) {
        output = out;
        reader = r;
    }
    public boolean isEmpty() {return joinedThread.size() == 0;}
    public PrintWriter getOutput() {
        return output;
    }
    public BufferedReader getReader() {
        return reader;
    }

    public void addJoinGame(GameThread th, int currNum) {
        joinedThread.add(th);
        numList.add(currNum);
    }
    public void deleteEndGame(GameThread th, int currNum) {
        for(int i = 0; i < joinedThread.size(); i++) {
            if(th.equals(joinedThread.get(i))) {
                joinedThread.remove(i);
                numList.remove(i);
            }
        }
    }
//    public String displayGameList() {
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < joinedThread.size(); i++) {
//            GameThread th = joinedThread.get(i);
//            sb.append(i + ". Game " + th.getRoomNumber() + ": " + th.getPlayerNum() + " players\n");
//        }
//        return sb.toString();
//    }
//    public GameThread select(int index) {
//        GameThread game = joinedThread.get(index);
//        ClientHandlerThread cli = game.backToGame(numList.get(index), output, reader);
//        return game;
//    }

}
