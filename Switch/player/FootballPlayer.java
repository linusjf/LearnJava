package player;

public class FootballPlayer extends Player {

  public FootballPlayer() {
    super();
    type = Type.FOOTBALL;
  }

  public FootballPlayer(Type type, int delta) {
    super(type, delta);
  }

  @Override
  public int playerEndurance() {
    return ComputeEnduranceAlgorithm.basicEndurance(getDelta());
  }

  @Override
  public String toString() {
    return "Football Player";
  }
}
