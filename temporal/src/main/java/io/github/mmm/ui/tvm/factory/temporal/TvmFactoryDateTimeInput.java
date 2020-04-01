/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.temporal;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.temporal.TvmDateTimeInput;
import io.github.mmm.ui.widget.temporal.UiDateTimeInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiDateTimeInput}.
 *
 * @since 1.0.0
 */
public class TvmFactoryDateTimeInput implements UiSingleWidgetFactoryNative<UiDateTimeInput> {

  @Override
  public Class<UiDateTimeInput> getType() {

    return UiDateTimeInput.class;
  }

  @Override
  public UiDateTimeInput create(UiContext context) {

    return new TvmDateTimeInput(context);
  }

}
