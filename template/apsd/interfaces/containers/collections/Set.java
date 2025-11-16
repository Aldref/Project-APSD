package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { 

  // Union
  default void Union(Set<Data> set){
    if(set == null) return;
    set.TraverseForward(dat->{
      Insert(dat); return false;
    });
  }

  // Difference
  default void Difference(Set<Data> set){
    if(set == null) return;
    set.TraverseForward(dat->{
      Remove(dat); return false;
    });
  }

  // Intersection
  default void Intersection(Set<Data> set){
    if(set == null){
      Clear();
      return;
    }
    set.TraverseForward(dat->{
      if (!Contains(dat)) Remove(dat);
      return false;
    });
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  @Override
  default boolean IsEqual(IterableContainer<Data> container) {
    if (container == null || !Size().equals(container.Size())) return false;
    return !TraverseForward(dat -> {
      if (!container.Contains(dat)) return true;
      return false;
    });
  }

}
