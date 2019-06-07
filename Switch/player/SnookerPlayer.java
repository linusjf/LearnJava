package player;

public class SnookerPlayer extends Player {
  
  public SnookerPlayer() {
    super();
  }

  public SnookerPlayer(Type type, int delta) {
      super(type, delta);
   }

  @Override
  public int playerEndurance() {
      return ComputeEnduranceAlgorithm.basicEndurance
         (this.getDelta());
  }

  @Override
  public String toString() {
    return "Snooker Player";
  }
}
