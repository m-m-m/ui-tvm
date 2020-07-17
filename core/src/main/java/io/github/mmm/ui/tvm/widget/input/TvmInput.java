/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.attribute.AttributeWriteAutocomplete;
import io.github.mmm.ui.api.datatype.bitmask.BitMask;
import io.github.mmm.ui.api.event.UiValueChangeEvent;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.form.UiInputContainer;
import io.github.mmm.ui.api.widget.input.UiInput;
import io.github.mmm.ui.api.widget.panel.UiHorizontalPanel;
import io.github.mmm.ui.tvm.widget.TvmActiveWidget;
import io.github.mmm.ui.tvm.widget.TvmLabel;
import io.github.mmm.validation.Validator;

/**
 * Implementation of {@link UiInput} using TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @param <V> type of {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmInput<V, W extends HTMLElement> extends TvmActiveWidget<W>
    implements UiInput<V>, AttributeWriteAutocomplete {

  private HTMLElement hPanel;

  private HTMLElement prefixLabel;

  private HTMLElement suffixLabel;

  private String name;

  private TvmLabel nameWidget;

  private UiInputContainer<V> containerWidget;

  private Validator<? super V> validator;

  private V originalValue;

  private long modificationTimestamp;

  private String autocomplete;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmInput(W widget) {

    super(widget);
    this.validator = Validator.none();
    this.modificationTimestamp = -1;
  }

  @Override
  protected void registerHandlers() {

    super.registerHandlers();
    this.widget.addEventListener(EVENT_TYPE_CHANGE, this::onChange);
  }

  /**
   * @param event the input {@link Event}.
   */
  protected void onChange(Event event) {

    updateModificationTimestamp(false);
    fireEvent(new UiValueChangeEvent(this, getProgrammaticEventType() == UiValueChangeEvent.TYPE));
  }

  @Override
  public HTMLElement getTopWidget() {

    if (this.hPanel != null) {
      return this.hPanel;
    }
    return super.getTopWidget();
  }

  /**
   * @return the {@link HTMLElement} used as {@link #getTopWidget() top widget}. Will be lazily initialized on the first
   *         call of this method.
   */
  public HTMLElement getHPanel() {

    if (this.hPanel == null) {
      this.hPanel = newElement(UiHorizontalPanel.STYLE);
      initHPanel(this.hPanel);
    }
    return this.hPanel;
  }

  /**
   * @param panel the {@link HTMLElement} used as {@link #getTopWidget() top widget}.
   */
  protected void initHPanel(HTMLElement panel) {

    panel.appendChild(this.widget);
  }

  @Override
  public String getName() {

    if (this.nameWidget == null) {
      return this.name;
    } else {
      return this.nameWidget.getText();
    }
  }

  @Override
  public void setName(String name) {

    if (this.nameWidget == null) {
      this.name = name;
    } else {
      this.nameWidget.setText(name);
    }
  }

  @Override
  protected void setIdNative(String id) {

    super.setIdNative(id);
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
      this.nameWidget = new TvmLabel();
      if (this.name != null) {
        this.nameWidget.setText(this.name);
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
  public boolean hasContainerWidget() {

    return (this.containerWidget != null);
  }

  @Override
  public UiRegularWidget getContainerWidget() {

    if (this.containerWidget == null) {
      this.containerWidget = UiInputContainer.of(this);
    }
    return this.containerWidget;
  }

  @Override
  public String getPrefix() {

    if (this.prefixLabel == null) {
      return null;
    }
    return this.prefixLabel.getTextContent();
  }

  @Override
  public void setPrefix(String prefix) {

    if (isEmpty(prefix)) {
      if (this.prefixLabel != null) {
        this.hPanel.removeChild(this.prefixLabel);
        this.prefixLabel = null;
        getStyles().remove(STYLE_WITH_PREFIX);
      }
    } else {
      if (this.prefixLabel == null) {
        this.prefixLabel = newLabel();
        this.prefixLabel.setClassName(STYLE_PREFIX);
        insertFirst(getHPanel(), this.prefixLabel);
      }
      this.prefixLabel.setTextContent(prefix);
      getStyles().add(STYLE_WITH_PREFIX);
    }
  }

  /**
   * @return the {@link HTMLElement} for the {@link #getPrefix() prefix}.
   */
  protected HTMLElement getPrefixLabel() {

    return this.prefixLabel;
  }

  @Override
  public String getSuffix() {

    if (this.suffixLabel == null) {
      return null;
    }
    return this.suffixLabel.getTextContent();
  }

  @Override
  public void setSuffix(String suffix) {

    if (isEmpty(suffix)) {
      if (this.suffixLabel != null) {
        this.hPanel.removeChild(this.suffixLabel);
        this.suffixLabel = null;
        getStyles().remove(STYLE_WITH_SUFFIX);
      }
    } else {
      if (this.suffixLabel == null) {
        this.suffixLabel = newLabel();
        this.suffixLabel.setClassName(STYLE_SUFFIX);
        getHPanel().appendChild(this.suffixLabel);
      }
      this.suffixLabel.setTextContent(suffix);
      getStyles().add(STYLE_WITH_SUFFIX);
    }
  }

  /**
   * @return the {@link HTMLElement} for the {@link #getSuffix() suffix}.
   */
  protected HTMLElement getSuffixLabel() {

    return this.suffixLabel;
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
    setMandatory(this.validator.isMandatory());
  }

  /**
   * @param mandatory the new mandatory (required) flag.
   */
  protected void setMandatory(boolean mandatory) {

    if (this.nameWidget != null) {
      if (mandatory) {
        this.nameWidget.getStyles().add(STYLE_MANDATORY);
      } else {
        this.nameWidget.getStyles().remove(STYLE_MANDATORY);
      }
    }
  }

  @Override
  public long getModificationTimestamp() {

    return this.modificationTimestamp;
  }

  private void updateModificationTimestamp(boolean reset) {

    if (reset) {
      this.modificationTimestamp = -1;
    } else {
      this.modificationTimestamp = System.currentTimeMillis();
    }
  }

  @Override
  public void setValue(V value, boolean forUser) {

    updateModificationTimestamp(!forUser);
    if (!forUser) {
      setOriginalValue(value);
    }
    setProgrammaticEventType(UiValueChangeEvent.TYPE);
    setValueNative(value);
  }

  /**
   * @param value the new {@link #getValue() value} to set in the native widget.
   */
  protected abstract void setValueNative(V value);

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

}
