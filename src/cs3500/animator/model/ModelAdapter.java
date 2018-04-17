package cs3500.animator.model;

import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.SimpleAnimation;

<<<<<<< HEAD
public class ModelAdapter implements SimpleAnimation{
  // AnimationOperations oldModel;
  AnimationModel concreteModel;

  public ModelAdapter(AnimationModel concreteModel) {
    //this.oldModel = oldModel;
    this.concreteModel = concreteModel;
=======
public class ModelAdapter extends AnimationModel implements SimpleAnimation{
  // AnimationOperations oldModel;
  AnimationModel concreteModel;

  public ModelAdapter(AnimationOperations oldModel) {
    //this.oldModel = oldModel;
    concreteModel = new AnimationModel();
>>>>>>> 5ad03cdc7bc21570f5bd993b2ec3624442b3e16d
  }


  @Override
  public void animate() throws IllegalArgumentException {
    // ignore
  }

  @Override
  public String printAnimation() {
    // moved into the textual view and taken out of the model
    return "";
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    concreteModel.addShape((AbstractShape)shape);
  }

  @Override
  public void addCommand(Command command, Shape s) throws IllegalArgumentException {
    concreteModel.addCommand((AnimationCommand) command);
  }

  @Override
  public Shape getShapeWithName(String name) {
    // todo need to access the hashmap in the concrete model
    for (Shapes s : concreteModel.getShapes()) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    return null;
  }

  @Override
  public List<Shape> getShapes() {
    // todo conflicting shapes????
    return (List) concreteModel.getShapes();
  }
}
