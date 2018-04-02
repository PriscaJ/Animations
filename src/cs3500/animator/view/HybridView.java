package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class HybridView extends JFrame implements IHybridView{
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
  private JButton incSpeed, decSpeed, stop, start, restart, loop, svgExport, runSelected;
  private JPanel buttonPanel;
  private JListShape shapeList;
  private JScrollPane scrollingShapes;
  private InteractivePanel interactivePanel;
  // looping is set to be false initially
  private boolean looping = false;
  private boolean selecting = false;
  private String outputDest;
  private int tps;


  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    super();
    this.allShapes = shapes;
    this.selectedShapes = shapes;
    this.endTime = endTime;
    this.outputDest = outputDest;
    this.tps = tps;

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel,BorderLayout.SOUTH);

    // the list of shapes
    shapeList = new JListShape(shapes);

    // pane that will display all the shapes
    // todo: add the pane so that the shapes can be selected 

    // set up for buttons

    // speed
    incSpeed = new JButton("Increase Speed");
    incSpeed.addActionListener((ActionEvent e)-> {increaseSpeed();});
    buttonPanel.add(incSpeed);

    decSpeed = new JButton("Decrease Speed");
    decSpeed.addActionListener((ActionEvent e)-> {decreaseSpeed();});
    buttonPanel.add(decSpeed);

    // stop/ start / restart
    stop = new JButton("Stop");
    stop.addActionListener((ActionEvent e)-> {stopTimer();});
    buttonPanel.add(stop);

    start = new JButton("Start");
    start.addActionListener((ActionEvent e)-> {start();});
    buttonPanel.add(start);

    restart = new JButton("Restart");
    restart.addActionListener((ActionEvent e)-> {restart();});
    buttonPanel.add(restart);

    // looping
    String loopCondition = "";
    if (looping) {
      loopCondition = "Turn off looping";
    }
    else {
      loopCondition = "Turn on looping";
    }
    loop = new JButton(loopCondition);
    loop.addActionListener((ActionEvent e)-> {setLooping();});
    buttonPanel.add(loop);

    // svg export
    svgExport = new JButton("Export in SVG");
    svgExport.addActionListener((ActionEvent e)-> {exportSVG();});
    buttonPanel.add(svgExport);

    // run selected
    runSelected = new JButton("Run Selected Shapes");
    runSelected.addActionListener((ActionEvent e)-> {runSelected();});
    buttonPanel.add(runSelected);

    this.pack();
  }

  // Pause animation
  @Override
  public void stopTimer() {
    interactivePanel.stopTimer();
  }

  @Override
  public void makeVisible() {
    this.start();
  }

  @Override
  public void increaseSpeed() {
    interactivePanel.increaseSpeed();
  }

  @Override
  public void decreaseSpeed() {
    interactivePanel.decreaseSpeed();
  }

  // Start the animation with the initial shapes.
  @Override
  public void start() {
    visualView = new VisualView(allShapes, endTime, tps, looping);
    visualView.makeVisible();
  }

  @Override
  public void setLooping() {
    looping = !looping;
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    interactivePanel.setTickToZero();
  }

  // in interface
  @Override
  public void exportSVG() {
    SVGView svgView;
    if (looping) {
      svgView = new SVGView(selectedShapes, outputDest, tps, true);
    } else {
      svgView = new SVGView(selectedShapes, outputDest, tps, false);
    }
    svgView.makeVisible();
  }

  @Override
  public void runSelected() {
    selectedShapes = (ArrayList<Shapes>) shapeList.getSelected();
    visualView = new VisualView(selectedShapes, endTime, tps, looping);
    visualView.makeVisible();
  }



}
