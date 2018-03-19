package cs3500.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.Animations;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Shapes;

public class TextualView implements IView {
  AnimationOperations model;
  Appendable ap;
  List<Shapes> allShapes;
  List<Animations> allAnimations;

  // todo: hand the textual view [the shapes] and [ the animations]
  // and have it loop through and call getDescription on all?
  // what does tight coupling look like?
  public TextualView(AnimationOperations model, Appendable ap, List<Shapes> allShapes,
      List<Animations> allAnimations) {
    this.model = model;
    this.ap = ap;
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
    try {
      // all the work is handled in the readBack method,
      // just send to the appendable
      ap.append(output);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    // create a file?
    try (PrintWriter out = new PrintWriter("text-transcript.txt")) {
      out.println(output);
    } catch (FileNotFoundException fe) {
      throw new RuntimeException(fe);
    }

    // or both?
  }
}
