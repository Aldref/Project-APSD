package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> {

  // public LLList()
  public LLList() {
    super((TraversableContainer<Data>) null);
  }

  public LLList(TraversableContainer<Data> con) {
    super(con);
  }

  // protected LLList(long size, LLNode<Data> head, LLNode<Data> tail)
  protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }

  // NewChain
  protected LLList<Data> NewChain( long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLList<Data>(size, head, tail);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  // ...
  // FIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new ListFIterator();
  }

  // BIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new ListBIterator();
  }
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data data) {
    if (data == null) return false;
    InsertFirst(data);
    return true;
  }

  // ...
  // SetAt
  @Override
  public void SetAt(Data data, Natural index) {
    if (data == null) return;
    List.super.SetAt(data, index);
  }

  // SetFirst
  @Override
  public void SetFirst(Data data) {
    if (data == null) return;
    if (headref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    headref.Get().Set(data);
  }

  // SetLast
  @Override
  public void SetLast(Data data) {
    if (data == null) return;
    if (tailref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    tailref.Get().Set(data);
  }

  // SubSequence
  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    return (MutableSequence<Data>) super.SubSequence(from, to);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  // InsertAt
  @Override
  public void InsertAt(Data data, Natural index) {
    if (index == null) throw new NullPointerException();
    if (data == null) return;
    long idx = index.ToLong();
    long sz  = size.ToLong();
    if (idx < 0 || idx > sz) throw new IndexOutOfBoundsException("Index: " + idx);
    if (idx == 0) {
      InsertFirst(data);
      return;
    }
    if (idx == sz) {
      InsertLast(data);
      return;
    }
    LLNode<Data> prev = headref.Get();
    for (long i = 0; i < idx - 1; i++) {
      prev = prev.GetNext().Get();
    }
    LLNode<Data> newNode = new LLNode<>(data);
    newNode.SetNext(prev.GetNext().Get());
    prev.SetNext(newNode);
    size.Increment();
  }

  // InsertFirst
  @Override
  public void InsertFirst(Data data) {
    if (data == null) return;
    LLNode<Data> newNode = new LLNode<Data>(data);
    newNode.SetNext(headref.Get());
    headref.Set(newNode);
    if (tailref.Get() == null) {
      tailref.Set(newNode);
    }
    size.Increment();
  }

  // InsertLast
  @Override
  public void InsertLast(Data data) {
    if (data == null) return;
    LLNode<Data> newNode = new LLNode<Data>(data);
    if (tailref.Get() == null) {
      headref.Set(newNode);
      tailref.Set(newNode);
    } else {
      tailref.Get().SetNext(newNode);
      tailref.Set(newNode);
    }
    size.Increment();
  }
  
}