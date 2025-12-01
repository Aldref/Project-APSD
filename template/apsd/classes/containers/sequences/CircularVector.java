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
    this();
    ArrayAlloc(inisize);
  }

  // public CircularVector(TraversableContainer<Data> con)
  public CircularVector(TraversableContainer<Data> con) {
    this();
    if (con != null) {
      ArrayAlloc(con.Size());
      final long[] idx = new long[] { 0L };
      con.TraverseForward(d -> {
        SetAt(d, Natural.Of(idx[0]));
        idx[0]++;
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
    new CircularVector<>(arr);
  }

}
