package edu.duke.ece651.teamX.shared;


import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameThread extends Thread {
    private List<ClientHandlerThread> clientThreads;
    private final Object lock = new Object();
    /** streams pass to client*/
    private List<PrintWriter> outputs;
    /** Reader for clients message*/
    private List<BufferedReader> readers;
    /** Buffer for message from clients */
    protected String buffer;

    final String END_OF_TURN = "END_OF_TURN";

    private final List<PlayerAccount> accounts;

    private final int roomNumber;
    private boolean isStart;
    final List<TextUser> players;
    /** confront pair in theis app*/
    protected ArrayList<TextUser> confrontPair;
    private final int playerNum;
    private V2ShipFactory factory;
    private List<Board<Character>> boards;
    private int flag;
    private String winner;
    public GameThread(int playerNum, int roomNumber) {
        this.outputs = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.clientThreads = new ArrayList<>();
        this.isStart = false;
//        this.accounts = new ArrayList<>();
        this.roomNumber = roomNumber;
        this.playerNum = playerNum;
        this.players = new ArrayList<>();
        this.confrontPair = new ArrayList<TextUser>();
        this.factory = new V2ShipFactory();
        this.accounts = new ArrayList<>();
        this.boards = new ArrayList<Board<Character>>();
        this.flag = 0;
        this.winner = "";
        boards.add(new BattleShipBoard<Character>(10, 20, 'X'));
        boards.add(new BattleShipBoard<Character>(10, 20, 'X'));
    }


    /**
     * Do the player selection
     * choose between two human players
     * and two computer players
     */
    public void doPlayerSelection(){
        confrontPair.add(players.get(0));
        confrontPair.add(players.get(1));
        confrontPair.get(0).setEnemyBoard(confrontPair.get(1).getOwnBoard());
        confrontPair.get(1).setEnemyBoard(confrontPair.get(0).getOwnBoard());
    }
    public void setWinner(String name) {
        winner = name;
    }
    public String getWinner() {return winner;}
    public int getPlayerNum() {
        return playerNum;
    }
//    public synchronized ClientHandlerThread backToGame(int index, PrintWriter out, BufferedReader in) {
//        ClientHandlerThread cliTh = clientThreads.get(index);
//        cliTh.reconnect(out, in);//haven't handle complete
////        cliTh.reconnect();
//        System.out.println("player reconnect");
//        return cliTh;
//    }
    public synchronized ClientHandlerThread join(PlayerAccount account){
        if(isStart) return null;
        PrintWriter out = account.getOutput();
        BufferedReader reader = account.getReader();
        outputs.add(out);
        readers.add(reader);
        String name;
        if(outputs.size() == 1) {
            name = "Human A";
        }else {
            name = "Human B";
        }
        TextPlayer p1 = new TextPlayer(name, boards.get(outputs.size() - 1), reader, out, factory, boards.get(2 - outputs.size()));
        if(playerNum == 1) {
            TextComputer p2 = new TextComputer("Computer", boards.get(1), reader, out, factory, boards.get(0));
            players.add(p2);
            ClientHandlerThread computerThread = new ClientHandlerThread(clientThreads.size(), out,
                    reader, factory, p2, this);
            clientThreads.add(computerThread);
            computerThread.start();
        }
        players.add(p1);
        ClientHandlerThread clientThread = new ClientHandlerThread(clientThreads.size(), out,
                reader, factory, p1, this);
        clientThreads.add(clientThread);
        clientThread.start();
        System.out.println("current num" + outputs.size() + " playerNum: " + playerNum);

        account.addJoinGame(this, outputs.size() - 1);
        accounts.add(account);
        if(outputs.size() == playerNum) {
            isStart = true;
            notify();
        }
        return clientThread;
    }

    @Override
    public void run() {
        while(!isStart) {
            synchronized (this) {
                try {
                    System.out.println("lock");
                    wait();
                    System.out.println("unlock");
                } catch (InterruptedException e) {
                    System.out.println(" game thread wait error");
                }
            }
        }
        try {
            System.out.println("game thread start");
            doPlayerSelection();
            while (true) {
                for (ClientHandlerThread t : clientThreads) {
                    if(t.getStatus() != -1) {
                        while (t.getState() != State.WAITING) {
                        }
                    }
                }
                if(!checkFinish()) break;
                while(!checkConnection()) {}
                notifyClients();
            }
            System.out.println("game end!!!");
            //add stop thread
        } finally {
            for (int i = 0; i < accounts.size(); i++) {
                accounts.get(i).deleteEndGame(this, i);
            }
        }
    }
    public int getFlag() {return flag;}
    public void setFlag(int val) {flag = val;}
    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass().equals(getClass())) {
            GameThread otherGame = (GameThread) other;//
            return roomNumber == otherGame.getRoomNumber();
        }
        return false;

    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public boolean checkFinish() {
        for(ClientHandlerThread t : clientThreads) {
            if(t.getStatus() != -1) return true;
        }
        return false;
    }
    public boolean checkConnection() {
        for(TextUser p : players) {
            if(p.isConnected()) return true;
        }
        return false;
    }
    public synchronized void notifyClients() {
        notifyAll(); // Notify all waiting threads
//        System.out.println("notify all");
    }

    public List<ClientHandlerThread> getClients() {
        return clientThreads;
    }
    public void shutDown() {
        for(ClientHandlerThread th : clientThreads) {
            th.status = -1;
        }
        for(PrintWriter out : outputs) {
            out.close();
        }
    }
}
