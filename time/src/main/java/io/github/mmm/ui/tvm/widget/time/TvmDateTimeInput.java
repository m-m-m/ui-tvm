/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.time;

import java.time.LocalDateTime;

import io.github.mmm.ui.api.widget.time.UiDateTimeInput;
import io.github.mmm.ui.tvm.widget.input.TvmTextualInput;

/**
 * Implementation of {@link UiDateTimeInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmDateTimeInput extends TvmTextualInput<LocalDateTime> implements UiDateTimeInput {

  /**
   * The constructor.
   */
  public TvmDateTimeInput() {

    super("datetime-local");
  }

  @Override
  public LocalDateTime getValueOrThrow() {

    String value = this.widget.getValue();
    if (isEmpty(value)) {
      return null;
    }
    return LocalDateTime.parse(value);
  }

  @Override
  protected void setValueNative(LocalDateTime value) {

    if (value == null) {
      setText("");
    } else {
      setText(value.toString());
    }
  }

}
