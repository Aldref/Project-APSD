package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer<Data>, InsertableContainer<Data> {

  // Head
  Data Head();
  // Dequeue
  Void Dequeue();

  // HeadNDequeue
  default Data HeadNDequeue(){
    Data headElement = Head();
    Dequeue();
    return headElement;
  }
  
  // Enqueue
  Void Enqueue(Data element);

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
  default Boolean Insert(Data element){ 
    Enqueue(element);
    return true;
    
  }
}
