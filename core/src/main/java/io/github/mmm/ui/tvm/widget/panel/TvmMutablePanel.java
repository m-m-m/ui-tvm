/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.panel.UiMutablePanel;
import io.github.mmm.ui.tvm.widget.composite.TvmMutableComposite;

/**
 * Implementation of {@link UiMutablePanel} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public class TvmMutablePanel<W extends HTMLElement> extends TvmMutableComposite<W, UiRegularWidget>
    implements UiMutablePanel {

  /**
   * The constructor.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmMutablePanel(W widget) {

    super(widget);
  }

}
