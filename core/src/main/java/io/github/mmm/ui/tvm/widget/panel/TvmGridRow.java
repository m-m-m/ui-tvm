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

  private int columnCount;

  /**
   * The constructor.
   */
  public TvmGridRow() {

    super(newTableRow());
  }

  @Override
  public int getChildCount() {

    return this.columnCount;
  }

  @Override
  public UiRegularWidget getChild(int index) {

    if ((this.firstCell != null) && (index >= 0) && (index < this.columnCount)) {
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
  public void setChild(UiRegularWidget child, int column, int colspan, int rowspan) {

    if ((column < 0) || (column > 1000)) {
      throw new IndexOutOfBoundsException(Integer.toString(column));
    }
    TableCell cell = null;
    if (this.firstCell == null) {
      if (child == null) {
        return;
      }
      cell = new TableCell(child);
      if (column == 0) {
        this.firstCell = cell;
      } else {
        this.firstCell = new TableCell(null);
        this.firstCell.setColspan(column);
        this.widget.appendChild(this.firstCell.td);
        this.firstCell.next = cell;
      }
      this.widget.appendChild(cell.td);
      this.columnCount = column + 1;
    } else {
      TableCell previous = null;
      cell = this.firstCell;
      int i = column;
      do {
        i = i - cell.colspan;
        if (i <= 0) {
          if (child == null) {
            if (i == 0) {
              clearCells(cell, previous);
            } // otherwise no cell to clear
            return;
          } else if (i == 0) {
            cell = cell.insertChild(child);
          } else {
            if ((cell.child != null) || ((colspan + i) > 0)) {
              throw new IndexOutOfBoundsException(Integer.toString(column)); // cells with colspan have to be disjunct
            }
            cell.setColspan(cell.colspan + i);
            cell = cell.insertChild(child);
            int rest = -i - colspan;
            if (rest > 0) {
              TableCell next = cell.insertChild(null);
              next.setColspan(rest);
            }
          }
        } else if (cell.next == null) {
          if (child == null) {
            return;
          }
          cell = cell.insertChild(null);
          cell.setColspan(i);
          cell = cell.insertChild(child);
          i = 0;
        } else {
          previous = cell;
          cell = cell.next;
        }
      } while (i > 0);
    }
    cell.setColspan(colspan);
    cell.setRowspan(rowspan);
  }

  private void clearCells(TableCell cell, TableCell previous) {

    if (cell.next == null) {
      this.widget.removeChild(cell.td);
      this.columnCount--;
      if (previous == null) {
        this.firstCell = null;
        this.columnCount = 0;
      } else {
        if (previous.child == null) {
          this.widget.removeChild(previous.td);
          previous = this.firstCell.findPrevious(previous);
        }
        previous.next = null;
      }
    } else {
      cell.setChild(null);
    }
  }

  @Override
  public UiRegularWidget removeChild(int index) {

    UiRegularWidget child = getChild(index);
    setChild(null, index);
    return child;
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

    private TableCell findPrevious(TableCell cell) {

      TableCell previous = this;
      while (previous != null) {
        if (previous.next == cell) {
          return previous;
        }
        previous = previous.next;
      }
      return null;
    }

    private TableCell insertChild(UiRegularWidget newChild) {

      TableCell cell = new TableCell(newChild);
      cell.next = this.next;
      this.next = cell;
      if (cell.next == null) {
        TvmGridRow.this.widget.appendChild(cell.td);
      } else {
        TvmGridRow.this.widget.insertBefore(cell.td, cell.next.td);
      }
      return cell;
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
        index += this.colspan;
      }
      return -1;
    }

    private TableCell get(int index) {

      TableCell cell = this;
      while ((index > 0) && (cell != null)) {
        cell = cell.next;
        index = index - this.colspan;
      }
      if (index < 0) {
        return null;
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
