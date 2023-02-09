package edu.duke.yl883.battleship;

/** Create Version 1 ship, adopting RectangleShip */
public class V1ShipFactory implements AbstractShipFactory<Character> {
  /**
   * Help to create the V1 ship object for all kinds of ships
   *
   * @param where  is the placement taken to construct the placement
   * @param w      is the width for vertical placed ship
   * @param h      is the height for vertical placed ship
   * @param letter is representation of ship type
   * @param name   is name of the ship
   * @throws IllegalArgumentException if orientation letter is not
   *                                  either H or V
   */
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {
    char ori = where.getOrientation();
    if (ori == 'H') {
      int temp = w;
      w = h;
      h = temp;
    } else if (ori != 'V') {
      throw new IllegalArgumentException("Invalid orientation letter " + ori);
    }
    Ship<Character> s = new RectangleShip<Character>(ori, name, where.getWhere(), w, h, letter, '*');
    return s;
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return createShip(where, 1, 2, 's', "Submarine");
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return createShip(where, 1, 4, 'b', "Battleship");
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return createShip(where, 1, 6, 'c', "Carrier");
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return createShip(where, 1, 3, 'd', "Destroyer");
  }

}
