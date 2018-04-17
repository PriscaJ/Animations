package cs3500.animator.provider.misc;

public interface IColors {

  /**
   * Check to see if the given parameters are valid (between 0 and 255).
   * @param r value for red
   * @param b value for blue
   * @param g value for green
   * @return boolean representing if everything is in bounds
   */
  boolean checkValid(double r, double g, double b);

  /**
   * Describe this color as a formatted string.
   * @return string detailing r, g, and b values
   */
  String toString();

  /**
   * Get this color in a formatted view.
   * @return the formatted String representing this color
   */
  String printRGB();

  /**
   * Get this color in a 0-255 formatted range view.
   * @return the formatted color
   */
  String print255Format();

  /**
   * Get the R value of this color.
   * @return the R value as a double (between 0 and 1)
   */
  double getR();

  /**
   * Get the G value of this color.
   * @return the G value as a double (between 0 and 1)
   */
  double getG();

  /**
   * Get the B value of this color.
   * @return the B value as a double (between 0 and 1)
   */
  double getB();

}
