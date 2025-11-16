package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;
import apsd.classes.containers.sequences.Vector; // forse

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data> {

  Data[] arr;

  // VectorBase
  protected VectorBase() {
    arr = null;
  }
  // NewVector
  abstract protected void NewVector(Data [] array);
  
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
  public void clear(){
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
  public MutableForwardIterator<Data> ForwardIterator() {

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
  public MutableBackwardIterator<Data> BackwardIterator() {

    class VecBackwardIter implements MutableBackwardIterator<Data> {

      private long index;

      public VecBackwardIter() {
        index = (size() == 0 ? -1 : size() - 1);
      }

      @Override
      public boolean IsValid() {
        return index >= 0;
      }

      @Override
      public Data DataNNext() {
        Data d = GetAt(Natural.Of(index));
        index--;
        return d;
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
    MutableSequence<Data> subSeq = new Vector<>();
    long newSize = endIdx - startIdx + 1;
    subSeq.ArrayAlloc(Natural.Of(newSize));
    for (long i = startIdx; i <= endIdx; i++) {
      subSeq.SetAt(GetAt(Natural.Of(i)), Natural.Of(i - startIdx));
    }
    return subSeq;
  }


}
