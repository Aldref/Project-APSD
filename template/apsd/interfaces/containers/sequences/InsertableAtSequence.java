package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<Data> extends Sequence<Data> { 

  // InsertAt
  void InsertAt(Data element, Natural num);

  // InsertFirst
  default void InsertFirst(Data element){
    InsertAt(element, Natural.ZERO);
  }
  // InsertLast
  default void InsertLast(Data element){
    long size = Size().ToLong();
    InsertAt(element, Natural.Of(size));
  }

}
