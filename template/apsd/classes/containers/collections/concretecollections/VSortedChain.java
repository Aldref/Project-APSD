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
    long pos = 0;
    MutableForwardIterator<Data> it = vec.FIterator();
    while (it.IsValid()) {
        Data curr = it.GetCurrent();
        if (curr == null) { 
            pos++;
            it.Next();
            continue;
        }
        int cmp = data.compareTo(curr);
        if (cmp == 0) return false; 
        if (cmp < 0) break;         
        pos++;
        it.Next();
    }
    vec.InsertAt(data, Natural.Of(pos));
    return true;
}

  // RemoveOccurrences
  @Override
  public void RemoveOccurrences(Data data) {
    if (data == null) return;
    MutableForwardIterator<Data> it = vec.FIterator();
    while (it.IsValid()) {
      Data curr = it.GetCurrent();
      if (curr == null) {
        it.Next();
        continue;
      }
      int cmp = data.compareTo(curr);
      if (cmp == 0) {
        Remove(curr);
      } else if (cmp < 0) {
        break;
      } else {
        it.Next();
      }
    }
    
  }

  @Override
  public VSortedChain<Data> SubChain(Natural from, Natural to) {
    Sequence<Data> subSeq = SubSequence(from, to);
    VSortedChain<Data> res = new VSortedChain<>();
    subSeq.TraverseForward(dat -> {
      res.Insert(dat); 
      return false;
    });
    return res;
  }

}
