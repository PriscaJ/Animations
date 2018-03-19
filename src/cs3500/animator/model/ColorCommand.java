package cs3500.animator.model;

/**
 * Primarily  uses its field of ColorChange to apply the Action whihc is relayed back to the shape.
 */
public class ColorCommand implements AnimationCommand {
  ColorChange changeColor;


  public ColorCommand(ColorChange changeColor) {
    this.changeColor = changeColor;
  }

  @Override
  public AbstractAnimation getAnimation() {
    return changeColor;
  }

  @Override
  public void execute(int tick) {
    changeColor.apply(tick);
  }

  @Override
  public String toString() {
    return changeColor.toString();
  }
}
