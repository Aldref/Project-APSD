package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer<Data>, MutableSequence<Data> {

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
      Long inirdr = size - 1;
      Long rdr = inirdr;
      for (Long wrt = rdr + len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);

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

  // SubVector
  // default Vector<Data> SubVector(Natural from, Natural to){
  //   long idxfrom = ExcIfOutOfBound(from);
  //   long idxto = ExcIfOutOfBound(to);
  //   if (idxfrom > idxto) throw new IndexOutOfBoundsException("From index greater than to index: from " + idxfrom + ", to " + idxto + "!");
  //   //creare il subvector
  //   subvec.Realloc(Natural.Of(idxto - idxfrom + 1));
  //   for (long i = 0; i < idxto; i++) {
  //       Data element = GetAt(Natural.Of(idxfrom + i));
  //       subvec.SetAt(element, Natural.Of(i));
  //   }
  //   return subvec;
  // }
  Vector<Data> SubVector(Natural from, Natural to);
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  @Override
  Natural Size();
}
