package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import org.junit.jupiter.api.*;

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

}
