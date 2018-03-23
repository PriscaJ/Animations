//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cs3500.animator.controller.Controller;
//import cs3500.animator.model.AbstractAnimation;
//import cs3500.animator.model.AbstractShape;
//import cs3500.animator.model.AnimationCommand;
//import cs3500.animator.model.AnimationModel;
//import cs3500.animator.model.AnimationOperations;
//import cs3500.animator.model.AnimationType;
//import cs3500.animator.model.Animations;
//import cs3500.animator.model.ColorChange;
//import cs3500.animator.model.ColorCommand;
//import cs3500.animator.model.Move;
//import cs3500.animator.model.MoveCommand;
//import cs3500.animator.model.Oval;
//import cs3500.animator.model.Rectangle;
//import cs3500.animator.model.ScaleChange;
//import cs3500.animator.model.ScaleCommand;
//import cs3500.animator.model.Shapes;
//import cs3500.animator.util.AnimationFileReader;
//import cs3500.animator.util.TweenModelBuilder;
//import cs3500.animator.view.IView;
//import cs3500.animator.view.SVGView;
//import cs3500.animator.view.TextualView;
//import cs3500.animator.view.VisualView;
//
//public class all {
//  package cs3500.animator.controller;
//
//import cs3500.animator.model.AnimationOperations;
//import cs3500.animator.view.IView;
//
//  /**
//   * Class that represents the controller which handles the communication between the model and the
//   * the view. Runs the view based on the information of the model.
//   */
//  public class Controller {
//
//    private AnimationOperations model;
//    private IView view;
//
//    /**
//     * Constructor for the controller.
//     *
//     * @param model The Model that the controller gets information from
//     * @param view  The view that the controller sends information to.
//     */
//    public Controller(AnimationOperations model, IView view) {
//      this.model = model;
//      this.view = view;
//    }
//
//    /**
//     * Runs the view by making it visible to the user.
//     */
//    public void run() {
//      view.makeVisible();
//    }
//  }
//
//  package cs3500.animator.model;
//
//  /**
//   * Abstract class for animations lifting fields and behavior that occur in all animations.
//   */
//  public abstract class AbstractAnimation implements Animations {
//
//    protected String name;
//    protected float startX;
//    protected float startY;
//    protected float endX;
//    protected float endY;
//    protected int startTime;
//    protected int endTime;
//    protected Shapes animatingShape = null;
//    protected AnimationType type = null;
//
//    /**
//     * Initialize the abstract command with the specific characteristics. Used for
//     * cs3500.animator.model.Move and cs3500.animator.model.Scale.
//     *
//     * @param name      is the name of the shape to be animated.
//     * @param startX    is the starting x characteristic. (X coordinate or width)
//     * @param startY    is the starting y characteristic.
//     * @param endX      is the ending x characteristic.
//     * @param endY      is the ending y characteristic.
//     * @param startTime is the time the animation starts.
//     * @param endTime   is the time the animation ends.
//     */
//    public AbstractAnimation(String name, float startX, float startY,
//                             float endX, float endY, int startTime, int endTime) {
//      this.name = name;
//      this.startX = startX;
//      this.startY = startY;
//      this.endX = endX;
//      this.endY = endY;
//      this.startTime = startTime;
//      this.endTime = endTime;
//    }
//
//    /**
//     * The constructor for an animation given a name and starttime and endtime.
//     *
//     * @param name      is the name of the shape to be animated.
//     * @param startTime is the time the animation starts.
//     * @param endTime   is the time the animation ends.
//     */
//    public AbstractAnimation(String name, int startTime, int endTime) {
//      this.name = name;
//      this.startTime = startTime;
//      this.endTime = endTime;
//    }
//
//    @Override
//    public void setAnimatingShape(Shapes s) {
//      this.animatingShape = s;
//    }
//
//    @Override
//    public float calculateChange(float startValue, float endValue, float tick) {
//      return startValue * (((float) this.endTime - tick) / (float) (this.endTime - this.startTime))
//              + endValue * (tick - (float) this.startTime) / (float) (this.endTime - this.startTime);
//    }
//
//    /**
//     * Various getters for the fields of a shape.
//     *
//     * @return Object that is one of the fields.
//     */
//    @Override
//    public float getOldR() {
//      return 0;
//    }
//
//    @Override
//    public float getOldG() {
//      return 0;
//    }
//
//    @Override
//    public float getOldB() {
//      return 0;
//    }
//
//    @Override
//    public float getNewR() {
//      return 0;
//    }
//
//    @Override
//    public float getNewG() {
//      return 0;
//    }
//
//    @Override
//    public float getNewB() {
//      return 0;
//    }
//
//    @Override
//    public abstract void apply(int tick);
//
//    @Override
//    public String getName() {
//      return this.name;
//    }
//
//    @Override
//    public int getStart() {
//      return startTime;
//    }
//
//    @Override
//    public int getFinish() {
//      return endTime;
//    }
//
//    @Override
//    public AnimationType getType() {
//      return this.type;
//    }
//
//    @Override
//    public float getStartX() {
//      return this.startX;
//    }
//
//    @Override
//    public float getEndX() {
//      return this.endX;
//    }
//
//    @Override
//    public float getStartY() {
//      return this.startY;
//    }
//
//    @Override
//    public float getEndY() {
//      return this.endY;
//    }
//  }
//
//
//
//package cs3500.animator.model;
//
//import java.util.ArrayList;
//
//  /**
//   * Class that shapes can extend to keep similar functionality in one place.
//   */
//
//  public abstract class AbstractShape implements Shapes {
//    // qualities of a shape
//    protected String name;
//    protected float xPosn;
//    protected float yPosn;
//    protected float xDimension;
//    protected float yDimension;
//    protected float red;
//    protected float green;
//    protected float blue;
//    protected int startOfLife;
//    protected int endOfLife;
//    // the animations that the shape invokes
//    private ArrayList<AnimationCommand> commands = new ArrayList<>();
//
//    /**
//     * Constructor for a shape.
//     * @param name shape name.
//     * @param xPosn Shape postion.
//     * @param yPosn Shape y position.
//     * @param xDimension The width of a Shape.
//     * @param yDimension The Height of a Shape.
//     * @param red The red color.
//     * @param green The green color.
//     * @param blue The blue color.
//     * @param startOfLife The appears time.
//     * @param endOfLife The disappears time.
//     */
//    public AbstractShape(String name,
//                         float xPosn, float yPosn, float xDimension, float yDimension,
//                         float red, float green, float blue,
//                         int startOfLife, int endOfLife) {
//      this.name = name;
//      this.xPosn = xPosn;
//      this.yPosn = yPosn;
//      this.xDimension = xDimension;
//      this.yDimension = yDimension;
//      this.red = red;
//      this.green = green;
//      this.blue = blue;
//      this.startOfLife = startOfLife;
//      this.endOfLife = endOfLife;
//    }
//
//    @Override
//    public void addCommand(AnimationCommand c) {
//      commands.add(c);
//    }
//
//    @Override
//    public void setAnimation(AnimationCommand newCommand) {
//      // does the action applied to the shape occur during the lifespan of the shape
//      if (newCommand.getAnimation().getStart() < getAppears()) {
//        throw new IllegalArgumentException("Cannot apply Action to shape");
//      }
//      if (newCommand.getAnimation().getFinish() > getDisappears()) {
//        throw new IllegalArgumentException("Cannot apply Action to shape");
//      }
//      commands.add(newCommand);
//    }
//
//    @Override
//    public ArrayList<AnimationCommand> getCommands() {
//      return commands;
//    }
//
//    @Override
//    public void setXPosn(float v) {
//      this.xPosn = v;
//    }
//
//    @Override
//    public void setYPosn(float v) {
//      this.yPosn = v;
//    }
//
//    @Override
//    public void setXDimension(float v) {
//      this.xDimension = v;
//    }
//
//    @Override
//    public void setYDimension(float v) {
//      this.yDimension = v;
//    }
//
//    @Override
//    public String getName() {
//      return name;
//    }
//
//    @Override
//    public Float getWidth() {
//      return xDimension;
//    }
//
//    @Override
//    public Float getHeight() {
//      return yDimension;
//    }
//
//    @Override
//    public int getAppears() {
//      return startOfLife;
//    }
//
//    @Override
//    public int getDisappears() {
//      return endOfLife;
//    }
//
//    @Override
//    public Float getXPosition() {
//      return xPosn;
//    }
//
//    @Override
//    public Float getYPosition() {
//      return yPosn;
//    }
//
//    @Override
//    public Float getRed() {
//      return red;
//    }
//
//    @Override
//    public void setRed(float v) {
//      this.red = v;
//    }
//
//    @Override
//    public Float getGreen() {
//      return green;
//    }
//
//    @Override
//    public void setGreen(float v) {
//      this.green = v;
//    }
//
//    @Override
//    public Float getBlue() {
//      return blue;
//    }
//
//    @Override
//    public void setBlue(float v) {
//      this.blue = v;
//    }
//  }
//package cs3500.animator.model;
//
//  /**
//   * Interface for commands to decouple the AbstractShape from the animation.
//   */
//  public interface AnimationCommand {
//
//    /**
//     * Gets the animation that this command acts on.
//     *
//     * @return Animations that the command is acting on.
//     */
//    cs3500.animator.model.AbstractAnimation getAnimation();
//
//    /**
//     * Used to call the Animation action it acts on. Used to invoke the command at the given time.
//     */
//    void execute(int tick);
//
//    /**
//     * Helper to the readback in order to get the string representation of the actionon the particular
//     * shape.
//     *
//     * @return String of what the animation action is doing to pass to the AbstractShape.
//     */
//    String toString();
//  }
//
//  package cs3500.animator.model;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cs3500.animator.util.TweenModelBuilder;
//
//
//  /**
//   * The cs3500.animator.model for how shapes will be called and then animmated.
//   */
//  public class AnimationModel implements AnimationOperations {
//    // the shapes that will occur during an animation.
//    private List<Animations> animations;
//    // hashmap of shape name to shape
//    private Map<String, Shapes> shapesMap;
//    private List<Shapes> shapes;
//
//    private Map<String, List<cs3500.animator.model.AnimationCommand>> shapeToCommands;
//
//    /**
//     * The constructor for the model.
//     */
//    public AnimationModel() {
//      shapesMap = new HashMap<>();
//      animations = new ArrayList<>();
//      shapeToCommands = new HashMap<>();
//      shapes = new ArrayList<>();
//    }
//
//
//    @Override
//    public ArrayList<Shapes> getShapes() {
//      return new ArrayList<>(shapes);
//    }
//
//    @Override
//    public List<Animations> getAnimations() {
//      return new ArrayList<>(animations);
//    }
//
//
//    @Override
//    public int getEndTime() {
//      int max = 0;
//      for (Shapes shape : shapesMap.values()) {
//        int comp = shape.getDisappears();
//        if (comp > max) {
//          max = comp;
//        }
//      }
//      return max;
//    }
//
//
//    /**
//     * This is a helper method to check if a command will be compatible with any existing commands. A
//     * command is incompatible with another if they occur during the same time frame, for the same
//     * shape. Two overlapping commands of the same type are not allowed.
//     *
//     * @param command is the command being added.
//     * @return whether the command will interfere with another command.
//     */
//    private boolean animationsCollide(cs3500.animator.model.AbstractAnimation command) {
//      for (Animations c : animations) {
//        // if they are the same type of command for the same shape
//        if (command.type == c.getType() && command.name.equals(c.getName())) {
//          // if they are running in the same time frame
//          if (!(c.getStart() >= command.getFinish() || c.getFinish() <= command.startTime)) {
//            return true;
//          }
//        }
//      }
//      return false;
//    }
//
//    public void addShape(cs3500.animator.model.AbstractShape shape) {
//      shapesMap.put(shape.getName(), shape);
//      shapes.add(shape);
//    }
//
//    /**
//     * Check if a command to be added has an animation that is compatible with the current animations
//     * in the model. If so, set the animation's shape to be the correct shape, and add the command to
//     * the list and the map.
//     *
//     * @param command to be added.
//     */
//    private void addCommand(cs3500.animator.model.AnimationCommand command) {
//      String shapeName = command.getAnimation().getName();
//      Shapes shape = shapesMap.get(shapeName);
//      shape.addCommand(command);
//      if (animationsCollide(command.getAnimation())) {
//        throw new IllegalArgumentException("not allowed");
//      }
//      command.getAnimation().setAnimatingShape(shapesMap.get(shapeName));
//
//      if (shapeToCommands.containsKey(shapeName)) {
//        List<cs3500.animator.model.AnimationCommand> currList = shapeToCommands.get(shapeName);
//        currList.add(command);
//        shapeToCommands.put(shapeName, currList);
//        animations.add(command.getAnimation());
//
//      } else {
//        List<cs3500.animator.model.AnimationCommand> newList = new ArrayList<>();
//        newList.add(command);
//        shapeToCommands.put(shapeName, newList);
//        animations.add(command.getAnimation());
//      }
//    }
//
//
//    //////////---------------------- BUILDER ---------------------------////////////
//    public static final class Builder implements TweenModelBuilder<AnimationOperations> {
//      cs3500.animator.model.AnimationModel model;
//
//      public Builder() {
//        model = new cs3500.animator.model.AnimationModel();
//      }
//
//      @Override
//      public TweenModelBuilder<AnimationOperations>
//      addOval(String name, float cx, float cy, float xRadius, float yRadius,
//              float red, float green, float blue, int startOfLife, int endOfLife) {
//        if (endOfLife < startOfLife) {
//          throw new IllegalArgumentException("Invalid Shape");
//        }
//        if (name == null) {
//          throw new IllegalArgumentException("Shapes must have names");
//        }
//        cs3500.animator.model.AbstractShape oval =
//                new Oval(name, cx, cy, xRadius, yRadius, red, green, blue, startOfLife, endOfLife);
//        model.addShape(oval);
//        return this;
//      }
//
//      @Override
//      public TweenModelBuilder<AnimationOperations>
//      addRectangle(String name, float lx, float ly, float width, float height,
//                   float red, float green, float blue, int startOfLife, int endOfLife) {
//        if (endOfLife < startOfLife) {
//          throw new IllegalArgumentException("Invalid Shape");
//        }
//        if (name == null) {
//          throw new IllegalArgumentException("Shapes must have names");
//        }
//        //      Double cxAsDouble = (double) cx;
//        //      Double cyAsDouble = (double) cy;
//        cs3500.animator.model.AbstractShape rect =
//                new Rectangle(name, lx, ly, width, height, red, green, blue, startOfLife, endOfLife);
//        model.addShape(rect);
//        return this;
//      }
//
//      @Override
//      public TweenModelBuilder<AnimationOperations>
//      addMove(String name, float moveFromX, float moveFromY,
//              float moveToX, float moveToY, int startTime, int endTime) {
//        if (endTime < startTime) {
//          throw new IllegalArgumentException("Invalid Shape");
//        }
//        Move move = new Move(name, moveFromX, moveFromY,
//                moveToX, moveToY, startTime, endTime);
//        // model.addAnimation(move);
//        MoveCommand command = new MoveCommand(move);
//        model.addCommand(command);
//        return this;
//      }
//
//      @Override
//      public TweenModelBuilder<AnimationOperations>
//      addColorChange(String name, float oldR, float oldG, float oldB,
//                     float newR, float newG, float newB, int startTime, int endTime) {
//        if (endTime < startTime) {
//          throw new IllegalArgumentException("Invalid Shape");
//        }
//        ColorChange color = new ColorChange(name,
//                oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
//        //model.addAnimation(color);
//        ColorCommand command = new ColorCommand(color);
//        model.addCommand(command);
//        return this;
//      }
//
//      @Override
//      public TweenModelBuilder<AnimationOperations>
//      addScaleToChange(String name, float fromSx, float fromSy,
//                       float toSx, float toSy, int startTime, int endTime) {
//        if (endTime < startTime) {
//          throw new IllegalArgumentException("Invalid Shape");
//        }
//        ScaleChange scale = new ScaleChange(name,
//                fromSx, fromSy, toSx, toSy, startTime, endTime);
//        //model.addAnimation(scale);
//        ScaleCommand command = new ScaleCommand(scale);
//        model.addCommand(command);
//        return this;
//      }
//
//      @Override
//      public AnimationOperations build() {
//        return model;
//      }
//    }
//  }
//package cs3500.animator.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//  /**
//   * An interface holding method behaviors that all models should share in order to animate.
//   */
//  public interface AnimationOperations {
//
//    /**
//     * A method to return a copy of all shapes in an animation.
//     */
//    ArrayList<Shapes> getShapes();
//
//    /**
//     * A method to return a copy of all animations in an animation.
//     */
//    List<Animations> getAnimations();
//
//    int getEndTime();
//
//    /**
//     * Provides the String representation of the animation.
//     * @return String of the animation.
//     */
//    //String readBack();
//  }
//package cs3500.animator.model;
//
//  /**
//   * An interface for various kinds of animations that Shapes can have.
//   */
//  public interface Animations {
//
//    /**
//     * Observational method for color
//     *
//     * @return Float the color value.
//     */
//    float getOldR();
//
//    float getOldG();
//
//    float getOldB();
//
//    float getNewR();
//
//    float getNewG();
//
//    float getNewB();
//
//    /**
//     * Applies the Animation actions and mutates its shape at the given time.
//     */
//    void apply(int tick);
//
//    /**
//     * Various observational methods to retrieve and access private fields when needed.
//     *
//     * @return int or an Object depending.
//     */
//    int getFinish();
//
//    int getStart();
//
//    String getDescription(int tps);
//
//    void setAnimatingShape(Shapes s);
//
//    /**
//     * Depending on what time the animation is at it will show the incrementation of the of an
//     * Action.
//     *
//     * @param startValue The value it begins at.
//     * @param endValue   The value it should end at.
//     * @param tick       The moment in time the action and subsequent change is taking place.
//     * @return Float The difference between the start and the end values.
//     */
//    float calculateChange(float startValue, float endValue, float tick);
//
//    AnimationType getType();
//
//    String getName();
//
//    float getStartX();
//
//    float getEndX();
//
//    float getStartY();
//
//    float getEndY();
//  }
//package cs3500.animator.model;
//
//  /**
//   * This is a class to represent the types of animations available in an animation.
//   */
//  public enum AnimationType {
//    MOVE, COLORCHANGE, SCALECHANGE;
//  }
//
//  package cs3500.animator.model;
//
//  /**
//   * Class that handles the shifts in colors within a shape during an animation.
//   */
//  public class ColorChange extends cs3500.animator.model.AbstractAnimation {
//    private float oldR;
//    private float oldG;
//    private float oldB;
//    private float newR;
//    private float newG;
//    private float newB;
//
//    /**
//     * Create a colorchange object that will change the color of a shape.
//     */
//    public ColorChange(String name, float oldR, float oldG, float oldB,
//                       float newR, float newG, float newB, int startTime, int endTime) {
//      super(name, startTime, endTime);
//      this.oldR = oldR;
//      this.oldG = oldG;
//      this.oldB = oldB;
//      this.newR = newR;
//      this.newG = newG;
//      this.newB = newB;
//      this.type = cs3500.animator.model.AnimationType.COLORCHANGE;
//    }
//
//    @Override
//    public float getOldR() {
//      return oldR;
//    }
//
//    @Override
//    public float getOldG() {
//      return oldG;
//    }
//
//    @Override
//    public float getOldB() {
//      return oldB;
//    }
//
//    @Override
//    public float getNewR() {
//      return newR;
//    }
//
//    @Override
//    public float getNewG() {
//      return newG;
//    }
//
//    @Override
//    public float getNewB() {
//      return newB;
//    }
//
//    @Override
//    public void apply(int tick) {
//      if (tick >= startTime && tick < endTime) {
//
//        animatingShape.setRed(calculateChange(oldR, newR, tick));
//        animatingShape.setGreen(calculateChange(oldG, newG, tick));
//        animatingShape.setBlue(calculateChange(oldB, newB, tick));
//      }
//    }
//
//    @Override
//    public String getDescription(int tps) {
//      return String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to (%.1f,%.1f,%.1f) "
//                      + "from t=%.1fs to t=%.1fs",
//              name, oldR, oldG, oldB, newR, newG, newB, (float) startTime * tps / 1000, (float) endTime * tps / 1000);
//    }
//  }
//package cs3500.animator.model;
//
//  /**
//   * Primarily  uses its field of ColorChange to apply the Action whihc is relayed back to the shape.
//   */
//  public class ColorCommand implements cs3500.animator.model.AnimationCommand {
//    cs3500.animator.model.ColorChange changeColor;
//
//    // This is the constructor for a command that changes a shape's color.
//    public ColorCommand(cs3500.animator.model.ColorChange changeColor) {
//      this.changeColor = changeColor;
//    }
//
//    @Override
//    public cs3500.animator.model.AbstractAnimation getAnimation() {
//      return changeColor;
//    }
//
//    @Override
//    public void execute(int tick) {
//      changeColor.apply(tick);
//    }
//
//    @Override
//    public String toString() {
//      return changeColor.toString();
//    }
//  }
//package cs3500.animator.model;
//
//  /**
//   * Class that handles moving a shape in an animation coupled to the shape by its command.
//   */
//  public class Move extends cs3500.animator.model.AbstractAnimation {
//
//    /**
//     * This is the constructor for a move. It initializes the fields!
//     */
//    public Move(String name, float moveFromX, float moveFromY, float moveToX, float moveToY,
//                int startTime, int endTime) {
//      super(name, moveFromX, moveFromY, moveToX, moveToY, startTime, endTime);
//      this.type = cs3500.animator.model.AnimationType.MOVE;
//    }
//
//    @Override
//    public float getOldR() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public float getOldG() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public float getOldB() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public float getNewR() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public float getNewG() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public float getNewB() {
//      throw new RuntimeException("No color in a move");
//    }
//
//    @Override
//    public void apply(int tick) {
//      if (tick >= startTime && tick <= endTime) {
//        animatingShape.setXPosn(calculateChange(this.startX, this.endX, tick));
//        animatingShape.setYPosn(calculateChange(this.startY, this.endY, tick));
//      }
//    }
//
//    @Override
//    public String getDescription(int tps) {
//      return String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%.1fs to t=%.1fs",
//              name, startX, startY, endX, endY, (float) startTime * tps / 1000,
//              (float) endTime * tps / 1000);
//    }
//
//
//  }
//package cs3500.animator.model;
//
//  /**
//   * Primarily  uses its field of Move to apply the Action which is relayed back to the shape.
//   */
//  public class MoveCommand implements cs3500.animator.model.AnimationCommand {
//    private cs3500.animator.model.Move move;
//
//    /**
//     * The constructor for the command controlling the Move action.
//     *
//     * @param move The Moving action.
//     */
//    public MoveCommand(cs3500.animator.model.Move move) {
//      this.move = move;
//    }
//
//    @Override
//    public cs3500.animator.model.AbstractAnimation getAnimation() {
//      return move;
//    }
//
//    @Override
//    public void execute(int tick) {
//      move.apply(tick);
//    }
//
//    @Override
//    public String toString() {
//      return move.toString();
//    }
//  }
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//import cs3500.animator.controller.Controller;
//import cs3500.animator.model.AnimationModel;
//import cs3500.animator.model.AnimationOperations;
//import cs3500.animator.model.Animations;
//import cs3500.animator.model.Shapes;
//import cs3500.animator.util.AnimationFileReader;
//import cs3500.animator.view.IView;
//import cs3500.animator.view.SVGView;
//import cs3500.animator.view.TextualView;
//import cs3500.animator.view.VisualView;
//
//  /**
//   * The Runner for the Animation.
//   */
//  public final class EasyAnimator {
//    /**
//     * The main method to run the animation.
//     * @param args
//     */
//    public static void main(String[] args) {
//      String animationFileName = null;
//      String typeOfView = null;
//      // if not specified, outputDest is out by default
//      String outputDest = "out";
//      String ticksPerSec = null;
//
//      for (int i = 0; i < args.length; i = i + 2) {
//        String tag = args[i];
//        System.out.print(args[i]);
//        String content = args[i + 1];
//        switch (tag) {
//          // name of animation file
//          case "-if":
//            animationFileName = content;
//            break;
//          // type of view
//          case "-iv":
//            typeOfView = content;
//            break;
//          // where output should go
//          case "-o":
//            outputDest = content;
//            break;
//          // integer-ticks-per-sec
//          case "-speed":
//            ticksPerSec = content;
//            break;
//          default:
//            System.out.println("Invalid input: " + String.join(" ", args) + ".");
//            System.exit(1);
//        }
//      }
//      cs3500.animator.model.AnimationOperations model = createModel(animationFileName);
//      if (typeOfView == null) {
//        throw new IllegalArgumentException("Type of view must be specified.");
//      }
//      IView view = null;
//      switch (typeOfView) {
//        case "text":
//          view = createTextView(outputDest, ticksPerSec, model);
//          break;
//        case "svg":
//          view = createSVGView(outputDest, ticksPerSec, model);
//          break;
//        case "visual":
//          view = createVisualView(model.getShapes(), model.getEndTime(), ticksPerSec);
//          break;
//        default:
//          System.out.println("Invalid type of view");
//          System.exit(1);
//      }
//      if (view == null) {
//        System.out.println("Must create model and view.");
//        System.exit(1);
//      } else {
//        cs3500.animator.controller.Controller c = new cs3500.animator.controller.Controller(model, view);
//        c.run();
//      }
//    }
//
//    private static cs3500.animator.model.AnimationOperations createModel(String animationFileName) {
//      AnimationFileReader fileReader = new AnimationFileReader();
//      cs3500.animator.model.AnimationModel.Builder modelBuilder = new cs3500.animator.model.AnimationModel.Builder();
//      try {
//        return fileReader.readFile(animationFileName, modelBuilder);
//      } catch (FileNotFoundException exception) {
//        System.out.println(String.format("Cannot find file: %s.", animationFileName));
//      }
//      System.exit(1);
//      // JUST A PLACEHOLDER
//      return new cs3500.animator.model.AnimationModel();
//    }
//
//
//    private static VisualView createVisualView(ArrayList<Shapes> shapesList, int lastTick,
//                                               String ticksPerSec) {
//      // list of shapes and last tick
//      int tps = getTicksPerSec(ticksPerSec);
//      return new VisualView(shapesList, lastTick, 1000 / tps);
//    }
//
//    private static SVGView createSVGView(String outputDest, String ticksPerSec,
//                                         cs3500.animator.model.AnimationOperations model) {
//      int tps = getTicksPerSec(ticksPerSec);
//      return new SVGView(model.getShapes(), outputDest, 1000 / tps);
//    }
//
//    private static TextualView createTextView(String outFile,
//                                              String ticksPerSec, cs3500.animator.model.AnimationOperations model) {
//      int tps = getTicksPerSec(ticksPerSec);
//      List<Shapes> shapes = model.getShapes();
//      List<cs3500.animator.model.Animations> animations = model.getAnimations();
//      // temporarily changed model from ReadOnly to AnimationOperations
//      TextualView view = new TextualView(outFile, shapes, animations, 1000 / tps);
//      return view;
//    }
//
//    private static int getTicksPerSec(String ticksPerSec) {
//      try {
//        return Integer.parseInt(ticksPerSec);
//      } catch (NumberFormatException ex) {
//        return 1;
//      }
//    }
//  }
//  package cs3500.animator.view;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cs3500.animator.model.ColorChange;
//import cs3500.animator.model.ColorCommand;
//import cs3500.animator.model.Move;
//import cs3500.animator.model.MoveCommand;
//import cs3500.animator.model.Oval;
//import cs3500.animator.model.Rectangle;
//import cs3500.animator.model.ScaleChange;
//import cs3500.animator.model.ScaleCommand;
//import cs3500.animator.model.Shapes;
//
//import static org.junit.Assert.assertEquals;
//
//  /**
//   * This is a test class to check the output of an SVG view.
//   */
//  public class SVGTest {
//    @Test
//    public void testSVG() {
//      Rectangle r = new Rectangle("R", 200f, 200f, 50f,
//              100f, 1f, 0f, 0f, 1, 100);
//      Oval o = new Oval("C", 500f, 100f, 60f, 30f,
//              0f, 0f, 1f, 6, 100);
//      cs3500.animator.model.Move c = new cs3500.animator.model.Move("R", 200, 200.0f,
//              300.0f, 300.0f, 10, 50);
//      cs3500.animator.model.Move m = new cs3500.animator.model.Move("C", 500, 100,
//              500, 400, 20, 70);
//      cs3500.animator.model.ColorChange cc = new cs3500.animator.model.ColorChange("C", 0f, 0, 1,
//              0, 1, 0, 50, 80);
//      ScaleChange s = new ScaleChange("R", 50, 100, 25, 100, 51, 70);
//      cs3500.animator.model.Move m2 = new cs3500.animator.model.Move("R", 300, 300,
//              200, 200, 70, 100);
//      List<Shapes> shapes = new ArrayList<>();
//      shapes.add(r);
//      shapes.add(o);
//      cs3500.animator.model.MoveCommand mc3 = new cs3500.animator.model.MoveCommand(c);
//      c.setAnimatingShape(r);
//      r.addCommand(mc3);
//      cs3500.animator.model.ColorCommand ac = new cs3500.animator.model.ColorCommand(cc);
//      r.addCommand(ac);
//      cc.setAnimatingShape(r);
//      cs3500.animator.model.MoveCommand mc = new cs3500.animator.model.MoveCommand(m);
//      cs3500.animator.model.MoveCommand mc2 = new cs3500.animator.model.MoveCommand(m2);
//      r.addCommand(mc);
//      m.setAnimatingShape(r);
//      o.addCommand(mc2);
//      m2.setAnimatingShape(o);
//      ScaleCommand sc = new ScaleCommand(s);
//      o.addCommand(sc);
//      s.setAnimatingShape(o);
//      SVGView view = new SVGView(shapes, "hi", 1);
//      assertEquals("<svg width= \"700\"  height= \"500\" version= \"1.1\" "
//              + "xmlns=\"http://www.w3.org/2000/svg\">\n"
//              + "\n"
//              + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
//              + "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
//              + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" "
//              + "begin=\"1.0ms\" dur=\"99.0ms\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" "
//              + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"10ms\" dur=\"40ms\" "
//              + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"30ms\" "
//              + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" "
//              + "/>\n"
//              + "<animate attributeType=\"xml\" begin=\"20ms\" dur=\"50ms\" "
//              + "attributeName=\"y\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
//              + "</rect>\n"
//              + "\n"
//              + "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"120.0\" ry=\"60.0\" "
//              + "fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
//              + "<set attributeName=\"visibility\" attributeType=\"CSS\" "
//              + "to=\"visible\" begin=\"6.0ms\" dur=\"94.0ms\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" "
//              + "attributeName=\"cx\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"70ms\" dur=\"30ms\" "
//              + "attributeName=\"cy\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
//              + "<animate attributeType=\"xml\" begin=\"51ms\" dur=\"19ms\" "
//              + "attributeName=\"rx\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n"
//              + "</ellipse>\n\n", view.svgOutput());
//    }
//  }
//package cs3500.animator.view;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//import cs3500.animator.model.AnimationCommand;
//import cs3500.animator.model.ColorChange;
//import cs3500.animator.model.Move;
//import cs3500.animator.model.Oval;
//import cs3500.animator.model.Rectangle;
//import cs3500.animator.model.ScaleChange;
//import cs3500.animator.model.Shapes;
//
//  /**
//   * Class that represent a view that is in an SVG format where it can be run on a browser due to its
//   * various tags.
//   */
//  public class SVGView implements IView {
//
//    private List<Shapes> allShapes;
//    private String fileName;
//    private int ticksPerSec;
//
//    /**
//     * The constructor for an SVG view.
//     *
//     * @param allShapes   All the shapes running in the animation.
//     * @param fileName    The file name that the SVG will output to.
//     * @param ticksPerSec The speed that the Animation will run.
//     */
//    public SVGView(List<Shapes> allShapes, String fileName, int ticksPerSec) {
//      this.allShapes = allShapes;
//      this.fileName = fileName;
//      this.ticksPerSec = ticksPerSec;
//    }
//
//    @Override
//    public void makeVisible() {
//      asSVG();
//    }
//
//    /**
//     * Formats the SVG for the entire animation.
//     *
//     * @return The String representation of an SVG for the animation
//     */
//    private void asSVG() {
//      String start = "<svg version= \"1.1\" "
//              + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";
//      String end = "</svg>";
//      String output = start + format(allShapes) + end;
//      if (fileName.equals("out")) {
//        System.out.print(output);
//      } else {
//        try {
//          File file = new File(fileName);
//          //Write Content
//          FileWriter writer = new FileWriter(file);
//          writer.write(output);
//          writer.close();
//        } catch (IOException ioe) {
//          // do nothing
//        }
//      }
//    }
//
//
//    /**
//     * This is a method to test the output of an SVG View.
//     *
//     * @return the svg output.
//     */
//    protected String svgOutput() {
//      String start = "<svg width= \"700\"  height= \"500\" version= \"1.1\" "
//              + "xmlns=\"http://www.w3.org/2000/svg\">\n\n";
//      return start + format(allShapes);
//    }
//
//    /**
//     * Formats the rest of the SVG depending on which shape it is.
//     *
//     * @param shapes The list of shapes to be parsed.
//     * @return The String SVG of the rest of the animation.
//     */
//    private String format(List<Shapes> shapes) {
//      StringBuilder workString = new StringBuilder();
//      for (Shapes s : shapes) {
//        if (s instanceof Oval) {
//          workString.append(String.format("<ellipse id=\"%s\" cx=\"%.1f\" cy=\"%.1f\" rx=\"%.1f\" "
//                          + "ry=\"%.1f\" "
//                          + "fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n", s.getName(),
//                  s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
//                  s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
//          workString.append(String.format("<set attributeName=\"visibility\" attributeType=\"CSS\" "
//                          + "to=\"visible\" begin=\"%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",
//                  (float) s.getAppears() * ticksPerSec,
//                  (float) (s.getDisappears() - s.getAppears()) * ticksPerSec)
//          );
//          workString.append("\n");
//
//          workString.append(formatCmd(s.getCommands(), "ellipse"));
//          workString.append("</" + "ellipse" + ">\n\n");
//        }
//
//        if (s instanceof Rectangle) {
//          workString.append(String.format("<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" "
//                          + "height=\"%.1f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
//                  s.getName(), s.getXPosition(), s.getYPosition(), s.getWidth(), s.getHeight(),
//                  s.getRed() * 255, s.getGreen() * 255, s.getBlue() * 255));
//          workString.append(String.format("<set attributeName=\"visibility\" attributeType=\"CSS\""
//                          + " to=\"visible\" begin=\"%.1fms\" dur=\"%.1fms\" fill=\"freeze\" />",
//                  (float) s.getAppears() * ticksPerSec,
//                  (float) (s.getDisappears() - s.getAppears()) * ticksPerSec)
//          );
//          workString.append("\n");
//          workString.append(formatCmd(s.getCommands(), "rect"));
//          workString.append("</" + "rect" + ">\n\n");
//        }
//      }
//      return workString.toString();
//    }
//
//    /**
//     * Takes a copy of the commands and parses through to properly format SVG.
//     *
//     * @param cmds copy of the animation commands that a shape has.
//     * @return Proper SVG format for Animation commands.
//     */
//    private String formatCmd(List<cs3500.animator.model.AnimationCommand> cmds, String shapeType) {
//      StringBuilder workString = new StringBuilder();
//      String workString2;
//      for (cs3500.animator.model.AnimationCommand a : cmds) {
//        workString2 = "<animate attributeType=\"xml\" begin=\"";
//        workString2 += a.getAnimation().getStart() * ticksPerSec + "ms\" dur=\""
//                + ((a.getAnimation().getFinish() - a.getAnimation().getStart()) * ticksPerSec)
//                + "ms\" attributeName=\"" //+ attributeCmd()
//        ;
//        switch (a.getAnimation().getType()) {
//          case MOVE:
//            workString.append(formatMove((cs3500.animator.model.Move) a.getAnimation(), shapeType, workString2));
//            break;
//          case COLORCHANGE:
//            workString.append(formatColor((cs3500.animator.model.ColorChange) a.getAnimation(), workString2));
//            break;
//          case SCALECHANGE:
//            workString.append(formatScale((ScaleChange) a.getAnimation(), shapeType, workString2));
//            break;
//        }
//        // todo: fill freeze or fill remove??
//        //workString.append("fill=\"freeze\" />\n");
//      }
//      return workString.toString();
//    }
//
//    /**
//     * Formats the Command move as SVG animate tag.
//     *
//     * @param move  Takes in a Move class in order to ask for fields
//     * @param shape Determines what kind of shape it is to change attributeName.
//     * @return The full animate tag for this command.
//     */
//    private String formatMove(cs3500.animator.model.Move move, String shape, String starter) {
//      String workString = "";
//      String additional = "";
//      String attributeX = "";
//      String attributeY = "";
//
//      // used to determine how to change the attribute name
//      if (shape.equals("rect")) {
//        attributeX = "x";
//        attributeY = "y";
//      } else if (shape.equals("ellipse")) {
//        attributeX = "cx";
//        attributeY = "cy";
//      }
//      if (move.getStartX() != move.getEndX()) {
//        workString = starter + attributeX + "\" "
//                + "from=\"" + move.getStartX() + "\" to=\"" + move.getEndX() + "\" "
//                + "fill=\"freeze\" />\n";
//      }
//      if (move.getStartY() != move.getEndY()) {
//        // if the x has also changed, make a new tag
//        additional += starter + attributeY + "\" "
//                + "from=\"" + move.getStartY() + "\" to=\"" + move.getEndY() + "\" ";
//        additional += "fill=\"freeze\" />\n";
//      }
//      return workString + additional;
//    }
//
//    /**
//     * Formats the Command move as SVG animate tag.
//     *
//     * @return The full animate tag for this command.
//     */
//    private String formatColor(cs3500.animator.model.ColorChange c, String starter) {
//      String workString = "";
//
//      workString += starter + "fill\"";
//      workString += String.format(" from=\"rgb(%.0f,%.0f,%.0f)\" to=\"rgb(%.0f,%.0f,%.0f)\"",
//              c.getOldR() * 255, c.getOldG() * 255, c.getOldB() * 255, c.getNewR() * 255, c.getNewG()
//                      * 255, c.getNewB() * 255);
//      workString += " fill=\"freeze\" />\n";
//      return workString;
//    }
//
//    /**
//     * Formats the Command move as SVG animate tag.
//     *
//     * @param sChange Takes in a Move class in order to ask for fields
//     * @param shape   Determines what kind of shape it is to change attributeName.
//     * @return The full animate tag for this command.
//     */
//    private String formatScale(ScaleChange sChange, String shape, String starter) {
//      String workString = "";
//      String workString2 = "";
//      String attributeX = "";
//      String attributeY = "";
//
//      // used to determine how to change the attribute name
//      if (shape.equals("rect")) {
//        attributeX = "width";
//        attributeY = "height";
//      } else if (shape.equals("ellipse")) {
//        attributeX = "rx";
//        attributeY = "ry";
//      }
//
//      if (sChange.getStartX() != sChange.getEndX()) {
//        workString = starter + attributeX + "\" "
//                + "from=\"" + sChange.getStartX()
//                + "\" to=\"" + sChange.getEndX() + "\" " + "fill=\"freeze\" />\n";
//      }
//      if (sChange.getStartY() != sChange.getEndY()) {
//        // if it was changed make a new tag
//        if (!workString.equals("")) {
//          workString2 = starter + attributeY + "\" "
//                  + "from=\"" + sChange.getStartY()
//                  + "\" to=\"" + sChange.getEndY() + "\" ";
//          workString2 += "fill=\"freeze\" />\n";
//        } else {
//          workString2 = starter + attributeY + "\" "
//                  + "from=\"" + sChange.getStartY()
//                  + "\" to=\"" + sChange.getEndY() + "\" " + "fill=\"freeze\" />\n";
//        }
//      }
//      return workString + workString2;
//    }
//  }
//
//
//}
