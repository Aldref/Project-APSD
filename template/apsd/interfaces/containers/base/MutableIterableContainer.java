package apsd.interfaces.containers.base;

// import apsd.interfaces.containers.iterators.MutableBackwardIterator;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: IterableContainer con supporto all'iterazione mutabile. */
public interface MutableIterableContainer<Data> extends InsertableContainer<Data>{ 

  // ...
  FMutIter<Data> FIterator();

  BMutIter<Data> BIterator();
}
