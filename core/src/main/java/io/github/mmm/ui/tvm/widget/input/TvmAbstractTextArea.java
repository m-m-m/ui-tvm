/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLTextAreaElement;

import io.github.mmm.ui.api.attribute.AttributeWritePlaceholder;
import io.github.mmm.ui.api.widget.input.UiTextArea;

/**
 * Implementation of {@link UiTextArea} for TeaVM.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractTextArea extends TvmInput<String, HTMLTextAreaElement>
    implements AttributeWritePlaceholder {

  private String placeholder;

  /**
   * The constructor.
   */
  public TvmAbstractTextArea() {

    super(newTextArea());
  }

  @Override
  public String getValueOrThrow() {

    return this.widget.getValue();
  }

  @Override
  protected void setValueNative(String value) {

    this.widget.setValue(value);
  }

  @Override
  public String getPlaceholder() {

    return this.placeholder;
  }

  @Override
  public void setPlaceholder(String placeholder) {

    this.placeholder = placeholder;
    this.widget.setPlaceholder(placeholder);
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
