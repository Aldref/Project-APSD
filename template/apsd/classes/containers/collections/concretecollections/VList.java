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
protected class ListFIter implements MutableForwardIterator<Data> {

  private Natural idx = Natural.Of(0); 

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

    private long idx;

    public ListBIter() {
      long size = vec.Size().ToLong();
      idx = (size == 0) ? -1L : size - 1;
    }

    @Override
    public boolean IsValid() {
      return idx >= 0 && idx < vec.Size().ToLong();
    }

    @Override
    public void Reset() {
      long size = vec.Size().ToLong();
      idx = (size == 0) ? -1L : size - 1;
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      return vec.GetAt(Natural.Of(idx));
    }

    @Override
    public Data DataNPrev() {
      Data data = GetCurrent();
      idx--;
      return data;
    }

    @Override
    public void SetCurrent(Data data) {
      if (!IsValid()) throw new IllegalStateException("Iterator terminated");
      vec.SetAt(data, Natural.Of(idx));
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
    if (num == null) throw new NullPointerException();
    if (data == null) return;
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
    if (num == null) throw new NullPointerException();
    if (data == null) return;
    vec.InsertAt(data, num);
  }

}