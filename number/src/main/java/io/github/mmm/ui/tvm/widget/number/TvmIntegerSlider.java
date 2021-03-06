/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.number;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.ui.api.widget.number.UiIntegerSlider;

/**
 * Implementation of {@link UiIntegerSlider} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmIntegerSlider extends TvmSlider<Integer> implements UiIntegerSlider {

  /**
   * The constructor.
   */
  public TvmIntegerSlider() {

    super();
  }

  @Override
  protected NumberType<Integer> getNumberType() {

    return NumberType.INTEGER;
  }

}
