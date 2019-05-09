package design.composite;

public interface Worker {
  void assignWork(Employee manager, Work work);

  void performWork();
}
