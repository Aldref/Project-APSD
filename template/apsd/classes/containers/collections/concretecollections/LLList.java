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
  protected class ListFIter implements MutableForwardIterator<Data> {

    private Box<LLNode<Data>> curRef;   

    public ListFIter() {
      curRef = headref;                
    }

    @Override
    public boolean IsValid() {
      return curRef != null && curRef.Get() != null;
    }

    @Override
    public void Reset() {
      curRef = headref;
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return curRef.Get().Get();
    }

    @Override
    public void Next() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      LLNode<Data> node = curRef.Get();
      curRef = node.GetNext();        
    }

    @Override
    public Data DataNNext() {
      Data d = GetCurrent();
      LLNode<Data> node = curRef.Get();
      curRef = node.GetNext();        
      return d;
    }

    @Override
    public void SetCurrent(Data data) {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      curRef.Get().Set(data);
    }
  }

  // BIterator
  protected class ListBIter implements MutableBackwardIterator<Data> {

    private LLNode<Data> curr;   
    private LLNode<Data> prev;  

    public ListBIter() {
      curr = tailref.Get();
      prev = null;
      if (curr != null) {
        LLNode<Data> tmpPrev = null;
        LLNode<Data> tmp = headref.Get();
        while (tmp != null && tmp != curr) {
          tmpPrev = tmp;
          tmp = tmp.GetNext().Get();
        }
        prev = tmpPrev;
      }
    }

    @Override
    public boolean IsValid() {
      return curr != null;
    }

    @Override
    public void Reset() {
      curr = tailref.Get();
      prev = null;
      if (curr != null) {
        LLNode<Data> tmpPrev = null;
        LLNode<Data> tmp = headref.Get();
        while (tmp != null && tmp != curr) {
          tmpPrev = tmp;
          tmp = tmp.GetNext().Get();
        }
        prev = tmpPrev;
      }
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return curr.Get();
    }

    @Override
    public void Prev() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      LLNode<Data> newCurr = prev;
      LLNode<Data> newPrev = null;
      LLNode<Data> tmp = headref.Get();
      while (tmp != null && tmp != newCurr) {
        newPrev = tmp;
        tmp = tmp.GetNext().Get();
      }
      curr = newCurr;
      prev = newPrev;
    }

    @Override
    public Data DataNPrev() {
      Data d = GetCurrent();
      if (prev == null) {
        curr = null;   
      } else {
        LLNode<Data> newCurr = prev;
        LLNode<Data> newPrev = null;
        LLNode<Data> tmp = headref.Get();
        while (tmp != null && tmp != newCurr) {
          newPrev = tmp;
          tmp = tmp.GetNext().Get();
        }
        curr = newCurr;
        prev = newPrev;
      }
      return d;
    }

    @Override
    public void SetCurrent(Data data) {
      if (!IsValid()) {
        throw new IllegalStateException("Iterator terminated");
      }
      curr.Set(data);
    }
  }


  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new ListFIter();
  }

  
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new ListBIter();
  }
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...
  // SetAt
  @Override
  public void SetAt(Data data, Natural index) {
    if (index.ToLong() >= Size().ToLong() || index.ToLong() < 0) throw new IndexOutOfBoundsException("Index: " + index);
    LLNode<Data> node = headref.Get();
    long i = 0;
    long target = index.ToLong();
    while (i < target && node != null) {
      node = node.GetNext().Get();
      i++;
    }
    if (node == null) {
      throw new IllegalStateException("Internal list structure corrupted");
    }
    node.Set(data);
  }

  // SetFirst
  @Override
  public void SetFirst(Data data) {
    if (headref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    headref.Get().Set(data);
  }

  // SetLast
  @Override
  public void SetLast(Data data) {
    if (tailref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    tailref.Get().Set(data);
  }

  // SubSequence
  @Override
  public MutableSequence<Data> SubSequence(Natural startindex, Natural endindex) {
    if (startindex.ToLong() < 0 || endindex.ToLong() > size.ToLong() || startindex.ToLong() > endindex.ToLong()) {
      throw new IndexOutOfBoundsException("Invalid start or end index");
    }
    LLList<Data> subList = new LLList<Data>();
    LLNode<Data> cur = headref.Get();
    long index = 0;
    while (cur != null && index < endindex.ToLong()) {
      if (index >= startindex.ToLong()) {
        subList.InsertLast(cur.Get());
      }
      cur = cur.GetNext().Get();
      index++;
    }
    return subList;
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  // InsertAt
  @Override
  public void InsertAt(Data data, Natural index) {
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