/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.window.UiPopup;
import io.github.mmm.ui.widget.window.UiWindow;

/**
 * Implementation of {@link UiPopup} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmWindow extends TvmChildWindow implements UiWindow {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmWindow(UiContext context) {

    super(context);
  }

}
