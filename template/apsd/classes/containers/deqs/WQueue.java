package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data> { // Must implement Queue

  protected final List<Data> lst;

  // public WQueue()
  public WQueue() {
    this.lst = new VList<>();
  }

  // public WQueue(List<Data> lst)
  public WQueue(List<Data> lst) {
    this.lst = lst;
  }

  // public WQueue(TraversableContainer<Data> con)
  public WQueue(TraversableContainer<Data> con) {
    lst = new VList<>(con);
  }

  // public WQueue(List<Data> lst, TraversableContainer<Data> con)
  public WQueue(List<Data> lst, TraversableContainer<Data> con) {
    this.lst = lst;
    if (con != null) {
      con.TraverseForward(d -> {
        this.lst.InsertLast(d);
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
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

  // ...
  // head
  @Override
  public Data Head() {
    if (IsEmpty()) throw new IndexOutOfBoundsException("Index out of bounds: 0; Size: " + Size() + "!");
    return lst.GetAt(Natural.ZERO);
  }

  // Dequeue
  @Override
  public void Dequeue() {
    lst.RemoveAt(Natural.ZERO);
  }

  // HeadNDequeue
  @Override
  public Data HeadNDequeue() {
    Data headElement = Head();
    Dequeue();
    return headElement;
  }

  // Enqueue
  @Override
  public void Enqueue(Data element) {
    lst.InsertLast(element);
  }

}
