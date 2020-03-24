/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.ui.tvm.widget.composite.TvmValuedComposite;
import io.github.mmm.ui.widget.UiLabel;
import io.github.mmm.ui.widget.input.UiInput;
import io.github.mmm.ui.widget.panel.UiFormGroup;

/**
 * Implementation of {@link UiFormGroup} using TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public class TvmFormGroup<V> extends TvmValuedComposite<HTMLElement, UiInput<?>, V> implements UiFormGroup<V> {

  private final Legend legend;

  private boolean collapsed;

  private boolean collapsible;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmFormGroup(UiContext context) {

    this(context, newFieldSet());
  }

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmFormGroup(UiContext context, HTMLElement widget) {

    super(context, widget);
    this.legend = new Legend(context);
    this.widget.appendChild(this.legend.getTopWidget());
    setCollapsible(true);
  }

  @Override
  protected void addChildWidget(UiInput<?> child, int index) {

    if (index >= 0) {
      index++;
    }
    insertAt(this.widget, getTopNode(child.getContainerWidget()), index);
  }

  @Override
  protected void removeChildWidget(UiInput<?> child) {

    this.widget.removeChild(getTopNode(child.getContainerWidget()));
  }

  @Override
  public String getName() {

    return this.legend.getText();
  }

  @Override
  public void setName(String name) {

    this.legend.setText(name);
  }

  @Override
  protected void doSetValidationFailure(String error) {

    // super.doSetValidationFailure(error);
    this.legend.errorWidget.setHidden(error == null);
    this.legend.errorWidget.setTitle(error);
  }

  @Override
  public boolean isCollapsed() {

    return this.collapsed;
  }

  @Override
  public void setCollapsed(boolean collapsed) {

    if (collapsed == this.collapsed) {
      return;
    }
    if (collapsed) {
      getStyles().add(STYLE_COLLAPSED);
      this.legend.expandIcon.setClassName(CLASS_EXPAND);
    } else {
      getStyles().remove(STYLE_COLLAPSED);
      this.legend.expandIcon.setClassName(CLASS_COLLAPSE);
    }
    this.collapsed = collapsed;
  }

  @Override
  public boolean isCollapsible() {

    return this.collapsible;
  }

  @Override
  public void setCollapsible(boolean collapsible) {

    if (collapsible == this.collapsible) {
      return;
    }
    if (collapsible) {
      getStyles().add(STYLE_COLLAPSIBLE);
    } else {
      getStyles().remove(STYLE_COLLAPSIBLE);
    }
    this.legend.expandIcon.setHidden(!collapsible);
    this.collapsible = collapsible;
  }

  private class Legend extends TvmWidgetHtmlElement<HTMLElement> implements UiLabel {

    private final HTMLElement expandIcon;

    private final HTMLElement topWidget;

    private final HTMLElement errorWidget;

    private String label;

    /**
     * The constructor.
     *
     * @param context the {@link #getContext() context}.
     */
    public Legend(UiContext context) {

      super(context, newLabel());
      this.topWidget = newLegend();
      this.expandIcon = newIcon(CLASS_COLLAPSE);
      this.topWidget.appendChild(this.expandIcon);
      this.topWidget.appendChild(this.widget);
      this.errorWidget = newIcon(CLASS_ERROR);
      this.errorWidget.setHidden(true);
      this.topWidget.appendChild(this.errorWidget);
      this.topWidget.addEventListener(EVENT_TYPE_CLICK, this::onClick);
    }

    @Override
    protected void onClick(Event event) {

      if (TvmFormGroup.this.collapsible) {
        setCollapsed(!TvmFormGroup.this.collapsed);
      }
      super.onClick(event);
    }

    @Override
    public HTMLElement getTopWidget() {

      return this.topWidget;
    }

    @Override
    public String getText() {

      return this.label;
    }

    @Override
    public void setText(String label) {

      setTextContent(label);
      this.label = label;
    }

    @Override
    protected void setEnabledNative(boolean enabled) {

    }
  }

}
