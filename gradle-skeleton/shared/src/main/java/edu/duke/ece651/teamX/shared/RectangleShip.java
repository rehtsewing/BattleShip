package edu.duke.ece651.teamX.shared;

import java.util.HashSet;

/** Ships in rectangle shape, could return
 * coordinate set
 */
public class RectangleShip<T> extends BasicShip<T>{
  /**
   * Name of the ship
   */
  final String name;
  /**
   * Construct a RectangleShip by making
   * the right coordinate set and pass
   * them up to the BasicShip constructor
   *
   * @param name is name of the ship
   * @param upperLeft is the coordinate of
   *                 upper left corner of the ship
   * @param width is the width of the ship
   * @param height is the height of the ship
   * @param myDisplayinfo is the display info to self
   * @param enemyDisplayinfo is the display info to enemy
   */
  public RectangleShip(char orientation, String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(orientation, upperLeft, makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }
  /**
   * (For own view display data if not hit
   * onHit if hit)
   * 
   * @param data is info for myData
   * @param onHit is info for hit
   */
  public RectangleShip(char orientation, String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(orientation, name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this('V', "testship", upperLeft, 1, 1, data, onHit);
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
   * a rectangle
   *
   * @param upperLeft is the coordinate of
   *                 upper left corner of the rectangle
   * @param width is the width of the rectangle
   * @param height is the height of the rectangle
   * @return a set of coordinate on rectangle
   */

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> s = new HashSet<>();
    int r = upperLeft.getRow();
    int c = upperLeft.getColumn();
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        Coordinate curr = new Coordinate(r + j, c + i);
        s.add(curr);
      }
    }
    return s;
  }


}
