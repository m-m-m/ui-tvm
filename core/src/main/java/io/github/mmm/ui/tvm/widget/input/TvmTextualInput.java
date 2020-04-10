/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.widget.input.UiTextualInput;

/**
 * Implementation of {@link UiTextualInput} using TeaVM.
 *
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmTextualInput<V> extends TvmHtmlInput<V> implements UiTextualInput<V> {

  private String placeholder;

  /**
   * The constructor.
   *
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   */
  public TvmTextualInput(String type) {

    super(type);
  }

  /**
   * The constructor.
   * 
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmTextualInput(HTMLInputElement widget) {

    super(widget);
  }

  @Override
  public String getText() {

    return this.widget.getValue();
  }

  @Override
  public void setText(String value) {

    this.widget.setValue(value);
  }

  @Override
  public String getPlaceholder() {

    return this.placeholder;
  }

  @Override
  public void setPlaceholder(String placeholder) {

    this.placeholder = placeholder;
    // sthis.widget.setPlaceholder(placeholder);
    this.widget.setAttribute("placeholder", placeholder);
  }

}
