package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> { // Must implement Stack

  protected final List<Data> lst;

  // public WStack()
  public WStack() {
    this.lst = new VList<>();
  }

  // public WStack(List<Data> lst)
  public WStack(List<Data> lst) {
    this.lst = lst;
  }

  // public WStack(TraversableContainer<Data> con)
  public WStack(TraversableContainer<Data> con) {
    lst = new VList<>();
    if (con != null) {
      con.TraverseForward(d -> {
        if (d != null) lst.InsertFirst(d);
        return false;
      });
    }
  }

  // public WStack(List<Data> lst, TraversableContainer<Data> con)
  public WStack(List<Data> lst, TraversableContainer<Data> con) {
    this.lst = lst;
    if (con != null) {
      con.TraverseForward(d -> {
        if (d != null) this.lst.InsertFirst(d);
        return false;          
      });
    }
  }
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return lst.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear() {
    lst.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

  // ...
  @Override
  public Data Top() {
    if (IsEmpty()) return null;
    long last = lst.Size().ToLong() - 1;
    return lst.GetAt(Natural.Of(last));
  }

  // Pop
  @Override
  public void Pop() {
    if (IsEmpty()) return;
    lst.RemoveLast();
  }

  // TopNPop
  @Override
  public Data TopNPop() {
    if (IsEmpty()) return null;
    Data top = Top();
    Pop();
    return top;
  }

  // SwapTop
  @Override
  public void SwapTop(Data data) {
    if (IsEmpty()) return;
    if (data == null) return;
    Pop();
    Push(data);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data data) {
    if (data == null) return false;
    Push(data);
    return true;
  }

  // TopNSwap
  @Override
  public Data TopNSwap(Data data) {
    Data top = Top();
    SwapTop(data);
    return top;
  }

  // Push
  @Override
  public void Push(Data data) {
    if (data == null) return;
    lst.InsertLast(data);
  }

}