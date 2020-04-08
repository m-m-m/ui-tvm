/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.JSObject;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.attribute.AttributeWriteMaximized;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.window.UiAbstractWindow;
import io.github.mmm.ui.tvm.widget.panel.TvmVerticalPanel;

/**
 * Implementation of {@link UiAbstractWindow} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmAbstractWindow<W extends JSObject> extends TvmWidgetJsObject<W>
    implements UiAbstractWindow, AttributeWriteMaximized {

  /** The content panel. */
  protected final TvmVerticalPanel content;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param nativeWidget the {@link #getWidget() TeaVM widget}.
   */
  public TvmAbstractWindow(UiContext context, W nativeWidget) {

    super(context, nativeWidget);
    this.content = new TvmVerticalPanel(context);
  }

  @Override
  public UiAbstractWindow getParent() {

    return (UiAbstractWindow) super.getParent();
  }

  @Override
  protected void setParent(UiComposite<?> parent) {

    assert ((parent == null) || (parent instanceof UiAbstractWindow));
    super.setParent(parent);
  }

  @Override
  public int getChildCount() {

    return this.content.getChildCount();
  }

  @Override
  public UiRegularWidget getChild(int index) {

    return this.content.getChild(index);
  }

  @Override
  public int getChildIndex(UiRegularWidget child) {

    return this.content.getChildIndex(child);
  }

  @Override
  public void addChild(UiRegularWidget child, int index) {

    this.content.addChild(child, index);
  }

  @Override
  public UiRegularWidget removeChild(int index) {

    return this.content.removeChild(index);
  }

}
