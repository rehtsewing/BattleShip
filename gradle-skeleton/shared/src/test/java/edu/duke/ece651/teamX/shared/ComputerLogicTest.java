package edu.duke.ece651.teamX.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComputerLogicTest {
  @Test
  public void test_next_action() {
    ComputerLogic cl = new ComputerLogic();
    assertEquals("A0V", cl.nextDeploy());
    assertEquals("A0", cl.nextAction());
    assertEquals("A1", cl.nextAction());
  }

}
