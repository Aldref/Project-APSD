package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data> extends LLChainBase<Data> implements SortedChain<Data> {

  // public LLSortedChain()
  public LLSortedChain() {
    super();
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
    LLNode<Data> currNode = head;
    while (currNode.next != tail && ((Comparable<Data>) currNode.next.data).compareTo(data) < 0) {
      currNode = currNode.next;
    }
    return currNode;
  }

  // PredPredFind
  protected LLNode<Data> PredPredFind(Data data) {
    LLNode<Data> currNode = head;
    LLNode<Data> predNode = head;
    while (currNode.next != tail && ((Comparable<Data>) currNode.next.data).compareTo(data) < 0) {
      predNode = currNode;
      currNode = currNode.next;
    }
    return predNode;
  }

  // SuccFind
  protected LLNode<Data> SuccFind(Data data) {
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) <= 0) {
      currNode = currNode.next;
    }
    return currNode;
  }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  // Insert
  @Override
  public boolean Insert(Data data) {
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    LLNode<Data> newNode = new LLNode<Data>(data, currNode);
    prevNodeBox.value.next = newNode;
    size++;
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // ...
  // Remove
  @Override
  public boolean Remove(Data data) {
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    if (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) == 0) {
      prevNodeBox.value.next = currNode.next;
      size--;
      return true;
    }
    return false;
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
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      index.Increment();
      currNode = currNode.next;
    }
    if (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) == 0) {
      return index;
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
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      index.Increment();
      currNode = currNode.next;
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
  public void RemovePredecessor(Data data){
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    if (prevNodeBox.value != head) {
      Box<LLNode<Data>> prePrevNodeBox = new Box<LLNode<Data>>(head);
      LLNode<Data> preCurrNode = head.next;
      while (preCurrNode != prevNodeBox.value) {
        prePrevNodeBox.value = preCurrNode;
        preCurrNode = preCurrNode.next;
      }
      prePrevNodeBox.value.next = prevNodeBox.value.next;
      size--;
    }
  }

  @Override
  public Data PredecessorNRemove(Data data){
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) < 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    if (prevNodeBox.value != head) {
      Box<LLNode<Data>> prePrevNodeBox = new Box<LLNode<Data>>(head);
      LLNode<Data> preCurrNode = head.next;
      while (preCurrNode != prevNodeBox.value) {
        prePrevNodeBox.value = preCurrNode;
        preCurrNode = preCurrNode.next;
      }
      Data predData = prevNodeBox.value.data;
      prePrevNodeBox.value.next = prevNodeBox.value.next;
      size--;
      return predData;
    }
    return null;
  }

  // All Successor methods
  @Override
  public Natural SearchSuccessor(Data data) {
    Natural index = Natural.ZERO;
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) <= 0) {
      index.Increment();
      currNode = currNode.next;
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
  public void RemoveSuccessor(Data data){
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) <= 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    if (currNode != tail) {
      prevNodeBox.value.next = currNode.next;
      size--;
    }
  }

  @Override
  public Data SuccessorNRemove(Data data){
    Box<LLNode<Data>> prevNodeBox = new Box<LLNode<Data>>(head);
    LLNode<Data> currNode = head.next;
    while (currNode != tail && ((Comparable<Data>) currNode.data).compareTo(data) <= 0) {
      prevNodeBox.value = currNode;
      currNode = currNode.next;
    }
    if (currNode != tail) {
      Data succData = currNode.data;
      prevNodeBox.value.next = currNode.next;
      size--;
      return succData;
    }
    return null;
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
