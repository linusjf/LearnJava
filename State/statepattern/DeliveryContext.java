package statepattern;

import java.util.Optional;

@SuppressWarnings("PMD.DataClass")
public class DeliveryContext implements IDeliveryContext {
  private PackageState currentState;
  private String packageId;

  public DeliveryContext(PackageState currentState, String packageId) {
    this.currentState = currentState;
    this.packageId = packageId;
    Optional<PackageState> pkgState = Optional.ofNullable(currentState);
    this.currentState = pkgState.orElseGet(() -> Acknowledged.getInstance());
  }

  @Override
  public PackageState getCurrentState() {
    return currentState;
  }

  @Override
  public void setCurrentState(PackageState currentState) {
    this.currentState = currentState;
  }

  @Override
  public String getPackageId() {
    return packageId;
  }

  @Override
  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  @Override
  public void update() {
    currentState.updateState(this);
  }

  @SuppressWarnings("all")
  @Override
  public String toString() {
    return "DeliveryContext(currentState=" + this.getCurrentState()
        + ", packageId=" + this.getPackageId() + ")";
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof DeliveryContext))
      return false;
    DeliveryContext other = (DeliveryContext)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$currentState = this.getCurrentState();
    Object other$currentState = other.getCurrentState();
    if (this$currentState == null
            ? other$currentState != null
            : !this$currentState.equals(other$currentState))
      return false;
    Object this$packageId = this.getPackageId();
    Object other$packageId = other.getPackageId();
    if (this$packageId == null ? other$packageId != null
                               : !this$packageId.equals(other$packageId))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof DeliveryContext;
  }

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $currentState = this.getCurrentState();
    result = result * PRIME
             + ($currentState == null ? 43 : $currentState.hashCode());
    Object $packageId = this.getPackageId();
    result = result * PRIME + ($packageId == null ? 43 : $packageId.hashCode());
    return result;
  }
}
