package edu.duke.yl883.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Function;

public class TextComputer extends AbstractTextUser{
    /** Computer logic used if this player 
   * is computer*/
  final ComputerLogic cl;
  
  public TextComputer(String name, Board<Character> theBoard, BufferedReader input, PrintStream out, V2ShipFactory f, Board<Character> enemyBoard) {
    super(name, theBoard, input, out, f, enemyBoard);
    this.cl = new ComputerLogic();
  }
  
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
    enemyBoard.fireAt(c);
  }
}
