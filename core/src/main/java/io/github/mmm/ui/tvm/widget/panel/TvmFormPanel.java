/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.tvm.widget.composite.TvmValuedComposite;
import io.github.mmm.ui.widget.UiWidget;
import io.github.mmm.ui.widget.input.UiAbstractInput;
import io.github.mmm.ui.widget.input.UiInput;
import io.github.mmm.ui.widget.panel.UiFormPanel;

/**
 * Implementation of {@link UiFormPanel} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmFormPanel<V> extends TvmValuedComposite<HTMLElement, UiAbstractInput<?>, V> implements UiFormPanel<V> {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmFormPanel(UiContext context) {

    super(context, newForm());
  }

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmFormPanel(UiContext context, HTMLElement widget) {

    super(context, widget);
  }

  @Override
  protected void addChildWidget(UiAbstractInput<?> child, int index) {

    UiWidget childWidget = child;
    if (child instanceof UiInput) {
      childWidget = ((UiInput<?>) child).getContainerWidget();
    }
    insertAt(this.widget, getTopNode(childWidget), index);
  }

  @Override
  protected void removeChildWidget(UiAbstractInput<?> child) {

    UiWidget childWidget = child;
    if (child instanceof UiInput) {
      childWidget = ((UiInput<?>) child).getContainerWidget();
    }
    this.widget.removeChild(getTopNode(childWidget));
  }

}
