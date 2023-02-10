package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
  @Test
  public void test_invalid_dimension() {
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(-1, 20, 'X'));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(10, -2, 'X'));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(0, 8, 'X'));
    assertThrows(IllegalArgumentException.class, ()->new BattleShipBoard<Character>(3, 0, 'X'));
  }
  
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected, boolean isSelf) {
    for(int i = 0; i < b.getWidth(); i++) {
      for(int j = 0; j < b.getHeight(); j++) {
        Coordinate c = new Coordinate(i, j);
        assertEquals(b.whatIsAt(c, isSelf), expected[i][j]);
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
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    Character[][] expected = new Character[wid][col];
    for(Character[] c : expected) {
      Arrays.fill(c, null);
    }
    checkWhatIsAtBoard(b1, expected, true);
    addOneBasicShip(b1, expected, 0, 1);
    checkWhatIsAtBoard(b1, expected, true);
    addOneBasicShip(b1, expected, 2, 1);
    checkWhatIsAtBoard(b1, expected, true);
  }
  @Test
  public void test_rule_checker() {
    int wid = 7;
    int col = 5;
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    PlacementRuleChecker<Character> c = new InBoundsRuleChecker<Character>(new NoCollisionRuleChecker<Character>(null));
    BattleShipBoard<Character> b2 = new BattleShipBoard<>(wid, 7, c, 'X');
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeBattleship(new Placement(new Coordinate(3, 0), 'V'));
    Ship<Character> s2 = f.makeDestroyer(new Placement(new Coordinate(1, 1), 'V'));
    Ship<Character> s3 = f.makeBattleship(new Placement(new Coordinate(3, 1), 'H'));
    Ship<Character> s4 = f.makeBattleship(new Placement(new Coordinate(4, 2), 'V'));
    Ship<Character> s5 = f.makeBattleship(new Placement(new Coordinate(0, 0), 'H'));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", b1.tryAddShip(s1));
    assertEquals(null, b1.tryAddShip(s2));
    assertEquals("That placement is invalid: the ship overlaps another ship.", b1.tryAddShip(s3));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", b1.tryAddShip(s4));
    assertEquals(null, b1.tryAddShip(s5));
    assertEquals(null, b2.tryAddShip(s1));
  }
  @Test
  public void test_fire_at() {
    int wid = 5;
    int col = 6;
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(3, 0), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1); //return a string if add ship failed
    assertSame(s1, b1.fireAt(new Coordinate(3, 0)));
    assertEquals(false, s1.isSunk());
    assertEquals(null, b1.fireAt(new Coordinate(4, 1)));
    assertEquals('X',b1.whatIsAtForEnemy(new Coordinate(4, 1)));
    assertEquals(false, s1.isSunk());
    
    assertEquals(null, b1.fireAt(new Coordinate(4, 1)));
    assertEquals('X',b1.whatIsAtForEnemy(new Coordinate(4, 1)));
    assertEquals(false, s1.isSunk());
    assertSame(s1, b1.fireAt(new Coordinate(4, 0)));
    assertSame(s1, b1.fireAt(new Coordinate(5, 0)));
    assertEquals(true, s1.isSunk());
  }
  @Test
  public void test_add_hide_ship() {
    int wid = 5;
    int col = 6;
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(0, 0), 'V'));
    Ship<Character> s2 = f.makeDestroyer(new Placement(new Coordinate(0, 1), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    BattleShipBoard<Character> b2 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1); //return a string if add ship failed
    b2.tryAddShip(s2);
    Coordinate where = new Coordinate(0, 0);
    assertSame(s1, b1.fireAt(where));
    Ship<Character> s3 = b1.takeoutShip(where);
    assertEquals('d', b1.whatIsAtForEnemy(where));
    assertEquals(null, b1.whatIsAtForSelf(where));
    //what is at enemy if case
    b1.fireAt(where);
    assertEquals('X', b1.whatIsAtForEnemy(where));
    assertEquals(null, b1.whatIsAtForSelf(where));
    
    
    String note = b1.tryAddHideShip(s3);
    assertEquals(null, note);
    assertEquals('*', b1.whatIsAtForSelf(where));
    assertEquals('X', b1.whatIsAtForEnemy(where));
    b1.fireAt(new Coordinate(1, 0));
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate(1, 0)));
    assertEquals('d', b1.whatIsAtForEnemy(new Coordinate(1, 0)));
    assertEquals(null, b1.whatIsAtForEnemy(new Coordinate(0, 1)));

    Coordinate hide = new Coordinate(1, 1);
    Coordinate miss = new Coordinate(0, 1);
    b2.fireAt(hide);
    b1.fireAt(miss);
    Ship<Character> s4 = b2.takeoutShip(miss);
    b1.tryAddHideShip(s4);
    assertEquals('d', b1.whatIsAtForSelf(miss));
    assertEquals('X', b1.whatIsAtForEnemy(miss));
    assertEquals('*', b1.whatIsAtForSelf(hide));
    assertEquals(null, b1.whatIsAtForEnemy(hide));
    //test for two if statement in fire at
    b1.fireAt(hide);
    assertEquals('*', b1.whatIsAtForSelf(hide));
    assertEquals('d', b1.whatIsAtForEnemy(hide));
    b1.fireAt(miss);
    assertEquals('*', b1.whatIsAtForSelf(miss));
    assertEquals('d', b1.whatIsAtForEnemy(miss));
    
  }
  @Test
  public void test_takeout_ship() {
    int wid = 5;
    int col = 6;
    V2ShipFactory f = new V2ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(3, 0), 'V'));
    Ship<Character> s2 = f.makeDestroyer(new Placement(new Coordinate(3, 1), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1); //return a string if add ship failed
    b1.tryAddShip(s2);
    assertSame(s2, b1.takeoutShip(new Coordinate(4, 1)));
    assertSame(s1, b1.takeoutShip(new Coordinate(3, 0)));
    assertEquals(null, b1.takeoutShip(new Coordinate(4, 0)));
  }
  @Test
  public void test_what_at_enemy_board() {
    int wid = 5;
    int col = 6;
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(3, 0), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1); //return a string if add ship failed
    b1.fireAt(new Coordinate(3, 1));
    assertEquals('X', b1.whatIsAtForEnemy(new Coordinate(3, 1)));
  }
  @Test
  public void test_lose_check() {
    int wid = 5;
    int col = 6;
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(3, 0), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1); //return a string if add ship failed
    b1.fireAt(new Coordinate(3, 0));
    assertEquals(false, b1.loseCheck());
    b1.fireAt(new Coordinate(4, 0));
    assertEquals(false, b1.loseCheck());
    b1.fireAt(new Coordinate(5, 0));
    assertEquals(true, b1.loseCheck());
  }
  @Test
  public void test_scan() {
    int wid = 10;
    int col = 20;
    V2ShipFactory f = new V2ShipFactory();
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(2, 0), 'V'));
    Ship<Character> s2 = f.makeDestroyer(new Placement(new Coordinate(4, 1), 'H'));
    Ship<Character> s3 = f.makeBattleship(new Placement(new Coordinate(6, 0), 'U'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    b1.tryAddShip(s1);
    b1.tryAddShip(s2);
    b1.tryAddShip(s3);
    HashMap<Character, Integer> expect = new HashMap<>();
    expect.put('d', 3);
    expect.put('b', 4);
    expect.put(null, 14);
    assertEquals(expect, b1.scan(new Coordinate(6, 1)));
  }
}
