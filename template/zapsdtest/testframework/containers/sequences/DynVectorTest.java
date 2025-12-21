package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.DynVector;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.base.ResizableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface DynVectorTest<Data, Con extends DynVector<Data>> extends VectorTest<Data, Con>, ResizableContainerTest<Con> {

  default void TestInsertAtWithAutoExpansion(Natural position, Data element) {
    BeginTest("InsertAtWithAutoExpansion");
    if (position == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().InsertAt(element, position),
      "InsertAt should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().InsertAt(element, position);
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "InsertAt should automatically expand size");
    EndTest();
  }

  default void TestAtNRemoveWithAutoReduction(Natural position, Data expectedElement) {
    BeginTest("AtNRemoveWithAutoReduction");
    if (position == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().AtNRemove(position),
      "AtNRemove should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    Data removed = ThisContainer().AtNRemove(position);
    assertEquals(expectedElement, removed,
    "AtNRemove should return the removed element");
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "AtNRemove should automatically reduce size");
    EndTest();
  }

  @Override
  default void TestShiftLeft(Natural position, Natural number) {
    BeginTest("ShiftLeft");
    if (position == null || number == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().ShiftLeft(position, number),
      "ShiftLeft should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position, number);
    assertEquals(initialSize - number.ToLong(), ThisContainer().Size().ToLong(),
    "ShiftLeft should automatically call Reduce and Size should decrease by " + number);
    EndTest();
  }

  @Override
  default void TestShiftLeft(Natural position) {
    BeginTest("ShiftLeft");
    if (position == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().ShiftLeft(position),
      "ShiftLeft should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position);
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftFirstLeft() {
    BeginTest("ShiftFirstLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstLeft();
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftFirstLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftLastLeft() {
    BeginTest("ShiftLastLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastLeft();
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "ShiftLastLeft should automatically call Reduce and Size should decrease by 1");
    EndTest();
  }

  @Override
  default void TestShiftRight(Natural position, Natural number) {
    BeginTest("ShiftRight");
    if (position == null || number == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().ShiftRight(position, number),
      "ShiftRight should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftRight(position, number);
    assertEquals(initialSize + number.ToLong(), ThisContainer().Size().ToLong(),
    "ShiftRight should automatically call Expand and Size should increase by " + number);
    for(long num = 0; num < number.ToLong(); num++) {
      assertNull(ThisContainer().GetAt(Natural.Of(position.ToLong() + num)), "Position " + (position.ToLong() + num) + " should be null");
    }
    EndTest();
  }

  @Override
  default void TestShiftRight(Natural position) {
    BeginTest("ShiftRight");
    if (position == null) {
      assertThrows(NullPointerException.class,
      () -> ThisContainer().ShiftRight(position),
      "ShiftRight should throw exception for null position");
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftRight(position);
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetAt(position), "Position " + position + " should be null");
    EndTest();
  }

  @Override
  default void TestShiftFirstRight() {
    BeginTest("ShiftFirstRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstRight();
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftFirstRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetFirst(), "First position should be null");
    EndTest();
  }

  @Override
  default void TestShiftLastRight() {
    BeginTest("ShiftLastRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastRight();
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "ShiftLastRight should automatically call Expand and Size should increase by 1");
    assertNull(ThisContainer().GetLast(), "Last position should be null");
    EndTest();
  }

}
