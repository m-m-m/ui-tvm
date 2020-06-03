/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.menubar;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiAbstractMenuItem;
import io.github.mmm.ui.tvm.widget.TvmClickableWidget;

/**
 * Implementation of {@link UiAbstractMenuItem} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmAbstractMenuItem<W extends HTMLElement> extends TvmClickableWidget<W>
    implements UiAbstractMenuItem {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractMenuItem(W widget) {

    super(widget);
    this.widget.setAttribute(ATR_ROLE, "menuitem");
  }

}
