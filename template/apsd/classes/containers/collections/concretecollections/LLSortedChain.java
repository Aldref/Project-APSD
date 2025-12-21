package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data> {

  // public LLSortedChain()
  public LLSortedChain() {
    super((TraversableContainer<Data>) null);
  }
  // public LLSortedChain(LLSortedChain<Data> chn)
  public LLSortedChain(LLSortedChain<Data> chn) {
    super(chn);
  }
  // public LLSortedChain(TraversableContainer<Data> con)
  public LLSortedChain(TraversableContainer<Data> con) {
    super((TraversableContainer<Data>) null);
    if (con != null) {
      con.TraverseForward(dat -> {
        Insert(dat);
        return false;
      });
    }
  }
  // protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail)
  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }
  // NewChain
  @Override
  protected LLSortedChain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLSortedChain<Data>(size, head, tail);
  }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  // ...
  // PredFind
  protected LLNode<Data> PredFind(Data data) {
    LLNode<Data> curr = HeadNode();
    if (curr == null) return null;
    while (true) {
      Box<LLNode<Data>> nextBox = curr.GetNext();
      if (nextBox == null) break;
      LLNode<Data> next = nextBox.Get();
      if (next == null) break;
      Comparable<Data> cmp = (Comparable<Data>) next.Get();
      if (cmp.compareTo(data) >= 0) break;
      curr = next;
    }
    return curr;
  }

  // PredPredFind
  protected LLNode<Data> PredPredFind(Data data) {
    LLNode<Data> pred = PredFind(data);
    if (pred == null) return null;
    if (pred == HeadNode()) return null;
    LLNode<Data> curr = HeadNode();
    LLNode<Data> predPred = null;
    while (curr != null && curr != pred) {
      predPred = curr;
      Box<LLNode<Data>> nextBox = curr.GetNext();
      curr = (nextBox != null) ? nextBox.Get() : null;
    }
    return predPred;
  }

  // SuccFind
  protected LLNode<Data> SuccFind(Data data) {
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    if (head == null) return null;
    Box<LLNode<Data>> nb = head.GetNext();
    LLNode<Data> curr = (nb != null) ? nb.Get() : null;
    while (curr != null && curr != tail) {
      Comparable<Data> cmp = (Comparable<Data>) curr.Get();
      if (cmp.compareTo(data) > 0) {
        return curr;
      }
      Box<LLNode<Data>> nextBox = curr.GetNext();
      curr = (nextBox != null) ? nextBox.Get() : null;
    }
    return null;
  }

  // PredSuccFind
  public LLNode<Data> PredSuccFind(Data dat) {
    if (dat == null || headref.IsNull()) return null;
    LLNode<Data> pred = null;
    LLNode<Data> curr = headref.Get();
    while (curr != null) {
      if (curr.Get().compareTo(dat) > 0) {
        return pred;
      }
      pred = curr;
      Box<LLNode<Data>> nextBox = curr.GetNext();
      curr = (nextBox != null) ? nextBox.Get() : null;
    }
    return pred;
  }


  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  // Insert

  @Override
  public boolean Insert(Data data) {
    if (data == null) return false;
    LLNode<Data> head = HeadNode();
    LLNode<Data> tail = TailNode();
    LLNode<Data> newNode = new LLNode<>(data);
    if (head == null) {
      headref.Set(newNode);
      tailref.Set(newNode);
    } else if (head.Get().compareTo(data) >= 0) {
      newNode.SetNext(head);
      headref.Set(newNode);
    } else {
      LLNode<Data> pred = PredFind(data);
      Box<LLNode<Data>> nextBox = pred.GetNext();
      LLNode<Data> next = (nextBox != null) ? nextBox.Get() : null;
      pred.SetNext(newNode);
      newNode.SetNext(next);
      if (pred == tail) tailref.Set(newNode);
    }
    size.Increment();
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // ...
  // Remove
  @Override
  public boolean Remove(Data data) {
    return super.Remove(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  

  /* ************************************************************************ */
  /* Override specific member functions from SortedSequence                   */
  /* ************************************************************************ */

  // ...
  // Search
  @Override
  public Natural Search(Data data) {
    if (data == null) return null;
    if (headref.Get() == null) return null;
    long low = 0;
    long high = size.ToLong() - 1;
    while (low <= high) {
      long mid = (low + high) / 2;
      LLNode<Data> midNode = headref.Get();
      for (long i = 0; i < mid; i++) {
        if (midNode == null) break;
        Box<LLNode<Data>> nextBox = midNode.GetNext();
        midNode = nextBox != null ? nextBox.Get() : null;
      }
      if (midNode == null) break;
      Comparable<? super Data> cmp = (Comparable<? super Data>) midNode.Get();
      int res = cmp.compareTo(data);
      if (res == 0) {
        return Natural.Of(mid);
      } else if (res < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...
  // all Predecessor methods
  @Override
  public Natural SearchPredecessor(Data data) {
    if (data == null || headref.Get() == null) return null;
    long low = 0;
    long high = size.ToLong() - 1;
    Long predIndex = null;
    while (low <= high) {
      long mid = (low + high) / 2;
      LLNode<Data> midNode = headref.Get();
      for (long i = 0; i < mid; i++) {
        if (midNode == null) break;
        Box<LLNode<Data>> nextBox = midNode.GetNext();
        midNode = nextBox != null ? nextBox.Get() : null;
      }
      if (midNode == null) break;
      Comparable<? super Data> cmp = (Comparable<? super Data>) midNode.Get();
      int res = cmp.compareTo(data);
      if (res < 0) {
        predIndex = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (predIndex == null) return null;
    return Natural.Of(predIndex.longValue());
  }

  @Override
  public Data Predecessor(Data data) {
    if (data == null) return null;
    Natural idx = SearchPredecessor(data);
    return idx == null ? null : GetAt(idx);
  }

  @Override
  public void RemovePredecessor(Data data) {
    Natural idx = SearchPredecessor(data);
    if (idx == null) return;
    AtNRemove(idx);
  }

  @Override
  public Data PredecessorNRemove(Data data) {
    Natural idx = SearchPredecessor(data);
    if (idx == null) return null;
    Data pred = GetAt(idx);
    AtNRemove(idx);
    return pred;
  }

  // All Successor methods
  @Override
  public Natural SearchSuccessor(Data data) {
    if (data == null || headref.Get() == null) return null;
    long low = 0;
    long high = size.ToLong() - 1;
    Long sucIndex = null;
    while (low <= high) {
      long mid = (low + high) / 2;
      LLNode<Data> midNode = headref.Get();
      for (long i = 0; i < mid; i++) {
        if (midNode == null) break;
        Box<LLNode<Data>> nextBox = midNode.GetNext();
        midNode = nextBox != null ? nextBox.Get() : null;
      }
      if (midNode == null) break;
      Comparable<? super Data> cmp = (Comparable<? super Data>) midNode.Get();
      int res = cmp.compareTo(data);
      if (res > 0) {
        sucIndex = mid; 
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    if (sucIndex == null) return null;
    return Natural.Of(sucIndex.longValue());
  }

  @Override
  public Data Successor(Data data) {
    if (data == null) return null;
    Natural idx = SearchSuccessor(data);
    return idx == null ? null : GetAt(idx);
  }

  @Override
  public void RemoveSuccessor(Data data) {
    Natural idx = SearchSuccessor(data);
    if (idx == null) return;
    AtNRemove(idx);
  }

  @Override
  public Data SuccessorNRemove(Data data) {
    Natural idx = SearchSuccessor(data);
    if (idx == null) return null;
    Data succ = GetAt(idx);
    AtNRemove(idx);
    return succ;
  }


  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  // ...
  // InsertIfAbsent
  @Override
  public boolean InsertIfAbsent(Data data){
    if (data == null) return false;
    if (Search(data) != null) {
      return false;
    }
    Insert(data);
    return true;
  }

  // RemoveOccurrences
  @Override
  public void RemoveOccurrences(Data data){
    if (data == null) return;
    while (Search(data) != null) {
      Remove(data);
    }
  }

}
