package edu.duke.ece651.teamX.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_no_collision() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(4, 4, 'X');
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(6, 5, new InBoundsRuleChecker<Character>(null), 'X');
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeBattleship(new Placement(new Coordinate(3, 0), 'H'));
    Ship<Character> s2 = f.makeBattleship(new Placement(new Coordinate(0, 3), 'V'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(1, 1), 'H'));
    NoCollisionRuleChecker<Character> c = new NoCollisionRuleChecker<>(null);

    b.tryAddShip(s1);
    b1.tryAddShip(s2);
    assertEquals("That placement is invalid: the ship overlaps another ship.", c.checkPlacement(s2, b));
    assertEquals(null, c.checkPlacement(s3, b1));
  }
  @Test
  public void test_chain() {
    BattleShipBoard<Character> b = new BattleShipBoard<>(4, 4, 'X');
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(6, 6, new InBoundsRuleChecker<Character>(null), 'X');
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> s1 = f.makeBattleship(new Placement(new Coordinate(3, 0), 'H'));
    Ship<Character> s2 = f.makeBattleship(new Placement(new Coordinate(0, 3), 'V'));
    Ship<Character> s3 = f.makeSubmarine(new Placement(new Coordinate(1, 4), 'H'));
    Ship<Character> s4 = f.makeCarrier(new Placement(new Coordinate(1, 0), 'H'));
    NoCollisionRuleChecker<Character> c = new NoCollisionRuleChecker<>(null);
    InBoundsRuleChecker<Character> c1 = new InBoundsRuleChecker<>(c);

    b.tryAddShip(s1);
    b1.tryAddShip(s2);
    assertEquals("That placement is invalid: the ship overlaps another ship.", c1.checkPlacement(s2, b));
    assertEquals(null, c1.checkPlacement(s3, b1));
    assertEquals("That placement is invalid: the ship goes off the right of the board.", c1.checkPlacement(s3, b));
    assertEquals("That placement is invalid: the ship overlaps another ship.", c1.checkPlacement(s4, b1));
  }

}
