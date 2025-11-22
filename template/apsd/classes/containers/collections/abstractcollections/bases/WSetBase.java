package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> { // Must implement Set; Chn must extend Chain

  protected Chn chn;

  // WSetBase
  public WSetBase() {
    ChainAlloc();
  }

  public WSetBase(Chn chn) {
    this.chn = chn;
  }
  public WSetBase(TraversableContainer<Data> con) {
    ChainAlloc();
    if (con != null) {
      con.TraverseForward(dat -> {
        chn.InsertIfAbsent(dat); return false;
      });
    }
  }

  public WSetBase(Chn chn, TraversableContainer<Data> con) {
    this.chn = chn;
    if (con != null) {
      con.TraverseForward(dat ->{
        chn.InsertIfAbsent(dat); return false;
      });
    }
  }
  // ChainAlloc
  abstract protected void ChainAlloc();
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return chn.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear() {
    chn.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  // Insert
  @Override
  public boolean Insert(Data data) {
    return chn.InsertIfAbsent(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // ...
  // Remove
  @Override
  public boolean Remove(Data data) {
    return chn.Remove(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  //FIterator
  @Override
  public ForwardIterator<Data> FIterator() {
    return chn.FIterator();
  }

  //BIterator
  @Override
  public BackwardIterator<Data> BIterator() {
    return chn.BIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...
  // Filter
  @Override
  public boolean Filter(Predicate<Data> fun) {
    return chn.Filter(fun);
  }
  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  // ...
  // Intersection
  @Override
  public void Intersection(Set<Data> other) {
    chn.Intersection(other);
  }

  // Exists (penso si debba togliere)
  @Override
  public boolean Exists(Data data) {
    return chn.TraverseForward(dat ->
        (dat == null ? data == null : dat.equals(data))
    );
  }
}
