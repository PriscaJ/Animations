package model;

import java.util.ArrayList;

/**
 * The model for how shapes will be called and then animmated.
 */
public class AnimationModel implements AnimationOperations{
  // the shapes that will occur during an animation.
  private ArrayList<Shapes> shapes;

  public  AnimationModel() {
    shapes = new ArrayList<Shapes>();
  }


  @Override
  public Shape getShape() {
    return null;
  }

  @Override
  public String readBack() {
    StringBuilder workString = new StringBuilder();

    // description of the shapes
    workString.append("Shapes:\n");
    for (Shapes s: shapes) {
      workString.append(s.toString());
    }
    return workString.toString();
  }
}
