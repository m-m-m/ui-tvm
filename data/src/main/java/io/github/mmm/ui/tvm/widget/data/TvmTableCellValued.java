/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;
import io.github.mmm.ui.tvm.widget.TvmWidget;

/**
 * {@link TvmTableCell} based on a {@link UiValuedWidget} that acts both as view and editor.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmTableCellValued<R, V> extends TvmTableCell<R, V> {

  private final UiValuedWidget<V> widget;

  /**
   * The constructor.
   *
   * @param column the {@link TvmTableColumn}.
   * @param widget the valued widget.
   */
  public TvmTableCellValued(TvmTableColumn<R, V> column, UiValuedWidget<V> widget) {

    super(column);
    this.widget = widget;
    this.td.appendChild(TvmWidget.getTopNode(widget));
    if (this.column.isReadOnly()) {
      widget.setReadOnly(true);
    }
  }

  @Override
  public UiWidget getView() {

    return this.widget;
  }

  @Override
  public UiValuedWidget<V> getEditor() {

    return this.widget;
  }

  @Override
  public V getValue() {

    return this.widget.getValue();
  }

  @Override
  public void setValue(V value) {

    this.widget.setValue(value);
  }

  @Override
  public void setReadOnly(boolean readOnly) {

    if (!this.column.isReadOnly()) {
      this.widget.setReadOnly(readOnly);
    }
  }

}
