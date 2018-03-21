package cs3500.animator.model;

/**
 * Abstract class for animations lifting fields and behavior that occur in all animations.
 */
public abstract class AbstractAnimation implements Animations {

  protected String name;
  protected float startX;
  protected float startY;
  protected float endX;
  protected float endY;
  protected int startTime;
  protected int endTime;
  protected Shapes animatingShape = null;
  protected AnimationType type = null;

  /**
   * Initialize the abstract command with the specific characteristics. Used for cs3500.animator.model.Move and cs3500.animator.model.Scale.
   *
   * @param name is the name of the shape to be animated.
   * @param startX is the starting x characteristic. (X coordinate or width)
   * @param startY is the starting y characteristic.
   * @param endX is the ending x characteristic.
   * @param endY is the ending y characteristic.
   * @param startTime is the time the animation starts.
   * @param endTime is the time the animation ends.
   */
  public AbstractAnimation(String name, float startX, float startY,
      float endX, float endY, int startTime, int endTime) {
    this.name = name;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * The constructor for an animation given a name and starttime and endtime.
   *
   * @param name is the name of the shape to be animated.
   * @param startTime is the time the animation starts.
   * @param endTime is the time the animation ends.
   */
  public AbstractAnimation(String name, int startTime, int endTime) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public void setAnimatingShape(Shapes s) {
    this.animatingShape = s;
  }

  @Override
  public float calculateChange(float startValue, float endValue, float tick) {
    return startValue * (((float) this.endTime - tick) / (float) (this.endTime - this.startTime))
        + endValue * (tick - (float) this.startTime) / (float) (this.endTime - this.startTime);
  }

  @Override
  public float getOldR(){return 0;}

  @Override
  public float getOldG(){return 0;}

  @Override
  public float getOldB(){return 0;}

  @Override
  public float getNewR(){return 0;}

  @Override
  public float getNewG(){return 0;}

  @Override
  public float getNewB(){return 0;}

  @Override
  public abstract void apply(int tick);

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getStart() {
    return startTime;
  }

  @Override
  public int getFinish() {
    return endTime;
  }

  @Override
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public float getStartX() {
    return this.startX;
  }

  @Override
  public float getEndX() {
    return this.endX;
  }

  @Override
  public float getStartY() {
    return this.startY;
  }

  @Override
  public float getEndY() {
    return this.endY;
  }
}



