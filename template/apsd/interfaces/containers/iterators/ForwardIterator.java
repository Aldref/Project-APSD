package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> { 

  // Next
  void Next();

  default void Next(Natural n){
    if (n != null){
      for (Long i = 0; i< n.ToLong(); i++){
        if (!IsValid()) break;
        Next();
      }
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
