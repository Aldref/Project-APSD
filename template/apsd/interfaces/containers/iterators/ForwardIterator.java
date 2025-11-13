package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> { 

  // Next
  default void Next(){
    if (IsValid()){
      DataNNext();
    }
  }

  default void Next(Natural n){
    if (n == null) return;
    for (long i = 0; i< n.ToLong() && IsValid(); i++){
      Next();
    }
  }
  
  // DataNNext
  Data DataNNext();
  
  // ForEachForward
  default boolean ForEachForward(Predicate<Data> fun) {
    if (fun != null) {
      while (IsValid()) {
        if (fun.Apply(DataNNext())) { return true; }
      }
    }
    return false;
  }

}
