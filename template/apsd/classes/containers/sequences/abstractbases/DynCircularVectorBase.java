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
    long oldSize = size;
    arr = (Data[]) new Object[(int) newCap];
    if (oldArr != null && oldSize > 0) {
      long copyLen = Math.min(oldSize, newCap);
      for (int i = 0; i < copyLen; i++) {
        arr[i] = oldArr[(int) ((start + i) % oldArr.length)];
      }
      size = copyLen;
    } else {
        size = 0;
    }
    start = 0;
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
    long len = num.ToLong();
    long sizeLong = size;
    if (len <= 0) return;
    if (idx < 0 || idx >= sizeLong) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + sizeLong);
    if (len > sizeLong - idx) {
      len = sizeLong - idx;
    }
    for (long i = idx; i < sizeLong - len; i++) {
      SetAt(GetAt(Natural.Of(i + len)), Natural.Of(i));
    }
    for (long i = sizeLong - len; i < sizeLong; i++) {
      SetAt(null, Natural.Of(i));
    }
    size -= len;
  }

  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num) {
    if (pos == null) throw new NullPointerException("Position cannot be null!");
    long idx = pos.ToLong();
    long len = num.ToLong();
    long sizeLong = size;
    if (idx < 0 || idx > sizeLong) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + sizeLong);
    if (len <= 0) return;
    if (sizeLong + len > arr.length) {
      Expand(Natural.Of(len));
      sizeLong = size;
    }
    for (long i = sizeLong - 1; i >= idx; i--) {
      SetAt(GetAt(Natural.Of(i)), Natural.Of(i + len));
    }
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of(i));
    }
    size += len;
  }


}