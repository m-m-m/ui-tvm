/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.menubar;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiAdvancedMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuBar;
import io.github.mmm.ui.tvm.widget.composite.TvmRemovableComposite;

/**
 * Implementation of {@link UiMenuBar} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuBar extends TvmRemovableComposite<HTMLElement, UiAdvancedMenu> implements UiMenuBar {

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
  public UiAdvancedMenu addMenu(String text, int index) {

    TvmMenu menu = new TvmMenu();
    menu.setText(text);
    setParent(menu, this);
    addChildWidget(menu, index);
    if (index == -1) {
      this.children.add(menu);
    } else {
      this.children.add(index, menu);
    }

    return menu;
  }

}
