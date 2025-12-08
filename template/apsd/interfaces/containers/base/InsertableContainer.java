package apsd.interfaces.containers.base;
import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{ 

  // Insert
  boolean Insert(Data data);

  // InsertAll
  default boolean InsertAll(TraversableContainer<Data> container){
    if (container.IsEmpty()) return false;
    final Box<Boolean> result = new Box<>(true);
    container.TraverseForward(dat ->{
      result.Set(result.Get() && Insert(dat));
      return false;
    });
    return result.Get();
  }

  // InsertSome
  default boolean InsertSome(TraversableContainer<Data> container){
    if (container.IsEmpty()) return false;
    final Box<Boolean> result = new Box<>(false);
    container.TraverseForward(dat ->{
      result.Set(Insert(dat) || result.Get());
      return false;
    });
    return result.Get();
  }

}
