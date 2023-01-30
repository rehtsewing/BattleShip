package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
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

  @Test
  void test_do_one_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);

    String prompt = "Player A where do you want to place a Destroyer?\n";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    Board<Character> b1 = new BattleShipBoard<>(10, 20);
    BoardTextView[] expectedView = new BoardTextView[3];
    V1ShipFactory f = new V1ShipFactory();

    for (int i = 0; i < expected.length; i++) {
      player.doOnePlacement();
      b1.tryAddShip(f.makeDestroyer(expected[i]));
      expectedView[i] = new BoardTextView(b1);
      assertEquals(prompt + expectedView[i].displayMyOwnBoard() + "\n", bytes.toString());
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_do_placement_phase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\n", bytes);

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

    String prompt = "Player A where do you want to place a Destroyer?\n";
    Placement expected = new Placement(new Coordinate(1, 2), 'V');
    Board<Character> b1 = new BattleShipBoard<>(10, 20);
    V1ShipFactory f = new V1ShipFactory();
    String emptyBoard = (new BoardTextView(b1)).displayMyOwnBoard() + "\n";

    player.doPlacementPhase();
    b1.tryAddShip(f.makeDestroyer(expected));
    BoardTextView expectedView = new BoardTextView(b1);
    assertEquals(emptyBoard + message.toString() + prompt + expectedView.displayMyOwnBoard() + "\n", bytes.toString());
    bytes.reset(); // clear out bytes for next time around

  }

}