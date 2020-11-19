/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.composite;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.datatype.UiEnabledFlags;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.composite.UiRemovableComposite;

/**
 * Implementation of {@link UiRemovableComposite} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @param <C> type of the {@link #getChild(int) child widgets}.
 * @since 1.0.0
 */
public abstract class TvmRemovableComposite<W extends HTMLElement, C extends UiWidget> extends TvmComposite<W, C>
    implements UiRemovableComposite<C> {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmRemovableComposite(W widget) {

    super(widget);
  }

  /**
   * @return the {@link HTMLElement} that actually contains the children.
   */
  protected HTMLElement getCompositeElement() {

    return this.widget;
  }

  @Override
  public boolean removeChild(C child) {

    boolean removed = this.children.remove(child);
    if (removed) {
      removeChildWidget(child);
      setParent(child, null);
    }
    return removed;
  }

  @Override
  public C removeChild(int index) {

    C child = this.children.remove(index);
    removeChildWidget(child);
    setParent(child, null);
    return child;
  }

  /**
   * @param child the widget to remove as child from the DOM.
   * @see #removeChild(UiWidget)
   * @see #removeChild(int)
   */
  protected void removeChildWidget(C child) {

    getCompositeElement().removeChild(getTopNode(child));
  }

  /**
   * @param child the child to add.
   * @param index the index where to insert the child.
   */
  protected void addChild(C child, int index) {

    setParent(child, this);
    int i = index;
    if (i >= 0) {
      i = i + getChildIndexOffset();
    }
    addChildWidget(child, i);
    if (index == -1) {
      this.children.add(child);
    } else {
      this.children.add(index, child);
    }
  }

  /**
   * @param child the widget to add as child to the DOM.
   * @param index the index where to insert the child.
   * @see TvmMutableComposite#addChild(UiWidget, int)
   */
  protected void addChildWidget(C child, int index) {

    insertAt(getCompositeElement(), getTopNode(child), index);
  }

  /**
   * @return the offset for the child index in the {@link #getCompositeElement() composite element}. Can be overridden
   *         to return the number of internal child elements.
   */
  protected int getChildIndexOffset() {

    return 0;
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    for (C child : this.children) {
      child.setEnabled(enabled, UiEnabledFlags.PARENT);
    }
  }

}
