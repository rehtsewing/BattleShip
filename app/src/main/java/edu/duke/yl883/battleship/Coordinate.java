package edu.duke.yl883.battleship;

/**
 * This class handles all manipulations on
 * coordinate
 * It supports the equal comparison, hashCode and
 * to string conversion.
 */
public class Coordinate {
  /**
   * Row and column of the Coordinate
   */
  private final int row, column;

  /**
   * Constructs a Coordinate with the specified row
   * and column
   * 
   * @param r is the row of the newly constructed coordinate.
   * @param c is the column of the newly constructed coordinate.
   */
  public Coordinate(int r, int c) {
    this.row = r;
    this.column = c;
  }

  /**
   * Constructs a Coordinate Corresponds to the
   * specified string like row=0, column=2 with
   * "A2"
   * 
   * @param descr is the string taken to construct the coordinate
   * @throws IllegalArgumentException if length of the string not 
   *      equals to 2
   * @throws IllegalArgumentException if column letter is not
   *      between 0 to 9
   * @throws IllegalArgumentException if row letter is not
   *      between A to Z 
  */
  public Coordinate(String descr) {
    if (descr.length() != 2) {
      throw new IllegalArgumentException("Length of the string taken should be equal to 2 but is" + descr.length());
    }
    char colLetter = descr.charAt(1);
    char rowLetter = descr.charAt(0);
    if (colLetter < '0' || colLetter > '9') {
      throw new IllegalArgumentException("Invalid column letter" + colLetter);
    }
    if (rowLetter < 'A' || rowLetter > 'Z') {
      throw new IllegalArgumentException("Invalid row letter" + rowLetter);
    }
    this.row = rowLetter - 'A';
    this.column = colLetter - '0';
  }

  /**
   * Get the row of the coordinate
   * 
   * @return row of the coordinate
   */
  public int getRow() {
    return row;
  }

  /**
   * Get the column of the coordinate
   * 
   * @return column of the cooridnate
   */
  public int getColumn() {
    return column;
  }

  /**
   * Override method equal from what
   * inherit from Object
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;
  }

  /**
   * Override the toString method to
   * adapt for the Coordinate class,
   * having row and column
   *
   * @return String representation of Coordinate
   */
  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  /**
   * Override the hashCode method to
   * use the hashcode from Java's Strings
   *
   * @return hashCode for the current coordinate
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}
