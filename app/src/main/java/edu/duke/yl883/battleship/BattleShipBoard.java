
package edu.duke.yl883.battleship;

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
  /**
   * ArrayList of ships has been moved by player
   */
  final ArrayList<Ship<T>> movedShips;
  /** PLacement rule check of the board*/
  private final PlacementRuleChecker<T> placementChecker;
  /** Record the where enemy has missed*/
  HashSet<Coordinate> enemyMisses;
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
    this.missInfo = missInfo;
    this.movedShips = new ArrayList<Ship<T>>();
  }
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
      for(Ship<T> s : movedShips) {
        if(s.occupiesCoordinates(where)) {
          if(s.wasHitAt(where)) return s.getDisplayInfoAt(where, isSelf); 
        }
      }
      if(enemyMisses.contains(where)) return missInfo;
    }
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
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
        return s;
      }
    }
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
        movedShips.add(s);
        return myShips.remove(i);
      }
    }
    return null;
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
    int x = c.getColumn();
    int y = c.getRow();
    int range = 3;
    HashMap<T,Integer> res = new HashMap<>();
    for(int j = y - range; j <= y + range; j++) {
      int diff = y - j;
      if(y < j) diff = j - y;
      for(int i = x - (range - diff); i <= x + (range - diff); i++) {
        checkCoordinate(i, j, res);
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
    if(x >=0 && x < width && y >= 0 && y < height) {
      T sign = whatIsAt(new Coordinate(x, y), true);
      int num = res.getOrDefault(sign, 0);
      res.put(sign, num + 1);
    }
  }
}
