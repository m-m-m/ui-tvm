/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.composite;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.api.widget.composite.UiMutableComposite;

/**
 * Implementation of {@link UiMutableComposite} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @param <C> type of the {@link #getChild(int) child widgets}.
 * @since 1.0.0
 */
public abstract class TvmMutableComposite<W extends HTMLElement, C extends UiWidget> extends TvmRemovableComposite<W, C>
    implements UiMutableComposite<C> {

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmMutableComposite(W widget) {

    super(widget);
  }

  @Override
  public void addChild(C child, int index) {

    addChildWidget(child, index);
    if (index == -1) {
      this.children.add(child);
    } else {
      this.children.add(index, child);
    }
    setParent(child, this);
  }

}
