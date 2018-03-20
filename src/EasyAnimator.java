//package cs3500.animator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    String outputDest = null;
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
        view = createTextView(animationFileName, outputDest, ticksPerSec, model);
        break;
      case "svg":
        view = createSVGView(animationFileName, outputDest, ticksPerSec, model);
        break;
      case "visual":
        view = createVisualView(animationFileName, ticksPerSec, model);
        break;
      default:
        System.out.println("Invalid type of view");
        System.exit(1);
    }
    if (view == null || model == null) {
      System.out.println("Must create model and view.");
      System.exit(1);
    }
    else {
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


  private static VisualView createVisualView(String animationFileName, String ticksPerSec,
      AnimationOperations model) {
    return new VisualView();
  }

  private static SVGView createSVGView(String animationFileName, String outputDest, String ticksPerSec,
      AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    return new SVGView(new ArrayList<>(), "");


  }

  private static TextualView createTextView(String file, String outFile,
      String ticksPerSec, AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    List<Shapes> shapes = model.getShapes();
    List<Animations> animations = model.getAnimations();
    FileWriter output;
    TextualView view;

    try {
      output = new FileWriter(outFile);
      BufferedWriter out = new BufferedWriter(output);
      // temporarily changed model from ReadOnly to AnimationOperations
      view = new TextualView(out, shapes, animations);
      return view;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static int getTicksPerSec(String ticksPerSec) {
    try {
      return Integer.parseInt(ticksPerSec);
    }
    catch (NumberFormatException ex) {
      return 1;
    }
  }
}