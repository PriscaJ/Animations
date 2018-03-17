package cs3500.animator.model;

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
  private Map<String, Shapes> shapesMap = new HashMap<>();
  private Map<String, List<Animations>> shapeToAnimations = new HashMap<>();


  public AnimationModel() {

  }

  @Override
  public void setShapes(Shapes shape) {

  }

  @Override
  public String readBack() {
    StringBuilder s = new StringBuilder();
    List<Shapes> allShapes = new ArrayList<>(shapesMap.values());
    s.append("Shapes:");
    for (Shapes a : allShapes) {
      s.append("\n");
      s.append(a.getDescription()).append("\n");
    }
    for (Animations cmd : animations) {
      s.append("\n");
      s.append(cmd.getDescription());
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


  //////////---------------------- BUILDER ---------------------------////////////
  public static final class Builder implements TweenModelBuilder<AnimationOperations> {

    private ArrayList<Animations> commands;
    private HashMap<String, AbstractShape> shapes;

    //default constructor
    public Builder() {
      this.commands = new ArrayList<Animations>();
      this.shapes = new HashMap<String, AbstractShape>();
    }

    // constructor that takes in specific animations and shapes
    private Builder(ArrayList<Animations> commands, HashMap<String, AbstractShape> shapes) {
      this.commands = commands;
      this.shapes = shapes;
    }

    // get one shape and change what you need
    // AbstractShape thisShape = this.workShapes.values();
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
      //      Double cxAsDouble = (double) cx;
      //      Double cyAsDouble = (double) cy;
      AbstractShape oval = new Oval(name, new Color(red, green, blue),
          new Point(Math.round(cx), Math.round(cy)),
          Math.round(xRadius), Math.round(yRadius), startOfLife, endOfLife);

      shapes.put(name, oval);

      return new Builder(this.commands, this.shapes);
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
      AbstractShape rect = new Rectangle(name, new Color(red, green, blue),
          new Point(Math.round(lx), Math.round(ly)),
          Math.round(width), Math.round(height), startOfLife, endOfLife);

      shapes.put(name, rect);

      return new Builder(this.commands, this.shapes);
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addMove(String name, float moveFromX, float moveFromY,
        float moveToX, float moveToY, int startTime, int endTime) {

      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      Animations move = new Move(name, startTime, endTime,
          new Point(Math.round(moveFromX), Math.round(moveFromY)),
          new Point(Math.round(moveToX), Math.round(moveToY)));

      commands.add(move);
      return new Builder(this.commands, this.shapes);
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addColorChange(String name, float oldR, float oldG, float oldB,
        float newR, float newG, float newB, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      Animations color = new ColorChange(name,
          oldR, oldG, oldB, newR, newG, newB, startTime, endTime,);

      commands.add(color);
      return new Builder(this.commands, this.shapes);
    }

    @Override
    public TweenModelBuilder<AnimationOperations>
    addScaleToChange(String name, float fromSx, float fromSy,
        float toSx, float toSy, int startTime, int endTime) {
      if (endTime < startTime) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      Animations scale = new ScaleChange(name,
          fromSx, fromSy, toSx, toSy, startTime, endTime);

      commands.add(scale);
      return new Builder(this.commands, this.shapes);
    }

    @Override
    public AnimationOperations build() {
      return new AnimationModel(this.commands, this.shapes);
    }
  }

}
