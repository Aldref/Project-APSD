package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data> { // Must extend DynLinearVectorBase

  // public DynVector()
  public DynVector() {
    super(null);
    ArrayAlloc(Natural.ONE); 
    size = 0L;
  }

  // public DynVector(Natural inisize)
  public DynVector(Natural inisize) {
    super(null);
    ArrayAlloc(inisize);
    size = 0L;
  }

  // public DynVector(TraversableContainer<Data> con)
  public DynVector(TraversableContainer<Data> con) {
    super(con);
  }

  // protected DynVector(Data[] arr)
  protected DynVector(Data[] arr) {
    super(null);
    this.arr = arr;
    size = arr.length;
  }

  // NewVector
  @Override
  protected void NewVector(Data[] arr) {
    new DynVector<>(arr);
  }

}
