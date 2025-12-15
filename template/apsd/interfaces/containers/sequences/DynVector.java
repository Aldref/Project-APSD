package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  @Override
  default void InsertAt(Data data, Natural pos){
    if (data == null) throw new IllegalArgumentException("data nullo");
    if (pos == null) throw new IndexOutOfBoundsException("Index out of bounds.");
    long idx = pos.ToLong();
    long size = Size().ToLong();
    if (idx < 0 || idx > size) throw new IndexOutOfBoundsException("Index out of bounds.");
    if (idx == size) {
      Expand();
      SetAt(data, pos);
      return;
    }
    if (size + 1 > Capacity().ToLong()) Grow();
    ShiftRight(pos, Natural.ONE);
    SetAt(data, pos);
  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  @Override
  default Data AtNRemove(Natural pos) {
    if (pos == null) throw new IndexOutOfBoundsException("Index out of bounds.");
    long idx = pos.ToLong();
    long size = Size().ToLong();
    if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException("Index out of bounds.");
    Data old = GetAt(Natural.Of(idx));
    if (idx < size - 1) {
      Vector.super.ShiftLeft(Natural.Of(idx), Natural.ONE);
    }
    SetAt(null, Natural.Of(size - 1));
    Reduce(Natural.ONE);
    return old;
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
    long oldSize = Size().ToLong();
    if (idx < 0 || idx > oldSize) throw new IndexOutOfBoundsException("Index out of bounds for shift right: " + idx + "; Size: " + oldSize + "!");
    long needed = oldSize + len;
    if (needed > Capacity().ToLong()) {
      Realloc(Natural.Of(needed));
      if (needed > Capacity().ToLong()) throw new IllegalStateException("Unable to grow capacity to accommodate shiftRight");
    }
    Expand(Natural.Of(len));
    for (long i = oldSize - 1; i >= idx; i--) {
      SetAt(GetAt(Natural.Of(i)), Natural.Of(i + len));
    }
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
