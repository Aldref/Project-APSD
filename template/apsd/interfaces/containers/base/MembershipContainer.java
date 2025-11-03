package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla verifica di appartenenza. */
public interface MembershipContainer<Data> extends Container<Data> {

  // Exists
  boolean Exists(Data data); 
}
