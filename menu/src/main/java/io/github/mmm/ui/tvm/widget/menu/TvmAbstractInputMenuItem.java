/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.attribute.AttributeWriteText;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuItem;
import io.github.mmm.ui.api.widget.value.UiWidgetWithSelection;
import io.github.mmm.ui.tvm.widget.TvmClickableWidget;

/**
 * Implementation {@link UiAbstractMenuItem} for TeaVM based on {@link HTMLInputElement}.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractInputMenuItem extends TvmClickableWidget<HTMLInputElement>
    implements UiAbstractMenuItem, UiWidgetWithSelection, AttributeWriteText {

  private final HTMLElement topWidget;

  private final HTMLElement label;

  private String text;

  /**
   * The constructor.
   *
   * @param type the {@link HTMLInputElement#getType() type} of the input.
   * @param tag the tag-name for the {@link #getTopWidget() top widget}.
   */
  public TvmAbstractInputMenuItem(String type, String tag) {

    super(newInput());
    this.widget.setType(type);
    this.topWidget = newElement(tag);
    this.label = newLabel();
    this.topWidget.appendChild(this.widget);
    this.topWidget.appendChild(this.label);
    this.text = "";
    this.topWidget.setAttribute(ATR_ROLE, "menuitem");
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
    this.widget.setTextContent(text);
    this.text = text;
  }

  @Override
  protected void setIdNative(String id) {

    super.setIdNative(id);
    this.label.setAttribute("for", id);
  }

  @Override
  public Boolean getValue() {

    return Boolean.valueOf(this.widget.isChecked());
  }

  @Override
  public void setValue(Boolean value) {

    this.widget.setChecked(Boolean.TRUE.equals(value));
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
