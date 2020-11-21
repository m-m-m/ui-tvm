/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiMenuItem;
import io.github.mmm.ui.api.widget.menu.UiMenuItemSeparator;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiMenuItem} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuItemSeparator extends TvmWidgetHtmlElement<HTMLElement>
    implements UiMenuItemSeparator, TvmAbstractMenuEntry {

  /**
   * The constructor.
   */
  public TvmMenuItemSeparator() {

    super(newHr());
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    // nothing to do...
  }

  @Override
  public boolean isCurrent() {

    return false;
  }

  @Override
  public void setCurrent(boolean selected) {

  }

  @Override
  public boolean isActive() {

    return false;
  }

  @Override
  public void setActive(boolean active) {

  }

}
