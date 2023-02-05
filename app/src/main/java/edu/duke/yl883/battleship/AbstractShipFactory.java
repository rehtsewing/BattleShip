package edu.duke.yl883.battleship;


/**
 * This interface represents an Abstract Factory 
 * pattern for Ship creation.
 */
public interface AbstractShipFactory<T> {
  /**
   * Make a submarine.
   * 
   * @param where specifies the top left corner and 
   *        orientation of the ship to make
   * @return the Ship created as a Submarine.
   */
  public Ship<T> makeSubmarine(Placement where);

  /**
   * Make a battleship.
   * 
   * @param where specifies the top left corner and 
   *        orientation of the ship to make
   * @return the Ship created as a Battleship.
   */
  public Ship<T> makeBattleship(Placement where);

  /**
   * Make a carrier.
   * 
   * @param where specifies the top left corner and 
   *        orientation of the ship to make
   * @return the Ship created as a Carrier.
   */

  public Ship<T> makeCarrier(Placement where);

  /**
   * Make a destroyer.
   * 
   * @param where specifies the top left corner and 
   *        orientation of the ship to make
   * @return the Ship created as a Destroyer.
   */

  public Ship<T> makeDestroyer(Placement where);

}
