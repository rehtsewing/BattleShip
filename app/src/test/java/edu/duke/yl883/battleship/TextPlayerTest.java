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
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
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
      Placement p = player.readPlacement(prompt, false);
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

    V2ShipFactory f = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
    b1.tryAddShip(f.makeDestroyer(expected));
    BoardTextView expectedView = new BoardTextView(b1);
    assertEquals(promptAll + expectedView.displayMyOwnBoard() + "\n", bytes.toString());
  }
  
  @Test
  void test_placement_error_handling() throws IOException {
    //EOF problem
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);

    V2ShipFactory f = new V2ShipFactory();
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
  
  private void attackErrorHandleHelper (String promptAll, TextPlayer player, ByteArrayOutputStream bytes) throws IOException{
    Placement expected = new Placement(new Coordinate(0, 0), 'V');
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');

    V2ShipFactory f = new V2ShipFactory();
    BoardTextView expectView = new BoardTextView(b1);

    String start = "Player A's turn:\n";
    String prompt = "Player A where do you want to fire at?\n";
    promptAll = start + expectView.displayMyBoardWithEnemyNextToIt(expectView, "Your Ocean", "Enemy's Ocean") + "\n" + prompt + promptAll + prompt;

    player.playOneTurn();
    assertEquals(promptAll + "You missed!\n" + "\n", bytes.toString());
  }
@Disabled
  @Test
  void test_coordinate_error_handling() throws IOException {
    //EOF problem
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);

    V2ShipFactory f = new V2ShipFactory();
    assertThrows(EOFException.class, ()->player.playOneTurn());

    //invalid row
    bytes.reset();
    TextPlayer player1 = createTextPlayer(10, 20, "12\nA0\n", bytes);
    String promptAll1 = "That coordinate is invalid: it does not have the correct format.\n";
    attackErrorHandleHelper(promptAll1, player1, bytes);
  
    //Invalid col
    bytes.reset();
    TextPlayer player2 = createTextPlayer(10, 20, "AAV\nA0\n", bytes);
    attackErrorHandleHelper(promptAll1, player2, bytes);
  
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
    V2ShipFactory f = new V2ShipFactory();

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
    TextPlayer player = createTextPlayer(10, 20, "A0V\nA1V\nC0V\nC1V\nC2V\nF0U\nF3U\nF6U\nI0u\nI2U\n", bytes);

    player.doPlacementPhase();

    StringBuilder message = new StringBuilder();
    message.append("Player A: you are going to place the following ships\n");
    message.append("For each ship, type the coordinate of the upper left\n");
    message.append("side of the ship, followed by either H (for horizontal) or V (for\n");
    message.append("vertical) for Submarine and Destroyer, followed by U(up), R(Right),\n");
    message.append("D(down) or L(left) for Battleship and Carrier. For example\n");
    message.append("M4H would place a Submarine or Destoyer horizontally starting\n");
    message.append("at M4 and going to the right.  You have\n\n");
    message.append("2 \"Submarines\" ships that are 1x2\n");
    message.append("3 \"Destroyers\" that are 1x3\n");
    message.append("3 \"Battleships\" \n");
    message.append("2 \"Carriers\" \n\n");

    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    V2ShipFactory f = new V2ShipFactory();
    String emptyBoard = (new BoardTextView(b1)).displayMyOwnBoard() + "\n";
    
    Placement[] expected = new Placement[10];
    addShipHelper(expected, 0, "Submarine", b1, message, (a)->f.makeSubmarine(a), 2, 1, 'V');
    addShipHelper(expected, 2, "Destroyer", b1, message, (a)->f.makeDestroyer(a), 3, 1, 'V');
    addShipHelper(expected, 5, "Battleship", b1, message, (a)->f.makeBattleship(a), 3, 3, 'U');
    addShipHelper(expected, 8, "Carrier", b1, message, (a)->f.makeCarrier(a), 2, 2, 'U');
    assertEquals(emptyBoard + message.toString(), bytes.toString());
    bytes.reset(); // clear out bytes for next time around

  }
  private void addShipHelper(Placement[] expected, int start, String name, Board<Character> b1, StringBuilder message, Function<Placement, Ship<Character>> createFn, int num, int gap, char ori) {
   String prompt = "Player A where do you want to place a " + name +"?\n";
    for(int i = 0; i < num*gap; i+=gap) {
      message.append(prompt);
      expected[i] = new Placement(new Coordinate(start, i), ori);
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
    V2ShipFactory shipFactory = new V2ShipFactory();
    
    TextPlayer player = new TextPlayer("A", board, input, output, shipFactory, board);
    V2ShipFactory f = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
    board.fireAt(new Coordinate(1, 2));
    board.fireAt(new Coordinate(2, 2));
    assertEquals(false, player.isWin());
    board.fireAt(new Coordinate(3, 2));
    assertEquals(true, player.isWin());
  }

  @Test
  void test_play_one_turn() throws IOException {

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("F\nB2\nF\nC2\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    
    TextPlayer player = new TextPlayer("A", board, input, output, shipFactory, board);
    board.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    player.playOneTurn();
    player.playOneTurn();
    
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    V2ShipFactory f = new V2ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    BoardTextView expectedView = new BoardTextView(b1);
    StringBuilder res = new StringBuilder();
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    String prompt1 =
      "Possible actions for Player A:\n\n" + " F Fire at a square\n" +
      " M Move a ship to another square (2 remaining)\n" +
      " S Sonar scan (1 remaining)\n\n" + "Player A, what would you like to do?\n\n";

    String prompt2 = "Player A where do you want to fire at?\n" + "You hit a Submarine!\n\n";
    res.append(prompt1 + prompt2);
    b1.fireAt(new Coordinate(1, 2));

    String prompt3 = "Player A where do you want to fire at?\n" + "You missed!\n\n";
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    res.append(prompt1+prompt3);
    b1.fireAt(new Coordinate(2, 2));
    assertEquals(res.toString(), bytes.toString());
    }

  @Test
  void test_conduct_scan() throws IOException {

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("S\nD3\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    
    TextPlayer player = new TextPlayer("A", board, input, output, shipFactory, board);
    board.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    player.playOneTurn();
    
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    V2ShipFactory f = new V2ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    BoardTextView expectedView = new BoardTextView(b1);
    StringBuilder res = new StringBuilder();
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    String prompt1 =
      "Possible actions for Player A:\n\n" + " F Fire at a square\n" +
      " M Move a ship to another square (2 remaining)\n" +
      " S Sonar scan (1 remaining)\n\n" + "Player A, what would you like to do?\n\n";

    String prompt2 = "Player A where do you want to do sonar scan?\n" +
      "Submarines occupy 1 squares\n" +
      "Destroyers occupy 0 squares\n" +
      "Battleships occupy 0 squares\n" +
      "Carriers occupy 0 squares\n\n";
    res.append(prompt1 + prompt2);
    assertEquals(res.toString(), bytes.toString());
    }

  @Test
  public void test_ship_after_move() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    int wid = 9;
    int col = 20;
    V2ShipFactory f = new V2ShipFactory();
    Ship<Character> s1 = f.makeSubmarine(new Placement(new Coordinate(3, 0), 'V'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    Ship<Character> res = player.shipAfterMove(s1, new Coordinate(0, 0), 'V');
    Ship<Character> res1 = player.shipAfterMove(s1, new Coordinate(2, 2), 'H');
    Ship<Character> res3 = player.shipAfterMove(s1, new Coordinate(2, 1), 'V');
    
    HashSet<Coordinate> expect0 = new HashSet<>();
    expect0.add(new Coordinate(2, 2));
    expect0.add(new Coordinate(2, 3));
    HashSet<Coordinate> expect = new HashSet<>();
    expect.add(new Coordinate(2, 1));
    expect.add(new Coordinate(3, 1));
    assertEquals(expect0, res1.getCoordinates());
    assertEquals(expect, res3.getCoordinates());
    assertEquals(true, res.occupiesCoordinates(new Coordinate(0,0)));
  }
  @Test
  public void test_default_ori_ship() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    int wid = 9;
    int col = 20;
    V2ShipFactory f = new V2ShipFactory();
    Ship<Character> s1 = f.makeBattleship(new Placement(new Coordinate(3, 0), 'L'));
    BattleShipBoard<Character> b1 = new BattleShipBoard<>(wid, col, 'X');
    s1.recordHitAt(new Coordinate(4, 0));
    Ship<Character> res = player.defaultOriShip(s1);
    
    HashSet<Coordinate> expect0 = new HashSet<>();
    expect0.add(new Coordinate(4, 0));
    expect0.add(new Coordinate(3, 1));
    expect0.add(new Coordinate(4, 1));
    expect0.add(new Coordinate(4, 2));
    assertEquals(expect0, res.getCoordinates());
    assertEquals(true, res.wasHitAt(new Coordinate(3, 1)));
  }
  @Test
  void test_conduct_move() throws IOException {

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("M\nB2\nI0H\nF\nC2\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    
    TextPlayer player = new TextPlayer("A", board, input, output, shipFactory, board);
    board.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    player.playOneTurn();
    player.playOneTurn();
    
    Board<Character> b1 = new BattleShipBoard<>(10, 20, 'X');
    V2ShipFactory f = new V2ShipFactory();
    Coordinate place = new Coordinate(0, 2);
    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(place, 'V')));
    BoardTextView expectedView = new BoardTextView(b1);
    StringBuilder res = new StringBuilder();
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    String prompt1 =
      "Possible actions for Player A:\n\n" + " F Fire at a square\n" +
      " M Move a ship to another square (2 remaining)\n" +
      " S Sonar scan (1 remaining)\n\n" + "Player A, what would you like to do?\n\n";

    String prompt2 = "Player A which ship would you like to choose?\n" + "Player A where do you want to place this Submarine?\n\n";
    res.append(prompt1 + prompt2);
    Ship<Character> s = b1.takeoutShip(place);
    Coordinate destination = new Coordinate(8, 0);
    Ship<Character> mid = player.defaultOriShip(s);
    Ship<Character> mid1 = player.shipAfterMove(s, destination, 'H');
    b1.tryAddShip(mid1);

    String prompt3 = "Player A where do you want to fire at?\n" + "You missed!\n\n";
    res.append("Player A's turn:\n" + expectedView.displayMyBoardWithEnemyNextToIt(expectedView, "Your Ocean", "Enemy's Ocean") + "\n");
    res.append(prompt1+prompt3);
    b1.fireAt(new Coordinate(2, 2));
    assertEquals(res.toString(), bytes.toString());
    }
}
    

