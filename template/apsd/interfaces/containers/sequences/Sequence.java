package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data> { 

  // GetAt
  default Data GetAt(Natural num){
    long idx = ExcIfOutOfBound(num);
    ForwardIterator<Data> it = ForwardIterator();
    it.Next(Natural.Of(idx));
    return it.GetCurrent();
  }

  // GetFirst
  default Data GetFirst() {
    return GetAt(Natural.ZERO);
  }

  // GetLast
  default Data GetLast() {
    long size = Size().ToLong();
    if (size == 0) throw new IndexOutOfBoundsException("Sequence is empty");
    return GetAt(Natural.Of(size - 1));
  }

  // Search
  default Natural Search(Data element) {
    Box<Natural> index = Box.Of(Natural.ZERO);
    boolean found = TraverseForward(data -> {
      if (data == null && element == null || data != null && data.equals(element)) {
        return true;
      } else {
        index.Set(Natural.Of(index.Get().ToLong() + 1));
        return false;
      }
    });
    return found ? index.Get() : null;
  }

  // IsInBound
  default boolean IsInBound(Natural num){
    if (num == null) return false;
    long idx = num.ToLong();
    return idx < Size().ToLong();
  } 

  // ExcIfOutOfBound
  default long ExcIfOutOfBound(Natural num) {
    if (num == null) throw new NullPointerException("Natural number cannot be null!");
    long idx = num.ToLong();
    if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
    return idx;
  }

  // SubSequence
  Sequence<Data> SubSequence(Natural start, Natural end);
}
