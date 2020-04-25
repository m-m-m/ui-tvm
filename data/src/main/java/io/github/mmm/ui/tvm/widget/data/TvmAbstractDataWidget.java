/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.data;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.data.UiAbstractDataWidget;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiAbstractDataWidget} for TeaVM.
 *
 * @param <R> type of the data for the rows displayed by this widget. Typically a {@link io.github.mmm.bean.Bean}.
 * @since 1.0.0
 */
public abstract class TvmAbstractDataWidget<R> extends TvmWidgetHtmlElement<HTMLElement>
    implements UiAbstractDataWidget<R> {

  /**
   * The constructor.
   */
  public TvmAbstractDataWidget() {

    super(newTable());
  }

}
