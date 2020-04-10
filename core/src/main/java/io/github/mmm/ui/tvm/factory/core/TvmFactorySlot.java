/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.composite.UiSlot;
import io.github.mmm.ui.tvm.widget.composite.TvmSlot;

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
  public UiSlot create() {

    return new TvmSlot();
  }

}
