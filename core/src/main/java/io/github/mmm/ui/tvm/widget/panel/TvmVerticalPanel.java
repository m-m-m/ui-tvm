/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.panel.UiVerticalPanel;

/**
 * Implementation of {@link UiVerticalPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmVerticalPanel extends TvmMutablePanel<HTMLElement> implements UiVerticalPanel {

  /**
   * The constructor.
   */
  public TvmVerticalPanel() {

    super(newElement(STYLE));
  }

  /**
   * The constructor.
   * 
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmVerticalPanel(HTMLElement widget) {

    super(widget);
  }

}
