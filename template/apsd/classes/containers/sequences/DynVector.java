package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data> { // Must extend DynLinearVectorBase

  // public DynVector()
  public DynVector() {
    ArrayAlloc(Natural.ONE); 
  }

  // public DynVector(Natural inisize)
  public DynVector(Natural inisize) {
    ArrayAlloc(inisize);
  }

  // public DynVector(TraversableContainer<Data> con)
  public DynVector(TraversableContainer<Data> con) {
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

  // protected DynVector(Data[] arr)
  protected DynVector(Data[] arr) {
    this.arr = arr;
    size = arr.length;
  }

  // NewVector
  void NewVector(Data[] arr) {
    this.arr = arr;
    size = arr.length;
  }
}
