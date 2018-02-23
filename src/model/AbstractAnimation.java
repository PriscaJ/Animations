package model;

/**
 * Abstract class for animations lifting fields and behavior that occur in all animations.
 */
public abstract class AbstractAnimation implements Animations {
  private int start, finish;

  AbstractAnimation(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }

  @Override
  public abstract void apply();


  @Override
  public int getStart() {
    return start;
  }

  @Override
  public int getFinish() {
    return finish;
  }
}
