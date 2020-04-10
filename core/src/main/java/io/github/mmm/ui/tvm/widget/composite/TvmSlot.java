/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.composite;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.composite.UiSlot;

/**
 * Implementation of {@link UiSlot} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmSlot extends TvmComposite<HTMLElement, UiRegularWidget> implements UiSlot {

  private UiRegularWidget child;

  /**
   * The constructor.
   */
  public TvmSlot() {

    super(newElement("ui-slot"));
  }

  @Override
  public void setChild(UiRegularWidget child) {

    if (this.child == child) {
      return;
    }
    if (this.child != null) {
      setParent(this.child, null);
      this.widget.removeChild(getTopNode(this.child));
    }
    this.widget.appendChild(getTopNode(child));
    this.child = child;
  }

  @Override
  public UiRegularWidget getChild() {

    return this.child;
  }

}
