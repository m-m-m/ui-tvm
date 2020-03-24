/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.TvmMainWindow;
import io.github.mmm.ui.widget.window.UiMainWindow;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiMainWindow}.
 *
 * @since 1.0.0
 */
public class TvmFactoryMainWindow implements UiSingleWidgetFactoryNative<UiMainWindow> {

  @Override
  public Class<UiMainWindow> getType() {

    return UiMainWindow.class;
  }

  @Override
  public UiMainWindow create(UiContext context) {

    return new TvmMainWindow(context);
  }

}
