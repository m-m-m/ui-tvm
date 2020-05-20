/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.form;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.form.UiFormPanel;
import io.github.mmm.ui.api.widget.input.UiAbstractInput;
import io.github.mmm.ui.api.widget.input.UiInput;
import io.github.mmm.ui.tvm.widget.composite.TvmValuedComposite;

/**
 * Implementation of {@link UiFormPanel} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmFormPanel<V> extends TvmValuedComposite<HTMLElement, UiAbstractInput<?>, V> implements UiFormPanel<V> {

  /**
   * The constructor.
   */
  public TvmFormPanel() {

    super(newForm());
    getStyles().add(STYLE);
  }

  /**
   * The constructor.
   * 
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmFormPanel(HTMLElement widget) {

    super(widget);
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
