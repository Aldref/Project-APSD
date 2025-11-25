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
    long newCap = newsize.ToLong();
    if (newCap >= Integer.MAX_VALUE) throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
    Data[] oldArr = arr;
    long oldSize = Size().ToLong();    
    long oldStart = start;
    ArrayAlloc(newsize);   
    if (oldArr != null && oldSize > 0) {
      for (long i = 0; i < oldSize && i < newCap; i++) {
        int oldIdx = (int) ((oldStart + i) % oldArr.length);
        arr[(int) i] = oldArr[oldIdx];
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
  public void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long len = num.ToLong();
    if (len <= 0) return; 
    long size = Size().ToLong();
    if (len > size - idx) {
      len = size - idx;
    }
    for (long i = idx; i < size - len; i++) {
      SetAt(GetAt(Natural.Of(i + len)), Natural.Of(i));
    }
    for (long i = size - len; i < size; i++) {
      SetAt(null, Natural.Of(i));
    }
    size -= len;
  }


  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num) {
    long idx = pos.ToLong();
    long len = num.ToLong();
    long size = Size().ToLong();
    if (idx < 0 || idx > size) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size);
    if (len <= 0) return; 
    while (size + len > arr.length) {
        Grow(); 
        size = Size().ToLong(); 
    }
    for (long i = size - 1; i >= idx; i--) {
      SetAt(GetAt(Natural.Of(i)), Natural.Of(i + len));
    }
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of(i));
    }
    size += len;
  }


}
