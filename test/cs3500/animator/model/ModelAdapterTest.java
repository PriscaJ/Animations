package cs3500.animator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ModelAdapterTest {


  @Test
  public void testGetShapes() {
    AnimationModel model = new AnimationModel();
    List<AbstractShape> curShapes = new ArrayList<>();

    // create a shape
    AbstractShape shape = new Rectangle("R", 200.0f, 200.0f, 50.0f,
        100.0f, 1.0f, 0.0f, 0.0f, 1, 100);

    // add the same shape to the model and the expected outcome list
    curShapes.add(shape);
    model.addShape(shape);


    ModelAdapter adapter = new ModelAdapter(model);
    assertEquals(adapter.getShapes(), curShapes);
  }

}
