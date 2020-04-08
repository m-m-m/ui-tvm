/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.input.UiCheckbox;
import io.github.mmm.ui.tvm.widget.input.TvmCheckbox;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiCheckbox}.
 *
 * @since 1.0.0
 */
public class TvmFactoryCheckbox implements UiSingleWidgetFactoryNative<UiCheckbox> {

  @Override
  public Class<UiCheckbox> getType() {

    return UiCheckbox.class;
  }

  @Override
  public UiCheckbox create(UiContext context) {

    return new TvmCheckbox(context);
  }

}
