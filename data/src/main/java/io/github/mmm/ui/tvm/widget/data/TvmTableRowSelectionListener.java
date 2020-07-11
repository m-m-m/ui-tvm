/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

/**
 * Interface for selection change listener on {@link TvmTableRow}.
 *
 * @param <R> type of the row data. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public interface TvmTableRowSelectionListener<R> {

  /**
   * @param row the {@link TvmTableRow} that changed its selection.
   * @param selected {@code true} if selected, {@code false} otherwise.
   */
  void onSelectionChange(TvmTableRow<R> row, boolean selected);

}
