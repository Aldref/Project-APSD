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
    RemoveAt(Natural.ZERO);
  }
  
  // FirstNRemove
  default Data FirstNRemove(){
    return AtNRemove(Natural.ZERO);
  }

  // RemoveLast
  default void RemoveLast(){
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    RemoveAt(Natural.Of(size - 1));
  }
  // LastNRemove
  default Data LastNRemove(){
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    return AtNRemove(Natural.Of(size - 1));
  }

}
