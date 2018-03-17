package cs3500.animator.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationOperations;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.VisualView;

public class Controller {

  public static void run(String[] args) {
    Controller c = new Controller();

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
    if (typeOfView == null) {
      throw new IllegalArgumentException("Type of view must be specified.");
    }
    switch (typeOfView) {
      case "text":
        c.startTextView(animationFileName, outputDest, ticksPerSec);
        break;
      case "svg":
        c.startSVGView(animationFileName, outputDest, ticksPerSec);
        break;
      case "visual":
        c.startVisualView(animationFileName, ticksPerSec);
        break;
      default:
        System.out.println("Invalid type of view");
    }
  }

  private AnimationOperations createModel(String animationFileName) {
    AnimationFileReader fileReader = new AnimationFileReader();
    AnimationModel.Builder modelBuilder = new AnimationModel.Builder();
    try {
      return fileReader.readFile(animationFileName, modelBuilder);
    } catch (FileNotFoundException exception) {
      System.out.println(String.format("Cannot find file: %s.", animationFileName));
    }
    System.exit(1);
    return null;
  }

  private void startVisualView(String animationFileName, String ticksPerSec) {
    AnimationOperations model = createModel(animationFileName);

  }

  private void startSVGView(String animationFileName, String outputDest, String ticksPerSec) {
    int tps = getTicksPerSec(ticksPerSec);
    AnimationOperations model = createModel(animationFileName);


  }

  private void startTextView(String file, String outFile, String ticksPerSec) {
    int tps = getTicksPerSec(ticksPerSec);
    AnimationOperations model = createModel(file);

  }

  private int getTicksPerSec(String ticksPerSec) {
    try {
      return Integer.parseInt(ticksPerSec);
    }
    catch (NumberFormatException ex) {
      return 1;
    }
  }
}
