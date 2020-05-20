/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.button.UiAbstractButton;
import io.github.mmm.ui.api.widget.panel.UiButtonPanel;
import io.github.mmm.ui.tvm.widget.composite.TvmMutableComposite;

/**
 * Implementation of {@link UiButtonPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmButtonPanel extends TvmMutableComposite<HTMLElement, UiAbstractButton> implements UiButtonPanel {

  /**
   * The constructor.
   */
  public TvmButtonPanel() {

    super(Window.current().getDocument().createElement(STYLE));
    getStyles().add(STYLE);
  }

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmButtonPanel(HTMLElement widget) {

    super(widget);
  }

}
