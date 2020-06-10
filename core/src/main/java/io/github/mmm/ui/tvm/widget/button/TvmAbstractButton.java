/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.button;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.button.UiAbstractButton;
import io.github.mmm.ui.api.widget.img.UiAbstractImage;
import io.github.mmm.ui.tvm.widget.TvmClickableWidget;

/**
 * Implementation of {@link UiAbstractButton} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmAbstractButton<W extends HTMLElement> extends TvmClickableWidget<W>
    implements UiAbstractButton {

  private String text;

  private UiAbstractImage icon;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractButton(W widget) {

    super(widget);
  }

  @Override
  public String getText() {

    return this.text;
  }

  @Override
  public void setText(String text) {

    this.widget.setTextContent(text);
    this.text = text;
  }

  @Override
  public UiAbstractImage getImage() {

    return this.icon;
  }

  @Override
  public void setImage(UiAbstractImage icon) {

    if (this.icon != null) {
      this.widget.removeChild(getTopNode(this.icon));
    }
    this.icon = icon;
    if (icon != null) {
      insertFirst(this.widget, getTopNode(icon));
    }
  }

}
