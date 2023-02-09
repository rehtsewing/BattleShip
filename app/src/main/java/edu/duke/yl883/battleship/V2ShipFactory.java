package edu.duke.yl883.battleship;

import java.util.HashSet;

public class V2ShipFactory implements AbstractShipFactory<Character>  {
  /**
   * Help to create the V2 ship object for Battleship
   * and Carrier
   *
   * @param whereVacant is the set of where vacanr in the rectangle
   *        encloses the ship
   * @param where  is the placement taken to construct the placement
   * @param w      is the width for up placed ship
   * @param h      is the height for up placed ship
   * @param letter is representation of ship type
   * @param name   is name of the ship
   */
  protected Ship<Character> createFourOriShip(HashSet<Coordinate> whereVacant, Placement where, int w, int h, char letter, String name) {
    HashSet<Coordinate> actualVacant = new HashSet<>();
    char ori = where.getOrientation();
    Coordinate topLeft = where.getWhere();
    for(Coordinate c : whereVacant) {
      actualVacant.add(applyOrientation(topLeft, ori, c, w - 1, h - 1));
    }
    if (ori == 'R' || ori == 'L') {
      int temp = w;
      w = h;
      h = temp;
    }
    Ship<Character> s = new AlienShip<Character>(ori, actualVacant, name, where.getWhere(), w, h, letter, '*');
    return s;
  }
  /**
   * Apply the orientation on the vacant pieces of the ship
   *
   * @param topLeft is coordinate of the topleft corner of 
   *        the ship
   * @param c  is the coordinate of current piece on the ship
   * @param w      is the width for up placed ship
   * @param h      is the height for up placed ship
   * @throws IllegalArgumentException if orientation letter is not
   *         R or D or L or U
   */
  public Coordinate applyOrientation(Coordinate topLeft, char ori, Coordinate c, int w, int h) {
    int row = topLeft.getRow();
    int col = topLeft.getColumn();
    if (ori == 'R') {
      row += c.getColumn();
      col += h - c.getRow();
    } else if(ori == 'D') {
      row += h - c.getRow();
      col += w - c.getColumn();
    } else if(ori == 'L' || ori == 'H') {
      row += w - c.getColumn();
      col += c.getRow();
    } else if(ori == 'U' || ori == 'V') {
      row += c.getRow();
      col += c.getColumn();
    }
    return new Coordinate(row, col);
  }
  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    V1ShipFactory f = new V1ShipFactory();
    return f.makeSubmarine(where);
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    HashSet<Coordinate> whereVacant = new HashSet<>();
    whereVacant.add(new Coordinate(0, 0));
    whereVacant.add(new Coordinate(0, 2));
    return createFourOriShip(whereVacant, where, 3, 2, 'b', "Battleship");
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    HashSet<Coordinate> whereVacant = new HashSet<>();
    whereVacant.add(new Coordinate(0, 1));
    whereVacant.add(new Coordinate(1, 1));
    whereVacant.add(new Coordinate(4, 0));
    return createFourOriShip(whereVacant, where, 2, 5, 'c', "Carrier");
  }

  /** {@inheritDoc} */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    V1ShipFactory f = new V1ShipFactory();
    return f.makeDestroyer(where);
  }
  
}
