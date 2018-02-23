package model;

/**
 * Class that handles the shifts in colors within a shape during an animation.
 */
public class ColorChange extends AbstractAnimation {
  private Double[] initialStage;
  private Double[] finalStage;

  public ColorChange(int start, int finish, Double[] initialStage, Double[] finalStage) {
    super(start, finish, new Double[3], new Double[3]);
    // set to an array that is only of length 3
    //this.initialStage = new Double[3];
    //this.finalStage = new Double[3];
  }

  @Override
  public void apply() {

  }
}
