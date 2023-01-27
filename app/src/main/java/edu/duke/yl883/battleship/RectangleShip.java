package edu.duke.yl883.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T>{
  /**
   * Construct a RectangleShip by making
   * the right coordinate set and pass
   * them up to the BasicShip constructor
   *
   * @param upperLeft is the coordinate of
   *                 upper left corner of the ship
   * @param width is the width of the ship
   * @param height is the height of the ship
   * @param info is the display info
   */
  public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> info) {
    super(makeCoords(upperLeft, width, height), info);
  }
  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }
  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this(upperLeft, 1, 1, data, onHit);
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
