package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final DynVector<Data> vec;

  // VChainBase
  public VChainBase() {
    vec = new DynCircularVector<Data>();
  }

  // NewChain
  public VChainBase(TraversableContainer<Data> con) {
    vec = new DynCircularVector<Data>(con);
  }

  protected VChainBase(DynVector<Data> vec) {
    this.vec = vec;
  }

  abstract protected VChainBase<Data> NewChain(DynVector<Data> vec);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return vec.Size();
  }
  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear(){
    vec.Clear();
  }
  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // ...
  // Remove
  @Override
  public boolean Remove(Data dat) {
    if (dat == null) return false;
    Natural pos = vec.Search(dat);
    if (pos == null) return false;
    vec.ShiftLeft(pos);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // FIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {
    return vec.FIterator();
  }

  // BIterator
  @Override
  public BackwardIterator<Data> BIterator() {
    return vec.BIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  // GetAt
  @Override
  public Data GetAt(Natural index) {
    return vec.GetAt(index);
  }

  // SubSequence
  @Override
  public Sequence<Data> SubSequence(Natural start, Natural end) {
    long from = start.ToLong();
    long to   = end.ToLong();
    if (from > to || to >= vec.Size().ToLong()) throw new IndexOutOfBoundsException();
    DynVector<Data> subVec =new DynCircularVector<Data>(Natural.Of(to - from + 1));
    long j = 0;
    for (long i = from; i <= to; ++i) {
      subVec.SetAt(
      vec.GetAt(Natural.Of(i)),Natural.Of(j));
      ++j;
    }
    return subVec;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  // AtNRemove
  @Override
  public Data AtNRemove(Natural index) {
    long idx = ExcIfOutOfBound(index);
    Data data = vec.GetAt(index);
    vec.ShiftLeft(index);
    return data;
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...
  // filter
  @Override
  public boolean Filter(Predicate<Data> fun) {
    long oldSize = vec.Size().ToLong();
    if (fun != null) {
      long i = 0;
      long newSize = oldSize;
      while (i < newSize) {
        Data dat = vec.GetAt(Natural.Of(i));
        if (!fun.Apply(dat)) {
          vec.ShiftLeft(Natural.Of(i));
          newSize--;
        } else {
          i++;
        }
      }
    }
    return vec.Size().ToLong() < oldSize;
  }

}
