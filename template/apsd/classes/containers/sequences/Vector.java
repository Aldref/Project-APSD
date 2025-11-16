package apsd.classes.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Data> { // Must extend LinearVectorBase

  // public Vector()
  public Vector() {
    ArrayAlloc(Natural.ZERO);
  }

  // public Vector(Natural inisize)
  public Vector(Natural inisize) {
    ArrayAlloc(inisize);
  }

  // public Vector(TraversableContainer<Data> con)
  public Vector(TraversableContainer<Data> con) {
    Natural conSize = con.Size();
    ArrayAlloc(conSize);
    MutableForwardIterator<Data> it = con.FIterator();
    Natural idx = Natural.ZERO;
    while (it.IsValid()) {
      arr[(int) idx.ToLong()] = it.GetCurrent();
      idx = idx.Increment();
      it.Next();
    }
  }

  // protected Vector(Data[] arr)
  protected Vector(Data[] arr) {
    this.arr = arr;
  }

  // NewVector
  @Override
  protected void NewVector(Data[] arr) {
    this.arr = arr;
  }

}
