package edu.duke.yl883.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

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
  }
  /**
   * Create a Placement object from the information
   * from information in input reader
   *
   * @param prompt is the message use as prompt for game
   * @return the Placement object created
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  /**
   * Read a Placement (prompt: "Where would you like to put your ship?")
   * Create a ship based on the location in that Placement
   * Add that ship to the board
   * Print out the board (to out, not to System.out)
   */
  public void doOnePlacement() throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a Destroyer?");
    Ship<Character> s  = shipFactory.makeDestroyer(p);
    theBoard.tryAddShip(s);
    out.println(view.displayMyOwnBoard());
  }
  /**
   * (a) Display the starting (empty) board
   * (b) Print the instructions message (from the README,
   *     but also shown again near the top of this file)
   * (c) Call doOnePlacement to place one ship
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
    doOnePlacement();
  }
}
