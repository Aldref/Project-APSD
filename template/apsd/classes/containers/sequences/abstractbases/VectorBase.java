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
  // NewVector
  abstract protected  void NewVector(Data [] array);

  abstract protected Vector<Data> NewVector(Natural newsize); // forse da togliere
  
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

  @Override
  public MutableForwardIterator<Data> FIterator() {

    class VecForwardIter implements MutableForwardIterator<Data> {

      private long index = 0;
      private final long size;

      public VecForwardIter() {
        this.size = arr != null ? arr.length : 0;
      }

      @Override
      public boolean IsValid() {
        return arr != null && index < size;
      }

      @Override
      public void Reset() {
        index = 0;
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
      public Data DataNNext() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        Data d = arr[(int) index];
        index++;
        return d;
      }
    }

    return new VecForwardIter();
  }

  // BackwardIterator
  @Override
  public MutableBackwardIterator<Data> BIterator() {

    class VecBackwardIter implements MutableBackwardIterator<Data> {

      private long index;

      public VecBackwardIter() {
        index = (arr == null ? -1 :  Size().ToLong() - 1);
      }

      @Override
      public boolean IsValid() {
        return arr != null && index >= 0;
      }

      @Override
      public void Reset() {
        index = (arr == null ? -1 :  Size().ToLong() - 1);
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
    long startIdx = ExcIfOutOfBound(start);
    long endIdx = ExcIfOutOfBound(end);
    if (startIdx > endIdx) throw new IndexOutOfBoundsException("Start index is greater than end index");
    long newSize = endIdx - startIdx + 1;
    Vector<Data> subVec = NewVector(Natural.Of(newSize));
    MutableSequence<Data> subSeq = (MutableSequence<Data>) subVec;
    for (long i = 0; i < newSize; i++) {
      Data value = GetAt(Natural.Of(startIdx + i));
      subSeq.SetAt(value, Natural.Of(i));  
    }
    return subSeq;
  }

  @Override
  public Vector<Data> SubVector(Natural start, Natural end) {
    MutableSequence<Data> subSeq = SubSequence(start, end);
    @SuppressWarnings("unchecked")
    Vector<Data> res = (Vector<Data>) subSeq;
    return res;
  }
}
