package edu.duke.yl883.battleship;

/** 
 * Rule that ship has no collision
 * with other ships already on the board
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  /**
   * Construct a NoCollisionRuleChecker 
   * with the constructor of the parent
   * class
   */
  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }
  /** {@inheritDoc} */
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()) {
      T sign = theBoard.whatIsAt(c);
      if(sign == null) {
        continue;
      }
      return false;
    }
    return true;
  }

}
