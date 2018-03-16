package cs3500.animator.model;

/**
 * Class that handles the shifts in colors within a shape during an animation.
 */
public class ColorChange extends AbstractAnimation {
  private Float[] initialStage;
  private Float[] finalStage;

  /**
   * constructor for colorchange
   * @param start the start
   * @param finish the finish
   * @param initialStage this initial
   * @param finalStage the final
   */
  public ColorChange(int start, int finish, Float[] initialStage, Float[] finalStage) {
    super(start, finish, new Float[3], new Float[3]);
    // set to an array that is only of length 3
    //this.initialStage = new Float[3];
    //this.finalStage = new Float[3];
  }

  @Override
  protected float calculateChange(float startValue, float endValue, float tick) {
    return (startValue - endValue) / (getFinish() - getStart());
  }

  @Override
  public void apply(int tick) {
    float updatedR = calculateChange(initialStage[1], finalStage[1], tick);
    float updatedG = calculateChange(initialStage[2], finalStage[2], tick);
    float updatedB = calculateChange(initialStage[3], finalStage[3], tick);

  }

  @Override
  public String toString() {
    StringBuilder workString = new StringBuilder();

    workString.append(" changes from " + getInitialStage()
            + " to " + getFinalStage()
            + "from t=" + getStart()
            + "to t=" + getFinish());
    return workString.toString();
  }
}
