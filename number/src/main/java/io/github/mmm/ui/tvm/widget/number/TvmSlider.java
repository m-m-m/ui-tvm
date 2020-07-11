/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.number;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import io.github.mmm.base.number.NumberType;
import io.github.mmm.base.range.WritableRange;
import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.number.UiSlider;
import io.github.mmm.ui.spi.range.NumericRange;
import io.github.mmm.ui.tvm.widget.input.TvmHtmlInput;

/**
 * Implementation of {@link UiSlider} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}. Typically {@link String}.
 * @since 1.0.0
 */
public abstract class TvmSlider<V extends Number> extends TvmHtmlInput<V> implements UiSlider<V> {

  private static int counter = 1;

  private final Range<V> range;

  private final HTMLElement topWidget;

  private final HTMLElement output;

  private HTMLInputElement input;

  private HTMLElement datalist;

  private boolean textVisible;

  private boolean textEditable;

  /**
   * The constructor.
   */
  public TvmSlider() {

    super("range");
    this.topWidget = newElement("ui-slider");
    this.topWidget.appendChild(this.widget);
    this.output = newOutput();
    this.topWidget.appendChild(this.output);
    this.datalist = newDatalist();
    for (int i = 0; i <= 10; i++) {
      newOption(this.datalist, Integer.toString(i * 10));
    }
    this.topWidget.appendChild(this.datalist);
    this.widget.setAttribute(ATR_MIN, "0");
    this.widget.setAttribute(ATR_MAX, "100");
    this.widget.addEventListener(EVENT_TYPE_INPUT, this::onInput);
    this.range = new Range<>(getNumberType());
  }

  @Override
  protected void setParent(UiComposite<?> parent) {

    if ((parent != null) && (getId() == null)) {
      setId("slider_" + counter++);
    }
    super.setParent(parent);
  }

  @Override
  public HTMLElement getTopWidget() {

    return this.topWidget;
  }

  /**
   * @return the {@link NumberType} for the underlying {@link Number}.
   */
  protected abstract NumberType<V> getNumberType();

  @Override
  public WritableRange<V> getRange() {

    return this.range;
  }

  private void onInput(Event event) {

    String value = this.widget.getValue();
    if (this.input != null) {
      this.input.setValue(value);
    }
    this.output.setTextContent(value);
  }

  @Override
  public V getValueOrThrow() {

    String value = this.widget.getValue();
    double d = Double.parseDouble(value) / 100;
    return this.range.fromFactor(d);
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
  protected void setIdNative(String id) {

    super.setIdNative(id);
    this.output.setAttribute(ATR_FOR, id);
    String listId = id + "_list";
    this.widget.setAttribute(ATR_LIST, listId);
    this.datalist.setId(listId);
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

  private class Range<N extends Number> extends NumericRange<N> {

    public Range(NumberType<N> type) {

      super(type);
    }

    @Override
    protected void onValueChange() {

      super.onValueChange();
    }

  }
}
