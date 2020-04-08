/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.temporal;

import java.time.LocalTime;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.widget.temporal.UiTimeInput;
import io.github.mmm.ui.tvm.widget.input.TvmTextualInput;

/**
 * Implementation of {@link UiTimeInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmTimeInput extends TvmTextualInput<LocalTime> implements UiTimeInput {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmTimeInput(UiContext context) {

    super(context, "date");
  }

  @Override
  public LocalTime getValueOrThrow() {

    String value = this.widget.getValue();
    if (isEmpty(value)) {
      return null;
    }
    return LocalTime.parse(value);
  }

  @Override
  protected void setValueNative(LocalTime value) {

    if (value == null) {
      setText("");
    } else {
      setText(value.toString());
    }
  }

}
