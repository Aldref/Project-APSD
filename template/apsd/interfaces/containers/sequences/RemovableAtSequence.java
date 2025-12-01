package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> { 

  // RemoveAt
  default void RemoveAt(Natural num){
    ExcIfOutOfBound(num);
    AtNRemove(num);                
  }

  // AtNRemove
  Data AtNRemove(Natural num);

  // RemoveFirst
  default void RemoveFirst(){
    if (IsEmpty()) return;
    RemoveAt(Natural.ZERO);
  }
  
  // FirstNRemove
  default Data FirstNRemove(){
    if (IsEmpty()) return null;
    return AtNRemove(Natural.ZERO);
  }

  // RemoveLast
  default void RemoveLast(){
    if (IsEmpty()) return;
    RemoveAt(Natural.Of(Size().ToLong() - 1));
  }
  
  // LastNRemove
  default Data LastNRemove(){
    if (IsEmpty()) return null;
    return AtNRemove(Natural.Of(Size().ToLong() - 1));
  }

}
