package visitorpattern;

// clang-format off
public interface Transportable {
  void accept(Visitor v);
}
// clang-format on
