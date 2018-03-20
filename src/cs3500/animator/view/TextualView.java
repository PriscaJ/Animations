package cs3500.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.Shapes;

public class TextualView implements IView {
  String outFile;
  List<Shapes> allShapes;
  List<Animations> allAnimations;

  // todo: hand the textual view [the shapes] and [ the animations]
  // and have it loop through and call getDescription on all?
  // what does tight coupling look like?
  public TextualView(String outFile, List<Shapes> allShapes,
      List<Animations> allAnimations, int ticksPerSec) {
    this.outFile = outFile;
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
  }

  private String readBack() {
    StringBuilder s = new StringBuilder();
    s.append("Shapes:");
    for (Shapes a : allShapes) {
      s.append("\n");
      s.append(a.getDescription()).append("\n");
    }
    for (Animations cmd : allAnimations) {
      s.append("\n");
      s.append(cmd.getDescription());
    }
    return s.toString();
  }

  @Override
  public void makeVisible() {
    String output = readBack();
    // simply append?
    // create a file?
    try (PrintWriter out = new PrintWriter(outFile)) {
      out.println(output);
    } catch (FileNotFoundException fe) {
      throw new RuntimeException(fe);
    }

    // or both?
  }
}
