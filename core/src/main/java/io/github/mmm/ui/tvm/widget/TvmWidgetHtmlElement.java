/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.UiContext;

/**
 * Implementation of {@link io.github.mmm.ui.api.widget.UiNativeWidget} for TeaVM based on {@link HTMLElement}.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmWidgetHtmlElement<W extends HTMLElement> extends TvmWidgetJsObject<W> {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmWidgetHtmlElement(UiContext context, W widget) {

    super(context, widget);
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
    this.widget.setClassName(newStyles);
  }

  @Override
  protected void setVisibleNative(boolean visible) {

    this.widget.setHidden(!visible);
  }

  /**
   * @return {@link io.github.mmm.ui.api.widget.UiAtomicWidget#getTooltip()}
   */
  public String getTooltip() {

    return this.widget.getTitle();
  }

  /**
   * @param tooltip - see {@link io.github.mmm.ui.api.widget.UiAtomicWidget#setTooltip(String)}
   */
  public void setTooltip(String tooltip) {

    this.widget.setTitle(tooltip);
  }

  protected void setTextContent(String text) {

    setTextContent(this.widget, text);
  }
}
