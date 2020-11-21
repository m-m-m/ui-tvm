/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.tvm.widget.button.TvmAbstractButton;

/**
 * Implementation of {@link UiAbstractMenuEntry} for TeaVM based on {@link HTMLButtonElement}.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractButtonMenuItem extends TvmAbstractButton<HTMLButtonElement>
    implements TvmAbstractMenuEntry {

  private boolean current;

  private boolean active;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractButtonMenuItem(HTMLButtonElement widget) {

    super(widget);
    this.widget.setTabIndex(-1);
    this.widget.setAttribute(ATR_ROLE, "menuitem");
    this.current = false;
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

  @Override
  public boolean isCurrent() {

    return this.current;
  }

  @Override
  public void setCurrent(boolean current) {

    if (current == this.current) {
      return;
    }
    this.widget.setAttribute(ATR_ARIA_CURRENT, "" + current);
    this.current = current;
  }

  @Override
  public boolean isActive() {

    return this.active;
  }

  @Override
  public void setActive(boolean active) {

    setActive(active, true);
  }

  void setActive(boolean active, boolean focus) {

    if (active == this.active) {
      return;
    }
    if (active) {
      this.widget.setTabIndex(0);
      if (focus) {
        this.widget.focus();
      }
    } else {
      this.widget.setTabIndex(-1);
    }
    this.active = active;
  }

}
