package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_where_and_orientation() {
    Coordinate c1 = new Coordinate(4, 5);
    Placement p1 = new Placement(c1, 'V');
    assertEquals(4, p1.getWhere().getRow());
    assertEquals(5, p1.getWhere().getColumn());
    assertEquals('V', p1.getOrientation());
  }
  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3); 
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c1, 'v');
    Placement p3 = new Placement(c2, 'V');
    Placement p4 = new Placement(c1, 'H');
    assertEquals(p1, p1);   //equals should be reflexsive
    assertEquals(p1, p2);   //different objects bu same contents
    assertNotEquals(p1, p3);  //different contents
    assertNotEquals(p1, p4);
    assertNotEquals(p1, "((1, 2), V)"); //different types
  }
  
  @Test
  public void test_toString() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3); 
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c1, 'v');
    Placement p3 = new Placement(c2, 'V');
    Placement p4 = new Placement(c1, 'H'); 
    Placement p5 = new Placement(c3, 'H');
    assertEquals("B2V", p1.toString());
    assertEquals(p1.toString(), p2.toString());
    assertEquals(p1.toString(), p3.toString());
    assertNotEquals(p1.toString(), p4.toString());
    assertNotEquals(p4.toString(), p5.toString());
  }

  @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3); 
    Placement p1 = new Placement(c1, 'V');
    Placement p2 = new Placement(c1, 'v');
    Placement p3 = new Placement(c3, 'V');
    Placement p4 = new Placement(c1, 'H'); 
    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
  }
 @Test
  void test_string_constructor_valid_cases() {
    Placement p1 = new Placement("B3V");
    Coordinate c1 = new Coordinate("B3");
    Placement p2 = new Placement(c1, 'V');
    assertEquals(p1, p2);
    Placement p3 = new Placement("D5h");
    Coordinate c2 = new Coordinate("D5");
    Placement p4 = new Placement(c2, 'H');
    assertEquals(p3, p4);
  }
  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("D51"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("AA"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("@"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("[0V"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1VV"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("[2H"));
    assertThrows(IllegalArgumentException.class, () -> new Placement(""));
    assertThrows(IllegalArgumentException.class, () -> new Placement("01H"));
  }

}
