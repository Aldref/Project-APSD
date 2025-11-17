package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  // LinearVectorBase
  protected LinearVectorBase(TraversableContainer<Data> con) {
    super();
    if (con != null) {
      long sz = con.Size().ToLong();
      ArrayAlloc(Natural.Of(sz));
      final long[] idx = {0L};
      con.TraverseForward(dat -> {
        arr[(int) idx[0]] = dat;
        idx[0]++;
        return false;
      });
    }
  }
  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...
  // Realloc 
  @Override
  public void Realloc(Natural newsize) {
    if (newsize == null) throw new IllegalArgumentException("New size cannot be null!");
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); 
    Data[] oldArr = arr;
    ArrayAlloc(newsize);
    if (oldArr != null){
      int copyLen = (oldArr.length < arr.length) ? oldArr.length : arr.length; 
      for (int i = 0; i < copyLen; i++) {
        arr[i] = oldArr[i];
      }
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  // GetAt
  @Override
  public Data GetAt(Natural index) {
    long idx = ExcIfOutOfBound(index);
    return arr[(int) idx];
  }

  
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...
  // SetAt
  @Override
  public void SetAt(Data data, Natural index) {
    long idx = ExcIfOutOfBound(index);
    arr[(int) idx] = data;
  }
}
