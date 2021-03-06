/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLSelectElement;

import io.github.mmm.ui.api.widget.input.UiInput;

/**
 * Implementation of {@link UiInput} using TeaVM based on {@link HTMLSelectElement}.
 *
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmHtmlSelect<V> extends TvmInput<V, HTMLSelectElement> {

  /**
   * The constructor.
   */
  public TvmHtmlSelect() {

    super(newSelect());
  }

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmHtmlSelect(HTMLSelectElement widget) {

    super(widget);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
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
