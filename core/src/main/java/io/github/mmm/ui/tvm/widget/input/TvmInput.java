/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.bitmask.BitMask;
import io.github.mmm.ui.event.UiValueChangeEvent;
import io.github.mmm.ui.tvm.widget.TvmActiveWidget;
import io.github.mmm.ui.tvm.widget.TvmLabel;
import io.github.mmm.ui.widget.attribute.UiWidgetWithAutocomplete;
import io.github.mmm.ui.widget.input.UiInput;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link UiInput} using TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmInput<V, W extends HTMLElement> extends TvmActiveWidget<W>
    implements UiInput<V>, UiWidgetWithAutocomplete {

  private String name;

  private TvmLabel nameWidget;

  private Validator<? super V> validator;

  private V originalValue;

  private boolean modified;

  private String autocomplete;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmInput(UiContext context, W widget) {

    super(context, widget);
    this.validator = Validator.none();
  }

  @Override
  protected void registerHandlers() {

    super.registerHandlers();
    this.widget.addEventListener(EVENT_TYPE_INPUT, this::onInput);
  }

  /**
   * @param event the input {@link Event}.
   */
  protected void onInput(Event event) {

    fireEvent(new UiValueChangeEvent(this, getProgrammaticEventType() == UiValueChangeEvent.TYPE));
  }

  @Override
  public String getName() {

    if (this.nameWidget == null) {
      return this.name;
    } else {
      return this.nameWidget.getLabel();
    }
  }

  @Override
  public void setName(String name) {

    if (this.nameWidget == null) {
      this.name = name;
    } else {
      this.nameWidget.setLabel(name);
    }
  }

  @Override
  public void setId(String id) {

    super.setId(id);
    if (this.nameWidget != null) {
      this.nameWidget.setFor(id);
    }
  }

  @Override
  public boolean hasNameWidget() {

    return (this.nameWidget != null);
  }

  @Override
  public TvmLabel getNameWidget() {

    if (this.nameWidget == null) {
      this.nameWidget = new TvmLabel(this.context);
      if (this.name != null) {
        this.nameWidget.setLabel(this.name);
      }
      doSetVisibleState(this.nameWidget, doGetVisibleState(this));
      String id = getId();
      if (id != null) {
        this.nameWidget.setFor(id);
        this.nameWidget.setId(id + "_label");
      }
      if (this.validator.isMandatory()) {
        this.nameWidget.getStyles().add("mandatory");
      }
    }
    return this.nameWidget;
  }

  @Override
  public void setVisible(boolean visible, BitMask flagMode) {

    super.setVisible(visible, flagMode);
    if (this.nameWidget != null) {
      this.nameWidget.setVisible(visible, flagMode);
    }
  }

  @Override
  public V getOriginalValue() {

    return this.originalValue;
  }

  @Override
  public void setOriginalValue(V originalValue) {

    this.originalValue = originalValue;
  }

  @Override
  protected void doSetValidationFailure(String error) {

    if (error == null) {
      setCustomValidity(this.widget, "");
    } else {
      setCustomValidity(this.widget, error);
      reportValidity(this.widget);
    }
  }

  @Override
  public Validator<? super V> getValidator() {

    return this.validator;
  }

  @Override
  public void setValidator(Validator<? super V> validator) {

    if (validator == null) {
      this.validator = Validator.none();
    } else {
      this.validator = validator;
    }
    if (this.nameWidget != null) {
      if (this.validator.isMandatory()) {
        this.nameWidget.getStyles().add("mandatory");
      } else {
        this.nameWidget.getStyles().remove("mandatory");
      }
    }
  }

  @Override
  public boolean isModified() {

    return this.modified;
  }

  @Override
  public void setValue(V value) {

    this.modified = false;
    setOriginalValue(value);
    setProgrammaticEventType(UiValueChangeEvent.TYPE);
    setValueNative(value);
  }

  @Override
  public void setValueForUser(V value) {

    this.modified = true;
    setProgrammaticEventType(UiValueChangeEvent.TYPE);
    setValueNative(value);
  }

  /**
   * @param value the new {@link #getValue() value} to set in the native widget.
   */
  protected abstract void setValueNative(V value);

  @Override
  protected void onFocusGain(Event event) {

    super.onFocusGain(event);
    if (!isValid()) {
      // TODO update tvm version
      reportValidity(this.widget);
    }
  }

  @Override
  protected void onFocusLoss(Event event) {

    validate();
    super.onFocusLoss(event);
  }

  @Override
  public String getAutocomplete() {

    return this.autocomplete;
  }

  @Override
  public void setAutocomplete(String autocomplete) {

    this.autocomplete = autocomplete;
    this.widget.setAttribute("autocomplete", autocomplete);
  }

  @JSBody(params = { EVENT_TYPE_INPUT, "value" }, script = "input.setCustomValidity(value);")
  private static native void setCustomValidity(HTMLElement input, String value);

  @JSBody(params = { EVENT_TYPE_INPUT }, script = "input.reportValidity();")
  private static native void reportValidity(HTMLElement input);
}
