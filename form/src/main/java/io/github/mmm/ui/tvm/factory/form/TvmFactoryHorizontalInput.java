/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.form;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.form.UiHorizontalInput;
import io.github.mmm.ui.tvm.widget.form.TvmHorizontalInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiHorizontalInput}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class TvmFactoryHorizontalInput implements UiSingleWidgetFactoryNative<UiHorizontalInput> {

  @Override
  public Class<UiHorizontalInput> getType() {

    return UiHorizontalInput.class;
  }

  @Override
  public UiHorizontalInput create() {

    return new TvmHorizontalInput();
  }

}
