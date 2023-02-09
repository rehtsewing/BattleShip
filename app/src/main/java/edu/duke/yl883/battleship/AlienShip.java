package edu.duke.yl883.battleship;

import java.util.HashMap;
import java.util.HashSet;

/** Ships in alien shape, could return
 * coordinate set
 */
public class AlienShip<T> extends BasicShip<T>{
  /**
   * Name of the ship
   */
  final String name;
  /**
   * Construct a Alien Ship by making
   * the right coordinate set and pass
   * them up to the BasicShip constructor
   *
   * @param orientation is orientation of the ship
   * @param whereVacant is the HashSet of vacant
   *        pieces in the rectangular encloses
   * @param name is name of the ship
   * @param upperLeft is the coordinate of
   *                 upper left corner of the ship
   * @param width is the width of the ship
   * @param height is the height of the ship
   * @param myDisplayinfo is the display info to self
   * @param enemyDisplayinfo is the display info to enemy
   */
  public AlienShip(char orientation, HashSet<Coordinate> whereVacant, String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(orientation, upperLeft, makeCoords(whereVacant, upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }
  /**
   * (For own view display data if not hit
   * onHit if hit)
   * 
   * @param data is info for myData
   * @param onHit is info for hit
   */
  public AlienShip(char orientation, HashSet<Coordinate> whereVacant, String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(orientation, whereVacant, name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }

  /**
   * (Create ship with all pieces in the ship)
   * 
   * @param where is are pieces on this ship
   * with all hit information
   */
  public AlienShip(char orientation, HashMap<Coordinate, Boolean> where, String name, Coordinate upperLeft, T data, T onHit) {
    super(orientation, upperLeft, where, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
    this.name = name;
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }
  /**
   * Build the set of coordinate for 
   * an AlienShip
   *
   * @param whereVacant is Iterable of vacant
   *        pieces in the rectangular encloses the ship
   * @param upperLeft is the coordinate of
   *                 upper left corner of the rectangle
   * @param width is the width of the rectangle
   * @param height is the height of the rectangle
   * @return a set of coordinate on AlienShip
   */

  static HashSet<Coordinate> makeCoords(HashSet<Coordinate> whereVacant, Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> s = new HashSet<>();
    int r = upperLeft.getRow();
    int c = upperLeft.getColumn();
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        Coordinate curr = new Coordinate(r + j, c + i);
        if(!whereVacant.contains(curr))
          s.add(curr);
      }
    }
    return s;
  }


}
