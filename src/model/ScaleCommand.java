package model;

public class ScaleCommand implements AnimationCommand {
  ScaleChange scale;

  public ScaleCommand(ScaleChange scale) {
    this.scale = scale;
  }

  @Override
  public Animations getAnimation() {
    return scale;
  }

  @Override
  public void execute() {
    getAnimation().apply();
  }

  @Override
  public String toString() {
    return scale.toString();
  }
}
