package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.List;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.sequences.MutableSequenceTest;
import zapsdtest.testframework.containers.sequences.InsertableAtSequenceTest;

import static org.junit.jupiter.api.Assertions.*;

public interface ListTest<Data, Con extends List<Data>> extends ChainTest<Data, Con>, MutableSequenceTest<Data, Con>, InsertableAtSequenceTest<Data, Con> {

  @Override
  default void TestSetAt(Data element, Natural position, boolean edgeCase) {
    BeginTest("SetAt");
    if (position == null) 
      assertThrows(NullPointerException.class,
      () -> ThisContainer().SetAt(element, position),
      "SetAt should throw exception for null position");
    else if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetAt(element, position),
      "SetAt should throw exception for invalid position");
    } else if (element == null) {
      Data oldElement = ThisContainer().GetAt(position);
      ThisContainer().SetAt(element, position);
      assertEquals(oldElement, ThisContainer().GetAt(position),
      "SetAt should NOT set null element at position " + position);
    } else {
      ThisContainer().SetAt(element, position);
      assertEquals(element, ThisContainer().GetAt(position),
      "SetAt should modify element at position " + position);
    }
    EndTest();
  }

  default void TestGetNSetAt(Data newElement, Data expectedOld, Natural position, boolean edgeCase) {
    BeginTest("GetNSetAt");
    if (position == null) 
      assertThrows(NullPointerException.class,
      () -> ThisContainer().SetAt(newElement, position),
      "GetNSetAt should throw exception for null position");
    else if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetAt(newElement, position),
      "GetNSetAt should throw exception for invalid position");
    } else if (newElement == null) {
      Data oldElement = ThisContainer().GetAt(position);
      ThisContainer().SetAt(newElement, position);
      assertEquals(oldElement, ThisContainer().GetAt(position),
      "SetAt should NOT set null element at position " + position);
    } else {
      Data oldElement = ThisContainer().GetNSetAt(newElement, position);
      assertEquals(expectedOld, oldElement,
      "GetNSetAt should return original element");
      assertEquals(newElement, ThisContainer().GetAt(position),
      "GetNSetAt should set new element");
    }
    EndTest();
  }

  default void TestSetFirst(Data element, boolean edgeCase) {
    BeginTest("SetFirst");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetFirst(element),
      "SetFirst should throw exception when sequence is empty");
    } else if (element == null) {
      Data oldElement = ThisContainer().GetFirst();
      ThisContainer().SetFirst(element);
      assertEquals(oldElement, ThisContainer().GetFirst(),
      "SetFirst should NOT set null element at first position");
    } else {
      ThisContainer().SetFirst(element);
      assertEquals(element, ThisContainer().GetFirst(),
      "SetFirst should modify first element");
    }
    EndTest();
  }

  default void TestGetNSetFirst(Data newElement, Data expectedOld, boolean edgeCase) {
    BeginTest("GetNSetFirst");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetFirst(newElement),
      "SetFirst should throw exception when sequence is empty");
    } else if (newElement == null) {
      Data oldElement = ThisContainer().GetFirst();
      ThisContainer().SetFirst(newElement);
      assertEquals(oldElement, ThisContainer().GetFirst(),
      "SetFirst should NOT set null element at first position");
    } else {
      Data oldElement = ThisContainer().GetNSetFirst(newElement);
      assertEquals(expectedOld, oldElement,
      "GetNSetFirst should return original first element");
      assertEquals(newElement, ThisContainer().GetFirst(),
      "GetNSetFirst should set new first element");
    }
    EndTest();
  }

  default void TestSetLast(Data element, boolean edgeCase) {
    BeginTest("SetLast");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetLast(element),
      "SetLast should throw exception when sequence is empty");
    } else if (element == null) {
      Data oldElement = ThisContainer().GetLast();
      ThisContainer().SetLast(element);
      assertEquals(oldElement, ThisContainer().GetLast(),
      "SetLast should NOT set null element at last position");
    } else {
      ThisContainer().SetLast(element);
      assertEquals(element, ThisContainer().GetLast(),
      "SetLast should modify last element");
    }
    EndTest();
  }

  default void TestGetNSetLast(Data newElement, Data expectedOld, boolean edgeCase) {
    BeginTest("GetNSetLast");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().SetLast(newElement),
      "GetNSetLast should throw exception when sequence is empty");
    } else if (newElement == null) {
      Data oldElement = ThisContainer().GetLast();
      ThisContainer().SetLast(newElement);
      assertEquals(oldElement, ThisContainer().GetLast(),
      "SetLast should NOT set null element at last position");
    } else {
      Data oldElement = ThisContainer().GetNSetLast(newElement);
      assertEquals(expectedOld, oldElement,
      "GetNSetLast should return original last element");
      assertEquals(newElement, ThisContainer().GetLast(),
      "GetNSetLast should set new last element");
    }
    EndTest();
  }

  default void TestSubList(Natural from, Natural to, boolean edgeCase) {
    BeginTest("SubList");
    List<Data> subList = ThisContainer().SubList(from, to);
    if (edgeCase) {
      assertNull(subList, "SubList should return null for invalid range");
    } else {
      assertNotNull(subList, "SubList should not return null");
      assertTrue(subList.Size().ToLong() <= ThisContainer().Size().ToLong(),
      "SubList should not be larger than original");
      assertEquals(subList.Size().ToLong(), to.ToLong() - from.ToLong() + 1,
      "SubList should not be as large as the required interval");
    }
    EndTest();
  }

}
