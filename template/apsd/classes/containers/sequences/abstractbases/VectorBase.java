package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data> {

  protected Data[] arr;

  // VectorBase
  protected VectorBase() {
    arr = null;
  }

  public VectorBase(TraversableContainer<Data> con){
    if (con == null) throw new NullPointerException("Container cannot be null!");
    ArrayAlloc(con.Size());
    final MutableNatural idx = new MutableNatural();
    con.TraverseForward(dat -> {
      SetAt(dat, idx.GetNIncrement());
      return false;
    });
  }
  
  public VectorBase(Natural inisize){
    if (inisize == null) throw new NullPointerException("Initial size cannot be null!");
    ArrayAlloc(inisize);
  }

  protected VectorBase(Data[] array){
    arr = array;
  }

  // NewVector
  abstract protected VectorBase<Data> NewVector(Data [] array);
  
  //ArrayAlloc
  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...
  // Clear
  @Override
  public void Clear(){
    if (arr != null){
      for (int i = 0; i < this.Size().ToLong(); i++) {
        arr[i] = null;
      }
    }
    arr = null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
  // Capacity
  @Override
  public Natural Capacity() {
    if (arr == null) {
      return Natural.ZERO;
    }
    return Natural.Of(arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...
  // FIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {

    class VecForwardIter implements MutableForwardIterator<Data> {

      private long index = 0L;

      @Override
      public boolean IsValid() {
        return arr != null && index >= 0 && index < Size().ToLong();
      }

      @Override
      public void Reset() {
        index = 0L;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return arr[(int) index];
      }

      @Override
      public void SetCurrent(Data data) {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        arr[(int) index] = data;
      }

      @Override
      public void Next() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        index++;
      }

      @Override
      public Data DataNNext() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        Data data = arr[(int) index];
        index++;
        return data;
      }
    }

    return new VecForwardIter();
  }

  // BackwardIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {

    class VecBackwardIter implements MutableBackwardIterator<Data> {

      private long index = Size().ToLong() - 1; 

      @Override
      public boolean IsValid() {
        return arr != null && index >= 0 && index < Size().ToLong();
      }

      @Override
      public void Reset() {
        index = Size().ToLong() - 1;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return arr[(int) index];
      }

      @Override
      public void SetCurrent(Data data) {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        arr[(int) index] = data;
      }

      @Override
      public void Prev() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        index--;
      }

      @Override
      public Data DataNPrev() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        Data cur = arr[(int) index];
        index--;
        return cur;
      }
    }

    return new VecBackwardIter();
  }


  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
  // GetAt
  @Override
  abstract public Data GetAt(Natural num);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  // ...

  // SetAt
  @Override
  abstract public void SetAt(Data data, Natural num);

  // SubSequence
  @Override
  public MutableSequence<Data> SubSequence(Natural start, Natural end){
    long StartIdx = (start == null) ? -1 : start.ToLong();
    long EndIdx   = (end   == null) ? -1 : end.ToLong();
    if (StartIdx < 0 || EndIdx < 0 || StartIdx > EndIdx || EndIdx > Size().ToLong()) return null;
    int len = (int)(EndIdx - StartIdx);
    @SuppressWarnings("unchecked")
    Data[] subarr = (Data[]) new Object[len];
    for (int i = 0; i < len; i++) {
      subarr[i] = arr[(int)StartIdx + i];
    }
    return (MutableSequence<Data>) NewVector(subarr);
  }
  
}