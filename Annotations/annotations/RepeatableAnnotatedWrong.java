package annotations;

@CanBeRepeated("the color is green")
// @CannotBeRepeated("the color is red")
@CannotBeRepeated("the color is blue")
public class RepeatableAnnotatedWrong {
  // empty class
}
