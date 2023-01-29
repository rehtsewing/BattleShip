package edu.duke.yl883.battleship;

/** Rule that ship is entirely in
 * the boundaries of the board
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T>{

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }
  /** {@inheritDoc} */
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()) {
      int r = c.getRow();
      int h = c.getColumn();
      if(r >= 0 && r < theBoard.getHeight() && h >= 0 && h < theBoard.getWidth()) {
          continue;
      }
      return false;
    }
    return true;
  }

}
