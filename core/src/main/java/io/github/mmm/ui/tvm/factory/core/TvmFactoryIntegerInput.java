/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.input.UiIntegerInput;
import io.github.mmm.ui.tvm.widget.input.TvmIntegerInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiIntegerInput}.
 *
 * @since 1.0.0
 */
public class TvmFactoryIntegerInput implements UiSingleWidgetFactoryNative<UiIntegerInput> {

  @Override
  public Class<UiIntegerInput> getType() {

    return UiIntegerInput.class;
  }

  @Override
  public UiIntegerInput create(UiContext context) {

    return new TvmIntegerInput(context);
  }

}
