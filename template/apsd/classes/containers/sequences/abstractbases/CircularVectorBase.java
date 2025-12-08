package apsd.classes.containers.sequences.abstractbases;

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
    super(con);
  }

  // ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural newsize) {
    if (newsize == null) throw new NullPointerException("Size cannot be null!");
    if (newsize.ToLong() < 0) throw new IllegalArgumentException("Size must be positive!");
    super.ArrayAlloc(newsize);
    start = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...
  // Realloc
  @Override
  public void Realloc(Natural size) {
    if (size == null) throw new NullPointerException("Size cannot be null!");
    if (arr == null) {
      super.ArrayAlloc(size);
      start = 0L;
      return;
    }
    Data[] oldArr = arr;
    int oldCapacity = oldArr.length;
    long oldStart = start;
    long oldLogicalSize = Size().ToLong();
    super.ArrayAlloc(size);
    int newCapacity = (arr == null) ? 0 : arr.length;
    start = 0L;
    if (oldLogicalSize == 0 || newCapacity == 0) return;
    int copyCount = (int) Math.min(oldLogicalSize, newCapacity);
    for (int i = 0; i < copyCount; i++) {
      int oldIdx = (int) ((oldStart + i) % oldCapacity);
      arr[i] = oldArr[oldIdx]; 
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
  public void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long len = num.ToLong();
    long size = Size().ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (idx < size - (idx + len)){
      long iniwrt = idx - 1 + len;
      long wrt = iniwrt;
      for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
      start = (start + len) % arr.length;
    } else{
      super.ShiftLeft(pos, num);
    }
  }

  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    if (arr == null || arr.length == 0) return;
    for (long i = size - 1; i >= idx; i--) {
      long wrt = i + len;
      SetAt(GetAt(Natural.Of(i)), Natural.Of((start + wrt) % arr.length));
    }
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of((start + i) % arr.length));
    }
  }

}
