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
  //FIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {
    return vec.FMutableIterator();
  }

  //BIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return vec.BMutableIterator();
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

  // SubSequence
  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end) {
    DynVector<Data> subVec = vec.SubVector(start, end);
    return new VList<Data>(subVec);;
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
}