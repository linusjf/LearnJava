class NoDot3 {
  public static void main(String[] args) throws Throwable {
    throw new Throwable("\rHello, world!                           ",null,true,false){};
}
}
