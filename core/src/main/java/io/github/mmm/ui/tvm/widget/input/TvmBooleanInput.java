/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

/**
 * Implementation of {@link HTMLInputElement#isChecked() checkable} input with {@link Boolean} {@link #getValue() value}
 * using TeaVM.
 *
 * @since 1.0.0
 */
public abstract class TvmBooleanInput extends TvmLabelledInput<Boolean> {

  /**
   * The constructor.
   *
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   * @param tag the {@link HTMLElement#getTagName() tag name} of the {@link #getTopWidget() top widget}.
   */
  public TvmBooleanInput(String type, String tag) {

    super(type, tag);
  }

  @Override
  public Boolean getValueOrThrow() {

    return Boolean.valueOf(this.widget.isChecked());
  }

  @Override
  protected void setValueNative(Boolean value) {

    this.widget.setChecked(Boolean.TRUE.equals(value));
  }

}
