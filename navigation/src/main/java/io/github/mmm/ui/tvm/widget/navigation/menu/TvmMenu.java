/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.navigation.menu;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;

import io.github.mmm.ui.api.event.UiClickEventListener;
import io.github.mmm.ui.api.widget.navigation.UiAbstractMenuEntry;
import io.github.mmm.ui.api.widget.navigation.UiAdvancedMenu;
import io.github.mmm.ui.api.widget.navigation.UiMenuItem;
import io.github.mmm.ui.api.widget.navigation.UiMenuItemCheckbox;
import io.github.mmm.ui.api.widget.navigation.UiMenuItemRadioButton;
import io.github.mmm.ui.api.widget.navigation.UiMenuItemSeparator;
import io.github.mmm.ui.tvm.TvmToggleGroup;

/**
 * Implementation of {@link UiAdvancedMenu} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenu extends TvmAbstractButtonMenuItem implements UiAdvancedMenu {

  private final HTMLElement menu;

  private final List<UiAbstractMenuEntry> children;

  private TvmToggleGroup toggleGroup;

  /**
   * The constructor.
   */
  public TvmMenu() {

    this(newButton());
  }

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmMenu(HTMLButtonElement widget) {

    super(widget);
    this.children = new ArrayList<>();
    this.menu = newElement("ui-menu");
  }

  @Override
  protected void onClick(Event event) {

    Node parentNode = this.menu.getParentNode();
    if (parentNode == null) {
      this.menu.setAttribute("style", "left:" + this.widget.getAbsoluteLeft() + "px,top:"
          + (this.widget.getAbsoluteTop() + this.widget.getClientHeight()) + "px");
      DOC.getBody().appendChild(this.menu);
    } else {
      parentNode.removeChild(this.menu);
    }
    super.onClick(event);
  }

  @Override
  public UiAdvancedMenu addMenu(String text, int index) {

    TvmMenu advancedMenu = new TvmMenu();
    advancedMenu.setText(text);
    addChild(advancedMenu, index);
    return advancedMenu;
  }

  @Override
  public UiMenuItem addItem(String text, UiClickEventListener listener, int index) {

    TvmMenuItem item = new TvmMenuItem();
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
  public UiMenuItemCheckbox addCheckbox(String text, UiClickEventListener listener, int index) {

    TvmMenuItemCheckbox item = new TvmMenuItemCheckbox();
    item.setText(text);
    item.addListener(listener);
    addChild(item, index);
    return item;
  }

  @Override
  public UiMenuItemRadioButton addRadioButton(String text, UiClickEventListener listener, int index) {

    TvmMenuItemRadioButton item = new TvmMenuItemRadioButton();
    item.setText(text);
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
