package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import org.junit.jupiter.api.*;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.classes.utilities.Natural;

abstract public class XSortedChainITest extends XSortedChainTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(11);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsert(9L, true);
    TestInsert(2L, true);
    TestInsert(1L, true);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsert(1L, true);
    TestSize(9, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("SortedChain Basics")
  public class SortedChainBasics {

    @Test
    @DisplayName("Check starting from an Empty SortedChain")
    public void Empty() {
      AddTest(9);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestExists(0L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
      TestRemove(0L, false);
      TestRemoveFirst();
      TestRemoveLast();
    }

    @Test
    @DisplayName("Check starting from a NonEmpty SortedChain")
    public void NonEmpty() {
      AddTest(19);
      NewNonEmptyContainer();
      TestGetFirst(0L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.Of(4), 2L, false);
      TestExists(4L, true);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveFirst();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveLast();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 18L);
      TestAtNRemove(Natural.Of(5), 5L, false);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 13L);
      TestSize(6, false);
      TestInsert(3L, true);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 16L);
      TestClear();
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Edge Cases")
  public class SortedChainEdgeCases {

    @Test
    @DisplayName("Insert maintains sorted order")
    public void InsertMaintainsOrder() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(3L, true);
      TestInsert(7L, true);
      TestInsert(1L, true);
      TestInsert(9L, true);
      TestGetFirst(1L, false);
      TestGetLast(9L, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 3L, false);
      TestGetAt(Natural.Of(2), 5L, false);
      TestGetAt(Natural.Of(3), 7L, false);
      TestGetAt(Natural.Of(4), 9L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("All duplicates")
    public void AllDuplicates() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestSize(4, false);
      TestGetFirst(5L, false);
      TestGetLast(5L, false);
      TestExists(5L, true);
      TestExists(4L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 20L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove all occurrences of duplicate")
    public void RemoveAllDuplicates() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(9L, true);
      TestSize(5, false);
      TestRemove(5L, true);
      TestExists(5L, true);
      TestRemove(5L, true);
      TestExists(5L, true);
      TestRemove(5L, true);
      TestExists(5L, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Single element operations")
    public void SingleElement() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestGetFirst(42L, false);
      TestGetLast(42L, false);
      TestGetAt(Natural.ZERO, 42L, false);
      TestExists(42L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 42L);
      TestRemoveFirst();
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Remove first and last alternating")
    public void RemoveAlternating() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestRemoveFirst();
      TestGetFirst(2L, false);
      TestRemoveLast();
      TestGetLast(4L, false);
      TestRemoveFirst();
      TestGetFirst(3L, false);
      TestRemoveLast();
      TestGetLast(3L, false);
      TestSize(1, false);
      TestRemoveFirst();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Negative numbers sorting")
    public void NegativeNumbers() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(-10L, true);
      TestInsert(5L, true);
      TestInsert(-1L, true);
      TestGetFirst(-10L, false);
      TestGetLast(5L, false);
      TestGetAt(Natural.Of(2), -1L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, -11L);
      TestPrintContent("");
      TestSize(5, false);
    }

    @Test
    @DisplayName("Insert at boundaries")
    public void InsertAtBoundaries() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(50L, true);
      TestInsert(100L, true);
      TestInsert(0L, true);
      TestGetFirst(0L, false);
      TestGetLast(100L, false);
      TestInsert(-100L, true);
      TestGetFirst(-100L, false);
      TestInsert(200L, true);
      TestGetLast(200L, false);
      TestSize(5, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Large sequence of inserts")
    public void LargeSequence() {
      AddTest(106);
      NewEmptyContainer();
      for (long i = 100L; i >= 1L; i--) {
        TestInsert(i, true);
      }
      TestSize(100, false);
      TestGetFirst(1L, false);
      TestGetLast(100L, false);
      TestGetAt(Natural.Of(49), 50L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 5050L);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("SortedChain Stress Tests")
  public class SortedChainStressTests {

    @Test
    @DisplayName("Interleaved duplicates")
    public void InterleavedDuplicates() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestSize(7, false);
      TestGetFirst(1L, false);
      TestGetLast(3L, false);
      TestGetAt(Natural.Of(0), 1L, false);
      TestGetAt(Natural.Of(1), 1L, false);
      TestGetAt(Natural.Of(2), 1L, false);
      TestGetAt(Natural.Of(3), 2L, false);
      TestGetAt(Natural.Of(4), 2L, false);
      TestGetAt(Natural.Of(5), 3L, false);
      TestGetAt(Natural.Of(6), 3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove from middle")
    public void RemoveFromMiddle() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestAtNRemove(Natural.Of(2), 3L, false);
      TestSize(4, false);
      TestGetAt(Natural.Of(2), 4L, false);
      TestAtNRemove(Natural.Of(1), 2L, false);
      TestSize(3, false);
      TestGetAt(Natural.Of(1), 4L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 10L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Predecessor and successor find")
    public void PredSuccFind() {
      AddTest(16);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestInsert(40L, true);
      TestInsert(50L, true);
      TestPredecessor(25L, 20L);
      TestPredecessor(30L, 20L);
      TestPredecessor(10L, null);
      TestPredecessor(5L, null);
      TestSuccessor(25L, 30L);
      TestSuccessor(30L, 40L);
      TestSuccessor(50L, null);
      TestSuccessor(55L, null);
      TestPredecessor(35L, 30L);
      TestSuccessor(35L, 40L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove until empty then refill")
    public void RemoveUntilEmptyThenRefill() {
      AddTest(18);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestRemoveFirst();
      TestRemoveFirst();
      TestRemoveFirst();
      TestIsEmpty(true, false);
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(15L, true);
      TestSize(3, false);
      TestGetFirst(10L, false);
      TestGetAt(Natural.Of(1), 15L, false);
      TestGetLast(20L, false);
      TestRemoveLast();
      TestRemoveLast();
      TestRemoveLast();
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Extreme values")
    public void ExtremeValues() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(Long.MAX_VALUE, true);
      TestInsert(Long.MIN_VALUE, true);
      TestInsert(0L, true);
      TestInsert(-1L, true);
      TestInsert(1L, true);
      TestGetFirst(Long.MIN_VALUE, false);
      TestGetLast(Long.MAX_VALUE, false);
      TestGetAt(Natural.Of(2), 0L, false);
      TestExists(Long.MAX_VALUE, true);
      TestExists(Long.MIN_VALUE, true);
      TestSize(5, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Consecutive removes")
    public void ConsecutiveRemoves() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestRemove(3L, true);
      TestRemove(1L, true);
      TestRemove(5L, true);
      TestSize(2, false);
      TestGetFirst(2L, false);
      TestGetLast(4L, false);
      TestRemove(2L, true);
      TestRemove(4L, true);
      TestIsEmpty(true, false);
      TestRemove(1L, false);
    }

    @Test
    @DisplayName("Many duplicates at boundaries")
    public void ManyDuplicatesAtBoundaries() {
      AddTest(17);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(1L, true);
      TestInsert(1L, true);
      TestInsert(100L, true);
      TestInsert(100L, true);
      TestInsert(100L, true);
      TestInsert(50L, true);
      TestSize(7, false);
      TestGetFirst(1L, false);
      TestGetLast(100L, false);
      TestRemoveFirst();
      TestGetFirst(1L, false);
      TestRemoveFirst();
      TestGetFirst(1L, false);
      TestRemoveLast();
      TestGetLast(100L, false);
      TestSize(4, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Construction and Equality Tests")
  public class SortedChainConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(7);
      NewEmptyContainer();
      LLList<Long> list = new LLList<>();
      list.Insert(4L);
      list.Insert(2L);
      list.Insert(3L);
      list.Insert(1L);
      LLSortedChain<Long> conFromList = new LLSortedChain<>(list);
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestIsEqual(conFromList, true);
      TestSize(4, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(4);
      LLList<Long> emptyList = new LLList<>();
      LLSortedChain<Long> conFromEmpty = new LLSortedChain<>(emptyList);
      NewEmptyContainer();
      TestIsEqual(conFromEmpty, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Construction from unsorted list produces sorted chain")
    public void ConstructionFromUnsortedList() {
      AddTest(8);
      LLList<Long> unsortedList = new LLList<>();
      unsortedList.InsertLast(5L);
      unsortedList.InsertLast(1L);
      unsortedList.InsertLast(4L);
      unsortedList.InsertLast(2L);
      unsortedList.InsertLast(3L);
      LLSortedChain<Long> conFromList = new LLSortedChain<>(unsortedList);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestIsEqual(conFromList, true);
      TestGetFirst(1L, false);
      TestGetLast(5L, false);
    }

    @Test
    @DisplayName("Equality with same elements")
    public void EqualitySameElements() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      LLSortedChain<Long> other = new LLSortedChain<>();
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
      
      LLSortedChain<Long> smaller = new LLSortedChain<>();
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
      
      LLSortedChain<Long> different = new LLSortedChain<>();
      different.Insert(1L);
      different.Insert(2L);
      different.Insert(4L);
      TestIsEqual(different, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality of two empty chains")
    public void EqualityEmptyChains() {
      AddTest(3);
      NewEmptyContainer();
      LLSortedChain<Long> other = new LLSortedChain<>();
      TestIsEqual(other, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(7);
      LLSortedChain<Long> original = new LLSortedChain<>();
      original.Insert(30L);
      original.Insert(10L);
      original.Insert(20L);
      LLSortedChain<Long> copy = new LLSortedChain<>(original);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestIsEqual(original, true);
      TestIsEqual(copy, true);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality with duplicates")
    public void EqualityWithDuplicates() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(1L, true);
      TestInsert(2L, true);
      
      LLSortedChain<Long> other = new LLSortedChain<>();
      other.Insert(2L);
      other.Insert(1L);
      other.Insert(2L);
      other.Insert(1L);
      TestIsEqual(other, true);
      TestSize(4, false);
    }

  }

  @Nested
  @DisplayName("SortedChain Null Testing")
  public class SortedChainNullTesting {

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
    @DisplayName("GetAt with null Natural")
    public void GetAtNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestGetAt(null, 0L, true);
      TestSize(2, false);
    }

    @Test
    @DisplayName("AtNRemove with null Natural")
    public void AtNRemoveNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestAtNRemove(null, 0L, true);
      TestSize(2, false);
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
    @DisplayName("Filter with null predicate")
    public void FilterWithNullPredicate() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Operations with null maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestInsert(3L, true);
      TestExists(null, false);
      TestSize(3, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
    }

    @Test
    @DisplayName("Empty chain null operations")
    public void EmptyChainNullOperations() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(null, false);
      TestRemove(null, false);
      TestExists(null, false);
      TestGetAt(null, 0L, true);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

  }

}
