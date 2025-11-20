package apsd.classes.containers.sequences.abstractbases;

import java.lang.reflect.Array;
import java.util.Set;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;


/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  protected long start = 0L;

  // CircularVectorBase
  protected CircularVectorBase() {
    super();
  }

  protected CircularVectorBase(TraversableContainer<Data> con) {
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

  // ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural newsize) {
    super.ArrayAlloc(newsize);
    start = 0L;
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
        int oldidx = (int) ((start + i) % oldArr.length);
        arr[i] = oldArr[oldidx];
      }
    }
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  // GetAt
  @Override
  public Data GetAt(Natural index) {
    long idx = ExcIfOutOfBound(index);
    long realIdx = (start + idx) % arr.length;
    return arr[(int) realIdx];
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...
  // SetAt
  @Override
  public void SetAt(Data data, Natural index) {
    long idx = ExcIfOutOfBound(index);
    long realIdx = (start + idx) % arr.length;
    arr[(int) realIdx] = data;
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ...
  // ShiftLeft
  @Override
  public void ShiftLeft(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (idx < size - (idx + len)) {
      long iniwrt = idx - 1 + len;
      long wrt = iniwrt;
      for (long rdr = wrt - len; rdr >= 0; rdr--, wrt--){
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++){
        SetAt(null, Natural.Of(wrt));
      }
      start = (start + len) % arr.length;
    } else {
      super.ShiftLeft(pos, num);
    }
  }

  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (size - (idx + len) < idx) {
      long iniwrt = idx + len; 
      long wrt = iniwrt;
      long rdr = idx - 1;
      for (; rdr >= 0; rdr--, wrt--){
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt > iniwrt; wrt--){
        SetAt(null, Natural.Of(wrt));
      }
      start = (start - len + arr.length) % arr.length;
    }
    else {
        super.ShiftRight(pos, num);
    }
  }

}
