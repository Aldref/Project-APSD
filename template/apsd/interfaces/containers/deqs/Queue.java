package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> {

  // Head
  Data Head();
  // Dequeue
  Void Dequeue();

  // HeadNDequeue
  default Data HeadNDequeue(){
    if (IsEmpty()) return null;
    Data headElement = Head();
    Dequeue();
    return headElement;
  }
  
  // Enqueue
  Void Enqueue(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  @Override
  default Void Clear(){
    while (!IsEmpty()){
      Dequeue();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default Boolean Insert(Data data){ 
    Enqueue(data);
    return true;
    
  }
}
