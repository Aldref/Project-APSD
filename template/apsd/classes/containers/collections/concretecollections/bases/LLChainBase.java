package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase
  public LLChainBase() { }

  protected LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
    this.size.Assign(Natural.Of(size)); 
    headref.Set(head);
    tailref.Set(tail);
  }

  public LLChainBase(TraversableContainer<Data> con) {
    if (con != null) {
      size.Assign(con.Size());
      final Box<Boolean> first = new Box<>(true);
      con.TraverseForward(dat -> {
        LLNode<Data> node = new LLNode<>(dat);
        if (first.Get()) {
          headref.Set(node);
          first.Set(false);
        } else {
          tailref.Get().SetNext(node);
        }
        tailref.Set(node);
        return false;
      });
    }
  }

  // NewChain
  abstract protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);
  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  // ...
  protected LLNode<Data> HeadNode() {
    return headref.Get();
  }

  protected LLNode<Data> TailNode() {
    return tailref.Get();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return size.ToNatural();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear(){
    headref.Set(null);
    tailref.Set(null);
    size.Zero();
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // Remove
  @Override
  public boolean Remove(Data dat) {
    boolean removed = false;
    LLNode<Data> prev = null;
    LLNode<Data> curr = headref.Get();
    while (curr != null) {
      Data curVal = curr.Get();
      if ((curVal == null && dat == null) || (curVal != null && curVal.equals(dat))) {
        LLNode<Data> next = (curr.GetNext() != null ? curr.GetNext().Get() : null);
        if (prev == null) {
          headref.Set(next);
        } else {
          prev.SetNext(next);
        }
        if (tailref.Get() == curr) {
          tailref.Set(prev);
        }
        size.Decrement();
        removed = true;
        curr = next;
        continue;
      }
      prev = curr;
      curr = (curr.GetNext() != null ? curr.GetNext().Get() : null);
    }
    return removed;
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // FRefIterator
  protected class ListFIterator implements ForwardIterator<Data> {
      protected LLNode<Data> cur;
  
      public ListFIterator() {
        cur = headref.Get();
      }
  
      @Override
      public boolean IsValid() {
        return cur != null;
      }
  
      @Override
      public void Reset() {
        cur = headref.Get();
      }
  
      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return cur.Get();
      }
      
      @Override
      public void Next() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        cur = (cur.GetNext() != null ? cur.GetNext().Get() : null);
      }

      @Override
      public Data DataNNext() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        Data old = GetCurrent();
        cur = (cur.GetNext() != null ? cur.GetNext().Get() : null);
        return old;
      }
    }
  
    @Override
    public ForwardIterator<Data> FIterator() {
      return new ListFIterator();
    }
  
    // BIterator
    protected class ListBIterator implements BackwardIterator<Data> {
      protected LLNode<Data> cur;
  
      public ListBIterator() {
        cur = headref.Get();
        if (cur != null) {
          while (cur.GetNext() != null && cur.GetNext().Get() != null) {
            cur = cur.GetNext().Get();
          }
        }
      }
  
      @Override
      public boolean IsValid() {
        return cur != null;
      }
  
      @Override
      public void Reset() {
        cur = headref.Get();
        if (cur != null) {
          while (cur.GetNext() != null && cur.GetNext().Get() != null) {
            cur = cur.GetNext().Get();
          }
        }
      }
  
      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return cur.Get();
      }
      
      @Override
      public void Prev() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        cur = (cur.GetNext() != null ? cur.GetNext().Get() : null);
      }

      @Override
      public Data DataNPrev() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        Data old = GetCurrent();
        LLNode<Data> node = headref.Get();
        LLNode<Data> prev = null;
        while (node != null && node != cur) {
          prev = node;
          node = (node.GetNext() != null ? node.GetNext().Get() : null);
        }
        cur = prev;
        return old;
      }
    }
  
    @Override
    public BackwardIterator<Data> BIterator() {
      return new ListBIterator();
    }

  
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  //GetFirst
  @Override
  public Data GetFirst() {
    if (headref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    return headref.Get().Get();
  }

  //GetLast
  @Override
  public Data GetLast() {
    if (tailref.Get() == null) throw new IndexOutOfBoundsException("Container is empty");
    return tailref.Get().Get();
  }

  // SubSequence
  @Override
  public Sequence<Data> SubSequence(Natural startindex, Natural endindex) {
    long start = startindex.ToLong();
    long end   = endindex.ToLong();
    long sz    = size.ToLong();
    if (start < 0 || end > sz || start > end) throw new IndexOutOfBoundsException("Invalid start or end index");
    LLNode<Data> subhead = null;
    LLNode<Data> subtail = null;
    LLNode<Data> cur = headref.Get();
    long index = 0;
    while (cur != null && index < end) {
      if (index >= start) {
        LLNode<Data> newnode = new LLNode<>(cur.Get());
        if (subhead == null) {
          subhead = newnode;
          subtail = newnode;
        } else {
          subtail.SetNext(newnode);
          subtail = newnode;
        }
      }
      cur = (cur.GetNext() != null ? cur.GetNext().Get() : null);
      index++;
    }
    long newSize = end - start;
    LLChainBase<Data> subChain = NewChain(newSize, subhead, subtail);
    return subChain;
  }

  public Chain<Data> SubChain(Natural from, Natural to) {
    Sequence<Data> subSeq = SubSequence(from, to);
    final Box<LLNode<Data>> headBox = new Box<>(null);
    final Box<LLNode<Data>> tailBox = new Box<>(null);
    subSeq.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (headBox.Get() == null) {
        headBox.Set(node);
        tailBox.Set(node);
      } else {
        tailBox.Get().SetNext(node);
        tailBox.Set(node);
      }
      return false;
    });

    long newSize = to.ToLong() - from.ToLong();
    return NewChain(newSize, headBox.Get(), tailBox.Get());
  }

  // non so se serva
  @Override
  public boolean Exists(Data data) {
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      if ((it.GetCurrent() == null && data == null) ||
          (it.GetCurrent() != null && it.GetCurrent().equals(data))) {
        return true;
      }
      it.DataNNext();
    }
    return false;
  }
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  
  //AtNRemove
  @Override
  public Data AtNRemove(Natural index) {
    long idx = index.ToLong();
    if (idx < 0 || idx >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    LLNode<Data> prev = null;
    LLNode<Data> curr = headref.Get();
    long i = 0;
    while (curr != null && i < idx) {
      prev = curr;
      curr = (curr.GetNext() != null ? curr.GetNext().Get() : null);
      i++;
    }
    if (curr == null) {
      throw new IndexOutOfBoundsException("Index out of range during removal");
    }
    Data removed = curr.Get();
    LLNode<Data> next = (curr.GetNext() != null ? curr.GetNext().Get() : null);
    if (prev == null) {
      headref.Set(next);
    } else {
      prev.SetNext(next);
    }
    if (tailref.Get() == curr) {
      tailref.Set(prev);
    }
    size.Decrement();
    return removed;
  }

  // RemoveFirst
  @Override
  public void RemoveFirst() {
    if (size.ToLong() == 0) return;
    AtNRemove(new Natural(0));
  }

  // RemoveLast
  @Override
  public void RemoveLast() {
    if (size.ToLong() == 0) return;
    AtNRemove(new Natural(size.ToLong() - 1));
  }

  // FirstNRemove
  @Override
  public Data FirstNRemove() {
    if (size.ToLong() == 0) return null;
    return AtNRemove(new Natural(0));
  }

  // LastNRemove
  @Override
  public Data LastNRemove() {
    if (size.ToLong() == 0) return null;
    return AtNRemove(new Natural(size.ToLong() - 1));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...
  // Filter
  @Override
  public boolean Filter(Predicate<Data> fun) {
    boolean removedAny = false;
    LLNode<Data> prev = null;
    LLNode<Data> curr = headref.Get();
    while (curr != null) {
      LLNode<Data> next = (curr.GetNext() != null ? curr.GetNext().Get() : null);
      if (fun.Apply(curr.Get())) {
        if (prev == null) {
          headref.Set(next);
        } else {
          prev.SetNext(next);
        }
        if (tailref.Get() == curr) {
          tailref.Set(prev);
        }
        size.Decrement();
        removedAny = true;
      } else {
        prev = curr;
      }
      curr = next;
    }
    return removedAny;
  }
}
