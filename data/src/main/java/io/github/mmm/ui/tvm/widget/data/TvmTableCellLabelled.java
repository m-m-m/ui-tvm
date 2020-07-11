/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import io.github.mmm.ui.api.widget.UiLabel;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.value.UiValuedWidget;
import io.github.mmm.ui.tvm.widget.TvmWidget;

/**
 * {@link TvmTableCell} that uses different widgets for view and editor.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmTableCellLabelled<R, V> extends TvmTableCell<R, V> {

  private UiLabel view;

  private UiValuedWidget<V> editor;

  private V value;

  private boolean readOnly;

  /**
   * The constructor.
   *
   * @param column the {@link TvmTableColumn}.
   */
  public TvmTableCellLabelled(TvmTableColumn<R, V> column) {

    super(column);
    this.td.appendChild(TvmWidget.getTopNode(getView()));
    this.readOnly = true;
  }

  /**
   * The constructor.
   *
   * @param column the {@link TvmTableColumn}.
   * @param editor the {@link #getEditor() editor widget}.
   */
  public TvmTableCellLabelled(TvmTableColumn<R, V> column, UiValuedWidget<V> editor) {

    super(column);
    this.editor = editor;
    this.td.appendChild(TvmWidget.getTopNode(this.editor));
    this.readOnly = false;
  }

  @Override
  public UiWidget getView() {

    if (this.view == null) {
      this.view = UiLabel.of(null);
    }
    return this.view;
  }

  @Override
  public UiValuedWidget<V> getEditor() {

    return this.editor;
  }

  @Override
  public V getValue() {

    if (this.readOnly) {
      return this.value;
    }
    return this.editor.getValue();
  }

  @Override
  public void setValue(V value) {

    if (this.readOnly) {
      String text = "";
      if (value != null) {
        text = text.toString();
      }
      this.view.setText(text);
    } else {
      this.editor.setValue(value);
    }
    this.value = value;
  }

  @Override
  public void setReadOnly(boolean readOnly) {

    if ((readOnly == this.readOnly) || this.column.isReadOnly()) {
      return;
    }
    this.td.removeChild(this.td.getFirstChild());
    UiWidget widget;
    if (readOnly) {
      widget = this.view;
    } else {
      widget = this.editor;
    }
    this.td.appendChild(TvmWidget.getTopNode(widget));
    this.readOnly = readOnly;
  }

}
