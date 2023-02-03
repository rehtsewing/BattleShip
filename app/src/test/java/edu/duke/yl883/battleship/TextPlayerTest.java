package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory, board);
  }

  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);

    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]); // did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }
  private void errorHandleHelper (String promptAll, TextPlayer player, ByteArrayOutputStream bytes) throws IOException{
    String prompt = "Player A where do you want to place a Destroyer?\n";
    promptAll = prompt + promptAll + prompt; 
    Placement expected = new Placement(new Coordinate(0, 0), 'V');
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');

    V1ShipFactory f = new V1ShipFactory();
    player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
    b1.tryAddShip(f.makeDestroyer(expected));
    BoardTextView expectedView = new BoardTextView(b1);
    assertEquals(promptAll + expectedView.displayMyOwnBoard() + "\n", bytes.toString());
  }
  
  @Test
  void test_all_error_handling() throws IOException {
    //EOF problem
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);

    V1ShipFactory f = new V1ShipFactory();
    assertThrows(EOFException.class, ()->player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a)));

    //invalid orientation
    bytes.reset();
    TextPlayer player1 = createTextPlayer(10, 20, "A0Q\nA0V\n", bytes);
    String promptAll1 = "That placement is invalid: it does not have the correct format.\n";
    errorHandleHelper(promptAll1, player1, bytes);
  
    //Invalid Placement string
    bytes.reset();
    TextPlayer player2 = createTextPlayer(10, 20, "ABV\nA0V\n", bytes);
    errorHandleHelper(promptAll1, player2, bytes);
  
    // tryAddShip problem
    bytes.reset();
    TextPlayer player3 = createTextPlayer(10, 20, "A9H\nA0V\n", bytes);
    String promptAll = "That placement is invalid: the ship goes off the right of the board.\n";

    errorHandleHelper(promptAll, player3, bytes);
  }

  @Test
  void test_do_one_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC3H\na8v\n", bytes);

    String prompt = "Player A where do you want to place a Destroyer?\n";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 3), 'H');
    expected[2] = new Placement(new Coordinate(0, 8), 'V');
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    BoardTextView[] expectedView = new BoardTextView[3];
    V1ShipFactory f = new V1ShipFactory();

    for (int i = 0; i < expected.length; i++) {
      player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
      b1.tryAddShip(f.makeDestroyer(expected[i]));
      expectedView[i] = new BoardTextView(b1);
      assertEquals(prompt + expectedView[i].displayMyOwnBoard() + "\n", bytes.toString());
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_do_placement_phase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "A0V\nA1V\nA2V\nA3V\nA4V\nA5V\nA6V\nA7V\nA8V\nA9V\n", bytes);

    player.doPlacementPhase();

    StringBuilder message = new StringBuilder();
    message.append("Player A: you are going to place the following ships (which are all\n");
    message.append("rectangular). For each ship, type the coordinate of the upper left\n");
    message.append("side of the ship, followed by either H (for horizontal) or V (for\n");
    message.append("vertical).  For example M4H would place a ship horizontally starting\n");
    message.append("at M4 and going to the right.  You have\n\n");
    message.append("2 \"Submarines\" ships that are 1x2\n");
    message.append("3 \"Destroyers\" that are 1x3\n");
    message.append("3 \"Battleships\" that are 1x4\n");
    message.append("2 \"Carriers\" that are 1x6\n\n");

    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    V1ShipFactory f = new V1ShipFactory();
    String emptyBoard = (new BoardTextView(b1)).displayMyOwnBoard() + "\n";
    
    Placement[] expected = new Placement[10];
    addShipHelper(expected, 0, "Submarine", b1, message, (a)->f.makeSubmarine(a), 2);
    addShipHelper(expected, 2, "Destroyer", b1, message, (a)->f.makeDestroyer(a), 3);
    addShipHelper(expected, 5, "Battleship", b1, message, (a)->f.makeBattleship(a), 3);
    addShipHelper(expected, 8, "Carrier", b1, message, (a)->f.makeCarrier(a), 2);
    assertEquals(emptyBoard + message.toString(), bytes.toString());
    bytes.reset(); // clear out bytes for next time around

  }
  private void addShipHelper(Placement[] expected, int start, String name, Board<Character> b1, StringBuilder message, Function<Placement, Ship<Character>> createFn, int num) {
   String prompt = "Player A where do you want to place a " + name +"?\n";
    for(int i = start; i < start + num; i++) {
      message.append(prompt);
      expected[i] = new Placement(new Coordinate(0, i), 'V');
      b1.tryAddShip(createFn.apply(expected[i]));
      BoardTextView expectedView = new BoardTextView(b1);
      message.append(expectedView.displayMyOwnBoard() + "\n");
    }
  }
  @Test
  void test_is_lose() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("B2V\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    
    TextPlayer player = new TextPlayer("A", board, input, output, shipFactory, board);
    V1ShipFactory f = new V1ShipFactory();
    player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
    board.fireAt(new Coordinate(1, 2));
    board.fireAt(new Coordinate(2, 2));
    assertEquals(false, player.isWin());
    board.fireAt(new Coordinate(3, 2));
    assertEquals(true, player.isWin());
  }
    
}
