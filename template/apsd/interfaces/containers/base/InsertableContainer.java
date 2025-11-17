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
        if (!Insert(dat)){
          result.Set(false);
          return true;
        }
        return false;
      });
      return result.Get();
  }

  // InsertSome
  default boolean InsertSome(TraversableContainer<Data> container){
      if (container.IsEmpty()) return false;
      final Box<Boolean> result = new Box<>(false);
      container.TraverseForward(dat ->{
        if (Insert(dat)){
          result.Set(true);
          return false;
        }
        else{
          return true;
        }
      });
      return result.Get();
  }

}
