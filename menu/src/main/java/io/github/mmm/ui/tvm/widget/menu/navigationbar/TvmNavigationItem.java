/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.navigationbar;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiMenuItem;
import io.github.mmm.ui.tvm.widget.link.TvmAbstractLink;

/**
 * Implementation of {@link UiMenuItem} for TeaVM and {@link TvmNavigationBar}.
 *
 * @since 1.0.0
 */
public class TvmNavigationItem extends TvmAbstractLink implements UiMenuItem {

  private final HTMLElement topWidget;

  /**
   * The constructor.
   */
  public TvmNavigationItem() {

    super();
    this.topWidget = newLi();
    this.topWidget.appendChild(this.widget);
    getStyles().add(STYLE_NAV);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

}
