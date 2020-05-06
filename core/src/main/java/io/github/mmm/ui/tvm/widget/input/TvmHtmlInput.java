/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.widget.input.UiInput;

/**
 * Implementation of {@link UiInput} using TeaVM based on {@link HTMLInputElement}.
 *
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmHtmlInput<V> extends TvmInput<V, HTMLInputElement> {

  /** {@link HTMLInputElement#getType() Type} of text input. */
  public static final String TYPE_TEXT = "text";

  /** {@link HTMLInputElement#getType() Type} of radio (button) input. */
  public static final String TYPE_RADIO = "radio";

  /** {@link HTMLInputElement#getType() Type} of checkbox input. */
  public static final String TYPE_CHECKBOX = "checkbox";

  /**
   * The constructor.
   *
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   */
  public TvmHtmlInput(String type) {

    super(newInput());
    this.widget.setType(type);
  }

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmHtmlInput(HTMLInputElement widget) {

    super(widget);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

  @Override
  protected void setMandatory(boolean mandatory) {

    super.setMandatory(mandatory);
    if (mandatory) {
      this.widget.setAttribute(ATR_REQUIRED, "");
    } else {
      this.widget.removeAttribute(ATR_REQUIRED);
    }
  }

  @Override
  protected void doSetValidationFailure(String error) {

    if (error == null) {
      this.widget.setCustomValidity("");
    } else {
      this.widget.setCustomValidity(error);
      this.widget.reportValidity();
    }
  }

  @Override
  protected void onFocusGain(Event event) {

    super.onFocusGain(event);
    if (!isValid()) {
      this.widget.reportValidity();
    }
  }

}
