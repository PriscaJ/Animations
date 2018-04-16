package cs3500.animator.model;

import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.SimpleAnimation;

public class ModelAdapter extends AnimationModel implements SimpleAnimation{
  AnimationOperations oldModel;
  AnimationModel concreteModel;

  public ModelAdapter(AnimationOperations oldModel) {
    this.oldModel = oldModel;
    concreteModel = new AnimationModel();
  }


  @Override
  public void animate() throws IllegalArgumentException {
    animate();
  }

  @Override
  public String printAnimation() {
    // moved into the textual view and taken out of the model
    return "";
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    addShape(shape);
  }

  @Override
  public void addCommand(Command command, Shape s) throws IllegalArgumentException {
    addCommand(command, s);
  }

  @Override
  public Shape getShapeWithName(String name) {
    // todo need to access the hashmap in the concrete model
    return null;
  }

  @Override
  public List<Shape> getShapes() {
    // todo conflicting shapes????
    return (List) oldModel.getShapes();
  }
}
