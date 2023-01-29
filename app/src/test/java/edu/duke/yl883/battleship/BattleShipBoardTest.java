package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimension() {
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(-1, 20));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(10, -2));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(0, 8));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(3, 0));
  }
  
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    for(int i = 0; i < b.getWidth(); i++) {
      for(int j = 0; j < b.getHeight(); j++) {
        Coordinate c = new Coordinate(i, j);
        assertEquals(b.whatIsAt(c), expected[i][j]);
      }
    }
  }
  private void addOneBasicShip(BattleShipBoard<Character> b, Character[][] expected, int row, int col) { 
    Coordinate c = new Coordinate(row, col);
    Ship<Character> s = new RectangleShip<Character>(c, 's', '*');
    b.tryAddShip(s);
    expected[row][col] = 's';
  }
  @Test
  public void test_what_at_board() {
    int wid = 3;
    int col = 5;
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col);
    Character[][] expected = new Character[wid][col];
    for(Character[] c : expected) {
      Arrays.fill(c, null);
    }
    checkWhatIsAtBoard(b1, expected);
    addOneBasicShip(b1, expected, 0, 1);
    checkWhatIsAtBoard(b1, expected);
    addOneBasicShip(b1, expected, 2, 1);
    checkWhatIsAtBoard(b1, expected);
  }
}
