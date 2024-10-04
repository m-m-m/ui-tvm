/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.ui.api.UiLocalizer;
import io.github.mmm.ui.api.event.UiSelectionEvent;
import io.github.mmm.ui.api.widget.data.UiAbstractDataTable;
import io.github.mmm.ui.api.widget.data.UiColumn;
import io.github.mmm.ui.api.widget.data.UiDataTable;
import io.github.mmm.ui.tvm.widget.input.TvmHtmlInput;
import io.github.mmm.value.PropertyPath;

/**
 * Implementation of {@link UiDataTable} for TeaVM.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public abstract class TvmAbstractDataTable<R> extends TvmAbstractDataWidget<R>
    implements UiAbstractDataTable<R>, TvmTableRowSelectionListener<R> {

  protected final List<TvmTableColumn<R, ?>> columns;

  private TvmTableRow<R> selectedRow;

  private final List<TvmTableRow<R>> selectedRows;

  private HTMLElement rowNumberHeader;

  private HTMLElement selectionHeader;

  private HTMLInputElement selectionInput;

  private boolean showRowNumbers;

  private boolean duringSelectionChange;

  private R rowTemplate;

  /**
   * The constructor.
   */
  public TvmAbstractDataTable() {

    super();
    this.columns = new ArrayList<>();
    this.selectedRows = new ArrayList<>();
  }

  @Override
  public int getColumnCount() {

    return this.columns.size();
  }

  @Override
  public <V> UiColumn<R, V> createColumn(PropertyPath<V> property) {

    TvmTableColumn<R, V> column = new TvmTableColumn<>(this, property);
    String title = UiLocalizer.get().localize(property.getName());
    column.setTitle(title);
    return column;
  }

  @Override
  public <V> UiColumn<R, V> createColumn(String title, ColumnAdapter<R, V> adapter) {

    TvmTableColumn<R, V> column = new TvmTableColumn<>(this, adapter);
    column.setTitle(title);
    return column;
  }

  @Override
  public UiColumn<R, ?> getColumn(int index) {

    if ((index >= 0) && (index < this.columns.size())) {
      return this.columns.get(index);
    }
    return null;
  }

  @Override
  public void addColumn(UiColumn<R, ?> column, int index) {

    int colIndex = index;
    if (colIndex == -1) {
      colIndex = this.columns.size();
    }
    TvmTableColumn<R, ?> tvmColumn = (TvmTableColumn<R, ?>) column;
    if (tvmColumn.getTable() != this) {
      throw new IllegalStateException("Column (" + column.getTitle() + ") does not belong to this table.");
    }
    this.headerRow.appendChild(tvmColumn.getWidget());
    this.columns.add(tvmColumn);
    addColumnToRows(tvmColumn, colIndex);
  }

  /**
   * @param tvmColumn the {@link TvmTableColumn} to add.
   * @param index the physical index where to insert the column.
   */
  protected abstract void addColumnToRows(TvmTableColumn<R, ?> tvmColumn, int index);

  @SuppressWarnings("unchecked")
  @Override
  public void sort(SortOrder order, UiColumn<R, ?>... cols) {

    // TODO Auto-generated method stub

  }

  @Override
  public void setFilterHandler(FilterHandler<R> filterHandler) {

    // TODO Auto-generated method stub

  }

  /**
   * @return rowTemplate
   */
  public R getRowTemplate() {

    return this.rowTemplate;
  }

  @Override
  public void setRowTemplate(R rowTemplate) {

    this.rowTemplate = rowTemplate;
  }

  @Override
  public boolean isShowRowNumbers() {

    return this.showRowNumbers;
  }

  @Override
  public void setShowRowNumbers(boolean showRowNumbers) {

    if (showRowNumbers == this.showRowNumbers) {
      return;
    }
    if (showRowNumbers) {
      if (this.rowNumberHeader == null) {
        this.rowNumberHeader = newTableHeaderCell();
        this.rowNumberHeader.setTextContent(ROW_NUMBER_HEADER_TITLE);
      }
      insertFirst(this.headerRow, this.rowNumberHeader);
    } else {
      this.headerRow.removeChild(this.rowNumberHeader);
    }
    setShowRowNumbersNative(showRowNumbers);
    this.showRowNumbers = showRowNumbers;
  }

  /**
   * @param showRowNumbers
   */
  protected abstract void setShowRowNumbersNative(boolean showRowNumbers);

  @Override
  public void onSelectionChange(TvmTableRow<R> row, boolean selected) {

    if (this.duringSelectionChange) {
      return;
    }
    if (selected) {
      // if (this.selectedRow != null) {
      // this.selectedRow.setSelectedStyle(false);
      // }
      this.selectedRow = row;
      this.selectedRows.add(row);
    } else {
      boolean removed = this.selectedRows.remove(row);
      if (!removed) {
        return;
      }
      int size = this.selectedRows.size();
      if (size > 0) {
        this.selectedRow = this.selectedRows.get(size - 1);
        // TODO update selection style
      } else {
        this.selectedRow = null;
      }
    }
    fireEvent(new UiSelectionEvent(this, false));
  }

  @Override
  public R getSelection() {

    if (this.selectedRow != null) {
      return this.selectedRow.getValue();
    }
    return null;
  }

  @Override
  public void setSelection(R selection) {

    TvmTableRow<R> row = null;
    if (selection != null) {
      row = findRow(selection);
    }
    if (row == this.selectedRow) {
      return;
    }
    try {
      this.duringSelectionChange = true;
      if (this.selectedRow != null) {
        // this.selectedRow.setSelected(false);
        this.selectedRow.setSelectedStyle(false);
        // this.selectedRows.remove(this.selectedRow);
      }
      this.selectedRow = row;
      if (this.selectedRow != null) {
        // this.selectedRow.setSelected(true);
        this.selectedRow.setSelectedStyle(true);
        // this.selectedRows.add(this.selectedRow);
      }
    } finally {
      this.duringSelectionChange = false;
    }
    fireEvent(new UiSelectionEvent(this, true));
  }

  @Override
  public List<R> getSelections() {

    // return this.selectedRows.stream().map(row -> row.getValue()).collect(Collectors.toList());
    List<R> selections = new ArrayList<>(this.selectedRows.size());
    for (TvmTableRow<R> row : this.selectedRows) {
      selections.add(row.getValue());
    }
    return selections;
  }

  @Override
  public void setSelections(Collection<R> selections) {

    if (selections.isEmpty() && this.selectedRows.isEmpty()) {
      return;
    }
    this.selectedRows.clear();
    try {
      this.duringSelectionChange = true;
      for (R selection : selections) {
        TvmTableRow<R> row = findRow(selection); // TODO: inefficient if many rows and many selections
        if (row != null) {
          this.selectedRows.add(row);
          row.setSelected(true);
        }
      }
    } finally {
      this.duringSelectionChange = false;
    }
    fireEvent(new UiSelectionEvent(this, true));
  }

  @Override
  protected void setMultiSelectionNative(boolean multiSelection) {

    if (multiSelection) {
      if (this.selectionHeader == null) {
        this.selectionHeader = newTableHeaderCell();
        this.selectionInput = newInput(TvmHtmlInput.TYPE_CHECKBOX);
        this.selectionInput.addEventListener(EVENT_TYPE_CHANGE, e -> onSelectAll(this.selectionInput.isChecked()));
      }
      insertFirst(this.headerRow, this.selectionHeader);
    }
    super.setMultiSelectionNative(multiSelection);
  }

  protected abstract void onSelectAll(boolean selectAll);

  /**
   * @param value the {@link TvmTableRow#getValue() row value}.
   * @return the {@link TvmTableRow} holding the given {@code value}.
   */
  protected abstract TvmTableRow<R> findRow(R value);

}
