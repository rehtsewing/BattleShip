package edu.duke.yl883.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

/** Player pattern in text mode */
public class TextPlayer {
  /**
   * theBoard is the Board for the App
   * view is the Text view of the board
   * inputReader is a bufferedReader of the App
   * out is the stream to be printed to
   * shipFactory is object to make ship
   */
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  final String name;
  /** ArrayList of the ships' name*/
  final ArrayList<String> shipsToPlace;
  /** A map from ship name to the lambda 
   * takes a placement and return a Ship
   */
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;


  /**
   * Construct the textplayer with specfied name, board,
   * BufferedReader, PrintStream and factory
   * @param name is name of the player
   * @param theBoard    is board used for current player
   * @param input is the input reader for current player
   * @param out         is the out stream for current player
   * @param f is version 1 ship factory of current player 
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V1ShipFactory f) {
    this.name = name;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = input;
    this.out = out;
    this.shipFactory = f;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    setupShipCreationMap();
    setupShipCreationList();
  }
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

  /**
   * Create a Placement object from the information
   * from information in input reader and handle
   * the IllegalArgumentException from placement
   * constructor
   *
   * @param prompt is the message use as prompt for game
   * @return the Placement object created
   * @throw EOFException if not enough placement entered
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if(s == null) {
      throw new EOFException("Not enough placement entered");
    }
    try {
      Placement p = new Placement(s);
    } catch(IllegalArgumentException e) {
      out.println("That placement is invalid: it does not have the correct format.");
      return readPlacement(prompt);
    }
    return new Placement(s);
  }

  /**
   * Read a Placement 
   * Create a ship based on the Placement
   * Add that ship to the board, redo this process
   * if previous adding failed
   * Print out the board (to out, not to System.out)
   * @param shipName is type string of the ship
   * @param createFn is an apply method that takes
   * a Placement and return a ship
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    String message = theBoard.tryAddShip(s);
    if(message != null) {
      out.println(message);
      doOnePlacement(shipName, createFn);
      return;
    }
    out.println(view.displayMyOwnBoard());
  }

  /**
   * (a) Display the starting (empty) board
   * (b) Print the instructions message (from the README,
   *     but also shown again near the top of this file)
   * (c) Recursively Call doOnePlacement to place all ships
   */
  public void doPlacementPhase() throws IOException {
    out.println(view.displayMyOwnBoard());
    out.println("Player " + name + ": you are going to place the following ships (which are all");
    out.println("rectangular). For each ship, type the coordinate of the upper left");
    out.println("side of the ship, followed by either H (for horizontal) or V (for");
    out.println("vertical).  For example M4H would place a ship horizontally starting");
    out.println("at M4 and going to the right.  You have\n");
    out.println("2 \"Submarines\" ships that are 1x2"); 
    out.println("3 \"Destroyers\" that are 1x3");
    out.println("3 \"Battleships\" that are 1x4");
    out.println("2 \"Carriers\" that are 1x6\n");
    for(String name : shipsToPlace) {
      doOnePlacement(name, shipCreationFns.get(name));
    }
  }
}
