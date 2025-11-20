package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data>{ 

  // Top
  Data Top();

  // Pop
  void Pop();

  // TopNPop
  default Data TopNPop(){
    if (IsEmpty()) return null;
    Data top = Top();
    Pop();
    return top;
  }
  
  // SwapTop
  default void SwapTop(Data data){
    if (IsEmpty()) return;
    Pop();
    Push(data);
  }

  //TopNSwap
  default Data TopNSwap(Data data){
    if (IsEmpty()) return null;
    Data top = Top();
    SwapTop(data);
    return top;
  }

  // Push
  void Push(Data data);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  @Override
  default void Clear(){
    while (!IsEmpty()){
      Pop();
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default boolean Insert(Data data){
    Push(data);
    return true;
  }

}
