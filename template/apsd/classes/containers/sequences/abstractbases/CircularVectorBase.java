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
  public void Realloc(Natural newsize) {
    if (newsize.ToLong() < 0) throw new IllegalArgumentException("Size must be positive!");
    ArrayAlloc(newsize);
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
    long idx = pos.ToLong();
    long len = num.ToLong();
    long size = Size().ToLong();
    if (idx < 0 || idx > size) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size);
    if (len <= 0) return;
    if (idx == size) {
      for (long i = 0; i < len; i++) {
        SetAt(null, Natural.Of(size + i));
      }
      start = (start - len + arr.length) % arr.length;
      return;
    }
    if (size - idx >= idx){
      long inirdr = size - 1;
      long rdr = inirdr;
      for (long wrt = rdr + len; rdr >= idx; rdr--, wrt--) {
        if (wrt < Capacity().ToLong()) {
          Natural natrdr = Natural.Of(rdr);
          SetAt(GetAt(natrdr), Natural.Of(wrt));
          SetAt(null, natrdr);
        }
      }
      for (long wrt = idx; wrt < idx + len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
      start = (start - len + arr.length) % arr.length;
    } else{
      super.ShiftRight(pos, num);
    }
  }


}
