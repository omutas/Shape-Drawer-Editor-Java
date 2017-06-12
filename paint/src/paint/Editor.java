package paint;

// Editor.java
// An applet for an object-oriented graphical editor.
// This class implements the GUI for the editor.
// Written by THC for CS 5 Lab Assignment 3.
// Modified by Matthew McNierney for CS 5 Lab Assignment 3

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Editor extends JApplet {
  private static final long serialVersionUID = 1L;
  
  private final int APPLET_WIDTH = 1500, APPLET_HEIGHT = 500;
  private final Color initialColor = Color.red; // default color starts as red

  private Command cmd; // the command being executed
  private Command latestShapeCmd; // the command that keeps latest shape
  private Drawing dwg; // the drawing: shapes in order
  private ColorIndicator colorBox; // a GUI component to show the current default color
  private JButton ActiveButtons[];
  JButton rectButton;
  JButton squareButton;
  JButton ellipseButton;
  JButton circleButton;
  JButton lineButton;
  JButton moveButton;
  JButton deleteButton;
  JButton frontButton;
  JButton backButton;
  JButton exchangeButton;
  
  CanvasPanel canvasPanel;

  public void init() {
    cmd = new Command(); // all methods in Command are empty
    dwg = new Drawing(initialColor); // make an empty drawing
    ActiveButtons = new JButton[1];

    // The drawing will appear in a white CanvasPanel.
    /*CanvasPanel */canvasPanel = new CanvasPanel();
    canvasPanel.setBackground(Color.white);

    // Make JButton objects for all the command buttons.
    ImageIcon rectangle = new ImageIcon(getClass().getResource("rectangle.png"));
    rectButton = new JButton(rectangle);
    ImageIcon square = new ImageIcon(getClass().getResource("square.png"));
    squareButton = new JButton(square);
    ImageIcon ellipse = new ImageIcon(getClass().getResource("ellipse.png"));
    ellipseButton = new JButton(ellipse);
    ImageIcon circle = new ImageIcon(getClass().getResource("circle.png"));
    circleButton = new JButton(circle);
    ImageIcon line = new ImageIcon(getClass().getResource("line.png"));
    lineButton = new JButton(line);
    moveButton = new JButton("Move");
    deleteButton = new JButton("Delete");
    frontButton = new JButton("Front");
    backButton = new JButton("Back");
    exchangeButton = new JButton("Exchange");
    JButton redButton = new JButton("");
    JButton greenButton = new JButton("");
    JButton blueButton = new JButton("");
    JButton orangeButton = new JButton("");
    JButton blackButton = new JButton("");
    JButton pinkButton = new JButton("");

    // Add listeners for all the command buttons.
    rectButton.addActionListener(new RectButtonListener());
    squareButton.addActionListener(new SquareButtonListener());
    ellipseButton.addActionListener(new EllipseButtonListener());
    circleButton.addActionListener(new CircleButtonListener());
    lineButton.addActionListener(new LineButtonListener());
    moveButton.addActionListener(new MoveButtonListener());
    deleteButton.addActionListener(new DeleteButtonListener());
    frontButton.addActionListener(new FrontButtonListener());
    backButton.addActionListener(new BackButtonListener());
    exchangeButton.addActionListener(new ExchangeButtonListener());
    redButton.addActionListener(new RedButtonListener());
    greenButton.addActionListener(new GreenButtonListener());
    blueButton.addActionListener(new BlueButtonListener());
    orangeButton.addActionListener(new OrangeButtonListener());
    blackButton.addActionListener(new BlackButtonListener());
    pinkButton.addActionListener(new PinkButtonListener());

    // The command buttons will be arranged in 3 rows.  Each row will
    // appear in its own JPanel, and the 3 JPanels will be stacked
    // vertically.
    JPanel shapePanel = new JPanel(); // holds buttons for adding shapes
    JLabel shapeLabel = new JLabel("Add shape:");
    shapePanel.setLayout(new FlowLayout());
    shapePanel.add(shapeLabel);
    shapePanel.add(rectButton);
    shapePanel.add(squareButton);
    shapePanel.add(ellipseButton);
    shapePanel.add(circleButton);
    shapePanel.add(lineButton);

    JPanel editPanel = new JPanel(); // holds buttons for editing operations
    JLabel editLabel = new JLabel("Edit:");
    editPanel.setLayout(new FlowLayout());
    editPanel.add(editLabel);
    editPanel.add(moveButton);
    editPanel.add(deleteButton);
    editPanel.add(frontButton);
    editPanel.add(backButton);
    editPanel.add(exchangeButton);

    // The color panel is slightly different from the other two. In
    // addition to a label and buttons for the color commands, this
    // panel holds a ColorIndicator that gives the current default
    // color.
    JPanel colorPanel = new JPanel();
    JLabel currentColorLabel = new JLabel("Current Color:");
    JLabel colorLabel = new JLabel("Colors:");
    colorPanel.setLayout(new FlowLayout());
    colorPanel.add(currentColorLabel);
    colorBox = new ColorIndicator();
    colorBox.show(initialColor);
    redButton.setBackground(Color.red);
    redButton.setPreferredSize(new Dimension(20, 20));
    greenButton.setBackground(Color.green);
    greenButton.setPreferredSize(new Dimension(20, 20));
    blueButton.setBackground(Color.blue);
    blueButton.setPreferredSize(new Dimension(20, 20));
    orangeButton.setBackground(Color.orange);
    orangeButton.setPreferredSize(new Dimension(20, 20));
    blackButton.setBackground(Color.black);
    blackButton.setPreferredSize(new Dimension(20, 20));
    pinkButton.setBackground(Color.pink);
    pinkButton.setPreferredSize(new Dimension(20, 20));
    colorPanel.add(colorBox);
    colorPanel.add(colorLabel);
    colorPanel.add(redButton);
    colorPanel.add(greenButton);
    colorPanel.add(blueButton);
    colorPanel.add(orangeButton);
    colorPanel.add(blackButton);
    colorPanel.add(pinkButton);

    // Use a grid layout to stack the button panels vertically.  Also,
    // give them a cyan background.
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(shapePanel);
    buttonPanel.add(editPanel);
    buttonPanel.add(colorPanel);

    // Now we have two panels: buttonPanel and canvasPanel.  We want
    // buttonPanel to appear above canvasPanel, and canvasPanel should
    // grow with the applet.
    Container cp = getContentPane();
    cp.setLayout(new BorderLayout());
    cp.add(buttonPanel, BorderLayout.NORTH);
    cp.add(canvasPanel, BorderLayout.CENTER);

    setSize(APPLET_WIDTH, APPLET_HEIGHT);
  }

  public void paint(Graphics page) {
    super.paint(page); // make all the GUI components paint themselves
  }

  // What to do when rectButton is pressed.
  private class RectButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new AddRect();
      latestShapeCmd = cmd;
      if(ActiveButtons[0] != null){
    		  ActiveButtons[0].setBackground(null);
      }
      rectButton.setBackground(Color.green);
      ActiveButtons[0]= rectButton;
      repaint();
    }
  }
  
  // What to do when squareButton is pressed.
  private class SquareButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new AddSquare();
      latestShapeCmd = cmd;
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  squareButton.setBackground(Color.green);
	  ActiveButtons[0]= squareButton;
      repaint();
    }
  }

  // What to do when ellipseButton is pressed.
  private class EllipseButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new AddEllipse();
      latestShapeCmd = cmd;
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  ellipseButton.setBackground(Color.green);
	  ActiveButtons[0]= ellipseButton;
      repaint();
    }
  }
  
  // What to do when circleButton is pressed.
  private class CircleButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new AddCircle();
      latestShapeCmd = cmd;
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  circleButton.setBackground(Color.green);
	  ActiveButtons[0]= circleButton;
      repaint();
    }
  }

  // What to do when lineButton is pressed.
  private class LineButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new AddSegment();
      latestShapeCmd = cmd;
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  lineButton.setBackground(Color.green);
	  ActiveButtons[0]= lineButton;
      repaint();
    }
  }

  // What to do when moveButton is pressed.
  private class MoveButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new MoveCmd();
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  moveButton.setBackground(Color.green);
	  ActiveButtons[0]= moveButton;
      repaint();
    }
  }

  // What to do when deleteButton is pressed.
  private class DeleteButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new DeleteCmd();
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  deleteButton.setBackground(Color.green);
	  ActiveButtons[0]= deleteButton;
      repaint();
    }
  }

  // What to do when frontButton is pressed.
  private class FrontButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new FrontCmd();
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  frontButton.setBackground(Color.green);
	  ActiveButtons[0]= frontButton;
      repaint();
    }
  }

  // What to do when backButton is pressed.
  private class BackButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new BackCmd();
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  backButton.setBackground(Color.green);
	  ActiveButtons[0]= backButton;
      repaint();
    }
  }

  // What to do when exchangeButton is pressed.
  private class ExchangeButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      cmd = new ExchangeCmd();
      if(ActiveButtons[0] != null){
		  ActiveButtons[0].setBackground(null);
	  }
	  exchangeButton.setBackground(Color.green);
	  ActiveButtons[0]= exchangeButton;
      repaint();
    }
  }

  // What to do when redButton is pressed.
  private class RedButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.red); // show that the new default color is red
      dwg.setColor(Color.red);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }

  // What to do when greenButton is pressed.
  private class GreenButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.green); // show that the new default color is green
      dwg.setColor(Color.green);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }

  // What to do when blueButton is pressed.
  private class BlueButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.blue); // show that the new default color is blue
      dwg.setColor(Color.blue);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }
  
  // What to do when orangeButton is pressed.
  private class OrangeButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.orange); // show that the new default color is red
      dwg.setColor(Color.orange);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }

  // What to do when blackButton is pressed.
  private class BlackButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.black); // show that the new default color is green
      dwg.setColor(Color.black);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }

  // What to do when blueButton is pressed.
  private class PinkButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      colorBox.show(Color.pink); // show that the new default color is blue
      dwg.setColor(Color.pink);
      cmd = new ColorCmd();
      if(latestShapeCmd != null){
          cmd = latestShapeCmd;
      }
      repaint();
    }
  }

  // A ColorIndicator shows what the current color is.
  private class ColorIndicator extends JPanel {
    private static final long serialVersionUID = 0;
    
    private final int COLORBOX_WIDTH = 20, COLORBOX_HEIGHT = 20;

    // Constructor sets the size and border.
    public ColorIndicator() {
      setBorder(BorderFactory.createEtchedBorder());
      setPreferredSize(new Dimension(COLORBOX_WIDTH, COLORBOX_HEIGHT));
    }

    // Show a new color.
    public void show(Color color) {
      setBackground(color);
    }
  }

  // CanvasPanel is the class upon which we actually draw.  It listens
  // for mouse events and calls the appropriate method of the current
  // command.
  private class CanvasPanel extends JPanel implements MouseListener,
      MouseMotionListener {
    private static final long serialVersionUID = 0;
    
    // Constructor just needs to set up the CanvasPanel as a listener.
    public CanvasPanel() {
      addMouseListener(this);
      addMouseMotionListener(this);
    }

    public void paint(Graphics page) {
      super.paint(page); // execute the paint method of JPanel
      dwg.draw(page); // draw all of the shapes
    }

    // When the mouse is clicked, call the executeClick method of the
    // current command.
    public void mouseClicked(MouseEvent event) {
      cmd.executeClick(event.getPoint(), dwg);
      repaint();
    }

    // When the mouse is pressed, call the executePress method of the
    // current command.
    public void mousePressed(MouseEvent event) {
      cmd.executePress(event.getPoint(), dwg);
      repaint();
    }

    // When the mouse is dragged, call the executeDrag method of the
    // current command.
    public void mouseDragged(MouseEvent event) {
      cmd.executeDrag(event.getPoint(), dwg);
      repaint();
    }

    // We don't care about the other mouse events.
    public void mouseReleased(MouseEvent event) { }
    public void mouseEntered(MouseEvent event) { }
    public void mouseExited(MouseEvent event) { }
    public void mouseMoved(MouseEvent event) { }
  }
}
