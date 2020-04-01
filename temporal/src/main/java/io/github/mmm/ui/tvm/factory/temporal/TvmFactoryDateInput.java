/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.temporal;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.temporal.TvmDateInput;
import io.github.mmm.ui.widget.temporal.UiDateInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiDateInput}.
 *
 * @since 1.0.0
 */
public class TvmFactoryDateInput implements UiSingleWidgetFactoryNative<UiDateInput> {

  @Override
  public Class<UiDateInput> getType() {

    return UiDateInput.class;
  }

  @Override
  public UiDateInput create(UiContext context) {

    return new TvmDateInput(context);
  }

}
