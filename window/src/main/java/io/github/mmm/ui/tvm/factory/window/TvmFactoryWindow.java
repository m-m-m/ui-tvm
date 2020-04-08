/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.window;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.window.UiWindow;
import io.github.mmm.ui.tvm.widget.window.TvmWindow;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiWindow}.
 *
 * @since 1.0.0
 */
public class TvmFactoryWindow implements UiSingleWidgetFactoryNative<UiWindow> {

  @Override
  public Class<UiWindow> getType() {

    return UiWindow.class;
  }

  @Override
  public UiWindow create(UiContext context) {

    return new TvmWindow(context);
  }

}
