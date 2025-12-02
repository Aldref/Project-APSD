package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.classes.utilities.Box;
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
    if(set == null) return;
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
  default void Intersection(Set<Data> set){
    Filter(dat->set.Exists(dat));
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // IsEqual
  @Override
  default boolean IsEqual(IterableContainer<Data> container) {
    if (container == null || !Size().equals(container.Size())) return false;
    boolean allFound = !TraverseForward(dat -> {
      Box<Boolean> found = new Box<>(false);
      container.TraverseForward(dat2 -> {
        if (dat == null ? dat2 == null : dat.equals(dat2)) {
          found.Set(true);
          return true;  
        }
        return false;
      });
      return !found.Get();
    });
    return allFound;
  }

}
