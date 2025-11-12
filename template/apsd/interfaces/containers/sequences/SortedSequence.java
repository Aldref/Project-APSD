package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data> extends Sequence<Data>, SortedIterableContainer<Data> {

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
  default Natural Search(Data element){
    if (element == null) return null;
    long high = Size().ToLong();
    long low = 0;;
    while (low < high) {
      long mid = (low + high) / 2;
      Data midElement = GetAt(Natural.Of(mid));
      int cmp = midElement.compareTo(element);
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
