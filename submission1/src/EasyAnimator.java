
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.Animations;
import cs3500.animator.model.Shapes;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualView;

public final class EasyAnimator {
  public static void main(String[] args) {
    String animationFileName = null;
    String typeOfView = null;
    // if not specified, outputDest is out by default
    String outputDest = "out";
    String ticksPerSec = null;

    for (int i = 0; i < args.length; i = i + 2) {
      String tag = args[i];
      System.out.print(args[i]);
      String content = args[i + 1];
      switch (tag) {
        // name of animation file
        case "-if":
          animationFileName = content;
          break;
        // type of view
        case "-iv":
          typeOfView = content;
          break;
        // where output should go
        case "-o":
          outputDest = content;
          break;
        // integer-ticks-per-sec
        case "-speed":
          ticksPerSec = content;
          break;
        default:
          System.out.println("Invalid input: " + String.join(" ", args) + ".");
          System.exit(1);
      }
    }
    AnimationOperations model = createModel(animationFileName);
    if (typeOfView == null) {
      throw new IllegalArgumentException("Type of view must be specified.");
    }
    IView view = null;
    switch (typeOfView) {
      case "text":
        view = createTextView(outputDest, ticksPerSec, model);
        break;
      case "svg":
        view = createSVGView(outputDest, ticksPerSec, model);
        break;
      case "visual":
        view = createVisualView(model.getShapes(), model.getEndTime(), ticksPerSec);
        break;
      default:
        System.out.println("Invalid type of view");
        System.exit(1);
    }
    if (view == null) {
      System.out.println("Must create model and view.");
      System.exit(1);
    } else {
      Controller c = new Controller(model, view);
      c.run();
    }
  }

  private static AnimationOperations createModel(String animationFileName) {
    AnimationFileReader fileReader = new AnimationFileReader();
    AnimationModel.Builder modelBuilder = new AnimationModel.Builder();
    try {
      return fileReader.readFile(animationFileName, modelBuilder);
    } catch (FileNotFoundException exception) {
      System.out.println(String.format("Cannot find file: %s.", animationFileName));
    }
    System.exit(1);
    // JUST A PLACEHOLDER
    return new AnimationModel();
  }


  private static VisualView createVisualView(ArrayList<Shapes> shapesList, int lastTick, String ticksPerSec) {
    // list of shapes and last tick
    int tps = getTicksPerSec(ticksPerSec);
    return new VisualView(shapesList, lastTick, 1000 / tps);
  }

  private static SVGView createSVGView(String outputDest, String ticksPerSec,
      AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    return new SVGView(model.getShapes(), outputDest, 1000 / tps);
  }

  private static TextualView createTextView(String outFile,
      String ticksPerSec, AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    List<Shapes> shapes = model.getShapes();
    List<Animations> animations = model.getAnimations();
    // temporarily changed model from ReadOnly to AnimationOperations
    TextualView view = new TextualView(outFile, shapes, animations, 1000 / tps);
    return view;
  }

  private static int getTicksPerSec(String ticksPerSec) {
    try {
      return Integer.parseInt(ticksPerSec);
    } catch (NumberFormatException ex) {
      return 1;
    }
  }
}