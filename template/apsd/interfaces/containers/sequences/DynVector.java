package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer<Data>, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  @Override
default void InsertAt(Data element, Natural idx) {
  long index = ExcIfOutOfBound(idx);
  long size = Size().ToLong();
  if (Size().Equals(Capacity())) Grow();
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
  

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  @Override
  abstract Natural Size();
}
