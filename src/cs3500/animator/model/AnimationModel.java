package cs3500.animator.model;

// import sun.tools.jstat.Scale;

import java.awt.*;
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
  // hashmap of shape name to shape
  private Map<String, Shapes> shapesMap;
  // todo: should this be shape to COMMAND?
  private Map<String, List<AnimationCommand>> shapeToCommands;
  //private Map<String, List<Animations>> shapeToAnimations;

  public AnimationModel() {

  }

  @Override
  public void setShapes(Shapes shape) {
    shapesMap = new HashMap<>();
    animations = new ArrayList<>();
    shapeToCommands = new HashMap<>();
  }

  @Override
  public List<Shapes> getShapes() {
    return new ArrayList<>(shapesMap.values());
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
   * This is a helper method to check if a command will be compatible with any existing commands.
   * A command is incompatible with another if they occur during the same time frame, for the same
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

  private void addShape(AbstractShape shape) {
    if (!shapesMap.containsKey(shape.name)) {
      shapesMap.put(shape.name, shape);
    }
  }

//  private void addAnimation(AbstractAnimation animation) {
//    if (animationsCollide(animation)) {
//      throw new IllegalArgumentException("There cannot be inconsistent animations.");
//    } else {
//      String shapeName = animation.name;
//      if (shapeToAnimations.containsKey(shapeName)) {
//        List<Animations> currList = shapeToAnimations.get(shapeName);
//        currList.add(animation);
//        shapeToAnimations.put(shapeName, currList);
//      } else {
//        List<Animations> newList = new ArrayList<>();
//        newList.add(animation);
//        shapeToAnimations.put(shapeName, newList);
//      }
//      animations.add(animation);
//      animation.animatingShape = shapesMap.get(animation.name);
//
//    }
//  }

  private void addCommand(AnimationCommand command) {
    String shapeName = command.getAnimation().getName();
    if (animationsCollide(command.getAnimation())) {
      throw new IllegalArgumentException("not allowed");
    }
    if (shapeToCommands.containsKey(shapeName)) {
      List<AnimationCommand> currList = shapeToCommands.get(shapeName);
      currList.add(command);
      shapeToCommands.put(shapeName, currList);
    } else {
      List<AnimationCommand> newList = new ArrayList<>();
      newList.add(command);
      shapeToCommands.put(shapeName, newList);
      animations.add(command.getAnimation());
    }
  }



  //////////---------------------- BUILDER ---------------------------////////////
  public static final class Builder implements TweenModelBuilder<AnimationOperations> {
    AnimationModel model = new AnimationModel();

    @Override
    public TweenModelBuilder<AnimationOperations>
    addOval(String name, float cx, float cy, float xRadius, float yRadius,
        float red, float green, float blue, int startOfLife, int endOfLife) {
      if (endOfLife < startOfLife) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (name == null) {
        throw new IllegalArgumentException("Shapes must have names");
      }
      AbstractShape oval =
          new Oval(name, cx, cy, xRadius, yRadius, red, green, blue, startOfLife, endOfLife);
      model.addShape(oval);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addRectangle(String name, float lx, float ly, float width, float height,
        float red, float green, float blue, int startOfLife, int endOfLife) {
      if (endOfLife < startOfLife) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (name == null) {
        throw new IllegalArgumentException("Shapes must have names");
      }
      //      Double cxAsDouble = (double) cx;
      //      Double cyAsDouble = (double) cy;
      AbstractShape rect =
          new Rectangle(name, lx, ly, width, height, red, green, blue, startOfLife, endOfLife);
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
      // model.addAnimation(move);
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
      //model.addAnimation(color);
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
      //model.addAnimation(scale);
      ScaleCommand command = new ScaleCommand(scale);
      model.addCommand(command);
      return this;
    }

    @Override
    public AnimationOperations build() {
      return model;
    }
  }
}
