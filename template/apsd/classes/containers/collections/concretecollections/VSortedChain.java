package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<Data>> extends VChainBase<Data> implements SortedChain<Data> { // Must extend VChainBase and implements SortedChain

  // public VSortedChain()
  public VSortedChain() {
    super();
  }
  // public VSortedChain(VSortedChain<Data> chn)
  public VSortedChain(VSortedChain<Data> chn) {
    super();
    if (chn != null) {
      chn.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
    
  }

  // public VSortedChain(TraversableContainer<Data> con)
  protected VSortedChain(TraversableContainer<Data> con) {
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
    if (data == null) return false;
    long size = Size().ToLong(); 
    long pos = 0;
    while (pos < size) {
      Data curr = vec.GetAt(Natural.Of(pos));
      if (curr != null && data.compareTo(curr) <= 0) {
        break;
      }
      pos++;
    }
    vec.InsertAt(data, Natural.Of(pos)); 
    return true;
  }
  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // ...
  // InsertIfAbsent
  @Override
  public boolean InsertIfAbsent(Data data) {
    if (data == null) return false;
    long size = Size().ToLong(); 
    long pos = 0;
    while (pos < size) {
      Data curr = vec.GetAt(Natural.Of(pos));
      if (curr != null && data.compareTo(curr) <= 0) {
        break;        
      }
      pos++;
    }
    vec.InsertAt(data, Natural.Of(pos)); 
    return true;
  }

  // RemoveOccurences
  @Override
  public void RemoveOccurences(Data data) {
    if (data == null) return;
    MutableForwardIterator<Data> it = vec.GetForwardIterator();
    while (it.IsValid()) {
      Data curr = it.GetCurrent();
      if (curr != null) {
        int cmp = data.compareTo(curr);
        if (cmp == 0) {
          it.Remove();
        } else if (cmp < 0) {
          break;
        }
      }
    }
  }

}
