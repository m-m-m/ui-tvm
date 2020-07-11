/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.attribute.AttributeWriteReadOnly;
import io.github.mmm.ui.api.attribute.AttributeWriteValue;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;
import io.github.mmm.ui.tvm.widget.TvmWidget;

/**
 * Container for a table cell.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @param <V> type of the value.
 * @since 1.0.0
 */
public abstract class TvmTableCell<R, V> implements AttributeWriteValue<V>, AttributeWriteReadOnly {

  final TvmTableColumn<R, V> column;

  final HTMLElement td;

  /**
   * The constructor.
   *
   * @param column the {@link TvmTableColumn}.
   */
  public TvmTableCell(TvmTableColumn<R, V> column) {

    super();
    this.td = TvmWidget.newTableDataCell();
    this.column = column;
  }

  /**
   * @return the td element for the table cell.
   */
  public HTMLElement getTableDataCell() {

    return this.td;
  }

  public void setRowValue(R rowValue) {

    V value = this.column.getValue(rowValue);
    setValue(value);
  }

  public abstract UiWidget getView();

  public abstract UiValuedWidget<V> getEditor();

}
