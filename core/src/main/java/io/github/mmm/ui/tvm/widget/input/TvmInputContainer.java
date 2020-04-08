/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * {@link TvmWidgetHtmlElement} for {@link TvmInput#getContainerWidget()}.
 *
 * @since 1.0.0
 */
public class TvmInputContainer extends TvmWidgetHtmlElement<HTMLElement> implements UiRegularWidget {

  private final TvmInput<?, ?> input;

  /**
   * The constructor.
   *
   * @param input the {@link TvmInput}.
   */
  public TvmInputContainer(TvmInput<?, ?> input) {

    super(input.getContext(), newElement("ui-input"));
    this.input = input;
    this.widget.appendChild(input.getNameWidget().getWidget());
    this.widget.appendChild(input.getTopWidget());
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.input.setEnabled(enabled);
  }

}
