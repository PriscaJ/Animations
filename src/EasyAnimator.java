import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

//import cs3500.animator.controller.AdapterController;
import cs3500.animator.controller.Controller;
//import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.Animations;
//import cs3500.animator.model.ModelAdapter;
import cs3500.animator.model.Shapes;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.SimpleAnimation;
import cs3500.animator.provider.view.View;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualView;

/**
 * The Runner for the Animation.
 */
public final class EasyAnimator {
  /**
   * The main method to run the animation.
   */
  public static void main(String[] args) throws IOException {
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
          makeErrorMessage("Invalid input: " + String.join(" ", args) + ".");
          System.exit(1);
      }
    }
    AnimationOperations model = createModel(animationFileName);
    if (typeOfView == null) {
      makeErrorMessage("Type of view must be specified.");
      System.exit(1);
    }
    IView view = null;
    View provided_view = null;
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
      case "interactive":
        view = createHybridView(model.getShapes(), model.getEndTime(), ticksPerSec, outputDest);
        break;
//      case "provider":
//
//        SimpleAnimation modelAdapter = new ModelAdapter(model);
//        provided_view = createProviderView(modelAdapter.getShapes(), outputDest);
//        AdapterController adapterController = new AdapterController(modelAdapter, provided_view);
//        adapterController.run();
//        break;
//      case "p-svg":
//        SimpleAnimation modelAdapter2 = new ModelAdapter(model);
//        provided_view = createProviderSVGView(modelAdapter2.getShapes(), outputDest, ticksPerSec);
//        AdapterController adapterController2 = new AdapterController(modelAdapter2, provided_view);
//        adapterController2.run();
//        break;
//      case "p-visual":
//        SimpleAnimation modelAdapter3 = new ModelAdapter(model);
//        provided_view = createProviderVisualView(modelAdapter3.getShapes(), ticksPerSec);
//        AdapterController adapterController3 = new AdapterController(modelAdapter3, provided_view);
//        adapterController3.run();
//        break;
//      case "p-text":
//        SimpleAnimation modelAdapter4 = new ModelAdapter(model);
//        provided_view = createProviderTextView(modelAdapter4.getShapes(), outputDest, ticksPerSec);
//        AdapterController adapterController4 = new AdapterController(modelAdapter4, provided_view);
//        adapterController4.run();
//        break;
      default:
        makeErrorMessage("Invalid type of view");
    }
    if (view == null && provided_view == null) {
      makeErrorMessage("Must create model and view.");
    } else if (view != null) {
      Controller c = new Controller(model, view);
      c.run();
    }
  }

  /**
   * This is a helper method to create the HybridView with information from the model.
   *
   * @param shapes the shapes in the animation.
   * @param endTime the last tick a shape is present.
   * @param ticksPerSec the speed of the animation.
   * @param outputDest the file name for the svg to be exported to.
   * @return the correctly created HybridView.
   */
  private static HybridView createHybridView(ArrayList<Shapes> shapes, int endTime,
      String ticksPerSec, String outputDest) {
    int tps = getTicksPerSec(ticksPerSec);
    return new HybridView(shapes, endTime, outputDest, 1000 / tps);
  }

  //
  //  private static ViewAdapter createProviderView(ArrayList<Shapes> shapes, int endTime,
  //      String ticksPerSec, String outputDest) {
  //    int tps = getTicksPerSec(ticksPerSec);
  //    IView hybridView = new HybridView(shapes, endTime, outputDest, 1000 / tps);
  //    return new ViewAdapter(hybridView);
  //  }
//  private static View createProviderView(List<Shape> shapes, String ticksPerSec)
//          throws IOException {
//    int tps = getTicksPerSec(ticksPerSec);
//    // todo convert model.getShapes to return a list of Shape
//    return new cs3500.animator.provider.view.HybridView(shapes, 1000 / tps);
//  }
//
//  private static View createProviderVisualView(List<Shape> shapesList,
//      String ticksPerSec) {
//    // list of shapes and last tick
//    int tps = getTicksPerSec(ticksPerSec);
//    return new cs3500.animator.provider.view.VisualView(shapesList, 1000 / tps);
//  }
//
//  // Create an SVGView with the given file name, speed, and model.
//  private static View createProviderSVGView(List<Shape> shapesList, String outputDest,
//      String ticksPerSec) {
//    int tps = getTicksPerSec(ticksPerSec);
//    return new cs3500.animator.provider.view.SVGView(shapesList, outputDest, 1000 / tps);
//  }
//
//  // Creates a text view, similar to the other views.
//  private static View createProviderTextView(List<Shape> shapesList, String outFile,
//      String ticksPerSec) {
//    int tps = getTicksPerSec(ticksPerSec);
//    // temporarily changed model from ReadOnly to AnimationOperations
//    return new cs3500.animator.provider.view.TextualView(shapesList, outFile, 1000 / tps);
//  }

  /**
   * This is a helper method to create the model, using the AnimationFileReader and the
   * file the user has selected to create an animation from.
   *
   * @param animationFileName the input file from which the animation will be created.
   * @return the model to be used in the animation.
   */
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

  /**
   * This method creates a visual view, similarly to how the Hybrid view is created.
   *
   * @param shapesList the shapes to be used in the animation.
   * @param lastTick the last tick a shape is present.
   * @param ticksPerSec the speed of the animation.
   * @return the VisualView that has been created.
   */
  private static VisualView createVisualView(ArrayList<Shapes> shapesList, int lastTick,
      String ticksPerSec) {
    // list of shapes and last tick
    int tps = getTicksPerSec(ticksPerSec);
    return new VisualView(shapesList, lastTick, 1000 / tps);
  }

  // Create an SVGView with the given file name, speed, and model.
  private static SVGView createSVGView(String outputDest, String ticksPerSec,
      AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    return new SVGView(model.getShapes(), outputDest, 1000 / tps, false);
  }

  // Creates a text view, similar to the other views.
  private static TextualView createTextView(String outFile,
      String ticksPerSec, AnimationOperations model) {
    int tps = getTicksPerSec(ticksPerSec);
    List<Shapes> shapes = model.getShapes();
    List<Animations> animations = model.getAnimations();
    // temporarily changed model from ReadOnly to AnimationOperations
    return new TextualView(outFile, shapes, animations, 1000 / tps);
  }

  // Interprets the string ticks per second as an integer and sets it as 1 else.
  private static int getTicksPerSec(String ticksPerSec) {
    try {
      return Integer.parseInt(ticksPerSec);
    } catch (NumberFormatException ex) {
      return 1;
    }
  }

  // Creates an error message upon invalid input.
  private static void makeErrorMessage(String message) {
    JFrame frame = new JFrame("Invalid Arguments");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    JOptionPane.showMessageDialog(frame,
        message,
        "Invalid Argument",
        JOptionPane.PLAIN_MESSAGE);
    frame.setVisible(true);
  }
}