package edu.duke.ece651.teamX.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "submarine", c, 1, 3, 's', '*');

    Coordinate c1 = new Coordinate(3, 2);
    Coordinate c2 = new Coordinate(1, 3);

    assertEquals("submarine", rShip.getName());
    assertEquals(true, rShip.occupiesCoordinates(c));
    assertEquals(true, rShip.occupiesCoordinates(c1));
    assertEquals(false, rShip.occupiesCoordinates(c2));
  }
  @Test
  public void test_hit_at() {
    Coordinate c1 = new Coordinate(2, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "carrier", c1, 4, 1, 's', '*');
    Coordinate h1 = new Coordinate(2, 3);
    Coordinate h2 = new Coordinate(2, 4);
    rShip.recordHitAt(h1);
    rShip.recordHitAt(h2);
    assertEquals(true, rShip.wasHitAt(h1));
    assertEquals(true, rShip.wasHitAt(h2));
    assertEquals(false, rShip.wasHitAt(c1));
  }
  @Test
  public void test_invalid_where() {
    Coordinate c1 = new Coordinate(2, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "submarine", c1, 4, 1, 's', '*');
    assertThrows(IllegalArgumentException.class, ()->rShip.getDisplayInfoAt(new Coordinate(1, 2), true));
    assertThrows(IllegalArgumentException.class, ()->rShip.recordHitAt(new Coordinate(3, 2)));
    assertThrows(IllegalArgumentException.class, ()->rShip.wasHitAt(new Coordinate(1, 3)));
    assertThrows(IllegalArgumentException.class, ()->rShip.wasHitAt(new Coordinate(2, 6)));
    assertThrows(IllegalArgumentException.class, ()->rShip.recordHitAt(new Coordinate(2, 1)));
  }
  private void sunkHelper(Ship<Character> rShip, Coordinate c, int w, int l) {
    for(int i = c.getRow(); i < c.getRow() + l; i++) {
      for(int j = c.getColumn(); j < c.getColumn() + w; j++) {
        Coordinate c1 = new Coordinate(i, j);
        rShip.recordHitAt(c1);
      }
    }
  }
        
  @Test
  public void test_isSunk() {
    Coordinate c1 = new Coordinate(2, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "carrier", c1, 3, 1, 's', '*');
    rShip.recordHitAt(c1);
    assertEquals(false, rShip.isSunk());
    sunkHelper(rShip, c1, 3, 1);
    assertEquals(true, rShip.isSunk());
  }
        
  @Test
  public void test_display_info_at() {
    Coordinate c1 = new Coordinate(2, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "carrier", c1, 1, 2, 's', '*');
    rShip.recordHitAt(c1);
    assertEquals('s', rShip.getDisplayInfoAt(c1, false));
    assertEquals('*', rShip.getDisplayInfoAt(c1, true));
    Coordinate c2 = new Coordinate(3, 2);
    assertEquals(null, rShip.getDisplayInfoAt(c2, false));
    assertEquals('s', rShip.getDisplayInfoAt(c2, true));
  }
  @Test
  public void test_get_coordinates() {
    Coordinate c1 = new Coordinate(2, 2);
    Coordinate c2 = new Coordinate(3, 2);
    Coordinate c3 = new Coordinate(4, 2);
    RectangleShip<Character> rShip = new RectangleShip<Character>('V', "carrier", c1, 1, 3, 's', '*');
    HashSet<Coordinate> s = new HashSet<>();
    s.add(c1);
    s.add(c2);
    s.add(c3);
    assertEquals(s, rShip.getCoordinates());
  }
  
}
