//package cs3500.animator;

import cs3500.animator.controller.Controller;

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
    Controller.run(animationFileName, typeOfView, outputDest, ticksPerSec);
  }
}