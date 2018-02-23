package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AnimationModelTest {

  AnimationOperations model;
  Shapes rect1;

  Point rect1Corner;
  Point initalMove1;
  Point finalMove1;

  Color rect1Color;
  AnimationCommand moveCommand1;
  Move move1;


  @Before
  void initData() {
    model = new AnimationModel();
    rect1Corner = new Point(200, 200);
    initalMove1 = new Point(200, 200);
    finalMove1 = new Point(300, 300);
    rect1Color = Color.BLUE;
    rect1 = new Shape("R", ShapeType.RECTANGLE, 50.0, 100.0,
            10, 50, rect1Corner, rect1Color);
    move1 = new Move(10, 50, initalMove1, finalMove1);
    moveCommand1 = new MoveCommand(move1);
  }

  @Test
  public void testGetShape() {
    initData();
    ArrayList<Shapes> animatedShapes = new ArrayList<Shapes>();
    animatedShapes.add(rect1);
    model.setShapes(rect1);
    assertEquals(animatedShapes, model.getShape());
  }

  @Test
  public void testReadBack() {

  }

  @Test
  public void TestSetAnimation() {

  }

  @Test
  public void testAnimateShape() {

  }





}