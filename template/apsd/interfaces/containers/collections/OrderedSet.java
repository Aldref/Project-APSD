package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.iterators.ForwardIterator;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data> { 

  // Min
  default Data Min(){
    if (IsEmpty()) return null;
    ForwardIterator<Data> itr = FIterator();
    if (!itr.IsValid()) return null;
    Data min = itr.GetCurrent();
    itr.Next();
    while(itr.IsValid()){
      Data cur = itr.GetCurrent();
      if (cur != null && min != null){
        if (cur.compareTo(min) < 0){
          min = cur;
        }
      }
      itr.Next();
    }
    return min;
  }

  // RemoveMin
  default void RemoveMin(){
    Data min = Min();
    if (min != null){
      Remove(min);
    }
  }

  // MinNRemove
  default Data MinNRemove(){
    Data min = Min();
    if (min != null){
      Remove(min);
    }
    return min;
  }

  // Max
  default Data Max(){
    if (IsEmpty()) return null;
    ForwardIterator<Data> itr = FIterator();
    if (!itr.IsValid()) return null;
    Data max = itr.GetCurrent();
    itr.Next();
    while(itr.IsValid()){
      Data cur = itr.GetCurrent();
      if (cur != null && max != null){
        if (cur.compareTo(max) > 0){
          max = cur;
        }
      }
      itr.Next();
    }
    return max;
  }

  // RemoveMax
  default void RemoveMax(){
    Data max = Max();
    if (max != null){
      Remove(max);
    }
  }

  // MaxNRemove
  default Data MaxNRemove(){
    Data max = Max();
    if (max != null){
      Remove(max);
    }
    return max;
  }

  // Predecessor
  default Data Predecessor(Data element) {
    if (IsEmpty()) return null;
    ForwardIterator<Data> itr = FIterator();
    if (!itr.IsValid() || element == null) return null;
    Data prev = null;
    while (itr.IsValid()){
      Data cur = itr.GetCurrent();
      if (cur != null && cur.equals(element)){
        return prev;
      }
      prev = cur;
      itr.Next();
    }
    return null;
  }
    

  // RemovePredecessor
  default void RemovePredecessor(Data element) {
    Data pred = Predecessor(element);
    if (pred != null){
      Remove(pred);
    }
  }

  // PredecessorNRemove
  default Data PredecessorNRemove(Data element) {
    Data pred = Predecessor(element);
    if (pred != null){
      Remove(pred);
    }
    return pred;
  }

  // Successor
  default Data Successor(Data element) {
    if (IsEmpty()) return null;
    ForwardIterator<Data> itr = FIterator();
    if (!itr.IsValid() || element == null) return null;
    while (itr.IsValid()){
      Data cur = itr.GetCurrent();
      if (cur != null && cur.equals(element)){
        itr.Next();
        if (itr.IsValid()){
          return itr.GetCurrent();
        } else {
          return null;
        }
      }
      itr.Next();
    }
    return null;
  }

  // RemoveSuccessor
  default void RemoveSuccessor(Data element) {
    Data succ = Successor(element);
    if (succ != null){
      Remove(succ);
    }
  }

  // SuccessorNRemove
  default Data SuccessorNRemove(Data element) {
    Data succ = Successor(element);
    if (succ != null){
      Remove(succ);
    }
    return succ;
  }
}
