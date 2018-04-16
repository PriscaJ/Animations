package cs3500.animator.provider.view;

import java.awt.Graphics;
import java.awt.Color;

import java.util.List;

import javax.swing.JPanel;
import cs3500.animator.provider.model.*;

import cs3500.misc.Colors;
import cs3500.shapes.Shape;

/**
 * This class renders the specifics of the animation and is what is displayed by the visual
 * view.
 */
public class JDraw extends JPanel {

  private final List<cs3500.animator.provider.model.Shape> shapes;

  /**
   * Constructor for JDraw.
   * @param shapes the list of shapes to be rendered.
   */
  public JDraw(List<Shape> shapes) {
    this.shapes = shapes;
  }

  public List<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (Shape shape : shapes) {

      switch (shape.getType()) {
        case "Rectangle":
          if (shape.isVisible()) {
            drawRectangle(g, shape);
          }
          break;
        case "Oval":
          if (shape.isVisible()) {
            drawOval(g, shape);
          }
          break;
        default:
          throw new IllegalArgumentException("Cannot draw a shape that is not implemented.");
      }
    }
  }

  /**
   * Draw a rectangle based on the parameters of the shape.
   * @param g the graphics for this render
   * @param shape the shape to be rendered
   */
  private void drawRectangle(Graphics g, Shape shape) {
    Colors color = shape.getColor();

    g.setColor(new Color(
            (float)color.getR(),
            (float)color.getG(),
            (float)color.getB()));

    g.fillRect((int)shape.getPosition().getX(),
            (int)shape.getPosition().getY(),
            (int)shape.getWidth(),
            (int)shape.getHeight());
  }

  /**
   * Draw an oval based on the parameters of this shape.
   * @param g the graphics for this render
   * @param shape the shape to be rendered
   */
  private void drawOval(Graphics g, Shape shape) {
    Colors color = shape.getColor();

    g.setColor(new Color(
            (float)color.getR(),
            (float)color.getG(),
            (float)color.getB()));

    g.fillOval((int)shape.getPosition().getX(),
            (int)shape.getPosition().getY(),
            (int)shape.getWidth(),
            (int)shape.getHeight());
  }
}

