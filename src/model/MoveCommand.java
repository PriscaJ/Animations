package model;

public class MoveCommand implements AnimationCommand {
  private Move move;

  public MoveCommand(Move move){
    this.move = move;
  }

  @Override
  public Animations getAnimation() {
    return move;
  }

  @Override
  public void execute() {
    move.apply();
  }

  @Override
  public String toString() {
    return move.toString();
  }
}
