package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.WSet;
import apsd.interfaces.containers.base.TraversableContainer;
import zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic.WSetITest;

public class VSetITest extends WSetITest {

  public VSetITest() {
    System.out.println("**** VSetI *****************************************");
    name = "13 - VSetI";
  }

  @Override
  public void NewEmptyContainer() { container = new WSet<>(); }

  @Override
  public void NewNonEmptyContainer(TraversableContainer<Long> con) { container = new WSet<>(con); }

  @Override
  public WSet<Long> GetNewEmptyContainer() { return new WSet<>(); }

  @Override
  public WSet<Long> GetNewNonEmptyContainer(TraversableContainer<Long> con) { return new WSet<>(con); }

}
