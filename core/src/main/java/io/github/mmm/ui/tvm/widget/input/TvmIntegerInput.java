/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.input.UiIntegerInput;
import io.github.mmm.ui.widget.input.UiTextInput;

/**
 * Implementation of {@link UiTextInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmIntegerInput extends TvmTextualInput<Integer> implements UiIntegerInput {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmIntegerInput(UiContext context) {

    super(context, "number");
  }

  @Override
  public Integer getValue() {

    String text = getText();
    if (isEmpty(text)) {
      return null;
    }
    try {
      return Integer.valueOf(text);
    } catch (NumberFormatException e) {
      // TODO validation
      return null;
    }
  }

  @Override
  protected void setValueNative(Integer value) {

    String text = "";
    if (value != null) {
      text = value.toString();
    }
    setText(text);
  }

}
