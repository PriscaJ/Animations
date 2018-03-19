//package cs3500.animator.model;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//import javafx.util.Pair;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Tests against the interface of the cs3500.animator.model (AnimationOperations)
// * ensuring all methods work properly.
// */
//public class AnimationModelTest {
//
//  AnimationOperations model;
//  Shapes rect1;
//
//  Point rect1Corner;
//  Point initalMove1;
//  Point finalMove1;
//
//  Color rect1Color;
//  AnimationCommand moveCommand1;
//  Move move1;
//
//
//  @Before
//  public void initData() {
//    model = new AnimationModel();
//    rect1Corner = new Point(200, 200);
//    initalMove1 = new Point(200, 200);
//    finalMove1 = new Point(300, 300);
//    rect1Color = Color.BLUE;
//    rect1 = new AbstractShape("R", ShapeType.RECTANGLE, 50.0, 100.0,
//            10, 50, rect1Corner, rect1Color);
//    move1 = new Move(10, 50, initalMove1, finalMove1);
//    moveCommand1 = new MoveCommand(move1);
//  }
//
//  @Test
//  public void emptyAnimation() {
//    initData();
//    assertEquals("", model.readBack());
//  }
//
//  @Test
//  public void testGetShape() {
//    initData();
//    ArrayList<Shapes> animatedShapes = new ArrayList<Shapes>();
//    animatedShapes.add(rect1);
//    model.setShapes(rect1);
//    assertEquals(animatedShapes, model.getShape());
//  }
//
//  @Test
//  public void testReadBack() {
//    initData();
//    model.setShapes(rect1);
//    assertEquals("Shapes:\n" +
//            "Name: R\n" +
//            "Type: RECTANGLE\n" +
//            "Lower-left corner: (200.0 , 200.0) , Width: 50.0, Height: 100.0\n" +
//            "Appears at t=10\n" +
//            "Disappears at t=50", model.readBack());
//    rect1.setAnimation(moveCommand1);
//    assertEquals("Shapes:\n" +
//            "Name: R\n" +
//            "Type: RECTANGLE\n" +
//            "Lower-left corner: (200.0 , 200.0) , Width: 50.0, Height: 100.0\n" +
//            "Appears at t=10\n" +
//            "Disappears at t=50\n" +
//            "R moves from (200, 200) to (300, 300) from time t=10 to t=50", model.readBack());
//
//  }
//
//  @Test
//  public void TestSetAnimation() {
//    initData();
//    model.setShapes(rect1);
//    assertEquals("", model.readBack());
//  }
//
//  @Test(expected = RuntimeException.class)
//  public void testOverlap() {
//    initData();
//    Move move2 = new Move(20, 50, new Point(200, 200), new Point(100, 100));
//    AnimationCommand move2cmd = new MoveCommand(move2);
//    rect1.setAnimation(moveCommand1);
//    rect1.setAnimation(move2cmd);
//  }
//
//  @Test
//  public void testGoodOverlap() {
//    initData();
//    Pair<Double, Double> start;
//    Pair<Double, Double> finsh;
//    ScaleChange scale1 = new ScaleChange(20, 50,
//            new Pair<Double, Double>(50.0, 100.0), new Pair<Double, Double>(100.0, 50.0));
//    ScaleCommand scaleCommand = new ScaleCommand(scale1);
//    model.setShapes(rect1);
//    rect1.setAnimation(moveCommand1);
//    rect1.setAnimation(scaleCommand);
//    assertEquals("", model.readBack());
//  }
//
//}