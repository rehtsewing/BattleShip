package edu.duke.ece651.teamX.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_rule_checker() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(6, 6, 'X');
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(5, 4, new InBoundsRuleChecker<Character>(null), 'X');
    V1ShipFactory f = new V1ShipFactory();
    InBoundsRuleChecker<Character> c = new InBoundsRuleChecker<>(null);
    Ship<Character> s1 = f.makeCarrier(new Placement(new Coordinate(-1, 0), 'H'));
    Ship<Character> s2 = f.makeCarrier(new Placement(new Coordinate(0, -2), 'V'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(5, 4), 'V'));
    Ship<Character> s4 = f.makeSubmarine(new Placement(new Coordinate(3, 4), 'H'));
    assertEquals("That placement is invalid: the ship goes off the top of the board.", c.checkPlacement(s1, b));
    assertEquals("That placement is invalid: the ship goes off the left of the board.", c.checkPlacement(s2, b));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", c.checkPlacement(s3, b));
    assertEquals(null, c.checkPlacement(s4, b));
    assertEquals("That placement is invalid: the ship goes off the right of the board.", c.checkPlacement(s4, b1));
  }

}
