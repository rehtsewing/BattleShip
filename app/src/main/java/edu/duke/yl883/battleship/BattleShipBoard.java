package edu.duke.yl883.battleship;

import java.util.ArrayList;

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
   * Constructs a BattleShipBoard with the specified width
   * and height, also with an empty list of ships
   * 
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or
   *                                  equal to zero.
   */
  public BattleShipBoard(int w, int h) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
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
   */
  public boolean tryAddShip(Ship<T> toAdd) {
    myShips.add(toAdd);
    return true;
  }
  /**
   * Takes a Coordinate, and sees which (if any) 
   * Ship occupies that coordinate
   *
   * @param where is the coordinate taken for check
   */ 
  public T whatIsAt(Coordinate where) {
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        return s.getDisplayInfoAt(where);
      }
    }
    return null;
  }

}
