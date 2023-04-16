package edu.duke.ece651.teamX.shared;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

/** Abstract user pattern in text mode */
public abstract class AbstractTextUser implements TextUser{
  /**
   * theBoard is the my own Board for the App
   * enemyBoard is the enemy Board for the App
   * view is the Text view of the board
   * enemyView is the Text view of the enemy board
   * inputReader is a bufferedReader of the App
   * out is the stream to be printed to
   * shipFactory is object to make ship
   * name is name of the text user
   */
  final Board<Character> theBoard;
  protected Board<Character> enemyBoard;
  final BoardTextView view;
  protected BoardTextView enemyView;
  final BufferedReader inputReader;
  final PrintWriter out;
  final AbstractShipFactory<Character> shipFactory;
  final String name;
  /** ArrayList of the ships' name*/
  final ArrayList<String> shipsToPlace;
  /** A map from ship name to the lambda 
   * takes a placement and return a Ship
   */
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  /** A map from ship name to whether it is a version
   * 2 special ship
   */
  final HashMap<String, Boolean> shipCreationVersion;
  /** Number of uses remaining for move ship
   *  and sonar scan
   */
  int moveShipNum, sonarScanNum;
  /** Whether this player is selected for the game*/
  final boolean selected;

  private boolean isConnect;

  final String END_OF_TURN = "END_OF_TURN";
  /** Buffer for message from clients */
  protected String buffer;

  /**
   * Construct the abstract textuser with specfied name, board,
   * BufferedReader, PrintStream, factory and enemy board
   * @param name is name of the player
   * @param theBoard    is board used for current player
   * @param input is the input reader for current player
   * @param out         is the out stream for current player
   * @param f is version 2 ship factory of current player
   * @param enemyBoard is the board of enemy 
   */
  public AbstractTextUser(String name, Board<Character> theBoard, BufferedReader input, PrintWriter out, V2ShipFactory f, Board<Character> enemyBoard) {
    this.name = name;
    this.theBoard = theBoard;
    this.enemyBoard = enemyBoard;
    this.view = new BoardTextView(theBoard);
    this.enemyView = new BoardTextView(enemyBoard);
    this.inputReader = input;
    this.out = out;
    this.shipFactory = f;
    this.selected = false;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    this.shipCreationVersion = new HashMap<String, Boolean>();
    this.moveShipNum = 2;
    this.sonarScanNum = 1;
    this.isConnect = true;
    setupShipCreationBool();
    setupShipCreationMap();
    setupShipCreationList();
  }

  public String getName() {
    return name;
  }
  /**
   * Send information to one client
   */
  public void send(String message) {
    out.println(message);
    out.println(END_OF_TURN);
    out.flush(); // flush the output buffer
  }

  /**
   * Receive message to buffer from the input reader
   * @throws IOException
   */
  public void receive() throws IOException {
    StringBuilder sb = new StringBuilder();
    String ss = inputReader.readLine();
//        System.out.println(ss);
    sb.append(ss);
    String receLine = inputReader.readLine();
    if(receLine==null){
      throw new IOException("");
    }
    while(!receLine.equals(END_OF_TURN)) {   //!!!!
      sb.append("\n"+receLine);
      receLine = inputReader.readLine();
    }
    buffer = sb.toString();
  }
  public void disconnect() {isConnect = false;}
  public boolean isConnected() {return isConnect;}
  public void connect() {isConnect = true;}
  public abstract boolean isComputer();
  /** Set up the shipCreationFns Hashmap*/
  protected void setupShipCreationMap() {
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
  }
  /** Set up the ship name ArrayList*/
  protected void setupShipCreationList() {
     shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
     shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
     shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
     shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }
  /** Set up the shipCreationVersion Hashmap*/
  protected void setupShipCreationBool() {
    shipCreationVersion.put("Submarine", false);
    shipCreationVersion.put("Battleship", true);
    shipCreationVersion.put("Carrier", true);
    shipCreationVersion.put("Destroyer", false);
  }
  /** {@inheritDoc}*/
  @Override
  public void setEnemyBoard(Board<Character> enemy) {
    enemyBoard = enemy;
    enemyView = new BoardTextView(enemyBoard);
  }
  /** {@inheritDoc}*/
  @Override
  public Board<Character> getOwnBoard() {
    return theBoard;
  }
  /** {@inheritDoc}*/
  @Override
  public boolean doPlayerSelection() throws IOException{
    out.println("Do you want to select " + name + " in this game?");
    out.println("Y yes\n" + "N no");
    String s = inputReader.readLine().toUpperCase();
    if(s.equals("Y")) return true;
    else if(s.equals("N")) return false;
    else {
      out.println("Invalid choice number " + s);
      return doPlayerSelection();
    }
  }
  /** {@inheritDoc}*/
  @Override
  public boolean isWin() {
    if(enemyBoard.loseCheck()) {
      if(!isComputer()) {
        send(name);
      }
      return true;
    }
    if(!isComputer()) {
      send("no winner");
    }
    return false;
  }
}
