package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> {

  // FIterator
  FIterator<Data> FIterator();

  // BIterator
  BIterator<Data> BIterator();

  // IsEqual
  default boolean IsEqual(IterableContainer<Data> num){
    if(num == null || !Size().equals(num.Size())) return false;

    ForwardIterator<Data> it1 = FIterator();
    ForwardIterator<Data> it2 = num.FIterator();
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
    while(it.IsValid()){
      if(fun.Apply(it.DataNNext())) { return true; }
    }
    return false;
  }

  @Override
  default boolean TraverseBackward(Predicate<Data> fun){
    BackwardIterator<Data> it = BackwardIterator();
    while(it.IsValid()){
      if(fun.Apply(it.DataNNext())) { return true; }
    }
    return false;
  }

}
