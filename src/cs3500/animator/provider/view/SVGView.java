package cs3500.animator.provider.view;

import java.io.IOException;
import java.util.List;

import cs3500.animator.provider.model.*;

import cs3500.misc.Helper;
import cs3500.shapes.Shape;

/**
 * View to represent animation as an SVG file that can be saved and run elsewhere.
 */
public class SVGView implements View {

  private final String path;
  private List<Shape> shapes;
  private int tempo;
  /**
   * Constructor for SVGView.
   *
   * @param path where this file will be saved.
   */
  public SVGView(List<Shape> shapes, String path, int tempo) {
    this.path = path;
    this.shapes = shapes;
    this.tempo = tempo;
  }

  @Override
  public void showView() throws IOException {

    String svgText = Helper.printSVG(shapes, true, tempo);

    if (path.equals("")) {
      System.out.println(svgText);
    } else {
      Helper.writeToFile(svgText, path);
    }
  }
}
