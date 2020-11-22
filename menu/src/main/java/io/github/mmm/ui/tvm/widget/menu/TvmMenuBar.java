/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.api.widget.menu.UiAdvancedMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuBar;
import io.github.mmm.ui.tvm.widget.composite.TvmRemovableComposite;

/**
 * Implementation of {@link UiMenuBar} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuBar extends TvmRemovableComposite<HTMLElement, UiAdvancedMenu>
    implements UiMenuBar, TvmAbstractMenu<UiAdvancedMenu> {

  private final HTMLElement submenus;

  private final HTMLElement topWidget;

  private TvmAbstractMenuEntry currentEntry;

  private TvmAbstractMenuEntry activeEntry;

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
    this.topWidget = newElement("ui-menucontainer");
    this.widget.setAttribute(ATR_ROLE, "menubar");
    this.submenus = newElement("ui-submenus");
    this.topWidget.appendChild(widget);
    this.topWidget.appendChild(this.submenus);
    insertFirst(DOC.getBody(), this.topWidget);
    ensureHandlers();
  }

  @Override
  protected void registerHandlers() {

    super.registerHandlers();
    this.topWidget.addEventListener(EVENT_TYPE_KEYDOWN, this::onKeyDown);
  }

  private void onKeyDown(Event e) {

    KeyboardEvent keyEvent = e.cast();
    switch (keyEvent.getKeyCode()) {
      case 27:
        onKeyEscape();
        break;
      case 37:
        onKeyLeft();
        break;
      case 38:
        onKeyUp();
        break;
      case 39:
        onKeyRight();
        break;
      case 40:
        onKeyDown();
        break;
    }
  }

  private void onKeyEscape() {

    setCurrentEntry(null);
    setActiveEntry((TvmAbstractMenuEntry) getChild(0), true);
  }

  private void onKeyLeft() {

    onKeyLeftRight(-1);
  }

  private void onKeyRight() {

    onKeyLeftRight(1);
  }

  private void onKeyLeftRight(int offset) {

    if (this.activeEntry != null) {
      UiComposite<?> parent = this.activeEntry.getParent();
      if (parent == this) {
        UiAdvancedMenu childSibling = getChildSibling((UiAdvancedMenu) this.activeEntry, offset);
        if (childSibling != null) {
          setActiveEntry((TvmAbstractMenuEntry) childSibling, true);
        }
      } else {
        if ((offset == 1) && (this.activeEntry instanceof TvmMenu)) {
          openSubMenu(1);
        } else {
          UiComposite<?> ancestor = parent.getParent();
          while (ancestor != null) {
            if (ancestor instanceof TvmMenuBar) {
              TvmMenu parentMenu = (TvmMenu) parent;
              closeSubMenu(parentMenu);
              UiAdvancedMenu childSibling = getChildSibling(parentMenu, offset);
              if (childSibling != null) {
                openSubMenu(1, (TvmMenu) childSibling);
              }
              ancestor = null;
            } else if (offset == -1) {
              closeSubMenu((TvmMenu) parent);
              ancestor = null;
            } else {
              parent = ancestor;
              ancestor = parent.getParent();
            }
          }
        }
      }
    }
  }

  private void closeSubMenu(TvmMenu menu) {

    menu.setExpanded(false);
    setActiveEntry(menu, true);
  }

  private void openSubMenu(int offset) {

    if (this.activeEntry instanceof TvmMenu) {
      openSubMenu(offset, (TvmMenu) this.activeEntry);
    }
  }

  private void openSubMenu(int offset, TvmMenu menu) {

    menu.setExpanded(true);
    UiAbstractMenuEntry firstChild;
    if (offset == 1) {
      firstChild = menu.getChild(0);
    } else {
      firstChild = menu.getChild(menu.getChildCount() - 1);
    }
    if (firstChild != null) {
      setActiveEntry((TvmAbstractMenuEntry) firstChild, false);
    }
  }

  private void onKeyUp() {

    onKeyUpDown(-1);
  }

  private void onKeyDown() {

    onKeyUpDown(1);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private void onKeyUpDown(int offset) {

    if (this.activeEntry != null) {
      UiComposite parent = this.activeEntry.getParent();
      if (parent == this) {
        openSubMenu(offset);
      } else if (parent != null) {
        TvmAbstractMenuEntry sibling = (TvmAbstractMenuEntry) parent.getChildSibling(this.activeEntry, offset, false);
        // skip separators
        while (sibling instanceof TvmMenuItemSeparator) {
          sibling = (TvmAbstractMenuEntry) parent.getChildSibling(sibling, offset, false);
        }
        if (sibling != null) {
          setActiveEntry(sibling, true);
        }
      }
    }
  }

  @Override
  protected void addChildWidget(UiAdvancedMenu child, int index) {

    if ((index == 0) || ((index == -1) && this.children.isEmpty())) {
      TvmMenu firstChild = (TvmMenu) getChild(0);
      if ((firstChild != null) && (firstChild == this.activeEntry)) {
        firstChild.setActive(false);
        this.activeEntry = null;
      }
      if (this.activeEntry == null) {
        TvmMenu menu = (TvmMenu) child;
        menu.setActive(true, false);
        this.activeEntry = menu;
      }

    }
    super.addChildWidget(child, index);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  /**
   * @return the {@link HTMLElement} where sub-menus are temporary added and removed. Shall be placed below menu-bar
   *         element.
   */
  protected HTMLElement getSubmenus() {

    return this.submenus;
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
    if (this.currentEntry != null) {
      this.currentEntry.setCurrent(true);
    }
  }

  TvmAbstractMenuEntry getActiveEntry() {

    return this.activeEntry;
  }

  void setActiveEntry(TvmAbstractMenuEntry activeEntry, boolean unsetCurrent) {

    if (activeEntry == this.activeEntry) {
      return;
    }
    if (this.activeEntry != null) {
      this.activeEntry.setActive(false);
      if (unsetCurrent) {
        this.activeEntry.setCurrent(false);
      }
    }
    this.activeEntry = activeEntry;
    if (this.activeEntry != null) {
      this.activeEntry.setCurrent(true);
      this.activeEntry.setActive(true);
    }
  }

  @Override
  public UiAdvancedMenu addMenu(String text, int index) {

    TvmMenu menu = new TvmMenu(this.submenus);
    UiWidget.initText(menu, text);
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