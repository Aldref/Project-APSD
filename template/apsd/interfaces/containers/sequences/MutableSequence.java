package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {

  // SetAt
  default void SetAt(Data element, Natural num){
    long idx = ExcIfOutOfBound(num);
    MutableForwardIterator<Data> it = MutableForwardIterator();
    it.Next(Natural.Of(idx));          
    it.SetCurrent(element);                
  }

  // GetNSetAt
  default Data GetNSetAt(Data element, Natural num){
    long idx = ExcIfOutOfBound(num);
    MutableForwardIterator<Data> it = MutableForwardIterator();
    it.Next(Natural.Of(idx));          
    Data oldData = it.GetCurrent();  
    it.SetCurrent(element);                
    return oldData;
  }

  // SetFirst
  default void SetFirst(Data element) {
    SetAt(Natural.ZERO, element);
  }

  // GetNSetFirst
  default Data GetNSetFirst(Data element) {
    return GetNSetAt(Natural.ZERO, element);
  }

  // SetLast
  default void SetLast(Data element) {
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    SetAt(Natural.Of(size - 1), element);
  }

  // GetNSetLast
  default Data GetNSetLast(Data element) {
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    return GetNSetAt(Natural.Of(size - 1), element);
  }

  // Swap
  default void Swap(Natural num1, Natural num2) {
    long idx1 = ExcIfOutOfBound(num1);
    long idx2 = ExcIfOutOfBound(num2);
    if (idx1 == idx2) return; 

    MutableForwardIterator<Data> it1 = MutableForwardIterator();
    MutableForwardIterator<Data> it2 = MutableForwardIterator();
    it1.Next(Natural.Of(idx1));
    Data data1 = it1.GetCurrent();

    it2.Next(Natural.Of(idx2));
    Data data2 = it2.GetCurrent();

    it1.SetCurrent(data2);
    it2.SetCurrent(data1);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @Override
  MutableSequence<Data> SubSequence(Natural start, Natural end);
}
