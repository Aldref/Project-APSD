package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data>{ // Must extend MutableSequence, InsertableAtSequence, and Chain

  // SubList
  default List<Data> SubList(Natural start, Natural end){
    long startIdx = ExcIfOutOfBound(start);
    long endIdx = ExcIfOutOfBound(end);
    if (startIdx > endIdx || endIdx >= Size().ToLong()) return null;
    return (List<Data>) SubSequence(start, end);
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default boolean Insert(Data data) {
    InsertFirst(data);
    return true;
  }
  
}