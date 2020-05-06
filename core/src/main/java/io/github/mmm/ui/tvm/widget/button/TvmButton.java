/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.button;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.widget.button.UiButton;
import io.github.mmm.ui.tvm.widget.TvmClickableWidget;

/**
 *
 */
public class TvmButton extends TvmClickableWidget<HTMLButtonElement> implements UiButton {

  private String label;

  /**
   * The constructor.
   */
  public TvmButton() {

    super(newButton());
  }

  @Override
  public String getText() {

    return this.label;
  }

  @Override
  public void setText(String text) {

    this.widget.setTextContent(text);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
