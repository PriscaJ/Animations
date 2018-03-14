package model;

/**
 * Abstract class for animations lifting fields and behavior that occur in all animations.
 */
public abstract class AbstractAnimation implements Animations {
  private int start;
  private int finish;
  private Object initalStage;
  private Object finalStage;

  AbstractAnimation(int start, int finish, Object initialStage, Object finalStage) {
    this.start = start;
    this.finish = finish;
    this.initalStage = initialStage;
    this.finalStage = finalStage;
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

  @Override
  public Object getInitialStage() {
    return initalStage;
  }

  @Override
  public Object getFinalStage() {
    return finalStage;
  }

  @Override
  public abstract String toString();
}
