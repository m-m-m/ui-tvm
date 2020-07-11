/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import java.util.Comparator;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.base.sort.SortOrder;
import io.github.mmm.bean.ReadableBean;
import io.github.mmm.ui.api.factory.UiWidgetFactoryDatatype;
import io.github.mmm.ui.api.factory.UiWidgetFactoryProperty;
import io.github.mmm.ui.api.widget.data.UiAbstractDataWidget.ColumnAdapter;
import io.github.mmm.ui.api.widget.data.UiColumn;
import io.github.mmm.ui.api.widget.input.UiInput;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.value.PropertyPath;
import io.github.mmm.value.TypedPropertyPath;

/**
 * Implementation of {@link UiColumn} for TeaVM.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @param <V> type of the value for the cells in this column.
 * @since 1.0.0
 */
public class TvmTableColumn<R, V> extends TvmWidgetHtmlElement<HTMLElement> implements UiColumn<R, V> {

  private final TvmAbstractDataTable<R> table;

  final ColumnAdapter<R, V> adapter;

  final TypedPropertyPath<V> property;

  private HTMLElement label;

  private String title;

  private boolean filtering;

  private boolean resizable;

  private SortOrder sortOrder;

  int tableIndex;

  /**
   * The constructor.
   *
   * @param table the owning {@link TvmAbstractDataTable}.
   * @param adapter the {@link io.github.mmm.ui.api.widget.data.UiAbstractDataWidget.ColumnAdapter}.
   */
  public TvmTableColumn(TvmAbstractDataTable<R> table, ColumnAdapter<R, V> adapter) {

    this(table, adapter, null);
  }

  /**
   * The constructor.
   *
   * @param table the owning {@link TvmAbstractDataTable}.
   * @param property the {@link PropertyPath}.
   */
  public TvmTableColumn(TvmAbstractDataTable<R> table, TypedPropertyPath<V> property) {

    this(table, null, property);
  }

  private TvmTableColumn(TvmAbstractDataTable<R> table, ColumnAdapter<R, V> adapter, TypedPropertyPath<V> property) {

    super(newTableHeaderCell());
    this.table = table;
    this.adapter = adapter;
    this.property = property;
    this.label = newLabel();
    this.widget.appendChild(this.label);
    setFiltering(true);
    setResizable(true);
  }

  /**
   * @return the owning {@link TvmAbstractDataTable}.
   */
  public TvmAbstractDataTable<R> getTable() {

    return this.table;
  }

  /**
   * @return the index of this column as currently displayed in the {@link #getTable() table}.
   */
  public int getTableIndex() {

    return this.tableIndex;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    this.label.setTextContent(title);
    this.title = title;
  }

  @Override
  public boolean isResizable() {

    return this.resizable;
  }

  @Override
  public void setResizable(boolean resizable) {

    if (resizable == this.resizable) {
      return;
    }
    if (resizable) {
      getStyles().add(STYLE_COLLAPSED);
    } else {
      getStyles().remove(STYLE_RESIZABLE);
    }
    this.resizable = resizable;
  }

  @Override
  public boolean isFiltering() {

    return this.filtering;
  }

  @Override
  public void setFiltering(boolean filtering) {

    if (filtering == this.filtering) {
      return;
    }
    this.filtering = filtering;
  }

  @Override
  public String getFilterText() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SortOrder getSortOrder() {

    return this.sortOrder;
  }

  @Override
  public Comparator<V> getSortComparator() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSortComparator(Comparator<V> sortComparator) {

    // TODO Auto-generated method stub

  }

  public TvmTableCell<R, V> createCell(boolean readOnly) {

    if (isReadOnly()) {
      readOnly = true;
    } else if (Boolean.FALSE.equals(getReadOnlyFixed())) {
      readOnly = false;
    }
    UiInput<V> editor;
    if (this.property != null) {
      editor = UiWidgetFactoryProperty.get().create(this.property);
    } else if (this.adapter != null) {
      Class<V> type = this.adapter.getType();
      editor = UiWidgetFactoryDatatype.get().create(type);
    } else {
      throw new IllegalStateException();
    }
    editor.setReadOnly(readOnly);
    return new TvmTableCellValued<>(this, editor);
  }

  /**
   * @param rowData the row data.
   * @return the cell value for this column.
   */
  @SuppressWarnings("unchecked")
  public V getValue(R rowData) {

    V value = null;
    if (this.property != null) {
      if (rowData != null) {
        value = (V) ((ReadableBean) rowData).get(this.property.getName());
      }
    } else if (this.adapter != null) {
      value = this.adapter.get(rowData);
    }
    return value;
  }

}
