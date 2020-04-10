/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.panel.UiHorizontalPanel;

/**
 * Implementation of {@link UiHorizontalPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmHorizontalPanel extends TvmDynamicPanel<HTMLElement> implements UiHorizontalPanel {

  /**
   * The constructor.
   */
  public TvmHorizontalPanel() {

    super(newElement("ui-hpanel"));
  }

  /**
   * The constructor.
   * 
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmHorizontalPanel(HTMLElement widget) {

    super(widget);
  }

}
