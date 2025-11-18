package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;

/** Interface: IterableContainer con supporto all'iterazione mutabile. */
public interface MutableIterableContainer<Data> extends IterableContainer<Data>{ 

  // ...
  MutableForwardIterator<Data> FIterator();

  MutableBackwardIterator<Data> BIterator();
}
