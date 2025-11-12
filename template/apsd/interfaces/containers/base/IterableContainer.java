package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> {

  //ForwardIterator
  ForwardIterator<Data> ForwardIterator();

  // BackwardIterator
  BackwardIterator<Data> BackwardIterator();

  // IsEqual
  default boolean IsEqual(IterableContainer<Data> num){
    if(num == null || !Size().equals(num.Size())) return false;

    ForwardIterator<Data> it1 = ForwardIterator();
    ForwardIterator<Data> it2 = num.ForwardIterator();
    while(it1.IsValid() && it2.IsValid()){
      //confronto per evitare il nullpointerexception
      Data d1 = it1.DataNNext();
      Data d2 = it2.DataNNext();
      if(d1 == null && d2 != null || d1 != null && !d1.equals(d2)) return false;
    }
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> fun){
    ForwardIterator<Data> it = ForwardIterator();
    if (it == null || fun == null) return false;
    while(it.IsValid()){
      if(fun.Apply(it.DataNNext())) { return true; }
    }
    return false;
  }

  @Override
  default boolean TraverseBackward(Predicate<Data> fun){
    BackwardIterator<Data> it = BackwardIterator();
    if (it == null || fun == null) return false;
    while(it.IsValid()){
      if(fun.Apply(it.DataNNext())) { return true; }
    }
    return false;
  }

}
