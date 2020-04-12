/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuBar;
import io.github.mmm.ui.tvm.widget.composite.TvmMutableComposite;

/**
 * Implementation of {@link UiMenuBar} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuBar extends TvmMutableComposite<HTMLElement, UiMenu> implements UiMenuBar {

  /**
   * The constructor.
   */
  public TvmMenuBar() {

    this(newElement("ui-menubar"));
  }

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() JavaFx widget}.
   */
  public TvmMenuBar(HTMLElement widget) {

    super(widget);
    this.widget.setAttribute(ATR_ROLE, "menubar");
    insertFirst(Window.current().getDocument().getBody(), this.widget);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    // TODO Auto-generated method stub

  }

}
