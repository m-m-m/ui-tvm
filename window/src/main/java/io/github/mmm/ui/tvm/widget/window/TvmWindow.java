/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import org.teavm.jso.dom.events.Event;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.widget.window.UiAbstractWindow;
import io.github.mmm.ui.api.widget.window.UiChildWindow;
import io.github.mmm.ui.api.widget.window.UiPopup;
import io.github.mmm.ui.api.widget.window.UiWindow;

/**
 * Implementation of {@link UiPopup} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmWindow extends TvmChildWindow implements UiWindow {

  static TvmWindow TOPMOST_WINDOW;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmWindow(UiContext context) {

    super(context);
  }

  @Override
  protected void doOpen() {

    UiAbstractWindow parent;
    if (TOPMOST_WINDOW == null) {
      parent = this.context.getMainWindow();
      this.z = 100;
    } else {
      parent = TOPMOST_WINDOW;
      this.z = TOPMOST_WINDOW.z + 10;
    }
    setParent(parent);
    super.doOpen();
    TOPMOST_WINDOW = this;
  }

  @Override
  protected void doClose() {

    UiAbstractWindow parent = getParent();
    if (TOPMOST_WINDOW == this) {
      if (parent instanceof TvmWindow) {
        TOPMOST_WINDOW = (TvmWindow) parent;
      } else {
        TOPMOST_WINDOW = null;
      }
    } else {
      UiChildWindow topmost = TvmPopup.TOPMOST_POPUP;
      if (topmost == null) {
        topmost = TOPMOST_WINDOW;
      }
      UiAbstractWindow child = findChild(topmost);
      if (child != null) {
        setParent(child, parent);
      }
    }
    super.doClose();
  }

  @Override
  protected void onClick(Event event) {

    super.onClick(event);
    bringToFront();
  }

  @Override
  public void bringToFront() {

    if ((this == TOPMOST_WINDOW) || (TOPMOST_WINDOW == null)) {
      // already at the front
      return;
    }
    int topZ = TOPMOST_WINDOW.z;
    TvmWindow current = TOPMOST_WINDOW;
    while ((current != this) && (current != null)) {
      UiAbstractWindow parent = current.getParent();
      if (parent instanceof TvmWindow) {
        TvmWindow parentWindow = (TvmWindow) parent;
        current.z = parentWindow.z;
        current.updateStyle();
        if (parentWindow == this) {
          current.setParent(getParent());
        }
        current = parentWindow;
      } else {
        current = null;
      }
    }
    this.z = topZ;
    updateStyle();
    UiAbstractWindow lowestPopup = TOPMOST_WINDOW.findChild(TvmPopup.TOPMOST_POPUP);
    if (lowestPopup != null) {
      setParent(lowestPopup, this);
    }
    setParent(TOPMOST_WINDOW);
    TOPMOST_WINDOW = this;
  }

}
