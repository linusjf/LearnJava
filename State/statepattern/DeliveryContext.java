package statepattern;

import java.util.Optional;

@SuppressWarnings("PMD.DataClass")
public class DeliveryContext {
  private PackageState currentState;
  private String packageId;

  public DeliveryContext(PackageState currentState, String packageId) {
    this.currentState = currentState;
    this.packageId = packageId;
    Optional<PackageState> pkgState = Optional.ofNullable(currentState);
    this.currentState = pkgState.orElse(Acknowledged.getInstance());
  }

  public PackageState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(PackageState currentState) {
    this.currentState = currentState;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public void update() {
    currentState.updateState(this);
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "DeliveryContext(currentState=" + this.getCurrentState() + ", packageId=" + this.getPackageId() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof DeliveryContext)) return false;
    DeliveryContext other = (DeliveryContext) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$currentState = this.getCurrentState();
    Object other$currentState = other.getCurrentState();
    if (this$currentState == null ? other$currentState != null : !this$currentState.equals(other$currentState)) return false;
    Object this$packageId = this.getPackageId();
    Object other$packageId = other.getPackageId();
    if (this$packageId == null ? other$packageId != null : !this$packageId.equals(other$packageId)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof DeliveryContext;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $currentState = this.getCurrentState();
    result = result * PRIME + ($currentState == null ? 43 : $currentState.hashCode());
    Object $packageId = this.getPackageId();
    result = result * PRIME + ($packageId == null ? 43 : $packageId.hashCode());
    return result;
  }
}
