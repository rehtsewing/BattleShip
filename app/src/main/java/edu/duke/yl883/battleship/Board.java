package edu.duke.yl883.battleship;

/**
 * Interface Board<T> with a generic type T
 * @method getWidht()
 * @method getHeight()
 */
public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public boolean tryAddShip(Ship<T> toAdd);
  public T whatIsAt(Coordinate where);
}
