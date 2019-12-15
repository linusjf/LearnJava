package player;

public class TennisPlayer extends Player {

  public TennisPlayer() {
    super();
    type = Type.TENNIS;
  }

  public TennisPlayer(Type type, int delta) {
    super(type, delta);
  }

  @Override
  public int playerEndurance() {
    return ComputeEnduranceAlgorithm.basicEndurance(getDelta());

  }

  @Override
  public String toString() {
    return "Tennis Player";
  }
}
