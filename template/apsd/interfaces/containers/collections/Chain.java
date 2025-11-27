package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;
public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{ // Must extend RemovableAtSequence

  // InsertIfAbsent
  default boolean InsertIfAbsent(Data data) {
    if (Search(data) == null) {
      return Insert(data);
    }
    return false;
  }

  // RemoveOccurrences
  default void RemoveOccurrences(Data data){
    while (TraverseForward(dat -> {
      if (dat == null ? data == null : dat.equals(data)) {
        Remove(data);
        return true;
      }
      return false;
    }));
  }

  // SubChain
  default Chain<Data> SubChain(Natural start, Natural end){
    long startIdx = ExcIfOutOfBound(start);
    long endIdx = ExcIfOutOfBound(end);
    if (startIdx > endIdx || endIdx >= Size().ToLong()) return null;
    return (Chain<Data>) SubSequence(start, end);
  }
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @Override
  default Natural Search(Data data){
    return RemovableAtSequence.super.Search(data);
  }

}
