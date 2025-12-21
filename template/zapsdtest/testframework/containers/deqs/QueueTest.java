package zapsdtest.testframework.containers.deqs;

import apsd.interfaces.containers.deqs.Queue;

import zapsdtest.testframework.containers.base.ClearableContainerTest;
import zapsdtest.testframework.containers.base.InsertableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface QueueTest<Data, Con extends Queue<Data>> extends ClearableContainerTest<Con>, InsertableContainerTest<Data, Con> {

  default void TestHead(Data expectedElement) {
    BeginTest("Head");
    Data head = ThisContainer().Head();
    assertEquals(expectedElement, head, "Head should return " + expectedElement);
    EndTest();
  }

  default void TestDequeue(boolean edgeCase) {
    BeginTest("Dequeue");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Dequeue();
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Dequeue on Empty Queue");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after Dequeue");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after Dequeue");
    }
    EndTest();
  }

  default void TestHeadNDequeue(Data expectedElement, boolean edgeCase) {
    BeginTest("HeadNDequeue");
    long initialSize = ThisContainer().Size().ToLong();
    Data popped = ThisContainer().HeadNDequeue();
    assertEquals(expectedElement, popped, "HeadNDequeue should return " + expectedElement);
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Dequeue on Empty Queue");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after HeadNDequeue");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after HeadNDequeue");
    }
    EndTest();
  }

  default void TestEnqueue(Data element) {
    BeginTest("Enqueue");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Enqueue(element);
    if (element == null) {
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not increase by 1 after Enqueue of null element");
      EndTest();
      return;
    }
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after Enqueue");
    EndTest();
  }

  default void TestIsEqual(Con otherContainer, boolean expectedResult) {
    BeginTest("IsEqual");
    if (otherContainer == null) {
      assertEquals(false, expectedResult,
      "IsEqual should return false for null container");
      EndTest();
      return;
    }
    boolean isEqual = true;
    while (!ThisContainer().IsEmpty() && !otherContainer.IsEmpty()) {
      Data thisHead = ThisContainer().HeadNDequeue();
      Data otherHead = otherContainer.HeadNDequeue();
      if (thisHead == null) {
        if (otherHead != null) {
          isEqual = false;
          break;
        }
      } else {
        if (!thisHead.equals(otherHead)) {
          isEqual = false;
          break;
        }
      }
      
    }
    if (!ThisContainer().IsEmpty() || !otherContainer.IsEmpty()) {
      isEqual = false;
    }
    assertEquals(expectedResult, isEqual,
    "IsEqual should return " + expectedResult + " for the given container");
    EndTest();
  }

  default void TestExists(Data element, boolean expectedResult) {
    BeginTest("Exists");
    boolean exists = false;
    while (!ThisContainer().IsEmpty()) {
      Data head = ThisContainer().HeadNDequeue();
      if (head == null) {
        break;
      } else {
        if (head.equals(element)) {
          exists = true;
          break;
        }
      }
    }
    assertEquals(expectedResult, exists,
    "Exists should return " + expectedResult + " for " + element);
    EndTest();
  }

}
