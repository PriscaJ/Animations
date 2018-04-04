package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Shapes;

public class HybridView extends JFrame implements IHybridView {
  private VisualView visualView;
  private ArrayList<Shapes> allShapes;
  private ArrayList<Shapes> selectedShapes;
  private int endTime;
  private JButton incSpeed, decSpeed, stop, start, restart, loop, svgExport, runSelected;
  private JTextField svgFileName;
  private JPanel buttonPanel;
  private JListShape shapeList;
  private JScrollPane scrollingShapes;
  private InteractivePanel interactivePanel;
  // looping is set to be false initially
  private boolean looping = false;
  private String outputDest;
  private int tps;

  private JScrollPane pane;


  public HybridView(ArrayList<Shapes> shapes, int endTime, String outputDest, int tps) {
    super();
    this.allShapes = shapes;
    this.selectedShapes = shapes;
    this.endTime = endTime;
    this.outputDest = outputDest;
    this.tps = tps;

    // Display the window.
    setSize(800, 800);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // interactive panel (Visual view)
    this.setLayout(new BorderLayout());
    interactivePanel = new InteractivePanel(shapes, endTime, tps);
    interactivePanel.setPreferredSize(new Dimension(300,300));
    this.add(interactivePanel,BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);
    System.out.print("buttons placed");

    // set up for buttons

    // speed
    incSpeed = new JButton("Increase Speed");
    incSpeed.addActionListener((ActionEvent e) -> increaseSpeed());
    buttonPanel.add(incSpeed);


    decSpeed = new JButton("Decrease Speed");
    decSpeed.addActionListener((ActionEvent e) -> decreaseSpeed());
    buttonPanel.add(decSpeed);

    // stop/ start / restart
    stop = new JButton("Stop");
    stop.addActionListener((ActionEvent e) -> stopTimer());
    buttonPanel.add(stop);

    start = new JButton("Start");

    start.addActionListener((ActionEvent e) -> start());
    buttonPanel.add(start);

    restart = new JButton("Restart");
    restart.addActionListener((ActionEvent e) -> restart());
    buttonPanel.add(restart);

    // looping
    String loopCondition = "";
    if (looping) {
      loopCondition = "Turn off looping";
    } else {
      loopCondition = "Turn on looping";
    }
    loop = new JButton(loopCondition);
    loop.addActionListener((ActionEvent e) -> {
      setLooping();
    });
    buttonPanel.add(loop);

    // svg export
    // - text box for output file name
    svgFileName = new JTextField("File output name:");
    buttonPanel.add(svgFileName);
    // - jbutton to export
    svgExport = new JButton("Export in SVG");
    svgExport.addActionListener((ActionEvent e) -> {
      exportSVG();
    });
    buttonPanel.add(svgExport);

    // run selected
    runSelected = new JButton("Run Selected Shapes");
    runSelected.addActionListener((ActionEvent e) -> {
      runSelected();
    });
    buttonPanel.add(runSelected);

    // the list of shapes
    shapeList = new JListShape(shapes);


    // pane that will display all the shapes
    //scrollingShapes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollingShapes = new JScrollPane(shapeList);
    scrollingShapes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollingShapes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scrollingShapes, BorderLayout.EAST);
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
    // creating a new instance outside of panel when declared new

    // visualView = new VisualView(allShapes, endTime, tps, looping);
    visualView.shapesList = allShapes;
    visualView.lastTick = endTime;
    visualView.ticksPerSec = tps;
    visualView.looping = looping;
    visualView.makeVisible();
  }

  @Override
  public void setLooping() {
    // toggle between turning looping on and off
    if (looping) {
      looping = false;
    }
    else {
      looping = true;
    }
    // looping = !looping;
  }

  // starting animation from beginning with selected shapes?
  @Override
  public void restart() {
    interactivePanel.setTickToZero();
  }

  // in interface
  // ON BUTTON CLICK, THE OUTPUT DEST SHOULD BE UPDATED BY WHAT THE USER SPECIFIES
  @Override
  public void exportSVG() {
    SVGView svgView;
    String fileName = svgFileName.getText();
    if (fileName != null) {
      outputDest = fileName;
    }
    if (looping) {
      svgView = new SVGView(selectedShapes, outputDest, interactivePanel.getSpeed(), true);
    } else {
      svgView = new SVGView(selectedShapes, outputDest, interactivePanel.getSpeed(), false);
    }
    svgView.setEndTime(endTime);
    svgView.makeVisible();
  }



  @Override
  public void runSelected() {
    if (looping) {
      // On the next iteration of the looped animation
      // it will run with the selected shapes.
      selectedShapes = (ArrayList<Shapes>) shapeList.getSelected();
    }
    // otherwise do nothing

  }
}
