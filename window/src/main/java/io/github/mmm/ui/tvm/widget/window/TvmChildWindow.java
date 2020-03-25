/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLBodyElement;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.base.placement.Direction;
import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.attribute.AttributeWriteMaximized;
import io.github.mmm.ui.datatype.UiSize;
import io.github.mmm.ui.datatype.UiWindowSizing;
import io.github.mmm.ui.tvm.widget.TvmAbstractWindow;
import io.github.mmm.ui.widget.window.UiAbstractWindow;
import io.github.mmm.ui.widget.window.UiChildWindow;

/**
 * Abstract base implementation of {@link TvmAbstractWindow} for windows inside the browser content (body).
 *
 * @since 1.0.0
 */
public abstract class TvmChildWindow extends TvmAbstractWindow<HTMLElement>
    implements UiChildWindow, AttributeWriteMaximized {

  private static final String STYLE_RESIZABLE = "resizable";

  private static final String STYLE_MOVABLE = "movable";

  private static UiAbstractWindow TOPMOST_WINDOW;

  private final Window window;

  private final HTMLElement headerElement;

  private final HTMLElement titleElement;

  private final HTMLElement controlsElement;

  private final HTMLButtonElement minimizeButton;

  private final HTMLButtonElement maximizeButton;

  private final HTMLButtonElement normalizeButton;

  private final HTMLButtonElement closeButton;

  private final HTMLElement borderRight;

  private final HTMLElement borderBottomRight;

  private final HTMLElement borderBottom;

  private final HTMLElement borderBottomLeft;

  private final HTMLElement borderLeft;

  private final HTMLElement borderTopLeft;

  private final HTMLElement borderTop;

  private final HTMLElement borderTopRight;

  private boolean visible;

  private double x;

  private double y;

  /** The z-index. */
  protected int z;

  private double width;

  private double height;

  private UiWindowSizing sizing;

  private boolean resizable;

  private boolean movable;

  private boolean closable;

  private String title;

  private int clientX;

  private int clientY;

  private Direction direction;

  private EventListener<?> pointerMoveListener;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmChildWindow(UiContext context) {

    this(context, Window.current());
  }

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param window the browser {@link Window}.
   */
  public TvmChildWindow(UiContext context, Window window) {

    super(context, newElement("ui-window"));
    this.window = window;
    this.headerElement = newElement("ui-wheader");
    this.titleElement = newElement("ui-wtitle");
    this.titleElement.addEventListener(EVENT_TYPE_POINTERDOWN, e -> onPointerDown(e, null), true);
    this.controlsElement = newElement("ui-wcontrols");
    this.minimizeButton = newButton();
    this.minimizeButton.setClassName("minimize");
    this.minimizeButton.addEventListener(EVENT_TYPE_CLICK, this::onMinimize);
    this.controlsElement.appendChild(this.minimizeButton);
    this.maximizeButton = newButton();
    this.maximizeButton.setClassName("maximize");
    this.maximizeButton.addEventListener(EVENT_TYPE_CLICK, this::onMaximize);
    this.controlsElement.appendChild(this.maximizeButton);
    this.normalizeButton = newButton();
    this.normalizeButton.setClassName("normalize");
    this.normalizeButton.setHidden(true);
    this.normalizeButton.addEventListener(EVENT_TYPE_CLICK, this::onNormalize);
    this.controlsElement.appendChild(this.normalizeButton);
    this.closeButton = newButton();
    this.closeButton.setClassName("close");
    this.closeButton.addEventListener(EVENT_TYPE_CLICK, this::onClose);
    this.controlsElement.appendChild(this.closeButton);

    this.headerElement.appendChild(this.titleElement);
    this.headerElement.appendChild(this.controlsElement);
    this.widget.appendChild(this.headerElement);
    this.widget.appendChild(this.content.getTopWidget());
    this.borderRight = createBorder(Direction.RIGHT);
    this.widget.appendChild(this.borderRight);
    this.borderBottomRight = createBorder(Direction.DOWN_RIGHT);
    this.widget.appendChild(this.borderBottomRight);
    this.borderBottom = createBorder(Direction.DOWN);
    this.widget.appendChild(this.borderBottom);
    this.borderBottomLeft = createBorder(Direction.DOWN_LEFT);
    this.widget.appendChild(this.borderBottomLeft);
    this.borderLeft = createBorder(Direction.LEFT);
    this.widget.appendChild(this.borderLeft);
    this.borderTopLeft = createBorder(Direction.UP_LEFT);
    this.widget.appendChild(this.borderTopLeft);
    this.borderTop = createBorder(Direction.UP);
    this.widget.appendChild(this.borderTop);
    this.borderTopRight = createBorder(Direction.UP_RIGHT);
    this.widget.appendChild(this.borderTopRight);
    this.window.getDocument().addEventListener(EVENT_TYPE_POINTERUP, this::onPointerUp);
    this.pointerMoveListener = this::onPointerMove;
    this.x = -1;
    this.y = -1;
    setResizable(true);
    setMovable(true);
    this.closable = true;
    this.sizing = UiWindowSizing.NORMAL;
    this.width = window.getInnerWidth() / 2;
    this.height = window.getInnerHeight() / 2;
  }

  private HTMLElement createBorder(Direction dir) {

    HTMLElement border = newElement("ui-wborder");
    border.setClassName(dir.getValue());
    border.addEventListener(EVENT_TYPE_POINTERDOWN, e -> onPointerDown(e, dir), true);
    return border;
  }

  @Override
  public HTMLElement getElement() {

    return this.widget;
  }

  @Override
  public boolean isResizable() {

    return this.resizable;
  }

  @Override
  public void setResizable(boolean resizable) {

    if (this.resizable == resizable) {
      return;
    }
    if (resizable) {
      getStyles().add(STYLE_RESIZABLE);
    } else {
      getStyles().remove(STYLE_RESIZABLE);
    }
    this.resizable = resizable;
  }

  @Override
  public boolean isMovable() {

    return this.movable;
  }

  @Override
  public void setMovable(boolean movable) {

    if (this.movable == movable) {
      return;
    }
    if (movable) {
      getStyles().add(STYLE_MOVABLE);
    } else {
      getStyles().remove(STYLE_MOVABLE);
    }
    this.movable = movable;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    setTextContent(this.titleElement, title);
    this.title = title;
  }

  @Override
  public boolean isClosable() {

    return this.closable;
  }

  @Override
  public void setClosable(boolean closable) {

    if (this.closable == closable) {
      return;
    }
    this.closeButton.setHidden(!closable);
    this.closable = closable;
  }

  @Override
  public double getWidthInPixel() {

    return this.width;
  }

  @Override
  public void setWidth(UiSize width) {

    this.width = UiSize.getSafe(width).toPixel(Window.current().getInnerWidth());
    updateStyle();
  }

  @Override
  public double getHeightInPixel() {

    return this.height;
  }

  @Override
  public void setHeight(UiSize height) {

    this.height = UiSize.getSafe(height).toPixel(Window.current().getInnerHeight());
    updateStyle();
  }

  @Override
  public void setSize(UiSize width, UiSize height) {

    this.width = UiSize.getSafe(width).toPixel(Window.current().getInnerWidth());
    this.height = UiSize.getSafe(height).toPixel(Window.current().getInnerHeight());
    updateStyle();
  }

  @Override
  public double getX() {

    return this.x;
  }

  @Override
  public void setX(double x) {

    this.x = x;
    updateStyle();
  }

  @Override
  public double getY() {

    return this.y;
  }

  @Override
  public void setY(double y) {

    this.y = y;
    updateStyle();
  }

  @Override
  public void setPosition(double x, double y) {

    this.x = x;
    this.y = y;
    updateStyle();
  }

  @Override
  public boolean isMaximized() {

    return (this.sizing == UiWindowSizing.MAXIMIZED);
  }

  @Override
  public void setMaximized(boolean maximized) {

    if (maximized) {
      if (this.sizing == UiWindowSizing.MINIMIZED) {
        this.minimizeButton.setHidden(false);
      }
      this.sizing = UiWindowSizing.MAXIMIZED;
      this.maximizeButton.setHidden(true);
      this.normalizeButton.setHidden(false);
      updateStyle();
    } else if (this.sizing == UiWindowSizing.MAXIMIZED) {
      this.sizing = UiWindowSizing.NORMAL;
      this.maximizeButton.setHidden(false);
      this.normalizeButton.setHidden(true);
      updateStyle();
    }
  }

  @Override
  public boolean isMinimized() {

    return (this.sizing == UiWindowSizing.MINIMIZED);
  }

  @Override
  public void setMinimized(boolean minimized) {

    if (minimized) {
      if (this.sizing == UiWindowSizing.MAXIMIZED) {
        this.maximizeButton.setHidden(false);
      }
      this.sizing = UiWindowSizing.MINIMIZED;
      this.content.setVisible(false);
      this.minimizeButton.setHidden(true);
      this.normalizeButton.setHidden(false);
      updateStyle();
    } else if (this.sizing == UiWindowSizing.MINIMIZED) {
      this.sizing = UiWindowSizing.MINIMIZED;
      this.content.setVisible(true);
      this.minimizeButton.setHidden(false);
      this.normalizeButton.setHidden(true);
      updateStyle();
    }
  }

  @Override
  protected final void setVisibleNative(boolean visible) {

    if (this.visible == visible) {
      return;
    }
    doSetVisible(visible);
    this.visible = visible;
  }

  /**
   * @param newVisible the new {@link #isVisible() visible flag} to set.
   */
  protected void doSetVisible(boolean newVisible) {

    HTMLBodyElement body = Window.current().getDocument().getBody();
    if (newVisible) {
      UiAbstractWindow parent = TOPMOST_WINDOW;
      if (parent == null) {
        parent = this.context.getMainWindow();
      }
      setParent(parent);
      int zParent = 1;
      if (parent instanceof TvmChildWindow) {
        zParent = ((TvmChildWindow) parent).z;
      }
      this.z = zParent + 10;
      updateStyle();
      body.appendChild(this.widget);
      TOPMOST_WINDOW = this;
    } else {
      body.removeChild(this.widget);
      UiAbstractWindow parent = getParent();
      if (parent == null) {
        parent = this.context.getMainWindow();
      }
      if (this == TOPMOST_WINDOW) {
        TOPMOST_WINDOW = parent;
      } else {
        UiAbstractWindow current = TOPMOST_WINDOW;
        while (current != null) {
          UiAbstractWindow currentParent = current.getParent();
          if (currentParent == this) {
            setParent(current, parent);
            break;
          }
          current = currentParent;
        }
      }
    }
  }

  private void updateStyle() {

    StringBuilder sb = new StringBuilder();
    if (this.sizing == UiWindowSizing.MAXIMIZED) {
      sb.append("left:0;right:0;width:100%;height:100%;");
    } else {
      sb.append("left:");
      if (this.x == -1) {
        this.x = (Window.current().getInnerWidth() - this.width) / 2.0;
      }
      if (this.x < 0) {
        this.x = 0;
      }
      sb.append(this.x);
      sb.append("px;top:");
      if (this.y == -1) {
        this.y = (Window.current().getInnerHeight() - this.height) / 2.0;
      }
      if (this.y < 0) {
        this.y = 0;
      }
      sb.append(this.y);
      sb.append("px;width:");
      sb.append(this.width);
      sb.append("px;");
      if (this.sizing == UiWindowSizing.NORMAL) {
        sb.append("height:");
        sb.append(this.height);
        sb.append("px;");
      }
    }
    sb.append("z-index:");
    sb.append(this.z);
    String style = sb.toString();
    this.widget.setAttribute(ATR_STYLE, style);
  }

  /**
   * @param e {@link Event} send when the minimize button is clicked.
   */
  protected void onMinimize(Event e) {

    setMinimized(true);
  }

  /**
   * @param e {@link Event} send when the maximize button is clicked.
   */
  protected void onMaximize(Event e) {

    setMaximized(true);
  }

  /**
   * @param e {@link Event} send when the normalize button is clicked.
   */
  protected void onNormalize(Event e) {

    setMaximized(false);
  }

  /**
   * @param e {@link Event} send when the close button is clicked.
   */
  protected void onClose(Event e) {

    close();
  }

  private void onPointerUp(Event e) {

    this.window.getDocument().removeEventListener(EVENT_TYPE_POINTERMOVE, this.pointerMoveListener);
  }

  private void onPointerMove(Event e) {

    MouseEvent me = e.cast();
    int oldX = this.clientX;
    this.clientX = me.getClientX();
    int movementX = this.clientX - oldX;
    int oldY = this.clientY;
    this.clientY = me.getClientY();
    int movementY = this.clientY - oldY;
    if ((movementX == 0) && (movementY == 0)) {
      return;
    }
    if (this.direction == null) {
      this.x = clip(this.x + movementX, 0, this.window.getInnerWidth() - 20);
      this.y = clip(this.y + movementY, 0, this.window.getInnerHeight() - 20);
    } else {
      if (this.direction.isLeft()) {
        double newX = this.x + movementX;
        if (newX < 0) {
          this.width = this.width + this.x;
          this.x = 0;
        } else {
          this.x = newX;
          this.width = this.width - movementX;
        }
      } else if (this.direction.isRight()) {
        this.width = clip(this.width + movementX, 200, this.window.getInnerWidth() - this.x);
      }
      if (this.direction.isUp()) {
        double newY = this.y + movementY;
        if (newY < 0) {
          this.height = this.height + this.y;
          this.y = 0;
        } else {
          this.y = newY;
          this.height = this.height - movementY;
        }
      } else if (this.direction.isDown()) {
        this.height = clip(this.height + movementY, 200, this.window.getInnerHeight() - this.y);
      }

    }
    updateStyle();
  }

  /**
   * @param e {@link Event} send when the close button is clicked.
   * @param dir the {@link Direction} to resize or {@code null} for move.
   */
  private void onPointerDown(Event e, Direction dir) {

    if ((dir == null) && !this.movable) {
      return;
    }
    if ((dir != null) && !this.resizable) {
      return;
    }
    e.preventDefault();
    MouseEvent me = e.cast();
    this.clientX = me.getClientX();
    this.clientY = me.getClientY();
    this.direction = dir;
    this.window.getDocument().addEventListener(EVENT_TYPE_POINTERMOVE, this.pointerMoveListener);
  }

  private static double clip(double d, double min, double max) {

    if (d < min) {
      return min;
    }
    if (d > max) {
      return max;
    }
    return d;
  }
}
