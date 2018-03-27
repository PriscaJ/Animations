package cs3500.animator.view;

public interface IHybridView extends IView{
  void stopTimer();

  // Reset animation = loop with current shapes
  void setTickToZero();

  void increaseSpeed();

  void decreaseSpeed();

  void start();

  void setLooping();

  // in interface
  void exportSVG();
}
