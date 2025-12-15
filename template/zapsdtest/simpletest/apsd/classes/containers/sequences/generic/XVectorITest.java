package zapsdtest.simpletest.apsd.classes.containers.sequences.generic;

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

}
