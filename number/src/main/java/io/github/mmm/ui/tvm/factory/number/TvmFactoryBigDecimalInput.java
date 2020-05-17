/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.number;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.number.UiBigDecimalInput;
import io.github.mmm.ui.tvm.widget.number.TvmBigDecimalInput;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiBigDecimalInput}.
 *
 * @since 1.0.0
 */
public class TvmFactoryBigDecimalInput implements UiSingleWidgetFactoryNative<UiBigDecimalInput> {

  @Override
  public Class<UiBigDecimalInput> getType() {

    return UiBigDecimalInput.class;
  }

  @Override
  public UiBigDecimalInput create() {

    return new TvmBigDecimalInput();
  }

}
