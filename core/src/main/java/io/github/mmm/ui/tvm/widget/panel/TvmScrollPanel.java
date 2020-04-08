/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.datatype.UiEnabledFlags;
import io.github.mmm.ui.api.datatype.UiScrollBarVisibility;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.panel.UiScrollPanel;
import io.github.mmm.ui.tvm.widget.composite.TvmComposite;

/**
 * Implementation of {@link UiScrollPanel} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmScrollPanel extends TvmComposite<HTMLElement, UiRegularWidget> implements UiScrollPanel {

  private UiRegularWidget child;

  private UiScrollBarVisibility horizontalScrolling;

  private UiScrollBarVisibility verticalScrolling;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmScrollPanel(UiContext context) {

    super(context, newElement("ui-scrollpanel"));
    this.horizontalScrolling = UiScrollBarVisibility.AUTO;
    this.verticalScrolling = UiScrollBarVisibility.AUTO;
    updateStyle();
  }

  @Override
  public void setChild(UiRegularWidget child) {

    if (this.child == child) {
      return;
    }
    if (this.child != null) {
      setParent(this.child, null);
      this.widget.removeChild(getTopNode(this.child));
    }
    this.widget.appendChild(getTopNode(child));
    this.child = child;
  }

  @Override
  public UiRegularWidget getChild() {

    return this.child;
  }

  @Override
  public UiScrollBarVisibility getHorizontalScrolling() {

    return this.horizontalScrolling;
  }

  @Override
  public void setHorizontalScrolling(UiScrollBarVisibility scrolling) {

    if (this.horizontalScrolling == scrolling) {
      return;
    }
    this.horizontalScrolling = scrolling;
    updateStyle();
  }

  @Override
  public UiScrollBarVisibility getVerticalScrolling() {

    return this.verticalScrolling;
  }

  @Override
  public void setVerticalScrolling(UiScrollBarVisibility scrolling) {

    if (this.verticalScrolling == scrolling) {
      return;
    }
    this.verticalScrolling = scrolling;
    updateStyle();
  }

  private String convert(UiScrollBarVisibility scrolling) {

    switch (scrolling) {
      case AUTO:
        return "auto";
      case HIDDEN:
        return "hidden";
      case ALWAYS:
        return "scroll";
    }
    throw new IllegalStateException();
  }

  private void updateStyle() {

    String style = "overflow-x:" + convert(this.horizontalScrolling) + ";overflow-y:" + convert(this.verticalScrolling);
    this.widget.setAttribute(ATR_STYLE, style);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    if (this.child != null) {
      this.child.setEnabled(enabled, UiEnabledFlags.PARENT);
    }
  }

}
