package edu.duke.yl883.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextComputerTest {

  @Test
  void test_do_one_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("Y\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    TextComputer player = new TextComputer("A", board, input, output, shipFactory, board);
    V2ShipFactory f = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (a)->f.makeDestroyer(a));
    assertEquals('d', board.whatIsAtForSelf(new Coordinate(0, 0)));
  }
  
  @Test
  void test_do_placement_phase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("Y\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    TextComputer player = new TextComputer("A", board, input, output, shipFactory, board);
    V2ShipFactory f = new V2ShipFactory();
    player.doPlacementPhase();
    assertEquals('s', board.whatIsAtForSelf(new Coordinate(0, 0)));
    assertEquals('b', board.whatIsAtForSelf(new Coordinate(4, 1)));
    assertEquals(null, board.whatIsAtForSelf(new Coordinate(4, 0)));
    assertEquals('c', board.whatIsAtForSelf(new Coordinate(7, 0)));
    assertEquals('c', board.whatIsAtForSelf(new Coordinate(10, 2)));
  }
  
  @Test
  void test_play_one_turn() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedReader input = new BufferedReader(new StringReader("Y\n"));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');   Board<Character> board1 = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    TextComputer player = new TextComputer("A", board, input, output, shipFactory, board1);
    V2ShipFactory f = new V2ShipFactory();
    board1.tryAddShip(f.makeSubmarine(new Placement(new Coordinate(0, 2), 'V')));
    player.playOneTurn();
    player.playOneTurn();
    player.playOneTurn();
    String s = "A missed!\n" + "A missed!\n" + "A hit a Submarine!";
  }
  
}
