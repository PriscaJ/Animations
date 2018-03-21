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
<<<<<<< HEAD
    float ans = startValue * (((float)this.endTime - tick) / (float)(this.endTime - this.startTime))
            + endValue * (tick - (float)this.startTime) / (float)(this.endTime - this.startTime);
    return ans;
=======
    return startValue * (((float) this.endTime - tick) / (float) (this.endTime - this.startTime))
        + endValue * (tick - (float) this.startTime) / (float) (this.endTime - this.startTime);
>>>>>>> 763da5ef2dbe1be53327d78aa47f4bee7b59a4bf
  }

  public abstract float getOldR();

  public abstract float getOldG();

  public abstract float getOldB();

  public abstract float getNewR();

  public abstract float getNewG();

  public abstract float getNewB();

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



