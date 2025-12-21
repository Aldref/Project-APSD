package zapsdtest.testframework.containers.deqs;

import apsd.interfaces.containers.deqs.Stack;

import zapsdtest.testframework.containers.base.ClearableContainerTest;
import zapsdtest.testframework.containers.base.InsertableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface StackTest<Data, Con extends Stack<Data>> extends ClearableContainerTest<Con>, InsertableContainerTest<Data, Con> {

  default void TestTop(Data expectedElement) {
    BeginTest("Top");
    Data top = ThisContainer().Top();
    assertEquals(expectedElement, top, "Top should return " + expectedElement);
    EndTest();
  }

  default void TestPop(boolean edgeCase) {
    BeginTest("Pop");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Pop();
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Pop on Empty Stack");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after Pop on Empty Stack");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after Pop");
    }
    EndTest();
  }

  default void TestTopNPop(Data expectedElement, boolean edgeCase) {
    BeginTest("TopNPop");
    long initialSize = ThisContainer().Size().ToLong();
    Data popped = ThisContainer().TopNPop();
    assertEquals(expectedElement, popped, "TopNPop should return " + expectedElement);
    if (edgeCase) {
      assertEquals(0, initialSize,
      "Initial size should be 0 after Pop on Empty Stack");
      assertEquals(0, ThisContainer().Size().ToLong(),
      "Size should stay 0 after TopNPop on Empty Stack");
    } else {
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after TopNPop");
    }
    EndTest();
  }

  default void TestPush(Data element) {
    BeginTest("Push");
    long initialSize = ThisContainer().Size().ToLong();
    Data oldTop = ThisContainer().Top();
    ThisContainer().Push(element);
    Data top = ThisContainer().Top();
    if (element == null) {
      assertEquals(oldTop, top, "Top should remain " + oldTop + " after Push of null element");
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not increase by 1 after Push of null element");
      EndTest();
      return;
    }
    assertEquals(element, top, "Top should return " + element + " after Push");
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after Push");
    EndTest();
  }

  default void TestIsEqual(Stack<Data> otherStack, boolean expectedResult) {
    BeginTest("IsEqual");
    if (otherStack == null) {
      assertEquals(false, expectedResult,
      "IsEqual should return false when other stack is null");
      EndTest();
      return;
    }
    boolean isEqual = true;
    while (!ThisContainer().IsEmpty() && !otherStack.IsEmpty()) {
      Data thisTop = ThisContainer().TopNPop();
      Data otherTop = otherStack.TopNPop();
      if (thisTop == null) {
        if (otherTop != null) {
          isEqual = false;
          break;
        }
      } else {
        if (!thisTop.equals(otherTop)) {
          isEqual = false;
          break;
        }
      }
    }
    if (!ThisContainer().IsEmpty() || !otherStack.IsEmpty()) {
      isEqual = false;
    }
    assertEquals(expectedResult, isEqual,
    "IsEqual should return " + expectedResult);
    EndTest();
  }

  default void TestExists(Data element, boolean expectedResult) {
    BeginTest("Exists");
    boolean exists = false;
    while (!ThisContainer().IsEmpty()) {
      Data top = ThisContainer().TopNPop();
      if (top == null) {
        break;
      } else {
        if (top.equals(element)) {
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
