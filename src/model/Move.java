package model;

import java.awt.*;

public class Move extends AbstractAnimation{
  Point initialStage, finalStage;

  public Move(int start, int finish, Point initialStage, Point finalStage) {
    super(start, finish);
    this.initialStage = initialStage;
    this.finalStage = finalStage;
  }


  @Override
  public void apply() {

  }
}
