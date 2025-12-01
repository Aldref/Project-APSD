package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence; // forse non serve

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> { // Must extend VChainBase and implements SortedChain

  // public VSortedChain()
  public VSortedChain() {
    super();
  }
  // public VSortedChain(VSortedChain<Data> chn)
  public VSortedChain(VSortedChain<Data> chn) {
    super();
    if (chn != null) {
      chn.TraverseForward(dat -> {
        InsertIfAbsent(dat);
        return false;
      });
    }
    
  }

  // public VSortedChain(TraversableContainer<Data> con)
  public VSortedChain(TraversableContainer<Data> con) {
    super();
    if (con != null) {
      con.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }

  // protected VSortedChain(DynVector<Data> vec)
  protected VSortedChain(DynVector<Data> vec) {
    super(vec);
  }

  // NewChain
  public VSortedChain<Data> NewChain(DynVector<Data> vec) {
    return new VSortedChain<Data>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  // Insert
  @Override
  public boolean Insert(Data data) {
    return InsertIfAbsent(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // ...
  // InsertIfAbsent
  @Override
public boolean InsertIfAbsent(Data data) {
    if (data == null) return false;
    if (vec.Size().ToLong() == 0) {
      vec.Expand(Natural.ONE);
      vec.SetAt(data, Natural.ZERO);
      return true;
    }
    Natural pred = SearchPredecessor(data);
    Natural pos;
    if (pred == null) {
      pos = Natural.ZERO;
    } else {
      Data predData = GetAt(pred);
      int cmp = predData.compareTo(data);
      if (cmp == 0) return false; 
      pos = pred.Increment(); 
    }
    vec.InsertAt(data, pos);
    return true;
}



  // RemoveOccurrences
  @Override
  public void RemoveOccurrences(Data data) {
    if (data == null) return;
    Natural pred = SearchPredecessor(data);
    Natural pos;
    if (pred == null) {
      pos = Natural.ZERO;
    } else {
      pos = pred.Increment();
    }
    while (pos.ToLong() < vec.Size().ToLong()) {
      Data curr = vec.GetAt(pos);
      int cmp = curr.compareTo(data);
      if (cmp == 0) {
        vec.RemoveAt(pos); 
      } else {
        break; 
      }
    }
  }

}
