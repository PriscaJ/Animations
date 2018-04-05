package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.Shapes;

public class JListShape extends JPanel {

  private JList<Shapes> shapesInAnimation;
  private List<Shapes> selectedValuesList;

  /**
   * This is the constructor for a JList of Shapes. These shapes can be selected and deselected.
   * @param shapeList is the list of shapes to be added to the JList.
   */
  public JListShape(ArrayList<Shapes> shapeList) {
    DefaultListModel<Shapes> loadedShapes = new DefaultListModel<>();

    // makes the model
    // todo: add shape name instead of shape, select shape by name in view
    for (Shapes shape : shapeList) {
      loadedShapes.addElement(shape);
    }

    // puts shape in the model in the list
    shapesInAnimation = new JList<>(loadedShapes);

    // deselecting shapes
    shapesInAnimation.setSelectionModel(new DefaultListSelectionModel() {
      private static final long serialVersionUID = 1L;

      boolean gestureStarted = false;

      @Override
      public void setSelectionInterval(int index0, int index1) {
        if (!gestureStarted) {
          if (isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
          } else {
            super.addSelectionInterval(index0, index1);
          }
        }
        gestureStarted = true;
      }

      @Override
      public void setValueIsAdjusting(boolean isAdjusting) {
        if (!isAdjusting) {
          gestureStarted = false;
        }
      }

    });

    // shapes in list wait to be selected to be added to the running list
    shapesInAnimation.addListSelectionListener((ListSelectionEvent e) -> {
      if (!e.getValueIsAdjusting()) {
        selectedValuesList = shapesInAnimation.getSelectedValuesList();
      }
    });

    add(shapesInAnimation);
    add(new JScrollPane(shapesInAnimation));

    this.setVisible(true);
  }

  /**
   * Gives the view the list of shapes that have been selected from the JList.
   * @return the list of shapes that have been selected.
   */
  public List<Shapes> getSelected() {
    return selectedValuesList;
  }


}
