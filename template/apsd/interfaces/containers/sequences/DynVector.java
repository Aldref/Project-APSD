package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  @Override
  default void InsertAt(Data element, Natural idx) {
    if (element == null) throw new NullPointerException("Element cannot be null");
    if (idx == null) throw new NullPointerException("Index cannot be null");
    long index = idx.ToLong();
    long size  = Size().ToLong();
    long cap   = Capacity().ToLong();
    if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds for insert: " + index + "; Size: " + size + "!");
    if (size + 1 > cap) Grow();
    ShiftRight(Natural.Of(index), Natural.ONE);
    SetAt(element, Natural.Of(index));
  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  @Override
  default Data AtNRemove(Natural idx) {
    long index = ExcIfOutOfBound(idx);
    Data removedElement = GetAt(Natural.Of(index));
    long size = Size().ToLong();
    if (index < size - 1) {
      ShiftLeft(idx, Natural.ONE); 
    }
    SetAt(null, Natural.Of(size - 1));
    Reduce();
    return removedElement;
  }


  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  // ...
  @Override
  default void ShiftLeft(Natural pos, Natural num) {
    if (pos == null || num == null) throw new NullPointerException();
    long idx = pos.ToLong();
    long len = num.ToLong();
    if (len <= 0) return;
    long size = Size().ToLong();
    if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("Index out of bounds for shift left: " + idx + "; Size: " + size + "!");
    if (idx + len > size) len = size - idx;
    if (len <= 0) return;
    Vector.super.ShiftLeft(Natural.Of(idx), Natural.Of(len));
    for (long i = size - len; i < size; i++) {
      SetAt(null, Natural.Of(i));
    }
    Reduce(Natural.Of(len));
  }

  @Override
  default void ShiftRight(Natural pos, Natural num) {
    if (pos == null || num == null) throw new NullPointerException();
    long idx = pos.ToLong();
    long len = num.ToLong();
    if (len <= 0) return;
    long size = Size().ToLong();
    long cap  = Capacity().ToLong();
    if (idx < 0 || idx > size) throw new IndexOutOfBoundsException("Index out of bounds for shift right: " + idx + "; Size: " + size + "!");
    if (size + len > cap) {
      Realloc(Natural.Of(size + len)); 
      if (size + len > Capacity().ToLong()) throw new IllegalStateException("Unable to grow capacity to accommodate shiftRight");
    }
    Expand(Natural.Of(len)); 
    Vector.super.ShiftRight(pos, num);
    for (long i = idx; i < idx + len; i++) {
      SetAt(null, Natural.Of(i));
    }
  }


  @Override
  default Vector<Data> SubVector(Natural start, Natural end){
    return (DynVector<Data>) SubSequence(start, end);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  @Override
  abstract Natural Size();
}
