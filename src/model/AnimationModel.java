package model;

import java.util.ArrayList;


/**
 * The model for how shapes will be called and then animmated.
 */
public class AnimationModel implements AnimationOperations {
  // the shapes that will occur during an animation.
  private ArrayList<Shapes> shapes;

  public  AnimationModel() {
    shapes = new ArrayList<Shapes>();
  }


  @Override
  public void setShapes(Shapes shape) {
    if (isOverlap()) {
      throw new RuntimeException("Cannot have inconsistent animations on shape");
    }
    shapes.add(shape);
  }

  @Override
  public ArrayList<Shapes> getShape() {
    return shapes;
  }

  @Override
  public String readBack() {
    StringBuilder workString = new StringBuilder();

    if (shapes.isEmpty()) {
      return workString.toString();
    }

    // description of the shapes
    workString.append("Shapes:\n");
    for (Shapes s: shapes) {
      workString.append(s.toString());
    }
    return workString.toString();
  }


  private boolean isOverlap() {
    boolean answer = false;
    for (Shapes s: shapes) {
      ArrayList<AnimationCommand> seen = new ArrayList<AnimationCommand>();
      for (AnimationCommand ac: s.getCommands()) {
        // check if the animation is in the seen list
        // if the animation is the same (contained in seen)
        // then check their times to see if they overlap

      }
    }
    return answer; // stub
  }
}
