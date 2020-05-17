/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.number;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.ui.api.widget.number.UiLongSlider;

/**
 * Implementation of {@link UiLongSlider} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmLongSlider extends TvmSlider<Long> implements UiLongSlider {

  /**
   * The constructor.
   */
  public TvmLongSlider() {

    super();
  }

  @Override
  protected NumberType<Long> getNumberType() {

    return NumberType.LONG;
  }

}
