package player;

public abstract class Player {

   private final Type type;
   private final int delta;
   
   public Player() {
     type = Type.INIT;
     delta = 0;
   }

   public Player(Type type, int delta) {
      this.type = type;
      this.delta = delta;
   }

   public Type getType() {
      return type;
   }

   public int getDelta() {
      return delta;
   }

   public abstract int playerEndurance();

   // More similar methods
}
