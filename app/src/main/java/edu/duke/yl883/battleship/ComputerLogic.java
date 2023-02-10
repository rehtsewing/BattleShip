package edu.duke.yl883.battleship;

import java.util.ArrayList;

/**
 * Logic pattern of how will computer
 * deploy ships and play this game
 */
public class ComputerLogic {
  final ArrayList<String> deployLogic, playLogic;
  int deployNum, playNum;
  /**
   * Construct a computer logic by initiate 
   * the deploy logic string and play logic 
   * string
   */
  public ComputerLogic() {
    this.deployLogic = new ArrayList<String>();
    this.playLogic = new ArrayList<String>();
    this.deployNum = 0;
    this.playNum = 0;
    makeDeployLogic();
    makePLayLogic();
  }
  /**
   * Get the logic of this computer
   * @return all logic string
   */
  public String nextAction() {
    String s = playLogic.get(playNum);
    playNum += 1;
    return s;
  }
  /**
   * Get the logic of this computer
   * @return all logic string
   */
  public String nextDeploy() {
    String s = deployLogic.get(deployNum);
    deployNum += 1;
    return s;
  }
  /**
   * Make the deploy input logic string
   * @return deploy logic string
   */
  private void makeDeployLogic() {
    deployLogic.add("A0V");
    deployLogic.add("A1V");
    deployLogic.add("A2V");
    deployLogic.add("A3V");
    deployLogic.add("A4V");
    deployLogic.add("E0U");
    deployLogic.add("E3R");
    deployLogic.add("E5L");
    deployLogic.add("H0U");
    deployLogic.add("H2U");
  }
  /**
   * Make the play input logic string
   * @return play logic string
   */
  private void makePLayLogic() {
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 20; j++) {
        char row = 'A';
        row += i;
        String s = row + "" + j;
        playLogic.add(s);
      }
    }
  }
}
