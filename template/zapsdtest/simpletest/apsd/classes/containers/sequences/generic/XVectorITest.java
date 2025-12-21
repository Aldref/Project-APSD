package zapsdtest.simpletest.apsd.classes.containers.sequences.generic;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Natural;

import org.junit.jupiter.api.*;

abstract public class XVectorITest extends XVectorTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(13);
    NewEmptyContainer();
    TestSetFirst(0L, true);
    TestRealloc(Natural.Of(1));
    TestSetFirst(0L, false);
    TestRealloc(Natural.Of(5));
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
      AddTest(39);
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
      TestShiftLeft(Natural.ZERO, Natural.ONE);
      TestPrintContent("");
      TestSize(4L, false);
      TestShiftRight(Natural.ZERO, Natural.Of(2));
      TestSize(4L, false);
      TestPrintContent("");
      TestClear();
      TestSize(0L, false);

      TestRealloc(Natural.Of(8));
      TestSetAt(1L, Natural.Of(0), false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(0L, Natural.Of(2), false);
      TestSetAt(11L, Natural.Of(3), false);
      TestSetAt(3L, Natural.Of(4), false);
      TestSetAt(4L, Natural.Of(5), false);
      TestSetAt(0L, Natural.Of(6), false);
      TestSetAt(2L, Natural.Of(7), false);
      TestPrintContent("");
      TestShiftRight(Natural.Of(6), Natural.Of(1));
      TestPrintContent("");
      TestClear();
      TestSize(0L, false);
    }

  }

  @Nested
  @DisplayName("Vector Edge Cases")
  public class VectorEdgeCases {

    @Test
    @DisplayName("Single element vector operations")
    public void SingleElement() {
      AddTest(12);
      NewEmptyContainer();
      TestRealloc(Natural.Of(1));
      TestSetFirst(42L, false);
      TestGetFirst(42L, false);
      TestGetLast(42L, false);
      TestGetAt(Natural.ZERO, 42L, false);
      TestExists(42L, true);
      TestExists(0L, false);
      TestSwap(Natural.ZERO, Natural.ZERO, false);
      TestGetFirst(42L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 42L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 42L);
      TestClear();
    }

    @Test
    @DisplayName("Boundary index access")
    public void BoundaryAccess() {
      AddTest(11);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(4), false);
      TestGetAt(Natural.ZERO, 10L, false);
      TestGetAt(Natural.Of(4), 20L, false);
      TestGetAt(Natural.Of(5), 0L, true);
      TestSetAt(0L, Natural.Of(5), true);
      TestSwap(Natural.ZERO, Natural.Of(4), false);
      TestGetAt(Natural.ZERO, 20L, false);
      TestGetAt(Natural.Of(4), 10L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Realloc to same size")
    public void ReallocSameSize() {
      AddTest(7);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestRealloc(Natural.Of(3));
      TestFoldForward((dat, acc) -> acc + dat, 0L, 6L);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Realloc to zero")
    public void ReallocToZero() {
      AddTest(4);
      NewNonEmptyContainer();
      TestRealloc(Natural.ZERO);
      TestSize(0, false);
      TestIsEmpty(true, false);
      TestGetFirst(0L, true);
    }

    @Test
    @DisplayName("Multiple consecutive shifts")
    public void ConsecutiveShifts() {
      AddTest(19);
      NewEmptyContainer();
      TestRealloc(Natural.Of(6));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestSetAt(6L, Natural.Of(5), false);
      TestPrintContent("");
      TestShiftLeft(Natural.ZERO, Natural.Of(2));
      TestPrintContent("");
      TestShiftRight(Natural.Of(2), Natural.Of(2));
      TestPrintContent("");
      TestShiftLeft(Natural.Of(1), Natural.ONE);
      TestPrintContent("");
      TestSize(6, false);
      TestFoldForward((dat, acc) -> { if (dat != null) return acc + dat; else return acc; }, 0L, 14L);
      TestShiftRight(Natural.Of(3), Natural.Of(2));
      TestFoldForward((dat, acc) -> { if (dat != null) return acc + dat; else return acc; }, 0L, 8L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Shift at boundaries")
    public void ShiftBoundaries() {
      AddTest(10);
      NewEmptyContainer();
      TestRealloc(Natural.Of(4));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestShiftLeft(Natural.ZERO, Natural.ONE);
      TestPrintContent("");
      TestShiftRight(Natural.Of(3), Natural.ONE);
      TestPrintContent("");
      TestSize(4, false);
    }

    @Test
    @DisplayName("Swap same position")
    public void SwapSamePosition() {
      AddTest(6);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(100L, Natural.Of(1), false);
      TestSwap(Natural.ONE, Natural.ONE, false);
      TestGetAt(Natural.Of(1), 100L, false);
      TestPrintContent("");
      TestSize(3, false);
    }

    @Test
    @DisplayName("Fold with identity operations")
    public void FoldIdentity() {
      AddTest(9);
      NewEmptyContainer();
      TestRealloc(Natural.Of(4));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestFoldForward((dat, acc) -> acc, 999L, 999L);
      TestFoldBackward((dat, acc) -> acc, 888L, 888L);
      TestFoldForward((dat, acc) -> dat, 0L, 4L);
      TestFoldBackward((dat, acc) -> dat, 0L, 1L);
    }

  }

  @Nested
  @DisplayName("Vector Stress Tests")
  public class VectorStressTests {

    @Test
    @DisplayName("Rapid reallocation cycles")
    public void RapidReallocation() {
      AddTest(22);
      NewEmptyContainer();
      TestRealloc(Natural.Of(10));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(9), false);
      TestRealloc(Natural.Of(5));
      TestSize(5, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestRealloc(Natural.Of(20));
      TestSize(20, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestRealloc(Natural.Of(1));
      TestSize(1, false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestRealloc(Natural.Of(100));
      TestSetAt(99L, Natural.Of(99), false);
      TestRealloc(Natural.Of(50));
      TestSize(50, false);
      TestRealloc(Natural.ZERO);
      TestIsEmpty(true, false);
      TestRealloc(Natural.Of(3));
      TestSize(3, false);
      TestSetAt(42L, Natural.Of(1), false);
      TestGetAt(Natural.Of(1), 42L, false);
    }

    @Test
    @DisplayName("All positions swap")
    public void AllPositionsSwap() {
      AddTest(16);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      // Reverse the array using swaps
      TestSwap(Natural.ZERO, Natural.Of(4), false);
      TestSwap(Natural.Of(1), Natural.Of(3), false);
      TestGetAt(Natural.ZERO, 5L, false);
      TestGetAt(Natural.Of(1), 4L, false);
      TestGetAt(Natural.Of(2), 3L, false);
      TestGetAt(Natural.Of(3), 2L, false);
      TestGetAt(Natural.Of(4), 1L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 15L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("GetNSet operations")
    public void GetNSetOperations() {
      AddTest(12);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestGetNSetFirst(100L, 10L, false);
      TestGetFirst(100L, false);
      TestGetNSetLast(300L, 30L, false);
      TestGetLast(300L, false);
      TestGetNSetAt(200L, 20L, Natural.Of(1), false);
      TestGetAt(Natural.Of(1), 200L, false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 600L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Shift with zero count")
    public void ShiftZeroCount() {
      AddTest(11);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestShiftLeft(Natural.Of(2), Natural.ZERO);
      TestShiftRight(Natural.Of(2), Natural.ZERO);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestSize(5, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Maximum index operations")
    public void MaximumIndexOperations() {
      AddTest(10);
      NewEmptyContainer();
      TestRealloc(Natural.Of(100));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(100L, Natural.Of(99), false);
      TestGetAt(Natural.ZERO, 1L, false);
      TestGetAt(Natural.Of(99), 100L, false);
      TestSwap(Natural.ZERO, Natural.Of(99), false);
      TestGetAt(Natural.ZERO, 100L, false);
      TestGetAt(Natural.Of(99), 1L, false);
      TestGetAt(Natural.Of(100), 0L, true);
      TestSetAt(0L, Natural.Of(100), true);
    }

    @Test
    @DisplayName("Iterator consistency after modifications")
    public void IteratorConsistency() {
      AddTest(14);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestSetAt(5L, Natural.Of(4), false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 15L);
      TestSetAt(10L, Natural.Of(2), false);
      TestFoldForward((dat, acc) -> acc + dat, 0L, 22L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 22L);
      TestRealloc(Natural.Of(3));
      TestFoldForward((dat, acc) -> acc + dat, 0L, 13L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 13L);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Exists after modifications")
    public void ExistsAfterModifications() {
      AddTest(15);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestExists(10L, true);
      TestExists(20L, true);
      TestExists(30L, true);
      TestExists(40L, false);
      TestSetAt(40L, Natural.Of(1), false);
      TestExists(20L, false);
      TestExists(40L, true);
      TestRealloc(Natural.Of(2));
      TestExists(30L, false);
      TestExists(10L, true);
      TestExists(40L, true);
    }

  }

  @Nested
  @DisplayName("Vector Construction and Equality Tests")
  public class VectorConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(7);
      NewEmptyContainer();
      LLList<Long> list = new LLList<>();
      list.InsertLast(1L);
      list.InsertLast(2L);
      list.InsertLast(3L);
      list.InsertLast(4L);
      Vector<Long> conFromList = new Vector<>(list);
      TestRealloc(Natural.Of(4));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestSetAt(4L, Natural.Of(3), false);
      TestIsEqual(conFromList, true);
      TestSize(4, false);
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(4);
      LLList<Long> emptyList = new LLList<>();
      Vector<Long> conFromEmpty = new Vector<>(emptyList);
      NewEmptyContainer();
      TestIsEqual(conFromEmpty, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Construction with initial size")
    public void ConstructionWithInitialSize() {
      AddTest(5);
      Vector<Long> sized = new Vector<>(Natural.Of(5));
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSize(5, false);
      TestIsEqual(sized, true);
      TestGetAt(Natural.ZERO, null, false);
      TestGetAt(Natural.Of(4), null, false);
    }

    @Test
    @DisplayName("Equality with same elements same order")
    public void EqualitySameOrder() {
      AddTest(7);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      
      Vector<Long> other = new Vector<>(Natural.Of(3));
      other.SetAt(1L, Natural.ZERO);
      other.SetAt(2L, Natural.Of(1));
      other.SetAt(3L, Natural.Of(2));
      TestIsEqual(other, true);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Inequality with same elements different order")
    public void InequalityDifferentOrder() {
      AddTest(7);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      
      Vector<Long> other = new Vector<>(Natural.Of(3));
      other.SetAt(3L, Natural.ZERO);
      other.SetAt(2L, Natural.Of(1));
      other.SetAt(1L, Natural.Of(2));
      TestIsEqual(other, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(6);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      
      Vector<Long> smaller = new Vector<>(Natural.Of(2));
      smaller.SetAt(1L, Natural.ZERO);
      smaller.SetAt(2L, Natural.Of(1));
      TestIsEqual(smaller, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality of two empty vectors")
    public void EqualityEmptyVectors() {
      AddTest(3);
      NewEmptyContainer();
      Vector<Long> other = new Vector<>();
      TestIsEqual(other, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(7);
      LLList<Long> list = new LLList<>();
      list.InsertLast(10L);
      list.InsertLast(20L);
      list.InsertLast(30L);
      Vector<Long> original = new Vector<>(list);
      Vector<Long> copy = new Vector<>(original);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(10L, Natural.ZERO, false);
      TestSetAt(20L, Natural.Of(1), false);
      TestSetAt(30L, Natural.Of(2), false);
      TestIsEqual(original, true);
      TestIsEqual(copy, true);
    }

  }

  @Nested
  @DisplayName("Vector Null Testing")
  public class VectorNullTesting {

    @Test
    @DisplayName("SetAt with null element")
    public void SetAtNullElement() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(null, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestGetAt(Natural.Of(1), null, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("SetFirst with null")
    public void SetFirstNull() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(2));
      TestSetFirst(null, false);
      TestGetFirst(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("SetLast with null")
    public void SetLastNull() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(2));
      TestSetLast(null, false);
      TestGetLast(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(2L, Natural.Of(2), false);
      TestExists(null, false);
      TestSetAt(null, Natural.Of(2), false);
      TestExists(null, true);
    }

    @Test
    @DisplayName("GetAt with null Natural")
    public void GetAtNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(2));
      TestSetAt(1L, Natural.ZERO, false);
      TestGetAt(null, 0L, true);
      TestSize(2, false);
    }

    @Test
    @DisplayName("SetAt with null Natural")
    public void SetAtNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(2));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(100L, null, true);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Swap with null Natural")
    public void SwapNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSwap(null, Natural.Of(1), true);
      TestSwap(Natural.ZERO, null, true);
      TestSize(3, false);
    }

    @Test
    @DisplayName("ShiftLeft with null Natural")
    public void ShiftLeftNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestShiftLeft(null, Natural.ONE);
      TestShiftLeft(Natural.ZERO, null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("ShiftRight with null Natural")
    public void ShiftRightNullNatural() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestShiftRight(null, Natural.ONE);
      TestShiftRight(Natural.ZERO, null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Realloc with null Natural")
    public void ReallocNullNatural() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestRealloc(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(4);
      NewEmptyContainer();
      TestRealloc(Natural.Of(2));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("FoldForward with null accumulator")
    public void FoldForwardNullAccumulator() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestFoldForward((dat, acc) -> dat, null, 3L);
    }

    @Test
    @DisplayName("FoldBackward with null accumulator")
    public void FoldBackwardNullAccumulator() {
      AddTest(5);
      NewEmptyContainer();
      TestRealloc(Natural.Of(3));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(2L, Natural.Of(1), false);
      TestSetAt(3L, Natural.Of(2), false);
      TestFoldBackward((dat, acc) -> dat, null, 1L);
    }

    @Test
    @DisplayName("Operations with null elements maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(9);
      NewEmptyContainer();
      TestRealloc(Natural.Of(5));
      TestSetAt(1L, Natural.ZERO, false);
      TestSetAt(null, Natural.Of(1), false);
      TestSetAt(2L, Natural.Of(2), false);
      TestSetAt(null, Natural.Of(3), false);
      TestSetAt(3L, Natural.Of(4), false);
      TestGetAt(Natural.Of(1), null, false);
      TestGetAt(Natural.Of(3), null, false);
      TestExists(null, true);
      TestSize(5, false);
    }

    @Test
    @DisplayName("Empty vector null operations")
    public void EmptyVectorNullOperations() {
      AddTest(5);
      NewEmptyContainer();
      TestExists(null, false);
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

  }

}
