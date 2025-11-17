package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.classes.utilities.Box;
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
    if (set == null) {
        Clear();
        return;
    }
    TraverseForward(dat -> {
        Box<Boolean> present = new Box<>(false);
        set.TraverseForward(x -> {
            boolean eq = (x == null ? dat == null : x.equals(dat));
            if (eq) {
                present.Set(true);
                return true; 
            }
            return false;
        });
        if (!present.Get()) {
            Remove(dat); 
        }
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
    boolean allFound = !TraverseForward(dat -> {
        Box<Boolean> found = new Box<>(false);
        container.TraverseForward(x -> {
          boolean eq = (x == null ? dat == null : x.equals(dat));
          if (eq) {
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
