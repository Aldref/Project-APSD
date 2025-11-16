package apsd.classes.containers.sequences;

import java.lang.reflect.Array;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

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

  // protected CircularVector(Data[] arr)
  protected CircularVector(Data[] arr) {
    this.arr = arr;
  }

  // NewVector
  void NewVector(Data[] arr) {
    this.arr = arr;
  }
  
}
