/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.base.range.WritableRange;
import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.widget.input.UiNumberInput;
import io.github.mmm.ui.spi.range.NumericRange;

/**
 * Implementation of {@link UiNumberInput} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}. Typically {@link String}.
 * @since 1.0.0
 */
public abstract class TvmNumberInput<V extends Number> extends TvmTextualInput<V> implements UiNumberInput<V> {

  private static final String ATR_STEP = "step";

  private final NumericRange<V> range;

  private V step;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmNumberInput(UiContext context) {

    super(context, "number");
    this.range = new NumericRange<>(getNumberType());
  }

  /**
   * @return the {@link NumberType} for the underlying {@link Number}.
   */
  protected abstract NumberType<V> getNumberType();

  @Override
  public WritableRange<V> getRange() {

    return this.range;
  }

  @Override
  public V getValueOrThrow() {

    String text = getText().trim();
    if (text.isEmpty()) {
      return null;
    }
    return getNumberType().valueOf(text);
  }

  @Override
  protected void setValueNative(V value) {

    String text = "";
    if (value != null) {
      text = value.toString();
    }
    setText(text);
  }

  @Override
  public V getStep() {

    return this.step;
  }

  @Override
  public void setStep(V step) {

    if (step == null) {
      this.widget.removeAttribute(ATR_STEP);
    } else {
      this.widget.setAttribute(ATR_STEP, step.toString());
    }
    this.step = step;
  }

}
