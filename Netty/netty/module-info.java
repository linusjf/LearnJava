@SuppressWarnings({"requires-automatic", "requires-transitive-automatic"})
module netty {
  requires java.logging;
  requires jdk.unsupported;
  requires netty.all;

  exports netty;
}
