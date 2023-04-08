@SuppressWarnings({"requires-automatic", "requires-transitive-automatic"})
module netty {
  requires java.logging;
  requires netty.all;

  exports netty;
}
