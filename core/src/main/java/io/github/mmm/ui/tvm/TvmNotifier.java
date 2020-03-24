/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm;

import org.teavm.jso.browser.Window;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.UiSeverity;
import io.github.mmm.ui.spi.AbstractUiNotifier;

/**
 * Implementation of {@link io.github.mmm.ui.UiNotifier} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmNotifier extends AbstractUiNotifier {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmNotifier(UiContext context) {

    super(context);
  }

  @Override
  public void showNotification(String message, UiSeverity severity) {

    // TODO: just a temporary hack, we will build real windows (modal popup and regular window) as web-components with
    // glass-pane, etc.
    Window.alert(message);
  }

}
