package cs3500.animator.view;

import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class JListShape extends JFrame {

  private JList<Shapes> shapesInAnimation;


  public JListShape(ArrayList<Shapes> shapeList) {
    DefaultListModel<Shapes> loadedShapes = new DefaultListModel<>();

    // makes the model
    for (Shapes shape: shapeList) {
      loadedShapes.addElement(shape);
    }

    // puts shape in the model in the list
    shapesInAnimation = new JList<Shapes>(loadedShapes);
    add(shapesInAnimation);
    add(new JScrollPane(shapesInAnimation));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("List of shapes");
    this.setSize(200,200);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}
