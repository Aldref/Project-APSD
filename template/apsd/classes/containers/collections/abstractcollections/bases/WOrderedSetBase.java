package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>> extends WSetBase<Data, Chn> implements OrderedSet<Data> { // Must extend WSetBase and implement OrderedSet; Chn must extend SortedChain

  // WOrderedSetBase
  public WOrderedSetBase() {
    super();
  }

  public WOrderedSetBase(TraversableContainer<Data> con) {
    super(con);
  }

  public WOrderedSetBase(Chn chn) {
    super(chn);
  }

  public WOrderedSetBase(Chn chn, TraversableContainer<Data> con) {
    super(chn, con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  //IsEqual
  @Override
  public boolean IsEqual(IterableContainer<Data> con) {
    return chn.IsEqual(con);
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...
  // Min
  @Override
  public Data Min() {
    return chn.Min();
  }

  //RemoveMin
  @Override
  public void RemoveMin() {
    chn.RemoveMin();
  }

  // MinNRmove
  @Override
  public Data MinNRemove() {
    return chn.MinNRemove();
  }

  // Max
  @Override
  public Data Max() {
    return chn.Max();
  }

  // RemoveMax
  @Override
  public void RemoveMax() {
    chn.RemoveMax();
  }

  // MaxNRmove
  @Override
  public Data MaxNRemove() {
    return chn.MaxNRemove();
  }

  // Predecessor
  @Override
  public Data Predecessor(Data data) {
    return chn.Predecessor(data);
  }

  //RemovePredecessor
  @Override
  public void RemovePredecessor(Data data) {
    chn.RemovePredecessor(data);
  }

  //PrecedessorNRemove
  @Override
  public Data PredecessorNRemove(Data data) {
    return chn.PredecessorNRemove(data);
  }

  // Successor
  @Override
  public Data Successor(Data data) {
    return chn.Successor(data);
  }

  // RemoveSuccessor
  @Override
  public void RemoveSuccessor(Data data) {
    chn.RemoveSuccessor(data);
  }

  // SuccessorNRemove
  @Override
  public Data SuccessorNRemove(Data data) {
    return chn.SuccessorNRemove(data);
  } 
}