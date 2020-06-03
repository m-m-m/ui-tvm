/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.menubar;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.attribute.AttributeWriteText;

/**
 * {@link TvmAbstractMenuItem} based on {@link HTMLButtonElement}.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractButtonMenuItem extends TvmAbstractMenuItem<HTMLButtonElement>
    implements AttributeWriteText {

  private String text;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractButtonMenuItem(HTMLButtonElement widget) {

    super(widget);
    this.text = "";
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
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
