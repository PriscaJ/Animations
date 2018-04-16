package cs3500.animator.model;

import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.SimpleAnimation;

public class ModelAdapter implements SimpleAnimation{
  AnimationOperations oldModel;

  public ModelAdapter(AnimationOperations oldModel) {
    this.oldModel = oldModel;
  }


  @Override
  public void animate() throws IllegalArgumentException {

  }

  @Override
  public String printAnimation() {
    return null;
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {

  }

  @Override
  public void addCommand(Command command, Shape s) throws IllegalArgumentException {

  }

  @Override
  public Shape getShapeWithName(String name) {
    return null;
  }

  @Override
  public List<Shape> getShapes() {
    return null;
  }
}
