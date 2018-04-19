package cs3500.animator.model;

public class Rotate extends AbstractAnimation{

  private float fromRadian;
  private float toRadian;

  Rotate(String name, float fromRadian, float toRadian, int startTime, int endTime) {
    super(name, startTime, endTime);
    this.fromRadian = fromRadian;
    this.toRadian = toRadian;
  }

  @Override
  public float getFromRadian() {
    return this.fromRadian;
  }

  @Override
  public float getToRadian() {
    return this.toRadian;
  }

  @Override
  public void apply(int tick) {
  }

  @Override
  public String getDescription(int tps) {
    return null;
  }
}
