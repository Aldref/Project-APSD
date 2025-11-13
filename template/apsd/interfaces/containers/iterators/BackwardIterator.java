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
      for (Long i = 0; i< n.ToLong(); i++){
        if (!IsValid()) break;
        Prev();
      }
    }
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
