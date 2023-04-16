package edu.duke.ece651.teamX.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.function.Function;

/** Computer player pattern in text mode*/
public class TextComputer extends AbstractTextUser{
    /** Computer logic used if this player 
   * is computer*/
  final ComputerLogic cl;

  
  /**
   * Construct the textcomputer with specfied name, board,
   * BufferedReader, PrintStream, factory and enemyboard
   * @param name is name of the player
   * @param theBoard    is board used for current player
   * @param input is the input reader for current player
   * @param out         is the out stream for current player
   * @param f is version 2 ship factory of current player
   * @param enemyBoard is the board of enemy 
   */
  public TextComputer(String name, Board<Character> theBoard, BufferedReader input, PrintWriter out, V2ShipFactory f, Board<Character> enemyBoard) {
    super(name, theBoard, input, out, f, enemyBoard);
    this.cl = new ComputerLogic();
  }
  @Override
  public boolean isComputer() {return true;}
  /** {@inheritDoc}*/
  @Override
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    String place = cl.nextDeploy();
    Placement p = new Placement(place, shipCreationVersion.get(shipName));
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
  }

  /** {@inheritDoc}*/
  @Override
  public void doPlacementPhase() throws IOException {
    for(String name : shipsToPlace) {
      doOnePlacement(name, shipCreationFns.get(name));
    }
  }

  /** {@inheritDoc}*/
  @Override
  public void playOneTurn() throws IOException {
    doOneAction();
  }
 /** {@inheritDoc}*/
  @Override
  public void doOneAction() throws IOException {
    conductFire();
  }
 /** {@inheritDoc}*/
  @Override
  public void conductFire() throws IOException {
    String coord = cl.nextAction();
    Coordinate c = new Coordinate(coord);
    Ship<Character> s = enemyBoard.fireAt(c);
   if(s != null) {
     send(name + " hit a " + s.getName() + "!\n");
    } else {
      send(name + " missed!\n");
    }
   }
}
