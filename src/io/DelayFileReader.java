package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Delay file reader class designed to read in input.
 */
public class DelayFileReader {

  /**
   * The function that reads in input from text file.
   * @param fileName name of the file
   * @return map for delay
   * @throws FileNotFoundException if file not found
   * @throws IllegalStateException if state is incorrect
   * @throws InputMismatchException if input mismatches with current
   */
  public Map<Integer,Integer> readFile(String fileName) throws
          FileNotFoundException, IllegalStateException, InputMismatchException {
    Map<Integer, Integer> delayList = new HashMap<>();
    Scanner sc;
    sc = new Scanner(new FileInputStream(fileName));
    while (sc.hasNext()) {
      int count = 0;
      if (count < 3) {

        int startTick = sc.nextInt();
        int endTick = sc.nextInt();
        int delay = sc.nextInt();
        count = count + 3;

        if (endTick < startTick) {
          throw new IllegalArgumentException("endTick less than startTick");
        } else if (delay < 0) {
          throw new IllegalArgumentException("delay not valid");
        } else {
          for (int i = startTick; i <= endTick; i++) {
            if (delayList.containsKey(i)) {
              throw new IllegalArgumentException("overlaps in input");
            }
            delayList.put(i, delay);
          }
        }
      }
    }
    return delayList;
  }
}
