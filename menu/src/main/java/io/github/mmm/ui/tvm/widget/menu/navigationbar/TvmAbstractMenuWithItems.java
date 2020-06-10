/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.navigationbar;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.event.UiClickEventListener;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuWithItems;
import io.github.mmm.ui.api.widget.menu.UiMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuItem;
import io.github.mmm.ui.api.widget.menu.UiMenuItemSeparator;
import io.github.mmm.ui.tvm.widget.composite.TvmRemovableComposite;
import io.github.mmm.ui.tvm.widget.menu.menubar.TvmMenuItemSeparator;

/**
 * Implementation of {@link UiAbstractMenuWithItems} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmAbstractMenuWithItems<W extends HTMLElement>
    extends TvmRemovableComposite<W, UiAbstractMenuEntry> implements UiAbstractMenuWithItems {

  /** @see #getCompositeElement() */
  protected final HTMLElement compositeElement;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractMenuWithItems(W widget) {

    super(widget);
    this.compositeElement = newUl();
  }

  @Override
  protected HTMLElement getCompositeElement() {

    return this.compositeElement;
  }

  @Override
  public UiMenu addMenu(String text, int index) {

    TvmNavigationMenu menu = new TvmNavigationMenu();
    menu.setText(text);
    addChild(menu, index);
    return menu;
  }

  @Override
  public UiMenuItem addItem(String text, UiClickEventListener listener, int index) {

    TvmNavigationItem item = new TvmNavigationItem();
    if (text != null) {
      item.setText(text);
    }
    if (listener != null) {
      item.addListener(listener);
    }
    addChild(item, index);
    return item;
  }

  @Override
  public UiMenuItemSeparator addSeparator(int index) {

    TvmMenuItemSeparator separator = new TvmMenuItemSeparator();
    addChild(separator, index);
    return separator;
  }

}
