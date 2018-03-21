package cs3500.animator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.Shapes;

/**
 * Creates the textual representation of the animation by creating separated by shapes and actions
 * ordered by occurrence.
 */
public class TextualView implements IView {
  private String outFile;
  private List<Shapes> allShapes;
  private List<Animations> allAnimations;
  private int ticksPerSec;

  /**
   * The constructor for the Textual View.
   *
   * @param outFile The file it outputs to.
   * @param allShapes All the shapes in the animation.
   * @param allAnimations All the animation actions that will be preformed.
   * @param ticksPerSec The speed of the animation by ticks per seconds.
   */
  public TextualView(String outFile, List<Shapes> allShapes,
      List<Animations> allAnimations, int ticksPerSec) {
    this.outFile = outFile;
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.ticksPerSec = ticksPerSec;
  }

  protected String readBack() {
    StringBuilder s = new StringBuilder();
    s.append("Shapes:");
    for (Shapes a : allShapes) {
      s.append("\n");
      s.append(a.getDescription(ticksPerSec)).append("\n");
    }
    for (Animations cmd : allAnimations) {
      s.append("\n");
      s.append(cmd.getDescription(ticksPerSec));
    }
    return s.toString();
  }

  @Override
  public void makeVisible() {
    if (outFile.equals("out")) {
      System.out.print(readBack());
    } else {
      try {
        File file = new File(outFile);
        String output = readBack();
        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
      } catch (IOException ioe) {
        //
      }
    }
  }
}
