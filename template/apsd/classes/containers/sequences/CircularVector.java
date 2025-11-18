package apsd.classes.containers.sequences;

import java.lang.reflect.Array;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.MutableSequence; // DA RIVEDERE

/** Object: Concrete (static) circular vector implementation. */
public class CircularVector<Data> extends CircularVectorBase<Data> { // Must extend CircularVectorBase

  // public CircularVector()
  public CircularVector() {
    ArrayAlloc(Natural.ZERO);
  }

  // public CircularVector(Natural inisize)
  public CircularVector(Natural inisize) {
    ArrayAlloc(inisize);
  }

  // public CircularVector(TraversableContainer<Data> con)
  public CircularVector(TraversableContainer<Data> con) {
    this();
    if (con != null) {
      con.TraverseForward(d -> {
        this.InsertLast(d); 
        return false;
      });
    }
  }

  // protected CircularVector(Data[] arr)
  protected CircularVector(Data[] arr) {
    this.arr = arr;
  }

  // NewVector
  @Override
  protected void NewVector(Data[] arr) {
    this.arr = arr;
  }

  
  // DA RIVEDERE
  @Override
  protected MutableSequence<Data> NewVector(Natural newsize) {
    CircularVector<Data> vec = new CircularVector<>();
    vec.ArrayAlloc(newsize);   
    return vec;
  }
}
