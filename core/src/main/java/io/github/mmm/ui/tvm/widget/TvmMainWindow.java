/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.JSObject;
import org.teavm.jso.browser.Screen;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLBodyElement;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.attribute.AttributeWritePosition;
import io.github.mmm.ui.api.attribute.AttributeWriteSizeInPixel;
import io.github.mmm.ui.api.widget.menu.UiMenuBar;
import io.github.mmm.ui.api.widget.window.UiMainWindow;

/**
 * Implementation of {@link UiMainWindow} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMainWindow extends TvmAbstractWindow<Window> implements UiMainWindow {

  private final HTMLBodyElement body;

  private final TvmMainWindowPositionAndSize positionAndSize;

  private UiMenuBar menuBar;

  private String title;

  private int width;

  private int height;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmMainWindow(UiContext context) {

    this(context, Window.current());
  }

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param widget the {@link #getWidget() JavaFx widget}.
   */
  public TvmMainWindow(UiContext context, Window widget) {

    super(context, widget);
    this.body = widget.getDocument().getBody();
    this.positionAndSize = new TvmMainWindowPositionAndSize(widget);
    setParent(this.content, this);
    this.body.appendChild(this.content.getTopWidget());
    this.title = "";
  }

  @Override
  protected boolean isVisibleWithoutRoot() {

    return true;
  }

  @Override
  public JSObject getTopWidget() {

    return this.body;
  }

  @Override
  public HTMLElement getElement() {

    return this.body;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    if (title == null) {
      title = "";
    }
    this.title = title;
    this.widget.getDocument().getHead().setTitle(title);
  }

  @Override
  public AttributeWritePosition getPosition() {

    return this.positionAndSize;
  }

  @Override
  public AttributeWriteSizeInPixel getSize() {

    return this.positionAndSize;
  }

  @Override
  public boolean isMaximized() {

    Screen screen = this.widget.getScreen();
    int widthLeft = screen.getAvailWidth() - this.widget.getOuterWidth();
    int heigthLeft = screen.getAvailHeight() - this.widget.getOuterHeight();
    return (widthLeft == 0) && (heigthLeft == 0);
  }

  @Override
  public void setMaximized(boolean maximized) {

    if (maximized) {
      this.width = this.widget.getOuterWidth();
      this.height = this.widget.getOuterHeight();
      Screen screen = this.widget.getScreen();
      this.widget.resizeTo(screen.getAvailWidth(), screen.getAvailHeight());
    } else {
      if (this.width > 0) {
        this.widget.resizeTo(this.width, this.height);
        this.width = 0;
        this.height = 0;
      }
    }
  }

  @Override
  public boolean isResizable() {

    return false;
  }

  @Override
  public void setResizable(boolean resizable) {

    // not supported to change resizable property of the browser
  }

  @Override
  protected void setVisibleNative(boolean visible) {

    // hiding browser window is odd (bad UX) and therefore unsupported
  }

  @Override
  protected void setReadOnlyNative(boolean readOnly) {

  }

  @Override
  public UiMenuBar getMenuBar() {

    if (this.menuBar == null) {
      this.menuBar = this.context.create(UiMenuBar.class);
      insertFirst(this.body, getTopNode(this.menuBar));
    }
    return this.menuBar;
  }

  @Override
  public boolean isWindowPositionAbsolute() {

    return false;
  }

}
