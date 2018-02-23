package model;

import java.awt.*;

/**
 * Class that handles moving a shape in an animation coupled to the shape by its command.
 */
public class Move extends AbstractAnimation{
  Point initialStage, finalStage;

  public Move(int start, int finish, Point initialStage, Point finalStage) {
    super(start, finish);
    this.initialStage = initialStage;
    this.finalStage = finalStage;
  }


  @Override
  public void apply() {
    // how long the move will last for
    int duration = getFinish() - getStart();
    Point intermediateMove = new Point();

    // distance to goal location broken up into X and Y
    double distX = initialStage.getX() - finalStage.getX();
    double distY = initialStage.getY() - finalStage.getY();

    // how much the shape should move at each time t
    double incrementX = distX / duration;
    double incrementY = distY / duration;

    for (int i = 0; i < duration; i++) {
      // todo fix the apply method
    }
  }
}
