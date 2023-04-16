package edu.duke.ece651.teamX.shared;

import java.io.*;
import java.util.ArrayList;

public class ClientHandlerThread extends Thread {
    private final Object lock = new Object();
    /** streams pass to client*/
    private PrintWriter output;
    /** Reader for clients message*/
    private BufferedReader reader;


    final String END_OF_TURN = "END_OF_TURN";
    private final TextUser player;
    private GameThread gameServer;
    public int status;
    final AbstractShipFactory<Character> shipFactory;
    private int flag;
    /**
     * Constructor of the ClientHandlerThread
     * @param output
     * @param reader
     * @param player
     * @param gameServer
     * @throws IOException
     */
    public ClientHandlerThread(int flag, PrintWriter output, BufferedReader reader, V2ShipFactory factory, TextUser player, GameThread gameServer) {
        this.flag = flag;
        this.output = output;
        this.reader = reader;
        this.player = player;
        this.gameServer = gameServer;
        this.status = 0;
        this.shipFactory = factory;
    }
    public int getFlag() {return flag;}
    /**
     * Handle user1 and user2 to
     * start placement phase
     */
    public void doPlacementPhase() throws IOException{
        if(status > 1) return; // refactor to hashmap?
        doSynchronization();
        player.doPlacementPhase();
    }
    /**
     * Handle user1 and user2 to
     * do attack until the game end
     */
    public void doAttackingPhase() throws IOException{
        while(true) {
            doSynchronization();
            if(gameServer.getFlag() == flag) {
                synchronized (gameServer) {
                    if(!gameServer.getWinner().isEmpty() && status != -1) {
                        System.out.println("send other");
                        if(!player.isComputer()) send(gameServer.getWinner(), output);
                        break;
                    } else if(status != -1){
                        if(!player.isComputer()) {
                            send("no winner", output);
                        }
                    }else break;
                    player.playOneTurn();
                    gameServer.setFlag(1-flag);
                    if (player.isWin()) {
                        gameServer.setWinner(player.getName());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            sendGameLoading();
            doPlacementPhase();
            doAttackingPhase();
        } catch(Exception e) {
            System.out.println(player.getName() + " disconnect");
            player.disconnect();
            System.out.println(e.getMessage());
        }
        status = -1;
        System.out.println("end!!!!!");
//        doSynchronization();
    }
    public int getStatus() {
        return status;
    }
//    public void reconnect(PrintWriter out, BufferedReader in) {
//        if(player.isConnected()) {
//            send("This player is already online", output);
//            return;
//        }
//        output = out;
//        reader = in;
//        send(String.valueOf(status), output);
//        send(player.getName(), output);
//        player.connect();
//    }

//    public void setIO(PrintWriter out, BufferedReader in) {
//    }
    public void doSynchronization() {
        synchronized (gameServer) {
            try {
                gameServer.wait();
            } catch (InterruptedException e) {
                System.out.println("wait error");
            }
        }
    }
    public void sendGameLoading() { //haven't finished
        if(status > 0) return; // refactor to hashmap?
        if(!player.isComputer()) {
            send(player.getName(), output);
            String prompt = "Loading...Waiting for more player to join...";
            send(prompt, output);
        }
        status += 1;
    }
    public void send(String message, PrintWriter out) {
        out.println(message);
        out.println(END_OF_TURN);
        out.flush(); // flush the output buffer
    }
}
