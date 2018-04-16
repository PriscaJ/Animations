package cs3500.animator.model;

import com.sun.xml.internal.ws.util.HandlerAnnotationInfo;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

public class CommandAdapter implements Command {
  AnimationCommand oldCmd;

  public CommandAdapter(AnimationCommand oldCmd) {
    this.oldCmd = oldCmd;
  }

  @Override
  public void execute(Shape s, int tick) {

  }

  @Override
  public void convertToTicks(int tempo) {

  }

  @Override
  public void setStartTick(double st) {

  }

  @Override
  public void setEndTick(double e) {

  }

  @Override
  public double delta(double a, double b, int tick) {
    return 0;
  }

  @Override
  public String printCommand(Shape s, int tempo) {
    return null;
  }

  @Override
  public double getStartTime() {
    return 0;
  }

  @Override
  public double getEndTime() {
    return 0;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String printAnimation(Shape s, int speedFactor, boolean loop) {
    return null;
  }

  @Override
  public boolean sameType(Command that) {
    return false;
  }

  @Override
  public boolean overlappingTimes(Command that) {
    return false;
  }
}
