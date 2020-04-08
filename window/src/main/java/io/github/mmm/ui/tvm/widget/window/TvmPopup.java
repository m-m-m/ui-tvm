/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.widget.panel.UiButtonPanel;
import io.github.mmm.ui.api.widget.window.UiAbstractWindow;
import io.github.mmm.ui.api.widget.window.UiPopup;

/**
 * Implementation of {@link UiPopup} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmPopup extends TvmChildWindow implements UiPopup {

  static TvmPopup TOPMOST_POPUP;

  private final HTMLElement modalPane;

  private final UiButtonPanel buttonPanel;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmPopup(UiContext context) {

    super(context);
    this.modalPane = newElement("ui-modal");
    this.buttonPanel = UiButtonPanel.of(context);
    this.contentPane.appendChild(getTopNode(this.buttonPanel));
  }

  @Override
  public UiButtonPanel getButtonPanel() {

    return this.buttonPanel;
  }

  @Override
  protected void doOpen() {

    UiAbstractWindow parent;
    if (TOPMOST_POPUP == null) {
      parent = TvmWindow.TOPMOST_WINDOW;
      if (parent == null) {
        parent = this.context.getMainWindow();
      }
      // if you open 10.000+ instances of UiWindow you will be in trouble but already due to memory
      this.z = 100_000;
    } else {
      parent = TOPMOST_POPUP;
      this.z = TOPMOST_POPUP.z + 10;
    }
    setParent(parent);
    this.modalPane.setAttribute(ATR_STYLE, "z-index:" + (this.z - 1));
    this.body.appendChild(this.modalPane);
    super.doOpen();
    TOPMOST_POPUP = this;
  }

  @Override
  protected void doClose() {

    this.body.removeChild(this.modalPane);
    UiAbstractWindow parent = getParent();
    if (TOPMOST_POPUP == this) {
      if (parent instanceof TvmPopup) {
        TOPMOST_POPUP = (TvmPopup) parent;
      } else {
        TOPMOST_POPUP = null;
      }
    } else {
      UiAbstractWindow child = findChild(TOPMOST_POPUP);
      if (child != null) {
        setParent(child, parent);
      }
    }
    super.doClose();
  }

}
