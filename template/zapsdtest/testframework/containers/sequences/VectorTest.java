package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.Vector;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.base.ReallocableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface VectorTest<Data, Con extends Vector<Data>> extends MutableSequenceTest<Data, Con>, ReallocableContainerTest<Con> {

  default void TestShiftLeft(Natural position, Natural number) {
    BeginTest("ShiftLeft");
    if (position == null || number == null) {
      assertThrows(NullPointerException.class, () -> ThisContainer().ShiftRight(position, number));
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position, number);
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftLeft");
    EndTest();
  }

  default void TestShiftLeft(Natural position) {
    BeginTest("ShiftLeft");
    if (position == null) {
      assertThrows(NullPointerException.class, () -> ThisContainer().ShiftLeft(position));
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLeft(position);
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftLeft");
    EndTest();
  }

  default void TestShiftFirstLeft() {
    BeginTest("ShiftFirstLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstLeft();
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftFirstLeft");
    EndTest();
  }

  default void TestShiftLastLeft() {
    BeginTest("ShiftLastLeft");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastLeft();
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftLastLeft");
    EndTest();
  }

  default void TestShiftRight(Natural position, Natural number) {
    BeginTest("ShiftRight");
    long initialSize = ThisContainer().Size().ToLong();
    if (position == null || number == null) {
      assertThrows(NullPointerException.class, () -> ThisContainer().ShiftRight(position, number));
      EndTest();
      return;
    }
    ThisContainer().ShiftRight(position, number);
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftRight");
    for(long num = 0; num < number.ToLong(); num++) {
      assertNull(ThisContainer().GetAt(Natural.Of(position.ToLong() + num)), "Position " + (position.ToLong() + num) + " should be null");
    }
    EndTest();
  }

  default void TestShiftRight(Natural position) {
    BeginTest("ShiftRight");
    if (position == null) {
      assertThrows(NullPointerException.class, () -> ThisContainer().ShiftRight(position));
      EndTest();
      return;
    }
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftRight(position);
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftRight");
    assertNull(ThisContainer().GetAt(position), "Position " + position + " should be null");
    EndTest();
  }

  default void TestShiftFirstRight() {
    BeginTest("ShiftFirstRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftFirstRight();
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftFirstRight");
    assertNull(ThisContainer().GetFirst(), "First position should be null");
    EndTest();
  }

  default void TestShiftLastRight() {
    BeginTest("ShiftLastRight");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().ShiftLastRight();
    assertEquals(initialSize, ThisContainer().Size().ToLong(),
    "Size should be preserved after ShiftLastRight");
    assertNull(ThisContainer().GetLast(), "Last position should be null");
    EndTest();
  }

}
