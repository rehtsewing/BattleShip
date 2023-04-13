
package edu.duke.ece651.teamX.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class BattleShipBoard implements interface Board having 
 * two final fields width and height.
 * 
 * field width is the width of the board.
 * field height is the height of the board.
 * method getWidth()
 * method getHeight()
 */
public class BattleShipBoard<T> implements Board<T> {
  /**
   * Width and height of the board
   */
  private final int width, height;
  /**
   * ArrayList of ships on the board
   */
  final ArrayList<Ship<T>> myShips;
  /** PLacement rule check of the board*/
  private final PlacementRuleChecker<T> placementChecker;
  /** Record the where enemy has missed*/
  /** Points hidden to enemy*/
  /** Hit point on the moved ship in the original place
   * with their hit information*/
  final HashSet<Coordinate> enemyMisses, hidePoints;
  final HashMap<Coordinate, T> movedHitPoints;
  /** Sign for missed information*/
  final T missInfo;
  /**
   * Constructs a BattleShipBoard with the specified width
   * and height, also with an empty list of ships
   * 
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @param pChecker is placement rule checker
   * @param missInfo is sign used for miss information
   * @throws IllegalArgumentException if the width or height are less than or
   *                                  equal to zero.
   */
  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> pChecker, T missInfo) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = pChecker;
    this.enemyMisses = new HashSet<Coordinate>();
    this.hidePoints = new HashSet<Coordinate>();
    this.missInfo = missInfo;
    this.movedHitPoints = new HashMap<Coordinate, T>();
  }
  /** Construct battleshipboard with 
   * @param w is width
   * @param h is height
   * @param missInfo is representation of missInfo
   */
  public BattleShipBoard(int w, int h, T missInfo) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)), missInfo);
  }

  /**
   * Get the width of the board
   * 
   * @return width of the board
   */
  public int getWidth() { 
    return width;
  }

  /**
   * Get the height of the board
   * 
   * @return height of the board
   */
  public int getHeight() {
    return height;
  }
  /**
   * Try to add the ship to myShips, but 
   * might not succeed
   * @param toAdd is the ship try to be added to the list
   * @return null if successfully add the ship, error
   *          message otherwisex
   */
  public String tryAddShip(Ship<T> toAdd) {
    String message = placementChecker.checkPlacement(toAdd, this);
    if(message == null) {
      myShips.add(toAdd);
      return null;
    }
    return message;
  }
  
  /**
   * Try to add the ship after moved to myShips and
   * all its hit points to hidePoints which hidden
   * to enemy
   * @param toAdd is the ship try to be added to the list
   * @return null if successfully add the ship, error
   *          message otherwisex
   */
  public String tryAddHideShip(Ship<T> toAdd) {
    for(Coordinate c : toAdd.getCoordinates()) {
      if(toAdd.wasHitAt(c)) hidePoints.add(c);
    }
    return tryAddShip(toAdd);
  }
  /**
   * Takes a Coordinate, and sees which (if any) 
   * Ship in own board occupies that coordinate
   *
   * @param where is the coordinate taken for check
   * @return display info if where occupied
   *         by ships or hits or
   *         null if it is vacant
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }
  /**
   * Takes a Coordinate, and sees which (if any) 
   * Ship in enemy board occupies that coordinate
   *
   * @param where is the coordinate taken for check
   * @return display info if where occupied
   *         by ships or hits or
   *         null if it is vacant
   */
  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  /**
   * Takes a Coordinate, and sees which (if any) 
   * Ship occupies that coordinate
   *
   * @param where is the coordinate taken for check
   * @param isSelf define to check own board or enemy's board
   * @return display info if where occupied
   *         by ships or hits or
   *         null if it is vacant or
   *         missInfo if recorded in enemyMiss and is enemy
   */
  protected T whatIsAt(Coordinate where, boolean isSelf) {
    if(!isSelf) {
      if(movedHitPoints.containsKey(where)) return movedHitPoints.get(where); 
      if(enemyMisses.contains(where)) return missInfo;
    }
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        if(!isSelf && hidePoints.contains(where)) return null;
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    return null;
  }
  /**
   * Search for any ship that occupies coordinate c
   * and record the hit, if no such ship, record the miss
   * @param c is coordinate chose to be fired at
   * @return ship if it is been fired, null if no
   * ship at the coordinate.
   */
  public Ship<T> fireAt(Coordinate c) {
    for(Ship<T> s: myShips) {
      if(s.occupiesCoordinates(c)) {
        s.recordHitAt(c);
        if(enemyMisses.contains(c)) enemyMisses.remove(c);
        if(hidePoints.contains(c)) hidePoints.remove(c);
        if(movedHitPoints.containsKey(c)) movedHitPoints.remove(c); 
        return s;
      }
    }
    if(movedHitPoints.containsKey(c)) movedHitPoints.remove(c);   
    enemyMisses.add(c);
    return null;
  }
  /**
   * Take out the ship that occupies coordinate c
   * @param c is coordinate
   * @return ship that contains the coordinate or
   *         null if no such ship
   */
  public Ship<T> takeoutShip(Coordinate c) {
    for(int i = 0; i < myShips.size(); i++) {
      Ship<T> s = myShips.get(i);
      if(s.occupiesCoordinates(c)) {
        addMovedHitPoint(s);
        return myShips.remove(i);
      }
    }
    return null;
  }
  /**
   * Add the original hit points in the
   * moved ship to movedHitPoints
   * @param s is the moved ship to check with
   */
  private void addMovedHitPoint(Ship<T> s) {
    for(Coordinate c : s.getCoordinates()) {
      movedHitPoints.put(c, s.getDisplayInfoAt(c, false));
    }
  }
  /**
   * Check for lose info of the board
   * i.e. All ships are sunk
   * @return true if all ship sunk
   */
  public boolean loseCheck() {
    for(Ship<T> s : myShips) {
      if(s.isSunk()) continue;
      return false;
    }
    return true;
  }
  /**
   * Scan coordinate around the selected 
   * point within range of 3 and return
   * the scan information through HashMap
   * @param c is the selected coordinate
   * @return HashMap for scan result
   */
  public HashMap<T, Integer> scan(Coordinate c) {
    int x = c.getColumn(); //1
    int y = c.getRow(); //6
    int range = 3;
    HashMap<T,Integer> res = new HashMap<>();
    for(int j = y - range; j <= y + range; j++) { //3, 9
      int diff = y - j;// 3
      if(y < j) diff = j - y;
      for(int i = x - (range - diff); i <= x + (range - diff); i++) {
        checkCoordinate(j, i, res);//1, 1
      }
    }
    return res;
  }
  /**
   * Find the value at coordinate(x, y) if it is a
   * valid coordinate and store it to the map
   * @param x, y are for the coordinate
   * @param res is the map to collect scan result
   */
  protected void checkCoordinate(int x, int y, HashMap<T, Integer>res) {
    if(x >= 0 && x < height && y >= 0 && y < width) {
      T sign = whatIsAtForSelf(new Coordinate(x, y));
      int num = res.getOrDefault(sign, 0);
      res.put(sign, num + 1);
    }
  }
}
