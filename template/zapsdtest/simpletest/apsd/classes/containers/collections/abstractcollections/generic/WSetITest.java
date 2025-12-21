package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import apsd.classes.containers.collections.abstractcollections.WSet;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;
import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.collections.Set;

import org.junit.jupiter.api.*;

abstract public class WSetITest extends WSetTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(10);
    NewEmptyContainer();
    TestInsert(4L, true);
    TestInsert(0L, true);
    TestInsert(4L, false);
    TestInsert(3L, true);
    TestInsert(1L, true);
    TestInsert(3L, false);
    TestInsert(2L, true);
    TestInsert(0L, false);
    TestSize(5, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("WSet Basics")
  public class WSetBasics {

    @Test
    @DisplayName("Check starting from an Empty WSet")
    public void Empty() {
      AddTest(6);
      NewEmptyContainer();
      TestExists(0L, false);
      TestFilter(dat -> false);
      TestUnion(ThisContainer());
      TestIntersection(ThisContainer());
      TestDifference(ThisContainer());
      TestSize(0, false);
    }

    @Test
    @DisplayName("Check starting from a NonEmpty WSet")
    public void NonEmpty() {
      AddTest(41);
      NewNonEmptyContainer();
      Set<Long> newcon = GetNewEmptyContainer();
      newcon.Insert(3L);
      newcon.Insert(1L);
      newcon.Insert(5L);
      TestInsert(-1L, true);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(5L, false);
      TestUnion(newcon);
      TestExists(1L, true);
      TestExists(3L, true);
      TestExists(5L, true);
      TestSize(7, false);
      TestDifference(newcon);
      TestExists(1L, false);
      TestExists(3L, false);
      TestExists(5L, false);
      TestSize(4, false);
      TestUnion(newcon);
      TestExists(1L, true);
      TestExists(3L, true);
      TestExists(5L, true);
      TestSize(7, false);
      TestIntersection(newcon);
      TestExists(-1L, false);
      TestExists(2L, false);
      TestSize(3, false);
      TestInsert(4L, true);
      TestInsert(0L, true);
      TestInsert(4L, false);
      TestInsert(3L, false);
      TestInsert(1L, false);
      TestInsert(3L, false);
      TestInsert(2L, true);
      TestInsert(0L, false);
      TestInsert(5L, false);
      TestPrintContent("");
      TestSize(6, false);
      TestFilter(dat -> dat % 2 == 0);
      TestSize(3, false);
      TestExists(5L, false);
      TestExists(2L, true);
      TestClear();
      TestSize(0, false);
    }

  }

  @Nested
  @DisplayName("WSet Edge Cases")
  public class WSetEdgeCases {

    @Test
    @DisplayName("Single element set")
    public void SingleElement() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(42L, true);
      TestSize(1, false);
      TestExists(42L, true);
      TestExists(0L, false);
      TestInsert(42L, false);
      TestSize(1, false);
      TestRemove(42L, true);
      TestIsEmpty(true, false);
      TestExists(42L, false);
      TestRemove(42L, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Duplicate insert rejection")
    public void DuplicateRejection() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(1L, false);
      TestInsert(2L, false);
      TestInsert(3L, false);
      TestSize(3, false);
      TestInsert(1L, false);
      TestInsert(1L, false);
      TestInsert(1L, false);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Remove non-existent element")
    public void RemoveNonExistent() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(3L, true);
      TestInsert(5L, true);
      TestRemove(2L, false);
      TestRemove(4L, false);
      TestRemove(0L, false);
      TestRemove(100L, false);
      TestSize(3, false);
      TestExists(1L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with empty set")
    public void UnionWithEmpty() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestUnion(emptySet);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with disjoint set")
    public void UnionWithDisjoint() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestUnion(other);
      TestSize(6, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestExists(6L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with overlapping set")
    public void UnionWithOverlapping() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      TestUnion(other);
      TestSize(4, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(4L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Intersection with empty set")
    public void IntersectionWithEmpty() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestIntersection(emptySet);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Intersection with disjoint set")
    public void IntersectionWithDisjoint() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestIntersection(other);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestExists(4L, false);
    }

    @Test
    @DisplayName("Intersection with overlapping set")
    public void IntersectionWithOverlapping() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(5L);
      TestIntersection(other);
      TestSize(2, false);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(1L, false);
      TestExists(4L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with empty set")
    public void DifferenceWithEmpty() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> emptySet = GetNewEmptyContainer();
      TestDifference(emptySet);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with disjoint set")
    public void DifferenceWithDisjoint() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(4L);
      other.Insert(5L);
      other.Insert(6L);
      TestDifference(other);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Difference with overlapping set")
    public void DifferenceWithOverlapping() {
      AddTest(11);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(5L);
      TestDifference(other);
      TestSize(2, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestExists(2L, false);
      TestExists(3L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Difference with superset")
    public void DifferenceWithSuperset() {
      AddTest(7);
      NewEmptyContainer();
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(1L);
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      TestDifference(other);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(2L, false);
      TestExists(3L, false);
    }

    @Test
    @DisplayName("Filter keeps matching elements")
    public void FilterKeepsMatching() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestInsert(6L, true);
      TestFilter(dat -> dat % 2 == 0);
      TestSize(3, false);
      TestExists(2L, true);
      TestExists(4L, true);
      TestExists(6L, true);
      TestExists(1L, false);
      TestExists(3L, false);
      TestExists(5L, false);
    }

    @Test
    @DisplayName("Filter removes all")
    public void FilterRemovesAll() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(dat -> dat > 100L);
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestExists(2L, false);
    }

    @Test
    @DisplayName("Filter keeps all")
    public void FilterKeepsAll() {
      AddTest(9);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(dat -> dat > 0L);
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Negative numbers in set")
    public void NegativeNumbers() {
      AddTest(15);
      NewEmptyContainer();
      TestInsert(-10L, true);
      TestInsert(-5L, true);
      TestInsert(0L, true);
      TestInsert(5L, true);
      TestInsert(10L, true);
      TestSize(5, false);
      TestExists(-10L, true);
      TestExists(-5L, true);
      TestExists(0L, true);
      TestInsert(-10L, false);
      TestInsert(0L, false);
      TestRemove(-5L, true);
      TestExists(-5L, false);
      TestSize(4, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Clear and reuse")
    public void ClearAndReuse() {
      AddTest(14);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestSize(3, false);
      TestClear();
      TestIsEmpty(true, false);
      TestSize(0, false);
      TestExists(1L, false);
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestSize(2, false);
      TestExists(10L, true);
      TestExists(20L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with self")
    public void UnionWithSelf() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestUnion(ThisContainer());
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Intersection with self")
    public void IntersectionWithSelf() {
      AddTest(8);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestIntersection(ThisContainer());
      TestSize(3, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
    }

    @Test
    @DisplayName("Difference with self")
    public void DifferenceWithSelf() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestDifference(ThisContainer());
      TestIsEmpty(true, false);
      TestSize(0, false);
    }

    @Test
    @DisplayName("Large set operations")
    public void LargeSetOperations() {
      AddTest(108);
      NewEmptyContainer();
      for (long i = 1L; i <= 50L; i++) {
        TestInsert(i, true);
      }
      TestSize(50, false);
      for (long i = 1L; i <= 50L; i++) {
        TestInsert(i, false);
      }
      TestSize(50, false);
      TestExists(1L, true);
      TestExists(25L, true);
      TestExists(50L, true);
      TestExists(51L, false);
      TestFilter(dat -> dat <= 25L);
      TestSize(25, false);
    }

    @Test
    @DisplayName("Symmetric difference behavior")
    public void SymmetricDifferenceBehavior() {
      AddTest(13);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      Set<Long> other = GetNewEmptyContainer();
      other.Insert(2L);
      other.Insert(3L);
      other.Insert(4L);
      TestDifference(other);
      TestSize(1, false);
      TestExists(1L, true);
      TestExists(2L, false);
      TestExists(3L, false);
      Set<Long> bMinusA = GetNewEmptyContainer();
      bMinusA.Insert(4L);
      TestUnion(bMinusA);
      TestSize(2, false);
      TestExists(1L, true);
      TestExists(4L, true);
      TestPrintContent("");
    }

  }

  @Nested
  @DisplayName("WSet Stress Tests")
  public class WSetStressTests {

    @Test
    @DisplayName("Chained set operations")
    public void ChainedSetOperations() {
      AddTest(19);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      
      Set<Long> set1 = GetNewEmptyContainer();
      set1.Insert(3L);
      set1.Insert(4L);
      set1.Insert(5L);
      set1.Insert(6L);
      
      Set<Long> set2 = GetNewEmptyContainer();
      set2.Insert(1L);
      set2.Insert(2L);
      
      TestUnion(set1);
      TestSize(6, false);
      TestExists(6L, true);
      
      TestDifference(set2);
      TestSize(4, false);
      TestExists(1L, false);
      TestExists(2L, false);
      
      TestIntersection(set1);
      TestSize(4, false);
      TestExists(3L, true);
      TestExists(4L, true);
      TestExists(5L, true);
      TestExists(6L, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Filter with complex predicate")
    public void FilterComplexPredicate() {
      AddTest(30);
      NewEmptyContainer();
      for (long i = 1L; i <= 20L; i++) {
        TestInsert(i, true);
      }
      // Keep only prime numbers
      TestFilter(dat -> {
        if (dat < 2L) return false;
        for (long i = 2L; i * i <= dat; i++) {
          if (dat % i == 0L) return false;
        }
        return true;
      });
      TestPrintContent("");
      TestSize(8, false); // 2, 3, 5, 7, 11, 13, 17, 19
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(5L, true);
      TestExists(7L, true);
      TestExists(11L, true);
      TestExists(4L, false);
      TestExists(6L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Extreme values in set")
    public void ExtremeValues() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(Long.MAX_VALUE, true);
      TestInsert(Long.MIN_VALUE, true);
      TestInsert(0L, true);
      TestSize(3, false);
      TestExists(Long.MAX_VALUE, true);
      TestExists(Long.MIN_VALUE, true);
      TestExists(0L, true);
      TestInsert(Long.MAX_VALUE, false);
      TestInsert(Long.MIN_VALUE, false);
      TestRemove(0L, true);
      TestSize(2, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Union with subset")
    public void UnionWithSubset() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      Set<Long> subset = GetNewEmptyContainer();
      subset.Insert(2L);
      subset.Insert(4L);
      TestUnion(subset);
      TestSize(5, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestExists(3L, true);
      TestExists(4L, true);
      TestExists(5L, true);
    }

    @Test
    @DisplayName("Intersection with subset")
    public void IntersectionWithSubset() {
      AddTest(12);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      Set<Long> subset = GetNewEmptyContainer();
      subset.Insert(2L);
      subset.Insert(4L);
      TestIntersection(subset);
      TestSize(2, false);
      TestExists(2L, true);
      TestExists(4L, true);
      TestExists(1L, false);
      TestExists(3L, false);
      TestExists(5L, false);
    }

    @Test
    @DisplayName("Multiple filters")
    public void MultipleFilters() {
      AddTest(110);
      NewEmptyContainer();
      for (long i = 1L; i <= 100L; i++) {
        TestInsert(i, true);
      }
      TestFilter(dat -> dat <= 50L);
      TestSize(50, false);
      TestFilter(dat -> dat % 2 == 0L);
      TestSize(25, false);
      TestFilter(dat -> dat % 5 == 0L);
      TestSize(5, false); // 10, 20, 30, 40, 50
      TestExists(10L, true);
      TestExists(50L, true);
      TestExists(5L, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Empty set operations")
    public void EmptySetOperations() {
      AddTest(12);
      NewEmptyContainer();
      Set<Long> other = GetNewEmptyContainer();
      TestUnion(other);
      TestIsEmpty(true, false);
      TestIntersection(other);
      TestIsEmpty(true, false);
      TestDifference(other);
      TestIsEmpty(true, false);
      other.Insert(1L);
      other.Insert(2L);
      TestUnion(other);
      TestSize(2, false);
      TestExists(1L, true);
      TestExists(2L, true);
      TestDifference(other);
      TestIsEmpty(true, false);
    }

  }

  @Nested
  @DisplayName("WSet Construction and Equality Tests")
  public class WSetConstructionAndEqualityTests {

    @Test
    @DisplayName("Construction from another container")
    public void ConstructionFromAnotherContainer() {
      AddTest(7);
      NewEmptyContainer();
      List<Long> list = new LLList<>();
      list.Insert(4L);
      list.Insert(2L);
      list.Insert(3L);
      list.Insert(2L);
      list.Insert(1L);
      WSet<Long> conFromList = new WSet<>(list);
      TestInsert(4L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(1L, true);
      TestIsEqual(conFromList, true);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Construction from empty container")
    public void ConstructionFromEmptyContainer() {
      AddTest(4);
      List<Long> emptyList = new LLList<>();
      WSet<Long> conFromEmpty = new WSet<>(emptyList);
      NewEmptyContainer();
      TestIsEqual(conFromEmpty, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Construction from chain")
    public void ConstructionFromChain() {
      AddTest(7);
      LLSortedChain<Long> chain = new LLSortedChain<>();
      chain.Insert(5L);
      chain.Insert(3L);
      chain.Insert(1L);
      chain.Insert(4L);
      chain.Insert(2L);
      WSet<Long> conFromChain = new WSet<>(chain);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestInsert(4L, true);
      TestInsert(5L, true);
      TestIsEqual(conFromChain, true);
    }

    @Test
    @DisplayName("Equality with same elements different order insertion")
    public void EqualityDifferentInsertionOrder() {
      AddTest(6);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WSet<Long> other = (WSet<Long>) GetNewEmptyContainer();
      other.Insert(3L);
      other.Insert(1L);
      other.Insert(2L);
      TestIsEqual(other, true);
      TestSize(3, false);
      TestPrintContent("");
    }

    @Test
    @DisplayName("Inequality with different sizes")
    public void InequalityDifferentSizes() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WSet<Long> smaller = (WSet<Long>) GetNewEmptyContainer();
      smaller.Insert(1L);
      smaller.Insert(2L);
      TestIsEqual(smaller, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Inequality with different elements")
    public void InequalityDifferentElements() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      
      WSet<Long> different = (WSet<Long>) GetNewEmptyContainer();
      different.Insert(1L);
      different.Insert(2L);
      different.Insert(4L);
      TestIsEqual(different, false);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Equality of two empty sets")
    public void EqualityEmptySets() {
      AddTest(3);
      NewEmptyContainer();
      WSet<Long> other = (WSet<Long>) GetNewEmptyContainer();
      TestIsEqual(other, true);
      TestSize(0, false);
      TestIsEmpty(true, false);
    }

    @Test
    @DisplayName("Copy construction preserves elements")
    public void CopyConstructionPreservesElements() {
      AddTest(6);
      List<Long> list = new LLList<>();
      list.Insert(10L);
      list.Insert(20L);
      list.Insert(30L);
      WSet<Long> original = new WSet<>(list);
      WSet<Long> copy = new WSet<>(original);
      NewEmptyContainer();
      TestInsert(10L, true);
      TestInsert(20L, true);
      TestInsert(30L, true);
      TestIsEqual(original, true);
      TestIsEqual(copy, true);
    }

  }

  @Nested
  @DisplayName("WSet Null Testing")
  public class WSetNullTesting {

    @Test
    @DisplayName("Insert null element")
    public void InsertNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestSize(1, false);
      TestExists(null, false);
    }

    @Test
    @DisplayName("Remove null element")
    public void RemoveNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Exists with null")
    public void ExistsNull() {
      AddTest(4);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestExists(null, false);
      TestSize(2, false);
    }

    @Test
    @DisplayName("Union with null set")
    public void UnionWithNull() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestUnion(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Intersection with null set")
    public void IntersectionWithNull() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestIntersection(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Difference with null set")
    public void DifferenceWithNull() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestDifference(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Filter with null predicate")
    public void FilterWithNullPredicate() {
      AddTest(5);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestInsert(3L, true);
      TestFilter(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("IsEqual with null container")
    public void IsEqualNullContainer() {
      AddTest(3);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(2L, true);
      TestIsEqual(null, false);
    }

    @Test
    @DisplayName("Operations after inserting and removing maintain consistency")
    public void NullOperationsConsistency() {
      AddTest(10);
      NewEmptyContainer();
      TestInsert(1L, true);
      TestInsert(null, false);
      TestInsert(2L, true);
      TestRemove(null, false);
      TestInsert(3L, true);
      TestExists(null, false);
      TestUnion(null);
      TestIntersection(null);
      TestDifference(null);
      TestSize(3, false);
    }

    @Test
    @DisplayName("Empty set null operations")
    public void EmptySetNullOperations() {
      AddTest(7);
      NewEmptyContainer();
      TestInsert(null, false);
      TestRemove(null, false);
      TestExists(null, false);
      TestUnion(null);
      TestIntersection(null);
      TestDifference(null);
      TestIsEmpty(true, false);
    }

  }

}
