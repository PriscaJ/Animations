package model;

/**
 * Abstract class for animations lifting fields and behavior that occur in all animations.
 */
public class AbstractAnimation implements Animations {
  private int start, finish;

  AbstractAnimation(int start, int finish) {
    this.start = start;
    this.finish = finish;
  }
  @Override
  public void apply() {

  }
}
