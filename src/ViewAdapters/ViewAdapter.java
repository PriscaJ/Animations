package ViewAdapters;

import java.io.IOException;

import cs3500.animator.provider.view.*;
import cs3500.animator.view.*;

/**
 * Creates an adapter so that the the provided code works with our system to create views.
 */
public class ViewAdapter implements View{
  IView originalView;

  public ViewAdapter(IView originalView) {
    this.originalView = originalView;
  }

  @Override
  public void showView() throws IOException {
    originalView.makeVisible();
  }
}
