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
  @SuppressWarnings("unchecked")
  @Override
  public void Realloc(Natural newsize) {
    if (newsize == null) return;
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
    if (num == null) return;
    long len = num.ToLong();
    if (len <= 0) return;
    long newSize = size + len;
    if (newSize > Capacity().ToLong()) {
      Realloc(Natural.Of(newSize));
      if (newSize > Capacity().ToLong()) throw new IllegalStateException("Unable to expand: capacity is still < new size");
    }
    size = newSize;
  }

  // Reduce
  @Override
  public void Reduce(Natural num) {
    if (num == null) return;
    long sub = num.ToLong();
    if (sub <= 0) return;
    if (sub > size) throw new IllegalArgumentException("Cannot reduce more elements than current size!");
    long oldSize = size;
    long newSize = size - sub;
    if (arr != null && arr.length > 0) {
      for (long i = newSize; i < oldSize; i++) {
        int realIdx = (int) ((start + i) % arr.length);
        arr[realIdx] = null;
      }
    }
    size = newSize;
    if (size == 0) start = 0L;
    Shrink();
  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ...
  // ShiftLeft
  @Override
  public void ShiftLeft(Natural pos, Natural num){
    if (pos == null || num == null) throw new NullPointerException();
    long idx = pos.ToLong();
    long len = num.ToLong();
    long sz = size;
    if (len <= 0) return;
    if (idx < 0 || idx >= sz) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + sz);
    if (len > sz - idx) len = sz - idx;
    if (len == 0) return;
    long start = idx + len;
    long count = sz - start;
    for (long i = 0; i < count; i++) {
      SetAt(GetAt(Natural.Of(start + i)), Natural.Of(idx + i));
    }
    for (long i = sz - len; i < sz; i++) {
      SetAt(null, Natural.Of(i));
    }
    Reduce(Natural.Of(len));
  }

  // ShiftRight
  @Override
  public void ShiftRight(Natural pos, Natural num) {
    if (pos == null || num == null) throw new NullPointerException();
    long len = num.ToLong();
    if (len <= 0) return;
    long sz = size;
    long idx = pos.ToLong();
    if (idx < 0 || idx > sz) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + sz);
    if (size + len > Capacity().ToLong()) {
      Expand(Natural.Of(len)); 
    } else {
      size += len; 
    }
    long oldSize = sz;
    for (long i = oldSize - 1; i >= idx; i--) {
      SetAt(GetAt(Natural.Of(i)), Natural.Of(i + len));
    }
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of(i));
    }
  }

}