package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * The model for how shapes will be called and then animmated.
 */
public class AnimationModel implements AnimationOperations {
  // the shapes that will occur during an animation.
  private ArrayList<Shapes> shapes;
  // hashmap of shape name to shape
  private Map<String, Shapes> allShapes = new HashMap<>();


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





}
