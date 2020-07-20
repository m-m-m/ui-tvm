/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.navigation;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.navigation.UiMenuBar;
import io.github.mmm.ui.tvm.widget.navigation.menu.TvmMenuBar;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiMenuBar}.
 *
 * @since 1.0.0
 */
public class TvmFactoryMenuBar implements UiSingleWidgetFactoryNative<UiMenuBar> {

  @Override
  public Class<UiMenuBar> getType() {

    return UiMenuBar.class;
  }

  @Override
  public UiMenuBar create() {

    return new TvmMenuBar();
  }

}
