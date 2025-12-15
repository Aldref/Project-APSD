package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import org.junit.jupiter.api.*;

import apsd.classes.utilities.Natural;

abstract public class XListITest extends XListTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(11);
    NewEmptyContainer();
    TestInsert(0L, true);
    TestInsertLast(4L);
    TestInsertFirst(5L);
    TestInsertLast(9L);
    TestInsertFirst(2L);
    TestInsertFirst(1L);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsertLast(1L);
    TestSize(9, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("List Basics")
  public class ListBasics {

    @Test
    @DisplayName("Check starting from an Empty List")
    public void Empty() {
      AddTest(12);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestSetFirst(0L, true);
      TestSetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestSetAt(0L, Natural.Of(2), true);
      TestExists(0L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
      TestRemove(0L, false);
      TestRemoveFirst();
      TestRemoveLast();
    }

    @Test
    @DisplayName("Check starting from a NonEmpty List")
    public void NonEmpty() {
      AddTest(22);
      NewNonEmptyContainer();
      TestGetFirst(5L, false);
      TestGetLast(1L, false);
      TestSetFirst(2L, false);
      TestSetLast(6L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestSetAt(3L, Natural.Of(4), false);
      TestExists(4L, true);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveFirst();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 25L);
      TestRemoveLast();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 19L);
      TestAtNRemove(Natural.Of(5), 4L, false);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 15L);
      TestSize(6, false);
      TestInsertAt(5L, Natural.Of(6), false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 20L);
      TestClear();
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("List Edge Cases")
  public class ListEdgeCases {

    @Test
    @DisplayName("Single element list operations")
    public void SingleElement() {
      AddTest(16);
      NewEmptyContainer();
      TestInsertFirst(42L);
      TestSize(1, false);
      TestGetFirst(42L, false);
      TestGetLast(42L, false);
      TestGetAt(Natural.ZERO, 42L, false);
      TestExists(42L, true);
      TestExists(0L, false);
      TestSetFirst(100L, false);
      TestGetFirst(100L, false);
      TestGetLast(100L, false);
      TestSetLast(200L, false);
      TestGetFirst(200L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 200L);
      TestRemoveFirst();
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Insert at various positions")
    public void InsertAtPositions() {
      AddTest(17);
      NewEmptyContainer();
      TestInsertFirst(3L);
      TestInsertFirst(1L);
      TestInsertLast(5L);
      TestInsertAt(2L, Natural.Of(1), false);
      TestInsertAt(4L, Natural.Of(3), false);
      TestPrintContent("");
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 4L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestSize(5, false);
      TestInsertAt(0L, Natural.ZERO, false);
      TestGetFirst(0L, false);
      TestInsertAt(6L, Natural.Of(6), false);
      TestGetLast(6L, false);
      TestSize(7, false);
    }

    @Test
    @DisplayName("Remove at various positions")
    public void RemoveAtPositions() {
      AddTest(15);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestInsertLast(4L);
      TestInsertLast(5L);
      TestPrintContent("");
      TestAtNRemove(Natural.Of(2), 3L, false);
      TestPrintContent("");
      TestSize(4, false);
      TestAtNRemove(Natural.ZERO, 1L, false);
      TestGetFirst(2L, false);
      TestAtNRemove(Natural.Of(2), 5L, false);
      TestGetLast(4L, false);
      TestSize(2, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove by value")
    public void RemoveByValue() {
      AddTest(13);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestInsertLast(2L);
      TestInsertLast(4L);
      TestRemove(2L, true);
      TestSize(4, false);
      TestExists(2L, true);
      TestRemove(2L, true);
      TestExists(2L, false);
      TestRemove(2L, false);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Duplicates handling")
    public void DuplicatesHandling() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestInsert(5L, true);
      TestSize(3, false);
      TestGetFirst(5L, false);
      TestGetLast(5L, false);
      TestExists(5L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestRemove(5L, true);
      TestSize(2, false);
      TestRemove(5L, true);
      TestRemove(5L, true);
      TestRemove(5L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Alternating insert first and last")
    public void AlternatingInserts() {
      AddTest(12);
      NewEmptyContainer();
      TestInsertFirst(3L);
      TestInsertLast(4L);
      TestInsertFirst(2L);
      TestInsertLast(5L);
      TestInsertFirst(1L);
      TestInsertLast(6L);
      TestPrintContent("");
      TestGetFirst(1L, false);
      TestGetLast(6L, false);
      TestSize(6, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 21L);
      TestGetAt(Natural.Of(3), 4L, false);
    }

    @Test
    @DisplayName("Remove first and last alternating")
    public void AlternatingRemoves() {
      AddTest(16);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestInsertLast(4L);
      TestInsertLast(5L);
      TestInsertLast(6L);
      TestRemoveFirst();
      TestGetFirst(2L, false);
      TestRemoveLast();
      TestGetLast(5L, false);
      TestRemoveFirst();
      TestGetFirst(3L, false);
      TestRemoveLast();
      TestGetLast(4L, false);
      TestSize(2, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Set at boundary positions")
    public void SetAtBoundaries() {
      AddTest(12);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestInsertLast(4L);
      TestInsertLast(5L);
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(50L, Natural.Of(4), false);
      TestGetFirst(10L, false);
      TestGetLast(50L, false);
      TestSetAt(0L, Natural.Of(5), true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 69L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Insert at out of bounds")
    public void InsertAtOutOfBounds() {
      AddTest(9);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestInsertAt(100L, Natural.Of(10), true);
      TestSize(3, false);
      TestInsertAt(0L, Natural.Of(4), true);
      TestSize(3, false);
      TestGetLast(3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove at out of bounds")
    public void RemoveAtOutOfBounds() {
      AddTest(9);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestAtNRemove(Natural.Of(10), 0L, true);
      TestSize(3, false);
      TestAtNRemove(Natural.Of(3), 0L, true);
      TestSize(3, false);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
    }

    @Test
    @DisplayName("Large list operations")
    public void LargeListOperations() {
      AddTest(82);
      NewEmptyContainer();
      for (long i = 1L; i <= 50L; i++) {
        TestInsertLast(i);
      }
      TestSize(50, false);
      TestGetFirst(1L, false);
      TestGetLast(50L, false);
      TestGetAt(Natural.Of(24), 25L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 1275L);
      for (int i = 0; i < 25; i++) {
        TestRemoveFirst();
      }
      TestSize(25, false);
      TestGetFirst(26L, false);
    }

    @Test
    @DisplayName("Negative numbers in list")
    public void NegativeNumbers() {
      AddTest(13);
      NewEmptyContainer();
      TestInsertLast(-10L);
      TestInsertLast(-5L);
      TestInsertLast(0L);
      TestInsertLast(5L);
      TestInsertLast(10L);
      TestGetFirst(-10L, false);
      TestGetLast(10L, false);
      TestExists(-5L, true);
      TestExists(-100L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 0L);
      TestRemove(-5L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 5L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Clear and reuse")
    public void ClearAndReuse() {
      AddTest(14);
      NewEmptyContainer();
      TestInsertLast(1L);
      TestInsertLast(2L);
      TestInsertLast(3L);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestInsertFirst(10L);
      TestInsertLast(20L);
      TestSize(2, false);
      TestGetFirst(10L, false);
      TestGetLast(20L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 30L);
      TestPrintContent("");
    }

  }

}
