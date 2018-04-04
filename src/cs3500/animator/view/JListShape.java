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
        if(!gestureStarted){
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

    //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //this.setTitle("List of shapes");
    //this.setSize(200, 500);
    //this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  // todo with the running list play those shapes

  public List<Shapes> getSelected() {
    return selectedValuesList;
  }


}
