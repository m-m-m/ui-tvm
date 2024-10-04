/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.time;

import java.time.LocalDate;

import io.github.mmm.ui.api.widget.time.UiDateInput;
import io.github.mmm.ui.tvm.widget.input.TvmTextualInput;

/**
 * Implementation of {@link UiDateInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmDateInput extends TvmTextualInput<LocalDate> implements UiDateInput {

  /**
   * The constructor.
   */
  public TvmDateInput() {

    super("date");
  }

  @Override
  public LocalDate getValueOrThrow() {

    String value = this.widget.getValue();
    if (isEmpty(value)) {
      return null;
    }
    return LocalDate.parse(value);
  }

  @Override
  protected void setValueNative(LocalDate value) {

    if (value == null) {
      setText("");
    } else {
      setText(value.toString());
    }
  }

}
