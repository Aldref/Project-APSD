package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must extend CircularVectorBase and implement DynVector

  protected long size = 0L;

  // DynCircularVectorBase
  protected DynCircularVectorBase(TraversableContainer<Data> con) {
    super();
    if (con != null) {
      con.TraverseForward(d -> {
        this.InsertLast(d); 
        return false;
      });
    }
  }
  // ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural newsize) {
    super.ArrayAlloc(newsize);
    size = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return Natural.Of(size);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear(){
    super.Clear();
    size = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...
  // Realloc
  @Override
  public void Realloc(Natural newsize) {
    if (newsize == null) throw new IllegalArgumentException("New size cannot be null!");
    long sizeLong = newsize.ToLong();
    if (sizeLong >= Integer.MAX_VALUE) throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); 
    Data[] oldArr = arr;
    ArrayAlloc(newsize);
    if (oldArr != null){
      int copyLen = (oldArr.length < arr.length) ? oldArr.length : arr.length; 
      for (int i = 0; i < copyLen; i++) {
        arr[i] = oldArr[i];
      }
    }
    if (size > sizeLong) size = sizeLong;

  }

  /* ****** ****************************************************************** */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
  // Expand
  @Override
  public void Expand(Natural num) {
    Natural newSize = Natural.Of(size + num.ToLong());
    if (newSize.ToLong() > Capacity().ToLong()) {
      Grow(Natural.Of(newSize.ToLong() - Capacity().ToLong()));
    }
    size = newSize.ToLong();
  }

  // Reduce
  @Override
  public void Reduce(Natural num) {
    if (num == null) throw new IllegalArgumentException("Number to reduce cannot be null!");
    if (num.ToLong() > size) {
      throw new IllegalArgumentException("Cannot reduce more elements than current size!");
    }
    size -= num.ToLong();
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
    if (len < 0) return;
    len = (len <= size - idx) ? len : size - idx;
    if (idx < size - (idx + len)) {
      long iniwrt = idx - 1 + len;
      long wrt = iniwrt;
      for (long rdr = wrt - len; rdr >= 0; rdr--, wrt--){
          Natural natrdr = Natural.Of(rdr);
          SetAt(GetAt(natrdr), Natural.Of(wrt));
          SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++)
          SetAt(null, Natural.Of(wrt));
      start = (start + len) % arr.length;
    }
    else {
      long neededMaxIndex = (size - 1) + len;
      while (neededMaxIndex >= Capacity().ToLong()) {
          Grow();
      }
      super.ShiftLeft(pos, num);
    }
  }

  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    if (len < 0) return;
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
      for (; wrt > iniwrt; wrt--)
          SetAt(null, Natural.Of(wrt));
      start = (start - len + arr.length) % arr.length;
    }
    else {
      long neededMaxIndex = (size - 1) + len;
      while (neededMaxIndex >= Capacity().ToLong()) {
          Grow();
      }
      super.ShiftRight(pos, num);
    }
  }

}