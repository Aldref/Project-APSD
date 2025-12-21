package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import org.junit.jupiter.api.*;

import apsd.classes.containers.collections.abstractcollections.WOrderedSet;
import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.collections.SortedChain;

abstract public class WOrderedSetITest extends WOrderedSetTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(10);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestInsert(0L, true);
    TestInsert(4L, false);
    TestInsert(3L, true);
    TestInsert(1L, true);
    TestInsert(3L, false);
    TestInsert(2L, true);
    TestInsert(0L, false);
    TestSize(5, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("WOrderedSet Basics")
  public class WOrderedSetBasics {

    @Test
    @DisplayName("Check starting from an Empty WOrderedSet")
    public void Empty() {
      AddTest(8);
      NewEmptyContainer();
      TestExists(0L, false);
      TestFilter(dat -> false);
      TestUnion(ThisContainer());
      TestIntersection(ThisContainer());
      TestDifference(ThisContainer());
      TestSize(0, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty WOrderedSet")
    public void NonEmpty() {
      AddTest(48);
      NewNonEmptyContainer();
      TestMin(0L);
      TestMax(4L);
      TestRemoveMin();
      TestMinNRemove(1L);
      TestInsert(-1L, true);
      TestInsert(1L, true);
      TestMin(-1L);
      TestMaxNRemove(4L);
      TestSize(4, false);
      TestInsert(6L, true);
      TestSize(5, false);
      TestMax(6L);
      TestInsert(7L, true);
      TestSize(6, false);
      TestExists(6L, true);
      TestExists(8L, false);
      TestExists(0L, false);
      TestExists(-1L, true);
      TestExists(2L, true);
      TestRemove(5L, false);
      TestRemove(2L, true);
      TestExists(5L, false);
      TestExists(2L, false);
      TestRemoveMax();
      TestPrintContent("");
      TestPredecessor(4L, 3L);
      TestPredecessor(5L, 3L);
      TestSuccessor(2L, 3L);
      TestSuccessor(4L, 6L);
      TestPredecessorNRemove(7L, 6L);
      TestSuccessorNRemove(0L, 1L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 2L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 2L);
      TestClear();
      TestSize(0, false);
      
      TestClear();
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestInsert(7L, true);
      TestInsert(8L, true);
      TestInsert(9L, true);
      TestInsert(10L, true);
      TestInsert(11L, true);
      TestSuccessorNRemove(3L, 4L);
      TestPredecessorNRemove(8L, 7L);
      TestPredecessorNRemove(6L, 5L);
      TestPredecessorNRemove(12L, 11L);
    }
  }

  @Nested
  @DisplayName("WOrderedSet Edge Cases")
  public class WOrderedSetEdgeCases {

    @Test
    @DisplayName("Single element set")
    public void SingleElement() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestMin(42L);
      TestMax(42L);
      TestExists(42L, true);
      TestPredecessor(42L, null);
      TestSuccessor(42L, null);
      TestPredecessor(50L, 42L);
      TestSuccessor(0L, 42L);
      TestRemove(42L, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
    }

    @Test
    @DisplayName("Predecessor and Successor with gaps")
    public void PredSuccWithGaps() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessor(15L, 10L);
      TestPredecessor(25L, 20L);
      TestPredecessor(35L, 30L);
      TestSuccessor(15L, 20L);
      TestSuccessor(25L, 30L);
      TestSuccessor(35L, 40L);
      TestPredecessor(5L, null);
      TestSuccessor(55L, null);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove min until empty")
    public void RemoveMinUntilEmpty() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestMinNRemove(1L);
      TestSize(2, false);
      TestMinNRemove(2L);
      TestSize(1, false);
      TestMinNRemove(3L);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Remove max until empty")
    public void RemoveMaxUntilEmpty() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestMaxNRemove(3L);
      TestSize(2, false);
      TestMaxNRemove(2L);
      TestSize(1, false);
      TestMaxNRemove(1L);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Consecutive numbers")
    public void ConsecutiveNumbers() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestPredecessor(3L, 2L);
      TestSuccessor(3L, 4L);
      TestPredecessor(1L, null);
      TestSuccessor(5L, null);
      TestRemove(3L, true);
      TestPredecessor(4L, 2L);
      TestSuccessor(2L, 4L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Negative numbers in set")
    public void NegativeNumbers() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(-10L, true);
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestMin(-10L);
      TestMax(10L);
      TestPredecessor(0L, -5L);
      TestSuccessor(0L, 5L);
      TestPredecessor(-7L, -10L);
      TestSuccessor(-7L, -5L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 0L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Duplicate insert rejection")
    public void DuplicateRejection() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, false);
      TestInsert(5L, false);
      TestSize(1, false);
      TestInsert(10L, true);
      TestInsert(10L, false);
      TestSize(2, false);
      TestInsert(5L, false);
      TestInsert(10L, false);
      TestSize(2, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove non-existent element")
    public void RemoveNonExistent() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(5L, true);
      TestRemove(2L, false);
      TestRemove(4L, false);
      TestRemove(0L, false);
      TestSize(3, false);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("WOrderedSet Stress Tests")
  public class WOrderedSetStressTests {

    @Test
    @DisplayName("Alternating min and max removal")
    public void AlternatingMinMaxRemoval() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestMinNRemove(1L);
      TestMaxNRemove(6L);
      TestMinNRemove(2L);
      TestMaxNRemove(5L);
      TestSize(2, false);
      TestMin(3L);
      TestMax(4L);
      TestMinNRemove(3L);
      TestMaxNRemove(4L);
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
    }

    @Test
    @DisplayName("Predecessor chain navigation")
    public void PredecessorChainNavigation() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessor(50L, 40L);
      TestPredecessor(40L, 30L);
      TestPredecessor(30L, 20L);
      TestPredecessor(20L, 10L);
      TestPredecessor(10L, null);
      TestPredecessor(45L, 40L);
      TestPredecessor(35L, 30L);
      TestPredecessor(25L, 20L);
      TestPredecessor(15L, 10L);
      TestPredecessor(5L, null);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Successor chain navigation")
    public void SuccessorChainNavigation() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestSuccessor(10L, 20L);
      TestSuccessor(20L, 30L);
      TestSuccessor(30L, 40L);
      TestSuccessor(40L, 50L);
      TestSuccessor(50L, null);
      TestSuccessor(5L, 10L);
      TestSuccessor(15L, 20L);
      TestSuccessor(25L, 30L);
      TestSuccessor(35L, 40L);
      TestSuccessor(55L, null);
      TestPrintContent("");
    }

    @Test
    @DisplayName("PredecessorNRemove chain")
    public void PredecessorNRemoveChain() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessorNRemove(55L, 50L);
      TestSize(4, false);
      TestPredecessorNRemove(45L, 40L);
      TestSize(3, false);
      TestPredecessorNRemove(35L, 30L);
      TestSize(2, false);
      TestPredecessorNRemove(25L, 20L);
      TestSize(1, false);
      TestPredecessorNRemove(15L, 10L);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("SuccessorNRemove chain")
    public void SuccessorNRemoveChain() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestSuccessorNRemove(5L, 10L);
      TestSize(4, false);
      TestSuccessorNRemove(15L, 20L);
      TestSize(3, false);
      TestSuccessorNRemove(25L, 30L);
      TestSize(2, false);
      TestSuccessorNRemove(35L, 40L);
      TestSize(1, false);
      TestSuccessorNRemove(45L, 50L);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Extreme values in ordered set")
    public void ExtremeValues() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(Long.MAX_VALUE, true);
      TestInsert(Long.MIN_VALUE, true);
      TestInsert(0L, true);
      TestMin(Long.MIN_VALUE);
      TestMax(Long.MAX_VALUE);
      TestPredecessor(0L, Long.MIN_VALUE);
      TestSuccessor(0L, Long.MAX_VALUE);
      TestExists(Long.MAX_VALUE, true);
      TestExists(Long.MIN_VALUE, true);
      TestMinNRemove(Long.MIN_VALUE);
      TestMin(0L);
      TestMaxNRemove(Long.MAX_VALUE);
      TestMax(0L);
      TestSize(1, false);
    }

    @Test
    @DisplayName("Large ordered set operations")
    public void LargeOrderedSetOperations() {
      AddTest(80);
      NewEmptyContainer();
      for (long i = 1L; i <= 50L; i++) {
        TestInsert(i, true);
      }
      TestSize(50, false);
      TestMin(1L);
      TestMax(50L);
      TestPredecessor(25L, 24L);
      TestSuccessor(25L, 26L);
      for (int i = 0; i < 10; i++) {
        TestRemoveMin();
      }
      TestSize(40, false);
      TestMin(11L);
      for (int i = 0; i < 10; i++) {
        TestRemoveMax();
      }
      TestSize(30, false);
      TestMax(40L);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 765L);
    }

    @Test
    @DisplayName("Clear and rebuild ordered set")
    public void ClearAndRebuild() {
      AddTest(19);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestClear();
      TestIsEmpty(true, false);
      TestMin(null);
      TestMax(null);
      TestInsert(100L, true);
      TestInsert(50L, true);
      TestInsert(150L, true);
      TestMin(50L);
      TestMax(150L);
      TestSize(3, false);
      TestPredecessor(100L, 50L);
      TestSuccessor(100L, 150L);
      TestPredecessor(50L, null);
      TestSuccessor(50L, 100L);
      TestPredecessor(150L, 100L);
      TestSuccessor(150L, null);
    }

  }

  @Nested
  @DisplayName("WOrderedSet Construction and Equality tests")
  public class WOrderedSetConstructionAndEqualityTests {
  
    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      // Constructing from a list
      AddTest(7);
      NewEmptyContainer();
      List<Long> list = new LLList<>();
      list.Insert(4L);
      list.Insert(2L);
      list.Insert(3L);
      list.Insert(2L);
      list.Insert(1L);
      WOrderedSet<Long> conFromList = new WOrderedSet<>(list);
      TestInsert(4L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestIsEqual(conFromList, true);
      TestPrintContent("");
      SortedChain<Long> sortedChain = new LLSortedChain<>(list);
      conFromList = new WOrderedSet<>(sortedChain, new LLList<>());
      TestIsEqual(conFromList, true);
      conFromList = new WOrderedSet<>(sortedChain, new LLList<>());
      TestIsEqual(conFromList, true);
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(4);
      List<Long> emptyList = new LLList<>();
      WOrderedSet<Long> conFromEmpty = new WOrderedSet<>(emptyList);
      NewEmptyContainer();
      TestIsEqual(conFromEmpty, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestMin(null);
    }

    @Test
    @DisplayName("Construction from sorted chain")
    public void ConstructionFromSortedChain() {
      AddTest(8);
      SortedChain<Long> sortedChain = new LLSortedChain<>();
      sortedChain.Insert(5L);
      sortedChain.Insert(3L);
      sortedChain.Insert(1L);
      sortedChain.Insert(4L);
      sortedChain.Insert(2L);
      WOrderedSet<Long> conFromChain = new WOrderedSet<>(sortedChain);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestIsEqual(conFromChain, true);
      TestSize(5, false);
      TestMin(1L);
    }

    @Test
    @DisplayName("Equality with same elements different order insertion")
    public void EqualityDifferentInsertionOrder() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WOrderedSet<Long> other = (WOrderedSet<Long>) GetNewEmptyContainer();
      other.Insert(3L);
      other.Insert(1L);
      other.Insert(2L);
      TestIsEqual(other, true);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WOrderedSet<Long> smaller = (WOrderedSet<Long>) GetNewEmptyContainer();
      smaller.Insert(1L);
      smaller.Insert(2L);
      TestIsEqual(smaller, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Inequality with different elements")
    public void InequalityDifferentElements() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WOrderedSet<Long> different = (WOrderedSet<Long>) GetNewEmptyContainer();
      different.Insert(1L);
      different.Insert(2L);
      different.Insert(4L);
      TestIsEqual(different, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality of two empty sets")
    public void EqualityEmptySets() {
      AddTest(3);
      NewEmptyContainer();
      WOrderedSet<Long> other = (WOrderedSet<Long>) GetNewEmptyContainer();
      TestIsEqual(other, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(7);
      List<Long> list = new LLList<>();
      list.Insert(10L);
      list.Insert(20L);
      list.Insert(30L);
      WOrderedSet<Long> original = new WOrderedSet<>(list);
      WOrderedSet<Long> copy = new WOrderedSet<>(original);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestIsEqual(original, true);
      TestIsEqual(copy, true);
      TestSize(3, false);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("WOrderedSet Null Testing")
  public class WOrderedSetNullTesting {

    @Test
    @DisplayName("Insert null element")
    public void InsertNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestSize(1, false);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Remove null element")
    public void RemoveNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestExists(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Predecessor with null")
    public void PredecessorNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestPredecessor(null, null);
    }

    @Test
    @DisplayName("Successor with null")
    public void SuccessorNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSuccessor(null, null);
    }

    @Test
    @DisplayName("PredecessorNRemove with null")
    public void PredecessorNRemoveNull() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestPredecessorNRemove(null, null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("SuccessorNRemove with null")
    public void SuccessorNRemoveNull() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSuccessorNRemove(null, null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Min and Max on empty set return null")
    public void MinMaxEmptyReturnsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestMin(null);
      TestMax(null);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("MinNRemove and MaxNRemove on empty set")
    public void MinMaxNRemoveEmpty() {
      AddTest(4);
      NewEmptyContainer();
      TestMinNRemove(null);
      TestMaxNRemove(null);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("FoldForward with null accumulator")
    public void FoldForwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFoldForward((dat, acc) -> dat, null, 3L);
    }

    @Test
    @DisplayName("FoldBackward with null accumulator")
    public void FoldBackwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFoldBackward((dat, acc) -> dat, null, 1L);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("Operations after inserting and removing maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestInsert(3L, true);
      TestExists(null, false);
      TestPredecessor(null, null);
      TestSuccessor(null, null);
      TestSize(3, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
    }

  }


}