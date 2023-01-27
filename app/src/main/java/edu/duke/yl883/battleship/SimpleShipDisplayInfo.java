package edu.duke.yl883.battleship;

/**
 * SimpleShipDisplayInfo 
 * have two fields of type T: one for 
 *        myData and one for onHit
 * have a constructor that takes two 
 *        Ts and initializes myData and onHit
 * getInfo check if (hit) and returns 
 *        onHit if so, and myData otherwise.
 */
public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  /**
   * myData is info of my board
   * onHit is the hit info on enemy board
   */
  T myData, onHit;
  /**
   * Construct a SimpleSimpleShipDisplayInfo
   * with specified myData and onHit info
   *
   * @param data is info for myData
   * @param h is info for hit
   */
  public SimpleShipDisplayInfo(T data, T h) {
    this.myData = data;
    this.onHit = h;
  }
  /** {@inheritDoc} */
  @Override
  public T getInfo(Coordinate where, boolean hit) {
    if(hit) return onHit;
    else return myData;
  }

}
