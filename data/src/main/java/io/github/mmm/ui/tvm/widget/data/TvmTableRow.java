/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.ui.api.attribute.AttributeWriteMultiSelection;
import io.github.mmm.ui.api.attribute.AttributeWriteSelected;
import io.github.mmm.ui.api.attribute.AttributeWriteShowRowNumbers;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.ui.tvm.widget.input.TvmHtmlInput;

/**
 * A {@link TvmAbstractDataTable table} row for TeaVM.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public class TvmTableRow<R> extends TvmWidgetHtmlElement<HTMLElement>
    implements UiValuedWidget<R>, AttributeWriteShowRowNumbers, AttributeWriteMultiSelection, AttributeWriteSelected {

  private final TvmAbstractDataTable<R> table;

  private HTMLElement selectionCell;

  private HTMLInputElement selectionInput;

  private HTMLElement rowNumber;

  private boolean selected;

  private R value;

  private TvmTableCell<R, ?>[] cells;

  private int cellCount;

  /**
   * The constructor.
   *
   * @param table the owning {@link TvmAbstractDataTable}.
   */
  public TvmTableRow(TvmAbstractDataTable<R> table) {

    super(newTableRow());
    this.table = table;
    // this.widget.addEventListener(EVENT_TYPE_CLICK, e -> );
    setReadOnly(true);
  }

  @Override
  public R getValue() {

    return this.value;
  }

  @Override
  public void setValue(R value) {

    setValue(value, true);
  }

  public void setValue(R value, boolean update) {

    this.value = value;
    if (update) {
      for (int i = 0; i < this.cellCount; i++) {
        TvmTableCell<R, ?> cell = this.cells[i];
        cell.setRowValue(value);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void addColumn(TvmTableColumn<R, ?> column, int index) {

    int cIndex = index;
    if (index == -1) {
      cIndex = this.cellCount;
    }
    if (this.cells == null) {
      this.cells = new TvmTableCell[this.cellCount + 4];
    } else if (this.cellCount >= this.cells.length) { // ensure capacity
      TvmTableCell<R, ?>[] newCells = new TvmTableCell[this.cellCount + 4];
      System.arraycopy(this.cells, 0, newCells, 0, this.cells.length);
      this.cells = newCells;
    }
    if (cIndex < this.cellCount) {
      System.arraycopy(this.cells, cIndex, this.cells, cIndex + 1, this.cellCount - cIndex);
    }
    this.cells[cIndex] = createCell(column);
    this.cellCount++;
    HTMLElement td = this.cells[cIndex].td;
    if (index == -1) {
      this.widget.appendChild(td);
    } else {
      int tIndex = cIndex;
      if (isMultiSelection()) {
        tIndex++;
      }
      if (this.rowNumber != null) {
        tIndex++;
      }
      insertAt(this.widget, td, tIndex);
    }
  }

  private TvmTableCell<R, ?> createCell(TvmTableColumn<R, ?> column) {

    return column.createCell(isReadOnly());
  }

  @Override
  protected void setReadOnlyNative(boolean readOnly) {

    for (int i = this.cellCount - 1; i >= 0; i--) {
      this.cells[i].setReadOnly(readOnly);
    }
  }

  @Override
  public boolean isShowRowNumbers() {

    return (this.rowNumber != null);
  }

  @Override
  public void setShowRowNumbers(boolean showLineNumbers) {

    if (showLineNumbers) {
      this.rowNumber = newTableDataCell(); // th vs. td?
      this.rowNumber.setClassName(STYLE_ROW_NUMBER);
      insertFirst(this.widget, this.rowNumber);
    } else {
      this.widget.removeChild(this.rowNumber);
      this.rowNumber = null;
    }
  }

  @Override
  public boolean isSelected() {

    return this.selected;
  }

  @Override
  public void setSelected(boolean selected) {

    if (selected == this.selected) {
      return;
    }
    this.selectionInput.setChecked(selected);
    this.selected = selected;
  }

  /**
   * @param selected - {@code true} if the selected row, {@code false} otherwise.
   */
  public void setSelectedStyle(boolean selected) {

    if (selected) {
      this.widget.setClassName(STYLE_SELECTED);
    } else {
      this.widget.setClassName("");
    }
  }

  @Override
  public boolean isMultiSelection() {

    return ((this.selectionCell != null) && (this.selectionCell.getParentNode() != null));
  }

  @Override
  public void setMultiSelection(boolean multiSelection) {

    if (multiSelection) {
      if (this.selectionCell == null) {
        this.selectionInput = newInput(TvmHtmlInput.TYPE_CHECKBOX);
        this.selectionInput.addEventListener(EVENT_TYPE_CHANGE,
            e -> this.table.onSelectionChange(this, this.selectionInput.isChecked()));
        this.selectionCell = newTableDataCell(); // th vs. td?
        this.selectionCell.setClassName(STYLE_SELECTION);
      }
      insertFirst(this.widget, this.selectionCell);
    } else if (this.selectionCell != null) {
      this.widget.removeChild(this.selectionCell);
    }
  }

}
