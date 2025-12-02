package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data>{ // Must extend OrderedChain and SortedSequence

  // SearchPredecessor
  default Natural SearchPredecessor(Data data) {
    if (IsEmpty()) return null;
    Natural left = Natural.ZERO;
    Natural right = Size().Decrement();
    Natural result = null;
    while (left.compareTo(right) <= 0) {
      Natural mid = Natural.Of(left.ToLong() + (right.ToLong() - left.ToLong()) / 2);
      Data midData = GetAt(mid);
      int cmp = midData.compareTo(data);
      if (cmp < 0) {
        result = mid;
        left = mid.Increment();
      } else {
        if (!mid.IsZero()) {
          right = mid.Decrement();
        } else {
          break; 
        }
      }
    }
    return result;
  }


  // SearchSuccessor
  default Natural SearchSuccessor(Data data) {
    if (IsEmpty()) return null;
    Natural left = Natural.ZERO;
    Natural right = Size().Decrement();
    Natural result = null;
    while (left.compareTo(right) <= 0) {
      Natural mid = Natural.Of(left.ToLong() + (right.ToLong() - left.ToLong()) / 2);
      Data midData = GetAt(mid);
      int cmp = midData.compareTo(data);
      if (cmp > 0) {
        result = mid;
        if (!mid.IsZero()) {
          right = mid.Decrement();
        } else {
          break; 
        }
      } else {
        left = mid.Increment();
      }
    }
    return result;
  }

  
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  // Search
  @Override
  default Natural Search(Data data){
    return SortedSequence.super.Search(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  // Intersection
  default void Intersection(SortedChain<Data> chn) {
    Natural i = Natural.ZERO;
    Natural j = Natural.ZERO;
    while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
      int cmp = GetAt(i).compareTo(chn.GetAt(j));
      if (cmp < 0) {
        RemoveAt(i);
      } else {
        j = j.Increment();
        if (cmp == 0) i = i.Increment();
      }
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...
  // min
  @Override
  default Data Min(){
    if (IsEmpty()) return null;
    return GetAt(Natural.ZERO);
  }

  // RemoveMin
  @Override
  default void RemoveMin(){
    if (!IsEmpty()){
      RemoveAt(Natural.ZERO);
    }
  }

  // MinNRemove
  @Override
  default Data MinNRemove(){
    if (IsEmpty()) return null;
    Data min = GetAt(Natural.ZERO);
    RemoveAt(Natural.ZERO);
    return min;
  }

  // max
  @Override
  default Data Max(){
    if (IsEmpty()) return null;
    return GetAt(Size().Decrement());
  }
  
  // RemoveMax
  @Override
  default void RemoveMax(){
    if (!IsEmpty()){
      RemoveAt(Size().Decrement());
    }
  }

  // MaxNRemove
  @Override
  default Data MaxNRemove(){
    if (IsEmpty()) return null;
    Natural lastIdx = Size().Decrement();
    Data max = GetAt(lastIdx);
    RemoveAt(lastIdx);
    return max;
  }

  //Predecessor
  @Override
  default Data Predecessor(Data data) {
    Natural predIdx = SearchPredecessor(data);
    if (predIdx != null){
      return GetAt(predIdx);
    }
    return null;
  }

  // RemovePredecessor
  @Override
  default void RemovePredecessor(Data data) {
    Natural predIdx = SearchPredecessor(data);
    if (predIdx != null){
      RemoveAt(predIdx);
    }
  }

  // PredecessorNRemove
  @Override
  default Data PredecessorNRemove(Data data) {
    Natural predIdx = SearchPredecessor(data);
    if (predIdx != null){
      Data predData = GetAt(predIdx);
      RemoveAt(predIdx);
      return predData;
    }
    return null;
  }

  // Successor
  @Override
  default Data Successor(Data data) {
    Natural succIdx = SearchSuccessor(data);
    if (succIdx != null){
      return GetAt(succIdx);
    }
    return null;
  }

  // RemoveSuccessor
  @Override
  default void RemoveSuccessor(Data data) {
    Natural succIdx = SearchSuccessor(data);
    if (succIdx != null){
      RemoveAt(succIdx);
    }
  }

  // SuccessorNRemove
  @Override
  default Data SuccessorNRemove(Data data) {
    Natural succIdx = SearchSuccessor(data);
    if (succIdx != null){
      Data succData = GetAt(succIdx);
      RemoveAt(succIdx);
      return succData;
    }
    return null;
  }

}