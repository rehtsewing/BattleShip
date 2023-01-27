package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {
  private void checkShip(Ship<Character> testShip, String expectedName,
                         char expectedLetter, Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for(Coordinate c : expectedLocs) {
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c));
    }
  }

  @Test
  public void test_make_destroyer() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> dst = f.makeDestroyer(v1_2);
    checkShip(dst, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
  }

  @Test
  public void test_make_submarine() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(2, 2), 'H');
    Ship<Character> dst = f.makeSubmarine(v1_2);
    checkShip(dst, "Submarine", 's', new Coordinate(2, 2), new Coordinate(2, 3));
  }
  @Test
  public void test_make_carrier() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 1), 'V');
    Ship<Character> dst = f.makeCarrier(v1_2);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1), new Coordinate(4, 1), new Coordinate(5, 1), new Coordinate(6, 1));
  }
  @Test
  public void test_make_battleship() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 3), 'V');
    Ship<Character> dst = f.makeBattleship(v1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(2, 3), new Coordinate(3, 3), new Coordinate(4, 3));
  }

  @Test
  public void test_make_invalid_ship() {
    V1ShipFactory f = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(new Placement(new Coordinate(1, 2), 'Q')));
  }         
}
