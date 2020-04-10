/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.widget.input.UiStringInput;

/**
 * Implementation of {@link UiStringInput} using TeaVM.
 *
 * @since 1.0.0
 */
public abstract class TvmStringInput extends TvmTextualInput<String> implements UiStringInput {

  /**
   * The constructor.
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   */
  public TvmStringInput(String type) {

    super(type);
  }

  /**
   * The constructor.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmStringInput(HTMLInputElement widget) {

    super(widget);
  }

  @Override
  public String getValueOrThrow() {

    return this.widget.getValue();
  }

  @Override
  protected void setValueNative(String value) {

    this.widget.setValue(value);
  }

}
