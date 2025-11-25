package apsd.classes.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Data> { // Must extend LinearVectorBase

  // public Vector()
  
  public Vector() {
    super((TraversableContainer<Data>) null);
  }

  // public Vector(Natural inisize)
  public Vector(Natural inisize) {
    super((TraversableContainer<Data>) null);
    ArrayAlloc(inisize);       
  }

  // public Vector(TraversableContainer<Data> con)
  public Vector(TraversableContainer<Data> con) {
    super(con);
  }

  // protected Vector(Data[] arr)
  protected Vector(Data[] arr) {
    super((TraversableContainer<Data>) null);
    this.arr = arr;
  }

  // NewVector
  @Override
  protected void NewVector(Data[] arr) {
    this.arr = arr;
  }

  @Override
  protected Vector<Data> NewVector(Natural newsize) {
    return new Vector<Data>(newsize);
  }
}
