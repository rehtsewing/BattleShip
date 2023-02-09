package edu.duke.yl883.battleship;

/**
 * Interface Board<T> with a generic type T
 * @method getWidht()
 * @method getHeight()
 */
public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public String tryAddShip(Ship<T> toAdd);
  public T whatIsAtForSelf(Coordinate where);
  public T whatIsAtForEnemy(Coordinate where);
  public Ship<T> fireAt(Coordinate c);
  public Ship<T> takeoutShip(Coordinate c);
  public boolean loseCheck();


}
