/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.notifier;

import org.teavm.jso.browser.Window;

import io.github.mmm.ui.api.datatype.UiSeverity;
import io.github.mmm.ui.api.notifier.AbstractUiNotifier;

/**
 * Implementation of {@link io.github.mmm.ui.api.notifier.UiNotifier} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmNotifier extends AbstractUiNotifier {

  /**
   * The constructor.
   */
  public TvmNotifier() {

    super();
  }

  @Override
  public void showNotification(String message, UiSeverity severity) {

    // TODO: just a temporary hack until js grow is implemented
    Window.alert(message);
  }

}
