/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.data.UiDataTable;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link UiDataTable} for TeaVM.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public class TvmDataTable<R> extends TvmAbstractDataTable<R> implements UiDataTable<R> {

  private final List<TvmTableRow<R>> rows;

  private List<R> originalValue;

  /**
   * The constructor.
   */
  public TvmDataTable() {

    super();
    this.rows = new ArrayList<>();
  }

  @Override
  protected void addColumnToRows(TvmTableColumn<R, ?> tvmColumn, int colIndex) {

    for (TvmTableRow<R> row : this.rows) {
      row.addColumn(tvmColumn, colIndex);
    }
  }

  @Override
  protected TvmTableRow<R> findRow(R value) {

    for (TvmTableRow<R> row : this.rows) {
      if (value == row.getValue()) {
        return row;
      }
    }
    return null;
  }

  @Override
  protected void setShowRowNumbersNative(boolean showRowNumbers) {

    for (TvmTableRow<R> row : this.rows) {
      row.setShowRowNumbers(showRowNumbers);
    }
  }

  @Override
  protected void setMultiSelectionNative(boolean multiSelection) {

    super.setMultiSelectionNative(multiSelection);
    for (TvmTableRow<R> row : this.rows) {
      row.setMultiSelection(multiSelection);
    }
  }

  @Override
  protected void onSelectAll(boolean selectAll) {

    for (TvmTableRow<R> row : this.rows) {
      row.setSelected(selectAll);
    }
  }

  @Override
  public List<R> getValueOrThrow() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setValue(List<R> value, boolean forUser) {

    int rowCount = 0;
    if (value != null) {
      rowCount = value.size();
    }
    if (!forUser) {
      setOriginalValue(value);
    }
    ensureRowCount(rowCount);
    boolean showRowNumbers = isShowRowNumbers();
    boolean multiSelection = isMultiSelection();
    for (int i = 0; i < rowCount; i++) {
      R rowData = value.get(i);
      TvmTableRow<R> row = this.rows.get(i);
      if (showRowNumbers) {
        row.setShowRowNumbers(true);
      }
      if (multiSelection) {
        row.setMultiSelection(true);
      }
      for (TvmTableColumn<R, ?> column : this.columns) {
        row.addColumn(column, -1);
      }
      row.setValue(rowData);
    }
  }

  private void ensureRowCount(int rowCount) {

    int rowSize = this.rows.size();
    while (rowSize < rowCount) {
      TvmTableRow<R> row = new TvmTableRow<>(this);
      this.tbody.appendChild(row.getTopWidget());
      this.rows.add(row);
      rowSize++;
    }
    for (int i = rowSize - 1; i >= rowCount; i--) {
      HTMLElement rowWidget = this.rows.get(i).getTopWidget();
      this.tbody.removeChild(rowWidget);
    }
  }

  @Override
  public List<R> getOriginalValue() {

    return this.originalValue;
  }

  @Override
  public void setOriginalValue(List<R> originalValue) {

    this.originalValue = originalValue;
  }

  @Override
  public Validator<? super List<R>> getValidator() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setValidator(Validator<? super List<R>> validator) {

    // TODO Auto-generated method stub

  }

}
