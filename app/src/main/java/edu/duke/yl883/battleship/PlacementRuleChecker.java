package edu.duke.yl883.battleship;

/** Chain of Responsibility pattern*/
public abstract class PlacementRuleChecker<T> {
  private final PlacementRuleChecker<T> next;

  /**
   * Construct a PlacementRuleChecker with the
   * next checker specified
   *
   * @param next is next checker
   */
   public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
   }
  /**
   * Check own rule
   *
   * @param theShip is ship to be placed
   * @param theBoard is current board
   * @return true if rule is followed   
   */
  protected abstract boolean checkMyRule(Ship<T> theShip, Board<T> theBoard);
  /**
   * Recursively check the rules on the
   * chain for current placement
   *
   * @param theShip is ship to be placed
   * @param theBoard is current board
   * @return true for legal placement, 
   *         false otherwise
   */
  public boolean checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    if (!checkMyRule(theShip, theBoard)) {
      return false;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return true;
  }

}
