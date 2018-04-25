package cs3500.animator.model;

public class Rotate extends AbstractAnimation{

  private float fromRadian;
  private float toRadian;

  Rotate(String name, float fromRadian, float toRadian, int startTime, int endTime) {
    super(name, startTime, endTime);
    this.fromRadian = fromRadian;
    this.toRadian = toRadian;
    this.type = AnimationType.ROTATE;
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
    System.out.print(tick + "\n");
    if (tick >= startTime && tick < endTime) {
      animatingShape.setRadian(calculateChange(fromRadian, toRadian, tick));
      System.out.print(calculateChange(fromRadian, toRadian, tick) + "\n");
    }
  }

  @Override
  public String getDescription(int tps) {
    return String.format("Shape %s rotates from %.1f to "
            + "%.1f from t=%.1fs to t=%.1fs",
        this.name, this.fromRadian, toRadian,
        (float) startTime * tps / 1000, (float) endTime * tps / 1000);
  }

  @Override
  public Rotate getCopy(Shapes copyShape) {
    Rotate copy = new Rotate(name, fromRadian, toRadian, startTime, endTime);
    copy.setAnimatingShape(copyShape);
    return copy;
  }
}
