/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.form;

import java.util.ArrayList;
import java.util.List;

import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.xml.Node;

import io.github.mmm.ui.api.binding.UiValueBinding;
import io.github.mmm.ui.api.datatype.UiVisibleFlags;
import io.github.mmm.ui.api.event.UiValueChangeEvent;
import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.form.UiCompositeInput;
import io.github.mmm.ui.tvm.widget.input.TvmInput;

/**
 * Implementation of {@link UiCompositeInput} for TeaVM.
 *
 * @param <V> type of the {@link #getValue() value}.
 * @since 1.0.0
 */
public abstract class TvmCompositeInput<V> extends TvmInput<V, HTMLElement> implements UiCompositeInput<V> {

  /** @see #getChild(int) */
  protected final List<UiRegularWidget> children;

  private UiValueBinding<V> binding;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmCompositeInput(HTMLElement widget) {

    super(widget);
    this.children = new ArrayList<>();
  }

  @Override
  public void initBinding(UiValueBinding<V> newBinding) {

    if (this.binding != null) {
      throw new IllegalStateException();
    }
    this.binding = newBinding;
    this.binding.setWidget(this);
  }

  @Override
  public int getChildCount() {

    return this.children.size();
  }

  @Override
  public UiRegularWidget getChild(int index) {

    if ((index < 0) || (index >= this.children.size())) {
      return null;
    }
    return this.children.get(index);
  }

  @Override
  public int getChildIndex(UiRegularWidget child) {

    if (child == null) {
      return -1;
    }
    return this.children.indexOf(child);
  }

  @Override
  public UiRegularWidget removeChild(int index) {

    if (index == -1) {
      index = this.children.size() - 1;
    }
    UiRegularWidget child = this.children.remove(index);
    removeChildWidget(child, index);
    setParent(child, null);
    return child;
  }

  /**
   * @param child the child to remove.
   * @param index the index of the child to remove.
   * @see #removeChild(UiWidget)
   * @see #removeChild(int)
   */
  protected void removeChildWidget(UiRegularWidget child, int index) {

    this.widget.removeChild(getTopNode(child));
  }

  @Override
  public void addChild(UiRegularWidget child, int index) {

    setParent(child, this);
    addChildWidget(child, index);
    if (index == -1) {
      this.children.add(child);
    } else {
      this.children.add(index, child);
    }
  }

  /**
   * @param child the child to add.
   * @param index the index where to insert the child.
   * @see #addChild(UiRegularWidget, int)
   */
  protected void addChildWidget(UiRegularWidget child, int index) {

    Node node = getTopNode(child);
    int i = index;
    if (i == -1) {
      if (getSuffix() != null) {
        i = this.children.size() - 1;
      }
    }
    if (i >= 0) {
      if (getPrefix() != null) {
        i++;
      }
    }
    insertAt(this.widget, node, i);
  }

  @Override
  public V getValueOrThrow() {

    if (this.binding != null) {
      return this.binding.getValue();
    }
    return null;
  }

  @Override
  public void setValue(V value, boolean forUser) {

    if (!forUser) {
      setOriginalValue(value);
    }
    if (this.binding != null) {
      this.binding.setValue(value, forUser);
      fireEvent(new UiValueChangeEvent(this, true));
    }
  }

  @Override
  protected void setValueNative(V value) {

    throw new IllegalStateException();
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    for (UiRegularWidget child : this.children) {
      child.setEnabled(enabled, UiVisibleFlags.PARENT);
    }
  }

}
