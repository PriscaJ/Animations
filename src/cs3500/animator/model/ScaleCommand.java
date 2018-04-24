package cs3500.animator.model;

/**
 * Primarily  uses its field of ScaleChange to apply the Action whihc is relayed back to the shape.
 */
public class ScaleCommand implements AnimationCommand {
  private ScaleChange scale;

  /**
   * The constructor for a Command that controls a Scale Change.
   *
   * @param scale The Scale Change action.
   */
  public ScaleCommand(ScaleChange scale) {
    this.scale = scale;
  }

  @Override
  public AbstractAnimation getAnimation() {
    return scale;
  }

  @Override
  public void execute(int tick) {
    getAnimation().apply(tick);
  }

  @Override
  public AnimationCommand getCopy(Shapes copy) {
    return new ScaleCommand(scale.getCopy(copy));
  }
}
