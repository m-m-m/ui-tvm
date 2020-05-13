/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.button;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.widget.button.UiButton;

/**
 * Implementation of {@link UiButton} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmButton extends TvmAbstractButton<HTMLButtonElement> implements UiButton {

  /**
   * The constructor.
   */
  public TvmButton() {

    super(newButton());
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
