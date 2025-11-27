package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

  // SetAt
  default void SetAt(Data data, Natural num){
    long idx = ExcIfOutOfBound(num);
    MutableForwardIterator<Data> it = FIterator();
    it.Next(Natural.Of(idx));          
    it.SetCurrent(data);                
  }

  // GetNSetAt
  default Data GetNSetAt(Data data, Natural num){
    long idx = ExcIfOutOfBound(num);
    MutableForwardIterator<Data> it = FIterator();
    it.Next(Natural.Of(idx));          
    Data oldData = it.GetCurrent();  
    it.SetCurrent(data);                
    return oldData;
  }

  // SetFirst
  default void SetFirst(Data data) {
    if (Size().ToLong() == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    SetAt(data, Natural.ZERO);
  }

  // GetNSetFirst
  default Data GetNSetFirst(Data data) {
    return GetNSetAt(data, Natural.ZERO);
  }

  // SetLast
  default void SetLast(Data data) {
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    SetAt(data, Natural.Of(size - 1));
  }

  // GetNSetLast
  default Data GetNSetLast(Data data) {
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    return GetNSetAt(data, Natural.Of(size - 1));
  }

  // Swap
  default void Swap(Natural num1, Natural num2) {
    long idx1 = ExcIfOutOfBound(num1);
    long idx2 = ExcIfOutOfBound(num2);
    if (idx1 == idx2) return; 
    Data data1 = GetAt(Natural.Of(idx1));
    Data data2 = GetAt(Natural.Of(idx2));
    SetAt(data2, Natural.Of(idx1));
    SetAt(data1, Natural.Of(idx2));
  }


  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @Override
  MutableSequence<Data> SubSequence(Natural start, Natural end);

}