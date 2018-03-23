package cs3500.animator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

<<<<<<< HEAD
/**
 * Class representing tests for model.
 */
public class AnimationModelTest {

  @Test
=======
// This is a class to test the methods in the model of an animation.
public class AnimationModelTest {

  @Test
  public void testAddShape() {
    AnimationModel model = new AnimationModel();
    AnimationOperations model2 = new AnimationModel();

    // create a shape
    AbstractShape shape = new Rectangle("R", 200.0f, 200.0f, 50.0f,
        100.0f, 1.0f, 0.0f, 0.0f, 1, 100);

    // add a shape to the model but not model2
    model.addShape(shape);

  }

  @Test
>>>>>>> 67e9a101365856cdf8c12c5642d244a34a537c64
  public void testGetCurrentShapes() {
    AnimationModel model = new AnimationModel();
    List<AbstractShape> curShapes = new ArrayList<>();

    // create a shape
    AbstractShape shape = new Rectangle("R", 200.0f, 200.0f, 50.0f,
            100.0f, 1.0f, 0.0f, 0.0f, 1, 100);

    // add the same shape to the model and the expected outcome list
    curShapes.add(shape);
    model.addShape(shape);

    assertEquals(model.getShapes(), curShapes);
  }
}
