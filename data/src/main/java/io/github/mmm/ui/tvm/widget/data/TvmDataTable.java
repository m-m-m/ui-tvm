/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import java.util.Collection;
import java.util.List;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.ui.api.widget.data.UiColumn;
import io.github.mmm.ui.api.widget.data.UiDataTable;
import io.github.mmm.validation.Validator;
import io.github.mmm.value.PropertyPath;

/**
 * Implementation of {@link UiDataTable} for TeaVM.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public class TvmDataTable<R> extends TvmAbstractDataWidget<R> implements UiDataTable<R> {

  /**
   * The constructor.
   */
  public TvmDataTable() {

    super();
  }

  @Override
  public int getColumnCount() {

    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public <V> UiColumn<R, V> createColumn(PropertyPath<V> property) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <V> UiColumn<R, V> createColumn(String title, ColumnAdapter<R, V> adapter) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UiColumn<R, ?> getColumn(int index) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addColumn(UiColumn<R, ?> column) {

    // TODO Auto-generated method stub

  }

  @SuppressWarnings("unchecked")
  @Override
  public void sort(SortOrder order, UiColumn<R, ?>... columns) {

    // TODO Auto-generated method stub

  }

  @Override
  public void setFilterHandler(FilterHandler<R> filterHandler) {

    // TODO Auto-generated method stub

  }

  @Override
  public boolean isMultiSelection() {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setRowTemplate(R rowTemplate) {

    // TODO Auto-generated method stub

  }

  @Override
  public boolean isShowRowNumbers() {

    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setShowRowNumbers(boolean showRowNumbers) {

    // TODO Auto-generated method stub

  }

  @Override
  public void setSelection(R selection) {

    // TODO Auto-generated method stub

  }

  @Override
  public List<R> getValueOrThrow() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setValue(List<R> value, boolean forUser) {

    // TODO Auto-generated method stub

  }

  @Override
  public List<R> getOriginalValue() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setOriginalValue(List<R> originalValue) {

    // TODO Auto-generated method stub

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

  @Override
  public void setSelections(Collection<R> selection) {

    // TODO Auto-generated method stub

  }

  @Override
  public void setMultiSelection(boolean multiSelection) {

    // TODO Auto-generated method stub

  }

  @Override
  public R getSelection() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<R> getSelections() {

    // TODO Auto-generated method stub
    return null;
  }

}
