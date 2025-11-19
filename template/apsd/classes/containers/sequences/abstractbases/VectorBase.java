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

  abstract protected Vector<Data> NewVector(Natural newsize);
  
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
    for (int i = 0; i < arr.length; i++) {
      arr[i] = null;
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...
  // Capacity
  @Override
  public Natural Capacity() {
    return Natural.Of(arr.length);
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...

  // ForwardIterator
  @Override
  public MutableForwardIterator<Data> FIterator() {

    class VecForwardIter implements MutableForwardIterator<Data> {

      private long index;

      public VecForwardIter() {
        index = 0;
      }

      @Override
      public boolean IsValid() {
        return index < Size().ToLong();
      }

      @Override
      public void Reset() {
        index = 0;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return GetAt(Natural.Of(index));   
      }

      @Override
      public void SetCurrent(Data Data) {
        SetAt(Data,Natural.Of(index));
      }

      @Override
      public Data DataNNext() {
        Data d = GetAt(Natural.Of(index));
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
        index = (Size().ToLong() == 0 ? -1 : Size().ToLong() - 1);
      }

      @Override
      public boolean IsValid() {
        return index >= 0;
      }

      @Override
      public void Reset() {
        index = 0;
      }

      @Override
      public Data GetCurrent() {
        if (!IsValid()) throw new IllegalStateException("Iterator terminated");
        return GetAt(Natural.Of(index));
      }

      @Override
      public void SetCurrent(Data Data) {
        SetAt(Data, Natural.Of(index));
      }

      @Override
      public Data DataNPrev() {
        Data cur = GetCurrent();
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


  //Da rivedere
  @Override
  public Natural Size() {
    return Natural.Of(arr.length);
  }

  // non so se serve
  @Override
  public boolean Exists(Data data) {
    MutableForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      if ((it.GetCurrent() == null && data == null) ||
          (it.GetCurrent() != null && it.GetCurrent().equals(data))) {
        return true;
      }
      it.DataNNext();
    }
    return false;
  }

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
