package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data> {

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // ...
  @Override
  default boolean Exists(Data data){
    return Search(data) != null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  @Override
  default Natural Search(Data data){
    if (data == null) return null;
    long high = Size().ToLong();
    long low = 0;;
    while (low < high) {
      long mid = low + (high - low) / 2;
      Data midData = GetAt(Natural.Of(mid));
      int cmp = midData.compareTo(data);
      if (cmp < 0) {
        low = mid + 1;
      } else if (cmp > 0) {
        high = mid;
      } else {
        return Natural.Of(mid);
      }
    }
    return null;
  }

}
