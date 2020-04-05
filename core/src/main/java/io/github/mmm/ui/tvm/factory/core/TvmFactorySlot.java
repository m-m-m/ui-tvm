/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.composite.TvmSlot;
import io.github.mmm.ui.widget.composite.UiSlot;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiSlot}.
 *
 * @since 1.0.0
 */
public class TvmFactorySlot implements UiSingleWidgetFactoryNative<UiSlot> {

  @Override
  public Class<UiSlot> getType() {

    return UiSlot.class;
  }

  @Override
  public UiSlot create(UiContext context) {

    return new TvmSlot(context);
  }

}
