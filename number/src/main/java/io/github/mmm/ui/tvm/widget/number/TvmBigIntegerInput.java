/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.number;

import java.math.BigInteger;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.ui.api.widget.number.UiBigIntegerInput;

/**
 * Implementation of {@link UiBigIntegerInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBigIntegerInput extends TvmNumberInput<BigInteger> implements UiBigIntegerInput {

  /**
   * The constructor.
   */
  public TvmBigIntegerInput() {

    super();
  }

  @Override
  protected NumberType<BigInteger> getNumberType() {

    return NumberType.BIG_INTEGER;
  }

}
