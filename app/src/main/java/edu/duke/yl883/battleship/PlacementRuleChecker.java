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
   * @return null if rule is followed, 
   *         an error massgae string if
   *         violated   
   */
  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
  /**
   * Recursively check the rules on the
   * chain for current placement
   *
   * @param theShip is ship to be placed
   * @param theBoard is current board
   * @return null for legal placement, 
   *         error message otherwise
   */
  public String checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    String s = checkMyRule(theShip, theBoard);
    if (s != null) {
      return s;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return null;
  }

}
