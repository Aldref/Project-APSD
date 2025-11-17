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
  long index = ExcIfOutOfBound(idx);
  long size = Size().ToLong();
  if (Size().equals(Capacity())) Grow();
  if (index == size) {
    SetAt(element, Natural.Of(size));
  } else {
    ShiftRight(Natural.Of(index), Natural.ONE);
    SetAt(element, Natural.Of(index));
  }
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
    if (index >= size) throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    if (index == size - 1) {
      SetAt(null, Natural.Of(size - 1));
      Reduce();
      return removedElement;
    }
    ShiftLeft(Natural.Of(index), Natural.ONE);
    SetAt(null, Natural.Of(size - 1));
    Reduce();
    return removedElement;
  }


  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  // ...
  @Override
  default void ShiftLeft(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    //forse shrink qui
    if (len > 0) {
      long iniwrt = idx;
      long wrt = iniwrt;
      for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
      Reduce();
    }
  }

  @Override
  default void ShiftRight(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    if (len > size - idx) {
      Grow(Natural.Of(len));
      size = Size().ToLong();
    }
    if (len > 0) {
      Long inirdr = size - 1;
      Long rdr = inirdr;
      for (Long wrt = rdr + len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);

      }
    }
  }

  @Override
  DynVector<Data> SubVector(Natural start, Natural end);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  @Override
  abstract Natural Size();
}
