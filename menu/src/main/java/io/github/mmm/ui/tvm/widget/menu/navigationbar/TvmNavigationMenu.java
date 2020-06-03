/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.navigationbar;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLAnchorElement;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.attribute.AttributeWriteCollapsed;
import io.github.mmm.ui.api.widget.img.UiIcon;
import io.github.mmm.ui.api.widget.menu.UiMenu;
import io.github.mmm.ui.api.widget.menu.UiMenuItem;

/**
 * Implementation of {@link UiMenu} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmNavigationMenu extends TvmAbstractMenuWithItems<HTMLAnchorElement>
    implements UiMenu, AttributeWriteCollapsed {

  private final HTMLElement topWidget;

  private final UiIcon icon;

  private boolean collapsed;

  /**
   * The constructor.
   */
  public TvmNavigationMenu() {

    super(newAnchor());
    this.topWidget = newLi();
    this.topWidget.setClassName(STYLE_NAV);
    getStyles().add(UiMenuItem.STYLE_NAV);
    this.icon = UiIcon.of(UiIcon.ID_COLLAPSE);
    this.widget.appendChild(getTopNode(this.icon));
    this.topWidget.appendChild(this.widget);
    this.topWidget.appendChild(this.compositeElement);
    ensureHandlers();
  }

  @Override
  protected void registerHandlers() {

    super.registerHandlers();
    this.widget.addEventListener(EVENT_TYPE_CLICK, this::onClick);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  @Override
  public String getText() {

    return this.widget.getText();
  }

  @Override
  public void setText(String text) {

    this.widget.setText(text);
  }

  @Override
  public boolean isCollapsed() {

    return this.collapsed;
  }

  @Override
  public void setCollapsed(boolean collapsed) {

    if (collapsed == this.collapsed) {
      return;
    }
    this.compositeElement.setHidden(collapsed);
    if (collapsed) {
      this.icon.setIconId(UiIcon.ID_EXPAND);
    } else {
      this.icon.setIconId(UiIcon.ID_COLLAPSE);
    }
    this.collapsed = collapsed;
  }

  @Override
  protected void onClick(Event event) {

    setCollapsed(!this.collapsed);
    super.onClick(event);
  }

}
