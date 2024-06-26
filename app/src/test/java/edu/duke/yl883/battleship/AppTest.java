/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

class AppTest {
  @Test
  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  void test_main() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);

    InputStream input = getClass().getClassLoader().getResourceAsStream("input.txt");
    assertNotNull(input);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output.txt");
    assertNotNull(expectedStream);
    InputStream oldIn = System.in;
    PrintStream oldOut = System.out;
    try {
      System.setIn(input);
      System.setOut(out);
      App.main(new String[0]);
    } finally {
      System.setIn(oldIn);
      System.setOut(oldOut);
    }

    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
  }
  @Test
  void test_do_player_selection() throws IOException{
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new StringReader("S\nY\nY\n"));
    V2ShipFactory factory = new V2ShipFactory();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    TextPlayer p1 = new TextPlayer("A", b1, input, output, factory, b2);
    TextPlayer p2 = new TextPlayer("A", b2, input, output, factory, b1);
    TextComputer p3 = new TextComputer("A", b3, input, output, factory, b3);
    TextComputer p4 = new TextComputer("A", b4, input, output, factory, b4);

    App app = new App(p1, p2, p3, p4);
    app.doPlayerSelection();
    String prompt = "Do you want to select A in this game?\n" +
      "Y yes\n" + "N no\n";
    String errorInfo = "Invalid choice number S\n";
    assertEquals(prompt + errorInfo + prompt + prompt, bytes.toString());
  }
  
  @Test
  void test_do_player_selection_computer() throws IOException{
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new StringReader("S\nN\nN\n"));
    V2ShipFactory factory = new V2ShipFactory();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    TextPlayer p1 = new TextPlayer("A", b1, input, output, factory, b2);
    TextPlayer p2 = new TextPlayer("A", b2, input, output, factory, b1);
    TextComputer p3 = new TextComputer("A", b3, input, output, factory, b3);
    TextComputer p4 = new TextComputer("A", b4, input, output, factory, b4);

    App app = new App(p1, p2, p3, p4);
    app.doPlayerSelection();
    String prompt = "Do you want to select A in this game?\n" +
      "Y yes\n" + "N no\n";
    String errorInfo = "Invalid choice number S\n";
    assertEquals(prompt + errorInfo + prompt + prompt, bytes.toString());
  }
  @Test
  void test_do_placement_phase() throws IOException{
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new StringReader("A0V\nA1V\nA2V\nA3V\nA4V\nA5U\nD0U\nD3U\nF0U\nF2U\nA0V\nA1V\nA2V\nA3V\nA4V\nA5U\nD0U\nD3U\nF0U\nF2U\n"));
    V2ShipFactory factory = new V2ShipFactory();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    PrintStream output1 = new PrintStream(bytes1, true);
    TextPlayer p1 = new TextPlayer("A", b1, input, output, factory, b2);
    TextPlayer p2 = new TextPlayer("A", b2, input, output1, factory, b1);
    TextComputer p3 = new TextComputer("Computer 1", b3, input, output, factory, b3);
    TextComputer p4 = new TextComputer("Computer 2", b4, input, output, factory, b4);

    App app = new App(p1, p2, p3, p4);
    app.addHuman();
    app.doPlacementPhase();
    assertEquals(bytes.toString(), bytes1.toString());
  }

  @Test
  /** action number*/
  void test_do_attack_phase_player2_win() throws IOException{
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new StringReader("F\nC2\nF\nC0\nF\nC0\nF\nC1\n"));
    V2ShipFactory factory = new V2ShipFactory();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    PrintStream output1 = new PrintStream(bytes1, true);
    b1.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    b2.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    TextPlayer p1 = new TextPlayer("A", b1, input, output, factory, b2);
    TextPlayer p2 = new TextPlayer("A", b2, input, output1, factory, b1);
    TextComputer p3 = new TextComputer("Computer 1", b3, input, output, factory, b3);
    TextComputer p4 = new TextComputer("Computer 2", b4, input, output, factory, b4);

    App app = new App(p1, p2, p3, p4);
    app.addHuman();

    Board<Character> b5 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b6 = new BattleShipBoard<Character>(10, 20, 'X');
    b5.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    b6.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    BoardTextView expectedView = new BoardTextView(b5);
    StringBuilder res = new StringBuilder();
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    String prompt =
      "Possible actions for Player A:\n\n" + " F Fire at a square\n" +
      " M Move a ship to another square (2 remaining)\n" +
      " S Sonar scan (1 remaining)\n\n" + "Player A, what would you like to do?\n" +
      "Player A where do you want to fire at?\n" + "You hit a Submarine!\n\n";
    res.append(prompt);
    b5.fireAt(new Coordinate(2, 0));

    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    res.append(prompt);
    b5.fireAt(new Coordinate(2, 1));

    res.append("Player A win the game!\n");
    
    app.doAttackingPhase();
    assertEquals(bytes1.toString(), res.toString());
  }

  @Test
  void test_do_attack_phase_player1_win() throws IOException{
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b3 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b4 = new BattleShipBoard<Character>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new StringReader("F\nC2\n"));
    V2ShipFactory factory = new V2ShipFactory();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
    PrintStream output1 = new PrintStream(bytes1, true);
    b1.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    TextPlayer p1 = new TextPlayer("A", b1, input, output, factory, b2);
    TextPlayer p2 = new TextPlayer("A", b2, input, output1, factory, b1);
    TextComputer p3 = new TextComputer("Computer 1", b3, input, output, factory, b3);
    TextComputer p4 = new TextComputer("Computer 2", b4, input, output, factory, b4);

    App app = new App(p1, p2, p3, p4);
    app.addHuman();
    
    Board<Character> b5 = new BattleShipBoard<Character>(10, 20, 'X');
    Board<Character> b6 = new BattleShipBoard<Character>(10, 20, 'X');
    b5.tryAddShip(factory.makeSubmarine(new Placement(new Coordinate(2, 0), 'H')));
    BoardTextView expectedView = new BoardTextView(b5);
    StringBuilder res = new StringBuilder();
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    String prompt =
      "Possible actions for Player A:\n\n" + " F Fire at a square\n" +
      " M Move a ship to another square (2 remaining)\n" +
      " S Sonar scan (1 remaining)\n\n" + "Player A, what would you like to do?\n" +
      "Player A where do you want to fire at?\n" + "You missed!\n\n";
    res.append(prompt);
    b5.fireAt(new Coordinate(2, 0));

    res.append("Player A win the game!\n");
    
    app.doAttackingPhase();
    assertEquals(bytes.toString(), res.toString());
  }
}
