package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.util.TweenModelBuilder;


/**
 * The cs3500.animator.model for how shapes will be called and then animmated.
 */
public final class AnimationModel implements AnimationOperations, IReadOnlyModel {
  // the shapes that will occur during an animation.
  private ArrayList<Shapes> shapes;
  // hashmap of shape name to shape
  private Map<String, Shapes> allShapes = new HashMap<String, Shapes>();
  private Map<String, AnimationCommand> allActions = new HashMap<String, AnimationCommand>();


  public  AnimationModel() {
    shapes = new ArrayList<Shapes>();
  }

  @Override
  public void setShapes(Shapes shape) {
    if (isOverlap()) {
      throw new RuntimeException("Cannot have inconsistent animations on shape");
    }
    allShapes.put(shape.getName(), shape);
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

//  /**
//   * This is a helper method to check if a command will be compatible with any existing commands.
//   * A command is incompatible with another if they occur during the same time frame, for the same
//   * shape. Two overlapping commands of the same type are not allowed.
//   *
//   * @param command is the command being added.
//   * @return whether the command will interfere with another command.
//   */
//  private boolean commandsCollide(AbstractCommand command) {
//    for (AbstractCommand c : commands) {
//      // if they are the same type of command for the same shape
//      if (command.type == c.type && command.name.equals(c.name)) {
//        // if they are running in the same time frame
//        if (!(c.startTime >= command.endTime || c.endTime <= command.startTime)) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }

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

  public static final class Builder implements TweenModelBuilder<AnimationModel> {
    @Override
    public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius
            , float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      return null;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx, float ly
            , float width, float height, float red, float green, float blue, int startOfLife
            , int endOfLife) {
      return null;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY
            , float moveToX, float moveToY, int startTime, int endTime) {
      return null;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG
            , float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      return null;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx
            , float fromSy, float toSx, float toSy, int startTime, int endTime) {
      return null;
    }

    @Override
    public AnimationModel build() {
      return null;
    }
  }



}
