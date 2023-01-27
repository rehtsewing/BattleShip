package edu.duke.yl883.battleship;

import java.util.HashMap;

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
   * @inheritdoc
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    return myPieces.containsKey(where);
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public T getDisplayInfoAt(Coordinate where) {
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
  }

}
