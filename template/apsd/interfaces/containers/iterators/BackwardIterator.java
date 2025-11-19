package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data>{ 

  // Prev
  default void Prev(){
    if (IsValid()){
      DataNPrev();
    }
  }

  default void Prev(Natural n){
    if (n != null){
      for (long i = 0; i< n.ToLong(); i++){
        if (!IsValid()) break;
        Prev();
      }
    }
  }

  default void Prev(long n) {
    if (n <= 0) return;
    Prev(Natural.Of(n));
  }
  
  // DataNPrev
  Data DataNPrev();

  // ForEachBackward
  default Boolean ForEachBackward(Predicate<Data> fun) {
    if (fun != null){
      while (IsValid()) {
        if (fun.Apply(DataNPrev())) { return true; }
      }
    }
    return false;
  } 

}
