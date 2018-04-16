package cs3500.animator.model;

import java.util.List;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

public class ShapeAdapter implements Shape {
  Shapes oldShapes;

  public ShapeAdapter(Shapes oldShapes) {
    this.oldShapes = oldShapes;
  }

  @Override
  public String printShape(int tempo) {
    return null;
  }

  @Override
  public Position2D getPosition() {
    return null;
  }

  @Override
  public void moveBy(double x, double y) {

  }

  @Override
  public void moveTo(Position2D position) {

  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void scale(double newWidth, double newHeight) {

  }

  @Override
  public void changeColor(Colors c) {

  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public Position2D getAnchor() {
    return null;
  }

  @Override
  public double getWidth() {
    return 0;
  }

  @Override
  public double getHeight() {
    return 0;
  }

  @Override
  public Colors getColor() {
    return null;
  }

  @Override
  public List<Command> getCommands() {
    return null;
  }

  @Override
  public int getStartTime() {
    return 0;
  }

  @Override
  public int getEndTime() {
    return 0;
  }

  @Override
  public void addCommand(Command c) {

  }

  @Override
  public void addListCommands(List<Command> commands) {

  }

  @Override
  public Shape clone() {
    return null;
  }

  @Override
  public boolean isVisible() {
    return false;
  }

  @Override
  public void setVisibility(boolean visible) {

  }

  @Override
  public boolean getVisibility() {
    return false;
  }
}
