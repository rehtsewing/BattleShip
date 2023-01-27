package edu.duke.yl883.battleship;

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
   * @param myDisplayinfo is the display info
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo);
    this.name = name;
  }
  /**
   * @param data is info for myData
   * @param onHit is info for hit
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
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
