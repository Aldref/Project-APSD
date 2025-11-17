package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer<Data> extends ReallocableContainer<Data>{ // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  // Expand
  default void Expand(){
    Expand(Natural.ONE);
  };

  void Expand(Natural num);

  // Reduce
  default void Reduce(){
    Reduce(Natural.ONE);
  };

  void Reduce(Natural n);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  abstract Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Shrink() {
    if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink();
  }
  
  @Override
  default void Grow(Natural dim) {
      if (capacity().ToLong() == Integer.MAX_VALUE) {
        throw new IllegalStateException("Cannot grow beyond Integer.MAX_VALUE");
      }
      if (Size().ToLong() + dim.ToLong() >= capacity().ToLong()) {
          ReallocableContainer.super.Grow(dim);
      }
  }

}
