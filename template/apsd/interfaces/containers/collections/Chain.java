package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data>{ // Must extend RemovableAtSequence

  // InsertIfAbsent
  default boolean InsertIfAbsent(Data data){
    if (IsEmpty()) Insert(data);
    else{
      if (!Contains(data)){
        Insert(data);
        return true;
      }
    }
    return false;
  }

  // RemoveOccurrences
  default void RemoveOccurrences(Data data){
    while (Contains(data)){
      Remove(data);
    }
  }

  // SubChain
  Chain<Data> SubChain(Natural start, Natural end);
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @override
  default Natural Search(Data data){
    return sequence.super.Search(data);
  }

}
