package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<Data> extends MembershipContainer<Data> { // Must extend MembershipContainer

  // TraverseForward
  boolean TraverseForward(Predicate<Data> fun);
  // TraverseBackward
  boolean TraverseBackward(Predicate<Data> fun);

  default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
    if (fun == null) return ini;
    if (ini == null) throw new NullPointerException();
    final Box<Acc> acc = new Box<>(ini);
    TraverseForward(dat -> { acc.Set(fun.Apply(dat, acc.Get())); return false; });
    return acc.Get();
  }

  // FoldBackward
  default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
    if (fun == null) return ini;
    if (ini == null) throw new NullPointerException();
    final Box<Acc> acc = new Box<>(ini);
    TraverseBackward(dat -> { acc.Set(fun.Apply(dat, acc.Get())); return false; });
    return acc.Get();
  }
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // Size
  @Override
  default Natural Size() { 
    final Box<Natural> count = new Box<>(Natural.ZERO);
    TraverseForward(dat -> { count.Set(count.Get().Increment()); return false; });
    return count.Get();
  }

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // Exists
  @Override
  default boolean Exists(Data data) {
    final Box<Boolean> found = new Box<>(false);
    TraverseForward(dat -> {
      if (dat == null ? data == null : dat.equals(data)) {
        found.Set(true);
        return true;   
      }
      return false;
    });
    return found.Get();
  }

}
