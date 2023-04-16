package edu.duke.ece651.teamX.shared;


import java.io.*;

/** Client pattern of the game*/
public class Client {
    protected ServerStream serverStream;
    /** Client socket for communicate with server */
    protected PrintStream out;
    /** Input stream of the client, like terminal input*/
    protected BufferedReader input;
    /** info to be transferred, entered by user */
    protected String mapInfo;
    /**client player color*/
    protected String combatOutcome;
    protected String color;
    /** Delimiter*/
    final String END_OF_TURN = "END_OF_TURN";

    protected boolean isDefeated = false;

    protected String winner;
    private int status;
    private boolean isRunning;
    private boolean withComputer;
    private String name;
    /**
     * Constructs a server with specified port
     *
     * @param port is the port of the socket
     * @param host is the address of the server
     */
    public Client(int port, String host,BufferedReader in) throws IOException {
        this.serverStream=new ServerStream(host,port);
        this.out = System.out;
        this.input = in;
        this.winner = "no winner";
        this.status = 0;
        this.isRunning = true;
        this.withComputer = false;
    }


    /** execute the client */
    public void run() {
        try {
            doLoginOrSignup();
            while (true) {
                joinNewGame();
//                doChooseGame();
                receivePlaceMessage();
                doInitialPlacement();
                doAllTurns();
                if(!isRunning) break;
            }
        } catch (IOException e) {
            out.println(e.getMessage());
        } finally {
            try {
                serverStream.close();
            } catch (IOException e) {
                out.println(e.getMessage());
            }
        }
    }
    public void stop() {
        isRunning = false;
    }
    public void doLoginOrSignup() throws IOException{
        String loginStatus;
        do {
            out.println("client enter:");
            serverStream.receive();
            out.println(serverStream.getBuffer());
            inputLogin();
            out.println(serverStream.read());
            //send username
            serverStream.send(input.readLine());
            out.println(serverStream.read());
            //send password
            serverStream.send(input.readLine());
            //receive login status
            loginStatus = serverStream.read();
            out.println(loginStatus);
        }while(!loginStatus.equals("Successfully login!"));
    }
    public void inputLogin() throws IOException{
        String s = input.readLine();
        if (!s.equals("L") && !s.equals("S")) {
            out.println("Should be L/S");
            inputLogin();
        }
        serverStream.send(s);
    }
//    public void doChooseGame() throws  IOException{
//        serverStream.receive();
//        //send N:new game Y:existing game
//        out.println("-------------------");
//        out.println(serverStream.getBuffer());
//        out.println("-------------------");
//        String newOrExistingGame = input.readLine();
//        serverStream.send(newOrExistingGame);
//
//        if(newOrExistingGame.equals("N")){
//            joinNewGame();
//        }else if(newOrExistingGame.equals("Y")){
//            //send ID of game;
//            //todo
//            serverStream.receive();
//            try {
//                int num = Integer.parseInt(serverStream.getBuffer());
//                if(num == 0) out.println("No joined game in your list");
//                joinNewGame();
//            } catch (Exception e) {
//                joinOldGame();
//            }
//
//        }else{
//            System.out.println("Your input is: "+ newOrExistingGame);
//            throw new IllegalArgumentException("Should be L/S");
//        }
//    }
    public void joinNewGame() throws IOException{
        //seng number of how many players' game
        // to do: add check

        serverStream.receive();
        out.println(serverStream.getBuffer());
        joinNewChoice();
        name = serverStream.read();
        out.println("You are " + name);
        serverStream.receive();
        out.println(serverStream.getBuffer());
    }
    public void joinNewChoice() throws IOException {
        String computer = input.readLine();
        if(computer.equals("C") || computer.equals("H")){
            serverStream.send(computer);
            if(computer.equals("C")) withComputer = true;
        }else {
            System.out.println("Your input is: "+ computer + " Should be H/C");
            joinNewChoice();
        }
    }
//    public void joinOldGame() throws IOException {
//        out.println(serverStream.getBuffer());
//        serverStream.receive();
//        out.println(serverStream.getBuffer());
//        serverStream.send(input.readLine());
//
//        name = serverStream.read();
//        out.println("You are " + name);
//        serverStream.receive();
//        out.println(serverStream.getBuffer());
//        status = Integer.parseInt(serverStream.getBuffer());
//
//    }

    public void doAllTurns() throws IOException {
        while(!isOver()) {//keep running if no one wins
            if(status % 2 != 0 || status == 0) {
                    doOneTurn();
            }
            reportResult();

            if(!isRunning) break;
//            System.out.println("outcome reach");
        }
    }
    public void reportResult() throws IOException{
        status = 0;
        receiveWinner();
        if(isOver()){
            if(name.equals(winner)){
                out.println("Congratulations! You win!");
            }else {
                out.println("You lose!!");
                out.println(winner+" wins.");
            }

        }

    }

    /**
     * receive color string from server
     * @throws IOException if something wrong with receive
     */
    public void receivePlaceMessage()throws  IOException{
        if(status > 1) return;
        System.out.println(serverStream.read());
        System.out.println(serverStream.read());

    }

    public void receiveWinner()throws  IOException{
        winner = serverStream.read();
    }




    /**
     * do initial placement phase, user need to input
     * the number she wants to place in a specific territory
     * if input is invalid, she needs to re-input
     * @throws IOException if something wrong with receive
     */
    public void doInitialPlacement() throws IOException{
        if(status > 2) return;
        int placementTimes = 10;//Integer.parseInt(serverStream.read());
        int i=0;
        while(i < placementTimes){
//            System.out.println(serverStream.getBuffer() + " value" + i);
            serverStream.receive();
            try {
                tryInputPlace(serverStream.getBuffer());
                out.println(serverStream.read());
            } catch (Exception e) {
                out.println(e.getMessage());
            }
            if(serverStream.getBuffer().contains("0|1|2|3")){
                i++;
            }
        }
    }

    /**
     * User input the unit number to place
     * @param prompt the prompt for placement
     * @throws Exception if something wrong with receive
     */
    public void tryInputPlace(String prompt)throws IOException{
        out.print(prompt);
        String s = input.readLine();
        serverStream.send(s);
        out.println(serverStream.read());
        if(!serverStream.getBuffer().equals("valid format\n")){
           tryInputPlace(serverStream.read());
        }

    }


    /**
     * Player do her turn
     * @throws IOException if something wrong with receive
     */
    public void doOneTurn()throws IOException{
        String choice;
        if(withComputer){
            out.println("computer result");
            out.println(serverStream.read());
        }
        serverStream.receive();
        out.print(serverStream.getBuffer());
        while (true) {
                serverStream.receive();
                choice = tryChooseOneAction(serverStream.getBuffer());
                out.println(serverStream.read());
                if(serverStream.getBuffer().equals("valid")){
                    break;
                }
        }
        switch (choice) {
            case "M":
                doOneMove();
                break;
            case "F":
                doOneFire();
                break;
            case "S":
                doOneScan();
                break;
        }
    }

    /**
     * player choose one action order to do
     * @param prompt to prompt for action choices
     * @return input choice string
     * @throws IllegalArgumentException illegal input choice
     * @throws IOException if something wrong with receive
     */
    public String tryChooseOneAction(String prompt)throws IOException{

        out.println(prompt);
        String s = input.readLine();
        if(isValidChoice(s)){
//            System.out.println("xxxxxx"+s + "xxxxxx");
            serverStream.send(s);
            return s;
        }else{
            out.println("Action should be \"F\"(fire), \"M\"(move), \"S\"(scan)");
            return tryChooseOneAction(prompt);
        }
    }

    /**
     * Determine if a string is a valid choice string
     * @param s the string to be judged
     * @return true valid. Otherwise, false
     */
    public boolean isValidChoice(String s){
        return s.equals("F")||s.equals("M")||s.equals("S");
    }

    /**
     * Player do move phase
     * @throws IOException if something wrong with receive
     */
    public void doOneMove()throws IOException{
        trySendCoordinate(serverStream.read());
        if(!serverStream.read().equals("")){
            out.println(serverStream.getBuffer());
            doOneMove();
        }else {
            moveTo();
        }
    }
    public void moveTo() throws IOException{
        tryInputPlace(serverStream.read());
        if(!serverStream.read().equals("")) {
            out.println(serverStream.getBuffer());
            moveTo();
        }
    }
    public void doOneScan()throws IOException{
        serverStream.read();
        trySendCoordinate(serverStream.getBuffer());
        serverStream.read();
        out.println(serverStream.getBuffer());
    }



    /**
     * try to send a valid unit number
     * @param prompt the prompt for input
     * @throws IllegalArgumentException invalid unit number input
     * @throws IOException if something wrong with receive
     */
    public void trySendCoordinate(String prompt)throws IOException{
        out.println(prompt);
        String s = input.readLine();
        serverStream.send(s);
        out.println(serverStream.read());
        if(!serverStream.getBuffer().equals("valid")) trySendCoordinate(serverStream.read());
    }



    /**
     * user do attack phase
     * @throws IOException if something wrong with receive
     * @return null if not actually conduct
     */
    public void doOneFire()throws IOException{
        serverStream.read();
        trySendCoordinate(serverStream.getBuffer());
        serverStream.read();
        out.println(serverStream.getBuffer());
    }


    public boolean isOver(){
        return !winner.equals("no winner");
    }

}

