/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.ui.api.widget.input.UiIntegerInput;

/**
 * Implementation of {@link UiIntegerInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmIntegerInput extends TvmNumberInput<Integer> implements UiIntegerInput {

  /**
   * The constructor.
   */
  public TvmIntegerInput() {

    super();
  }

  @Override
  protected NumberType<Integer> getNumberType() {

    return NumberType.INTEGER;
  }

}
