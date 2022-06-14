package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import model.IKeyFrame;
import model.IModel;
import model.IShape;
import view.IView;
import view.InteractiveVisualView;

/**
 * controller for interactive view.
 */
public class InteractiveVisualController implements IInteractiveController {
  private IModel model;
  private IView view;
  private int currTick;
  private int tempo = 1;
  private Timer timer;
  private boolean isLooping;
  private boolean isPaused;
  private boolean isDiscreteMode;
  private Map<Integer, Integer> delayList;



  /**
   * Creates an InteractiveVisualController instance that takes in a given IModel and IView.
   * @param model the given Model
   * @param view the given View
   * @throws IllegalArgumentException if the inputs are null or the view is not an
   *         InteractiveVisualView
   */
  public InteractiveVisualController(IModel model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Inputs are invalid!");
    }

    if (!(view instanceof InteractiveVisualView)) {
      throw new IllegalArgumentException("Invalid view given!");
    }
    this.model = model;
    this.view = view;
    isLooping = false;
    isPaused = false;
    view.setController(this);
  }

  @Override
  public void start() {
    createTimer(tempo);
  }

  private void createTimer(int t) {
    timer = new Timer(0, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        if (currTick > model.getMaxTick()) {
          timer.stop();
        }
        List<IKeyFrame> framesForShapesPerTick = new ArrayList<>();

        if (isDiscreteMode) {
          List<IKeyFrame> kfs = new ArrayList<>();
          for (IShape s : model.getAllShapes()) {
            List<IKeyFrame> allDiscreteKeyFrames = model.discreteKeyFrames(s);

            for (int i = 0; i < allDiscreteKeyFrames.size(); i ++) {
              if (currTick == allDiscreteKeyFrames.get(i).getTick()) {
                kfs.add(allDiscreteKeyFrames.get(i));
              }
            }
          }
          framesForShapesPerTick = kfs;
        }
        else {
          framesForShapesPerTick = model.getKeyFramesForEachShapeAtGivenTick(currTick);
        }

        try {
          int k;
          if (delayList.containsKey(currTick)) {
            k = delayList.get(currTick);
            timer.setDelay(k);
            view.setDelay(k);
          }
          else {
            timer.setDelay(tempo);
            view.setDelay(0);
          }

          view.drawGUI(framesForShapesPerTick);

        }
        catch (IOException e) {
          e.printStackTrace();
        }

        if (!isPaused && !isLooping) {
          currTick++;
        }

        if (!isPaused && isLooping) {
          if (currTick == model.getMaxTick()) {
            currTick = 0;
          }
          else {
            currTick++;
          }
        }
      }
    });
    setTimer(timer);
  }

  private void setTimer(Timer t) {
    System.out.println(this.delayList);
    if (t == null) {
      throw new IllegalArgumentException("Timer input is null!");
    }
    this.timer = t;
  }

  @Override
  public void setTempo(int tempo) {
    if (tempo <= 0) {
      throw new IllegalArgumentException("Tempo can't be <= 0!");
    }
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public String getType() {
    return "interactive";
  }

  @Override
  public void setDelayList(Map<Integer, Integer> delay) {

    this.delayList = delay;
  }

  @Override
  public int getDelay() {
    if (delayList.containsKey(currTick)) {
      return delayList.get(currTick);
    }
    else {
      return 0;
    }
  }

  @Override
  public void startAnimation() {
    timer.start();
  }

  @Override
  public void resume() {
    isPaused = false;
  }

  @Override
  public void restart() {
    currTick = 0;
  }

  @Override
  public void pause() {
    isPaused = true;
  }

  @Override
  public void setLooping() {
    if (isLooping) {
      isLooping = false;
    }
    else {
      isLooping = true;
    }
  }

  @Override
  public boolean getLoopingState() {
    return isLooping;
  }

  @Override
  public boolean getPausedState() {
    return isPaused;
  }

  @Override
  public void setDiscreteMode(boolean discreteMode) {
    this.isDiscreteMode = discreteMode;
  }


}
