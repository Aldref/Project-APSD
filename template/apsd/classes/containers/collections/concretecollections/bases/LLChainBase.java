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

  protected LLNode<Data> HeadNode() {
    return headref.Get();
  }

  protected LLNode<Data> TailNode() {
    return tailref.Get();
  }

  // LLChainBase
  protected LLChainBase(LLChainBase<Data> chn) {
    if (chn == null) {
      size.Zero();
      headref.Set(null);
      tailref.Set(null);
      return;
    }
    this.size.Assign(chn.size.ToNatural());
    LLNode<Data> src = chn.headref.Get();
    LLNode<Data> newHead = null;
    LLNode<Data> newTail = null;
    while (src != null) {
      LLNode<Data> newNode = new LLNode<>(src.Get());
      if (newHead == null) {
        newHead = newNode;
        newTail = newNode;
      } else {
        newTail.SetNext(newNode);
        newTail = newNode;
      }
      src = (src.GetNext() != null ? src.GetNext().Get() : null);
    }
    this.headref.Set(newHead);
    this.tailref.Set(newTail);
  }

  protected LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
    this.size.Assign(Natural.Of(size)); 
    this.headref.Set(head);
    this.tailref.Set(tail);
  }

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
    long start = startindex.ToLong();
    long end   = endindex.ToLong();
    long sz    = size.ToLong();
    if (start < 0 || end > sz || start > end) {
      throw new IndexOutOfBoundsException("Invalid start or end index");
    }
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
    LLNode<Data> head = null;
    LLNode<Data> tail = null;
    subSeq.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (head == null) {
        head = node;
        tail = node;
      } else {
        tail.SetNext(node);
        tail = node;
      }
      return false;
    });
    long newSize = to.ToLong() - from.ToLong();
    return NewChain(newSize, head, tail);
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
    if (index.ToLong() < 0 || index.ToLong() >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    final Box<Data> removedData = new Box<>();
    final Box<LLNode<Data>> prd = new Box<>();
    FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (index.ToLong() == 0) {
        removedData.Set(node.Get());
        cur.Set(node.GetNext().Get());
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
  public boolean Filter(Predicate<Data> fun) {
    final Box<Boolean> removed = new Box<>(false);
    final Box<LLNode<Data>> prd = new Box<>();
    FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (fun.Apply(node.Get())) {
        cur.Set(node.GetNext().Get());
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
