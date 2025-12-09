package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data> { // Must extend DynCircularVectorBase

  // public DynCircularVector()
  public DynCircularVector() {
    super(null);
    ArrayAlloc(Natural.ZERO);  // modificato da ONE a ZERO
  }

  // public DynCircularVector(Natural inisize)
  public DynCircularVector(Natural inisize) {
    super(null);
    ArrayAlloc(inisize);
  }

  // public DynCircularVector(TraversableContainer<Data> con)
  public DynCircularVector(TraversableContainer<Data> con) {
    super(con);  
  }

  // protected DynCircularVector(Data[] arr)
  protected DynCircularVector(Data[] arr) {
    super(null);
    this.arr = arr;
    this.size = arr.length;
  }

  // NewVector
  @Override
  protected DynCircularVectorBase<Data> NewVector(Data[] arr) {
    return new DynCircularVector<>(arr);
  }

}