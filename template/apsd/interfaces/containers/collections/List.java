package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.iterators.ForwardIterator;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data>{ // Must extend MutableSequence, InsertableAtSequence, and Chain

  // SubList
  List<Data> SubList(Natural start, Natural end);
  
  /* ************************************************************************ */
  /* Override specific member functions from ExtensibleContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default boolean Insert(Data data) {
    if (data == null) return false;
    InsertLast(data);
    return true;
  }
}