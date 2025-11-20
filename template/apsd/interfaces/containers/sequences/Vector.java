package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data> {

  // ShiftLeft
  default void ShiftLeft(Natural pos) {
    ShiftLeft(pos, Natural.ONE);
  }

  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len > 0) {
      long iniwrt = idx;
      long wrt = iniwrt;
      for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
    }
  }

  // ShiftFirstLeft
  default void ShiftFirstLeft() {
    ShiftLeft(Natural.ZERO, Natural.ONE);
  }

  // ShiftLastLeft
  default void ShiftLastLeft(){
    if (Size().ToLong() == 0) return;
    ShiftLeft(Natural.Of(Size().ToLong()-1), Natural.ONE);
  }

  // ShiftRight
  default void ShiftRight(Natural pos) {
    ShiftRight(pos, Natural.ONE);
  }

  default void ShiftRight(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len > 0) {
      long inirdr = size - 1;
      long rdr = inirdr;
      for (long wrt = rdr + len; rdr >= idx; rdr--, wrt--) {
        if (wrt < Capacity().ToLong()) {
          Natural natrdr = Natural.Of(rdr);
          SetAt(GetAt(natrdr), Natural.Of(wrt));
          SetAt(null, natrdr);
        }
      }
    }
  }

  // ShiftFirstRight
  default void ShiftFirstRight() {
    ShiftRight(Natural.ZERO, Natural.ONE);
  }

  // ShiftLastRight
  default void ShiftLastRight(){
    if (Size().ToLong() <=1) return;
    ShiftRight(Natural.Of(Size().ToLong()-2), Natural.ONE);
  }

  Vector<Data> SubVector(Natural from, Natural to);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  @Override
  Natural Size();
}
