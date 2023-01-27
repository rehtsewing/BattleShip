package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_char_constructor_and_getInfo() {
    char data = 's';
    char hit = '*';
    SimpleShipDisplayInfo<Character> d = new SimpleShipDisplayInfo<>(data, hit);
    Coordinate c = new Coordinate(1, 2);
    assertEquals(data, d.getInfo(c, false));
    assertEquals(hit, d.getInfo(c, true));
      
  }

}
