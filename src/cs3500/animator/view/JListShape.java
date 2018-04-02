package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.Shapes;

public class JListShape extends JFrame {

  private JList<Shapes> shapesInAnimation;
  private List<Shapes> selectedValuesList;


  public JListShape(ArrayList<Shapes> shapeList) {
    DefaultListModel<Shapes> loadedShapes = new DefaultListModel<>();

    // makes the model
    for (Shapes shape : shapeList) {
      loadedShapes.addElement(shape);
    }

    // puts shape in the model in the list
    shapesInAnimation = new JList<>(loadedShapes);

    // shapes in list wait to be selected to be added to the running list
    shapesInAnimation.addListSelectionListener((ListSelectionEvent e) -> {
      if (!e.getValueIsAdjusting()) {
        selectedValuesList = shapesInAnimation.getSelectedValuesList();
      }
    });

    add(shapesInAnimation);
    add(new JScrollPane(shapesInAnimation));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("List of shapes");
    this.setSize(200, 200);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  // todo with the running list play those shapes

  public List<Shapes> getSelected() {
    return selectedValuesList;
  }


  /**
   * call the visual view to run the shapes
   * set up a button to run those shapes
   */
  private void runSelected() {

  }


}
