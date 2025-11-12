package apsd.interfaces.containers.base;
import apsd.classes.utilities.Box;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container{ 

  // Remove
  boolean Remove(Data data);

  // RemoveAll
  default boolean RemoveAll(TraversableContainer<Data> container){
    if (container == null) return false;
    final Box<Boolean> removed = new Box<>(true);
    container.TraverseForward(dat->{
      if(!Remove(dat)){
        removed.Set(false);
        return true;
      }
      return false;
    });
    return removed.Get();
  }
  
  // RemoveSome
  default boolean RemoveSome(TraversableContainer<Data> container){
    if (container == null) return false;
    final Box<Boolean> removed = new Box<>(false);
    container.TraverseForward(dat->{
      if(Remove(dat)){
        removed.Set(true);
      }
      return false;
    });
    return removed.Get();
  }
}
