package controller;

/**
 * Interactive view for the system.
 */
public interface IInteractiveController extends IController {
  void startAnimation();

  void resume();

  void restart();

  void pause();

  void setLooping();

  boolean getLoopingState();

  boolean getPausedState();

  void setDiscreteMode(boolean isDiscreteMode);

}
