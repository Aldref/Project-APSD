package zapsdtest.simpletest.apsd.classes.containers.sequences.generic;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

import org.junit.jupiter.api.*;

abstract public class XDynVectorITest extends XDynVectorTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(13);
    NewEmptyContainer();
    TestSetFirst(0L, true);
    TestExpandWithNumber(Natural.Of(1));
    TestSetFirst(0L, false);
    TestExpandWithNumber(Natural.Of(4));
    TestSize(5, false);
    TestSwap(Natural.ZERO, Natural.Of(3), false);
    TestSetFirst(4L, false);
    TestSetAt(3L, Natural.Of(1), false);
    TestSetAt(2L, Natural.Of(2), false);
    TestSetAt(1L, Natural.Of(5), true);
    TestSetLast(1L, false);
    TestSize(5, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("Vector Basics")
  public class VectorBasics {

    @Test
    @DisplayName("Check starting from an Empty Vector")
    public void Empty() {
      AddTest(9);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestSetFirst(0L, true);
      TestSetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestSetAt(0L, Natural.Of(2), true);
      TestExists(4L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty Vector")
    public void NonEmpty() {
      AddTest(25);
      NewNonEmptyContainer();
      TestGetFirst(4L, false);
      TestGetLast(1L, false);
      TestSetFirst(5L, false);
      TestSetLast(4L, false);
      TestExists(4L, true);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 14L);
      TestFoldForward((dat, acc) -> acc * dat, 1L, 0L);
      TestGetAt(Natural.Of(3), 0L, false);
      TestSetAt(1L, Natural.Of(3), false);
      TestFoldForward((dat, acc) -> acc * dat, 1L, 120L);
      TestPrintContent("");
      TestRealloc(Natural.Of(2));
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc * dat, 1L, 15L);
      TestRealloc(Natural.Of(4));
      TestShiftRight(Natural.ONE, Natural.Of(2));
      TestPrintContent("");
      TestSize(4L, false);
      TestShiftLeft(Natural.ZERO, Natural.ONE);
      TestSize(3L, false);
      TestShiftRight(Natural.ZERO, Natural.Of(2));
      TestSize(5L, false);
      TestPrintContent("");
      TestClear();
      TestSize(0L, false);
    }

  }

  @Nested
  @DisplayName("DynVector Edge Cases")
  public class DynVectorEdgeCases {

    @Test
    @DisplayName("Expand from zero")
    public void ExpandFromZero() {
      AddTest(8);
      NewEmptyContainer();
      TestSize(0, false);
      TestExpandWithNumber(Natural.Of(5));
      TestSize(5, false);
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(4), false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(4), 20L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Multiple expansions")
    public void MultipleExpansions() {
      AddTest(10);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(2));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestExpandWithNumber(Natural.Of(3));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestSetAt(5L, Natural.Of(4), false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Shrink preserves front elements")
    public void ShrinkPreservesFront() {
      AddTest(8);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestRealloc(Natural.Of(2));
      TestSize(2, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
    }

    @Test
    @DisplayName("ShiftLeft reduces size")
    public void ShiftLeftReducesSize() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestSize(5, false);
      TestShiftLeft(Natural.Of(2), Natural.Of(2));
      TestSize(3, false);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 8L);
    }

    @Test
    @DisplayName("ShiftRight increases size")
    public void ShiftRightIncreasesSize() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSize(3, false);
      TestShiftRight(Natural.ONE, Natural.Of(2));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(3), 2L, false);
      TestGetAt(Natural.Of(4), 3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Insert at with expansion")
    public void InsertAtWithExpansion() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(3L, Natural.Of(1), false);
      TestSetAt(5L, Natural.Of(2), false);
      TestInsertAtWithAutoExpansion(Natural.ONE, 2L);
      TestSize(4, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 5L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove at with reduction")
    public void RemoveAtWithReduction() {
      AddTest(12);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestAtNRemoveWithAutoReduction(Natural.Of(2), 3L);
      TestSize(4, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 4L, false);
      TestGetAt(Natural.Of(3), 5L, false);
    }

    @Test
    @DisplayName("Reduce by number")
    public void ReduceByNumber() {
      AddTest(8);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(10));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSize(10, false);
      TestReduceWithNumber(Natural.Of(3));
      TestSize(7, false);
      TestGetAt(Natural.ZERO, 1L, false);
    }

    @Test
    @DisplayName("Reduce to zero")
    public void ReduceToZero() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSize(5, false);
      TestReduceWithNumber(Natural.Of(5));
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Reduce by zero does nothing")
    public void ReduceByZero() {
      AddTest(6);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(42L, Natural.Of(2), false);
      TestSize(5, false);
      TestReduceWithNumber(Natural.ZERO);
      TestSize(5, false);
    }

    @Test
    @DisplayName("Multiple reduce operations")
    public void MultipleReduces() {
      AddTest(10);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(10));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSize(10, false);
      TestReduceWithNumber(Natural.Of(2));
      TestSize(8, false);
      TestReduceWithNumber(Natural.Of(3));
      TestSize(5, false);
      TestReduceWithNumber(Natural.Of(5));
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Expand then reduce preserves front elements")
    public void ExpandThenReduce() {
      AddTest(10);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestSetAt(40L, Natural.Of(3), false);
      TestSetAt(50L, Natural.Of(4), false);
      TestReduceWithNumber(Natural.Of(2));
      TestSize(3, false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(2), 30L, false);
    }

  }

  @Nested
  @DisplayName("DynVector Stress Tests")
  public class DynVectorStressTests {

    @Test
    @DisplayName("Rapid expand and shrink cycles")
    public void RapidExpandShrinkCycles() {
      AddTest(21);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(10));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(9), false);
      TestSize(10, false);
      TestRealloc(Natural.Of(5));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestExpandWithNumber(Natural.Of(15));
      TestSize(20, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestRealloc(Natural.Of(1));
      TestSize(1, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestExpandWithNumber(Natural.Of(99));
      TestSetAt(100L, Natural.Of(99), false);
      TestRealloc(Natural.Of(50));
      TestSize(50, false);
      TestRealloc(Natural.ZERO);
      TestIsEmpty(true, false);
      TestExpandWithNumber(Natural.Of(3));
      TestSize(3, false);
    }

    @Test
    @DisplayName("Insert at beginning repeatedly")
    public void InsertAtBeginningRepeatedly() {
      AddTest(14);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(1));
      TestSetAt(5L, Natural.ZERO, false);
      TestInsertAtWithAutoExpansion(Natural.ZERO, 4L);
      TestInsertAtWithAutoExpansion(Natural.ZERO, 3L);
      TestInsertAtWithAutoExpansion(Natural.ZERO, 2L);
      TestInsertAtWithAutoExpansion(Natural.ZERO, 1L);
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 4L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Insert at end repeatedly")
    public void InsertAtEndRepeatedly() {
      AddTest(14);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(1));
      TestSetAt(1L, Natural.ZERO, false);
      TestInsertAtWithAutoExpansion(Natural.ONE, 2L);
      TestInsertAtWithAutoExpansion(Natural.Of(2), 3L);
      TestInsertAtWithAutoExpansion(Natural.Of(3), 4L);
      TestInsertAtWithAutoExpansion(Natural.Of(4), 5L);
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 4L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove from beginning repeatedly")
    public void RemoveFromBeginningRepeatedly() {
      AddTest(14);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestAtNRemoveWithAutoReduction(Natural.ZERO, 1L);
      TestAtNRemoveWithAutoReduction(Natural.ZERO, 2L);
      TestAtNRemoveWithAutoReduction(Natural.ZERO, 3L);
      TestSize(2, false);
      TestGetAt(Natural.ZERO, 4L, false);
      TestGetAt(Natural.Of(1), 5L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 9L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove from end repeatedly")
    public void RemoveFromEndRepeatedly() {
      AddTest(14);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestAtNRemoveWithAutoReduction(Natural.Of(4), 5L);
      TestAtNRemoveWithAutoReduction(Natural.Of(3), 4L);
      TestAtNRemoveWithAutoReduction(Natural.Of(2), 3L);
      TestSize(2, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 3L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Alternating insert and remove")
    public void AlternatingInsertRemove() {
      AddTest(17);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestInsertAtWithAutoExpansion(Natural.ONE, 100L);
      TestSize(4, false);
      TestAtNRemoveWithAutoReduction(Natural.ONE, 100L);
      TestSize(3, false);
      TestInsertAtWithAutoExpansion(Natural.Of(2), 200L);
      TestSize(4, false);
      TestAtNRemoveWithAutoReduction(Natural.Of(2), 200L);
      TestSize(3, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Expand with zero")
    public void ExpandWithZero() {
      AddTest(8);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestExpandWithNumber(Natural.ZERO);
      TestSize(3, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("ShiftRight at last position")
    public void ShiftRightAtLastPosition() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestShiftRight(Natural.Of(2), Natural.Of(2));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(1), 2L, false);
      TestGetAt(Natural.Of(4), 3L, false);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> { if (dat != null) return acc + dat; else return acc; }, 0L, 6L);
    }

    @Test
    @DisplayName("ShiftLeft at first position")
    public void ShiftLeftAtFirstPosition() {
      AddTest(11);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestShiftLeft(Natural.ZERO, Natural.Of(2));
      TestSize(3, false);
      TestGetAt(Natural.ZERO, 3L, false);
      TestGetAt(Natural.Of(1), 4L, false);
      TestGetAt(Natural.Of(2), 5L, false);
    }

    @Test
    @DisplayName("Multiple operations preserve data integrity")
    public void DataIntegrityTest() {
      AddTest(22);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestSetAt(40L, Natural.Of(3), false);
      TestSetAt(50L, Natural.Of(4), false);
      TestInsertAtWithAutoExpansion(Natural.Of(2), 25L);
      TestSize(6, false);
      TestGetAt(Natural.Of(2), 25L, false);
      TestGetAt(Natural.Of(3), 30L, false);
      TestAtNRemoveWithAutoReduction(Natural.Of(2), 25L);
      TestSize(5, false);
      TestGetAt(Natural.Of(2), 30L, false);
      TestShiftRight(Natural.Of(1), Natural.Of(2));
      TestSize(7, false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(3), 20L, false);
      TestShiftLeft(Natural.Of(1), Natural.Of(2));
      TestSize(5, false);
      TestGetAt(Natural.Of(1), 20L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 150L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Extreme values in dynamic vector")
    public void ExtremeValues() {
      AddTest(12);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(Long.MAX_VALUE, Natural.ZERO, false);
      TestSetAt(Long.MIN_VALUE, Natural.Of(1), false);
      TestSetAt(0L, Natural.Of(2), false);
      TestSetAt(-1L, Natural.Of(3), false);
      TestSetAt(1L, Natural.Of(4), false);
      TestGetAt(Natural.ZERO, Long.MAX_VALUE, false);
      TestGetAt(Natural.Of(1), Long.MIN_VALUE, false);
      TestExists(Long.MAX_VALUE, true);
      TestExists(Long.MIN_VALUE, true);
      TestAtNRemoveWithAutoReduction(Natural.ZERO, Long.MAX_VALUE);
      TestSize(4, false);
    }

    @Test
    @DisplayName("Clear and rebuild")
    public void ClearAndRebuild() {
      AddTest(14);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestSize(3, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 60L);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("DynVector Construction and Equality Tests")
  public class DynVectorConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(9);
      LLList<Long> source = new LLList<>();
      source.InsertLast(1L);
      source.InsertLast(2L);
      source.InsertLast(3L);
      source.InsertLast(4L);
      source.InsertLast(5L);
      NewNonEmptyContainer((TraversableContainer<Long>) source);
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(3);
      LLList<Long> source = new LLList<>();
      NewNonEmptyContainer(source);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Construction with initial size")
    public void ConstructionWithInitialSize() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(10));
      TestSize(10, false);
      TestSetAt(42L, Natural.Of(5), false);
      TestGetAt(Natural.Of(5), 42L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Equality with same elements same order")
    public void EqualitySameOrder() {
      AddTest(7);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      DynVector<Long> other = GetNewEmptyContainer();
      other.Expand(Natural.Of(3));
      other.SetAt(1L, Natural.ZERO);
      other.SetAt(2L, Natural.Of(1));
      other.SetAt(3L, Natural.Of(2));
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Inequality with same elements different order")
    public void InequalityDifferentOrder() {
      AddTest(7);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      DynVector<Long> other = GetNewEmptyContainer();
      other.Expand(Natural.Of(3));
      other.SetAt(3L, Natural.ZERO);
      other.SetAt(2L, Natural.Of(1));
      other.SetAt(1L, Natural.Of(2));
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(6);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      DynVector<Long> other = GetNewEmptyContainer();
      other.Expand(Natural.Of(2));
      other.SetAt(1L, Natural.ZERO);
      other.SetAt(2L, Natural.Of(1));
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Equality of two empty dynamic vectors")
    public void EqualityEmptyDynVectors() {
      AddTest(2);
      NewEmptyContainer();
      DynVector<Long> other = GetNewEmptyContainer();
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(9);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(4));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestSetAt(40L, Natural.Of(3), false);
      DynVector<Long> copy = GetNewNonEmptyContainer(ThisContainer());
      TestIsEqual(copy, true);
      TestSize(4, false);
      copy.SetAt(999L, Natural.ZERO);
      TestGetAt(Natural.ZERO, 10L, false);
    }

  }

  @Nested
  @DisplayName("DynVector Null Testing")
  public class DynVectorNullTesting {

    @Test
    @DisplayName("SetAt with null element")
    public void SetAtNullElement() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(null, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestGetAt(Natural.Of(1), null, false);
    }

    @Test
    @DisplayName("SetFirst with null")
    public void SetFirstNull() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(1));
      TestSetFirst(null, false);
      TestGetFirst(null, false);
    }

    @Test
    @DisplayName("SetLast with null")
    public void SetLastNull() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(1));
      TestSetLast(null, false);
      TestGetLast(null, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(6);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(null, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestExists(null, true);
    }

    @Test
    @DisplayName("GetAt with null Natural")
    public void GetAtNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestGetAt(null, 0L, true);
    }

    @Test
    @DisplayName("SetAt with null Natural")
    public void SetAtNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, null, true);
    }

    @Test
    @DisplayName("Swap with null Natural")
    public void SwapNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSwap(null, Natural.ONE, true);
      TestSwap(Natural.ZERO, null, true);
    }

    @Test
    @DisplayName("ShiftLeft with null Natural")
    public void ShiftLeftNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestShiftLeft(null, Natural.ONE);
      TestShiftLeft(Natural.ZERO, null);
    }

    @Test
    @DisplayName("ShiftRight with null Natural")
    public void ShiftRightNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestShiftRight(null, Natural.ONE);
      TestShiftRight(Natural.ZERO, null);
    }

    @Test
    @DisplayName("Expand with null Natural")
    public void ExpandNullNatural() {
      AddTest(2);
      NewEmptyContainer();
      TestExpandWithNumber(null);
    }

    @Test
    @DisplayName("Reduce with null Natural")
    public void ReduceNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(5));
      TestReduceWithNumber(null);
    }

    @Test
    @DisplayName("Realloc with null Natural")
    public void ReallocNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestRealloc(null);
    }

    @Test
    @DisplayName("InsertAt with null Natural")
    public void InsertAtNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestInsertAtWithAutoExpansion(null, 1L);
    }

    @Test
    @DisplayName("AtNRemove with null Natural")
    public void AtNRemoveNullNatural() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestAtNRemoveWithAutoReduction(null, 0L);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("FoldForward with null accumulator")
    public void FoldForwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestFoldForward((dat, acc) -> dat != null ? dat + acc : acc, null, 1L);
    }

    @Test
    @DisplayName("FoldBackward with null accumulator")
    public void FoldBackwardNullAccumulator() {
      AddTest(4);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(1L, Natural.Of(2), false);
      TestFoldBackward((dat, acc) -> dat, null, 1L);
    }

    @Test
    @DisplayName("Operations with null elements maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(8);
      NewEmptyContainer();
      TestExpandWithNumber(Natural.Of(3));
      TestSetAt(null, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(null, Natural.Of(2), false);
      TestSize(3, false);
      TestExists(null, true);
      TestExists(2L, true);
      TestGetAt(Natural.ZERO, null, false);
      TestGetAt(Natural.Of(2), null, false);
    }

    @Test
    @DisplayName("Empty dynamic vector null operations")
    public void EmptyDynVectorNullOperations() {
      AddTest(4);
      NewEmptyContainer();
      TestExists(null, false);
      TestGetAt(null, 0L, true);
      TestSetAt(null, null, true);
    }

  }

}
