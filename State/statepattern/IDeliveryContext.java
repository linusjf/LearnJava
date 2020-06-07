package statepattern;

public interface IDeliveryContext {

  PackageState getCurrentState();

  void setCurrentState(PackageState currentState);

  String getPackageId();

  void setPackageId(String packageId);

  void update();
}
