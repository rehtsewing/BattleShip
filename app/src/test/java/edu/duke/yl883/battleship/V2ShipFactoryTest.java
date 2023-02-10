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
    Placement u1_2 = new Placement(new Coordinate(1, 2), 'U');
    Ship<Character> s0 = f.makeBattleship(u1_2);
    checkShip(s0, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 4), new Coordinate(2, 3));
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
  public void test_apply_orientation() {
    //'V'
    Coordinate topLeft = new Coordinate(2, 0);
    Coordinate c = new Coordinate(2, 2);
    V2ShipFactory f = new V2ShipFactory();
    Coordinate res = f.applyOrientation(topLeft, 'V', c, 2, 1);
    assertEquals(new Coordinate(4, 2), res);
    Coordinate res1 = f.applyOrientation(topLeft, 'U', c, 2, 1);
    assertEquals(new Coordinate(4, 2), res1);
    //invalid
    Coordinate res2 = f.applyOrientation(topLeft, 'M', c, 2, 1);
    assertEquals(new Coordinate(2, 0), res2);
  }
}
