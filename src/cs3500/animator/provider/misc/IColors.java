package cs3500.animator.provider.misc;

public interface IColors {

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
