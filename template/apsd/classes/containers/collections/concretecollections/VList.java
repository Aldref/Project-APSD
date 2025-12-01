package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data> extends VChainBase<Data> implements List<Data> { // Must extend VChainBase and implement List

  // public VList()
  public VList() {
    super();
  }
  // public VList(TraversableContainer<Data> con)
  public VList(TraversableContainer<Data> con) {
    super();
    if (con != null) {
      con.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }

  // protected VList(DynVector<Data> vec)
  protected VList(DynVector<Data> vec) {
    super(vec);
  }

  // NewChain
  @Override
  public VList<Data> NewChain(DynVector<Data> vec) {
    return new VList<Data>(vec);
  }
  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  // ...
  //FMutIterator
  // FMutIterator
protected class ListFIter implements MutableForwardIterator<Data> {

  private Natural idx = Natural.Of(0); // non usare Natural.ZERO

  @Override
  public boolean IsValid() {
    return !vec.Size().IsZero() && idx.ToLong() < vec.Size().ToLong();
  }

  @Override
  public void Reset() {
    idx = Natural.Of(0);
  }

  @Override
  public Data GetCurrent() {
    if (!IsValid()) throw new IllegalStateException("Iterator terminated");
    return vec.GetAt(idx);
  }

  @Override
  public Data DataNNext() {
    Data d = GetCurrent();
    idx = Natural.Of(idx.ToLong() + 1);
    return d;
  }

  @Override
  public void SetCurrent(Data data) {
    if (!IsValid()) throw new IllegalStateException("Iterator terminated");
    vec.SetAt(data, idx);
  }
}


  // BMutIterator
  protected class ListBIter implements MutableBackwardIterator<Data> {

    private Natural idx;

    public ListBIter() {
      if (vec.Size().IsZero()) {
        idx = Natural.Of(0);
      } else {
        idx = Natural.Of(vec.Size().ToLong() - 1);
      }
    }

    @Override
    public boolean IsValid() {
      return !vec.Size().IsZero() && idx.ToLong() < vec.Size().ToLong();
    }

    @Override
    public void Reset() {
      if (vec.Size().IsZero()) {
        idx = Natural.Of(0);
      } else {
        idx = Natural.Of(vec.Size().ToLong() - 1);
      }
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return vec.GetAt(idx);
    }

    @Override
    public Data DataNPrev() {
      Data d = GetCurrent();
      if (idx.ToLong() == 0) {
        idx = Natural.Of(vec.Size().ToLong()); 
        idx = Natural.Of(idx.ToLong() - 1);
      } else {
        idx = Natural.Of(idx.ToLong() - 1);
      }
      return d;
    }

    @Override
    public void SetCurrent(Data data) {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      vec.SetAt(data, idx);
    }
  }


  // FIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new ListFIter();
  }

  // BIterator
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
  public void SetAt(Data data, Natural num) {
    vec.SetAt(data, num);
  }

  //SubSequence
  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    return (MutableSequence<Data>) super.SubSequence(start, end);
  }

  // SubChain
  @Override
  public VList<Data> SubChain(Natural from, Natural to) {
    MutableSequence<Data> seq = SubSequence(from, to);
    return new VList<Data>((DynVector<Data>) seq);
  }

  // SubList
  @Override
  public VList<Data> SubList(Natural from, Natural to) {
    MutableSequence<Data> seq = SubSequence(from, to);
    return new VList<Data>((DynVector<Data>) seq);
  }
  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  // ...
  // InsertAt
  @Override
  public void InsertAt(Data data, Natural num) {
    vec.InsertAt(data, num);
  }

  // non penso serva
  @Override
  public boolean Exists(Data data) {
    DynVector<Data> v = vec;
    Natural i = Natural.ZERO;
    while (i.ToLong() < v.Size().ToLong()) {
      if (v.GetAt(i) == null ? data == null : v.GetAt(i).equals(data)) return true;
      i.Increment();
    }
    return false;
  }
}