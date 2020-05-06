/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.datatype.UiTextAlignment;
import io.github.mmm.ui.api.widget.UiText;

/**
 * Implementation of {@link UiText} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmText extends TvmWidgetHtmlElement<HTMLElement> implements UiText {

  private String label;

  private UiTextAlignment alignment;

  /**
   * The constructor.
   */
  public TvmText() {

    super(newElement("ui-text"));
    setAlignment(UiTextAlignment.LEFT);
  }

  @Override
  public String getText() {

    return this.label;
  }

  @Override
  public void setText(String text) {

    this.widget.setTextContent(text);
    this.label = text;
  }

  @Override
  public UiTextAlignment getAlignment() {

    return this.alignment;
  }

  @Override
  public void setAlignment(UiTextAlignment alignment) {

    if (this.alignment == alignment) {
      return;
    }
    this.widget.setAttribute(ATR_STYLE, "text-align:" + convert(alignment));
    this.alignment = alignment;
  }

  private static String convert(UiTextAlignment alignment) {

    switch (alignment) {
      case LEFT:
        return "left";
      case RIGHT:
        return "right";
      case CENTER:
        return "center";
      case JUSTIFY:
        return "justify";
    }
    throw new IllegalStateException();
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

  }
}
