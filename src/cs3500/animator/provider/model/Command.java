package cs3500.animator.provider.model;


/**
 * Interface to represent commands for shapes. Each command is represented in its own concrete
 * class and acts upon a shape given to it by executing the specific actions of that command.
 */
public interface Command {
  /**
   * execute the command.
   * @param tick int of counter
   */
  void execute(Shape s, int tick);

  /**
   * Convert the time given into our own unit of measure, ticks.
   * @param tempo how many ticks per second
   */
  void convertToTicks(int tempo);

  /**
   * Set the start tick of this command to a given tick number.
   * @param st the tick number at which to start the command
   */
  void setStartTick(double st);

  /**
   * Set the end tick of this command to a given tick number.
   * @param e the tick number at which to end the command
   */
  void setEndTick(double e);

  /**
   * Figures out amount by which to change the variable (i.e. distance, color, etc.)
   * of the affected shape based on this command.
   * @param a variable's value at start time
   * @param b variable's value at end time
   * @param tick what tick is it currently at
   * @return the amount changed
   */
  double delta(double a, double b, int tick);

  /**
   * Print the command in a way that can be understood as words.
   * @param s shape to be affected
   * @param tempo how many ticks per second
   * @return the string that describes the command
   */
  String printCommand(Shape s, int tempo);



  /**
   * Get the time at which this command begins execution.
   * @return the time as a double
   */
  double getStartTime();

  /**
   * Get the time at which this command finishes executing.
   * @return the time as a double
   */
  double getEndTime();

  /**
   * Get the name of this command.
   * @return A string representing the name
   */
  String getName();

  /**
   * Print this command in svg format so that it can be executed as an animation.
   * @param s the shape this command acts on
   * @param speedFactor 1 would be regular duration, 2 would be half the duration
   * @param loop does this animation repeat
   * @return A string formatted as an svg animation
   */
  String printAnimation(Shape s, int speedFactor, boolean loop);

  /**
   * Check to see if these are the same time of command.
   * @param that the other command
   * @return true if they're the same type
   */
  boolean sameType(Command that);

  /**
   * Check to see if the times at which this command runs overlaps at all with the times at which.
   * that command runs.
   * @param that the other command
   * @return true if they overlap at any point
   */
  boolean overlappingTimes(Command that);


}
