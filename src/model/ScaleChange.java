package model;

import javafx.util.Pair;

/**
 * Class that alters a shapes dimensions when called by its corresponding command by a shape.
 */
public class ScaleChange extends AbstractAnimation {
  private Pair<Double, Double> initialStage;
  private Pair<Double, Double> finalStage;

  public ScaleChange(int start, int finish,
                     Pair<Double, Double> initialStage, Pair<Double, Double> finalStage) {
    super(start, finish);
    this.initialStage = initialStage;
    this.finalStage = finalStage;
  }

  @Override
  public void apply() {

  }
}
