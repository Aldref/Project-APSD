package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> { // Must extend LinearVectorBase and implement DynVector

  protected long size = 0L;

  // DynLinearVectorBase
  protected DynLinearVectorBase(TraversableContainer<Data> con) {
    super(con);
    this.size = con.Size().ToLong();
  }

  @Override
  protected void NewVector(Data[] arr) {
    this.arr = arr;
  }

  @Override
  protected abstract DynVector<Data> NewVector(Natural newsize);

  // ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural newcapacity) {
    super.ArrayAlloc(newcapacity);
    size = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...
  // Size
  @Override
  public Natural Size() {
    return Natural.Of(size);
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear() {
    super.Clear();
    size = 0L;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  // ...
  // Realloc
  @Override
  public void Realloc(Natural newsize) {
    super.Realloc(newsize);
    if (size > newsize.ToLong()) {
      size = newsize.ToLong();
    }
  }
  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
  // Expand
  @Override
  public void Expand(Natural num) {
    Natural oldSize = Natural.Of(size);
    Natural newSize = Natural.Of(size + num.ToLong());
    if (newSize.ToLong() > Capacity().ToLong()) {
      Grow(Natural.Of(newSize.ToLong() - Capacity().ToLong()));
    }
    size = newSize.ToLong();
  }

  // Reduce
  @Override
  public void Reduce(Natural n) {
    if (n.ToLong() > size) {
      throw new IllegalArgumentException("Cannot reduce more than current size");
    }
    size -= n.ToLong();
  }
}