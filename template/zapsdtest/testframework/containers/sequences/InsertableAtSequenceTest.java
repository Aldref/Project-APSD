package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.classes.utilities.Natural;

import static org.junit.jupiter.api.Assertions.*;

public interface InsertableAtSequenceTest<Data, Con extends InsertableAtSequence<Data>> extends SequenceTest<Data, Con> {

  default void TestInsertAt(Data element, Natural position, boolean edgeCase) {
    BeginTest("InsertAt");
    long initialSize = ThisContainer().Size().ToLong();
    if (element == null && edgeCase) {
      ThisContainer().InsertAt(element, position);
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not increase by 1 after InsertAt of null element");
    } else if (position == null) 
      assertThrows(NullPointerException.class,
      () -> ThisContainer().InsertAt(element, position),
      "InsertAt should throw exception for null position");
    else if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().InsertAt(element, position),
      "InsertAt should throw exception for invalid position");
    } else {
      ThisContainer().InsertAt(element, position);
      assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
      "Size should increase by 1 after InsertAt");
      assertEquals(element, ThisContainer().GetAt(position),
      "InsertAt should place element at correct position");
    }
    EndTest();
  }

  default void TestInsertFirst(Data element) {
    TestInsertFirst(element, false);
  }

  default void TestInsertFirst(Data element, boolean edgeCase) {
    BeginTest("InsertFirst");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().InsertFirst(element);
    if (edgeCase && element == null) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after inserting null element");
      EndTest();
      return;
    }
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after InsertFirst");
    assertEquals(element, ThisContainer().GetFirst(),
    "InsertFirst should place element at beginning");
    EndTest();
  }

  default void TestInsertLast(Data element) {
    BeginTest("InsertLast");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().InsertLast(element);
    if (element == null) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after inserting null element");
      EndTest();
      return;
    }
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after InsertLast");
    assertEquals(element, ThisContainer().GetLast(),
    "InsertLast should place element at end");
    EndTest();
  }

}
