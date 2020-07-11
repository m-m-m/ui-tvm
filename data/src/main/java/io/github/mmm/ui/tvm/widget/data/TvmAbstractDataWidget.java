/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.data.UiAbstractDataWidget;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiAbstractDataWidget} for TeaVM.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public abstract class TvmAbstractDataWidget<R> extends TvmWidgetHtmlElement<HTMLElement>
    implements UiAbstractDataWidget<R> {

  /** The (main) table header row. */
  protected final HTMLElement headerRow;

  /** The table footer row. */
  protected final HTMLElement footerRow;

  /** The table header. */
  protected final HTMLElement theader;

  /** The table body. */
  protected final HTMLElement tbody;

  /** The table footer. */
  protected final HTMLElement tfooter;

  private boolean multiSelection;

  /**
   * The constructor.
   */
  public TvmAbstractDataWidget() {

    super(newTable());
    getStyles().add(STYLE);
    this.theader = newTableHead();
    this.widget.appendChild(this.theader);
    this.headerRow = newTableRow();
    this.theader.appendChild(this.headerRow);
    this.tbody = newTableBody();
    this.widget.appendChild(this.tbody);
    this.tfooter = newTableFoot();
    this.widget.appendChild(this.tfooter);
    this.footerRow = newTableRow();
    this.tfooter.appendChild(this.footerRow);
  }

  @Override
  public boolean isMultiSelection() {

    return this.multiSelection;
  }

  @Override
  public void setMultiSelection(boolean multiSelection) {

    if (multiSelection == isModified()) {
      return;
    }
    setMultiSelectionNative(multiSelection);
    this.multiSelection = multiSelection;
  }

  /**
   * @param multiSelection - see {@link #isMultiSelection()}.
   */
  protected void setMultiSelectionNative(boolean multiSelection) {

  }

}
