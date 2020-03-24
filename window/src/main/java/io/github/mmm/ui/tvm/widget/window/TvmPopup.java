/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.panel.UiButtonPanel;
import io.github.mmm.ui.widget.window.UiPopup;

/**
 * Implementation of {@link UiPopup} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmPopup extends TvmChildWindow implements UiPopup {

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
    this.widget.appendChild(getTopNode(this.buttonPanel));
  }

  @Override
  public UiButtonPanel getButtonPanel() {

    return this.buttonPanel;
  }

  @Override
  protected void doSetVisible(boolean newVisible) {

    super.doSetVisible(newVisible);
    if (newVisible) {
      this.modalPane.setAttribute(ATR_STYLE, "z-index:" + (this.z - 1));
      Window.current().getDocument().getBody().appendChild(this.modalPane);
    } else {
      Window.current().getDocument().getBody().removeChild(this.modalPane);
    }
  }

}
