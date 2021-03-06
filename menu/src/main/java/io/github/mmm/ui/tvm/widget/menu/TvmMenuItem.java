/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.dom.html.HTMLButtonElement;

import io.github.mmm.ui.api.widget.menu.UiMenuItem;

/**
 * Implementation of {@link UiMenuItem} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuItem extends TvmAbstractButtonMenuItem implements UiMenuItem {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmMenuItem(HTMLButtonElement widget) {

    super(widget);
  }

  /**
   * The constructor.
   */
  public TvmMenuItem() {

    super(newButton());
  }

}
