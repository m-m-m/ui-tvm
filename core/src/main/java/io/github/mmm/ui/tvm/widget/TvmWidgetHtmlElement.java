/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.dom.html.HTMLElement;

/**
 * Implementation of {@link io.github.mmm.ui.api.widget.UiNativeWidget} for TeaVM based on {@link HTMLElement}.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmWidgetHtmlElement<W extends HTMLElement> extends TvmWidget<W> {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmWidgetHtmlElement(W widget) {

    super(widget);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.widget;
  }

  @Override
  public HTMLElement getElement() {

    return this.widget;
  }

  @Override
  protected void onStylesChanged(String newStyles) {

    super.onStylesChanged(newStyles);
    if (this.widget != null) {
      this.widget.setClassName(newStyles);
    }
  }

  @Override
  protected void setVisibleNative(boolean visible) {

    if (this.widget != null) {
      this.widget.setHidden(!visible);
    }
  }

  @Override
  protected void setTooltipNative(String tooltip) {

    if (this.widget != null) {
      this.widget.setTitle(tooltip);
    }
  }
}
