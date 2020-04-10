/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.temporal;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.temporal.UiDateInput;
import io.github.mmm.ui.tvm.widget.temporal.TvmDateInput;

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
  public UiDateInput create() {

    return new TvmDateInput();
  }

}
