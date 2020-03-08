/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.base.range.WritableRange;
import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.spi.range.NumericRange;
import io.github.mmm.ui.widget.input.UiSlider;

/**
 * Implementation of {@link UiSlider} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}. Typically {@link String}.
 * @since 1.0.0
 */
public abstract class TvmSlider<V extends Number> extends TvmHtmlInput<V> implements UiSlider<V> {

  private final NumericRange<V> range;

  private final HTMLElement topWidget;

  private final HTMLElement output;

  private HTMLInputElement input;

  private boolean textVisible;

  private boolean textEditable;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmSlider(UiContext context) {

    super(context, "range");
    this.topWidget = newElement("ui-slider");
    this.topWidget.appendChild(this.widget);
    this.output = newOutput();
    this.topWidget.appendChild(this.output);
    this.widget.addEventListener(EVENT_TYPE_CHANGE, this::onChange);
    this.range = new NumericRange<>(getNumberType());
  }

  /**
   * @return the {@link NumberType} for the underlying {@link Number}.
   */
  protected abstract NumberType<V> getNumberType();

  @Override
  public WritableRange<V> getRange() {

    return this.range;
  }

  private void onChange(Event event) {

    String value = this.widget.getValue();
    if (this.input != null) {
      this.input.setValue(value);
    }
    setTextContent(this.output, value);
  }

  @Override
  public V getValueOrThrow() {

    String value = this.widget.getValue();
    return getNumberType().valueOf(value);
  }

  @Override
  protected void setValueNative(V value) {

    String text = "";
    if (value != null) {
      text = value.toString();
    }
    this.widget.setValue(text);
  }

  @Override
  public void setId(String id) {

    super.setId(id);
  }

  @Override
  public boolean isTextVisible() {

    return this.textVisible;
  }

  @Override
  public void setTextVisible(boolean textVisible) {

    if (textVisible == this.textVisible) {
      return;
    }
    if (this.textEditable) {
      this.input.setHidden(!textVisible);
    } else {
      this.output.setHidden(!textVisible);
    }
    this.textVisible = textVisible;
  }

  @Override
  public boolean isTextEditable() {

    return this.textEditable;
  }

  @Override
  public void setTextEditable(boolean textEditable) {

    if (textEditable == this.textEditable) {
      return;
    }
    if (textEditable && (this.input == null)) {
      this.input = newInput("number");
      this.topWidget.appendChild(this.input);
      if (!this.textVisible) {
        this.input.setHidden(true);
      }
    }
    if (this.textVisible) {
      this.output.setHidden(textEditable);
      if (this.input != null) {
        this.input.setHidden(!textEditable);
        if (textEditable) {
          this.input.setTabIndex(0);
          this.widget.setTabIndex(-1);
        } else {
          this.input.setTabIndex(-1);
          this.widget.setTabIndex(0);
        }
      }
    }
    this.textEditable = textEditable;
  }

}
