package cs3500.animator.provider.view;

import java.io.IOException;
import java.util.List;

import cs3500.animator.provider.misc.IHelper;
import cs3500.animator.provider.model.*;

/**
 * View to represent the view in the form of human readable text.
 */
public class  TextualView implements View {

  private final String path;
  private List<Shape> shapes;
  private int tempo;
  /**
   * Constructor for textual view.
   *
   * @param shapes the shapes that compose the animation
   * @param path the path where this text view should be saved
   * @param tempo how many ticks per second
   */
  public TextualView(List<Shape> shapes, String path, int tempo) {
    this.shapes = shapes;
    this.path = path;
    this.tempo = tempo;
  }

  @Override
  public void showView() throws IOException {

    String text = IHelper.printAnimation(shapes, tempo);

    if (path.equals("")) {
      System.out.println(text);
    } else {
      IHelper.writeToFile(text, path);
    }
  }
}
