package cs3500.animator.view;

public interface IHybridView extends IView{
  void stopTimer();

  void increaseSpeed();

  void decreaseSpeed();

  void start();

  void setLooping();

  // Reset animation = loop with current shapes
  void restart();

  // in interface
  void exportSVG();
}
