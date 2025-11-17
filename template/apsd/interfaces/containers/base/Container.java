package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: Container, la base di tutti i contenitori. */
public interface Container<Data> {

  //size
  Natural Size();

  //is empty
  default boolean IsEmpty(){
    Natural s = Size();
    return s == null || s.IsZero();
  }

}
