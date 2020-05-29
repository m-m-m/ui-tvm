/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.panel.UiGridPanel;
import io.github.mmm.ui.api.widget.panel.UiGridRow;
import io.github.mmm.ui.tvm.widget.composite.TvmMutableComposite;

/**
 * Implementation of {@link UiGridPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmGridPanel extends TvmMutableComposite<HTMLElement, UiGridRow> implements UiGridPanel {

  /**
   * The constructor.
   */
  public TvmGridPanel() {

    super(newTable());
    this.widget.setAttribute(ATR_ROLE, ROLE_PRESENTATION);
    getStyles().add(STYLE);
  }

  @Override
  public UiGridRow addRow(int rowIndex) {

    TvmGridRow row = new TvmGridRow();
    addChild(row, rowIndex);
    return row;
  }

}
