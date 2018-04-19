package cs3500.animator.model;

/**
 * This is a class to represent a command executed to rotate a shape.
 */
public class RotateCommand implements AnimationCommand {

  private Rotate rotate;

  /**
   * The constructor for a Command that controls a Rotate.
   * @param rotate the Rotate animation to be applied.
   */
  public RotateCommand(Rotate rotate) {
    this.rotate = rotate;
  }

  @Override
  public AbstractAnimation getAnimation() {
    return rotate;
  }

  @Override
  public void execute(int tick) {
    getAnimation().apply(tick);
  }
}
