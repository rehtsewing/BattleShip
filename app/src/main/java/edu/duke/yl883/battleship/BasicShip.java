package edu.duke.yl883.battleship;

import java.util.HashMap;

/** Abstract of features of a ship*/
public abstract class BasicShip<T> implements Ship<T> {
  /**
   * HashMap for all pieces of the ship
   * Coordinate is the place of the piece
   * Boolean is true if piece has been hit
   *            false if has not been hit
   */
  protected HashMap<Coordinate, Boolean> myPieces;
  /** 
   * Display info of the ship
   */
  protected ShipDisplayInfo<T> myDisplayInfo;

  /**
   * Constructs a BasicShip with specified coordinate
   * Iterable
   * 
   * @param where is the Iterable of pieces' coordinate
   *           in the newly constructed basic ship.
   * @param myDisplayInfo is the display info of the ship
   */
  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    myPieces = new HashMap<Coordinate, Boolean>();
    for(Coordinate c: where) {
      myPieces.put(c, false);
    }
    this.myDisplayInfo = myDisplayInfo;
  }
  /**
   * Check if the given coordinate is part of
   * the ship.
   *
   * @param c is the coordinate to be checked
   * @throws IllegalArgumentException if c is
   *           not part of the ship
   */
  protected void checkCoordinateInThisShip(Coordinate c) {
    if(!myPieces.containsKey(c)) {
      throw new IllegalArgumentException("Coordinate" + c.toString() + "is not on the ship");
    }
  }
  /** {@inheritDoc}*/
  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    return myPieces.containsKey(where);
  }
  /** {@inheritDoc}*/
  @Override
  public boolean isSunk() {
    for(boolean hit : myPieces.values()) {
      if(!hit) return false;
    }
    return true;
  }
  /** {@inheritDoc} */
  @Override
  public void recordHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);  
  }
  /** {@inheritDoc} */
  @Override
  public boolean wasHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    return myPieces.get(where);
  }
  /** {@inheritDoc} */
  @Override
  public T getDisplayInfoAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    return myDisplayInfo.getInfo(where, myPieces.get(where));
  }

}
