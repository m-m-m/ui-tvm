/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.TextRectangle;
import org.teavm.jso.dom.xml.Node;

import io.github.mmm.ui.api.event.UiClickEventListener;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.api.widget.menu.UiAdvancedMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuItem;
import io.github.mmm.ui.api.widget.menu.UiMenuItemCheckbox;
import io.github.mmm.ui.api.widget.menu.UiMenuItemRadioButton;
import io.github.mmm.ui.api.widget.menu.UiMenuItemSeparator;
import io.github.mmm.ui.tvm.TvmToggleGroup;

/**
 * Implementation of {@link UiAdvancedMenu} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenu extends TvmAbstractButtonMenuItem implements UiAdvancedMenu, TvmAbstractMenu<UiAbstractMenuEntry> {

  private final HTMLElement menu;

  private final List<UiAbstractMenuEntry> children;

  private final HTMLElement submenus;

  private TvmToggleGroup toggleGroup;

  private TvmAbstractMenuEntry currentEntry;

  private boolean expanded;

  /**
   * The constructor.
   *
   * @param submenus the {@link HTMLElement} where sub-menus are temporary added and removed. Shall be placed below
   *        menu-bar element for tab-order.
   */
  public TvmMenu(HTMLElement submenus) {

    this(submenus, newButton());
  }

  /**
   * The constructor.
   *
   * @param submenus the {@link HTMLElement} where sub-menus are temporary added and removed. Shall be placed below
   *        menu-bar element for tab-order.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmMenu(HTMLElement submenus, HTMLButtonElement widget) {

    super(widget);
    this.submenus = submenus;
    this.children = new ArrayList<>();
    this.menu = newElement("ui-menu");
    widget.setTabIndex(-1);
    widget.setAttribute(ATR_ROLE, "menuitem");
    widget.setAttribute(ATR_ARIA_HAS_POPUP, "true");
    widget.setAttribute(ATR_ARIA_EXPANDED, "false");
    this.menu.setAttribute(ATR_ROLE, "menu");
    this.menu.setAttribute("style", "display:none");
    this.submenus.appendChild(this.menu);
    this.expanded = false;
  }

  @Override
  public void setCurrent(boolean current) {

    boolean collapse = (!current && isCurrent());
    super.setCurrent(current);
    if (collapse) {
      setExpanded(false);
    }
  }

  @Override
  public boolean isExpanded() {

    return this.expanded;
  }

  @Override
  public void setExpanded(boolean expanded) {

    if (expanded == this.expanded) {
      return;
    }
    if (expanded) {
      TextRectangle rect = this.widget.getBoundingClientRect();
      this.menu.setAttribute("style", "position:absolute;left:" + rect.getLeft() + "px;top:" + rect.getBottom() + "px");
    } else {
      this.menu.setAttribute("style", "display:none");
      // also collapse all child menus recursively
      if (this.currentEntry != null) {
        this.currentEntry.setCurrent(false);
        this.currentEntry = null;
      }
    }
    this.widget.setAttribute(ATR_ARIA_EXPANDED, "" + expanded);
    this.expanded = expanded;
  }

  @Override
  public TvmAbstractMenuEntry getCurrentEntry() {

    return this.currentEntry;
  }

  @Override
  public void setCurrentEntry(TvmAbstractMenuEntry currentEntry) {

    if (currentEntry == this.currentEntry) {
      return;
    }
    if (this.currentEntry != null) {
      this.currentEntry.setCurrent(false);
    }
    this.currentEntry = currentEntry;
    this.currentEntry.setCurrent(true);
  }

  @Override
  protected void onClick(Event event) {

    boolean collapsed = !this.expanded;
    TvmAbstractMenu<UiAdvancedMenu> parentMenu = getParentMenu();
    if (parentMenu != null) {
      TvmAbstractMenuEntry parentCurrent = parentMenu.getCurrentEntry();
      if (parentCurrent == this) {
        setCurrent(!isCurrent());
      } else {
        parentMenu.setCurrentEntry(this);
      }
    }
    if (collapsed) {
      setExpanded(true);
    }
    super.onClick(event);
  }

  @Override
  public UiAdvancedMenu addMenu(String text, int index) {

    TvmMenu subMenu = new TvmMenu(this.submenus);
    UiWidget.initText(subMenu, text);
    addChild(subMenu, index);
    return subMenu;
  }

  @Override
  public UiMenuItem addItem(String text, UiClickEventListener listener, int index) {

    TvmMenuItem item = new TvmMenuItem();
    UiWidget.initText(item, text);
    if (listener != null) {
      item.addListener(listener);
    }
    addChild(item, index);
    return item;
  }

  @Override
  public UiMenuItemCheckbox addCheckbox(String text, UiClickEventListener listener, int index) {

    TvmMenuItemCheckbox item = new TvmMenuItemCheckbox();
    UiWidget.initText(item, text);
    item.addListener(listener);
    addChild(item, index);
    return item;
  }

  @Override
  public UiMenuItemRadioButton addRadioButton(String text, UiClickEventListener listener, int index) {

    TvmMenuItemRadioButton item = new TvmMenuItemRadioButton();
    UiWidget.initText(item, text);
    item.addListener(listener);
    addChild(item, index);
    return item;
  }

  @Override
  public UiMenuItemSeparator addSeparator(int index) {

    TvmMenuItemSeparator separator = new TvmMenuItemSeparator();
    addChild(separator, index);
    return separator;
  }

  void addChild(UiAbstractMenuEntry child, int index) {

    setParent(child, this);
    Node node = getTopNode(child);
    if (index == -1) {
      this.menu.appendChild(node);
      this.children.add(child);
    } else {
      insertAt(this.menu, node, index);
      this.children.add(index, child);
    }
  }

  @Override
  public UiAbstractMenuEntry removeChild(int index) {

    UiAbstractMenuEntry childItem = this.children.remove(index);
    this.menu.removeChild(getTopNode(childItem));
    setParent(childItem, null);
    return childItem;
  }

  @Override
  public int getChildCount() {

    return this.children.size();
  }

  @Override
  public UiAbstractMenuEntry getChild(int index) {

    if ((index < 0) || (index >= this.children.size())) {
      return null;
    }
    return this.children.get(index);
  }

  @Override
  public int getChildIndex(UiAbstractMenuEntry child) {

    return this.children.indexOf(child);
  }

  /**
   * @return the {@link TvmToggleGroup}.
   */
  public TvmToggleGroup getToggleGroup() {

    if (this.toggleGroup == null) {
      this.toggleGroup = new TvmToggleGroup();
    }
    return this.toggleGroup;
  }

}
