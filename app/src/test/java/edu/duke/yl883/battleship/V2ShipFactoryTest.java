package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  private void checkShip(Ship<Character> testShip, String expectedName,
                         char expectedLetter, Coordinate... expectedLocs) {
    assertEquals(expectedName, testShip.getName());
    for(Coordinate c : expectedLocs) {
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
    }
  }

  @Test
  public void test_make_destroyer() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> dst = f.makeDestroyer(v1_2);
    checkShip(dst, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
  }

  @Test
  public void test_make_submarine() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(2, 2), 'H');
    Ship<Character> dst = f.makeSubmarine(v1_2);
    checkShip(dst, "Submarine", 's', new Coordinate(2, 2), new Coordinate(2, 3));
  }
  @Test
  public void test_make_carrier() {
    V2ShipFactory f = new V2ShipFactory();
    Placement u1_1 = new Placement(new Coordinate(1, 1), 'U');
    Ship<Character> dst = f.makeCarrier(u1_1);
    checkShip(dst, "Carrier", 'c', new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(3, 1), new Coordinate(4, 1), new Coordinate(3, 2), new Coordinate(4, 2), new Coordinate(5, 2));
  }
  
  @Test
  public void test_make_battleship() {
    V2ShipFactory f = new V2ShipFactory();
    Placement r1_2 = new Placement(new Coordinate(1, 2), 'R');
    Ship<Character> dst = f.makeBattleship(r1_2);
    checkShip(dst, "Battleship", 'b', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2), new Coordinate(2, 3));
    Placement d3_2 = new Placement(new Coordinate(3, 2), 'D');
    Ship<Character> s2 = f.makeBattleship(d3_2);
    checkShip(s2, "Battleship", 'b', new Coordinate(3, 2), new Coordinate(3, 3), new Coordinate(3, 4), new Coordinate(4, 3));
    Placement l2_2 = new Placement(new Coordinate(2, 2), 'L');
    Ship<Character> s3 = f.makeBattleship(l2_2);
    checkShip(s3, "Battleship", 'b', new Coordinate(2, 3), new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(3, 2));
  }

  @Test
  public void test_make_invalid_ship() {
    V1ShipFactory f = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class, ()->f.makeDestroyer(new Placement(new Coordinate(1, 2), 'Q')));
    V2ShipFactory f2 = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, ()->f2.makeBattleship(new Placement(new Coordinate(1, 2), 'A')));
  }         

}
