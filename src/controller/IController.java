package controller;

import java.util.Map;

/**
 * This interface represents an IController which handles starting the application for the various
 * implementations of Controller in outputting the IModel info into an output file, SVG file, or
 * running the animation.
 */
public interface IController {
  /**
   * Runs the animation.
   */
  void start();

  /**
   * Sets the tempo of the model with the given input.
   * @param tempo the tempo (ticks per second) as an int
   * @throws IllegalArgumentException if input is <= 0
   */
  void setTempo(int tempo);

  /**
   * Returns the tempo of the IController as an int.
   * @return the int representing the tempo
   */
  int getTempo();

  /**
   * Returns the type of IController as a String. Used for testing.
   * @return the String name of the Controller
   */
  String getType();

  /**
   * Set the delay list of the controller.
   * @param delay the delay list
   */
  void setDelayList(Map<Integer, Integer> delay);

  /**
   * Return the delay of the shape.
   * @return the delay of the view.
   */
  int getDelay();
}
