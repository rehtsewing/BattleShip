package edu.duke.ece651.teamX.shared;

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
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()) {
      T sign = theBoard.whatIsAtForSelf(c);
      if(sign == null) {
        continue;
      }
      return "That placement is invalid: the ship overlaps another ship.";
    }
    return null;
  }

}
