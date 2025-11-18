package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data> {

  // public LLSortedChain()
  public LLSortedChain() {
    super((TraversableContainer<Data>) null);
  }
  // public LLSortedChain(LLSortedChain<Data> chn)
  public LLSortedChain(LLSortedChain<Data> chn) {
    super(chn);
  }
  // public LLSortedChain(TraversableContainer<Data> con)
  public LLSortedChain(TraversableContainer<Data> con) {
    super(con);
  }
  // protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail)
  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }
  // NewChain
  @Override
  protected LLSortedChain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLSortedChain<Data>(size, head, tail);
  }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  // ...
  // PredFind
  protected LLNode<Data> PredFind(Data data) {
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    if (head == null) return null;
    LLNode<Data> curr = head;
    while (true) {
      Box<LLNode<Data>> nextBox = curr.GetNext();
      if (nextBox == null) break;
      LLNode<Data> next = nextBox.Get();
      if (next == null || next == tail) break;
      @SuppressWarnings("unchecked")
      Comparable<Data> cmp = (Comparable<Data>) next.Get();
      if (cmp.compareTo(data) >= 0) break;
      curr = next;
    }
    return curr;
  }

  // PredPredFind
  protected LLNode<Data> PredPredFind(Data data) {
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    if (head == null) return null;
    LLNode<Data> predPred = null;
    LLNode<Data> curr = head;
    while (true) {
      Box<LLNode<Data>> nextBox = curr.GetNext();
      if (nextBox == null) break;
      LLNode<Data> next = nextBox.Get();
      if (next == null || next == tail) break;
      @SuppressWarnings("unchecked")
      Comparable<Data> cmp = (Comparable<Data>) next.Get();
      if (cmp.compareTo(data) >= 0) break;
      predPred = curr;
      curr = next;
    }
    return predPred;
  }

  // SuccFind
  protected LLNode<Data> SuccFind(Data data) {
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    if (head == null) return null;
    Box<LLNode<Data>> nb = head.GetNext();
    LLNode<Data> curr = (nb != null) ? nb.Get() : null;
    while (curr != null && curr != tail) {
      @SuppressWarnings("unchecked")
      Comparable<Data> cmp = (Comparable<Data>) curr.Get();
      if (cmp.compareTo(data) > 0) {
        return curr;
      }
      Box<LLNode<Data>> nextBox = curr.GetNext();
      curr = (nextBox != null) ? nextBox.Get() : null;
    }
    return null;
  }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  // Insert
  // @Override
  // public boolean Insert(Data data) {
  //   Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
  //   LLNode<Data> currNode = head.next;
  //   while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
  //     prevNodeBox.value = currNode;
  //     currNode = currNode.next;
  //   }
  //   LLNode<Data> newNode = new LLNode<Data>(data, currNode);
  //   prevNodeBox.value.next = newNode;
  //   size++;
  //   return true;
  // }

  @Override
  public boolean Insert(Data data) {
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    LLNode<Data> newNode = new LLNode<>(data);
    if (head == null) {
      headref.Set(newNode);
      tailref.Set(newNode);
      size.Increment();
      return true;
    }
    @SuppressWarnings("unchecked")
    Comparable<Data> firstCmp = (Comparable<Data>) head.Get();
    if (firstCmp.compareTo(data) >= 0) {
      newNode.SetNext(head);
      headref.Set(newNode);
      size.Increment();
      return true;
    }
    LLNode<Data> pred = PredFind(data);      
    Box<LLNode<Data>> nextBox = pred.GetNext();
    LLNode<Data> next = (nextBox != null) ? nextBox.Get() : null;
    newNode.SetNext(next);
    pred.SetNext(newNode);
    if (pred == tail) {   
      tailref.Set(newNode);
    }
    size.Increment();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // ...
  // Remove
  @Override
  public boolean Remove(Data data) {
    return super.Remove(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

  // ...
  // Search
  @Override
  public Natural Search(Data data) {
    Natural index = Natural.ZERO;
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data cur = it.GetCurrent();
      int cmp = cur.compareTo(data);
      if (cmp == 0) return index;
      if (cmp > 0) break; 
      index.Increment();
      it.DataNNext();
    }
    return null;
  }
  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...
  // all Predecessor methods
  @Override
  public Natural SearchPredecessor(Data data) {
    Natural index = Natural.ZERO;
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data cur = it.GetCurrent();
      if (cur.compareTo(data) >= 0) break;
      index.Increment();
      it.DataNNext();
    }
    return index;
  }

  // @Override
  // public Data SearchPredecessor(Data data) {
  //   LLNode<Data> currNode = head.next;
  //   LLNode<Data> predecessorNode = null;
  //   while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
  //     predecessorNode = currNode;
  //     currNode = currNode.next;
  //   }
  //   return (predecessorNode != null) ? predecessorNode.data : null;
  // }

  @Override
  public void RemovePredecessor(Data data) {
    Natural idx = SearchPredecessor(data);
    long pos = idx.ToLong();
    if (pos == 0) return; 
    Natural predIdx = Natural.Of(pos - 1);
    AtNRemove(predIdx);
  }

  @Override
  public Data PredecessorNRemove(Data data) {
    Natural idx = SearchPredecessor(data);
    long pos = idx.ToLong();
    if (pos == 0) return null;
    Natural predIdx = Natural.Of(pos - 1);
    Data pred = GetAt(predIdx); 
    AtNRemove(predIdx);         
    return pred;
  }

  // All Successor methods
  @Override
  public Natural SearchSuccessor(Data data) {
    Natural index = Natural.ZERO;
    ForwardIterator<Data> it = FIterator();

    while (it.IsValid()) {
      Data cur = it.GetCurrent();
      if (cur.compareTo(data) > 0) break;
      index.Increment();
      it.DataNNext();
    }
    return index;
  }
  // @Override
  // public Data SearchSuccessor(Data data) {
  //   LLNode<Data> currNode = head.next;
  //   while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) <= 0) {
  //     currNode = currNode.next;
  //   }
  //   return (currNode != tail) ? currNode.data : null;
  // }

  @Override
  public void RemoveSuccessor(Data data) {
    Natural idx = SearchSuccessor(data);
    if (idx.ToLong() >= Size().ToLong()) return;
    AtNRemove(idx);
  }

  @Override
  public Data SuccessorNRemove(Data data) {
    Natural idx = SearchSuccessor(data);
    if (idx.ToLong() >= Size().ToLong()) return null;

    Data succ = GetAt(idx);
    AtNRemove(idx);
    return succ;
  }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // ...
  // InsertIfAbsent
  @Override
  public boolean InsertIfAbsent(Data data){
    if (Contains(data)) {
      return false;
    }
    Insert(data);
    return true;
  }

  // RemoveOccurences
  @Override
  public void RemoveOccurences(Data data){
    while (Contains(data)) {
      Remove(data);
    }
  }

}
