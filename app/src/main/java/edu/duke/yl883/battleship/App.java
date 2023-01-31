/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.yl883.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**c- Highest level structure of battleship game */
public class App {
  /** Two textplayer of the game*/
  final TextPlayer player1, player2; 
  /**
   * Construct the game app with specfied two players
   *
   * @param player1 is first player
   * @param player2 is second player
   */
  public App(TextPlayer player1, TextPlayer player2) {
    this.player1 = player1;
    this.player2 = player2;
  }
  /** 
   * Handle player1 and player 2 to
   * start placement phase 
   */
  public void doPlacementPhase() throws IOException{
    player1.doPlacementPhase();
    player2.doPlacementPhase();
  }

  public static void main(String[] args) throws IOException {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    Board<Character> b2 = new BattleShipBoard<Character>(10, 20);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    V1ShipFactory factory = new V1ShipFactory();
    TextPlayer p1 = new TextPlayer("A", b1, input, System.out, factory);
    TextPlayer p2 = new TextPlayer("B", b2, input, System.out, factory);

    App app = new App(p1, p2);
    app.doPlacementPhase();
  }
}
