package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The cs3500.animator.model for how shapes will be called and then animmated.
 */
public class AnimationModel implements AnimationOperations {
  // list of animaions ordered by time of addition
  private List<AbstractAnimation> animations;
  private Map<String, ArrayList<AbstractAnimation>> animationMap = new HashMap<>();
  private final Map<String, Shapes> shapesMap = new HashMap<>();


  public  AnimationModel() {

  }

  @Override
  public void setShapes(Shapes shape) {

  }

  @Override
  public String readBack() {
    StringBuilder s = new StringBuilder();
    List<Shapes> allShapes = new ArrayList<>(shapesMap.values());
    s.append("Shapes:");
    for (Shapes sh : allShapes) {
      s.append("\n");
      s.append(sh.getDescription()).append("\n");
    }
    for (Animations anim : animations) {
      s.append("\n");
      s.append(anim.getDescription());
    }
    return s.toString();
  }

  /**
   * This is a helper method to check if a command will be compatible with any existing commands.
   * A command is incompatible with another if they occur during the same time frame, for the same
   * shape. Two overlapping commands of the same type are not allowed.
   *
   * @param command is the command being added.
   * @return whether the command will interfere with another command.
   */
  private boolean commandsCollide(AbstractAnimation command) {
    for (AbstractAnimation c : animations) {
      // if they are the same type of command for the same shape
      if (command.type == c.type && command.name.equals(c.name)) {
        // if they are running in the same time frame
        if (!(c.startTime >= command.endTime || c.endTime <= command.startTime)) {
          return true;
        }
      }
    }
    return false;
  }
}
