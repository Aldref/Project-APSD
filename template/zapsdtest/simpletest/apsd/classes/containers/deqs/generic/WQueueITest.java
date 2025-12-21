package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.interfaces.containers.deqs.Queue;

import org.junit.jupiter.api.*;

abstract public class WQueueITest extends WQueueTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(6);
    NewEmptyContainer();
    TestEnqueue(4L);
    TestInsert(0L, true);
    TestEnqueue(3L);
    TestInsert(1L, true);
    TestEnqueue(2L);
    TestSize(5, false);
  }

  @Nested
  @DisplayName("Queue Basics")
  public class QueueBasics {

    @Test
    @DisplayName("Check starting from an Empty Queue")
    public void Empty() {
      AddTest(3);
      NewEmptyContainer();
      TestHead(null);
      TestDequeue(true);
      TestHeadNDequeue(null, true);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty Queue")
    public void NonEmpty() {
      AddTest(24);
      NewNonEmptyContainer();
      TestSize(5, false);
      TestHeadNDequeue(4L, false);
      TestHead(0L);
      TestEnqueue(5L);
      TestEnqueue(6L);
      TestHead(0L);
      TestHeadNDequeue(0L, false);
      TestDequeue(false);
      TestHead(1L);
      TestClear();
      TestHeadNDequeue(null, true);
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      TestEnqueue(4L);
      TestEnqueue(5L);
      TestHead(1L);
      TestDequeue(false);
      TestHead(2L);
      TestHeadNDequeue(2L, false);
      TestHead(3L);
      TestSize(3, false);
      TestEnqueue(6L);
      TestSize(4, false);
    }
  }

  @Nested
  @DisplayName("Queue Edge Cases")
  public class QueueEdgeCases {

    @Test
    @DisplayName("FIFO order verification")
    public void FIFOOrder() {
      AddTest(12);
      NewEmptyContainer();
      TestEnqueue(10L);
      TestEnqueue(20L);
      TestEnqueue(30L);
      TestEnqueue(40L);
      TestEnqueue(50L);
      TestHeadNDequeue(10L, false);
      TestHeadNDequeue(20L, false);
      TestHeadNDequeue(30L, false);
      TestHeadNDequeue(40L, false);
      TestHeadNDequeue(50L, false);
      TestIsEmpty(true, false);
      TestHead(null);
    }

    @Test
    @DisplayName("Single element queue")
    public void SingleElement() {
      AddTest(7);
      NewEmptyContainer();
      TestEnqueue(42L);
      TestSize(1, false);
      TestHead(42L);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestHead(null);
      TestDequeue(true);
    }

    @Test
    @DisplayName("Enqueue after complete dequeue")
    public void EnqueueAfterEmpty() {
      AddTest(11);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestDequeue(false);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(3L);
      TestEnqueue(4L);
      TestSize(2, false);
      TestHead(3L);
      TestHeadNDequeue(3L, false);
      TestHead(4L);
    }

    @Test
    @DisplayName("Large number of operations")
    public void LargeOperations() {
      AddTest(154);
      NewEmptyContainer();
      for (long i = 1L; i <= 100L; i++) {
        TestEnqueue(i);
      }
      TestSize(100, false);
      TestHead(1L);
      for (int i = 0; i < 50; i++) {
        TestDequeue(false);
      }
      TestSize(50, false);
      TestHead(51L);
    }

    @Test
    @DisplayName("Alternating enqueue and dequeue")
    public void AlternatingOperations() {
      AddTest(15);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestHead(1L);
      TestEnqueue(2L);
      TestDequeue(false);
      TestHead(2L);
      TestEnqueue(3L);
      TestEnqueue(4L);
      TestDequeue(false);
      TestHead(3L);
      TestDequeue(false);
      TestHead(4L);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(5L);
      TestHead(5L);
    }

    @Test
    @DisplayName("Two element queue operations")
    public void TwoElementQueue() {
      AddTest(14);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestSize(2, false);
      TestHead(1L);
      TestDequeue(false);
      TestHead(2L);
      TestSize(1, false);
      TestEnqueue(3L);
      TestSize(2, false);
      TestHead(2L);
      TestHeadNDequeue(2L, false);
      TestHead(3L);
      TestHeadNDequeue(3L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Negative numbers in queue")
    public void NegativeNumbers() {
      AddTest(12);
      NewEmptyContainer();
      TestEnqueue(-10L);
      TestEnqueue(-5L);
      TestEnqueue(0L);
      TestEnqueue(5L);
      TestEnqueue(10L);
      TestHead(-10L);
      TestHeadNDequeue(-10L, false);
      TestHead(-5L);
      TestHeadNDequeue(-5L, false);
      TestHead(0L);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Extreme values in queue")
    public void ExtremeValues() {
      AddTest(10);
      NewEmptyContainer();
      TestEnqueue(Long.MAX_VALUE);
      TestEnqueue(Long.MIN_VALUE);
      TestEnqueue(0L);
      TestHead(Long.MAX_VALUE);
      TestHeadNDequeue(Long.MAX_VALUE, false);
      TestHead(Long.MIN_VALUE);
      TestHeadNDequeue(Long.MIN_VALUE, false);
      TestHead(0L);
    }

    @Test
    @DisplayName("Clear and reuse queue")
    public void ClearAndReuse() {
      AddTest(14);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestHead(null);
      TestEnqueue(10L);
      TestEnqueue(20L);
      TestSize(2, false);
      TestHead(10L);
      TestHeadNDequeue(10L, false);
      TestHead(20L);
    }

    @Test
    @DisplayName("Duplicate values in queue")
    public void DuplicateValues() {
      AddTest(12);
      NewEmptyContainer();
      TestEnqueue(5L);
      TestEnqueue(5L);
      TestEnqueue(5L);
      TestSize(3, false);
      TestHead(5L);
      TestHeadNDequeue(5L, false);
      TestHead(5L);
      TestHeadNDequeue(5L, false);
      TestHead(5L);
      TestHeadNDequeue(5L, false);
      TestIsEmpty(true, false);
      TestHead(null);
    }

  }

  @Nested
  @DisplayName("Queue Stress Tests")
  public class QueueStressTests {

    @Test
    @DisplayName("Rapid enqueue dequeue cycles")
    public void RapidEnqueueDequeueCycles() {
      AddTest(22);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestDequeue(false);
      TestEnqueue(2L);
      TestDequeue(false);
      TestEnqueue(3L);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(10L);
      TestEnqueue(20L);
      TestEnqueue(30L);
      TestDequeue(false);
      TestDequeue(false);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(100L);
      TestHead(100L);
      TestEnqueue(200L);
      TestHead(100L);
      TestDequeue(false);
      TestHead(200L);
      TestSize(1, false);
      TestDequeue(false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Fill and empty multiple times")
    public void FillAndEmptyMultipleTimes() {
      AddTest(20);
      NewEmptyContainer();
      // First cycle
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      TestHeadNDequeue(1L, false);
      TestHeadNDequeue(2L, false);
      TestHeadNDequeue(3L, false);
      TestIsEmpty(true, false);
      // Second cycle
      TestEnqueue(10L);
      TestEnqueue(20L);
      TestHeadNDequeue(10L, false);
      TestHeadNDequeue(20L, false);
      TestIsEmpty(true, false);
      // Third cycle
      TestEnqueue(100L);
      TestHead(100L);
      TestEnqueue(200L);
      TestEnqueue(300L);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestHead(null);
    }

    @Test
    @DisplayName("Interleaved operations with size checks")
    public void InterleavedOperationsWithSizeChecks() {
      AddTest(24);
      NewEmptyContainer();
      TestSize(0, false);
      TestEnqueue(1L);
      TestSize(1, false);
      TestEnqueue(2L);
      TestSize(2, false);
      TestEnqueue(3L);
      TestSize(3, false);
      TestDequeue(false);
      TestSize(2, false);
      TestEnqueue(4L);
      TestSize(3, false);
      TestDequeue(false);
      TestSize(2, false);
      TestDequeue(false);
      TestSize(1, false);
      TestEnqueue(5L);
      TestSize(2, false);
      TestEnqueue(6L);
      TestSize(3, false);
      TestHead(4L);
      TestDequeue(false);
      TestHead(5L);
      TestDequeue(false);
      TestHead(6L);
    }

    @Test
    @DisplayName("Queue maintains order after many operations")
    public void MaintainsOrderAfterManyOperations() {
      AddTest(20);
      NewEmptyContainer();
      // Add 1-5
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      TestEnqueue(4L);
      TestEnqueue(5L);
      // Remove first two
      TestHeadNDequeue(1L, false);
      TestHeadNDequeue(2L, false);
      // Add 6-8
      TestEnqueue(6L);
      TestEnqueue(7L);
      TestEnqueue(8L);
      // Check order: should be 3,4,5,6,7,8
      TestHead(3L);
      TestHeadNDequeue(3L, false);
      TestHeadNDequeue(4L, false);
      TestHeadNDequeue(5L, false);
      TestHeadNDequeue(6L, false);
      TestHeadNDequeue(7L, false);
      TestHeadNDequeue(8L, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Single element repeated operations")
    public void SingleElementRepeatedOperations() {
      AddTest(18);
      NewEmptyContainer();
      TestEnqueue(42L);
      TestHead(42L);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(43L);
      TestHead(43L);
      TestDequeue(false);
      TestIsEmpty(true, false);
      TestEnqueue(44L);
      TestHead(44L);
      TestSize(1, false);
      TestEnqueue(45L);
      TestSize(2, false);
      TestHeadNDequeue(44L, false);
      TestHeadNDequeue(45L, false);
      TestIsEmpty(true, false);
      TestDequeue(true);
    }

    @Test
    @DisplayName("Descending order enqueue")
    public void DescendingOrderEnqueue() {
      AddTest(14);
      NewEmptyContainer();
      TestEnqueue(100L);
      TestEnqueue(90L);
      TestEnqueue(80L);
      TestEnqueue(70L);
      TestEnqueue(60L);
      // FIFO: should come out in same order as entered
      TestHeadNDequeue(100L, false);
      TestHeadNDequeue(90L, false);
      TestHeadNDequeue(80L, false);
      TestHeadNDequeue(70L, false);
      TestHeadNDequeue(60L, false);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestHead(null);
    }

    @Test
    @DisplayName("Mixed positive and negative values")
    public void MixedPositiveNegative() {
      AddTest(14);
      NewEmptyContainer();
      TestEnqueue(-3L);
      TestEnqueue(0L);
      TestEnqueue(3L);
      TestEnqueue(-6L);
      TestEnqueue(6L);
      TestHead(-3L);
      TestHeadNDequeue(-3L, false);
      TestHeadNDequeue(0L, false);
      TestHeadNDequeue(3L, false);
      TestHeadNDequeue(-6L, false);
      TestHeadNDequeue(6L, false);
      TestIsEmpty(true, false);
      TestHead(null);
    }

    @Test
    @DisplayName("Partial dequeue and continue")
    public void PartialDequeueAndContinue() {
      AddTest(18);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      TestEnqueue(4L);
      TestEnqueue(5L);
      TestEnqueue(6L);
      TestEnqueue(7L);
      TestEnqueue(8L);
      TestEnqueue(9L);
      TestEnqueue(10L);
      TestSize(10, false);
      // Remove first 3
      TestDequeue(false);
      TestDequeue(false);
      TestDequeue(false);
      TestSize(7, false);
      TestHead(4L);
      // Add more
      TestEnqueue(11L);
      TestEnqueue(12L);
      TestSize(9, false);
      // Verify order
      TestHeadNDequeue(4L, false);
    }

  }

  @Nested
  @DisplayName("Queue Construction and Equality Tests")
  public class QueueConstructionAndEqualityTests {

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
      TestHead(1L);
      TestHeadNDequeue(1L, false);
      TestHead(2L);
      TestHeadNDequeue(2L, false);
      TestHead(3L);
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
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      Queue<Long> other = GetNewEmptyContainer();
      other.Enqueue(1L);
      other.Enqueue(2L);
      other.Enqueue(3L);
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Inequality with same elements different order")
    public void InequalityDifferentOrder() {
      AddTest(5);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      Queue<Long> other = GetNewEmptyContainer();
      other.Enqueue(3L);
      other.Enqueue(2L);
      other.Enqueue(1L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(5);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      Queue<Long> other = GetNewEmptyContainer();
      other.Enqueue(1L);
      other.Enqueue(2L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Inequality with different elements")
    public void InequalityDifferentElements() {
      AddTest(5);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      Queue<Long> other = GetNewEmptyContainer();
      other.Enqueue(1L);
      other.Enqueue(2L);
      other.Enqueue(4L);
      TestIsEqual(other, false);
    }

    @Test
    @DisplayName("Equality of two empty queues")
    public void EqualityEmptyQueues() {
      AddTest(2);
      NewEmptyContainer();
      Queue<Long> other = GetNewEmptyContainer();
      TestIsEqual(other, true);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(8);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(2L);
      TestEnqueue(3L);
      Queue<Long> copy = GetNewEmptyContainer();
      copy.Enqueue(1L);
      copy.Enqueue(2L);
      copy.Enqueue(3L);
      TestSize(3, false);
      TestHead(1L);
      TestIsEqual(copy, true);
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("Queue Null Testing")
  public class QueueNullTesting {

    @Test
    @DisplayName("Enqueue null element")
    public void EnqueueNull() {
      AddTest(4);
      NewEmptyContainer();
      TestEnqueue(null);
      TestSize(0, false);
      TestHead(null);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Insert null element")
    public void InsertNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(null, false);
      TestSize(0, false);
      TestHead(null);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Dequeue returns null on empty queue")
    public void DequeueEmptyReturnsNull() {
      AddTest(3);
      NewEmptyContainer();
      TestHead(null);
      TestHeadNDequeue(null, true);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Head returns null on empty queue")
    public void HeadEmptyReturnsNull() {
      AddTest(2);
      NewEmptyContainer();
      TestHead(null);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestEnqueue(1L);
      TestEnqueue(null);
      TestEnqueue(3L);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Exists null on empty queue")
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
      TestEnqueue(1L);
      TestEnqueue(null);
      TestEnqueue(2L);
      TestSize(2, false);
      TestHead(1L);
      TestHeadNDequeue(1L, false);
      TestHead(2L);
      TestHeadNDequeue(2L, false);
      TestHead(null);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Multiple null elements")
    public void MultipleNullElements() {
      AddTest(9);
      NewEmptyContainer();
      TestEnqueue(null);
      TestEnqueue(null);
      TestEnqueue(null);
      TestSize(0, false);
      TestHead(null);
      TestHeadNDequeue(null, true);
      TestHead(null);
      TestHeadNDequeue(null, true);
      TestHead(null);
    }

    @Test
    @DisplayName("Empty queue null operations")
    public void EmptyQueueNullOperations() {
      AddTest(4);
      NewEmptyContainer();
      TestExists(null, false);
      TestHead(null);
      TestHeadNDequeue(null, true);
      TestIsEmpty(true, false);
    }

  }

}
