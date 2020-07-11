/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.panel;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.UiRegularWidget;
import io.github.mmm.ui.api.widget.panel.UiGridRow;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiGridRow} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmGridRow extends TvmWidgetHtmlElement<HTMLElement> implements UiGridRow {

  private TableCell firstCell;

  private int childCount;

  /**
   * The constructor.
   */
  public TvmGridRow() {

    super(newTableRow());
  }

  @Override
  public int getChildCount() {

    return this.childCount;
  }

  @Override
  public UiRegularWidget getChild(int index) {

    if ((this.firstCell != null) && (index >= 0) && (index < this.childCount)) {
      TableCell cell = this.firstCell.get(index);
      if (cell != null) {
        return cell.child;
      }
    }
    return null;
  }

  @Override
  public int getChildIndex(UiRegularWidget child) {

    if (this.firstCell == null) {
      return -1;
    }
    return this.firstCell.indexOf(child);
  }

  @Override
  public void addChild(UiRegularWidget child, int index, int colspan, int rowspan) {

    if (index == -1) {
      index = this.childCount;
    }
    TableCell cell = new TableCell(child);
    cell.setColspan(colspan);
    cell.setRowspan(rowspan);
    if (this.firstCell == null) {
      if (index == 0) {
        this.firstCell = cell;
      } else {
        throw new IndexOutOfBoundsException(Integer.toString(index));
      }
      this.widget.appendChild(this.firstCell.td);
    } else {
      TableCell previous = null;
      TableCell next = this.firstCell;
      int i = index;
      while (i > 0) {
        if (next == null) {
          throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        previous = next;
        next = next.next;
        i--;
      }
      if (previous == null) {
        this.firstCell = cell;
      } else {
        previous.next = cell;
      }
      if (next == null) {
        this.widget.appendChild(cell.td);
      } else {
        cell.next = next;
        this.widget.insertBefore(cell.td, next.td);
      }
    }
    this.childCount++;
  }

  @Override
  public UiRegularWidget removeChild(int index) {

    if (index == -1) {
      index = this.childCount - 1;
    }
    TableCell cell;
    if (index == 0) {
      cell = this.firstCell;
      this.firstCell = this.firstCell.next;
    } else if ((index > 0) && (index < this.childCount)) {
      TableCell previous = null;
      cell = this.firstCell;
      int i = 0;
      while (i < index) {
        previous = cell;
        cell = cell.next;
        i++;
      }
      if (previous != null) { // pointless check to prevent warning
        previous.next = cell.next;
      }
    } else {
      throw new IndexOutOfBoundsException(Integer.toString(index));
    }
    this.widget.removeChild(cell.td);
    this.childCount--;
    setParent(cell.child, null);
    return cell.child;
  }

  private class TableCell {

    private final HTMLElement td;

    private UiRegularWidget child;

    private int colspan;

    private int rowspan;

    private TableCell next;

    private TableCell(UiRegularWidget child) {

      super();
      this.child = child;
      this.td = newTableDataCell();
      if (child != null) {
        this.td.appendChild(getTopNode(child));
      }
      this.colspan = 1;
      this.rowspan = 1;
    }

    private int indexOf(UiRegularWidget childWidget) {

      int index = 0;
      TableCell cell = this;
      while (cell != null) {
        if (cell.child == childWidget) {
          return index;
        }
        cell = cell.next;
        index++;
      }
      return -1;
    }

    private TableCell get(int index) {

      TableCell cell = this;
      while ((index > 0) && (cell != null)) {
        cell = cell.next;
      }
      return cell;
    }

    private void setColspan(int colspan) {

      if (colspan < 1) {
        colspan = 1;
      }
      if (this.colspan == colspan) {
        return;
      }
      this.colspan = colspan;
      this.td.setAttribute(ATR_COLSPAN, Integer.toString(colspan));
    }

    private void setRowspan(int rowspan) {

      if (rowspan < 1) {
        rowspan = 1;
      }
      if (this.rowspan == rowspan) {
        return;
      }
      this.rowspan = rowspan;
      this.td.setAttribute(ATR_ROWSPAN, Integer.toString(rowspan));
    }

  }

}
