package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data> { // Must extend DynCircularVectorBase

  // public DynCircularVector()
  public DynCircularVector() {
    ArrayAlloc(Natural.ZERO);
  }

  // public DynCircularVector(Natural inisize)
  public DynCircularVector(Natural inisize) {
    ArrayAlloc(inisize);
  }

  // public DynCircularVector(TraversableContainer<Data> con)
  public DynCircularVector(TraversableContainer<Data> con) {
    Natural conSize = con.Size();
    ArrayAlloc(conSize);
    MutableForwardIterator<Data> it = con.FIterator();
    Natural idx = Natural.ZERO;
    while (it.IsValid()) {
      arr[(int) idx.ToLong()] = it.GetCurrent();
      idx = idx.Increment();
      it.Next();
    }
    size = conSize.ToLong();
  }

  // protected DynCircularVector(Data[] arr)
  protected DynCircularVector(Data[] arr) {
    this.arr = arr;
    this.size = arr.length;
  }

  // NewVector
  @Override
  protected void NewVector(Data[] arr) {
    this.arr = arr;
    this.size = arr.length;
  }
  
}