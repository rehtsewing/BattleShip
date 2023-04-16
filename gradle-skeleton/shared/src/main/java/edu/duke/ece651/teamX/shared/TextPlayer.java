package edu.duke.ece651.teamX.shared;

import java.io.*;
import java.util.HashMap;
import java.util.function.Function;

/** Player pattern in text mode */
public class TextPlayer extends AbstractTextUser{

  /**
   * Construct the textplayer with specfied name, board,
   * BufferedReader, PrintStream, factory and enemy board
   * @param name is name of the player
   * @param theBoard    is board used for current player
   * @param input is the input reader for current player
   * @param out         is the out stream for current player
   * @param f is version 2 ship factory of current player
   * @param enemyBoard is the board of enemy 
   */
  public TextPlayer(String name, Board<Character> theBoard, BufferedReader input, PrintWriter out, V2ShipFactory f, Board<Character> enemyBoard) {
    super(name, theBoard, input, out, f, enemyBoard);
  }
  @Override
  public boolean isComputer() {return false;}

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
    send(prompt);
    receive();
    String s = buffer;
    if(s == null) {
      throw new EOFException("Not enough placement entered");
    }
    try {
      Placement p = new Placement(s, version2);
    } catch(IllegalArgumentException e) {
      send("That placement is invalid: it does not have the correct format.");
      return readPlacement(prompt, version2);
    }
    send("valid format\n");
    return new Placement(s, version2);
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?", shipCreationVersion.get(shipName));
    Ship<Character> s = createFn.apply(p);
    String message = theBoard.tryAddShip(s);
    if(message != null) {
      send(message);
      doOnePlacement(shipName, createFn);
      return;
    }
    send(view.displayMyOwnBoard());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doPlacementPhase() throws IOException {
    send(view.displayMyOwnBoard());

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
    send(message.toString());
    for(String name : shipsToPlace) {
      doOnePlacement(name, shipCreationFns.get(name));
    }
  }
 
  /**
   * Display board relevant information
   * Read an action
   * Implement that action
   */
  @Override
  public void playOneTurn() throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("Player " + name + "'s turn:\n");
    sb.append(view.displayMyBoardWithEnemyNextToIt(enemyView, "Your Ocean", "Enemy's Ocean"));
    sb.append("Possible actions for Player " + name + ":\n");
    sb.append(" F Fire at a square\n" +
                " M Move a ship to another square (" +  moveShipNum + " remaining)\n" +
                " S Sonar scan (" + sonarScanNum + " remaining)\n");
    send(sb.toString());
    handleOneAction();
  }
  /**
   * Handle possible error from doOneAction
   */
  private void handleOneAction() throws IOException{
      try {
      doOneAction();
    } catch(IllegalArgumentException e) {
      send("That action is invalid: it does not have the correct format or current action is exhausted.");
      handleOneAction();
    }
  }
  /** 
   * Implement the action user input
   * @throw IllegalArgumentException if action letter 
   *        is not F, M or S
   */
  @Override
  public void doOneAction() throws IOException{
    send("Player " + name + ", what would you like to do?");
    receive();
    String s = buffer.toUpperCase();
    if(s.equals("F")) {
      send("valid");
      conductFire();
    }
    else if(s.equals("M") && moveShipNum > 0) {
      moveShipNum -= 1;
      send("valid");
      conductMove();
    } else if(s.equals("S") && sonarScanNum > 0) {
      sonarScanNum -= 1;
      send("valid");
      conductScan();
    }
    else throw new IllegalArgumentException("Invalid action number " + s);
  }
  /**
   * Conduct fire at action
   */
  @Override
  public void conductFire() throws IOException{
    Coordinate c = readCoordinate("Player " + name + " where do you want to fire at?");
    Ship<Character> s = enemyBoard.fireAt(c);
    if(s != null) {
      send("You hit a " + s.getName() + "!\n");
    } else {
      send("You missed!\n");
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
    send(s.toString());
  }
  /**
   * Return appearing times of sign if exists
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
      send("");
      moveTo(s);
    } else {
      send("No ship at this coordinate!\n");
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
      Placement p = readPlacement("Player " + name + " where do you want to place this " + s.getName() + "?", shipCreationVersion.get(s.getName()));
      Ship<Character> res = shipAfterMove(mid, p.getWhere(), p.getOrientation());
      String message = theBoard.tryAddHideShip(res);
      if(message != null) {
        send(message);
        moveTo(s);
      } else {
        send("");
      }
    }

  
  /**
   * Rotate the coordinate of all pieces
   * on the ship to default: 'V' for version1
   * 'U' for version2
   * @param s is the ship to be handled
   * @return the newly created rotated ship
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
   * @return the newly created ship after the movement
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
    send(prompt);
    receive();
    String s = buffer;
    if(s == null) {
      throw new EOFException("Not enough coordinate entered");
    }
    try {
      Coordinate c = new Coordinate(s, theBoard.getWidth(), theBoard.getHeight());
    } catch(IllegalArgumentException e) {
      send("That coordinate is invalid: it does not have the correct format.");
      return readCoordinate(prompt);
    }
    send("valid");
    return new Coordinate(s);
  }

}
