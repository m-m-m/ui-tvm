/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.notifier;

import io.github.mmm.ui.api.notify.UiNotification;
import io.github.mmm.ui.api.window.notiy.AbstractUiNotifier;

/**
 * Implementation of {@link io.github.mmm.ui.api.window.notiy.UiPopupNotifier} for TeaVM.
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
  public void showGrowl(UiNotification notification) {

    // TODO implement growl for TeaVM
    showPopupOk(notification);
  }

}
