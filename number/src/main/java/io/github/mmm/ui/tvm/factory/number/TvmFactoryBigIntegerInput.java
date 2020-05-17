/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.number;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.number.UiBigIntegerInput;
import io.github.mmm.ui.tvm.widget.number.TvmBigIntegerInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiBigIntegerInput}.
 *
 * @since 1.0.0
 */
public class TvmFactoryBigIntegerInput implements UiSingleWidgetFactoryNative<UiBigIntegerInput> {

  @Override
  public Class<UiBigIntegerInput> getType() {

    return UiBigIntegerInput.class;
  }

  @Override
  public UiBigIntegerInput create() {

    return new TvmBigIntegerInput();
  }

}
