package edu.duke.yl883.battleship;

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
  private final Board toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   * @throws IllegalArgumentException if the board is larger than 10x26.
   */
  public BoardTextView(Board toDisplay) {
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
    String h = makeHeader();
    String m = makeEmptyBody();
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
   * This makes the internal lines for empty board, e.g. A | | A\n
   * 
   * @return the String that is the empty body for the given board
   */
  String makeEmptyBody() {
    StringBuilder ans = new StringBuilder(""); // Start at nothing
    for (int i   = 0; i  < toDisplay.getHeight(); i++) {
      String sep=" "; //Start with a space, then switch to | to separate
      char letter = 'A';
      letter += i;
      ans.append(letter);
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        ans.append(sep);
        ans.append(" ");
        sep = "|";
      }
      ans.append(" ");
      ans.append(letter);
      ans.append("\n");
    }
    return ans.toString();
  }


}
