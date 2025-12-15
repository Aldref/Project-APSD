package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

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

  }
}
