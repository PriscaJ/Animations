package cs3500.animator.model;

import com.sun.xml.internal.ws.util.HandlerAnnotationInfo;

import cs3500.animator.provider.model.Command;
import cs3500.animator.provider.model.Shape;

public class CommandAdapter implements Command {
  AnimationCommand oldCmd;
  Animations oldAnimations;

  public CommandAdapter(AnimationCommand oldCmd, Animations oldAnimations) {
    this.oldCmd = oldCmd;
    this.oldAnimations = oldAnimations;
  }

  @Override
  public void execute(Shape s, int tick) {
    oldCmd.execute(tick);
  }

  @Override
  public void convertToTicks(int tempo) {
    // ignore
  }

  @Override
  public void setStartTick(double st) {
    // setting is done on the intitialization of an animation
  }

  @Override
  public void setEndTick(double e) {
    // setting is done on the intitialization of an animation
  }

  @Override
  public double delta(double a, double b, int tick) {
   return oldAnimations.calculateChange((float) a, (float) b, tick);
  }

  @Override
  public String printCommand(Shape s, int tempo) {
    return oldCmd.toString();
  }

  @Override
  public double getStartTime() {
    return oldAnimations.getStart();
  }

  @Override
  public double getEndTime() {
    return oldAnimations.getFinish();
  }

  @Override
  public String getName() {
    return oldAnimations.toString();
  }

  @Override
  public String printAnimation(Shape s, int speedFactor, boolean loop) {
    return oldCmd.toString();
  }

  @Override
  public boolean sameType(Command that) {
    return false;
  }

  @Override
  public boolean overlappingTimes(Command that) {
    return false;
    // this is checked privately in the model.
  }
}
