package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { 

  // Union
  default void Union(Set<Data> set){
    if(set == null) return;
    set.TraverseForward(dat->{
      if (dat != null && !Exists(dat)) {
        Insert(dat);
      }
      return false;
    });
  }

  // Difference
  default void Difference(Set<Data> set){
    if (set == null) return;
    if (set == this) { 
      Clear();
      return;
    }
    set.TraverseForward(dat->{
      if (dat == null) {
        Remove(null);
      } else {
        while (Exists(dat)) {
          Remove(dat);
        }
      }
      return false;
    });
  }

  // Intersection
  default void Intersection(Set<Data> other) {
    if (other == null) return;
    if (other == this) return;
    Filter(elem -> other.Exists(elem));
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // IsEqual
  @Override
  default boolean IsEqual(IterableContainer<Data> other) {
    if (other == null || !Size().equals(other.Size())) return false;
    if (this == other) return true;
    return !this.TraverseForward(elem -> !other.Exists(elem));
  }

}
