/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.tab;

import java.util.function.Supplier;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.datatype.UiEnabledFlags;
import io.github.mmm.ui.api.datatype.UiVisibleFlags;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.tab.UiTab;
import io.github.mmm.ui.api.widget.tab.UiTabPanel;
import io.github.mmm.ui.tvm.widget.composite.TvmMutableComposite;

/**
 * Implementation of {@link UiTabPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmTabPanel extends TvmMutableComposite<HTMLElement, UiTab> implements UiTabPanel {

  private final HTMLElement topWidget;

  private int selectedTabIndex;

  /**
   * The constructor.
   */
  public TvmTabPanel() {

    super(newElement("ui-tabs"));
    this.topWidget = newElement("ui-tabpanel");
    this.topWidget.appendChild(this.widget);
    this.selectedTabIndex = -1;
    this.widget.addEventListener(EVENT_TYPE_KEYDOWN, this::onKeyDown);
  }

  private void onKeyDown(Event e) {

    KeyboardEvent keyEvent = e.cast();
    switch (keyEvent.getKeyCode()) {
      case 37: // arrow left
      case 38: // arrow up
        selectPreviousTab();
        break;
      case 39: // arrow right
      case 40: // arrow down
        selectNextTab();
        break;
    }
  }

  /**
   * Selects the next {@link UiTab}.
   */
  public void selectNextTab() {

    selectTab(true);
  }

  /**
   * Selects the previous {@link UiTab}.
   */
  public void selectPreviousTab() {

    selectTab(false);
  }

  private void selectTab(boolean next) {

    int tabIndex = this.selectedTabIndex;
    int size = this.children.size();
    if (size == 0) {
      return;
    }
    while (true) {
      if (tabIndex < 0) {
        tabIndex = 0;
      } else if (next) {
        tabIndex++;
        if (tabIndex >= size) {
          tabIndex = 0;
        }
      } else {
        tabIndex--;
        if (tabIndex < 0) {
          tabIndex = size - 1;
        }
      }
      if (tabIndex == this.selectedTabIndex) {
        return; // no other (visible) tab
      }
      UiTab tab = getChild(tabIndex);
      if (tab == null) {
        return;
      }
      if (tab.isVisible(UiVisibleFlags.ALL)) {
        setActiveChild(tab, tabIndex);
        return;
      }
    }
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  @Override
  public void addChild(UiTab child, int index) {

    int size = this.children.size();
    super.addChild(child, index);
    TvmTab tab = (TvmTab) child;
    if (size == 0) {
      tab.setSelected(true, false);
      this.selectedTabIndex = 0;
    }
  }

  @Override
  public UiTab addTab(String text, UiRegularWidget child, int index) {

    TvmTab tab = new TvmTab();
    UiWidget.initText(tab, text);
    tab.setChild(child);
    addChild(tab, index);
    return tab;
  }

  @Override
  public UiTab addTab(String text, Supplier<UiRegularWidget> childSupplier, int index) {

    TvmTab tab = new TvmTab();
    UiWidget.initText(tab, text);
    tab.setChild(childSupplier);
    addChild(tab, index);
    return tab;
  }

  @Override
  protected void removeChildWidget(UiTab child) {

    super.removeChildWidget(child);
    TvmTab tab = (TvmTab) child;
    if (tab.isSelected()) {
      tab.setSelected(false);
      this.selectedTabIndex = -1;
      selectNextTab();
    }
  }

  @Override
  public int getActiveChildIndex() {

    return this.selectedTabIndex;
  }

  @Override
  public void setActiveChildIndex(int index) {

    UiTab tab = getChild(index);
    if (tab == null) {
      return;
    }
    setActiveChild(tab, index);
  }

  @Override
  public boolean setActiveChild(UiTab child) {

    int index = this.children.indexOf(child);
    if (index < 0) {
      return false;
    }
    setActiveChild(child, index);
    return true;
  }

  private void setActiveChild(UiTab tab, int index) {

    if (this.selectedTabIndex == index) {
      return;
    }
    UiTab previousTab = getChild(this.selectedTabIndex);
    if (previousTab != null) {
      ((TvmTab) previousTab).setSelected(false);
    }
    this.selectedTabIndex = index;
    ((TvmTab) tab).setSelected(true);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    int size = this.children.size();
    for (int i = 0; i < size; i++) {
      TvmTab tab = (TvmTab) this.children.get(i);
      tab.setEnabled(enabled, UiEnabledFlags.PARENT);
    }
  }

}
