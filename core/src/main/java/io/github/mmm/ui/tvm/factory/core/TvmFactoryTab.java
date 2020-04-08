/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.composite.UiTab;
import io.github.mmm.ui.tvm.widget.composite.TvmTab;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiTab}.
 *
 * @since 1.0.0
 */
public class TvmFactoryTab implements UiSingleWidgetFactoryNative<UiTab> {

  @Override
  public Class<UiTab> getType() {

    return UiTab.class;
  }

  @Override
  public UiTab create(UiContext context) {

    return new TvmTab(context);
  }

}
