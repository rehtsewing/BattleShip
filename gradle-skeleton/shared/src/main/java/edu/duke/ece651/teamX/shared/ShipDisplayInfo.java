package edu.duke.ece651.teamX.shared;


/**
 * This interface represents the Ship display information.
 */
public interface ShipDisplayInfo<T> {
  /**
   * Get information from specified coordinate
   * and whether has been hit or not
   *
   * @param where is the coordinate for the place
   * @param hit represents whether has been hit or not
   */
  public T getInfo(Coordinate where, boolean hit);
}
