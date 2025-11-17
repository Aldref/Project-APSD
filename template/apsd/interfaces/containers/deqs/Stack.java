package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data>{ 

  // Top
  Data Top();

  // Pop
  Void Pop();

  // TopNPop
  default Data TopNPop(){
    Data topElement = Top();
    Pop();
    return topElement;
  }
  
  // SwapTop
  default Void SwapTop(Data data){
    Pop();
    Push(data);
  }

  //TopNSwap
  default Data TopNSwap(Data data){
    Data topElement = Top();
    SwapTop(data);
    return topElement;
  }

  // Push
  Void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  @Override
  default Void Clear(){
    while (!IsEmpty()){
      Pop();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default Boolean Insert(Data data){
    Push(data);
    return true;
  }

}
