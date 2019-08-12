package state;

public class Player implements PlayerState {
  private PlayerState registered;
  private PlayerState unregistered;

  private PlayerState playerState;

  public Player() {
    this.registered = new PlayerRegister(this);
    this.unregistered = new PlayerUnregister(this);

    this.playerState = this.unregistered;
  }

  @Override
  public void register() {
    playerState.register();
  }

  @Override
  public void unregister() {
    playerState.unregister();
  }

  public PlayerState getRegistered() {
    return registered;
  }

  public void setRegistered(PlayerState registered) {
    this.registered = registered;
  }

  public PlayerState getUnregistered() {
    return unregistered;
  }

  public void setUnregistered(PlayerState unregistered) {
    this.unregistered = unregistered;
  }

  public PlayerState getState() {
    return playerState;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public void setState(PlayerState playerState) {
    this.playerState = playerState;
  }
}
