/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.composite.UiSlot;
import io.github.mmm.ui.api.widget.panel.UiBorderPanel;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.ui.tvm.widget.composite.TvmSlot;

/**
 * Implementation of {@link UiBorderPanel} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBorderPanel extends TvmWidgetHtmlElement<HTMLElement> implements UiBorderPanel {

  private HTMLElement content;

  private UiSlot center;

  private UiSlot top;

  private UiSlot left;

  private UiSlot bottom;

  private UiSlot right;

  private int childCount;

  /**
   * The constructor.
   */
  public TvmBorderPanel() {

    super(newElement("ui-borderpanel"));
  }

  @Override
  public int getChildCount() {

    return this.childCount;
  }

  @Override
  public int getChildIndex(UiSlot child) {

    if (this.center == child) {
      return INDEX_CENTER;
    } else if (this.top == child) {
      return INDEX_TOP;
    } else if (this.left == child) {
      return INDEX_LEFT;
    } else if (this.bottom == child) {
      return INDEX_BOTTOM;
    } else if (this.right == child) {
      return INDEX_RIGHT;
    }
    return -1;
  }

  /**
   * @return content
   */
  public HTMLElement getContent() {

    if (this.content == null) {
      this.content = newElement("ui-content");
      if (this.top == null) {
        insertFirst(this.widget, this.content);
      } else {
        insertAt(this.widget, this.content, 1);
      }
    }
    return this.content;
  }

  private void ensureChildCount(int minIndex) {

    int count = minIndex + 1;
    if (this.childCount < count) {
      this.childCount = count;
    }
  }

  @Override
  public UiSlot getTop() {

    if (this.top == null) {
      HTMLElement header = newHeader();
      this.top = new TvmSlot(header);
      insertFirst(this.widget, header);
      ensureChildCount(INDEX_TOP);
    }
    return this.top;
  }

  @Override
  public UiSlot getBottom() {

    if (this.bottom == null) {
      HTMLElement footer = newFooter();
      this.bottom = new TvmSlot(footer);
      this.widget.appendChild(footer);
      ensureChildCount(INDEX_BOTTOM);
    }
    return this.bottom;
  }

  @Override
  public UiSlot getLeft() {

    if (this.left == null) {
      HTMLElement leftElement = newASide();
      this.left = new TvmSlot(leftElement);
      insertFirst(getContent(), leftElement);
      ensureChildCount(INDEX_LEFT);
    }
    return this.left;
  }

  @Override
  public UiSlot getRight() {

    if (this.right == null) {
      HTMLElement rightElement = newASide();
      this.right = new TvmSlot(rightElement);
      getContent().appendChild(rightElement);
      ensureChildCount(INDEX_RIGHT);
    }
    return this.right;
  }

  @Override
  public UiSlot getCenter() {

    if (this.center == null) {
      HTMLElement main = newMain();
      this.center = new TvmSlot(main);
      if (this.left == null) {
        insertFirst(getContent(), main);
      } else {
        insertAt(getContent(), main, 1);
      }
      ensureChildCount(INDEX_CENTER);
    }
    return this.center;
  }

}
