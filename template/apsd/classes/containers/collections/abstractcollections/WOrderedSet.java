package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Wrapper ordered set implementation via ordered chain. */
public class WOrderedSet<Data extends Comparable<? super Data>> extends WOrderedSetBase<Data, VSortedChain<Data>> { // Must extend WOrderedSetBase

  // public WOrderedSet()
  public WOrderedSet() {
    super();
  }
  // public WOrderedSet(Chain<Data> chn)
  public WOrderedSet(VSortedChain<Data> chn) {
    super(chn);
  }
  // public WOrderedSet(TraversableContainer<Data> con)
  public WOrderedSet(TraversableContainer<Data> con) {
    super(con);
  }
  // public WOrderedSet(Chain<Data> chn, TraversableContainer<Data> con)
  public WOrderedSet(VSortedChain<Data> chn, TraversableContainer<Data> con) {
    super(chn, con);
  }

  // ...
  // ChainAlloc
  @Override
  protected void ChainAlloc() {
    chn = new VSortedChain<>();
  }
}
