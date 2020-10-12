/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.form;

import io.github.mmm.ui.api.widget.form.UiHorizontalInput;
import io.github.mmm.ui.api.widget.panel.UiHorizontalPanel;

/**
 * Implementation of {@link UiHorizontalInput} for TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmHorizontalInput<V> extends TvmCompositeInput<V> implements UiHorizontalInput<V> {

  /**
   * The constructor.
   */
  public TvmHorizontalInput() {

    super(newElement(UiHorizontalPanel.STYLE));
  }

}
