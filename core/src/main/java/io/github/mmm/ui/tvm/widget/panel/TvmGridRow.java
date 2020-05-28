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

  private int cellCount;

  /**
   * The constructor.
   */
  public TvmGridRow() {

    super(newTableRow());
  }

  @Override
  public int getChildCount() {

    return this.cellCount;
  }

  @Override
  public UiRegularWidget getChild(int index) {

    if ((this.firstCell != null) && (index >= 0) && (index < this.cellCount)) {
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

    setOrAddChild(false, child, index, colspan, rowspan);
  }

  @Override
  public void setChild(UiRegularWidget child, int index, int colspan, int rowspan) {

    setOrAddChild(true, child, index, colspan, rowspan);
  }

  private void setOrAddChild(boolean set, UiRegularWidget child, int index, int colspan, int rowspan) {

    int row = index;
    if (row == -1) {
      row = this.cellCount;
    }
    TableCell cell = null;
    if (this.firstCell == null) {
      if (row == 0) {
        cell = new TableCell(child);
        this.firstCell = cell;
      } else {
        if (!set) {
          throw new IndexOutOfBoundsException(index);
        }
        this.firstCell = new TableCell(null);
      }
      this.widget.appendChild(this.firstCell.td);
      this.cellCount = 1;
    }
    if (cell == null) {
      TableCell currentCell = this.firstCell;
      int i = 1;
      if (!set) {
        i = 2;
      }
      while (i < row) {
        if (currentCell.next == null) {
          if (!set) {
            throw new IndexOutOfBoundsException(index);
          }
          currentCell.next = new TableCell(null);
          this.widget.appendChild(currentCell.next.td);
        }
        currentCell = currentCell.next;
        i++;
      }
      if (set) {
        cell = currentCell;
        cell.setChild(child);
      } else {
        cell = new TableCell(child);
        if (currentCell.next == null) {
          this.widget.appendChild(cell.td);
        } else {
          this.widget.insertBefore(cell.td, currentCell.next.td);
          cell.next = currentCell.next;
        }
        currentCell.next = cell;
      }
    }
    cell.setColspan(colspan);
    cell.setRowspan(rowspan);
  }

  @Override
  public UiRegularWidget removeChild(int index) {

    return null;
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
      this.td = newTableCell();
      if (child != null) {
        this.td.appendChild(getTopNode(child));
      }
      this.colspan = 1;
      this.rowspan = 1;
    }

    private void setChild(UiRegularWidget child) {

      if (this.child == child) {
        return;
      }
      if (this.child != null) {
        setParent(this.child, null);
        this.td.removeChild(getTopNode(this.child));
      }
      this.child = child;
      if (this.child != null) {
        setParent(this.child, TvmGridRow.this);
        this.td.appendChild(getTopNode(this.child));
      }
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
