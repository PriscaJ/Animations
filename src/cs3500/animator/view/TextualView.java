package cs3500.animator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cs3500.animator.model.Animations;
import cs3500.animator.model.Shapes;

public class TextualView implements IView {
  String outFile;
  List<Shapes> allShapes;
  List<Animations> allAnimations;
  int ticksPerSec;

  // todo: hand the textual view [the shapes] and [ the animations]
  // and have it loop through and call getDescription on all?
  // what does tight coupling look like?
  public TextualView(String outFile, List<Shapes> allShapes,
      List<Animations> allAnimations, int ticksPerSec) {
    this.outFile = outFile;
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.ticksPerSec = ticksPerSec;
  }

  private String readBack() {
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
      // or both?
    }
  }
}
