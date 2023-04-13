package edu.duke.ece651.teamX.shared;

import java.util.HashMap;

/** Abstract of features of a ship*/
public abstract class BasicShip<T> implements Ship<T> {
  /**
   * Orientation and topLeft corner of the ship
   */
  protected char orientation;
  protected Coordinate topLeft;
  /**
   * HashMap for all pieces of the ship
   * Coordinate is the place of the piece
   * Boolean is true if piece has been hit
   *            false if has not been hit
   */
  protected HashMap<Coordinate, Boolean> myPieces;
  /** 
   * Display info of the ship to me
   */
  protected ShipDisplayInfo<T> myDisplayInfo;
  /** 
   * Display info of the ship to enemy
   */
  protected ShipDisplayInfo<T> enemyDisplayInfo;
  /**
   * Constructs a BasicShip with specified coordinate
   * Iterable
   * 
   * @param orientation is orientation of ship
   * @param topLeft is the top left corner of the ship
   * @param where is the Iterable of pieces' coordinate
   *           in the newly constructed basic ship.
   * @param myDisplayInfo is display info of the ship to self
   * @param enemyDisplayInfo is display info of the ship to enemy
   */
  public BasicShip(char orientation, Coordinate topLeft, Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    myPieces = new HashMap<Coordinate, Boolean>();
    for(Coordinate c: where) {
      myPieces.put(c, false);
    }
    this.orientation = orientation;
    this.topLeft = topLeft;
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
  }
  public BasicShip(char orientation, Coordinate topLeft, HashMap<Coordinate, Boolean> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    myPieces = new HashMap<Coordinate, Boolean>();
    for(Coordinate c: where.keySet()) {
      if(where.get(c)) myPieces.put(c, true);
      else  myPieces.put(c, false);
    }
    this.orientation = orientation;
    this.topLeft = topLeft;
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
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
  public Coordinate getTopLeft() {
    return topLeft;
  }
  
  /** {@inheritDoc}*/
  @Override
  public char getOrientation() {
    return orientation;
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
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    checkCoordinateInThisShip(where);
    if(myShip) {
      return myDisplayInfo.getInfo(where, myPieces.get(where));
    } else {
      return enemyDisplayInfo.getInfo(where, myPieces.get(where));
    }
  }
  /** {@inheritDoc} */
  @Override
  public Coordinate getDiagonal() {
    int row = 0;
    int col = 0;
    for(Coordinate c : myPieces.keySet()) {
      if(c.getRow() > row) row = c.getRow();
      if(c.getColumn() > col) col = c.getColumn();
    }
    return new Coordinate(row, col);
  }
}
