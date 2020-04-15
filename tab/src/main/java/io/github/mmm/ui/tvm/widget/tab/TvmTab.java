/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.tab;

import java.util.function.Supplier;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.tab.UiTab;
import io.github.mmm.ui.tvm.widget.composite.TvmComposite;

/**
 * Implementation of {@link UiTab} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmTab extends TvmComposite<HTMLButtonElement, UiRegularWidget> implements UiTab {

  private final HTMLElement labelWidget;

  private final HTMLElement sectionWidget;

  private final HTMLElement errorWidget;

  private String text;

  private UiRegularWidget child;

  private Supplier<UiRegularWidget> childSupplier;

  private boolean closable;

  private boolean selected;

  /**
   * The constructor.
   */
  public TvmTab() {

    super(newButton());
    this.widget.setAttribute(ATR_ROLE, "tab");
    HTMLDocument document = Window.current().getDocument();
    this.errorWidget = newIcon(CLASS_ERROR);
    this.errorWidget.setHidden(true);
    this.widget.appendChild(this.errorWidget);
    this.labelWidget = newLabel();
    this.widget.appendChild(this.labelWidget);
    this.text = "";
    this.closable = false;
    this.sectionWidget = document.createElement("ui-tabcontent");
    setSelected(false);
    this.widget.addEventListener(EVENT_TYPE_CLICK, this::onClick);
  }

  private TvmTabPanel getTabPanel() {

    UiComposite<?> parent = getParent();
    if (parent instanceof TvmTabPanel) {
      return (TvmTabPanel) parent;
    }
    return null;
  }

  @Override
  protected void onClick(Event event) {

    super.onClick(event);
    TvmTabPanel tabPanel = getTabPanel();
    if (tabPanel != null) {
      tabPanel.setActiveChild(this);
    }
  }

  /**
   * @return the widget with the {@link #getChild() content} of this tab.
   */
  public HTMLElement getSectionWidget() {

    return this.sectionWidget;
  }

  @Override
  public void setChild(UiRegularWidget child) {

    if (child == this.child) {
      return;
    }
    if (this.child != null) {
      setParent(this.child, null);
    }
    this.child = child;
    this.sectionWidget.clear();
    this.sectionWidget.appendChild(getTopNode(child));
    setParent(child, this);
  }

  /**
   * @param childSupplier the {@link Supplier} {@link Supplier#get() providing} the {@link #getChild() child widget}.
   */
  public void setChild(Supplier<UiRegularWidget> childSupplier) {

    if (this.child != null) {
      throw new IllegalStateException();
    }
    this.childSupplier = childSupplier;
  }

  @Override
  public UiRegularWidget getChild() {

    if ((this.child == null) && (this.childSupplier != null)) {
      setChild(this.childSupplier.get());
      assert (this.child != null);
      this.childSupplier = null;
    }
    return this.child;
  }

  @Override
  public boolean isClosable() {

    return this.closable;
  }

  @Override
  public void setClosable(boolean closable) {

    if (closable == this.closable) {
      return;
    }
    this.widget.setAttribute("closable", Boolean.toString(closable));
    this.closable = closable;
  }

  @Override
  public char getAccessKey() {

    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setAccessKey(char accessKey) {

    // TODO Auto-generated method stub

  }

  @Override
  public String getText() {

    return this.text;
  }

  @Override
  public void setText(String text) {

    if (text == null) {
      text = "";
    }
    this.text = text;
    setTextContent(this.labelWidget, text);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

  @Override
  protected void setVisibleNative(boolean visible) {

    super.setVisibleNative(visible);
    if (!visible) {
      this.sectionWidget.setAttribute(ATR_ARIA_HIDDEN, "true");
      if (this.selected) {
        TvmTabPanel tabPanel = getTabPanel();
        if (tabPanel != null) {
          int activeChildIndex = tabPanel.getActiveChildIndex();
          if (activeChildIndex == 0) {
            tabPanel.selectNextTab();
          } else {
            tabPanel.selectPreviousTab();
          }
        }
      }
    }
  }

  /**
   * @param selected {@code true} to set this tab selected, {@code false} for deselected.
   */
  public void setSelected(boolean selected) {

    setSelected(selected, selected);
  }

  /**
   * @param selected {@code true} to set this tab selected, {@code false} for deselected.
   * @param focus {@code true} to focus this tab, {@code false} otherwise.
   */
  public void setSelected(boolean selected, boolean focus) {

    this.widget.setAttribute(ATR_TABINDEX, selected ? "0" : "-1");
    this.widget.setAttribute(ATR_ARIA_SELECTED, Boolean.toString(selected));
    if (selected) {
      getChild(); // ensure lazy loading from Supplier
    }
    TvmTabPanel tabPanel = getTabPanel();
    if (tabPanel != null) {
      HTMLElement tabContent = tabPanel.getTopWidget();
      if (selected) {
        tabContent.appendChild(this.sectionWidget);
      } else {
        tabContent.removeChild(this.sectionWidget);
      }
    }
    this.selected = selected;
    if (focus) {
      this.widget.focus();
    }
  }

  @Override
  protected void doSetValidationFailure(String error) {

    super.doSetValidationFailure(error);
    this.errorWidget.setHidden(error == null);
    this.errorWidget.setTitle(error);
    if (error == null) {
      this.sectionWidget.setClassName("");
    } else {
      this.sectionWidget.setClassName(STYLE_INVALID);
    }
  }

}
