package player;

public class SnookerPlayer extends Player {

  public SnookerPlayer() {
    super();
    type = Type.SNOOKER;
  }

  public SnookerPlayer(Type type, int delta) {
    super(type, delta);
  }

  @Override
  public int playerEndurance() {
    return ComputeEnduranceAlgorithm.basicEndurance(getDelta());
  }

  @Override
  public String toString() {
    return "Snooker Player";
  }
}
