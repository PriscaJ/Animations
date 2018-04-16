package cs3500.animator.provider.view;

import java.io.IOException;

/**
 * Interface to represent different views available for animations.
 */
public interface View {

  /**
   * Show the view (will vary based on concrete implementation).
   * @throws IOException if file cannot be created
   */
  void showView() throws IOException;

}
