package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import controller.IInteractiveController;
import model.IKeyFrame;
import model.IModel;

/**
 * A public class that represents an interactive visual animation that the user can interact with.
 * The user is able to interact with buttons, check boxes, and text inputs to be able to start,
 * pause, restart, resume, and change the speed of the animation being played. The animation can
 * also be checked to loop once it ends.
 */
public class InteractiveVisualView extends JFrame implements ActionListener, ItemListener, IView {
  private AnimatorPanel animPanel;
  private JTextField tempoTextField;
  private JLabel animationDisplay;
  private JLabel tempoLabel;
  private IInteractiveController ctrl;
  private int tempo = 1;
  private int delay;

  /**
   * constructor for interactive visual view.
   * @param cx left bound
   * @param cy height bound
   * @param tempo speed
   */
  public InteractiveVisualView(int cx, int cy, int tempo) {
    super();
    this.setTitle("Interactive Visual Animation");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    animPanel = new AnimatorPanel();
    animPanel.setPreferredSize(new Dimension(cx, cy));
    JScrollPane jScrollPane = new JScrollPane(animPanel);
    jScrollPane.setPreferredSize(new Dimension(cx + 50, cy + 50));
    this.add(jScrollPane, BorderLayout.CENTER);

    JPanel actionPanel = new JPanel();
    animationDisplay = new JLabel("Press Start to begin the animation!");
    animationDisplay.setSize(new Dimension(100, 100));
    this.add(actionPanel, BorderLayout.NORTH);
    actionPanel.add(animationDisplay);

    JPanel buttonPanel;
    buttonPanel = new JPanel();
    buttonPanel.setSize(new Dimension(100, 800));
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Interactive features"));
    this.add(buttonPanel, BorderLayout.SOUTH);

    JButton startButton;
    startButton = new JButton("Start");
    startButton.setActionCommand("Start animation");
    startButton.addActionListener(this);
    buttonPanel.add(startButton);

    JButton pauseButton;
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause animation");
    pauseButton.addActionListener(this);
    buttonPanel.add(pauseButton);

    JButton resumeButton;
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("Resume animation");
    resumeButton.addActionListener(this);
    buttonPanel.add(resumeButton);

    JButton restartButton;
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart animation");
    restartButton.addActionListener(this);
    buttonPanel.add(restartButton);

    JCheckBox loopCheckBox;
    loopCheckBox = new JCheckBox("Loop");
    loopCheckBox.setSelected(false);
    loopCheckBox.setActionCommand("Loop animation");
    loopCheckBox.addItemListener(this);
    buttonPanel.add(loopCheckBox);

    JCheckBox outlineCheckBox;
    outlineCheckBox = new JCheckBox("Outline");
    outlineCheckBox.setSelected(false);
    outlineCheckBox.setActionCommand("Outline mode");
    outlineCheckBox.addItemListener(this);
    buttonPanel.add(outlineCheckBox);

    JCheckBox discreteCheckBox;
    discreteCheckBox = new JCheckBox("discrete");
    discreteCheckBox.setSelected(false);
    discreteCheckBox.setActionCommand("discrete mode");
    discreteCheckBox.addItemListener(this);
    buttonPanel.add(discreteCheckBox);


    tempoTextField = new JTextField(5);
    tempoTextField.addActionListener(this);
    tempoTextField.setActionCommand("Set tempo");
    tempoTextField.addActionListener(this);
    buttonPanel.add(tempoTextField);

    JButton tempoButton;
    tempoButton = new JButton("Change Speed");
    tempoButton.setActionCommand("Set tempo");
    tempoButton.addActionListener(this);
    buttonPanel.add(tempoButton, BorderLayout.LINE_END);

    tempoLabel = new JLabel("");
    tempoLabel.setText("The current speed is: " + tempo);
    buttonPanel.add(tempoLabel);

    JLabel delayLabel;
    delayLabel = new JLabel("");
    delayLabel.setText("The current delay is: " + delay);
    buttonPanel.add(delayLabel);



    setVisible(true);
    this.pack();
  }

  @Override
  public void drawGUI(List<IKeyFrame> keyFrames) throws IOException {
    animPanel.setKeyFrames(keyFrames);
    animPanel.repaint();
    tempoLabel.setText("The current delay is: " + delay);
  }

  @Override
  public void drawSVG() throws IOException {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public String drawText(int tempo) {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void export() {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void setTempo(int tempo) {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void setFileName(String file) {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void setLoopInfo(int maxTick) {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public boolean getLoopState() {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public int getMaxTick() {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void appendToFile(String s) throws IOException {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public IModel getModel() {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void setModel(IModel model) {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public String getFinalString() {
    throw new UnsupportedOperationException("Method not used!");
  }

  @Override
  public void setController(IInteractiveController ctrl) {
    if (ctrl == null) {
      throw new IllegalArgumentException("Input is null!");
    }
    this.ctrl = ctrl;
  }

  @Override
  public void setDelay(int d) {
    delay = d;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start animation":
        animationDisplay.setText("Start was pressed. Animation is being run!");
        ctrl.startAnimation();
        break;
      case "Pause animation":
        animationDisplay.setText("Animation was paused!");
        ctrl.pause();
        break;
      case "Restart animation":
        animationDisplay.setText("Animation was restarted!");
        ctrl.restart();
        break;
      case "Resume animation":
        animationDisplay.setText("Animation was resumed!");
        ctrl.resume();
        break;
      case "Set tempo":
        animationDisplay.setText("Changing speed to " + tempoTextField.getText() + "!");
        try {
          int newTempo = Integer.parseInt(tempoTextField.getText());
          ctrl.setTempo(newTempo);
          tempoLabel.setText("The current speed is: " + ctrl.getTempo());
        }
        catch (NumberFormatException nfe) {
          animationDisplay.setText("Invalid speed! Please enter an integer!");
          tempoTextField.setText("");
        }
        break;
      default:

    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String action = ((JCheckBox) e.getItemSelectable()).getActionCommand();

    if (action.equals("Loop animation")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        animationDisplay.setText("Animation is set to loop!");
        ctrl.setLooping();
      } else {
        animationDisplay.setText("Animation is set to not loop!");
        ctrl.setLooping();
      }
    }

    if (action.equals("Outline mode")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        animationDisplay.setText("Animation is set to outline mode");
        animPanel.setOutline(true);
      } else {
        animationDisplay.setText("Animation is set to fill mode!");
        animPanel.setOutline(false);
      }
    }

    if (action.equals("discrete mode")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        animationDisplay.setText("Animation is set to discrete mode");
        ctrl.setDiscreteMode(true);
      } else {
        animationDisplay.setText("Animation is set to discrete mode!");
        ctrl.setDiscreteMode(false);
      }
    }
  }
}