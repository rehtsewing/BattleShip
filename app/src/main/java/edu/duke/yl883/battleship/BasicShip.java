package edu.duke.yl883.battleship;

public class BasicShip implements Ship<Character> {
  /**
   *Location of the basic ship
   */
  private final Coordinate myLocation;

  /**
   * Constructs a BasicShip with the specified coordinate
   * 
   * @param where is the coordinate of the newly constructed basic ship.
   */
  public BasicShip(Coordinate where) {
    this.myLocation = where;
  }
  
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    return where.equals(myLocation);
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
  public Character getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    return 's';
  }

}
