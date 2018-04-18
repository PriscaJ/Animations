package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.provider.misc.Colors;
import cs3500.animator.provider.misc.IColors;
import cs3500.animator.provider.misc.IPosition2D;
import cs3500.animator.provider.misc.Position2D;
import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

public class ShapeAdapter implements Shape {
  private Shapes oldShapes;


  public ShapeAdapter(Shapes oldShapes) {
    this.oldShapes = oldShapes;
  }

  public static Shapes convertShapeToShapes(Shape shape) {
    if (shape.getType().equals("rect")) {
      return new Rectangle(shape.getName(), (float) shape.getPosition().getX(),
          (float) shape.getPosition().getY(), (float) shape.getWidth(), (float) shape.getHeight(),
          (float) shape.getColor().getR(),
          (float) shape.getColor().getG(), (float) shape.getColor().getB(), shape.getStartTime(),
          shape.getEndTime());
    } else if (shape.getType().equals("ellipse")) {
      return new Oval(shape.getName(), (float) shape.getPosition().getX(),
          (float) shape.getPosition().getY(), (float) shape.getWidth(), (float) shape.getHeight(),
          (float) shape.getColor().getR(),
          (float) shape.getColor().getG(), (float) shape.getColor().getB(), shape.getStartTime(),
          shape.getEndTime());
    }
    throw new IllegalArgumentException("this is a weird shape, man.");
  }


  @Override
  public String printShape(int tempo) {
    return oldShapes.getDescription(tempo);
  }

  @Override
  public IPosition2D getPosition() {
    // do nothing or should it be vvv
    return new Position2D(oldShapes.getXPosition(), oldShapes.getYPosition());
  }

  @Override
  public void moveBy(double x, double y) {
    oldShapes.setXPosn(oldShapes.getXPosition() + (float) x);
    oldShapes.setYPosn(oldShapes.getYPosition() + (float) y);
  }

  @Override
  public void moveTo(IPosition2D position) {
    oldShapes.setXPosn((float) position.getX());
    oldShapes.setYPosn((float) position.getY());
    //    Move makeMove =
    //        new Move(getName(), (float) getPosition().getX(), (float) getPosition().getY(),
    //            (float) position.getX(), (float) position.getY(), getStartTime(), getEndTime());
    //    MoveCommand move = new MoveCommand(makeMove);
    //    //move.execute();

  }

  @Override
  public String getName() {
    return oldShapes.getName();
  }

  @Override
  // todo what does this do lol
  public void scale(double newWidth, double newHeight) {
    //    ScaleChange makeScale;
    //    makeScale =
    //        new ScaleChange(oldShapes.getName(), oldShapes.getXPosition(), oldShapes.getYPosition(),
    //            (float) newWidth, (float) newHeight, oldShapes.getAppears(), oldShapes.getDisappears());

    //    ScaleCommand scaleCommand = new ScaleCommand(makeScale);
    oldShapes.setXDimension((float) newWidth);
    oldShapes.setYDimension((float) newHeight);
    //    CommandAdapter c = new CommandAdapter(scaleCommand, scaleCommand.getAnimation());
    //    ShapeAdapter shapeAdapter = new ShapeAdapter(oldShapes);
    //    c.execute(shapeAdapter, 4);

  }

  @Override
  public void changeColor(IColors c) {
    oldShapes.setRed((float) c.getR());
    oldShapes.setGreen((float) c.getG());
    oldShapes.setBlue((float) c.getB());
    //    ColorChange makeColor;
    //    makeColor =
    //        new ColorChange(getName(), (float) getColor().getR(), (float) getColor().getG(),
    //            (float) getColor().getB(), (float) c.getR(), (float) c.getG(), (float) c.getB(),
    //            getStartTime(), getEndTime());
    //    ColorCommand color = new ColorCommand(makeColor);
  }

  @Override
  public String getType() {
    if (oldShapes.isOval()) {
      return "Oval";
    } else {
      return "Rectangle";
    }
  }

  @Override
  public IPosition2D getAnchor() {
    return new Position2D(oldShapes.getXPosition(), oldShapes.getYPosition());
  }

  @Override
  public double getWidth() {
    return oldShapes.getWidth();
  }

  @Override
  public double getHeight() {
    return oldShapes.getHeight();
  }

  @Override
  public IColors getColor() {
    return new Colors(oldShapes.getRed(), oldShapes.getGreen(), oldShapes.getBlue());
  }

  @Override
  public List<Command> getCommands() {
    List<AnimationCommand> animationCommands = oldShapes.getCommands();
    List<Command> commandsToReturn = new ArrayList<>();
    for (AnimationCommand ac : animationCommands) {
      CommandAdapter commandAdapter = new CommandAdapter(ac, ac.getAnimation());
      commandsToReturn.add(commandAdapter);
    }
    return commandsToReturn;
  }

  @Override
  public int getStartTime() {
    return oldShapes.getAppears();
  }

  @Override
  public int getEndTime() {
    return oldShapes.getDisappears();
  }

  @Override
  // todo turn a Command to an AnimationCommand
  //todo this is weird
  public void addCommand(Command c) {

    // make the two way adapter
    // oldShapes.addCommand((AnimationCommand) c);
    // commands c --> commandAdapter
    //
    // todo convert command to the correct type -- handled privately in builder???
  }

  @Override
  public void addListCommands(List<Command> commands) {
    // ignore
  }

  @Override
  public Shape clone() {
    // ignore
    return null;
  }

  @Override
  public boolean isVisible() {
    return true;
  }

  @Override
  public boolean getVisibility() {
    return true;
  }

  @Override
  public void setVisibility(boolean visible) {
    visible = true;
  }
}
