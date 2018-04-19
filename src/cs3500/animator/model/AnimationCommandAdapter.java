package cs3500.animator.model;

import cs3500.animator.provider.model.Command;

public class AnimationCommandAdapter implements Animations, AnimationCommand {
  private Command cmd;

  public AnimationCommandAdapter(Command cmd) {
    this.cmd = cmd;
  }

  @Override
  public float getOldR() {
    return 0;
  }

  @Override
  public float getOldG() {
    return 0;
  }

  @Override
  public float getOldB() {
    return 0;
  }

  @Override
  public float getNewR() {
    return 0;
  }

  @Override
  public float getNewG() {
    return 0;
  }

  @Override
  public float getNewB() {
    return 0;
  }

  @Override
  public void apply(int tick) {
    // do nothing
  }

  @Override
  public int getFinish() {
    return 0;
  }

  @Override
  public int getStart() {
    return 0;
  }

  @Override
  public String getDescription(int tps) {
    return null;
  }

  @Override
  public void setAnimatingShape(Shapes s) {
    // do nothing
  }

  @Override
  public float calculateChange(float startValue, float endValue, float tick) {
    return 0;
  }

  @Override
  public AnimationType getType() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public float getStartX() {
    return 0;
  }

  @Override
  public float getEndX() {
    return 0;
  }

  @Override
  public float getStartY() {
    return 0;
  }

  @Override
  public float getEndY() {
    return 0;
  }

  @Override
  public AbstractAnimation getAnimation() {
    return null;
  }

  @Override
  public void execute(int tick) {
    // do nothing
  }
}
