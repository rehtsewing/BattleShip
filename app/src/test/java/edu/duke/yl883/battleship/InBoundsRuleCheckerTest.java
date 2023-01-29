package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_rule_checker() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(6, 6);
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(6, 4, new InBoundsRuleChecker<Character>(null));
    V1ShipFactory f = new V1ShipFactory();
    InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<>(null);
    Ship<Character> s1 = f.makeCarrier(new Placement(new Coordinate(0, 0), 'H'));
    Ship<Character> s2 = f.makeCarrier(new Placement(new Coordinate(0, 5), 'V'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(4, 5), 'V'));
    Ship<Character> s4 = f.makeSubmarine(new Placement(new Coordinate(4, 5), 'H'));
    assertEquals(true, c.checkPlacement(s1, b));
    assertEquals(true, c.checkPlacement(s1, b1));
    assertEquals(true, c.checkPlacement(s2, b));
    assertEquals(true, c.checkPlacement(s3, b));
    assertEquals(false, c.checkPlacement(s4, b));
    assertEquals(false, c.checkPlacement(s2, b1));
  }

}
