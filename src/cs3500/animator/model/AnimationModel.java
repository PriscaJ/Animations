package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.util.TweenModelBuilder;


/**
 * The cs3500.animator.model for how shapes will be called and then animmated.
 */
public class AnimationModel implements AnimationOperations {
  // the shapes that will occur during an animation.
  private List<Animations> animations;
  // HashMap of shape name to shape
  private Map<String, Shapes> shapesMap;
  private List<Shapes> shapes;

  private Map<String, List<AnimationCommand>> shapeToCommands;

  /**
   * The constructor for the model.
   */
  public AnimationModel() {
    shapesMap = new HashMap<>();
    animations = new ArrayList<>();
    shapeToCommands = new HashMap<>();
    shapes = new ArrayList<>();
  }


  @Override
  public ArrayList<Shapes> getShapes() {
    return new ArrayList<>(shapes);
  }

  @Override
  public List<Animations> getAnimations() {
    return new ArrayList<>(animations);
  }

  @Override
  public int getEndTime() {
    int max = 0;
    for (Shapes shape : shapesMap.values()) {
      int comp = shape.getDisappears();
      if (comp > max) {
        max = comp;
      }
    }
    return max;
  }

  /**
   * This is a helper method to check if a command will be compatible with any existing commands. A
   * command is incompatible with another if they occur during the same time frame, for the same
   * shape. Two overlapping commands of the same type are not allowed.
   *
   * @param command is the command being added.
   * @return whether the command will interfere with another command.
   */
  private boolean animationsCollide(AbstractAnimation command) {
    for (Animations c : animations) {
      // if they are the same type of command for the same shape
      if (command.type == c.getType() && command.name.equals(c.getName())) {
        // if they are running in the same time frame
        if (!(c.getStart() >= command.getFinish() || c.getFinish() <= command.startTime)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This is a helper method to add a shape to an animation by adding it to the HashMap of shape
   * name to shape and to the list of all shapes.
   */
  protected void addShape(AbstractShape shape) {
    shapesMap.put(shape.getName(), shape);
    shapes.add(shape);
  }

  /**
   * Check if a command to be added has an animation that is compatible with the current animations
   * in the model. If so, set the animation's shape to be the correct shape, and add the command to
   * the list and the map.
   *
   * @param command to be added.
   */
  protected void addCommand(AnimationCommand command) {
    String shapeName = command.getAnimation().getName();
    Shapes shape = shapesMap.get(shapeName);
    shape.addCommand(command);
    if (animationsCollide(command.getAnimation())) {
      throw new IllegalArgumentException("not allowed");
    }
    command.getAnimation().setAnimatingShape(shapesMap.get(shapeName));

    if (shapeToCommands.containsKey(shapeName)) {
      List<AnimationCommand> currList = shapeToCommands.get(shapeName);
      currList.add(command);
      shapeToCommands.put(shapeName, currList);
      animations.add(command.getAnimation());

    } else {
      List<AnimationCommand> newList = new ArrayList<>();
      newList.add(command);
      shapeToCommands.put(shapeName, newList);
      animations.add(command.getAnimation());
    }
  }

  //////////---------------------- BUILDER ---------------------------////////////

  /**
   * This is an embedded class in the model that builds the model by creating and adding shapes and
   * animations based upon the input file.
   */
  public static final class Builder implements TweenModelBuilder<AnimationOperations> {
    AnimationModel model;

    public Builder() {
      model = new AnimationModel();
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addOval(String name, float cx, float cy, float xRadius, float yRadius,
        float red, float green, float blue, int startOfLife, int endOfLife, int layer, float radian) {
      if (endOfLife < startOfLife) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (name == null) {
        throw new IllegalArgumentException("Shapes must have names");
      }
      AbstractShape oval = new Oval(name, cx, cy, xRadius, yRadius, red, green, blue,
                  startOfLife, endOfLife, layer, radian);
      model.addShape(oval);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations> addRectangle(String name, float lx, float ly,
        float width, float height, float red,
        float green, float blue,
        int startOfLife, int endOfLife,
        int layer, float radian) {
      if (endOfLife < startOfLife) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (name == null) {
        throw new IllegalArgumentException("Shapes must have names");
      }
      AbstractShape rect =
          new Rectangle(name, lx, ly, width, height, red, green, blue,
              startOfLife, endOfLife, layer, radian);
      model.addShape(rect);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addMove(String name, float moveFromX, float moveFromY,
        float moveToX, float moveToY, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      Move move = new Move(name, moveFromX, moveFromY,
          moveToX, moveToY, startTime, endTime);
      MoveCommand command = new MoveCommand(move);
      model.addCommand(command);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addColorChange(String name, float oldR, float oldG, float oldB,
        float newR, float newG, float newB, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      ColorChange color = new ColorChange(name,
          oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
      ColorCommand command = new ColorCommand(color);
      model.addCommand(command);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addScaleToChange(String name, float fromSx, float fromSy,
        float toSx, float toSy, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      ScaleChange scale = new ScaleChange(name,
          fromSx, fromSy, toSx, toSy, startTime, endTime);
      ScaleCommand command = new ScaleCommand(scale);
      model.addCommand(command);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations> addRotateChange(String name, float fromRadian,
        float toRadian, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      Rotate scale = new Rotate(name,
          fromRadian, toRadian, startTime, endTime);
      RotateCommand command = new RotateCommand(scale);
      model.addCommand(command);
      return this;
    }

    @Override
    public AnimationOperations build() {
      return model;
    }
  }
}
