package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board b1 = new BattleShipBoard(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimension() {
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard(-1, 20));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard(10, -2));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard(0, 8));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard(3, 0));
  }
}
