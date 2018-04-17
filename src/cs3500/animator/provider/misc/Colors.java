package cs3500.animator.provider.misc;

import cs3500.animator.provider.misc.IColors;

/**
 * Class representing colors in RGB notation.
 */
public class Colors implements IColors{

  /**
   * Constructor for colors.
   * @param r red value between 0 and 1 inclusive
   * @param b blue value between 0 and 1 inclusive
   * @param g green value between 0 and 1 inclusive
   * @throws IllegalArgumentException if color value is outside of 0 - 1
   */
  public Colors(double r, double g, double b) throws IllegalArgumentException {
    if (!checkValid(r,b,g)) {
      throw new IllegalArgumentException("out of bounds");
    }

    this.r = r;
    this.b = b;
    this.g = g;

  }

  private final double r;
  private final double b;
  private final double g;

  /**
   * Check to see if the given parameters are valid (between 0 and 255).
   * @param r value for red
   * @param b value for blue
   * @param g value for green
   * @return boolean representing if everything is in bounds
   */
  private boolean checkValid(double r, double g, double b) {
    return r >= 0 && r <= 1 && b >= 0 && b <= 1 && g >= 0 && g <= 1;
  }

  /**
   * Describe this color as a formatted string.
   * @return string detailing r, g, and b values
   */
  @Override
  public String toString() {

    return String.format("(r: %.1f g: %.1f b: %.1f)", r, g, b);
  }

  /**
   * Get this color in a formatted view.
   * @return the formatted String representing this color
   */
  @Override
  public String printRGB() {
    return String.format("(%.1f,%.1f,%.1f)", r, g, b);
  }

  /**
   * Get this color in a 0-255 formatted range view.
   * @return the formatted color
   */
  @Override
  public String print255Format() {
    return "(" + (int) (r * 255) + ", " + (int) (g * 255) + ", " + (int) (b * 255) + ")";

  }

  /**
   * Get the R value of this color.
   * @return the R value as a double (between 0 and 1)
   */
  @Override
  public double getR() {
    return r;
  }

  /**
   * Get the G value of this color.
   * @return the G value as a double (between 0 and 1)
   */
  @Override
  public double getG() {
    return g;
  }

  /**
   * Get the B value of this color.
   * @return the B value as a double (between 0 and 1)
   */
  @Override
  public double getB() {
    return b;
  }

}
