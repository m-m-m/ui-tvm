/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.navigation;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.navigation.UiNavigationBar;
import io.github.mmm.ui.tvm.widget.navigation.TvmNavigationBar;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiNavigationBar}.
 *
 * @since 1.0.0
 */
public class TvmFactoryNavigationBar implements UiSingleWidgetFactoryNative<UiNavigationBar> {

  @Override
  public Class<UiNavigationBar> getType() {

    return UiNavigationBar.class;
  }

  @Override
  public UiNavigationBar create() {

    return new TvmNavigationBar();
  }

}
