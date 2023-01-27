package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate c = new Coordinate(1, 2);
    HashSet<Coordinate> s = new HashSet<>();
    s = RectangleShip.makeCoords(c, 1, 3);
    HashSet<Coordinate> expected = new HashSet<>();
    expected.add(new Coordinate(1, 2));
    expected.add(new Coordinate(2, 2));
    expected.add(new Coordinate(3, 2));
    assertEquals(expected, s);
  }
  @Test
  public void test_constructor() {
    Coordinate c = new Coordinate(1, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>(c, 1, 3, 's', '*');

    Coordinate c1 = new Coordinate(3, 2);
    Coordinate c2 = new Coordinate(1, 3);
    
    assertEquals(true, rShip.occupiesCoordinates(c));
    assertEquals(true, rShip.occupiesCoordinates(c1));
    assertEquals(false, rShip.occupiesCoordinates(c2));
  }

}
