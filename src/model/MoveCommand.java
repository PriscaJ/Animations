package model;

public class MoveCommand implements AnimationCommand {
  private Move move;

  public MoveCommand(Move move){
    this.move = move;
  }

  @Override
  public void execute() {
    move.apply();
  }
}
