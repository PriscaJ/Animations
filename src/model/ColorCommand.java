package model;

public class ColorCommand implements AnimationCommand {
  ColorChange changeColor;

  public ColorCommand(ColorChange changeColor) {
    this.changeColor = changeColor;
  }

  @Override
  public Animations getAnimation() {
    return changeColor;
  }

  @Override
  public void execute() {
    changeColor.apply();
  }

  @Override
  public String toString() {
    return changeColor.toString();
  }
}
