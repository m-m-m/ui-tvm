/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.menubar;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.tvm.widget.button.TvmAbstractButton;

/**
 * Implementation of {@link UiAbstractMenuEntry} for TeaVM based on {@link HTMLButtonElement}.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractButtonMenuItem extends TvmAbstractButton<HTMLButtonElement>
    implements UiAbstractMenuEntry {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractButtonMenuItem(HTMLButtonElement widget) {

    super(widget);
    this.widget.setAttribute(ATR_ROLE, "menuitem");
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
