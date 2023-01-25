package edu.duke.yl883.battleship;

/**
 * Class BattleShipBoard implements interface Board having 
 * two final fields width and height.
 * 
 * field width is the width of the board.
 * @field height is the height of the board.
 * method getWidth()
 * method getHeight()
 */
public class BattleShipBoard implements Board {
  /**
   * Width and height of the board
   */
  private final int width, height;

  /**
   * Constructs a BattleShipBoard with the specified width
   * and height
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
}
