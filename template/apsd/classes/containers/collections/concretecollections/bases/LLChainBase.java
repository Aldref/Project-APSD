package apsd.classes.containers.collections.concretecollections.bases;

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
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase

  public LLChainBase(TraversableContainer<Data> con) {
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

  // NewChain
  abstract protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);
  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  // ...

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
    if (dat == null) return false;
    final Box<LLNode<Data>> prd = new Box<>();
    return FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (node.Get().equals(dat)) {
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) { tailref.Set(prd.Get()); }
        size.Decrement();
        return true;
      }
      prd.Set(node);
      return false;
    });
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // FRefIterator
  protected class ListFRefIterator implements ForwardIterator<Box<LLNode<Data>>> {

    protected long cur = 0;
    protected Vector<Box<LLNode<Data>>> arr = null;

    public ListFRefIterator() { Reset(); }

    @Override
    public boolean IsValid() {
      return (cur >= 0 && cur < Size().ToLong());
    }

    @Override
    public void Reset() {
      cur = 0;
      if (Size().IsZero()) {
        arr = null;
      } else {
        arr = new Vector<>(Size());
        long i = 0;
        for (Box<LLNode<Data>> ref = headref; ref.Get() != null; ref = ref.Get().GetNext()) {
          arr.SetAt(ref, Natural.Of(i++));
        }
      }
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return arr.GetAt(Natural.Of(cur));
    }

    @Override
    public Box<LLNode<Data>> DataNNext() {
      Box<LLNode<Data>> old = GetCurrent();
      ++cur;
      return old;
    }
  }

  protected ForwardIterator<Box<LLNode<Data>>> FRefIterator() {
    return new ListFRefIterator();
  }

  
  protected class ListBRefIterator implements BackwardIterator<Box<LLNode<Data>>> {

    protected long cur = -1;
    protected Vector<Box<LLNode<Data>>> arr = null;

    public ListBRefIterator() { Reset(); }

    @Override
    public boolean IsValid() {
      return (cur >= 0 && cur < Size().ToLong());
    }

    @Override
    public void Reset() {
      if (Size().IsZero()) {
        cur = -1;
        arr  = null;
      } else {
        arr = new Vector<>(Size());
        long i = 0;
        for (Box<LLNode<Data>> ref = headref; ref.Get() != null; ref = ref.Get().GetNext()) {
          arr.SetAt(ref, Natural.Of(i++));
        }
        cur = Size().ToLong() - 1;
      }
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return arr.GetAt(Natural.Of(cur));
    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {
      Box<LLNode<Data>> old = GetCurrent();
      --cur;
      return old;
    }
  }

  protected BackwardIterator<Box<LLNode<Data>>> BRefIterator() {
    return new ListBRefIterator();
  }

  
  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  //GetFirst
  @Override
  public Data GetFirst() {
    if (headref.Get() == null) throw new IllegalStateException("Container is empty");
    return headref.Get().Get();
  }

  //GetLast
  @Override
  public Data GetLast() {
    if (tailref.Get() == null) throw new IllegalStateException("Container is empty");
    return tailref.Get().Get();
  }

  // SubSequence
  @Override
  public Sequence<Data> SubSequence(Natural startindex, Natural endindex) {
    if (startindex.ToLong() < 0 || endindex.ToLong() > size.ToLong() || startindex.ToLong() > endindex.ToLong()) {
      throw new IndexOutOfBoundsException("Invalid start or end index");
    }
    LLNode<Data> subhead = null;
    LLNode<Data> subtail = null;
    LLNode<Data> cur = headref.Get();
    long index = 0;
    while (cur != null && index < endindex.ToLong()) {
      if (index >= startindex.ToLong()) {
        LLNode<Data> newnode = new LLNode<>(cur.Get());
        if (subhead == null) {
          subhead = newnode;
          subtail = newnode;
        } else {
          subtail.SetNext(newnode);
          subtail = newnode;
        }
      }
      cur = cur.GetNext();
      index++;
    }
    return new LLChainBase<>(subhead, subtail, endindex.ToLong() - startindex.ToLong());
  }
  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  
  //AtNRemove
  @Override
  public Data AtNRemove(Natural index) {
    if (index.ToLong() < 0 || index.ToLong() >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    final Box<Data> removedData = new Box<>();
    final Box<LLNode<Data>> prd = new Box<>();
    FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (index.ToLong() == 0) {
        removedData.Set(node.Get());
        cur.Set(node.GetNext());
        if (tailref.Get() == node) { tailref.Set(prd.Get()); }
        size.Decrement();
        return true;
      }
      prd.Set(node);
      index.Decrement();
      return false;
    });
    return removedData.Get();
  }

  // RemoveFirst
  @Override
  public void RemoveFirst() {
    AtNRemove(new Natural(0));
  }

  // RemoveLast
  @Override
  public void RemoveLast() {
    AtNRemove(new Natural(size.ToLong() - 1));
  }

  // FirstNRemove
  @Override
  public Data FirstNRemove() {
    return AtNRemove(new Natural(0));
  }

  // LastNRemove
  @Override
  public Data LastNRemove() {
    return AtNRemove(new Natural(size.ToLong() - 1));
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...
  // Filter
  @Override
  public boolean Filter(Predicate<Data> pred) {
    final Box<Boolean> removed = new Box<>(false);
    final Box<LLNode<Data>> prd = new Box<>();
    FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (pred.Test(node.Get())) {
        cur.Set(node.GetNext());
        if (tailref.Get() == node) { tailref.Set(prd.Get()); }
        size.Decrement();
        removed.Set(true);
        return false;
      }
      prd.Set(node);
      return false;
    });
    return removed.Get();
  }
}
