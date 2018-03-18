package cs3500.animator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import cs3500.animator.model.IReadOnlyModel;

public class TextualView implements IView {
  IReadOnlyModel model;
  Appendable ap;

  public TextualView(IReadOnlyModel model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void makeVisible() {
    String output = model.readBack();

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
