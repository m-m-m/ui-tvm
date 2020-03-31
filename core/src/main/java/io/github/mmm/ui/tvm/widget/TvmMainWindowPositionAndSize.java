/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLBodyElement;

import io.github.mmm.ui.attribute.AttributeWritePosition;
import io.github.mmm.ui.attribute.AttributeWriteSizeInPixel;

/**
 * Implementation of {@link AttributeWritePosition} and {@link AttributeWriteSizeInPixel} for {@link TvmMainWindow}.
 */
class TvmMainWindowPositionAndSize implements AttributeWritePosition, AttributeWriteSizeInPixel {

  private final Window widget;

  /**
   * The constructor.
   *
   * @param window the {@link HTMLBodyElement}.
   */
  public TvmMainWindowPositionAndSize(Window window) {

    super();
    this.widget = window;
  }

  @Override
  public double getX() {

    return this.widget.getScreenX();
  }

  @Override
  public double getY() {

    return this.widget.getScreenY();
  }

  @Override
  public void setX(double x) {

    this.widget.moveTo((int) x, this.widget.getScreenY());
  }

  @Override
  public void setY(double y) {

    this.widget.moveTo(this.widget.getScreenX(), (int) y);
  }

  @Override
  public double getWidthInPixel() {

    return this.widget.getOuterWidth();
  }

  @Override
  public void setWidthInPixel(double width) {

    this.widget.resizeTo((int) width, this.widget.getOuterHeight());
  }

  @Override
  public double getHeightInPixel() {

    return this.widget.getOuterHeight();
  }

  @Override
  public void setHeightInPixel(double height) {

    this.widget.resizeTo(this.widget.getOuterWidth(), (int) height);
  }

  @Override
  public void setSizeInPixel(double width, double height) {

    this.widget.resizeTo((int) width, (int) height);
  }

}
