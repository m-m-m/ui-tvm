/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.attribute.AttributeWriteText;

/**
 * {@link TvmInput} that also implements {@link AttributeWriteText}.
 *
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmLabelledInput<V> extends TvmHtmlInput<V> implements AttributeWriteText {

  private final HTMLElement topWidget;

  private final HTMLElement label;

  private String text;

  /**
   * The constructor.
   *
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   * @param tag the {@link HTMLElement#getTagName() tag name} of the {@link #getTopWidget() top widget}.
   */
  public TvmLabelledInput(String type, String tag) {

    super(type);
    this.topWidget = newElement(tag);
    this.label = newLabel();
    this.topWidget.appendChild(this.widget);
    this.topWidget.appendChild(this.label);
    this.text = "";
  }

  @Override
  protected void setIdNative(String id) {

    super.setIdNative(id);
    this.label.setAttribute("for", id);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  @Override
  public String getText() {

    return this.text;
  }

  @Override
  public void setText(String text) {

    if (text == null) {
      text = "";
    }
    this.text = text;
    this.label.setTextContent(text);
  }

}
