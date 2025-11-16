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
  public boolean Remove(Data data) {
    MutableForwardIterator<Data> it = vec.FIterator();
    while (it.IsValid()) {
      Data curr = it.GetCurrent();
      if ((data == null && curr == null) || (data != null && data.equals(curr))) {
        it.Remove(); // da rivedere
        return true;;
      }
      it.Next();
    }
    return false;;
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
  public Sequence<Data> SubSequence(Natural start, Natural length) {
    return vec.SubVector(start, length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...
  // AtNRemove
  @Override
  public Data AtNRemove(Natural index) {
    long idx = ExcIfOutOfBound(index);
    MutableForwardIterator<Data> it = vec.FIterator();
    it.Next(Natural.Of(idx));
    Data data = it.GetCurrent();
    it.Remove();
    return data;
  }
  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...
  // filter
  @Override
  public boolean filter(Predicate<Data> fun) {
    long del = 0;
    if (fun != null){
      MutableForwardIterator<Data> it = vec.FIterator();
      while (it.IsValid()) {
        if (!fun.Apply(it.GetCurrent())) {
          del++;
          it.SetCurrent(null);
        } 
        it.Next();
      }
      if (del > 0) {
        it.Reset();
        MutableForwardIterator<Data> newIt = vec.FIterator();
        while (newIt.IsValid()) {
          if (newIt.GetCurrent() != null) {
            Data dato = newIt.GetCurrent();
            newIt.SetCurret(null);
            it.SetCurrent(dato);
            it.Next();
            newIt.Next();
          } 
          }
          vec.Reduce(Natural.Of(del));
        }
    }
    return (del > 0);
  }



}
