package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> { // Must extend LinearVectorBase and implement DynVector

  protected long size = 0L;

  // DynLinearVectorBase
  protected DynLinearVectorBase(TraversableContainer<Data> con) {
    super(con);
    if (con != null) {
      Natural conSize = con.Size();
      size = conSize != null ? conSize.ToLong() : 0L;
      if (arr != null && size > arr.length) size = arr.length;
    }
  }

  // ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural newcapacity) {
    super.ArrayAlloc(newcapacity);
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
  public void Clear() {
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
    if (newsize == null) return;
    long newLen = newsize.ToLong();
    if (newLen >= Integer.MAX_VALUE) throw new ArithmeticException("Overflow");
    Data[] oldArr = arr;
    ArrayAlloc(newsize);
    if (oldArr != null) {
      long limit = Math.min(size, newLen);
      for (int i = 0; i < limit; i++) {
        arr[i] = oldArr[i];
      }
    }
    if (size > newLen) size = newLen;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
  // Expand
  @Override
  public void Expand(Natural num) {
    if (num == null) return;
    long add = num.ToLong();
    if (add <= 0) return;
    long newSize = size + add;
    if (newSize > Capacity().ToLong()) {
      Realloc(Natural.Of(newSize));
    }
    size = newSize;
  }

  @Override
  public void Reduce(Natural num) {
    if (num == null) return;
    long sub = num.ToLong();
    if (sub <= 0) return;
    if (sub > size) throw new IllegalArgumentException("Cannot reduce more than current size");
    long oldSize = size;
    long newSize = size - sub;
    for (long i = newSize; i < oldSize && i < arr.length; i++) {
      arr[(int) i] = null;
    }
    size = newSize;
    Shrink();
  }
}