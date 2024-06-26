package edu.duke.ece651.teamX.shared;

public class Placement {
  /** 
   * Coordinate for placement
   */
  final Coordinate where;
  /**
   * Orientation for the placement
   * either Horizontal 'H' or Vertical 'V'
   */
  final char orientation;

  /**
   * Constructs PLacement with the specified coordinate
   * and orientation
   * 
   * @param w is where the newly constructed placement is.
   * @param o is the orientation of the newly constructed placement.
   */
  public Placement(Coordinate w, char o) {
    o = Character.toUpperCase(o);
    this.where = w;
    this.orientation = o;
  }

  /**
   * Constructs a Placement Corresponds to the
   * specified string, like where(row=0, column=2),
   * orientation='V' with
   * "A2V"
   * 
   * @param descr is the string taken to construct the placement
   * @param version2 indicate whether this placement used for
   *        version2 ship or not
   * @throws IllegalArgumentException if length of the string  
   *      smaller than 1 
   * @throws IllegalArgumentException if orientation letter is not
   *      either H or V
   */  
  public Placement(String descr, boolean version2) {
    descr = descr.toUpperCase();
    if (descr.length() < 1) {
      throw new IllegalArgumentException("String of the placement should be longer but is " + descr.length());
    }
    String coorDescr = descr.substring(0, descr.length() - 1);
    Coordinate w = new Coordinate(coorDescr);
    char oriLetter = descr.charAt(descr.length() - 1);
    if (!version2 && oriLetter != 'H' && oriLetter != 'V') {
      throw new IllegalArgumentException("Invalid version 1 orientation letter " + oriLetter);
    }
    if (version2 && oriLetter != 'U' && oriLetter != 'R' && oriLetter != 'D' && oriLetter != 'L') {
      throw new IllegalArgumentException("Invalid version 2 orientation letter " + oriLetter);
    }
    this.where = w;
    this.orientation = oriLetter;
  }


  /**
   * Get where the placement is
   * 
   * @return the coordinate of the place
   */
  public Coordinate getWhere() {
    return where;
  }

  /**
   * Get the orientation of the placement
   * 
   * @return orientation of the placement
   */
  public char getOrientation() {
    return orientation;
  }

 
  /**
   * Override method equals from what
   * inherited from Object
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o;
      return where.equals(p.where) && orientation == p.orientation;
    }
    return false;
  }

  /**
   * Override the toString method to
   * adapt for the Placement class,
   * having features of where and orientation
   *
   * @return String representation of Placement
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    char sign = 'A';
    sign += where.getRow();
    s.append(sign);
    s.append(where.getColumn());
    s.append(orientation);
    return s.toString();
  }

  /**
   * Override the hashCode method to
   * use the hashcode from Java's Strings
   *
   * @return hashCode for the current placement
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
 
}
