package edu.duke.ece651.teamX.shared;

import java.util.Arrays;
import java.util.function.Function;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the
 * enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.
   */
  public BoardTextView(Board<Character> toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }
  /**
   * Get the string of own board
   */
   public String displayMyOwnBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
  }
  /**
   * Get the string of enemy board
   */
   public String displayEnemyBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
  }

  /**
   * Get the string of any board
   * @param getSquareFn if Func used to display
   * info at coordination in different board
   */
  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    String h = makeHeader();
    String m = makeBody(getSquareFn);
    return h + m + h;
  }

  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  /**
   * This makes the lines for body of board, e.g. A |s| A\n
   * 
   * @param getSquareFn if Func used to display
   * info at coordination in different board
   * @return the String that is the body for the given board
   */
  String makeBody(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder(""); // Start at nothing
    for (int i = 0; i  < toDisplay.getHeight(); i++) {
      String sep=" "; //Start with a space, then switch to | to separate
      char letter = 'A';
      letter += i;
      ans.append(letter);
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        String sign = " "; //The sign of which specific coordinate
        Coordinate c = new Coordinate(i, j);
        if(getSquareFn.apply(c) != null) sign = getSquareFn.apply(c).toString(); 
        ans.append(sep);
        ans.append(sign);
        sep = "|";
      }
      ans.append(" ");
      ans.append(letter);
      ans.append("\n");
    }
    return ans.toString();
  }

  /**
   * Display board together with enemy board
   * @param enemyView is view info of enemy board
   * @param myHeader is header info of my board
   * @param enemyHeader is header info of enemy board
   */
  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    String [] ownLines = displayMyOwnBoard().split("\n");
    String [] enemyLines = enemyView.displayEnemyBoard().split("\n");
    StringBuilder res = new StringBuilder("     ");
    int width = toDisplay.getWidth();
    int height = toDisplay.getHeight();
    makeMyAndEnemyHeader(myHeader, enemyHeader, width, res);
    
    char[] bodyBlankMid = new char[16];
    Arrays.fill(bodyBlankMid, ' ');
    for(int i = 0; i < height + 2; i++) {
      res.append(ownLines[i]);
      res.append(bodyBlankMid);
      if(i == 0 || i == height + 1) res.append("  ");
      res.append(enemyLines[i] + "\n");
    }
    return res.toString();
  }
  /**
   * Make the header line of the two board 
   * side by side
   * @param myHeader is header info of my board
   * @param enemyHeader is header info of enemy board
   * @param res is the StringBuilder of the result string
   */
  private void makeMyAndEnemyHeader(String myHeader, String enemyHeader, int width, StringBuilder res) {
    char[] headerBlankMid = new char[2*width+17-myHeader.length()];
    Arrays.fill(headerBlankMid, ' ');
    res.append(myHeader);
    res.append(headerBlankMid);
    res.append(enemyHeader + "\n");
  }

}
