package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

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
  default boolean IsEqual(IterableContainer<Data> data){
    if (data == null) return false;
      ForwardIterator<Data> it1 = FIterator();
      ForwardIterator<Data> it2 = data.FIterator();
    if (it1 == null || it2 == null) return false;
    while (it1.IsValid() && it2.IsValid()) {
      Data data1 = it1.DataNNext();
      Data data2 = it2.DataNNext();
      if (data1 == null ? data2 != null : !data1.equals(data2)) return false;
    }
    return !it1.IsValid() && !it2.IsValid();
  }

}
