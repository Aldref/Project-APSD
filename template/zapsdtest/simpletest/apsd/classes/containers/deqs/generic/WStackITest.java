package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.deqs.WStack;
import apsd.interfaces.containers.deqs.Stack;

import org.junit.jupiter.api.*;

abstract public class WStackITest extends WStackTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(6);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestPush(0L);
    TestInsert(3L, true);
    TestPush(1L);
    TestInsert(2L, true);
    TestSize(5, false);
  }

  @Nested
  @DisplayName("Stack Basics")
  public class StackBasics {

    @Test
    @DisplayName("Check starting from an Empty Stack")
    public void Empty() {
      AddTest(3);
      NewEmptyContainer();
      TestTop(null);
      TestPop(true);
      TestTopNPop(null, true);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty Stack")
    public void NonEmpty() {
      AddTest(24);
      NewNonEmptyContainer();
      TestSize(5, false);
      TestTopNPop(2L, false);
      TestTop(1L);
      TestPush(5L);
      TestPush(6L);
      TestTop(6L);
      TestTopNPop(6L, false);
      TestPop(false);
      TestTop(1L);
      TestClear();
      TestTopNPop(null, true);
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestPush(4L);
      TestPush(5L);
      TestTop(5L);
      TestPop(false);
      TestTop(4L);
      TestTopNPop(4L, false);
      TestTop(3L);
      TestSize(3, false);
      TestPush(6L);
      TestSize(4, false);
    }
  }

  @Nested
  @DisplayName("Stack Edge Cases")
  public class StackEdgeCases {

    @Test
    @DisplayName("LIFO order verification")
    public void LIFOOrder() {
      AddTest(12);
      NewEmptyContainer();
      TestPush(10L);
      TestPush(20L);
      TestPush(30L);
      TestPush(40L);
      TestPush(50L);
      TestTopNPop(50L, false);
      TestTopNPop(40L, false);
      TestTopNPop(30L, false);
      TestTopNPop(20L, false);
      TestTopNPop(10L, false);
      TestIsEmpty(true, false);
      TestTop(null);
    }

    @Test
    @DisplayName("Single element stack")
    public void SingleElement() {
      AddTest(7);
      NewEmptyContainer();
      TestPush(42L);
      TestSize(1, false);
      TestTop(42L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestTop(null);
      TestPop(true);
    }

    @Test
    @DisplayName("Push after complete pop")
    public void PushAfterEmpty() {
      AddTest(11);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPop(false);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(3L);
      TestPush(4L);
      TestSize(2, false);
      TestTop(4L);
      TestTopNPop(4L, false);
      TestTop(3L);
    }

    @Test
    @DisplayName("Large number of operations")
    public void LargeOperations() {
      AddTest(154);
      NewEmptyContainer();
      for (long i = 1L; i <= 100L; i++) {
        TestPush(i);
      }
      TestSize(100, false);
      TestTop(100L);
      for (int i = 0; i < 50; i++) {
        TestPop(false);
      }
      TestSize(50, false);
      TestTop(50L);
    }

    @Test
    @DisplayName("Alternating push and pop")
    public void AlternatingOperations() {
      AddTest(15);
      NewEmptyContainer();
      TestPush(1L);
      TestTop(1L);
      TestPush(2L);
      TestPop(false);
      TestTop(1L);
      TestPush(3L);
      TestPush(4L);
      TestPop(false);
      TestTop(3L);
      TestPop(false);
      TestTop(1L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(5L);
      TestTop(5L);
    }

    @Test
    @DisplayName("Two element stack operations")
    public void TwoElementStack() {
      AddTest(14);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestSize(2, false);
      TestTop(2L);
      TestPop(false);
      TestTop(1L);
      TestSize(1, false);
      TestPush(3L);
      TestSize(2, false);
      TestTop(3L);
      TestTopNPop(3L, false);
      TestTop(1L);
      TestTopNPop(1L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Negative numbers in stack")
    public void NegativeNumbers() {
      AddTest(12);
      NewEmptyContainer();
      TestPush(-10L);
      TestPush(-5L);
      TestPush(0L);
      TestPush(5L);
      TestPush(10L);
      TestTop(10L);
      TestTopNPop(10L, false);
      TestTop(5L);
      TestTopNPop(5L, false);
      TestTop(0L);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Extreme values in stack")
    public void ExtremeValues() {
      AddTest(10);
      NewEmptyContainer();
      TestPush(Long.MIN_VALUE);
      TestPush(Long.MAX_VALUE);
      TestPush(0L);
      TestTop(0L);
      TestTopNPop(0L, false);
      TestTop(Long.MAX_VALUE);
      TestTopNPop(Long.MAX_VALUE, false);
      TestTop(Long.MIN_VALUE);
    }

    @Test
    @DisplayName("Clear and reuse stack")
    public void ClearAndReuse() {
      AddTest(14);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestTop(null);
      TestPush(10L);
      TestPush(20L);
      TestSize(2, false);
      TestTop(20L);
      TestTopNPop(20L, false);
      TestTop(10L);
    }

    @Test
    @DisplayName("Duplicate values in stack")
    public void DuplicateValues() {
      AddTest(12);
      NewEmptyContainer();
      TestPush(5L);
      TestPush(5L);
      TestPush(5L);
      TestSize(3, false);
      TestTop(5L);
      TestTopNPop(5L, false);
      TestTop(5L);
      TestTopNPop(5L, false);
      TestTop(5L);
      TestTopNPop(5L, false);
      TestIsEmpty(true, false);
      TestTop(null);
    }

  }

  @Nested
  @DisplayName("Stack Stress Tests")
  public class StackStressTests {

    @Test
    @DisplayName("Rapid push pop cycles")
    public void RapidPushPopCycles() {
      AddTest(22);
      NewEmptyContainer();
      TestPush(1L);
      TestPop(false);
      TestPush(2L);
      TestPop(false);
      TestPush(3L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(10L);
      TestPush(20L);
      TestPush(30L);
      TestPop(false);
      TestPop(false);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(100L);
      TestTop(100L);
      TestPush(200L);
      TestTop(200L);
      TestPop(false);
      TestTop(100L);
      TestSize(1, false);
      TestPop(false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Fill and empty multiple times")
    public void FillAndEmptyMultipleTimes() {
      AddTest(20);
      NewEmptyContainer();
      // First cycle
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestTopNPop(3L, false);
      TestTopNPop(2L, false);
      TestTopNPop(1L, false);
      TestIsEmpty(true, false);
      // Second cycle
      TestPush(10L);
      TestPush(20L);
      TestTopNPop(20L, false);
      TestTopNPop(10L, false);
      TestIsEmpty(true, false);
      // Third cycle
      TestPush(100L);
      TestTop(100L);
      TestPush(200L);
      TestPush(300L);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestTop(null);
    }

    @Test
    @DisplayName("Interleaved operations with size checks")
    public void InterleavedOperationsWithSizeChecks() {
      AddTest(24);
      NewEmptyContainer();
      TestSize(0, false);
      TestPush(1L);
      TestSize(1, false);
      TestPush(2L);
      TestSize(2, false);
      TestPush(3L);
      TestSize(3, false);
      TestPop(false);
      TestSize(2, false);
      TestPush(4L);
      TestSize(3, false);
      TestPop(false);
      TestSize(2, false);
      TestPop(false);
      TestSize(1, false);
      TestPush(5L);
      TestSize(2, false);
      TestPush(6L);
      TestSize(3, false);
      TestTop(6L);
      TestPop(false);
      TestTop(5L);
      TestPop(false);
      TestTop(1L);
    }

    @Test
    @DisplayName("Stack maintains LIFO after many operations")
    public void MaintainsLIFOAfterManyOperations() {
      AddTest(20);
      NewEmptyContainer();
      // Push 1-5
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestPush(4L);
      TestPush(5L);
      // Pop last two (5,4)
      TestTopNPop(5L, false);
      TestTopNPop(4L, false);
      // Push 6-8
      TestPush(6L);
      TestPush(7L);
      TestPush(8L);
      // Stack should be: 1,2,3,6,7,8 (bottom to top)
      // Pop order: 8,7,6,3,2,1
      TestTop(8L);
      TestTopNPop(8L, false);
      TestTopNPop(7L, false);
      TestTopNPop(6L, false);
      TestTopNPop(3L, false);
      TestTopNPop(2L, false);
      TestTopNPop(1L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Single element repeated operations")
    public void SingleElementRepeatedOperations() {
      AddTest(18);
      NewEmptyContainer();
      TestPush(42L);
      TestTop(42L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(43L);
      TestTop(43L);
      TestPop(false);
      TestIsEmpty(true, false);
      TestPush(44L);
      TestTop(44L);
      TestSize(1, false);
      TestPush(45L);
      TestSize(2, false);
      TestTopNPop(45L, false);
      TestTopNPop(44L, false);
      TestIsEmpty(true, false);
      TestPop(true);
    }

    @Test
    @DisplayName("Ascending order push")
    public void AscendingOrderPush() {
      AddTest(14);
      NewEmptyContainer();
      TestPush(10L);
      TestPush(20L);
      TestPush(30L);
      TestPush(40L);
      TestPush(50L);
      // LIFO: should come out in reverse order
      TestTopNPop(50L, false);
      TestTopNPop(40L, false);
      TestTopNPop(30L, false);
      TestTopNPop(20L, false);
      TestTopNPop(10L, false);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestTop(null);
    }

    @Test
    @DisplayName("Mixed positive and negative values")
    public void MixedPositiveNegative() {
      AddTest(14);
      NewEmptyContainer();
      TestPush(-3L);
      TestPush(0L);
      TestPush(3L);
      TestPush(-6L);
      TestPush(6L);
      TestTop(6L);
      TestTopNPop(6L, false);
      TestTopNPop(-6L, false);
      TestTopNPop(3L, false);
      TestTopNPop(0L, false);
      TestTopNPop(-3L, false);
      TestIsEmpty(true, false);
      TestTop(null);
    }

    @Test
    @DisplayName("Partial pop and continue")
    public void PartialPopAndContinue() {
      AddTest(18);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      TestPush(4L);
      TestPush(5L);
      TestPush(6L);
      TestPush(7L);
      TestPush(8L);
      TestPush(9L);
      TestPush(10L);
      TestSize(10, false);
      // Pop top 3
      TestPop(false);
      TestPop(false);
      TestPop(false);
      TestSize(7, false);
      TestTop(7L);
      // Push more
      TestPush(11L);
      TestPush(12L);
      TestSize(9, false);
      // Verify top
      TestTopNPop(12L, false);
    }

    @Test
    @DisplayName("Reverse sequence verification")
    public void ReverseSequenceVerification() {
      AddTest(16);
      NewEmptyContainer();
      // Push in order
      for (long i = 1L; i <= 5L; i++) {
        TestPush(i);
      }
      TestSize(5, false);
      // Pop should give reverse order
      TestTop(5L);
      TestTopNPop(5L, false);
      TestTop(4L);
      TestTopNPop(4L, false);
      TestTop(3L);
      TestTopNPop(3L, false);
      TestTop(2L);
      TestTopNPop(2L, false);
      TestTop(1L);
      TestTopNPop(1L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Stack as LIFO buffer")
    public void StackAsLIFOBuffer() {
      AddTest(20);
      NewEmptyContainer();
      // Simulate undo operations
      TestPush(100L); // Action 1
      TestPush(200L); // Action 2
      TestPush(300L); // Action 3
      TestTop(300L);
      // Undo action 3
      TestPop(false);
      TestTop(200L);
      // New action 4
      TestPush(400L);
      TestTop(400L);
      // Undo action 4
      TestPop(false);
      TestTop(200L);
      // Undo action 2
      TestPop(false);
      TestTop(100L);
      // Redo: push new actions
      TestPush(500L);
      TestPush(600L);
      TestTop(600L);
      TestSize(3, false);
      // Final check
      TestTopNPop(600L, false);
      TestTopNPop(500L, false);
      TestTopNPop(100L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Boundary value operations")
    public void BoundaryValueOperations() {
      AddTest(14);
      NewEmptyContainer();
      TestPush(0L);
      TestPush(1L);
      TestPush(-1L);
      TestPush(Long.MAX_VALUE);
      TestPush(Long.MIN_VALUE);
      TestSize(5, false);
      TestTopNPop(Long.MIN_VALUE, false);
      TestTopNPop(Long.MAX_VALUE, false);
      TestTopNPop(-1L, false);
      TestTopNPop(1L, false);
      TestTopNPop(0L, false);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestTop(null);
    }

  }

  @Nested
  @DisplayName("Stack Construction and Equality Tests")
  public class StackConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(8);
      LLList<Long> source = new LLList<>();
      source.InsertLast(1L);
      source.InsertLast(2L);
      source.InsertLast(3L);
      source.InsertLast(4L);
      source.InsertLast(5L);
      NewNonEmptyContainer(source);
      TestSize(5, false);
      TestTop(1L);
      TestTopNPop(1L, false);
      TestTop(2L);
      TestTopNPop(2L, false);
      TestTop(3L);
      TestSize(3, false);
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
    @DisplayName("Equality with same elements same order")
    public void EqualitySameOrder() {
      AddTest(5);
      NewEmptyContainer();
      TestPush(3L);
      TestPush(2L);
      TestPush(1L);
      Stack<Long> other = GetNewEmptyContainer();
      other.Push(3L);
      other.Push(2L);
      other.Push(1L);
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Inequality with same elements different order")
    public void InequalityDifferentOrder() {
      AddTest(5);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      Stack<Long> other = GetNewEmptyContainer();
      other.Push(3L);
      other.Push(2L);
      other.Push(1L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(5);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      Stack<Long> other = GetNewEmptyContainer();
      other.Push(1L);
      other.Push(2L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Inequality with different elements")
    public void InequalityDifferentElements() {
      AddTest(5);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(2L);
      TestPush(3L);
      Stack<Long> other = GetNewEmptyContainer();
      other.Push(1L);
      other.Push(2L);
      other.Push(4L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Equality of two empty stacks")
    public void EqualityEmptyStacks() {
      AddTest(2);
      NewEmptyContainer();
      Stack<Long> other = GetNewEmptyContainer();
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(8);
      NewEmptyContainer();
      TestPush(3L);
      TestPush(2L);
      TestPush(1L);
      Stack<Long> copy = new WStack<>();
      copy.Push(3L);
      copy.Push(2L);
      copy.Push(1L);
      TestSize(3, false);
      TestTop(1L);
      TestSize(3, false);
      TestIsEqual(copy, true);
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("Stack Null Testing")
  public class StackNullTesting {

    @Test
    @DisplayName("Push null element")
    public void PushNull() {
      AddTest(4);
      NewEmptyContainer();
      TestPush(null);
      TestSize(0, false);
      TestTop(null);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Insert null element")
    public void InsertNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(null, false);
      TestSize(0, false);
      TestTop(null);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Pop returns null on empty stack")
    public void PopEmptyReturnsNull() {
      AddTest(3);
      NewEmptyContainer();
      TestTop(null);
      TestTopNPop(null, true);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Top returns null on empty stack")
    public void TopEmptyReturnsNull() {
      AddTest(2);
      NewEmptyContainer();
      TestTop(null);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestPush(1L);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(null);
      TestPush(3L);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Exists null on empty stack")
    public void ExistsNullEmpty() {
      AddTest(2);
      NewEmptyContainer();
      TestExists(null, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Operations with null elements maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(10);
      NewEmptyContainer();
      TestPush(1L);
      TestPush(null);
      TestPush(2L);
      TestSize(2, false);
      TestTop(2L);
      TestTopNPop(2L, false);
      TestTop(1L);
      TestTopNPop(1L, false);
      TestTop(null);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Multiple null elements")
    public void MultipleNullElements() {
      AddTest(9);
      NewEmptyContainer();
      TestPush(null);
      TestPush(null);
      TestPush(null);
      TestSize(0, false);
      TestTop(null);
      TestTopNPop(null, true);
      TestTop(null);
      TestTopNPop(null, true);
      TestTop(null);
    }

    @Test
    @DisplayName("Empty stack null operations")
    public void EmptyStackNullOperations() {
      AddTest(4);
      NewEmptyContainer();
      TestExists(null, false);
      TestTop(null);
      TestTopNPop(null, true);
      TestIsEmpty(true, false);
    }

  }

}
