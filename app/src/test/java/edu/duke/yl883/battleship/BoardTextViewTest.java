package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
    /**
   * This makes the structure to test empty board with different dimension
   * 
   * int w: Width of the board
   * int h: Height of the board
   * String expectedHeader: Header expected to be generated for empty board
   * String expectedbody: Board body expected to be have
   */
 private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody){
    Board<Character> b1 = new BattleShipBoard<Character>(w, h);
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + expectedBody + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_empty_2by2() {
    String expectedHeader= "  0|1\n";
    String expectedBody=
      "A  |  A\n"+
      "B  |  B\n";
    emptyBoardHelper(2, 2, expectedHeader, expectedBody);
  }
  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20);
    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27);
    //you should write two assertThrows here
    
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, ()->new BoardTextView(tallBoard));
    
  }


  @Test
  public void test_display_empty_3by2() {
    String expectedHeader= "  0|1|2\n";
    String expectedBody=
      "A  | |  A\n"+
      "B  | |  B\n";
    emptyBoardHelper(3, 2, expectedHeader, expectedBody);
  }

  @Test
  public void test_display_empty_3by5() {
    String expectedHeader= "  0|1|2\n";
    String expectedBody=
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n";
    emptyBoardHelper(3, 5, expectedHeader, expectedBody);
  }

  @Test
  public void test_display_3by5() {
    String expectedHeader= "  0|1|2\n";
    String expectedBody=
      "A  | |  A\n"+
      "B  |s|  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |s E\n";
    String expected = expectedHeader + expectedBody + expectedHeader;
    Board<Character> b1 = new BattleShipBoard<Character>(3, 5);
    Coordinate c = new Coordinate(1, 1);
    Coordinate c1 = new Coordinate(4, 2);
    Ship<Character> s = new BasicShip(c);
    Ship<Character> s1 = new BasicShip(c1);
    b1.tryAddShip(s);
    b1.tryAddShip(s1);
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expected, view.displayMyOwnBoard());
  }
}
