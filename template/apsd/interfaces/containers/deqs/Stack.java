package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer<Data>, InsertableContainer<Data>{ 

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
  default Void SwapTop(Data num){
    Pop();
    Push(num);
  }

  //TopNSwap
  default Data TopNSwap(Data num){
    Data topElement = Top();
    SwapTop(num);
    return topElement;
  }

  // Push
  Void Push(Data num);

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
  default Boolean Insert(Data element){
    Push(element);
    return true;
  }

}
