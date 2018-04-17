package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.SimpleAnimation;

public class ModelAdapter implements SimpleAnimation {
  // AnimationOperations oldModel;
  private AnimationModel concreteModel;

  public ModelAdapter(AnimationModel concreteModel) {
    //this.oldModel = oldModel;
    this.concreteModel = concreteModel;
  }


  @Override
  public void animate() throws IllegalArgumentException {
    // do nothing
  }

  @Override
  public String printAnimation() {
    // moved into the textual view and taken out of the model
    return "";
  }

  @Override
  // SHAPE --> SHAPES
  public void addShape(Shape shape) throws IllegalArgumentException {
    // need to turn their shape into a shapes?
    Shapes shapeToAdd = ShapeAdapter.convertShapeToShapes(shape);
    concreteModel.addShape((AbstractShape) shapeToAdd);
  }

  @Override
  public void addCommand(Command command, Shape s) throws IllegalArgumentException {
    // concreteModel.addCommand(command);
    // concreteModel.addCommand();
    // handled by builder
  }

  @Override
  public Shape getShapeWithName(String name) {
    // todo need to access the hashmap in the concrete model
    for (Shapes s : concreteModel.getShapes()) {
      if (s.getName().equals(name)) {
        return new ShapeAdapter(s);
      }
    }
    return null;
  }

  @Override
  // List<Shape> --> List<Shapes>
  // our model will return Shapes, but we want a list of Shape...
  public List<Shape> getShapes() {
    // todo conflicting shapes????
    ShapeAdapter shapeAdapter;
    List<Shape> shapesToReturn = new ArrayList<Shape>();
    List<Shapes> old_shapes = concreteModel.getShapes();
    for (Shapes old_shape : old_shapes) {
      shapeAdapter = new ShapeAdapter(old_shape);
      shapesToReturn.add(shapeAdapter);
    }
    return shapesToReturn;
  }
}
