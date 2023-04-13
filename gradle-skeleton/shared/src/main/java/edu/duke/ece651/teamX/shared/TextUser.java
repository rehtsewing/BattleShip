package edu.duke.ece651.teamX.shared;

import java.io.IOException;
import java.util.function.Function;

/** This interface represents game User pattern
 *  in text mode */
public interface TextUser {
  /** set the enemyBoard
   * @param enemy is enemybord to be set*/
  public void setEnemyBoard(Board<Character> enemy);
  /** get own board
   * @return theBoard*/
  public Board<Character> getOwnBoard();
    /**
   * Decide whether this player is select 
   * for current game
   * @return true if select, false otherwise
   */
  public boolean doPlayerSelection() throws IOException;

   /**
   * Read a Placement 
   * Create a ship based on the Placement
   * Add that ship to the board, redo this process
   * if previous adding failed
   * Print out the board (to out, not to System.out)
   * @param shipName is type string of the ship
   * @param createFn is an apply method that takes
   * a Placement and return a ship
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException;
 /**
   * (a) Display the starting (empty) board
   * (b) Print the instructions message (from the README,
   *     but also shown again near the top of this file)
   * (c) Recursively Call doOnePlacement to place all ships
   */
  public void doPlacementPhase() throws IOException;

  /**
   * Check for the win/lose of the player
   * @return true if this player win
   *        false if not win yet 
   */
  public boolean isWin();
  /**
   * Display board relevant information
   * Read a Coordinate
   * Get a ship by firing at the coordinate
   * Print relevant info according to the gotten ship
   */
  public void playOneTurn() throws IOException;

  /** 
   * Implement the action user input
   * @throw IllegalArgumentException if action letter 
   *        is not F, M or S
   */
  public void doOneAction() throws IOException;

    /**
   * Conduct fireat action
   */
  public void conductFire() throws IOException;

}
