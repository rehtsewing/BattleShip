package edu.duke.yl883.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

/** Player pattern in text mode */
public class TextPlayer extends AbstractTextUser{
  
  /**
   * Construct the textplayer with specfied name, board,
   * BufferedReader, PrintStream and factory
   * @param name is name of the player
   * @param theBoard    is board used for current player
   * @param input is the input reader for current player
   * @param out         is the out stream for current player
   * @param f is version 1 ship factory of current player
   * @param isComputer indicate whether this player is computer 
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V2ShipFactory f, Board<Character> enemyBoard) {
    super(name, theBoard, input, out, f, enemyBoard);
  }
  /**
   * Create a Placement object from the information
   * in input reader and handle the IllegalArgumentException 
   * from placement constructor
   *
   * @param prompt is the message use as prompt for game
   * @param version2 indicates whether the placement is
   *        specific for version 2 or not
   * @return the Placement object created
   * @throw EOFException if not enough placement entered
   */
  public Placement readPlacement(String prompt, boolean version2) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if(s == null) {
      throw new EOFException("Not enough placement entered");
    }
    try {
      Placement p = new Placement(s, version2);
    } catch(IllegalArgumentException e) {
      out.println("That placement is invalid: it does not have the correct format.");
      return readPlacement(prompt, version2);
    }
    return new Placement(s, version2);
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
  @Override
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?", shipCreationVersion.get(shipName));
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
  @Override
  public void doPlacementPhase() throws IOException {
    out.println(view.displayMyOwnBoard());

    StringBuilder message = new StringBuilder();
    message.append("Player " + name + ": you are going to place the following ships\n");
    message.append("For each ship, type the coordinate of the upper left\n");
    message.append("side of the ship, followed by either H (for horizontal) or V (for\n");
    message.append("vertical) for Submarine and Destroyer, followed by U(up), R(Right),\n");
    message.append("D(down) or L(left) for Battleship and Carrier. For example\n");
    message.append("M4H would place a Submarine or Destoyer horizontally starting\n");
    message.append("at M4 and going to the right.  You have\n\n");
    message.append("2 \"Submarines\" ships that are 1x2\n");
    message.append("3 \"Destroyers\" that are 1x3\n");
    message.append("3 \"Battleships\" \n");
    message.append("2 \"Carriers\" \n");
    out.println(message.toString());
    for(String name : shipsToPlace) {
      doOnePlacement(name, shipCreationFns.get(name));
    }
  }
 
  /**
   * Display board relevant information
   * Read a Coordinate
   * Get a ship by firing at the coordinate
   * Print relevant info according to the gotten ship
   */
  @Override
  public void playOneTurn() throws IOException {
    out.println("Player " + name + "'s turn:");
    out.println(view.displayMyBoardWithEnemyNextToIt(enemyView, "Your Ocean", "Enemy's Ocean"));
    out.println("Possible actions for Player " + name + ":\n");
    out.println(" F Fire at a square\n" +
                " M Move a ship to another square (" +  moveShipNum + " remaining)\n" +
                " S Sonar scan (" + sonarScanNum + " remaining)\n");
    try {
      doOneAction();
    } catch(IllegalArgumentException e) {
      out.println("That action is invalid: it does not have the correct format.");
      doOneAction();
    }
  }
  /** 
   * Implement the action user input
   * @throw IllegalArgumentException if action letter 
   *        is not F, M or S
   */
  @Override
  public void doOneAction() throws IOException{
    out.println("Player " + name + ", what would you like to do?\n");
    String s = inputReader.readLine();
    if(s.equals("F")) conductFire();
    else if(s.equals("M") && moveShipNum > 0) {
      moveShipNum -= 1;
      conductMove();
    } else if(s.equals("S") && sonarScanNum > 0) {
      sonarScanNum -= 1;
      conductScan();
    }
    else throw new IllegalArgumentException("Invalid action number " + s);
  }
  /**
   * Conduct fireat action
   */
  @Override
  public void conductFire() throws IOException{
    Coordinate c = readCoordinate("Player " + name + " where do you want to fire at?");
    Ship<Character> s = enemyBoard.fireAt(c);
    if(s != null) {
      out.println("You hit a " + s.getName() + "!\n");
    } else {
      out.println("You missed!\n");
    }
  }
  /**
   * Conduct sonar scan action
   */
  public void conductScan() throws IOException{
    Coordinate c = readCoordinate("Player " + name + " where do you want to do sonar scan?");
    HashMap<Character, Integer> scanRes = enemyBoard.scan(c);
    StringBuilder s = new StringBuilder();
    s.append("Submarines occupy " + getScanRes('s', scanRes) + " squares\n");
    s.append("Destroyers occupy " + getScanRes('d', scanRes) + " squares\n");
    s.append("Battleships occupy " + getScanRes('b', scanRes) + " squares\n");
    s.append("Carriers occupy " + getScanRes('c', scanRes) + " squares\n");
    out.println(s.toString());
  }
  /**
   * Return times of sign if exists
   * otherwise 0
   * @param sign is the tag of the ship to search
   * @param scanRes is the HashMap for scan result
   * @return times appear
   */
  protected Integer getScanRes(char sign, HashMap<Character, Integer> scanRes) {
    if(scanRes.get(sign) == null) return 0;
    else return scanRes.get(sign);
  }
    /**
   * Conduct move ship action
   */
  public void conductMove() throws IOException{
    Coordinate c = readCoordinate("Player " + name + " which ship would you like to choose?");
    Ship<Character> s = theBoard.takeoutShip(c);
    if(s != null) {
      moveTo(s);
    } else {
      out.println("No ship at this coordinate!\n");
      conductMove();
    }
  }
  /** 
   * User type in the new placement for
   * the moved ship and update that ship
   * @param s is the ship tobe moved
   */
    private void moveTo(Ship<Character> s) throws IOException {
      Ship<Character> mid = defaultOriShip(s);
      Placement p = readPlacement("Player " + name + " where do you want to place this " + s.getName() + "?\n", shipCreationVersion.get(s.getName()));
      Ship<Character> res = shipAfterMove(mid, p.getWhere(), p.getOrientation());
      String message = theBoard.tryAddHideShip(res);
      if(message != null) {
        out.println(message);
        moveTo(s);
      }
    }

  
  /**
   * Rotate the coordinate of all pieces
   * on the ship to default: 'V' for version1
   * 'U' for version2
   * @param s is the ship to be handled
   */    
  public Ship<Character> defaultOriShip(Ship<Character> s) {
    char ori = s.getOrientation();
    Coordinate origin = new Coordinate(0, 0);
    if(ori == 'R') return shipAfterMove(s, s.getTopLeft(), 'L');
    if(s.getOrientation() == 'D') return shipAfterMove(s, s.getTopLeft(), 'D');
    if(s.getOrientation() == 'L' || ori == 'H') return shipAfterMove(s, s.getTopLeft(), 'R');
    return s;
  }
  /**
   * Update the coordinate change of all pieces
   * on the ship
   * @param s is the ship to be handled
   * @param newCoordinate is the new top left corner
   * @param ori is the relative orientation between
   * the new and old orientation
   */    
  public Ship<Character> shipAfterMove(Ship<Character> s, Coordinate newCoordinate, char ori) {
    Coordinate topLeft = s.getTopLeft();
    V2ShipFactory f = new V2ShipFactory();
    int width = s.getDiagonal().getColumn() - topLeft.getColumn();
    int length = s.getDiagonal().getRow() - topLeft.getRow();
    char sign = ' ';
    HashMap<Coordinate, Boolean> newCoords = new HashMap<>();
    for(Coordinate c : s.getCoordinates()) {
      if(!s.wasHitAt(c)) sign = s.getDisplayInfoAt(c, true);
      Coordinate modify = new Coordinate(c.getRow() - topLeft.getRow(), c.getColumn() - topLeft.getColumn());
      //      System.out.println(modify.getColumn() + "," + modify.getRow() + "," + width + "," + length);
      newCoords.put(f.applyOrientation(newCoordinate, ori, modify, width, length), s.wasHitAt(c));
    }
    Ship<Character> newShip = new AlienShip<Character>(ori, newCoords, s.getName(), newCoordinate, sign, '*');
    return newShip;
  }
  /**
   * Create a Coordinate object from the information
   * in input reader and handle the IllegalArgumentException 
   * from coordinate constructor
   *
   * @param prompt is the message use as prompt for attacking
   * @return the Coordinate object created
   * @throw EOFException if not enough coordinate entered
   */

  public Coordinate readCoordinate(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    if(s == null) {
      throw new EOFException("Not enough coordinate entered");
    }
    try {
      Coordinate c = new Coordinate(s);
    } catch(IllegalArgumentException e) {
      out.println("That coordinate is invalid: it does not have the correct format.");
      return readCoordinate(prompt);
    }
    return new Coordinate(s);
  }

}
