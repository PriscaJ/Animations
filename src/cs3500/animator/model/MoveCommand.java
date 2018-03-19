package cs3500.animator.model;

/**
 * Primarily  uses its field of Move to apply the Action which is relayed back to the shape.
 */
public class MoveCommand implements AnimationCommand {
  private Move move;

  public MoveCommand(Move move) {
    this.move = move;
  }

  @Override
  public AbstractAnimation getAnimation() {
    return move;
  }

  @Override
  public void execute(int tick) {
    move.apply(tick);
  }

  @Override
  public String toString() {
    return move.toString();
  }
}
